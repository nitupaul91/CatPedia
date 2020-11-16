package com.pauln.catpedia.ui.catlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pauln.catpedia.R
import com.pauln.catpedia.databinding.FragmentCatListBinding
import com.pauln.catpedia.domain.model.Cat
import com.pauln.catpedia.ui.catdetails.ARG_CAT
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_cat_list.*

const val CAT_COUNTRIES_BOTTOM_SHEET_TAG = "CatCountriesBottomSheet"

@AndroidEntryPoint
class CatListFragment : Fragment() {

    private val catListViewModel: CatListViewModel by viewModels()
    private lateinit var catAdapter: CatListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cat_list, container, false)
        setupDataBinding(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        catListViewModel.openCatFilterEvent.observe(viewLifecycleOwner, {
            CatCountriesBottomFragment.newInstance()
                .show(childFragmentManager, CAT_COUNTRIES_BOTTOM_SHEET_TAG)
        })

        catListViewModel.openCatDetailsEvent.observe(viewLifecycleOwner, { catDetails ->
            catDetails?.let {
                navigateToDetails(catDetails)
            }
        })
    }

    private fun setupDataBinding(view: View) {
        val binding = FragmentCatListBinding.bind(view)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = catListViewModel
        }
    }

    private fun setupRecyclerView() {
        catAdapter = CatListAdapter(catListViewModel)
        catListRv.apply {
            this.adapter = catAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        }
    }

    private fun navigateToDetails(cat: Cat) {
        val args = Bundle()
        args.putParcelable(ARG_CAT, cat)
        arguments = args
        Navigation.findNavController(requireView()).navigate(R.id.catDetailsFragment, args)
    }
}