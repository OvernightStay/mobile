package com.overnightstay.view.night_bus

import android.animation.ValueAnimator
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.overnightstay.R
import com.overnightstay.databinding.FragmentNightBusTrainingBinding
import com.overnightstay.utils.animateCharacterByCharacter2
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NightBusTrainingFragment : Fragment() {

    private var _binding: FragmentNightBusTrainingBinding? = null
    private val binding get() = _binding!!

    val currentAnimator: ValueAnimator by lazy {
        ValueAnimator.ofInt(0, 0)
    }

    private var array = mutableListOf(
        "Давайте поближе познакомимся с работой Ночного автобуса. Сегодня к вам подошёл\nМихаил. Ваша задача — не только накормить его, но и рассказать о возможностях,\nкоторые мы предоставляем, и о правилах, которые нужно соблюдать.",
        "Хочу обратить твое внимание на интерфейс, в нем находится шкала стресса. \nЗдесь ошибки будут накапливаться в стресс.",
        "Если шкала будет заполнена, тебе необходимо посетить КНИГУ ПРАВИЛ.\nДомик – это твой опыт. Чуть позже я подробнее расскажу тебе о нём.\nА сейчас, давай поможем Михаилу !",
        "Здравствуйте. Можно мне тарелку супа? Я не ел с утра...",
        "Конечно, Михаил. Вот, возьмите суп и хлеб. Хотите узнать больше о том, как мы\nможем вам помочь?\n\nЕда только для тех, кто уже зарегистрировался у нас. Сначала заполни анкету",
        "Спасибо за еду. Я раньше не слышал о вас. Что это за автобус?",
        "Это 'Ночной автобус' от 'Ночлежки'. Мы ездим по городу и раздаём еду\nбездомным, оказываем первую помощь и консультируем по любым вопросам\n\nЭто просто благотворительный автобус, который иногда ездит по городу.",
        "А что нужно, чтобы снова сюда прийти? Какие у вас правила?",
        "Нужно обязательно принести документы, иначе мы не сможем вас обслужить\n\nВедите себя спокойно, уважайте других людей, пропускайте вперед женщин \nи инвалидов. Мы работаем по графику и время указано на брошюре ",
        "Не переживай и не расстраивайся, если будут ошибки! Наш психолог поможет тебе\nсправиться со стрессом",
        "Знаете, в последнее время я стал хуже себя чувствовать.\nПростудился, но идти в больницу боюсь и не хочу.\nНе знаю, что делать. Дайте какую–то таблетку.",
        "Просто отдохните и попейте горячего чая. Всё само пройдёт.\n\nЕсли вам станет плохо, сразу скажите об этом любому волонтёру. Мы можем\nоказать первую помощь, дать таблетки и вызвать машину скорой помощи.",
        "Я уже давно на улице и даже не знаю, как вернуться к обычной жизни. С чего начать?",
        "Для начала вам нужно прийти в консультационный центр . Мы поможем на каждом этапе. Держите нашу листовку с подробной информацией\n\nВам нужно самому решить, что делать. Мы не можем помочь в таких вопросах.",
        "Спасибо за помощь... Я подумаю над вашими словами.",
        "Как хотите. Я здесь только чтобы раздавать еду.\n\nНе откладывайте. Чем раньше вы начнете, тем быстрее мы сможем вам помочь. \nЯ здесь, чтобы поддержать вас.",
        "Отличная работа! Ты молодец!",
        "Мы называем Ночной автобус «Точкой входа» в организацию. Придя за тарелкой супа,\nклиент узнает о других наших проектах и о том, как можно улучшить свою жизнь,\nрешить юридические и медицинские проблемы.")
    private var count = 0

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNightBusTrainingBinding.inflate(inflater, container, false)
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

            if (count < array.size) {
                binding.text.animateCharacterByCharacter2(text = array[count], animator = currentAnimator)

                lifecycleScope.launch {
                    delay(25L * array[count].length.toLong())
                }

                when(count) {
                    2 -> {
                        binding.stress.visibility = View.VISIBLE
                        binding.rules.visibility = View.VISIBLE
                    }
                    3 -> {
                        with(binding) {
                            catStatus.visibility = View.GONE
                            statusName.text = "Михаил"
                        }
                    }
                    4, 6, 8, 11, 15 -> animateChoise()
                    5, 7 -> {
                        status()
                        binding.radioGroup.visibility = View.GONE
                        binding.text.visibility = View.VISIBLE
                    }
                    9 -> {
                        with(binding)  {
                            bgTransparent.visibility = View.VISIBLE
                            catStatus.visibility = View.VISIBLE
                            statusName.text = "Статус"
                            psycholog.visibility = View.VISIBLE
                            radioGroup.visibility = View.GONE
                            text.visibility = View.VISIBLE
                        }
                    }
                    10 -> {
                        status()
                        with(binding) {
                            bgTransparent.visibility = View.GONE
                            catStatus.visibility = View.GONE
                            statusName.text = "Михаил"
                            psycholog.visibility = View.GONE
                            radioGroup.visibility = View.GONE
                            text.visibility = View.VISIBLE
                        }
                    }
                    12 -> {
                        status()
                        with(binding)  {
                            radioGroup.visibility = View.GONE
                            text.visibility = View.VISIBLE
                        }
                    }
                    13 -> {
                        binding.cards.visibility = View.VISIBLE
                        animateChoise()
                    }
                    14 -> {
                        status()
                        with(binding)  {
                            radioGroup.visibility = View.GONE
                            text.visibility = View.VISIBLE
                            cards.visibility = View.GONE
                        }
                    }
                    16 -> {
                        with(binding) {
                            status()
                            radioGroup.visibility = View.GONE
                            text.visibility = View.VISIBLE
                            michael.visibility = View.VISIBLE
                            catStatus.visibility = View.VISIBLE
                            statusName.text = "Статус"
                        }
                    }
                    17 -> binding.michael.visibility = View.GONE
                }
            } else findNavController().navigate(R.id.action_nightBusTrainingFragment2_to_gameToFeedTheNeedyFragment)
        }

    }

    private fun status() {
        with(binding) {
            statusName.visibility = View.VISIBLE
            userName.visibility = View.INVISIBLE
            rectangle.setBackgroundResource(R.drawable.rectangle_100)
        }
    }

    private fun animateChoise() {
        with(binding) {
            userName.visibility = View.VISIBLE
            statusName.visibility = View.INVISIBLE
            rectangle.setBackgroundResource(R.drawable.rectangle_user)
            radioGroup.visibility = View.VISIBLE
        }
    }
}