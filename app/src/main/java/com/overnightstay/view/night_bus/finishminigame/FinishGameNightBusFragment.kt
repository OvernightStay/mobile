package com.overnightstay.view.night_bus.finishminigame

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.overnightstay.R
import com.overnightstay.databinding.FragmentFinishGameNightBusBinding
import com.overnightstay.utils.animateCharacterByCharacter2
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class FinishGameNightBusFragment : Fragment() {
    private var countPositiv = 0
    private var countNegativ = 0
    private var txt = ""
    private val currentAnimator: ValueAnimator by lazy {
        ValueAnimator.ofInt(0, 0)
    }

    private var _binding: FragmentFinishGameNightBusBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FinishGameNightBusViewModel

    @Inject
    lateinit var vmFactory: FinishGameNightBusViewModel.Factory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFinishGameNightBusBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        countPositiv = arguments?.getInt("countPositiv") ?: -1
        countNegativ = arguments?.getInt("countNegativ") ?: -1

        println("FinishGameNightBusFragment: \"countPositiv\" to $countPositiv, \"countNegativ\" to $countNegativ")


        viewModel =
            ViewModelProvider(this, vmFactory)[FinishGameNightBusViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.gameStateFlow.collect {
                renderData(it)
            }
        }

        initBtnListeners()
    }

    private fun initBtnListeners() = with(binding) {
        btnAgain.setOnClickListener {
            findNavController().navigate(R.id.action_finishGameNightBusFragment_to_gameToFeedTheNeedyFragment)
        }

        btnFinish.setOnClickListener {
            viewModel.nextStage()
        }

        btnTake.setOnClickListener {
            viewModel.takeAchieve()
        }
        dialogNext.setOnClickListener {
            if (currentAnimator.isRunning) {
                currentAnimator.end()
                return@setOnClickListener
            }

            viewModel.nextStage()
        }
        map.setOnClickListener {
            findNavController().navigate(R.id.action_finishGameNightBusFragment_to_locationMapFragment)
        }
        rules.setOnClickListener {
            findNavController().navigate(R.id.action_finishGameNightBusFragment_to_contentsOfBookFragment)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun renderData(gameState: FihishMiniGameState) {
        when (gameState) {

            is FihishMiniGameState.Stage1<*> -> {
                with(binding) {
                    if (countPositiv >= 0 && countNegativ >= 0 && (countPositiv + countNegativ) == 10) {
                        tvCountPositiv.text = "$countPositiv\\${countPositiv + countNegativ}"
                        tvCountNegativ.text = "$countNegativ\\${countPositiv + countNegativ}"
                    } else {
                        tvCountPositiv.text = ""
                        tvCountNegativ.text = ""

                    }
                    home.visibility = View.VISIBLE
                    stress.visibility = View.VISIBLE
                    gearWheel.visibility = View.VISIBLE
                    backpack.visibility = View.INVISIBLE
                    rules.visibility = View.VISIBLE
                    catAvatar.visibility = View.INVISIBLE
                    map.visibility = View.INVISIBLE
                    catStatus.visibility = View.INVISIBLE
                    rectangle.visibility = View.INVISIBLE
                    statusName.visibility = View.INVISIBLE
                    dialogNext.visibility = View.INVISIBLE
                    text.visibility = View.INVISIBLE
                    loadingLayout.root.visibility = View.VISIBLE
                    modalCongr.visibility = View.VISIBLE
                }
            }

            is FihishMiniGameState.Stage2<*> -> {
                with(binding) {
                    home.visibility = View.VISIBLE
                    stress.visibility = View.VISIBLE
                    gearWheel.visibility = View.VISIBLE
                    backpack.visibility = View.INVISIBLE
                    rules.visibility = View.VISIBLE
                    catAvatar.visibility = View.INVISIBLE
                    map.visibility = View.INVISIBLE
                    catStatus.visibility = View.VISIBLE
                    rectangle.visibility = View.VISIBLE
                    statusName.visibility = View.VISIBLE
                    dialogNext.visibility = View.VISIBLE
                    text.visibility = View.VISIBLE

                    txt = "Молодец! У тебя все получилось!\nПервая локация позади.\nТебе в подарок кружки дружбы!"
                    text.animateCharacterByCharacter2(
                        text = txt,
                        animator = currentAnimator)

                    lifecycleScope.launch {
                        delay(25L * txt.length.toLong())
                    }

                    loadingLayout.root.visibility = View.GONE
                    modalCongr.visibility = View.GONE
                }
            }

            is FihishMiniGameState.Stage3<*> -> {
                with(binding) {
                    home.visibility = View.VISIBLE
                    stress.visibility = View.VISIBLE
                    gearWheel.visibility = View.VISIBLE
                    backpack.visibility = View.INVISIBLE
                    rules.visibility = View.VISIBLE
                    catAvatar.visibility = View.INVISIBLE
                    map.visibility = View.INVISIBLE
                    catStatus.visibility = View.INVISIBLE
                    rectangle.visibility = View.INVISIBLE
                    statusName.visibility = View.INVISIBLE
                    dialogNext.visibility = View.INVISIBLE
                    text.text = ""
                    text.visibility = View.INVISIBLE
                    loadingLayout.root.visibility = View.VISIBLE
                    modalAchieve.visibility = View.VISIBLE
                }
            }

            is FihishMiniGameState.Stage4<*> -> {
                with(binding) {
                    home.visibility = View.VISIBLE
                    stress.visibility = View.VISIBLE
                    gearWheel.visibility = View.VISIBLE
                    backpack.visibility = View.VISIBLE
                    rules.visibility = View.VISIBLE
                    catAvatar.visibility = View.VISIBLE
                    map.visibility = View.VISIBLE
                    catStatus.visibility = View.VISIBLE
                    rectangle.visibility = View.VISIBLE
                    statusName.visibility = View.VISIBLE
                    dialogNext.visibility = View.INVISIBLE
                    text.visibility = View.VISIBLE
                    txt = "Они будут храниться в рюкзаке.\nВ нем будут хранится все ваши найденные в локациях предметы.\nА теперь давай познакомимся с остальными проектами, нажми на карту!"
                    text.animateCharacterByCharacter2(
                        text = txt,
                        animator = currentAnimator)

                    lifecycleScope.launch {
                        delay(25L * txt.length.toLong())
                    }
                    loadingLayout.root.visibility = View.GONE
                    modalAchieve.visibility = View.GONE
                }
            }

            is FihishMiniGameState.StageFinish<*> -> {

            }

            is FihishMiniGameState.Error -> {

            }

            FihishMiniGameState.Loading -> {

            }
        }
    }
}