package com.pauln.catpedia.ui.bindings

import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.pauln.catpedia.R
import com.pauln.catpedia.domain.model.Cat
import com.pauln.catpedia.domain.model.Country
import com.pauln.catpedia.ui.catlist.CatCountriesAdapter
import com.pauln.catpedia.ui.catlist.CatListAdapter

class ViewBinding {

    companion object {

        @JvmStatic
        @BindingAdapter("snackbarText")
        fun setSnackbarText(layout: ConstraintLayout, text: String?) {
            text?.let {
                Snackbar.make(layout, text, Snackbar.LENGTH_LONG).show()
            }
        }

        @JvmStatic
        @BindingAdapter("snackbarTextId")
        fun setSnackbarTextId(layout: ConstraintLayout, textId: Int?) {
            textId?.let {
                Snackbar.make(layout, textId, Snackbar.LENGTH_LONG).show()
            }
        }

        @JvmStatic
        @BindingAdapter("isLoading")
        fun isLoading(progressBar: ProgressBar, isLoading: Boolean) {
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        @JvmStatic
        @BindingAdapter("buttonText")
        fun setButtonText(button: Button, isLoading: Boolean) {
            button.text = if (isLoading) "" else button.context.getString(R.string.login_btn_text)
        }

        @JvmStatic
        @BindingAdapter("isClickable")
        fun isClickable(button: Button, isLoading: Boolean) {
            button.isClickable = !isLoading
        }

        @JvmStatic
        @BindingAdapter("catImage")
        fun setCatImage(imageView: ImageView, url: String?) {
            Glide.with(imageView.context)
                .load(url)
                .dontAnimate()
                .into(imageView)
        }

        @JvmStatic
        @BindingAdapter("cats")
        fun setCats(recyclerView: RecyclerView, cats: List<Cat>?) {
            val adapter = recyclerView.adapter as CatListAdapter
            cats?.let { adapter.setCats(cats) }
        }

        @JvmStatic
        @BindingAdapter("countries")
        fun setCountries(recyclerView: RecyclerView, countries: List<Country>?) {
            val adapter = recyclerView.adapter as CatCountriesAdapter
            countries?.let { adapter.setCountries(countries) }
        }

        @JvmStatic
        @BindingAdapter("filterVisibility")
        fun setFilterVisibility(fab: FloatingActionButton, isVisible: Boolean) {
            fab.visibility = if (isVisible) View.VISIBLE else View.GONE
        }

        @JvmStatic
        @BindingAdapter("placeholderVisibility")
        fun setPlaceholderVisibility(textView: TextView, isVisible: Boolean) {
            textView.visibility = if (isVisible) View.VISIBLE else View.GONE
        }
    }
}