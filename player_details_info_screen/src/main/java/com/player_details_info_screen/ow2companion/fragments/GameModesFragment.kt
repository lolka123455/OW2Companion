package com.player_details_info_screen.ow2companion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.player_details_info_screen.ow2companion.R
import com.player_details_info_screen.ow2companion.adapters.ViewPagerAdapter
import com.player_details_info_screen.ow2companion.databinding.FragmentGameModesBinding

class GameModesFragment : Fragment() {

    private val binding: FragmentGameModesBinding
        get() = _binding!!
    private var _binding: FragmentGameModesBinding? = null

    private val fragmentsList: List<Fragment> = listOf(
        CompetitivePlayerDetailsInfoFragment(),
        QuickPlayPlayerDetailsInfoFragment()
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameModesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialViewPager()
    }

    private fun initialViewPager() {
        val titles = resources.getStringArray(R.array.tab_titles)
        val adapter = ViewPagerAdapter(requireActivity(), fragmentsList)
        binding.pager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.pager) { tabLayout, position ->
            tabLayout.text = titles[position]
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}