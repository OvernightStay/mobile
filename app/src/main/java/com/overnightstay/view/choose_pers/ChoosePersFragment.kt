package com.overnightstay.view.choose_pers

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.overnightstay.databinding.FragmentChoosePersBinding
import dagger.android.support.AndroidSupportInjection

class ChoosePersFragment : Fragment() {
    private var _binding: FragmentChoosePersBinding? = null
    private val binding get() = _binding!!

    private lateinit var  viewModel: ChoosePersViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChoosePersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
    }
}