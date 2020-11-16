package com.pauln.catpedia.ui.catlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pauln.catpedia.databinding.ItemListCatCountryBinding
import com.pauln.catpedia.domain.model.Country

class CatCountriesAdapter(
    private val viewModel: CatListViewModel
) :
    RecyclerView.Adapter<CatCountriesAdapter.ViewHolder>() {

    private val countries: MutableList<Country> = mutableListOf()

    fun setCountries(newCountries: List<Country>) {
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListCatCountryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(countries[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = DataBindingUtil.getBinding<ItemListCatCountryBinding>(itemView)

        fun bindView(country: Country) {
            binding?.country = country
            binding?.catListVM = viewModel
        }
    }
}