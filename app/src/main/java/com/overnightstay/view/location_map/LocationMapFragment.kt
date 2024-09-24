package com.overnightstay.view.location_map

import android.animation.ValueAnimator
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.overnightstay.R
import com.overnightstay.databinding.FragmentLocationMapBinding
import com.overnightstay.domain.models.User
import com.overnightstay.utils.animateCharacterByCharacter2
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LocationMapFragment : Fragment() {

    private var _binding: FragmentLocationMapBinding? = null
    private val binding get() = _binding!!

    val currentAnimator: ValueAnimator by lazy {
        ValueAnimator.ofInt(0, 0)
    }

    private var array = mutableListOf(
        "У тебя появился Домик Опыта\nПри успешном прохождении локаций, у тебя будет накапливаться опыт. В дальнейшем,\nты можешь зайти в свой дом и расставить там предметы из рюкзака.",
        "Карта поможет тебе вернуться на главный экран и выбрать следующую локацию.\nЖми на меня, если нужна будет помощь.",
        "Вперед! К новым испытаниям и приключениям! \nНу, а я буду всегда с тобой! Муррр–р–р–р\nВыбери куда отправимся дальше"
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
        _binding = FragmentLocationMapBinding.inflate(inflater, container, false)
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

                when (count) {
                    1 -> binding.catAvatar.visibility = View.VISIBLE
                }
                binding.text.animateCharacterByCharacter2(
                    text = array[count],
                    animator = currentAnimator
                )

                lifecycleScope.launch {
                    delay(25L * array[count].length.toLong())
                }

            } else if (count == 3) {
                binding.rectangle.visibility = View.INVISIBLE
                binding.catStatus.visibility = View.INVISIBLE
                binding.statusName.visibility = View.INVISIBLE
                binding.text.visibility = View.INVISIBLE
                binding.dialogNext.visibility = View.INVISIBLE
            }
        }

        initBtnListeners()
    }

    private fun initBtnListeners() = with(binding) {
        nightBus.setOnClickListener {
            if (nightBus.alpha == 0f) {
                nightBus.alpha = 1f
                lifecycleScope.launch {
                    delay(1000L)
                    findNavController().navigate(R.id.action_locationMapFragment_to_nightBusFragment)
                }
            } else {
                nightBus.alpha = 0f
            }
        }
    }
}