package com.overnightstay.view.house_shower

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
import com.google.android.material.snackbar.Snackbar
import com.overnightstay.R
import com.overnightstay.databinding.FragmentDialogInTheBuilding2Binding
import com.overnightstay.utils.animateCharacterByCharacter2
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DialogInTheBuilding2Fragment : Fragment() {

    var stress = Stress.GREEN

    private var _binding: FragmentDialogInTheBuilding2Binding? = null
    private val binding get() = _binding!!

    val currentAnimator: ValueAnimator by lazy {
        ValueAnimator.ofInt(0, 0)
    }

    private var answer = mutableListOf(
        mutableListOf(2, 1, 0),
        mutableListOf(4, 1, 0),
        mutableListOf(6, 2, 0),
        mutableListOf(8, 2, 0),
        mutableListOf(10, 1, 0),
    )

    private var array = mutableListOf(
        "Давайте поближе познакомимся с работой Неравнодуша. Сегодня к вам подошёл Владимир. Ваша задача — рассказать о возможностях, которые мы предоставляем, и о правилах, которые нужно соблюдать.",
        "Здравствуйте. Могу я здесь помыться?","",
        "Спасибо. Я раньше не слышал о вас. Что такое Неравнодуш? Это бесплатно?","",
        "Могу я снова сюда прийти? Какие у вас правила?","",
        "Здесь наверное очень грязно? Столько людей приходит!","",
        "Я хочу, чтобы мне помогли вернуться к обычной жизни. С чего начать?","",
        "Спасибо за помощь!",
        "Задание выполнено отлично! Ты молодец!"
    )

    private val arrayRadioButtonText1 = mutableListOf(
        "","","Конечно, Владимир. Пройдите к дежурному. Он вам всё расскажет и покажет.","",
        "Это бесплатно. Здесь вы можете помыться, подстричься, вам выдадут туалетные принадлежности, и если вы хотите постирать одежду, вам выдадут чистый халат.","",
        "Нужно обязательно принести документы, иначе мы не сможем вас обслужить.","",
        "Мы тщательно обрабатываем Неравнодуш один раз в месяц.","",
        "Для начала вам нужно прийти в консультационный центр и обратится к социальному работнику. Рассказать, что с вами произошло. Вам обязательно помогут."
    )

    private val arrayRadioButtonText2 = mutableListOf(
        "","","Это только для тех, кто уже зарегистрировался у нас.","",
        "Это только для тех, кто уже зарегистрировался у нас.","",
        "Вы можете посещать Неравнодуш один раз в неделю. Дежурный на входе проверит вашу температуру","",
        "У нас тщательная обработка душевых кабинок, туалетов, зоны ожидания: уборка после каждого клиента, уборка в конце рабочего дня, и генеральная уборка один раз в месяц.","",
        "Вам нужно прийти в консультационный центр и потребовать помощи."
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
        _binding = FragmentDialogInTheBuilding2Binding.inflate(inflater, container, false)
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

            if (!binding.radioGroup.isGone) {
                if (!binding.radioButton1.isChecked && !binding.radioButton2.isChecked) {
                    Snackbar.make(
                        binding.root,
                        "Выберите одну из опций.",
                        Snackbar.LENGTH_LONG
                    ).show()
                    return@setOnClickListener
                }
                val index = answer.indexOfFirst { it[0] == count }
                if (index != -1) {
                    if (binding.radioButton1.isChecked) {
                        answer[index][2] = 1
                    } else {
                        answer[index][2] = 2
                    }
                    if (answer[index][1] != answer[index][2]) {

                        /*                        Snackbar.make(
                            binding.root,
                            "Ответ не правильный.",
                            Snackbar.LENGTH_LONG
                        ).show()*/

                        stress.getNextStress()?.let {
                            binding.stress.setImageResource(it.idImg)
                            stress = it
                        }
                    }
                }
                println("answer $answer")
            }

            count++

            if (count < array.size) {
                when (count) {
                    1 -> {
                        with(binding) {
                            statusName.text = "Владимир"
                            vladimir02.visibility = View.GONE
                            catStatus.visibility = View.GONE
                            vladimir.visibility = View.VISIBLE
                            bgMichael.visibility = View.VISIBLE
                        }
                    }
                    2, 4, 6, 8, 10 -> {
                        with(binding) {
                            rectangle.setBackgroundResource(R.drawable.dialog_house_shower_user)
                            radioGroup.clearCheck()
                            radioGroup.visibility = View.VISIBLE
                            statusName.visibility = View.GONE
                            text.visibility = View.GONE
                            userName.visibility = View.VISIBLE
                            radioButton1.text = arrayRadioButtonText1[count]
                            radioButton2.text = arrayRadioButtonText2[count]
                        }
                    }
                    3, 5, 7, 9, 11 -> {
                        with(binding) {
                            rectangle.setBackgroundResource(R.drawable.dialog_house_shower_status)
                            radioGroup.visibility = View.GONE
                            statusName.visibility = View.VISIBLE
                            text.visibility = View.VISIBLE
                            userName.visibility = View.GONE
                        }
                    }
                    12 -> {
                        with(binding) {
                            statusName.text = "Статус"
                            vladimir.visibility = View.GONE
                            bgMichael.visibility = View.GONE
                            vladimir02.visibility = View.VISIBLE
                            catStatus.visibility = View.VISIBLE
                        }
                    }
                }

                binding.text.animateCharacterByCharacter2(
                    text = array[count],
                    animator = currentAnimator
                )

            } else if (stress == Stress.GREEN) {
                findNavController().navigate(R.id.action_dialogInTheBuilding2Fragment_to_locationMapFragment)
            } else {
                findNavController().navigate(R.id.action_dialogInTheBuilding2Fragment_to_contentsOfBookFragment)
            }
        }

        binding.rules.setOnClickListener {
            findNavController().navigate(R.id.action_dialogInTheBuildingFragment_to_contentsOfBookFragment)
        }
    }

    enum class Stress(val idImg: Int, val pos: Int) {
        GREEN(R.drawable.img_stress, 0),
        YELLOW(R.drawable.img_stress_yellow, 1),
        ORANGE(R.drawable.img_stress_orange, 2),
        RED(R.drawable.img_stress_red, 3);

        fun getNextStress(): Stress? {
            return if (pos < entries.size - 1) entries[pos + 1] else
                if (pos == 3) entries[pos] else null
        }
    }

}