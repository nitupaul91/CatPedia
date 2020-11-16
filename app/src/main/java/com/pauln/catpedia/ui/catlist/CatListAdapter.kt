package com.pauln.catpedia.ui.catlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pauln.catpedia.databinding.ItemListCatBinding
import com.pauln.catpedia.domain.model.Cat

class CatListAdapter(private val viewModel: CatListViewModel) :
    RecyclerView.Adapter<CatListAdapter.ViewHolder>() {

    private val cats: MutableList<Cat> = mutableListOf()

    fun setCats(newCats: List<Cat>) {
        cats.clear()
        cats.addAll(newCats)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListCatBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun getItemCount() = cats.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(cats[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = DataBindingUtil.getBinding<ItemListCatBinding>(itemView)

        fun bindView(cat: Cat) {
            binding?.cat = cat
            binding?.catListViewModel = viewModel
        }
    }
}