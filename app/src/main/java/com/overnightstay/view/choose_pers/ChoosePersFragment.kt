package com.overnightstay.view.choose_pers

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.overnightstay.databinding.FragmentChoosePersBinding
import com.overnightstay.view.MainActivity
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.launch

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
            viewLifecycleOwner.lifecycleScope.launch {
                startActivity(Intent(activity, MainActivity::class.java))
                activity?.finish()
            }
        }
    }
}