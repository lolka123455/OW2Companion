package com.list_of_heroes.ow2companion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.list_of_heroes.ow2companion.adapter.AllHeroesAdapterList
import com.list_of_heroes.ow2companion.databinding.FragmentTanksBinding
import com.list_of_heroes.ow2companion.network.models.AllHeroesItem
import com.list_of_heroes.ow2companion.viewmodels.TankHeroesViewModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class TanksFragment : Fragment(), AllHeroesAdapterList.HeroItemListener {

    private val binding: FragmentTanksBinding
        get() = _binding!!
    private var _binding: FragmentTanksBinding? = null

    private var listAllHeroes = listOf<AllHeroesItem>()

    private val viewModel: TankHeroesViewModel by viewModel()

    private val itemAdapter: ItemAdapter<AllHeroesAdapterList> = ItemAdapter()
    private val recyclerViewAdapter = FastAdapter.with(itemAdapter)

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
        binding.gameModesRecyclerView.adapter = recyclerViewAdapter
        binding.gameModesRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        observeAllHeroes()
    }

    private fun observeAllHeroes() {
        lifecycleScope.launchWhenCreated {
            viewModel.allHeroesList.collect {
                listAllHeroes = it
                val heroes = it.map { heroItem ->
                    AllHeroesAdapterList(heroItem, this@TanksFragment)
                }
                FastAdapterDiffUtil[itemAdapter] = heroes
            }
        }
    }

    override fun onClickedHero(heroName: String) {
        val bundle = Bundle()
        bundle.putString("key", heroName)
        navigateToDetailsHeroFragment(bundle)
    }

    private fun navigateToDetailsHeroFragment(bundle: Bundle) {
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