package com.overnightstay.view.mini_games

import android.animation.ValueAnimator
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.overnightstay.R
import com.overnightstay.databinding.FragmentGameToFeedTheNeedyBinding
import com.overnightstay.utils.animateCharacterByCharacter2
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameToFeedTheNeedyFragment : Fragment() {
    val places = PlacesPersons(
        Persons.entries.toTypedArray().random(),
        Persons.entries.toTypedArray().random(),
        Persons.entries.toTypedArray().random()
    )

    private var _binding: FragmentGameToFeedTheNeedyBinding? = null
    private val binding get() = _binding!!

    val currentAnimator: ValueAnimator by lazy {
        ValueAnimator.ofInt(0, 0)
    }

    private var array = mutableListOf(
        "В Ночлежке мы следим, чтобы наши сотрудники и волонтеры чувствовали себя\nкомфортно, отдыхали.\nЯ тоже предлагаю тебе отдохнуть. Давай сыграем в мини игру.",
        "Нужно накормить нуждающихся. Будь внимателен. На подносе должны быть все три\nсоставляющих ужина. Нужно поторопиться, у клиентов есть шкала ожидания.\nЖелаю удачи!"
    )
    private var count: Int = 0

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameToFeedTheNeedyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.text.animateCharacterByCharacter2(text = array[0], animator = currentAnimator)
        binding.dialogNext.isClickable = true

        binding.dialogNext.setOnClickListener {
            if (currentAnimator.isRunning) {
                currentAnimator.end()
                return@setOnClickListener
            }

            count++

            when (count) {
                1 -> {
                    binding.main.setBackgroundResource(R.drawable.bg_game_to_feed_the_needy)
                    binding.text.animateCharacterByCharacter2(
                        text = array[count],
                        animator = currentAnimator
                    )
                    lifecycleScope.launch {
                        delay(25L * array[count].length.toLong())
                    }
                }

                2 -> {
                    with(binding) {
                        theNeedy1.setImageResource(places.left.idImg)
                        theNeedy2.setImageResource(places.center.idImg)
                        theNeedy3.setImageResource(places.right.idImg)

                        catStatus.visibility = View.GONE
                        rectangle.visibility = View.GONE
                        statusName.visibility = View.GONE
                        text.visibility = View.GONE
                        dialogNext.visibility = View.GONE
                        needy.visibility = View.VISIBLE
                        teapod.visibility = View.VISIBLE
                        cup1.visibility = View.VISIBLE
                        cup2.visibility = View.VISIBLE
                        cup3.visibility = View.VISIBLE
                        meal1.visibility = View.VISIBLE
                        meal2.visibility = View.VISIBLE
                        meal3.visibility = View.VISIBLE
                        bread1.visibility = View.VISIBLE
                        bread2.visibility = View.VISIBLE
                        bread3.visibility = View.VISIBLE
                        map.visibility = View.VISIBLE
                    }
                }
            }
            binding.map.setOnClickListener {
                findNavController().navigate(R.id.action_gameToFeedTheNeedyFragment_to_locationMapFragment)
            }
        }

        binding.rules.setOnClickListener {
            findNavController().navigate(R.id.action_gameToFeedTheNeedyFragment_to_contentsOfBookFragment)
        }

        initBtnListeners()
    }

    private fun initBtnListeners() = with(binding) {
        listOf(cup1, cup2, cup3).forEach { cup -> cup.setOnClickListener { add_cup(it) } }
        listOf(meal1, meal2, meal3).forEach { meal -> meal.setOnClickListener { add_meal(it) } }
        listOf(
            bread1,
            bread2,
            bread3
        ).forEach { bread -> bread.setOnClickListener { add_bread(it) } }
        listOf(cup, meal, bread).forEach { it.setOnClickListener { checkTray() } }
    }

    private fun checkTray() {
        if (!binding.cup.isGone && !binding.meal.isGone && !binding.bread.isGone) {
            binding.cup.isGone = true
            binding.meal.isGone = true
            binding.bread.isGone = true

            binding.cup1.isGone = false
            binding.cup2.isGone = false
            binding.cup3.isGone = false
            binding.meal1.isGone = false
            binding.meal2.isGone = false
            binding.meal3.isGone = false
            binding.bread1.isGone = false
            binding.bread2.isGone = false
            binding.bread3.isGone = false

            eat_needy()
            binding.theNeedy1.setImageResource(places.left.idImg)
            binding.theNeedy2.setImageResource(places.center.idImg)
            binding.theNeedy3.setImageResource(places.right.idImg)
        }
    }

    private fun add_cup(view: View) {
        if (binding.cup.isGone) {
            view.isGone = true
            binding.cup.isGone = false
        }
    }

    private fun add_meal(view: View) {
        if (binding.meal.isGone) {
            view.isGone = true
            binding.meal.isGone = false
        }
    }

    private fun add_bread(view: View) {
        if (binding.bread.isGone) {
            view.isGone = true
            binding.bread.isGone = false
        }
    }

    private fun eat_needy() {
        places.center =places.left
        places.left = places.right
        places.right = Persons.entries.toTypedArray().random()
    }

    data class PlacesPersons(var left: Persons, var center: Persons, var right: Persons)

    enum class Persons(val idImg: Int) {
        NEEDY1(R.drawable.img_the_needy_1),
        NEEDY2(R.drawable.img_the_needy_2),
        NEEDY3(R.drawable.img_the_needy_3),
        NEEDY4(R.drawable.img_the_needy_4),
        NEEDY5(R.drawable.img_the_needy_5),
        NEEDY6(R.drawable.img_the_needy_6);
    }
}