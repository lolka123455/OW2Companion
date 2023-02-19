package com.list_of_heroes.ow2companion.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.ow2companion.R as R2
import com.list_of_heroes.ow2companion.adapter.AllHeroesAdapter
import com.list_of_heroes.ow2companion.databinding.FragmentTanksBinding
import com.list_of_heroes.ow2companion.network.models.AllHeroesItem
import com.list_of_heroes.ow2companion.viewmodels.TankHeroesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

const val KEY_FRAGMENT_HEROES = "TanksFragment"

class TanksFragment : Fragment() {

    private val binding: FragmentTanksBinding
        get() = _binding!!
    private var _binding: FragmentTanksBinding? = null

    private val adapter = AllHeroesAdapter()

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
        binding.gameModesRecyclerView.adapter = adapter
        viewModel.getAllHeroes()
        observeAllHeroes()
        setListenerAdapter()
    }

    private fun setListenerAdapter() {
        adapter.onHeroItemClickListener = {
            val bundle = Bundle().apply {
                putParcelable(KEY_FRAGMENT_HEROES, it)
            }
            requireActivity().findNavController(R2.id.navHostFragment)
                .navigate(R2.id.action_tanksFragment_to_detailsHeroFragment, bundle)
        }
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