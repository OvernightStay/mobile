package com.overnightstay.view.book.history

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.overnightstay.R
import com.overnightstay.databinding.FragmentHistoryBinding
import dagger.android.support.AndroidSupportInjection

class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBtnListeners()
    }

    private fun initBtnListeners() = with(binding) {
        home.setOnClickListener {
            findNavController().navigate(R.id.action_historyFragment_to_houseFragment)
        }
        rules.setOnClickListener {
            findNavController().navigate(R.id.action_historyFragment_to_contentsOfBookFragment)
        }
        backpack.setOnClickListener {
            findNavController().navigate(R.id.action_historyFragment_to_backpackFragment)
        }
        map.setOnClickListener {
            findNavController().navigate(R.id.action_historyFragment_to_locationMapFragment)
        }
        backArrow.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }
}