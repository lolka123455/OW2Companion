package com.search_player.ow2companion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.search_player.ow2companion.databinding.FragmentSearchPlayerBinding

class SearchPlayerFragment : Fragment() {

    private lateinit var binding: FragmentSearchPlayerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }
}