package com.list_of_heroes.ow2companion.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.list_of_heroes.ow2companion.adapter.AllHeroesAdapter
import com.list_of_heroes.ow2companion.databinding.FragmentDamageBinding
import com.list_of_heroes.ow2companion.network.models.AllHeroesItem
import com.list_of_heroes.ow2companion.viewmodels.DamageHeroesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DamageFragment : Fragment(), AllHeroesAdapter.HeroItemListener {

    private val binding: FragmentDamageBinding
        get() = _binding!!
    private var _binding: FragmentDamageBinding? = null

    private lateinit var adapter: AllHeroesAdapter

    private var listAllHeroes = listOf<AllHeroesItem>()

    private val viewModel: DamageHeroesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDamageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllHeroes()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = AllHeroesAdapter(this)
        binding.gameModesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.gameModesRecyclerView.adapter = adapter

        observeAllHeroes()
    }

    private fun observeAllHeroes(){
        lifecycleScope.launchWhenCreated {
            viewModel.allHeroesList.collect {
                listAllHeroes = it
                adapter.allHeroesList = listAllHeroes
            }
        }
    }

    override fun onClickedHero(heroName: String) {
        val bundle = Bundle()
        bundle.putString("key",heroName)
        findNavController().navigate(com.navigation.ow2companion.R.id.action_listOfHeroesFragment_to_detailsHeroFragment,bundle)
        Toast.makeText(context,"CLICKED",Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}