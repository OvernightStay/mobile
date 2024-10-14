package com.overnightstay.view.book.overnightstay_projects

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.overnightstay.databinding.FragmentOverNightStayProjectsBinding
import dagger.android.support.AndroidSupportInjection

class OverNightStayProjectsFragment : Fragment() {
    private var _binding: FragmentOverNightStayProjectsBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOverNightStayProjectsBinding.inflate(inflater, container, false)
        return binding.root
    }

}