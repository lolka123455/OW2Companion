package com.player_details_info_screen.ow2companion.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.player_details_info_screen.ow2companion.R
import com.player_details_info_screen.ow2companion.adapters.ViewPagerAdapter
import com.player_details_info_screen.ow2companion.databinding.FragmentPersonalInfoDetailsBinding
import com.player_details_info_screen.ow2companion.network.models.playerBasicInfo.FoundPlayerBasicInfo
import com.player_details_info_screen.ow2companion.viewmodels.PersonalPlayerInfoDetailsViewModel
import com.player_details_info_screen.ow2companion.viewmodels.QuickPlayPlayerDetailsInfoViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PersonalPlayerInfoDetailsFragment : Fragment() {

    private val binding: FragmentPersonalInfoDetailsBinding
        get() = _binding!!
    private var _binding: FragmentPersonalInfoDetailsBinding? = null

    private val viewModelTitleInfo: PersonalPlayerInfoDetailsViewModel by viewModel()

    private val viewModelQuickPlay: QuickPlayPlayerDetailsInfoViewModel by viewModel()


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
        initialViewPager()
    }

    // TODO время персов прилетает в секундах

    fun setInitialData() {
        val player = arguments?.getString("player") ?: throw IllegalArgumentException("Missing player argument")
        viewModelTitleInfo.getTitleExactFoundPlayerBasicInfo(player)
    }


    private val fragmentsList: List<Fragment> = listOf(
        QuickPlayPlayerDetailsInfoFragment(),
        CompetitivePlayerDetailsInfoFragment()
    )

    private fun initialViewPager() {
        val titles = resources.getStringArray(R.array.tab_titles)
        val adapter = ViewPagerAdapter(requireActivity(), fragmentsList)
        binding.pager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.pager) { tabLayout, position ->
            tabLayout.text = titles[position]
        }.attach()

        // Set data in the fragments
        fragmentsList.forEach { fragment ->
            val bundle = Bundle()
            bundle.putString("player", arguments?.getString("player"))
            fragment.arguments = bundle
        }
    }

    private fun observe() {
        observeTitleBasicInfoPlayers()
    }

    private fun observeTitleBasicInfoPlayers() {
        lifecycleScope.launchWhenCreated {
            viewModelTitleInfo.exactFoundPlayerBasicInfo.collect {
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