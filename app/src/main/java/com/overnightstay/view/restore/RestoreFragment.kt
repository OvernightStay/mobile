package com.overnightstay.view.restore

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.overnightstay.databinding.FragmentAuthBinding
import com.overnightstay.databinding.FragmentRestoreBinding
import dagger.android.support.AndroidSupportInjection

class RestoreFragment : Fragment() {
    private var _binding: FragmentRestoreBinding? = null
    private val binding get() = _binding!!

    private lateinit var  viewModel: RestoreViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
    }
}