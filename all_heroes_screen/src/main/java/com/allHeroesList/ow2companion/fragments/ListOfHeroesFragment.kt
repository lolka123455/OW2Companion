package com.allHeroesList.ow2companion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.allHeroesList.ow2companion.R
import com.allHeroesList.ow2companion.adapter.ViewPagerAdapter
import com.allHeroesList.ow2companion.databinding.FragmentListOfHeroesBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ListOfHeroesFragment : Fragment() {

    private val binding: FragmentListOfHeroesBinding
        get() = _binding!!
    private var _binding: FragmentListOfHeroesBinding? = null

    private val fragmentsList: List<Fragment> = listOf(
        TanksFragment(),
        DamageFragment(),
        SupportFragment()
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
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
        val titles = resources.getStringArray(R.array.tab_titles_heroes)

        binding.pager.adapter = ViewPagerAdapter(requireActivity(), fragmentsList)

        setupTabLayout(binding.tabLayout, binding.pager, titles)
    }

    private fun setupTabLayout(tabLayout: TabLayout, viewPager: ViewPager2, titles: Array<String>) {
        val tabLayoutMediator = TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = titles[position]
        }
        tabLayoutMediator.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}