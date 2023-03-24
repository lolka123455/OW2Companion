package com.game_modes.ow2companion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.game_modes.ow2companion.adapter.GameModesListAdapter
import com.game_modes.ow2companion.databinding.FragmentGameModesBinding
import com.game_modes.ow2companion.network.models.GameModesItem
import com.game_modes.ow2companion.viewmodels.GameModesViewModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import kotlinx.coroutines.flow.filterNotNull
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameModesFragment : Fragment() {

    private val binding: FragmentGameModesBinding
        get() = _binding!!
    private var _binding: FragmentGameModesBinding? = null

    private val viewModel: GameModesViewModel by viewModel()

    private val itemAdapter: ItemAdapter<GameModesListAdapter> = ItemAdapter()
    private val recyclerViewAdapter = FastAdapter.with(itemAdapter)

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
        binding.gameModesRecyclerView.adapter = recyclerViewAdapter
        viewModel.getGameModes()
        observe()
    }

    private fun observe() {
        observeGameModes()
        observeServerResponse()
    }

    private fun observeGameModes() {
        lifecycleScope.launchWhenStarted {
            viewModel.gameModesList.collect { gameModesList ->
                val gameModes = mapGameModesListToGameModesAdapterList(gameModesList)
                FastAdapterDiffUtil[itemAdapter] = gameModes
            }
        }
    }

    private fun mapGameModesListToGameModesAdapterList(
        gameModesList: List<GameModesItem?>?
    ): List<GameModesListAdapter> {
        return gameModesList?.mapNotNull { gameModesItem: GameModesItem? ->
            try {
                gameModesItem?.let { GameModesListAdapter(it) }
            } catch (e: Exception) {
                null
            }
        } ?: emptyList()
    }

    /**
     * Observes the serverResponse variable of the viewModel to show a Toast message
     * to the user whenever it changes.
     * The Toast message will display the message stored in serverResponse if it is not null.
     * After displaying the message, the
     * clearServerResponse() function is called to reset the value of serverResponse to null.
     */
    private fun observeServerResponse() {
        lifecycleScope.launchWhenStarted {
            viewModel.serverResponse
                .filterNotNull()
                .collect { response ->
                    displayServerResponse(response)
                    viewModel.clearServerResponse()
                }
        }
    }

    /**
     * Displays a Toast message.
     * This function is called by observeServerResponse() whenever the serverResponse variable
     * of the viewModel changes.
     *
     * @param response The message to be displayed in the Toast.
     */
    private fun displayServerResponse(response: String) {
        Toast.makeText(requireContext(), response, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}