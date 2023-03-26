package com.player_details_info_screen.ow2companion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.player_details_info_screen.ow2companion.databinding.FragmentCompetitivePlayerDetailsInfoBinding
import com.player_details_info_screen.ow2companion.network.models.competitive.PlayerStatsSummaryCompetitive
import com.player_details_info_screen.ow2companion.viewmodels.CompetitivePlayerDetailsInfoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CompetitivePlayerDetailsInfoFragment : Fragment() {

    private val binding: FragmentCompetitivePlayerDetailsInfoBinding
        get() = _binding!!
    private var _binding: FragmentCompetitivePlayerDetailsInfoBinding? = null

    private val viewModel: CompetitivePlayerDetailsInfoViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCompetitivePlayerDetailsInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        setInitialData()
    }

    private fun setInitialData() {
        arguments?.getString("player")?.let { player ->
            viewModel.getViewPagerCompetitiveDetailsInfo(player)
        } ?: run {
            showErrorMessage("No player was found!")
        }
    }

    private fun showErrorMessage(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        requireActivity().onNavigateUp()
    }

    private fun observe() {
        observePlayerStatsSummaryQuickPlay()
    }

    private fun observePlayerStatsSummaryQuickPlay() {
        lifecycleScope.launchWhenStarted {
            viewModel.playerStatsSummaryCompetitive.collect { setPlayerStatsSummaryQuickPlay(it) }
        }
    }

    private fun setPlayerStatsSummaryQuickPlay(item: PlayerStatsSummaryCompetitive?) {

        if (item == null) return

        with(binding) {
            quantityGamesPlayed.text = item.general.games_played.toString()
            quantityTimePlayed.text = item.general.time_played.formatSecondsToHHMMSS()
            quantityWinrate.text = item.general.winrate.toString()
            quantityKda.text = item.general.kda.toString()
            quantityEliminations.text = item.general.total.eliminations.toString()
            quantityAssists.text = item.general.total.assists.toString()
            quantityDeaths.text = item.general.total.deaths.toString()
            quantityDamage.text = item.general.total.damage.toString()
            quantityHealing.text = item.general.total.healing.toString()
        }

    }

    private fun Int.formatSecondsToHHMMSS(): String {
        val seconds = this % 60
        val minutes = (this % 3600) / 60
        val hours = this / 3600
        return if (hours > 0)
            String.format("%02d:%02d:%02d", hours, minutes, seconds)
        else
            String.format("%02d:%02d", minutes, seconds)
    }

}