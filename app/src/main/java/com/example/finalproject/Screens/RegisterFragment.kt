package com.example.finalproject.Screens

import android.annotation.SuppressLint
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
import com.example.finalproject.DatabaseModel.AllDatabase.Entity.User
import com.example.finalproject.R
import com.example.finalproject.Repository.UserRepoImp
import com.example.finalproject.SharedPreference.SharedPreferenceManager
import com.example.finalproject.ViewModel.UserViewModel
import com.example.finalproject.ViewModel.UserViewModelFactory

class RegisterFragment : Fragment() {
    lateinit var ed_name : EditText
    lateinit var ed_pass : EditText
    lateinit var ed_passconfirmation : EditText
    lateinit var signUpButton: Button
    lateinit var txt_signin : TextView
    lateinit var userViewModel: UserViewModel


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        signUpButton = view.findViewById(R.id.signup)
        ed_pass = view.findViewById(R.id.ed_pass_signup)
        ed_passconfirmation = view.findViewById(R.id.ed_conf_pass)
        ed_name = view.findViewById(R.id.ed_name_signup)
        txt_signin = view.findViewById(R.id.move_signin)

        val userDao = AppDatabase.getDatabase(requireContext()).userDao()
        val userRepository = UserRepoImp(userDao)
        userViewModel = ViewModelProvider(
            this,
            UserViewModelFactory(userRepository)
        ).get(UserViewModel::class.java)

        signUpButton.setOnClickListener {
            val username = ed_name.text.toString().trim()
            val password = ed_pass.text.toString().trim()
            val conf_password = ed_passconfirmation.text.toString().trim()
            if (username.isNotEmpty() && password.isNotEmpty() && conf_password.isNotEmpty()) {
                if (password == conf_password) {
                    // Check if user exists
                    userViewModel.checkUserExists(username)
                    userViewModel.userExistsStatus.observe(viewLifecycleOwner) { userExists ->
                        if (userExists) {
                            Toast.makeText(requireContext(), "Username is already taken", Toast.LENGTH_LONG).show()
                        } else {
                            val user = User(username, password)
                            userViewModel.registerUser(user)
                            Toast.makeText(requireContext(), "Registration successful", Toast.LENGTH_LONG).show()

                            SharedPreferenceManager.setLoggedIn(requireContext(), true)
                        }
                    }
                } else {
                    Toast.makeText(requireContext(), "Passwords do not match", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(requireContext(), "Fields cannot be empty", Toast.LENGTH_LONG).show()
            }


        }
        txt_signin.setOnClickListener{
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)

        }

        return view
    }
}