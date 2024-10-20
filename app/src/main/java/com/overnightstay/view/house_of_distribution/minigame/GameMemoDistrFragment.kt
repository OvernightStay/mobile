package com.overnightstay.view.house_of_distribution.minigame

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.overnightstay.R
import com.overnightstay.databinding.FragmentGameMemoDistrBinding
import com.overnightstay.view.night_bus.minigame.GameNightBusViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class GameMemoDistrFragment : Fragment() {

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
    }

    enum class memoEnum(val imageViewId: Int) {
        BACKPACK(R.drawable.img_memo_backpack),
        BOOTS(R.drawable.img_memo_boots),
        DRESS(R.drawable.img_memo_dress),
        JACKET(R.drawable.img_memo_jacket),
        RAZOR(R.drawable.img_memo_razor),
        SHAMPOO (R.drawable.img_memo_shampoo),
        SWEATER(R.drawable.img_memo_sweater),
        TOOTH(R.drawable.img_memo_tooth);

        companion object {
            fun fillMemoField(): Array<Array<memoEnum>> {
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