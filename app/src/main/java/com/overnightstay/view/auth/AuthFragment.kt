package com.overnightstay.view.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.overnightstay.R
import com.overnightstay.databinding.FragmentAuthBinding
import com.overnightstay.domain.AppState
import com.overnightstay.domain.models.User
import com.overnightstay.view.MainActivity
import com.overnightstay.view.reg.RegViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthFragment : Fragment() {
    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AuthViewModel

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

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.appState.collect {
                when(it){

                    is AppState.Success<*> -> {
                        if (it.data is Pair<*,*>) {
                            if (it.data.first as Boolean) {
                                if (it.data.second == null) {
                                    findNavController().navigate(R.id.action_authFragment_to_choosePersFragment)
                                } else {
                                    startActivity(Intent(activity, MainActivity::class.java))
                                    activity?.finish()
                                }
                            } else {
                                binding.btnEnter.isEnabled = true
                                binding.loadingLayout.root.isGone = true

                                Snackbar.make(
                                    binding.root,
                                    "Ошибка сервера.",
                                    Snackbar.LENGTH_LONG
                                ).show()
                            }
                        }
                    }

                    is AppState.Error -> {}

                    AppState.Loading -> {
                        binding.btnEnter.isEnabled = false
                        binding.loadingLayout.root.isGone = false
                    }

                    AppState.None -> {}
                }
            }
        }

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
            viewModel.login(User(binding.etLogin.text.toString(), binding.etPass.text.toString()))
        }
    }
}