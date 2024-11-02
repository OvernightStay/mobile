package com.overnightstay.view.house_of_distribution

import android.animation.ValueAnimator
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.overnightstay.R
import com.overnightstay.databinding.FragmentHouseOfDistributionBinding
import com.overnightstay.utils.animateCharacterByCharacter2
import com.overnightstay.utils.gone
import com.overnightstay.utils.hide
import com.overnightstay.utils.show
import com.overnightstay.domain.models.Stress
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class HouseOfDistributionFragment : Fragment() {
    var stressElement = Stress.GREEN

    private val currentAnimator: ValueAnimator by lazy {
        ValueAnimator.ofInt(0, 0)
    }

    private var answer = arrayOf(
        arrayOf(3, 3, 1, 0),
        arrayOf(3, 5, 1, 0),
        arrayOf(3, 7, 1, 0),
        arrayOf(3, 9, 1, 0),
        arrayOf(3, 11, 1, 0),
        arrayOf(3, 13, 1, 0),
    )

    private var _binding: FragmentHouseOfDistributionBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HouseOfDistributionViewModel

    @Inject
    lateinit var vmFactory: HouseOfDistributionViewModel.Factory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHouseOfDistributionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(this, vmFactory)[HouseOfDistributionViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.gameStageFlow.collect {
                renderData(it)
            }
        }

        initBtnListeners()
    }

    private fun initBtnListeners() = with(binding) {

        dialogNext.setOnClickListener {
            if (currentAnimator.isRunning) {
                currentAnimator.end()
                return@setOnClickListener
            }

            if (!binding.radioGroup.isGone) {
                if (!binding.radioButton1.isChecked && !binding.radioButton2.isChecked) {
                    Snackbar.make(
                        binding.root,
                        "Выберите одну из опций.",
                        Snackbar.LENGTH_LONG
                    ).show()
                    return@setOnClickListener
                }

                when(viewModel.gameStageFlow.value) {
                    HouseOfDistributionEnum.DISTRIBUTION_STAGE3_3,
                    HouseOfDistributionEnum.DISTRIBUTION_STAGE3_5,
                    HouseOfDistributionEnum.DISTRIBUTION_STAGE3_7,
                    HouseOfDistributionEnum.DISTRIBUTION_STAGE3_9,
                    HouseOfDistributionEnum.DISTRIBUTION_STAGE3_11,
                    HouseOfDistributionEnum.DISTRIBUTION_STAGE3_13 -> {

                        /*** RdFlg Поменять большие выражения на короткие имена - TypeAlias*/
                        val index = answer.indexOfFirst {
                            it[0] == viewModel.gameStageFlow.value.stage1 &&
                                    it[1] == viewModel.gameStageFlow.value.stage2
                        }
                        if (index != -1) {
                            if (binding.radioButton1.isChecked) {
                                answer[index][3] = 1
                            } else {
                                answer[index][3] = 2
                            }
                            if (answer[index][2] != answer[index][3]) {

                                stressElement.getNextStress()?.let {
                                    binding.stress.setImageResource(it.idImg)
                                    stressElement = it
                                }
                            }
                        }
                    }
                    else -> {}
                }
            }

            viewModel.nextStage()
        }

        map.setOnClickListener {
            findNavController().navigate(R.id.action_houseOfDistributionFragment_to_locationMapFragment)
        }

        rules.setOnClickListener {
            findNavController().navigate(R.id.action_houseOfDistributionFragment_to_contentsOfBookFragment)
        }
        backpack.setOnClickListener {
            findNavController().navigate(R.id.action_houseOfDistributionFragment_to_backpackFragment)
        }
        home.setOnClickListener {
            findNavController().navigate(R.id.action_houseOfDistributionFragment_to_houseFragment)
        }
    }

    private fun renderData(gameStage: HouseOfDistributionEnum) {
        with(binding) {
/*            bgHouseOfDistr1.show()
            bgHouseOfDistrMinigame.gone()
            bgHouseOfDistrMemo.gone()
            bgHouseOfDistr2.gone()
            bgHouseOfDistr20.gone()
            bgHouseOfDistr3.gone()
            bgHouseOfDistr4.gone()
            bgHouseOfDistr5.gone()
            home.show()
            stress.show()
            gearWheel.show()
            backpack.show()
            rules.show()
            catAvatar.show()
            map.show()
            catStatus.show()
            womanDistr.show()
            womanCenter.gone()
            womanCenterOnLeft.gone()
            womanLeft.gone()
            rectangle.show()
            userName
            statusName
            dialogNext.show()
            gameStage.txt.first?.let { text.setText(it) } ?: ""*/

            home.show()
            stress.show()
            gearWheel.show()
            backpack.show()
            rules.show()
            catAvatar.show()
            map.show()
            catStatus.show()
            womanDistr.show()
            rectangle.show()
            dialogNext.show()
            radioGroup.gone()
        }

        when (gameStage) {
            HouseOfDistributionEnum.DISTRIBUTION_STAGE1_1,
            HouseOfDistributionEnum.DISTRIBUTION_STAGE1_2,
            HouseOfDistributionEnum.DISTRIBUTION_STAGE1_3,
            HouseOfDistributionEnum.DISTRIBUTION_STAGE1_4,
            HouseOfDistributionEnum.DISTRIBUTION_STAGE1_5 -> {

                with(binding) {
                    bgHouseOfDistr1.show()
                    bgHouseOfDistrMinigame.gone()
                    bgHouseOfDistrMemo.gone()
                    bgHouseOfDistr2.gone()
                    bgHouseOfDistr20.gone()
                    bgHouseOfDistr3.gone()
                    bgHouseOfDistr4.gone()
                    bgHouseOfDistr5.gone()
                    womanCenter.gone()
                    womanCenterOnLeft.gone()
                    womanLeft.gone()
//                    gameStage.txt.first?.let { text.setText(it) } ?: ""
                    setAnimationText(text, gameStage.txt.first?.let { resources.getString(it) })
                }

                when (gameStage) {
                    HouseOfDistributionEnum.DISTRIBUTION_STAGE1_1,
                    HouseOfDistributionEnum.DISTRIBUTION_STAGE1_2,
                    HouseOfDistributionEnum.DISTRIBUTION_STAGE1_4,
                    HouseOfDistributionEnum.DISTRIBUTION_STAGE1_5 -> {
                        with(binding) {
                            binding.userName.hide()
                            statusName.show()
                            rectangle.setBackgroundResource(R.drawable.dialog_left)
                        }
                    }

                    HouseOfDistributionEnum.DISTRIBUTION_STAGE1_3 -> {
                        with(binding) {
                            binding.userName.show()
                            rectangle.setBackgroundResource(R.drawable.dialog_right)
                            statusName.hide()
                        }
                    }

                    else -> {
                        //Сюда никогда не зайдет ветка
                    }
                }
            }

            HouseOfDistributionEnum.DISTRIBUTION_STAGE2_1,
            HouseOfDistributionEnum.DISTRIBUTION_STAGE2_2,
            HouseOfDistributionEnum.DISTRIBUTION_STAGE2_3,
            HouseOfDistributionEnum.DISTRIBUTION_STAGE2_4,
            HouseOfDistributionEnum.DISTRIBUTION_STAGE2_9,
            HouseOfDistributionEnum.DISTRIBUTION_STAGE2_10,
            HouseOfDistributionEnum.DISTRIBUTION_STAGE2_11,
            HouseOfDistributionEnum.DISTRIBUTION_STAGE2_16,
            HouseOfDistributionEnum.DISTRIBUTION_STAGE2_17,
            HouseOfDistributionEnum.DISTRIBUTION_STAGE2_18,
            HouseOfDistributionEnum.DISTRIBUTION_STAGE2_19 -> {

                with(binding) {
                    bgHouseOfDistr1.gone()
                    bgHouseOfDistrMinigame.gone()
                    bgHouseOfDistrMemo.gone()
                    bgHouseOfDistr2.show()
                    bgHouseOfDistr20.gone()
                    bgHouseOfDistr3.gone()
                    bgHouseOfDistr4.gone()
                    bgHouseOfDistr5.gone()
                    womanCenter.gone()
                    womanCenterOnLeft.gone()
                    womanLeft.gone()
//                    gameStage.txt.first?.let { text.setText(it) } ?: ""
                    setAnimationText(text, gameStage.txt.first?.let { resources.getString(it) })


                    when (gameStage) {
                        HouseOfDistributionEnum.DISTRIBUTION_STAGE2_1,
                        HouseOfDistributionEnum.DISTRIBUTION_STAGE2_4,
                        HouseOfDistributionEnum.DISTRIBUTION_STAGE2_9,
                        HouseOfDistributionEnum.DISTRIBUTION_STAGE2_11,
                        HouseOfDistributionEnum.DISTRIBUTION_STAGE2_18 -> {
                            with(binding) {
                                binding.userName.show()
                                rectangle.setBackgroundResource(R.drawable.dialog_right)
                                statusName.hide()
                            }
                        }

                        HouseOfDistributionEnum.DISTRIBUTION_STAGE2_2,
                        HouseOfDistributionEnum.DISTRIBUTION_STAGE2_3,
                        HouseOfDistributionEnum.DISTRIBUTION_STAGE2_10,
                        HouseOfDistributionEnum.DISTRIBUTION_STAGE2_16,
                        HouseOfDistributionEnum.DISTRIBUTION_STAGE2_17,
                        HouseOfDistributionEnum.DISTRIBUTION_STAGE2_19 -> {
                            with(binding) {
                                binding.userName.hide()
                                statusName.show()
                                rectangle.setBackgroundResource(R.drawable.dialog_left)
                            }
                        }

                        else -> {
                            //Сюда никогда не зайдет ветка
                        }
                    }
                }
            }

            HouseOfDistributionEnum.DISTRIBUTION_STAGE2_5,
            HouseOfDistributionEnum.DISTRIBUTION_STAGE2_6,
            HouseOfDistributionEnum.DISTRIBUTION_STAGE2_7,
            HouseOfDistributionEnum.DISTRIBUTION_STAGE2_13,
            HouseOfDistributionEnum.DISTRIBUTION_STAGE2_14 -> {

                with(binding) {
                    bgHouseOfDistr1.gone()
                    bgHouseOfDistrMinigame.gone()
                    bgHouseOfDistrMemo.gone()
                    bgHouseOfDistr2.gone()
                    bgHouseOfDistr20.gone()
                    bgHouseOfDistr3.show()
                    bgHouseOfDistr4.gone()
                    bgHouseOfDistr5.gone()
                    womanCenter.gone()
                    womanCenterOnLeft.gone()
                    womanLeft.gone()
//                    gameStage.txt.first?.let { text.setText(it) } ?: ""
                    setAnimationText(text, gameStage.txt.first?.let { resources.getString(it) })
                }

                when (gameStage) {
                    HouseOfDistributionEnum.DISTRIBUTION_STAGE2_5,
                    HouseOfDistributionEnum.DISTRIBUTION_STAGE2_6,
                    HouseOfDistributionEnum.DISTRIBUTION_STAGE2_14 -> {
                        with(binding) {
                            binding.userName.hide()
                            statusName.show()
                            rectangle.setBackgroundResource(R.drawable.dialog_left)
                        }
                    }

                    HouseOfDistributionEnum.DISTRIBUTION_STAGE2_7,
                    HouseOfDistributionEnum.DISTRIBUTION_STAGE2_13 -> {
                        with(binding) {
                            binding.userName.show()
                            rectangle.setBackgroundResource(R.drawable.dialog_right)
                            statusName.hide()
                        }
                    }

                    else -> {}
                }
            }

            HouseOfDistributionEnum.DISTRIBUTION_STAGE2_8 -> {

                with(binding) {
                    bgHouseOfDistr1.gone()
                    bgHouseOfDistrMinigame.gone()
                    bgHouseOfDistrMemo.gone()
                    bgHouseOfDistr2.gone()
                    bgHouseOfDistr20.gone()
                    bgHouseOfDistr3.gone()
                    bgHouseOfDistr4.show()
                    bgHouseOfDistr5.gone()
                    womanCenter.gone()
                    womanCenterOnLeft.gone()
                    womanLeft.gone()

//                    gameStage.txt.first?.let { text.setText(it) } ?: ""
                    setAnimationText(text, gameStage.txt.first?.let { resources.getString(it) })

                    binding.userName.hide()
                    statusName.show()
                    rectangle.setBackgroundResource(R.drawable.dialog_left)
                }
            }

            HouseOfDistributionEnum.DISTRIBUTION_STAGE2_12,
            HouseOfDistributionEnum.DISTRIBUTION_STAGE2_15 -> {

                with(binding) {
                    bgHouseOfDistr1.gone()
                    bgHouseOfDistrMinigame.gone()
                    bgHouseOfDistrMemo.gone()
                    bgHouseOfDistr2.gone()
                    bgHouseOfDistr20.gone()
                    bgHouseOfDistr3.gone()
                    bgHouseOfDistr4.gone()
                    bgHouseOfDistr5.show()
                    womanCenter.gone()
                    womanCenterOnLeft.gone()
                    womanLeft.gone()
//                    gameStage.txt.first?.let { text.setText(it) } ?: ""
                    setAnimationText(text, gameStage.txt.first?.let { resources.getString(it) })
                    binding.userName.hide()
                    statusName.show()
                    rectangle.setBackgroundResource(R.drawable.dialog_left)
                }
            }


            HouseOfDistributionEnum.DISTRIBUTION_STAGE3_1,
            HouseOfDistributionEnum.DISTRIBUTION_STAGE3_14 -> {
                with(binding) {
                    bgHouseOfDistr1.gone()
                    bgHouseOfDistrMinigame.gone()
                    bgHouseOfDistrMemo.gone()
                    bgHouseOfDistr2.gone()
                    bgHouseOfDistr20.show()
                    bgHouseOfDistr3.gone()
                    bgHouseOfDistr4.gone()
                    bgHouseOfDistr5.gone()
                    womanCenter.show()
                    womanCenterOnLeft.gone()
                    womanLeft.gone()
                    text.show()
//                    gameStage.txt.first?.let { text.setText(it) } ?: ""
                    setAnimationText(text, gameStage.txt.first?.let { resources.getString(it) })
                    binding.userName.hide()
                    statusName.show()
                    rectangle.setBackgroundResource(R.drawable.dialog_left)
                }
            }

            HouseOfDistributionEnum.DISTRIBUTION_STAGE3_2,
            HouseOfDistributionEnum.DISTRIBUTION_STAGE3_3,
            HouseOfDistributionEnum.DISTRIBUTION_STAGE3_4,
            HouseOfDistributionEnum.DISTRIBUTION_STAGE3_5,
            HouseOfDistributionEnum.DISTRIBUTION_STAGE3_6,
            HouseOfDistributionEnum.DISTRIBUTION_STAGE3_7,
            HouseOfDistributionEnum.DISTRIBUTION_STAGE3_8,
            HouseOfDistributionEnum.DISTRIBUTION_STAGE3_9,
            HouseOfDistributionEnum.DISTRIBUTION_STAGE3_10,
            HouseOfDistributionEnum.DISTRIBUTION_STAGE3_11,
            HouseOfDistributionEnum.DISTRIBUTION_STAGE3_12,
            HouseOfDistributionEnum.DISTRIBUTION_STAGE3_13 -> {
                with(binding) {
                    bgHouseOfDistr1.gone()
                    bgHouseOfDistrMinigame.gone()
                    bgHouseOfDistrMemo.gone()
                    bgHouseOfDistr2.show()
                    bgHouseOfDistr20.gone()
                    bgHouseOfDistr3.gone()
                    bgHouseOfDistr4.gone()
                    bgHouseOfDistr5.gone()
                    catStatus.hide()
                    womanCenter.gone()
                }

                when (gameStage) {
                    HouseOfDistributionEnum.DISTRIBUTION_STAGE3_2,
                    HouseOfDistributionEnum.DISTRIBUTION_STAGE3_4,
                    HouseOfDistributionEnum.DISTRIBUTION_STAGE3_6,
                    HouseOfDistributionEnum.DISTRIBUTION_STAGE3_8,
                    HouseOfDistributionEnum.DISTRIBUTION_STAGE3_10,
                    HouseOfDistributionEnum.DISTRIBUTION_STAGE3_12,
                    -> {
                        with(binding) {
                            radioGroup.gone()
//                            gameStage.txt.first?.let { text.setText(it) } ?: ""
                            setAnimationText(
                                text,
                                gameStage.txt.first?.let { resources.getString(it) })
                            text.show()
                            binding.userName.hide()
                            womanCenterOnLeft.gone()
                            womanLeft.show()
                            statusName.text = "Тамара"
                            statusName.show()
                            rectangle.setBackgroundResource(R.drawable.dialog_left)
                        }
                    }

                    HouseOfDistributionEnum.DISTRIBUTION_STAGE3_3,
                    HouseOfDistributionEnum.DISTRIBUTION_STAGE3_5,
                    HouseOfDistributionEnum.DISTRIBUTION_STAGE3_7,
                    HouseOfDistributionEnum.DISTRIBUTION_STAGE3_9,
                    HouseOfDistributionEnum.DISTRIBUTION_STAGE3_11,
                    HouseOfDistributionEnum.DISTRIBUTION_STAGE3_13 -> {
                        with(binding) {
                            text.hide()
                            radioGroup.clearCheck()
                            radioGroup.show()
                            gameStage.txt.first?.let { radioButton1.setText(it) } ?: ""
                            gameStage.txt.second?.let { radioButton2.setText(it) } ?: ""
                            binding.userName.show()
                            womanCenterOnLeft.show()
                            womanLeft.gone()
                            statusName.text = "Статус"
                            statusName.hide()
                            rectangle.setBackgroundResource(R.drawable.dialog_right)
                        }
                    }

                    else -> {}
                }
            }

            HouseOfDistributionEnum.DISTRIBUTION_STAGE4_1,
            HouseOfDistributionEnum.DISTRIBUTION_STAGE4_2 -> {
                with(binding) {
                    bgHouseOfDistr1.gone()
                    bgHouseOfDistrMinigame.show()
                    bgHouseOfDistrMemo.gone()
                    bgHouseOfDistr2.gone()
                    bgHouseOfDistr20.gone()
                    bgHouseOfDistr3.gone()
                    bgHouseOfDistr4.gone()
                    bgHouseOfDistr5.gone()
                    womanCenter.gone()
                    womanCenterOnLeft.gone()
                    womanLeft.gone()
                    text.show()
//                    gameStage.txt.first?.let { text.setText(it) } ?: ""
                    setAnimationText(text, gameStage.txt.first?.let { resources.getString(it) })

                    when (gameStage) {
                        HouseOfDistributionEnum.DISTRIBUTION_STAGE4_1 -> {
                            with(binding) {
                                binding.userName.hide()
                                statusName.text = "Статус"
                                statusName.show()
                                rectangle.setBackgroundResource(R.drawable.dialog_left)
                            }
                        }

                        HouseOfDistributionEnum.DISTRIBUTION_STAGE4_2 -> {
                            with(binding) {
                                binding.userName.show()
                                rectangle.setBackgroundResource(R.drawable.dialog_right)
                                statusName.text = "Статус"
                                statusName.hide()
                            }
                        }

                        else -> {}
                    }
                }
            }

            HouseOfDistributionEnum.DISTRIBUTION_STAGE4_3 -> {
                with(binding) {
                    bgHouseOfDistr1.gone()
                    bgHouseOfDistrMinigame.gone()
                    bgHouseOfDistrMemo.show()
                    bgHouseOfDistr2.gone()
                    bgHouseOfDistr20.gone()
                    bgHouseOfDistr3.gone()
                    bgHouseOfDistr4.gone()
                    bgHouseOfDistr5.gone()
                    womanCenter.gone()
                    womanCenterOnLeft.gone()
                    womanLeft.gone()
                    text.show()
//                    gameStage.txt.first?.let { text.setText(it) } ?: ""
                    setAnimationText(text, gameStage.txt.first?.let { resources.getString(it) })
                    binding.userName.hide()
                    statusName.text = "Статус"
                    statusName.show()
                    rectangle.setBackgroundResource(R.drawable.dialog_left)
                }
            }

            HouseOfDistributionEnum.DISTRIBUTION_STAGE_FINISH -> {
                if (stressElement == Stress.GREEN) {
                    findNavController().navigate(R.id.action_houseOfDistributionFragment_to_gameMemoDistrFragment)
                } else {
                    /*** flRed Поменять на переход на Книгу Правил*/
                    findNavController().navigate(R.id.action_houseOfDistributionFragment_to_gameMemoDistrFragment)
                }
            }
        }
    }

    private fun setAnimationText(view: TextView, text: String?) {

        if (text != null) {
            view.animateCharacterByCharacter2(
                text = text,
                animator = currentAnimator
            )

            lifecycleScope.launch {
                delay(25L * text.length.toLong())
            }
        } else {
            view.text = ""
        }
    }
}