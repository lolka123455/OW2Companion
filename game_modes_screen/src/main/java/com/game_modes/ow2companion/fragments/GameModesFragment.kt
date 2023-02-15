package com.game_modes.ow2companion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.game_modes.ow2companion.adapter.GameModesAdapter
import com.game_modes.ow2companion.databinding.FragmentGameModesBinding
import com.game_modes.ow2companion.network.models.GameModesItem
import com.game_modes.ow2companion.viewmodels.GameModesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameModesFragment : Fragment() {

    private val binding: FragmentGameModesBinding
        get() = _binding!!
    private var _binding: FragmentGameModesBinding? = null
    private val viewModel: GameModesViewModel by viewModel()
    private val adapter = GameModesAdapter()
    private var listGameModes = listOf<GameModesItem>()


        override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameModesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.gameModesRecyclerView.adapter = adapter
        viewModel.getGameModes()
        observeGameModes()
    }

    private fun observeGameModes(){
        lifecycleScope.launchWhenCreated {
            viewModel.gameModeList.collect{
                listGameModes = it
                adapter.gameModesList = listGameModes
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}