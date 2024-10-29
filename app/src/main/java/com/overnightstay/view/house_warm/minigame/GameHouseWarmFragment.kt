package com.overnightstay.view.house_warm.minigame

import android.animation.Animator
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.overnightstay.R
import com.overnightstay.databinding.FragmentGameHouseWarmBinding
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameHouseWarmFragment : Fragment() {

    private var _binding: FragmentGameHouseWarmBinding? = null
    private val binding get() = _binding!!

    private lateinit var tent: View
    private lateinit var countTextView: TextView
    private var screenWidth = 0
    private var screenHeight = 0
    private var tentX = 0f
    private var tentWidth = 0
    private var count = 0

    private val arrayImages = arrayOf(
        R.drawable.img_house_warm_game_human_01,
        R.drawable.img_house_warm_game_human_02,
        R.drawable.img_house_warm_game_human_03,
        R.drawable.img_house_warm_game_human_04,
        R.drawable.img_house_warm_game_human_05,
        R.drawable.img_house_warm_game_snowman,
        R.drawable.img_house_warm_game_people_01,
        R.drawable.img_house_warm_game_people_02,
        R.drawable.img_house_warm_game_people_03,
        R.drawable.img_house_warm_game_people_04,
        R.drawable.img_house_warm_game_people_05
    )

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameHouseWarmBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tent = binding.imgTent
        countTextView = binding.count

        // Получаем размеры экрана
        tent.post {
            screenWidth = tent.rootView.width
            screenHeight = tent.rootView.height
            tentWidth = tent.width
        }

        // Перемещение палатки
        tent.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_MOVE -> {
                    tentX = event.rawX - tent.width / 2
                    if (tentX < 0) tentX = 0f
                    if (tentX > screenWidth - tent.width) tentX =
                        (screenWidth - tent.width).toFloat()

                    tent.x = tentX
                }
            }
            true
        }

        // Запуск падения объектов
        startFallingObjects()

        binding.btnFinish.setOnClickListener {
            findNavController().navigate(R.id.action_gameHouseWarmFragment_to_backpackFragment)
        }
    }

    private var isPaused = false
    private var delayForObjectsCreation = 1000L
    private val activeAnimations = mutableListOf<ObjectAnimator>()

    @SuppressLint("SetTextI18n")
    private fun createAndAnimateFallingObject() {

        if (isPaused) return

        // Динамически создаем новый падающий объект
        val fallingObject = ImageView(requireContext())

        // Случайно выбираем одно из изображений из массива
        val randomImage = arrayImages[Random.nextInt(arrayImages.size)]
        fallingObject.setImageResource(randomImage)

        // Добавляем объект на экран
        val rootLayout = binding.main
        rootLayout.addView(fallingObject)

        // Устанавливаем случайную начальную позицию по горизонтали
        fallingObject.x = Random.nextInt(0, binding.playingField.width - fallingObject.width).toFloat()
        fallingObject.y = 0f

        // Анимация падения объекта с использованием ObjectAnimator
        val fallAnimator =
            ObjectAnimator.ofFloat(fallingObject, "translationY", screenHeight.toFloat())
        fallAnimator.duration = 3000 // Время падения

        // Сохраняем анимацию в списке активных анимаций
        activeAnimations.add(fallAnimator)

        fallAnimator.addUpdateListener {
            // Постоянно проверяем, пойман ли объект корзиной во время падения
            if (checkCatch(fallingObject)) {
                // Проверяем, если пойман "особенный" объект
                when (randomImage) {
                    R.drawable.img_house_warm_game_snowman -> {
                        // Останавливаем создание новых объектов
                        pauseObjectCreation()

                        // Показать элемент TextView
                        showAndHideMessage()

                        // Убираем объект с экрана
                        rootLayout.removeView(fallingObject)

                        // Возобновляем процесс создания объектов через
                        lifecycleScope.launch(Dispatchers.Main) {
                            delay(2000)
                            resumeObjectCreation()
                        }

                    }

                    R.drawable.img_house_warm_game_people_01, R.drawable.img_house_warm_game_people_02, R.drawable.img_house_warm_game_people_03, R.drawable.img_house_warm_game_people_04, R.drawable.img_house_warm_game_people_05 -> count += 2
                    else -> count++
                }

                rootLayout.removeView(fallingObject)
                fallAnimator.cancel()
                if (count >= 50) {
                    // Останавливаем создание новых объектов
                    pauseObjectCreation()
                    countTextView.text = "50"
                    // Останавливаем все активные анимации
                    for (animator in activeAnimations) {
                        animator.cancel()
                    }
                    binding.catMessage.setImageResource(R.drawable.img_house_warm_game_cat_02)
                    binding.catMessage.visibility = View.VISIBLE
                    binding.gameEnd.visibility = View.VISIBLE
                    binding.rug.visibility = View.VISIBLE
                } else countTextView.text = "$count"
            }
        }

        fallAnimator.start()

        // Если объект не был пойман, удаляем его в конце анимации
        fallAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                rootLayout.removeView(fallingObject)
            }

            override fun onAnimationCancel(animation: Animator) {
                rootLayout.removeView(fallingObject)
            }

            override fun onAnimationRepeat(animation: Animator) {}
        })
    }

    // Проверяем, пойман ли объект корзиной
    private fun checkCatch(fallingObject: View): Boolean {
        val tentRect = Rect()
        tent.getHitRect(tentRect) // Получаем фактическую область корзины

        val objectRect = Rect()
        fallingObject.getGlobalVisibleRect(objectRect) // Получаем фактическую область падающего объекта

        return Rect.intersects(tentRect, objectRect) // Проверяем пересечение
    }

    // Останавливаем создание новых объектов
    private fun pauseObjectCreation() {
        isPaused = true
    }

    // Возобновляем создание новых объектов
    private fun resumeObjectCreation() {
        isPaused = false
    }

    // Показать и скрыть сообщение
    private fun showAndHideMessage() {
        // Показать элемент
        binding.catMessage.visibility = View.VISIBLE
        // Скрыть элемент
        lifecycleScope.launch(Dispatchers.Main) {
            delay(2000)
            binding.catMessage.visibility = View.GONE
        }
    }

    private fun startFallingObjects() {
        lifecycleScope.launch(Dispatchers.Main) {
            while (true) {
                // Задержка перед созданием следующего объекта
                delay(delayForObjectsCreation)
                // Проверяем, можно ли создавать объекты
                if (!isPaused) {
                    createAndAnimateFallingObject()
                }
            }
        }
    }

}