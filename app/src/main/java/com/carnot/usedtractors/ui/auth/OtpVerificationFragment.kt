package com.carnot.usedtractors.ui.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.carnot.usedtractors.R
import com.carnot.usedtractors.databinding.FragmentOtpVerificationBinding
import com.carnot.usedtractors.ui.auth.helper.GenericKeyEvent
import com.carnot.usedtractors.ui.auth.helper.GenericTextWatcher
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtpVerificationFragment : Fragment() {
    private lateinit var binding: FragmentOtpVerificationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOtpVerificationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etOne.addTextChangedListener(GenericTextWatcher(binding.etOne, binding.etTwo))
        binding.etTwo.addTextChangedListener(GenericTextWatcher(binding.etTwo, binding.etThree))
        binding.etThree.addTextChangedListener(GenericTextWatcher(binding.etThree, binding.etFour))
        binding.etFour.addTextChangedListener(GenericTextWatcher(binding.etFour, null))

        binding.etOne.setOnKeyListener(GenericKeyEvent(binding.etOne, null))
        binding.etTwo.setOnKeyListener(GenericKeyEvent(binding.etTwo, binding.etOne))
        binding.etThree.setOnKeyListener(GenericKeyEvent(binding.etThree, binding.etTwo))
        binding.etFour.setOnKeyListener(GenericKeyEvent(binding.etFour,binding.etThree))
    }
}



