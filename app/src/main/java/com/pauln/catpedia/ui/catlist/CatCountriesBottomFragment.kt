package com.pauln.catpedia.ui.catlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pauln.catpedia.R
import com.pauln.catpedia.databinding.FragmentCatCountryListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_cat_country_list.*

@AndroidEntryPoint
class CatCountriesBottomFragment : BottomSheetDialogFragment() {

    private val catListViewModel: CatListViewModel by viewModels(this::requireParentFragment)
    private lateinit var catCountriesAdapter: CatCountriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cat_country_list, container, false)
        setupDataBinding(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupDataBinding(view: View) {
        val binding = FragmentCatCountryListBinding.bind(view)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = catListViewModel
        }
    }

    private fun setupRecyclerView() {
        catCountriesAdapter = CatCountriesAdapter(catListViewModel)
        countriesRv.apply {
            this.adapter = catCountriesAdapter
            layoutManager = GridLayoutManager(context, 4)
        }
    }

    companion object {
        fun newInstance(): CatCountriesBottomFragment {
            return CatCountriesBottomFragment()
        }
    }
}
