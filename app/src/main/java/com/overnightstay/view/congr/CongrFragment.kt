package com.overnightstay.view.congr

import android.content.Context
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
import com.overnightstay.databinding.FragmentCongrBinding
import com.overnightstay.domain.AppState
import com.overnightstay.domain.models.User
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.launch
import javax.inject.Inject

class CongrFragment : Fragment() {
    private var _binding: FragmentCongrBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CongrViewModel

    @Inject
    lateinit var vmFactory: CongrViewModel.Factory

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
        val arg1 = arguments?.getString("arg1")
        val arg2 = arguments?.getString("arg2")

        viewModel =
            ViewModelProvider(this, vmFactory)[CongrViewModel::class.java]


        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.appState.collect {
                when (it) {
                    is AppState.Success<*> -> {
                        if (it.data is Boolean) {
                            if (it.data) {
                                findNavController().navigate(R.id.action_congrFragment_to_choosePersFragment)
                            } else {
                                binding.btnMain.isEnabled = true
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
                        binding.btnMain.isEnabled = false
                        binding.loadingLayout.root.isGone = false
                    }

                    AppState.None -> {}
                }
            }
        }

        binding.btnMain.setOnClickListener {
            if (arg1 != null && arg2 != null) {
                viewModel.login(User(login = arg1, password = arg2))
            }
        }
    }
}