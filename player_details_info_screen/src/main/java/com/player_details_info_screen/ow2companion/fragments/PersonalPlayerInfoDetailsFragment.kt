package com.player_details_info_screen.ow2companion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.player_details_info_screen.ow2companion.R
import com.player_details_info_screen.ow2companion.databinding.FragmentPersonalInfoDetailsBinding
import com.player_details_info_screen.ow2companion.network.models.playerBasicInfo.FoundPlayerBasicInfo
import com.player_details_info_screen.ow2companion.viewmodels.PersonalPlayerInfoDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PersonalPlayerInfoDetailsFragment : Fragment() {

    private val binding: FragmentPersonalInfoDetailsBinding
        get() = _binding!!
    private var _binding: FragmentPersonalInfoDetailsBinding? = null

    private val viewModel: PersonalPlayerInfoDetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonalInfoDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInitialData()
        observe()
    }

    private fun setInitialData() {
        arguments?.getString("player")?.let { viewModel.getTitleExactFoundPlayerBasicInfo(it) }
    }

    private fun observe() {
        observeTitleBasicInfoPlayers()
    }

    private fun observeTitleBasicInfoPlayers() {
        lifecycleScope.launchWhenCreated {
            viewModel.exactFoundPlayerBasicInfo.collect {
                setTitleBasicInfoPlayers(it)
            }
        }
    }

    private fun setTitleBasicInfoPlayers(item: FoundPlayerBasicInfo?) {

        fun loadGlideImage(url: Any?, imageView: ImageView) {
            Glide.with(binding.root)
                .load(url)
                .into(imageView)
        }

        if (item == null) return

        with(binding) {
            playerUsername.text = item.username
            playerTitle.text = item.title
        }

        loadGlideImage(item.avatar, binding.playerAvatar)
        loadGlideImage(item.competitive.pc.tank.rank_icon, binding.ivTankRank)
        loadGlideImage(item.competitive.pc.damage.rank_icon, binding.ivDamageRank)
        loadGlideImage(item.competitive.pc.support.rank_icon, binding.ivSupportRank)

        when (item.endorsement.level) {
            1 -> loadGlideImage(R.drawable.ic_1_lvl_rep, binding.ivLvlReputation)
            2 -> loadGlideImage(R.drawable.ic_2_lvl_rep, binding.ivLvlReputation)
            3 -> loadGlideImage(R.drawable.ic_3_lvl_rep, binding.ivLvlReputation)
            4 -> loadGlideImage(R.drawable.ic_4_lvl_rep, binding.ivLvlReputation)
            else -> binding.ivLvlReputation.visibility = View.GONE
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}