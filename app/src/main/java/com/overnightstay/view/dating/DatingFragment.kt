package com.overnightstay.view.dating

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
import com.overnightstay.databinding.FragmentDatingBinding
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DatingFragment : Fragment() {

    private var _binding: FragmentDatingBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: DatingViewModel

    private var array = mutableListOf(
        "Привет!\nЯ - кот Статус, расскажу, что тебя ждет в игре.\nСейчас я работаю волонтером в Ночлежке.",
        "Но так было не всегда...\nПару лет назад я потерял все",
        "В ночлежке мне помогли.\nТеперь у меня есть дом",
        "Ночлежка - это благотворительная общественная организация. Наша цель -\nреабилитация бездомных людей и профилактика бездомности, основанная на\nпринципах гуманности, добровольности, уважения личности и ее прав.",
        "Ночлежка с 1990 года кормит, обогревает, помогает с документами, работой,\nоформлением инвалидностей, пособий, устройством в интернаты, в поиске\nродственников и отъезде домой, оспаривает незаконные сделки с недвижимостью.",
        "Давай сначала я покажу тебе что и как здесь все работает",
        "Смотри...\nШестеренка - это твой профиль. Здесь ты можешь поменять имя, пароль",
        "И в любой момент ты можешь выключить звук. Эта функция находится внутри\nшестеренки",
        "А теперь познакомлю тебя с нашими проектами.\nИди за мной"
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
        _binding = FragmentDatingBinding.inflate(inflater, container, false)
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
                    binding.text.animateCharacterByCharacter(array[count])
                    delay(25L * array[count].length.toLong())
                    binding.dialogNext.isClickable = true
                }
            } else findNavController().navigate(R.id.action_datingFragment_to_nightBusFragment)
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



}