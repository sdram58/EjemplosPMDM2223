package com.catata.navigationexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.catata.navigationexample.databinding.FragmentLoginBinding
import com.catata.navigationexample.model.Login

class LoginFragment : Fragment() {

    lateinit var binding:FragmentLoginBinding

    lateinit var navController:NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    //In this function view is R.layout.fragment_login
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //We obtain the NavController through findNavigation
        //navController is  defined as member variable of the fragment
        navController = findNavController(view)

        binding.btnLoginNext.setOnClickListener{
            //Navigates to InfoFragment

            //navController.navigate(R.id.action_loginFragment_to_infoFragment)

            val login = Login()
            login.usr = binding.etUser.text.toString()
            login.pass = binding.etPass.text.toString()

            val action = LoginFragmentDirections.actionLoginFragmentToInfoFragment(login)

            navController.navigate(action)

        }
    }
}