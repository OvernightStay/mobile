package com.overnightstay.view.mini_games

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
import com.overnightstay.databinding.FragmentGameToFeedTheNeedyBinding
import com.overnightstay.utils.animateCharacterByCharacter2
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameToFeedTheNeedyFragment : Fragment() {

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
                    binding.text.animateCharacterByCharacter2(text = array[count], animator = currentAnimator)
                    lifecycleScope.launch {
                        delay(25L * array[count].length.toLong())
                    }
                }

                2 -> {
                    with(binding) {
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
    }
}