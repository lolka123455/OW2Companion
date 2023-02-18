package com.list_of_heroes.ow2companion.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.list_of_heroes.ow2companion.R
import com.list_of_heroes.ow2companion.adapter.ViewPagerAdapter
import com.list_of_heroes.ow2companion.databinding.FragmentListOfHeroesBinding

class ListOfHeroesFragment : Fragment() {

    private val binding: FragmentListOfHeroesBinding
        get() = _binding!!
    private var _binding: FragmentListOfHeroesBinding? = null

    private val fragmentsList = listOf(
        TanksFragment(),
        DamageFragment(),
        SupportFragment()
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListOfHeroesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialViewPager()
    }

    private fun initialViewPager() {
        val titles = resources.getStringArray(R.array.tab_titles)
        val adapter =ViewPagerAdapter(requireActivity(),fragmentsList)
        binding.pager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.pager) {
                tabLayout, position -> tabLayout.text = titles[position]
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}