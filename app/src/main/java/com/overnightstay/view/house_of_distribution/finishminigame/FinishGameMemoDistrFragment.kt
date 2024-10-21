package com.overnightstay.view.house_of_distribution.finishminigame

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.overnightstay.R

class FinishGameMemoDistrFragment : Fragment() {

    companion object {
        fun newInstance() = FinishGameMemoDistrFragment()
    }

    private val viewModel: FinishGameMemoDistrViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_finish_game_memo_distr, container, false)
    }
}