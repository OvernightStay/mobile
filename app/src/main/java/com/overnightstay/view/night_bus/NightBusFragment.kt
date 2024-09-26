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
import com.overnightstay.databinding.FragmentNightBusBinding
import com.overnightstay.utils.animateCharacterByCharacter2
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NightBusFragment : Fragment() {

    private var _binding: FragmentNightBusBinding? = null
    private val binding get() = _binding!!

    val currentAnimator: ValueAnimator by lazy {
        ValueAnimator.ofInt(0, 0)
    }

    private var array = mutableListOf("Приветствую тебя на первой локации!\nХочу рассказать тебе о проекте Ночной автобус",
        "Это мобильный пункт социальной помощи от благотворительной\nорганизации Ночлежка, где любой нуждающийся может  бесплатно получить горячий\nужин, консультацию водителя-соцработника и помощь медиков-волонтёров",
        "Расскажи мне, пожалуйста, о нем!",
        "Давай подойдем поближе.\nВ этом году проекту исполнилось 20 лет, всё это время он бесперебойно кормит\nбездомных людей в Петербурге, а с 2021 года и в Москве.",
        "Расскажи, чем  занимаются волонтеры?",
        "Водитель – наш сотрудник. Он, как дирижер в оркестре, всем здесь\nуправляет. Ему всегда помогают 4 волонтера.",
        "Мужчина–волонтер раздает ложки, стоит перед машиной, где происходит раздача, и\nконтролирует очередь, просит не торопиться и пропускать вперед женщин, людей с\nинвалидностью. Следит за порядком и меняет пакеты для мусора.",
        "Как все продумано!",
        "Еще один человек в кузове разливает суп/второе (чай/кофе),\nвторой – раздает хлеб или сладости.\nЧетвертый волонтер записывает всех клиентов в CRM-систему.",
        "Если клиенту стало плохо, нужно вызвать скорую помощь. А также\nодин раз в неделю выезжают два волонтера-медика. ",
        "Это очень хорошо, что есть волонтеры–медики! Можно сразу оказать помощь\nнуждающимся.",
        "Так же в машине всегда есть одежда по сезону для волонтеров. Водитель следит,\nчтобы все волонтеры были в форме Ночлежки для того, чтобы клиенты знали, к кому\nможно обратиться с вопросом, а водитель видел всех в толпе для безопасности.",
        "Также водитель рассказывает клиентам про проекты Ночлежки и какую помощь\nони могут получить в наших проектах.\nУ водителя есть листовки с необходимой информацией.",
        "Это очень хорошо, что бездомные могут узнать и о других проектах Ночлежки!",
        "Мы называем Ночной автобус «Точкой входа» в организацию. Придя за тарелкой\nсупа, клиент узнает о других наших проектах и о том, как можно улучшить свою жизнь,\nрешить юридические и медицинские проблемы."
        )
        private var count = 0

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNightBusBinding.inflate(inflater, container, false)
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

                when (count) {
                    2, 4, 7, 10, 13 -> user()
                    3, 8 -> status()
                    5 -> {
                        status()
                        binding.main.setBackgroundResource(R.drawable.bg_night_bus_02)
                    }

                    6 -> binding.main.setBackgroundResource(R.drawable.bg_night_bus_03)
                    9 -> binding.main.setBackgroundResource(R.drawable.bg_night_bus_04)
                    11 -> {
                        status()
                        binding.main.setBackgroundResource(R.drawable.bg_night_bus_03)
                    }

                    12 -> {
                        status()
                        binding.main.setBackgroundResource(R.drawable.bg_night_bus_05)
                        binding.cards.visibility = View.VISIBLE
                    }

                    14 -> {
                        status()
                        binding.main.setBackgroundResource(R.drawable.bg_night_bus_03)
                        binding.cards.visibility = View.INVISIBLE
                    }
                }
            } else findNavController().navigate(R.id.action_nightBusFragment_to_nightBusTrainingFragment2)
        }
    }

    private fun status() {
        binding.statusName.visibility = View.VISIBLE
        binding.userName.visibility = View.INVISIBLE
        binding.rectangle.setBackgroundResource(R.drawable.rectangle_100)
    }
    private fun user() {
        binding.userName.visibility = View.VISIBLE
        binding.statusName.visibility = View.INVISIBLE
        binding.rectangle.setBackgroundResource(R.drawable.rectangle_user)
    }

}