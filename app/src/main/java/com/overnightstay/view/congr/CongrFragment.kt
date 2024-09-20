package com.overnightstay.view.congr

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.overnightstay.R
import com.overnightstay.databinding.FragmentCongrBinding
import com.overnightstay.domain.models.User
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class CongrFragment : Fragment() {
    private var isAuth: Boolean = false
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
            viewModel.isEntry.collect {
                if (it) {
                    isAuth = true
                    delay(3000)
                    findNavController().navigate(R.id.action_congrFragment_to_choosePersFragment)
                } else {
//                    Snackbar.make(
//                        binding.root,
//                        "Ошибка сервера.",
//                        Snackbar.LENGTH_LONG
//                    ).show()
                }
            }
        }

        initBtnListeners()

        if (arg1 != null && arg2 != null) viewModel.login(User(login = arg1, password = arg2))
    }

    private fun initBtnListeners() = with(binding) {
        btnMain.setOnClickListener {
            if (!isAuth) findNavController().navigate(R.id.action_congrFragment_to_authFragment)
        }
    }
}