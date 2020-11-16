package com.pauln.catpedia.ui.catdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.pauln.catpedia.R
import com.pauln.catpedia.databinding.FragmentCatDetailsBinding
import com.pauln.catpedia.domain.model.Cat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_cat_details.*

const val ARG_CAT = "cat"

@AndroidEntryPoint
class CatDetailsFragment : Fragment() {

    private val catDetailsViewModel: CatDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cat_details, container, false)
        val cat: Cat? = arguments?.getParcelable(ARG_CAT)
        setupDataBinding(view, cat)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        catDetailsViewModel.navigateBackEvent.observe(viewLifecycleOwner, {
            Navigation.findNavController(requireView()).navigateUp()
        })
    }

    private fun setupDataBinding(view: View, catDetails: Cat?) {
        val binding = FragmentCatDetailsBinding.bind(view)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            catDetailsVM = catDetailsViewModel
            cat = catDetails
        }
    }
}