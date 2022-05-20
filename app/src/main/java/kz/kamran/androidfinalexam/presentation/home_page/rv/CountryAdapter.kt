package kz.kamran.androidfinalexam.presentation.home_page.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kz.kamran.androidfinalexam.data.model.Country
import kz.kamran.androidfinalexam.databinding.ItemCountryBinding

class CountryAdapter:RecyclerView.Adapter<CountryViewHolder>() {

    var countries:List<Country> = mutableListOf()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    var onItemClick: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding =ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val item = countries[position]
        holder.textViewCountry.text = item.country
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(item.slug)
        }
    }

    override fun getItemCount() = countries.size
}

