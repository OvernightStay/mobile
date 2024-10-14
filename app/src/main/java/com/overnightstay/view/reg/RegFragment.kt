package com.overnightstay.view.reg

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.overnightstay.R
import com.overnightstay.databinding.FragmentRegBinding
import com.overnightstay.domain.AppState
import com.overnightstay.domain.models.User
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegFragment : Fragment() {
    private var _binding: FragmentRegBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: RegViewModel

    @Inject
    lateinit var vmFactory: RegViewModel.Factory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(this, vmFactory)[RegViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            println("AuthFragment: запуск authFragmentViewModel.isEntry outside")
            viewModel.appState.collect {
                when (it) {
                    is AppState.Success<*> -> {
                        if (it.data is Triple<*, *, *>) {
                            if (it.data.first as Boolean) {
                                val bundle = bundleOf("arg1" to it.data.second, "arg2" to it.data.third)

                                findNavController().navigate(R.id.action_regFragment_to_congrFragment, bundle)
                            } else {
                                binding.btnReg.isEnabled = true
                                binding.loadingLayout.root.isGone = true

                                Snackbar.make(
                                    binding.root,
                                    "Ошибка сервера.",
                                    Snackbar.LENGTH_LONG
                                ).show()
                            }
                        }
                    }

                    is AppState.Error -> {

                    }

                    AppState.Loading -> {
                        binding.btnReg.isEnabled = false
                        binding.loadingLayout.root.isGone = false
                    }

                    AppState.None -> {}
                }


//                if (it.first) {
//                    val bundle = bundleOf("arg1" to it.second, "arg2" to it.third)
//
//                    findNavController().navigate(R.id.action_regFragment_to_congrFragment, bundle)
//                } else {
//                    Snackbar.make(
//                        binding.root,
//                        "Ошибка сервера.",
//                        Snackbar.LENGTH_LONG
//                    ).show()
//                }
            }
        }

        initBtnListeners()
        initEtListeners()
    }

    private fun initEtListeners() = with(binding) {
        etPass.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                tvError.isVisible = etRepeatpass.text.toString().trim().isNotEmpty() &&
                        (etPass.text.toString().trim() != etRepeatpass.text.toString().trim())
            }
        }
        etRepeatpass.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                tvError.isVisible = etRepeatpass.text.toString().trim().isNotEmpty() &&
                        (etPass.text.toString().trim() != etRepeatpass.text.toString().trim())
            }
        }
    }

    private fun initBtnListeners() = with(binding) {
        chbAgree.setOnClickListener {
            if (chbAgree.isSelected) {
                chbAgree.isSelected = false
            } else {
                chbAgree.isSelected = true
            }
            btnReg.isEnabled = chbAgree.isSelected
        }

        btnReg.setOnClickListener {
            if (binding.etLogin.text.toString().isEmpty() ||
                binding.etPass.text.toString().isEmpty() ||
                binding.etPhone.text.toString().isEmpty() ||
                binding.etName.text.toString().isEmpty() ||
                binding.etSecname.text.toString().isEmpty() ||
                binding.etRepeatpass.text.toString().isEmpty()
            ) {
                Snackbar.make(
                    binding.root,
                    "Заполните обязательные поля.",
                    Snackbar.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            viewModel.reg(
                User(
                    login = binding.etLogin.text.toString(),
                    password = binding.etPass.text.toString(),
                    email = binding.etEmail.text.toString(),
                    phone = binding.etPhone.text.toString(),
                    first_name = binding.etName.text.toString(),
                    last_name = binding.etSecname.text.toString()
                )
            )
        }
    }
}