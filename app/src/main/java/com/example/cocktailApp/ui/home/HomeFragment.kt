package com.example.cocktailApp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.cocktailApp.R
import com.example.cocktailApp.adapter.DrinkAdapter
import com.example.cocktailApp.databinding.FragmentHomeBinding
import com.example.cocktailApp.databinding.FragmentSearchBinding
import com.example.cocktailApp.ui.search.SearchFragmentDirections

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )

        // Création de notre adapter et d'un évenement click sur les éléments
        val adapter = DrinkAdapter { drink ->
            val action = SearchFragmentDirections.actionNavigationSearchToNavigationDetail(idDrink = drink.idDrink)
            findNavController().navigate(action)
        }
        // Initialisation de l'adapter
        binding.recyclerDrinkHome.adapter = adapter
        // Observeur listDrink
        homeViewModel.homeDrinkLiveData.observe(viewLifecycleOwner, {
            adapter.addList(it)
        })
        // Observer error
        homeViewModel.apiErrorLiveData.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), "Error !", Toast.LENGTH_SHORT).show()
        })
        // Get random drink
        homeViewModel.getRandomDrink()


        return binding.root
    }
}