package com.list_of_heroes.ow2companion.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.list_of_heroes.ow2companion.R
import com.list_of_heroes.ow2companion.adapter.AllHeroesAdapter
import com.list_of_heroes.ow2companion.databinding.FragmentDmgBinding
import com.list_of_heroes.ow2companion.databinding.FragmentTanksBinding
import com.list_of_heroes.ow2companion.network.models.AllHeroesItem
import com.list_of_heroes.ow2companion.viewmodels.AllHeroesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DmgFragment : Fragment() {

    private val binding: FragmentDmgBinding
        get() = _binding!!
    private var _binding: FragmentDmgBinding? = null

    private val adapter = AllHeroesAdapter()

    private var listAllHeroes = listOf<AllHeroesItem>()

    private val viewModel: AllHeroesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDmgBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.gameModesRecyclerView.adapter = adapter
        viewModel.getAllHeroes()
        observeAllHeroes()
    }

    private fun observeAllHeroes(){
        lifecycleScope.launchWhenCreated {
            viewModel.allHeroesList.collect{
                listAllHeroes = it
                adapter.allHeroesList = listAllHeroes
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}