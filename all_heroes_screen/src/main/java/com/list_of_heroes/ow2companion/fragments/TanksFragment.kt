package com.list_of_heroes.ow2companion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.list_of_heroes.ow2companion.adapter.AllHeroesAdapter
import com.list_of_heroes.ow2companion.databinding.FragmentTanksBinding
import com.list_of_heroes.ow2companion.network.models.AllHeroesItem
import com.list_of_heroes.ow2companion.viewmodels.TankHeroesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TanksFragment : Fragment(), AllHeroesAdapter.HeroItemListener {

    private val binding: FragmentTanksBinding
        get() = _binding!!
    private var _binding: FragmentTanksBinding? = null

    private lateinit var adapter: AllHeroesAdapter

    private var listAllHeroes = listOf<AllHeroesItem>()

    private val viewModel: TankHeroesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTanksBinding.inflate(inflater, container, false)
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

    private fun observeAllHeroes() {
        lifecycleScope.launchWhenCreated {
            viewModel.allHeroesList.collect {
                listAllHeroes = it
                adapter.allHeroesList = listAllHeroes
            }
        }
    }

    override fun onClickedHero(heroName: String) {
        val bundle = Bundle()
        bundle.putString("key", heroName)
        findNavController().navigate(
            com.navigation.ow2companion.R.id.action_listOfHeroesFragment_to_detailsHeroFragment,
            bundle
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}