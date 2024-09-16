package com.overnightstay.view.reg

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.overnightstay.R
import com.overnightstay.databinding.FragmentRegBinding
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
            viewModel.isEntry.collect {
                if (it) {
                    val bundle = bundleOf("amount" to it)

                    findNavController().navigate(R.id.action_regFragment_to_congrFragment, bundle)
                } else {
                    Snackbar.make(
                        binding.root,
                        "Ошибка сервера.",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
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
                    binding.etLogin.text.toString(),
                    binding.etPass.text.toString(),
                    binding.etEmail.text.toString(),
                    binding.etPhone.text.toString(),
                    binding.etName.text.toString(),
                    binding.etSecname.text.toString()
                )
            )
        }
    }
}