package com.overnightstay.view.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.overnightstay.R
import com.overnightstay.databinding.FragmentAuthBinding
import com.overnightstay.domain.models.User
import com.overnightstay.view.reg.RegViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class AuthFragment : Fragment() {
    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    private lateinit var  viewModel: AuthViewModel

    @Inject
    lateinit var vmFactory: AuthViewModel.Factory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(this, vmFactory)[AuthViewModel::class.java]

        initBtnListeners()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initBtnListeners() = with(binding) {
        tvReg.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_regFragment)
        }

        tvForgot.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_restoreFragment)
        }

        btnEnter.setOnClickListener {
            viewModel.login(User(binding.etLogin.text.toString(),binding.etPass.text.toString()))
//            findNavController().navigate(R.id.action_authFragment_to_choosePersFragment)
        }
    }
}