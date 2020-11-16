package com.pauln.catpedia.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.pauln.catpedia.R
import com.pauln.catpedia.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        setupDataBinding(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginViewModel.openCatListEvent.observe(viewLifecycleOwner, {
            navigateToCatList()
        })
    }

    private fun setupDataBinding(view: View) {
        val binding = FragmentLoginBinding.bind(view)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = loginViewModel
        }
    }

    private fun navigateToCatList() {
        Navigation.findNavController(requireView())
            .navigate(R.id.action_loginFragment_to_catListFragment)
    }
}