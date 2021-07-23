package com.example.cocktailApp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailApp.databinding.ItemDrinkBinding
import com.example.cocktailApp.models.Drink

class DrinkAdapter (val onDrinkSelected: (drink: Drink) -> Unit) : RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder>(){
    private val drinks = mutableListOf<Drink>()

    inner class DrinkViewHolder(private val binding: ItemDrinkBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(drink: Drink){
            binding.drink = drink;
            binding.root.setOnClickListener{
                onDrinkSelected(drink)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        return DrinkViewHolder(
            ItemDrinkBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        holder.bind(drinks[position])
    }

    override fun getItemCount(): Int = drinks.size
}