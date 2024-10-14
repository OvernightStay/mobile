package com.overnightstay.view.house_shower

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
import com.overnightstay.databinding.FragmentDialogOnTheStreetBinding
import com.overnightstay.utils.animateCharacterByCharacter2
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DialogOnTheStreetFragment : Fragment() {

    private var _binding: FragmentDialogOnTheStreetBinding? = null
    private val binding get() = _binding!!

    val currentAnimator: ValueAnimator by lazy {
        ValueAnimator.ofInt(0, 0)
    }

    private var array = mutableListOf(
        "Приветствую тебя в локации Неравнодуш! Хочу рассказать тебе о проекте. Это душевой пункт, где любой человек может бесплатно вымыться и привести в порядок свою одежду.  ",
        "Расскажи мне, пожалуйста, о нем!",
        "Наш проект Неравнодуш работает в двух городах Москве и Санкт–Петербурге. Наши клиенты могут бесплатно проходить в Неравнодуше санитарную обработку, постирать и высушить одежду. Пойдем, я тебе покажу как здесь все работает."
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
        _binding = FragmentDialogOnTheStreetBinding.inflate(inflater, container, false)
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

                when(count) {
                    1 -> {
                        with(binding) {
                            rectangle.setBackgroundResource(R.drawable.dialog_house_shower_user)
                            statusName.visibility = View.GONE
                            userName.visibility = View.VISIBLE
                        }
                    }
                    2 -> {
                        with(binding) {
                            rectangle.setBackgroundResource(R.drawable.dialog_house_shower_status)
                            statusName.visibility = View.VISIBLE
                            userName.visibility = View.GONE
                        }
                    }
                }

                binding.text.animateCharacterByCharacter2(text = array[count], animator = currentAnimator)
                lifecycleScope.launch {
                    delay(25L * array[count].length.toLong())
                }

            } else findNavController().navigate(R.id.action_dialogOnTheStreetFragment_to_dialogInTheBuildingFragment)
        }

        binding.rules.setOnClickListener {
            findNavController().navigate(R.id.action_dialogOnTheStreetFragment_to_contentsOfBookFragment)
        }
    }
}