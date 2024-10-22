package com.overnightstay.view.house_of_distribution.finishminigame

import android.animation.ValueAnimator
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
import com.overnightstay.databinding.FragmentFinishGameMemoDistrBinding
import com.overnightstay.utils.gone
import com.overnightstay.utils.hide
import com.overnightstay.utils.show
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class FinishGameMemoDistrFragment : Fragment() {

    private val currentAnimator: ValueAnimator by lazy {
        ValueAnimator.ofInt(0, 0)
    }

    private var _binding: FragmentFinishGameMemoDistrBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FinishGameMemoDistrViewModel

    @Inject
    lateinit var vmFactory: FinishGameMemoDistrViewModel.Factory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFinishGameMemoDistrBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(this, vmFactory)[FinishGameMemoDistrViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.gameStageFlow.collect {
                renderData(it)
            }
        }

        binding.dialogNext.setOnClickListener {
            with(binding) {
                bgHouseOfDistr7.gone()
                bgHouseOfDistr8.gone()
                memoShampoo.gone()
                memoBoots.gone()
                memoJacket.gone()
                memoBackpack.gone()
                memoDress.gone()
                memoTooth.gone()
                memoSweater.gone()

                home.show()
                stress.show()
                gearWheel.show()
                backpack.show()
                rules.show()
                catAvatar.show()
                map.show()

                kuCatStatus.gone()
                kuBubble.gone()
                kuText.text = ""
                kuText.gone()

                catStatus.gone()
                rectangle.gone()
                statusName.gone()
                dialogNext.gone()
                text.text = ""
                text.gone()

                modalAchieve.show()
            }
        }

        binding.btnTake.setOnClickListener {
            findNavController().navigate(R.id.action_finishGameMemoDistrFragment_to_locationMapFragment)
        }

        with(binding) {
            listOf(
                memoShampoo,
                memoBoots,
                memoJacket,
                memoBackpack,
                memoDress,
                memoRazor,
                memoTooth,
                memoSweater
            ).forEach { it.setOnClickListener { viewModel.nextStage() } }

        }
    }

    private suspend fun renderData(gameStage: FinishGameMemoDistrEnum) {
        /*        with(binding) {
                    bgHouseOfDistr7.show()
                    bgHouseOfDistr8.gone()
                    memoShampoo.show()
                    memoBoots.show()
                    memoJacket.show()
                    memoBackpack.show()
                    memoDress.show()
                    memoTooth.show()
                    memoSweater.show()

                    home.show()
                    stress.show()
                    gearWheel.show()
                    backpack.show()
                    rules.show()
                    catAvatar.show()
                    map.show()

                    kuCatStatus.gone()
                    kuBubble.gone()
                    kuText.gone()
        //            gameStage.txt.let { kuText.setText(it) }

                    catStatus.gone()
                    rectangle.gone()
                    statusName.gone()
                    dialogNext.gone()
                    text.gone()
        //            gameStage.txt.let { text.setText(it) }

                    modalAchieve.gone()*/
        when (gameStage) {
            FinishGameMemoDistrEnum.DISTRIBUTION_FINISH_GAME_1 -> {
                with(binding) {
                    bgHouseOfDistr7.show()
                    bgHouseOfDistr8.gone()
                    memoShampoo.show()
                    memoBoots.show()
                    memoJacket.show()
                    memoBackpack.show()
                    memoDress.show()
                    memoTooth.show()
                    memoSweater.show()

                    home.show()
                    stress.show()
                    gearWheel.show()
                    backpack.show()
                    rules.show()
                    catAvatar.show()
                    map.show()

                    lifecycleScope.launch {
                        with(binding) {
                            kuCatStatus.show()
                            kuBubble.show()
                            kuText.setText(gameStage.txt)
                            kuText.show()
                        }

                        delay(3000L)

                        with(binding) {
                            kuCatStatus.hide()
                            kuBubble.hide()
                            kuText.text = ""
                            kuText.hide()
                        }
                    }

                    catStatus.gone()
                    rectangle.gone()
                    statusName.gone()
                    dialogNext.gone()
                    text.gone()
                    //gameStage.txt.let { text.setText(it) }

                    modalAchieve.gone()
                }
            }

            FinishGameMemoDistrEnum.DISTRIBUTION_FINISH_GAME_2 -> {
                with(binding) {
                    bgHouseOfDistr7.gone()
                    bgHouseOfDistr8.show()
                    memoShampoo.hide()
                    memoBoots.hide()
                    memoJacket.hide()
                    memoBackpack.hide()
                    memoDress.hide()
                    memoTooth.hide()
                    memoSweater.hide()

                    home.show()
                    stress.show()
                    gearWheel.show()
                    backpack.show()
                    rules.show()
                    catAvatar.show()
                    map.show()

                    lifecycleScope.launch {
                        with(binding) {
                            kuCatStatus.show()
                            kuBubble.show()
                            kuText.setText(gameStage.txt)
                            kuText.show()
                        }

                        delay(3000L)

                        with(binding) {
                            kuCatStatus.hide()
                            kuBubble.hide()
                            kuText.text = ""
                            kuText.hide()
                        }
                    }

                    catStatus.gone()
                    rectangle.gone()
                    statusName.gone()
                    dialogNext.gone()
                    text.gone()
                    //gameStage.txt.let { text.setText(it) }

                    modalAchieve.gone()
                    lifecycleScope.launch {
                        delay(5000L)
                    }.join()

                    viewModel.nextStage()
                }
            }

            FinishGameMemoDistrEnum.DISTRIBUTION_FINISH_GAME_3 -> {
                with(binding) {
                    bgHouseOfDistr7.gone()
                    bgHouseOfDistr8.show()
                    memoShampoo.hide()
                    memoBoots.hide()
                    memoJacket.hide()
                    memoBackpack.hide()
                    memoDress.hide()
                    memoTooth.hide()
                    memoSweater.hide()

                    home.show()
                    stress.show()
                    gearWheel.show()
                    backpack.show()
                    rules.show()
                    catAvatar.show()
                    map.show()

                    kuCatStatus.hide()
                    kuBubble.hide()
                    kuText.text = ""
                    kuText.hide()

                    catStatus.show()
                    rectangle.show()
                    statusName.show()
                    dialogNext.show()
                    text.setText(gameStage.txt)
                    text.show()

                    modalAchieve.gone()
                }
            }
        }
    }
}