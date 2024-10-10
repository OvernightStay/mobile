package com.overnightstay.view.choose_pers

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.overnightstay.databinding.FragmentChoosePersBinding
import com.overnightstay.view.MainActivity
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChoosePersFragment : Fragment() {
    private var _binding: FragmentChoosePersBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ChoosePersViewModel

    @Inject
    lateinit var vmFactory: ChoosePersViewModel.Factory

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
        println("ChoosePersFragment onViewCreated")

        viewModel =
            ViewModelProvider(this, vmFactory)[ChoosePersViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {

            println("ChoosePersViewModel lifecycleScope.launch")

            viewModel.userName.collect {
                println("ChoosePersViewModel it: $it")
                if (it.isNotEmpty()) binding.etLogin.setText(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isEntry.collect {
                println("ChoosePersViewModel isEntry: $it")
                if (it) {
                    startActivity(Intent(activity, MainActivity::class.java))
                    activity?.finish()
                }
            }
        }

        viewModel.getPlayerFromPref()
        initBtnListeners()
    }


    private fun initBtnListeners() = with(binding) {
        ivWoman.setOnClickListener {
            if (ivWoman.isSelected) {
                ivWoman.isSelected = false
            } else {
                ivMan.isSelected = false
                ivWoman.isSelected = true
            }
        }
        ivMan.setOnClickListener {
            if (ivMan.isSelected) {
                ivMan.isSelected = false
            } else {
                ivWoman.isSelected = false
                ivMan.isSelected = true
            }
        }
        btnBegin.setOnClickListener {
            if (!ivMan.isSelected && !ivWoman.isSelected) {
                Snackbar.make(
                    binding.root,
                    "Выберите пол персонажа.",
                    Snackbar.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            if (etLogin.text?.isEmpty() == true) {
                Snackbar.make(
                    binding.root,
                    "Поле имени персонажа не должно быть пустым.",
                    Snackbar.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            val gender = if(ivMan.isSelected) "M" else "Ж"
            viewModel.updatePlayer(etLogin.text.toString(), gender)

        }
    }
}