package com.catata.navigationexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.catata.navigationexample.databinding.FragmentInfoBinding
import com.catata.navigationexample.model.Login

class InfoFragment : Fragment() {

    lateinit var binding: FragmentInfoBinding

    var navController: NavController? = null

    private val args:InfoFragmentArgs by navArgs()
    private lateinit var login: Login

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentInfoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login = args.loginInfo //loginInfo is our login_info safe arg


        navController = findNavController(view)

        binding.btnInfoNext.setOnClickListener{

            login.email = binding.etEmail.text.toString()
            login.birthDate = binding.etBirthDate.text.toString()

            val action = InfoFragmentDirections.actionInfoFragmentToHomeFragment(login)

            navController?.navigate(action)
            //navController?.navigate(R.id.action_infoFragment_to_homeFragment)
        }
    }
}