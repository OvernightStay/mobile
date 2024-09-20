package com.overnightstay.view.location_map

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
import com.overnightstay.databinding.FragmentLocationMapBinding
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LocationMapFragment : Fragment() {

    private var _binding: FragmentLocationMapBinding? = null
    private val binding get() = _binding!!
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

        //            for (i in array) {
//                count++
//                binding.text.animateCharacterByCharacter(i)
//                delay(30L * i.length.toLong() + 1000L)
//                when(count) {
//                    1 -> binding.catAvatar.visibility = View.VISIBLE
//                }
//            }
        binding.text.animateCharacterByCharacter(array[0])
        binding.dialogNext.isClickable = true

        binding.dialogNext.setOnClickListener {
            lifecycleScope.launch {
                count++
                if (count < array.size) {
                    lifecycleScope.launch {
                        binding.dialogNext.isClickable = false
                        when(count) {
                            1 -> binding.catAvatar.visibility = View.VISIBLE
                        }
                        binding.text.animateCharacterByCharacter(array[count])
                        delay(25L * array[count].length.toLong())
                        binding.dialogNext.isClickable = true
                    }
                } else if (count == 3) {
                    binding.rectangle.visibility = View.INVISIBLE
                    binding.catStatus.visibility = View.INVISIBLE
                    binding.statusName.visibility = View.INVISIBLE
                    binding.text.visibility = View.INVISIBLE
                    binding.dialogNext.visibility = View.INVISIBLE
                }
            }
        }

    }

    private fun TextView.animateCharacterByCharacter(text: String, delay: Long = 30L) {
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