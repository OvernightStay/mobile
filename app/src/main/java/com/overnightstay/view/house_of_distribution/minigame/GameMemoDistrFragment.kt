package com.overnightstay.view.house_of_distribution.minigame

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.overnightstay.R

class GameMemoDistrFragment : Fragment() {

    companion object {
        fun newInstance() = GameMemoDistrFragment()
    }

    private val viewModel: GameMemoDistrViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_game_memo_distr, container, false)
    }
}