package com.overnightstay.view.night_bus.finishminigame

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.overnightstay.R

class FinishGameNightBusFragment : Fragment() {

    companion object {
        fun newInstance() = FinishGameNightBusFragment()
    }

    private val viewModel: FinishGameNightBusViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_finish_game_night_bus, container, false)
    }
}