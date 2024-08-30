package com.overnightstay.view.congr

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.overnightstay.databinding.FragmentCongrBinding
import dagger.android.support.AndroidSupportInjection

class CongrFragment : Fragment() {
    private var _binding: FragmentCongrBinding? = null
    private val binding get() = _binding!!

    private lateinit var  viewModel: CongrViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCongrBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
    }
}