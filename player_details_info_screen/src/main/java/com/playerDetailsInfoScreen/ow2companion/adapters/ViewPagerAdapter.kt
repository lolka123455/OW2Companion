package com.playerDetailsInfoScreen.ow2companion.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    fragment: FragmentActivity,
    private val list: List<Fragment>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount() = list.size

    override fun createFragment(position: Int) = list[position]
}