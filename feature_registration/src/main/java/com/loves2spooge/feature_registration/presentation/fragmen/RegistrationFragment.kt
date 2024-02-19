package com.loves2spooge.feature_registration.presentation.fragmen

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.loves2spooge.feature_registration.R
import com.loves2spooge.feature_registration.di.DaggerRegistrationComponent
import com.loves2spooge.feature_registration.di.RegistrationComponent
import com.loves2spooge.feature_registration.presentation.viewmodel.RegistrationViewModel
import com.loves2spooge.feature_registration.presentation.viewmodel.RegistrationViewModelFactory
import com.loves2spooge.navigation.navigate
import javax.inject.Inject


class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private lateinit var registrationComponent: RegistrationComponent

    private lateinit var inputTextFirstName: EditText
    private lateinit var buttonClearFirstName: ImageButton
    private lateinit var inputTextLastName: EditText
    private lateinit var buttonClearLastName: ImageButton
    private lateinit var inputPhoneNumber: EditText
    private lateinit var buttonClearPhoneNumber: ImageButton
    private lateinit var buttonInput: Button

    @Inject
    lateinit var viewModelFactory: RegistrationViewModelFactory

    private val registrationViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[RegistrationViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        registrationComponent = DaggerRegistrationComponent.builder().build()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registrationComponent.inject(this)

        setUI()
        correctInputFirstLastName(buttonClearFirstName, inputTextFirstName)
        correctInputFirstLastName(buttonClearLastName, inputTextLastName)
        correctInputPhoneNumber(buttonClearPhoneNumber, inputPhoneNumber)
        clearField(buttonClearFirstName, inputTextFirstName)
        clearField(buttonClearLastName, inputTextLastName)
        clearField(buttonClearPhoneNumber, inputPhoneNumber)
        goToMainFragment()
    }

    @SuppressLint("CommitPrefEdits")
    private fun goToMainFragment() {
        buttonInput.setOnClickListener {
            val firstName = inputTextFirstName.text.toString()
            val lastName = inputTextLastName.text.toString()
            val phoneNumber = inputPhoneNumber.text.toString()
            val sharedPreferences =
                requireContext().getSharedPreferences("UserData", Context.MODE_PRIVATE)
            sharedPreferences.edit().apply {
                putString("firstName", firstName)
                putString("lastName", lastName)
                putString("phoneNumber", phoneNumber)
                apply()
            }
            navigate(R.id.action_registrationFragment_to_mainFragment)
        }
    }

    private fun setUI() {
        inputTextFirstName = requireActivity().findViewById(R.id.input_first_name)
        buttonClearFirstName = requireActivity().findViewById(R.id.button_delete_first_name)
        inputTextLastName = requireActivity().findViewById(R.id.input_last_name)
        buttonClearLastName = requireActivity().findViewById(R.id.button_delete_last_name)
        inputPhoneNumber = requireActivity().findViewById(R.id.input_phone_number)
        buttonClearPhoneNumber = requireActivity().findViewById(R.id.button_delete_phone_number)
        buttonInput = requireActivity().findViewById(R.id.button_input)
        buttonInput.isEnabled = false
    }

    private fun clearField(buttonClear: ImageButton, field: EditText) {
        buttonClear.setOnClickListener {
            field.text = null
            field.setBackgroundColor(Color.TRANSPARENT)
        }
    }

    private fun correctInputFirstLastName(buttonClear: ImageButton, text: EditText) {
        text.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val input = text.text.toString()
                input.trim()
                if (!input.matches("[а-я]+".toRegex()) && !input.matches("[А-Я]+".toRegex())) {
                    text.setBackgroundColor(Color.RED)
                } else {
                    text.setBackgroundColor(Color.TRANSPARENT)
                }

                if (input.isNotEmpty()) {
                    buttonClear.visibility = View.VISIBLE
                } else {
                    buttonClear.visibility = View.INVISIBLE
                }
            }

            override fun afterTextChanged(s: Editable?) {
//                buttonInput.isClickable = text.toString().isNotBlank()
                buttonInput.isEnabled = text.toString().isNotEmpty()
            }
        })
    }

    private fun correctInputPhoneNumber(buttonClear: ImageButton, number: EditText) {
        number.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val input = number.text.toString()
                if (input.startsWith("+7")) {
                    if (input.length == 1) {
                        number.setText("")
                        return
                    } else if (input.length == 3) {
                        number.setText("+7 ")
                        number.setSelection(number.text.length)
                    }
                }
                val filteredInput = input.filter { it.isDigit() }
                if (filteredInput != input) {
                    number.setText(filteredInput)
                    number.setSelection(filteredInput.length)
                }

                if (input.isNotEmpty()) {
                    buttonClear.visibility = View.VISIBLE
                } else {
                    buttonClear.visibility = View.INVISIBLE
                }
            }

            override fun afterTextChanged(s: Editable?) {
//                buttonInput.isClickable = number.toString().isNotBlank()
                buttonInput.isEnabled = number.toString().isNotEmpty()
            }
        })
    }
}
