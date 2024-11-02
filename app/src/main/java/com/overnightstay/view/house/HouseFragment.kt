package com.overnightstay.view.house

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.overnightstay.R
import com.overnightstay.databinding.FragmentHouseBinding

class HouseFragment : Fragment() {

    private var _binding: FragmentHouseBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = HouseFragment()
    }

    private val viewModel: HouseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHouseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBtnListeners()
    }

    private fun initBtnListeners() = with(binding) {
        rules.setOnClickListener {
            findNavController().navigate(R.id.action_houseFragment_to_contentsOfBookFragment)
        }
        backpack.setOnClickListener {
            findNavController().navigate(R.id.action_houseFragment_to_backpackFragment)
        }
        map.setOnClickListener {
            findNavController().navigate(R.id.action_houseFragment_to_locationMapFragment)
        }
        backArrow.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }
}