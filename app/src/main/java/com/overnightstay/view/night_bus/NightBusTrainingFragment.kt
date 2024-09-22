package com.overnightstay.view.night_bus

import android.animation.ValueAnimator
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.marginStart
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.overnightstay.R
import com.overnightstay.databinding.FragmentNightBusTrainingBinding
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NightBusTrainingFragment : Fragment() {

    private var _binding: FragmentNightBusTrainingBinding? = null
    private val binding get() = _binding!!

    private var array = mutableListOf(
        "Давайте поближе познакомимся с работой Ночного автобуса. Сегодня к вам подошёл\nМихаил. Ваша задача — не только накормить его, но и рассказать о возможностях,\nкоторые мы предоставляем, и о правилах, которые нужно соблюдать.",
        "Хочу обратить твое внимание на интерфейс, в нем находится шкала стресса. \nЗдесь ошибки будут накапливаться в стресс.",
        "Если шкала будет заполнена, тебе необходимо посетить КНИГУ ПРАВИЛ.\nДомик – это твой опыт. Чуть позже я подробнее расскажу тебе о нём.\nА сейчас, давай поможем Михаилу !",
        "Здравствуйте. Можно мне тарелку супа? Я не ел с утра...",
            "п",
        "Спасибо за еду. Я раньше не слышал о вас. Что это за автобус?",
            "с",
        "А что нужно, чтобы снова сюда прийти? Какие у вас правила?",
            "о",
        "Не переживай и не расстраивайся, если будут ошибки! Наш психолог поможет тебе\nсправиться со стрессом",
        "Знаете, в последнее время я стал хуже себя чувствовать.\nПростудился, но идти в больницу боюсь и не хочу.\nНе знаю, что делать. Дайте какую–то таблетку.",
            "с",
        "Я уже давно на улице и даже не знаю, как вернуться к обычной жизни. С чего начать?",
            "й ",
        "Спасибо за помощь... Я подумаю над вашими словами.",
            ".",
        "Отличная работа! Ты молодец!",
        "Мы называем Ночной автобус «Точкой входа» в организацию. Придя за тарелкой супа,\nклиент узнает о других наших проектах и о том, как можно улучшить свою жизнь,\nрешить юридические и медицинские проблемы.")
    private val array2 = mutableListOf(
        "Конечно, Михаил. Вот, возьмите суп и хлеб. Хотите узнать больше о том, как мы\nможем вам помочь?",
        "Еда только для тех, кто уже зарегистрировался у нас. Сначала заполни анкету",
        "Это 'Ночной автобус' от 'Ночлежки'. Мы ездим по городу и раздаём еду\nбездомным, оказываем первую помощь и консультируем по любым вопросам",
        "Это просто благотворительный автобус, который иногда ездит по городу.",
        "Нужно обязательно принести документы, иначе мы не сможем вас обслужить",
        "Ведите себя спокойно, уважайте других людей, пропускайте вперед женщин \nи инвалидов. Мы работаем по графику и время указано на брошюре ",
        "Просто отдохните и попейте горячего чая. Всё само пройдёт.",
        "Если вам станет плохо, сразу скажите об этом любому волонтёру.\nМы можем оказать первую помощь, дать таблетки и вызвать машину скорой помощи.",
        "Для начала вам нужно прийти в консультационный центр . Мы поможем на каждом этапе. Держите нашу листовку с подробной информацией",
        "Вам нужно самому решить, что делать. Мы не можем помочь в таких вопросах.",
        "Как хотите. Я здесь только чтобы раздавать еду.",
        "Не откладывайте. Чем раньше вы начнете, тем быстрее мы сможем вам помочь. \nЯ здесь, чтобы поддержать вас."
    )
    private var count = 0

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNightBusTrainingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        

        binding.text.animateCharacterByCharacter(array[0])
        binding.dialogNext.isClickable = true

        binding.dialogNext.setOnClickListener {
            count++
            if (count < array.size) {
                lifecycleScope.launch {
                    binding.dialogNext.isClickable = false
                    when(count) {
                        1 -> {
                            binding.stress.visibility = View.VISIBLE
                            binding.rules.visibility = View.VISIBLE
                        }
                        3 -> {
                            with(binding) {
                                michael.visibility = View.GONE
                                catStatus.setBackgroundResource(R.drawable.img_michael)
                                statusName.text = "Михаил"
                            }
                        }
                        4-> userChoise(array2[0], array2[1])
                        6 -> userChoise(array2[2], array2[3])
                        8 -> userChoise(array2[4], array2[5])
                        5, 7 -> {
                            status()
                            binding.radioGroup.visibility = View.GONE
                            binding.text.visibility = View.VISIBLE
                        }
                        9 -> {
                            with(binding)  {
                                bgTransparent.visibility = View.VISIBLE
                                catStatus.setBackgroundResource(R.drawable.cat_status)
                                statusName.text = "Статус"
                                psycholog.visibility = View.VISIBLE
                                michaelTransparent.visibility = View.VISIBLE
                                radioGroup.visibility = View.GONE
                                text.visibility = View.VISIBLE
                            }
                        }
                        10 -> {
                            binding.bgTransparent.visibility = View.GONE
                            binding.catStatus.setBackgroundResource(R.drawable.img_michael)
                            binding.statusName.text = "Михаил"
                            binding.psycholog.visibility = View.GONE
                            binding.michaelTransparent.visibility = View.GONE
                            status()
                            binding.radioGroup.visibility = View.GONE
                            binding.text.visibility = View.VISIBLE
                        }
                        11 -> userChoise(array2[6], array2[7])
                        12 -> {
                            status()
                            with(binding)  {
                                radioGroup.visibility = View.GONE
                                text.visibility = View.VISIBLE
                            }
                        }
                        13 -> {
                            userChoise(array2[8], array2[9])
                            binding.cards.visibility = View.VISIBLE
                        }
                        14 -> {
                            status()
                            with(binding)  {
                                radioGroup.visibility = View.GONE
                                text.visibility = View.VISIBLE
                                cards.visibility = View.GONE
                            }
                        }
                        15 -> userChoise(array2[10], array2[11])
                        16 -> {
                            with(binding) {
                                status()
                                radioGroup.visibility = View.GONE
                                text.visibility = View.VISIBLE
                                michael.visibility = View.VISIBLE
                                catStatus.setBackgroundResource(R.drawable.cat_status)
                                statusName.text = "Статус"
                            }
                        }
                        17 -> binding.michael.visibility = View.GONE
                    }
                    if (count == 4) animateChoise(array2[0], array2[1])
                    else if (count == 6) animateChoise(array2[2], array2[3])
                    else if (count == 8) animateChoise(array2[4], array2[5])
                    else if (count == 11) animateChoise(array2[6], array2[7])
                    else if (count == 13) animateChoise(array2[8], array2[9])
                    else if (count == 15) animateChoise(array2[10], array2[11])
                    else {
                        binding.text.animateCharacterByCharacter(array[count])
                        delay(25L * array[count].length.toLong())
                    }
                    binding.dialogNext.isClickable = true
                }
            } else findNavController().navigate(R.id.action_nightBusTrainingFragment2_to_locationMapFragment)
        }

    }

    private fun TextView.animateCharacterByCharacter(text: String, delay: Long = 25L) {
        if (text.isEmpty()) return

        val charAnimation = ValueAnimator.ofInt(0, text.length)

        charAnimation.apply {
            this.duration = delay * text.length.toLong()
            this.repeatCount = 0
            addUpdateListener {
                val charCount = it.animatedValue as Int
                val animatedText = text.substring(0, charCount)
                this@animateCharacterByCharacter.text = animatedText
            }
        }
        charAnimation.start()
    }

    private fun status() {
        with(binding) {
            statusName.visibility = View.VISIBLE
            userName.visibility = View.INVISIBLE
            rectangle.setBackgroundResource(R.drawable.rectangle_100)
        }
    }

    private fun userChoise(text1: String, text2: String) {
        with(binding)  {
            userName.visibility = View.VISIBLE
            statusName.visibility = View.INVISIBLE
            rectangle.setBackgroundResource(R.drawable.rectangle_user)
            text.visibility = View.GONE
            radioGroup.visibility = View.VISIBLE
            radioButton1.text = text1
            radioButton2.text = text2
        }
    }
    suspend fun animateChoise(text1: String, text2: String) {
        binding.radioButton2.visibility = View.INVISIBLE
        binding.radioButton1.animateCharacterByCharacter(text1)
        delay(25L * text1.length.toLong())
        binding.radioButton2.visibility = View.VISIBLE
        binding.radioButton2.animateCharacterByCharacter(text2)
        delay(25L * text2.length.toLong())
    }

}