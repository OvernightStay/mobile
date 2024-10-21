package com.overnightstay.view.house_of_distribution.minigame

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.overnightstay.R
import com.overnightstay.databinding.FragmentGameMemoDistrBinding
import com.overnightstay.domain.models.IndexMemoGame
import com.overnightstay.utils.hide
import com.overnightstay.utils.show
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class GameMemoDistrFragment : Fragment() {
    private lateinit var arrFieldMemo: Array<Array<MemoCard>>

    private var _binding: FragmentGameMemoDistrBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: GameMemoDistrViewModel

    @Inject
    lateinit var vmFactory: GameMemoDistrViewModel.Factory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameMemoDistrBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(this, vmFactory)[GameMemoDistrViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.gameMemoStateFlow.collect {
                gameLogic(it)
            }
        }
    }

    private suspend fun gameLogic(state: GameMemoDistrState) {
        when (state) {
            is GameMemoDistrState.MemoFirstMatch<*> -> {}
            is GameMemoDistrState.MemoSecondMatch<*> -> {}
            is GameMemoDistrState.MemoNotMatch<*> -> {
            }

            is GameMemoDistrState.MemoMatchAfterNot<*> -> {}
            is GameMemoDistrState.MemoLastMatch<*> -> {}
            is GameMemoDistrState.MemoFinish<*> -> {}

            is GameMemoDistrState.MemoOpenedCard<*> -> {
                if(state.data is IndexMemoGame) {
                    arrFieldMemo[state.data.i][state.data.j].view.setBackgroundResource(R.drawable.background_memo_card)
                    arrFieldMemo[state.data.i][state.data.j].view.setImageResource(
                        arrFieldMemo[state.data.i][state.data.j].memoElement.imageViewId)
                }
            }

            GameMemoDistrState.MemoStart -> {
                initFieldMemo(MemoElementEnum.fillMemoField())

                viewModel.allReverseCards()

                /*        arrFieldMemo.forEachIndexed { _, subArray ->
                            subArray.forEachIndexed { _, value ->
                                value.view.setOnClickListener {
                                    value.view.setBackgroundResource(R.drawable.background_memo_card)
                                    value.view.setImageResource(value.memoElement.imageViewId)
                                }
                            }
                        }*/
                arrFieldMemo.forEachIndexed { i, subArray ->
                    subArray.forEachIndexed { j, value ->
                        value.view.setOnClickListener {
                            viewModel.openCard(i, j)
                        }
                    }
                }
            }

            is GameMemoDistrState.MemoSuccess<*> -> {

            }

            is GameMemoDistrState.MemoCheckingCards<*> -> {


                if (state.data is Pair<*, *>) {
                    val firstElement = state.data.first
                    val secondElement = state.data.second

                    if ((firstElement is IndexMemoGame) && (secondElement is IndexMemoGame)) {
                        lifecycleScope.launch {
                            arrFieldMemo[secondElement.i][secondElement.j].view.setBackgroundResource(R.drawable.background_memo_card)
                            arrFieldMemo[secondElement.i][secondElement.j].view.setImageResource(
                                arrFieldMemo[secondElement.i][secondElement.j].memoElement.imageViewId)

                            delay(2000L)
                            if (arrFieldMemo[firstElement.i][firstElement.j].memoElement == arrFieldMemo[secondElement.i][secondElement.j].memoElement) {
                                arrFieldMemo[firstElement.i][firstElement.j].view.hide()
                                arrFieldMemo[secondElement.i][secondElement.j].view.hide()

                                when (arrFieldMemo[firstElement.i][firstElement.j].memoElement) {
                                    MemoElementEnum.BACKPACK -> binding.memoBackpack.show()
                                    MemoElementEnum.BOOTS -> binding.memoBoots.show()
                                    MemoElementEnum.DRESS -> binding.memoDress.show()
                                    MemoElementEnum.JACKET -> binding.memoJacket.show()
                                    MemoElementEnum.RAZOR -> binding.memoRazor.show()
                                    MemoElementEnum.SHAMPOO -> binding.memoShampoo.show()
                                    MemoElementEnum.SWEATER -> binding.memoSweater.show()
                                    MemoElementEnum.TOOTH -> binding.memoTooth.show()
                                }

                            } else {
                                arrFieldMemo[firstElement.i][firstElement.j].view.setBackgroundResource(
                                    R.drawable.background_memo_backcard
                                )
                                arrFieldMemo[firstElement.i][firstElement.j].view.setImageResource(R.drawable.img_card_back)
                                arrFieldMemo[firstElement.i][firstElement.j].view.show()
                                arrFieldMemo[secondElement.i][secondElement.j].view.setBackgroundResource(
                                    R.drawable.background_memo_backcard
                                )
                                arrFieldMemo[secondElement.i][secondElement.j].view.setImageResource(
                                    R.drawable.img_card_back
                                )
                                arrFieldMemo[secondElement.i][secondElement.j].view.show()

                            }
                        }.join()

                        viewModel.allReverseCards()

                    } else {
                        throw IllegalArgumentException("в этом состоянии должно быть данные индекса открытой карточки")
                    }
                } else {
                    throw IllegalArgumentException("в этом состоянии должно быть данные индекса открытой карточки")
                }
            }

            is GameMemoDistrState.MemoReverseCard<*> -> {}
        }

    }

    private fun initFieldMemo(arrCard: Array<Array<MemoElementEnum>>) {
        val ivMemoList = with(binding) {
            listOf(
                ivMemo11, ivMemo12, ivMemo13, ivMemo14,
                ivMemo21, ivMemo22, ivMemo23, ivMemo24,
                ivMemo31, ivMemo32, ivMemo33, ivMemo34,
                ivMemo41, ivMemo42, ivMemo43, ivMemo44,
            )
        }

        arrFieldMemo = Array(4) { i ->
            Array(4) { j ->
                MemoCard(ivMemoList[i * 4 + j], arrCard[i][j], false, false)

            }
        }
    }

    data class MemoCard(
        val view: ImageView,
        val memoElement: MemoElementEnum,
        var isOpen: Boolean,
        var isMatched: Boolean
    )

    enum class MemoElementEnum(val imageViewId: Int, var isOpened: Boolean = false) {
        BACKPACK(R.drawable.img_memo_backpack),
        BOOTS(R.drawable.img_memo_boots),
        DRESS(R.drawable.img_memo_dress),
        JACKET(R.drawable.img_memo_jacket),
        RAZOR(R.drawable.img_memo_razor),
        SHAMPOO(R.drawable.img_memo_shampoo),
        SWEATER(R.drawable.img_memo_sweater),
        TOOTH(R.drawable.img_memo_tooth);

        companion object {
            fun fillMemoField(): Array<Array<MemoElementEnum>> {
                val list = entries.flatMap { listOf(it, it) }
                val memoField = list.shuffled().toTypedArray()
                return Array(4) { i ->
                    Array(4) { j ->
                        memoField[i * 4 + j]
                    }
                }
            }
        }
    }
}