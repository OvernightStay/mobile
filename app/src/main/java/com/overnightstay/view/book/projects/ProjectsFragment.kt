package com.overnightstay.view.book.projects

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.overnightstay.R
import com.overnightstay.databinding.FragmentProjectsBinding
import dagger.android.support.AndroidSupportInjection

class ProjectsFragment : Fragment() {

    private var _binding: FragmentProjectsBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProjectsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            home.setOnClickListener {
                findNavController().navigate(R.id.action_projectsFragment_to_houseFragment)
            }
            rules.setOnClickListener {
                findNavController().navigate(R.id.action_projectsFragment_to_contentsOfBookFragment)
            }
            backpack.setOnClickListener {
                findNavController().navigate(R.id.action_projectsFragment_to_backpackFragment)
            }
            map.setOnClickListener {
                findNavController().navigate(R.id.action_projectsFragment_to_locationMapFragment)
            }
            backArrow.setOnClickListener {
                activity?.supportFragmentManager?.popBackStack()
            }
        }

        initBtnListeners()
    }

    private fun initBtnListeners() = with(binding) {
        tvNightBus.setOnClickListener {
            findNavController().navigate(R.id.action_projectsFragment_to_projectNightBusFragment)
        }
        tvHouseShower.setOnClickListener {
            findNavController().navigate(R.id.action_projectsFragment_to_projectHouseShowerFragment)
        }
        tvHouseRehabilitation.setOnClickListener {
            findNavController().navigate(R.id.action_projectsFragment_to_projectHouseRehabilitationFragment)
        }
        tvHouseOfDistribution.setOnClickListener {
            findNavController().navigate(R.id.action_projectsFragment_to_projectHouseOfDistributionFragment)
        }
        tvHouseWarm.setOnClickListener {
            findNavController().navigate(R.id.action_projectsFragment_to_projectHouseWarmFragment)
        }
    }
}