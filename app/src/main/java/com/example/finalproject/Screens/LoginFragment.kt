package com.example.finalproject.Screens

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.finalproject.DatabaseModel.AllDatabase.Database.AppDatabase
import com.example.finalproject.R
import com.example.finalproject.Repository.UserRepoImp
import com.example.finalproject.SharedPreference.SharedPreferenceManager
import com.example.finalproject.ViewModel.UserViewModel
import com.example.finalproject.ViewModel.UserViewModelFactory

class LoginFragment : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var ed_name : EditText
    lateinit var ed_pass : EditText
    lateinit var btn_login : Button
    lateinit var userViewModel: UserViewModel
    lateinit var txt_signup : TextView
    private  val USER_NAME ="name"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_login, container, false)
        sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        ed_name =view.findViewById(R.id.name_edit_text)
        ed_pass =view.findViewById(R.id.password_edit_text)
        btn_login =view.findViewById(R.id.sign_in_button)
        txt_signup =view.findViewById(R.id.moveRegister)

        val userDao = AppDatabase.getDatabase(requireContext()).userDao()
        val userRepository = UserRepoImp(userDao)
        userViewModel = ViewModelProvider(
            this,
            UserViewModelFactory(userRepository)
        ).get(UserViewModel::class.java)

        btn_login.setOnClickListener{
            var name= ed_name.text.toString().trim()
            var pass= ed_pass.text.toString().trim()
            val editor = sharedPreferences.edit()

            if (name.isNotEmpty() && pass.isNotEmpty()) {
                // Observe login status
                userViewModel.loginUser(name, pass)
                userViewModel.loginStatus.observe(viewLifecycleOwner) { isSuccess ->
                    if (isSuccess) {
                        SharedPreferenceManager.setLoggedIn(requireContext(), true)

                        editor.putString(USER_NAME, name)  // Save the user name
                        editor.apply()

                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment2)
                        val intent = Intent(requireContext(), MainActivity2::class.java)
                        startActivity(intent)
                        requireActivity().finish()                     } else {
                        Toast.makeText(requireContext(), "Name or password is incorrect", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Please enter both name and password", Toast.LENGTH_LONG).show()
            }
        }
        txt_signup.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)

        }


        return  view
    }
}