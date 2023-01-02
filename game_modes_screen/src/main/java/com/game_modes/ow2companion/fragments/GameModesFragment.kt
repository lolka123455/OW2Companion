package com.game_modes.ow2companion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.game_modes.ow2companion.adapters.adapter.GameModesAdapter
import com.game_modes.ow2companion.databinding.FragmentGameModesBinding
import com.game_modes.ow2companion.viewmodels.GameModesViewModel
import com.mikepenz.fastadapter.adapters.ItemAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameModesFragment : Fragment() {

    private val binding: FragmentGameModesBinding
        get() = _binding!!
    private var _binding: FragmentGameModesBinding? = null

    private val gameModesItemsAdapter = GameModesAdapter()

    private val viewModel by viewModel<GameModesViewModel>()

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
        binding.gameModesRecyclerView.adapter = gameModesItemsAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}