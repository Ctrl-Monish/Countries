package com.internship.mycountries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CountriesAdapter(): RecyclerView.Adapter<CountriesViewHolder>() {
    private val items: ArrayList<Countries> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cards, parent, false)
        return CountriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        val currentItem = items[position]
        holder.country.text = currentItem.country
        holder.capital.text = currentItem.capital
        holder.region.text = currentItem.region
        holder.subRegion.text = currentItem.subRegion
        holder.population.text = currentItem.population
        holder.borders.text = currentItem.borders
        holder.languages.text = currentItem.languages

        Picasso.get().load(currentItem.imageUrl).into(holder.flag)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateCountry(updatedCountries: ArrayList<Countries>) {
        items.clear()
        items.addAll(updatedCountries)
        notifyDataSetChanged()
    }
}

class CountriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val country : TextView = itemView.findViewById(R.id.item_country)
    val capital : TextView = itemView.findViewById(R.id.item_capital)
    val region : TextView = itemView.findViewById(R.id.item_region)
    val subRegion : TextView = itemView.findViewById(R.id.item_subregion)
    val population : TextView = itemView.findViewById(R.id.item_population)
    val borders : TextView = itemView.findViewById(R.id.item_border)
    val languages : TextView = itemView.findViewById(R.id.item_languages)
    val flag : ImageView = itemView.findViewById(R.id.item_flag)
}