package com.overnightstay.view.night_bus

import android.animation.ValueAnimator
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
        "Конечно, Михаил. Вот, возьмите суп и хлеб. Хотите узнать больше о том, как мы\nможем вам помочь?\nЕда только для тех, кто уже зарегистрировался у нас. Сначала заполни анкету",
        "Спасибо за еду. Я раньше не слышал о вас. Что это за автобус?",
        "Это 'Ночной автобус' от 'Ночлежки'. Мы ездим по городу и раздаём еду\nбездомным, оказываем первую помощь и консультируем по любым вопросам",
        "А что нужно, чтобы снова сюда прийти? Какие у вас правила?",
        "Нужно обязательно принести документы, иначе мы не сможем вас обслужить",
        "Не переживай и не расстраивайся, если будут ошибки! Наш психолог поможет тебе\nсправится со стрессом",
        "Знаете, в последнее время я стал хуже себя чувствовать.\nПростудился, но идти в больницу боюсь и не хочу.\nНе знаю, что делать. Дайте какую–то таблетку.",
        "Просто отдохните и попейте горячего чая. Всё само пройдёт.",
        "Я уже давно на улице и даже не знаю, как вернуться к обычной жизни. С чего начать?",
        "Для начала вам нужно прийти в консультационный центр . Мы поможем на\nкаждом этапе. Держите нашу листовку с подробной информацией ",
        "Спасибо за помощь... Я подумаю над вашими словами.",
        "Как хотите. Я здесь только чтобы раздавать еду.",
        "Отличная работа! Ты молодец!",
        "Мы называем Ночной автобус «Точкой входа» в организацию. Придя за тарелкой супа,\nклиент узнает о других наших проектах и о том, как можно улучшить свою жизнь,\nрешить юридические и медицинские проблемы."
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

        

//        binding.text.animateCharacterByCharacter(array[0])
//        binding.dialogNext.isClickable = true
//
//        binding.dialogNext.setOnClickListener {
//            count++
//            if (count < array.size) {
//                lifecycleScope.launch {
//                    binding.dialogNext.isClickable = false
//                    when(count) {
//                        1 -> {
//                            binding.stress.visibility = View.VISIBLE
//                            binding.rules.visibility = View.VISIBLE
//                        }
//                        3 -> {
//                            binding.michael.visibility = View.INVISIBLE
//                            binding.catStatus.setBackgroundResource(R.drawable.img_michael)
//                            binding.statusName.text = "Михаил"
//                        }
//                        4, 6, 8 -> user()
//                        5, 7, 10 -> status()
//                    }
//                    binding.text.animateCharacterByCharacter(array[count])
//                    delay(25L * array[count].length.toLong())
//                    binding.dialogNext.isClickable = true
//                }
//            } else findNavController().navigate(R.id.action_nightBusTrainingFragment2_to_locationMapFragment)
//        }

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

    private fun user() {
        binding.userName.visibility = View.VISIBLE
        binding.statusName.visibility = View.INVISIBLE
        binding.rectangle.setBackgroundResource(R.drawable.rectangle_user)
    }

    private fun status() {
        binding.statusName.visibility = View.VISIBLE
        binding.userName.visibility = View.INVISIBLE
        binding.rectangle.setBackgroundResource(R.drawable.rectangle_100)
    }

}