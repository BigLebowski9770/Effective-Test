package com.loves2spooge.feature_profile

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.loves2spooge.navigation.navigate

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUi()

        val buttonOut = view.findViewById<Button>(R.id.button_output)

        buttonOut.setOnClickListener {
            navigate(R.id.action_mainFragment_to_registrationFragment, com.loves2spooge.navigation.R.id.host_global)
        }
    }

    private fun setUi() {
        val sharedPreferences =
            requireContext().getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val firstName = sharedPreferences.getString("firstName", "")
        val lastName = sharedPreferences.getString("lastName", "")
        val phoneNumber = sharedPreferences.getString("phoneNumber", "")

        requireActivity().findViewById<TextView>(R.id.firstNameProfile).text = firstName
        requireActivity().findViewById<TextView>(R.id.lastNameProfile).text = lastName
        requireActivity().findViewById<TextView>(R.id.phoneNumberProfile).text = phoneNumber
    }

}
