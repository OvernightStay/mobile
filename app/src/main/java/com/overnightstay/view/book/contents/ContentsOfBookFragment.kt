package com.overnightstay.view.book.contents

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.overnightstay.R
import com.overnightstay.databinding.FragmentContentsOfBookBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ContentsOfBookFragment : Fragment() {
    private var _binding: FragmentContentsOfBookBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ContentsOfBookViewModel

    @Inject
    lateinit var vmFactory: ContentsOfBookViewModel.Factory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContentsOfBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(this, vmFactory)[ContentsOfBookViewModel::class.java]

        binding.tvProjects.setOnClickListener {
            findNavController().navigate(R.id.action_contentsOfBookFragment_to_overNightStayProjectsFragment)
        }

//        viewLifecycleOwner.lifecycleScope.launch {
//            viewModel.appState.collect {
//                when (it) {
//                    is AppState.Success<*> -> {
//                        when (it.data) {
//                            is User -> {
//                                println("ChoosePersViewModel it: $it")
//                                if (it.data.userName?.isNotEmpty() == true) {
//                                    binding.etLogin.setText(it.data.userName)
//                                } else {
//                                    binding.etLogin.setText("${it.data.first_name} ${it.data.last_name}")
//                                }
//
//                                if (it.data.gender?.isNotEmpty() == true) {
//                                    if (it.data.gender == "M") {
//                                        binding.ivMan.isSelected = true
//                                        binding.ivWoman.isSelected = false
//                                    } else {
//                                        binding.ivMan.isSelected = false
//                                        binding.ivWoman.isSelected = true
//                                    }
//                                } else {
//                                    binding.ivWoman.isSelected = false
//                                    binding.ivMan.isSelected = false
//                                }
//                            }
//
//                            is Boolean -> {
//                                if (it.data) {
//                                    startActivity(Intent(activity, MainActivity::class.java))
//                                    activity?.finish()
//                                } else {
//                                    binding.btnBegin.isEnabled = true
//                                    binding.loadingLayout.root.isGone = true
//
//                                    Snackbar.make(
//                                        binding.root,
//                                        "Ошибка сервера.",
//                                        Snackbar.LENGTH_LONG
//                                    ).show()
//                                }
//                            }
//                        }
//                    }
//
//                    is AppState.Error -> {}
//                    AppState.Loading -> {
//                        binding.btnBegin.isEnabled = false
//                        binding.loadingLayout.root.isGone = false
//                    }
//
//                    AppState.None -> {}
//                }
//            }
//        }

        initBtnListeners()
    }


    private fun initBtnListeners() = with(binding) {

    }
}