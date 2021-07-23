package com.example.cocktailApp.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.cocktailApp.R
import com.example.cocktailApp.adapter.DrinkAdapter
import com.example.cocktailApp.databinding.FragmentDetailBinding
import com.example.cocktailApp.databinding.FragmentSearchBinding
import com.example.cocktailApp.ui.search.SearchFragmentDirections


class DetailFragment : Fragment() {

    private lateinit var detailViewModel: DetailViewModel
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        val binding: FragmentDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_detail, container, false
        )

        // Création de notre adapter et d'un évenement click sur les éléments
        val adapter = DrinkAdapter { }
        // Observeur listDrink
        detailViewModel.detailDrinkLiveData.observe(viewLifecycleOwner, {
            adapter.updateList(it.drinks)
            binding.drink = it.drinks[0]
        })
        // Observer error
        detailViewModel.apiErrorLiveData.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), "Error !", Toast.LENGTH_SHORT).show()
        })
        // Get all drink
        detailViewModel.getDetailDrink(args.idDrink)
        // Search plants
        return binding.root
    }
}