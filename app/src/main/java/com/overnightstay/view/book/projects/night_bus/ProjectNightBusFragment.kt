package com.overnightstay.view.book.projects.night_bus

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.overnightstay.R
import com.overnightstay.databinding.FragmentProjectNightBusBinding
import dagger.android.support.AndroidSupportInjection

class ProjectNightBusFragment : Fragment() {

    private var _binding: FragmentProjectNightBusBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProjectNightBusBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBtnListeners()
    }

    private fun initBtnListeners() = with(binding) {
        home.setOnClickListener {
            findNavController().navigate(R.id.action_projectNightBusFragment_to_houseFragment)
        }
        rules.setOnClickListener {
            findNavController().navigate(R.id.action_projectNightBusFragment_to_contentsOfBookFragment)
        }
        backpack.setOnClickListener {
            findNavController().navigate(R.id.action_projectNightBusFragment_to_backpackFragment)
        }
        map.setOnClickListener {
            findNavController().navigate(R.id.action_projectNightBusFragment_to_locationMapFragment)
        }
        backArrow.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

}