package com.example.cocktailApp.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.cocktailApp.R
import com.example.cocktailApp.adapter.DrinkAdapter
import com.example.cocktailApp.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel
    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchViewModel =
            ViewModelProvider(this).get(SearchViewModel::class.java)

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val binding: FragmentSearchBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_search, container, false
        )

        // Création de notre adapter et d'un évenement click sur les éléments
        val adapter = DrinkAdapter { drink ->
            val action = SearchFragmentDirections.actionNavigationSearchToNavigationDetail(idDrink = drink.idDrink)
            findNavController().navigate(action)
        }
        // Initialisation de l'adapter
        binding.recyclerDrink.adapter = adapter
        // Observeur listDrink
        searchViewModel.searchedDrinkLiveData.observe(viewLifecycleOwner, {
            adapter.updateList(it.drinks)
            Log.e("Error", "Passage dans l'update")
        })
        // Observer error
        searchViewModel.apiErrorLiveData.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), "Error !", Toast.LENGTH_SHORT).show()
        })
        // Get all drink
        searchViewModel.getDrinks("")
        // Search plants
        binding.searchDrink.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(txt: String): Boolean {
                searchViewModel.getDrinks(txt)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}