package com.player_details_info_screen.ow2companion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.player_details_info_screen.ow2companion.databinding.FragmentQuickplayPlayerDetailsInfoBinding
import com.player_details_info_screen.ow2companion.network.models.quickplay.PlayerStatsSummaryQuickPlay
import com.player_details_info_screen.ow2companion.viewmodels.QuickPlayPlayerDetailsInfoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuickPlayPlayerDetailsInfoFragment : Fragment() {

    private val binding: FragmentQuickplayPlayerDetailsInfoBinding
        get() = _binding!!
    private var _binding: FragmentQuickplayPlayerDetailsInfoBinding? = null

    private val viewModel: QuickPlayPlayerDetailsInfoViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuickplayPlayerDetailsInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInitialDataQuickPlay()
        observe()
    }

    //TODO запрос не проходит
    private fun setInitialDataQuickPlay(){
        viewModel.getViewPagerQuickPlayDetailsInfo("fullgenga-2713")

    }

    private fun observe(){
        observePlayerStatsSummaryQuickPlay()
    }

    private fun observePlayerStatsSummaryQuickPlay() {
        lifecycleScope.launchWhenStarted {
            viewModel.playerStatsSummaryQuickPlay.collect { setPlayerStatsSummaryQuickPlay(it) }
        }
    }

    private fun setPlayerStatsSummaryQuickPlay(item: PlayerStatsSummaryQuickPlay?) {

        if (item == null) return

        with(binding) {
            quantityGamesPlayed.text = item.general.games_played.toString()
            quantityTimePlayed.text = item.general.time_played.toString()
            quantityWinrate.text = item.general.winrate.toString()
            quantityKda.text = item.general.kda.toString()
            quantityEliminations.text = item.general.total.eliminations.toString()
            quantityAssists.text = item.general.total.assists.toString()
            quantityDeaths.text = item.general.total.deaths.toString()
            quantityDamage.text = item.general.total.damage.toString()
            quantityHealing.text = item.general.total.healing.toString()
        }

    }

}