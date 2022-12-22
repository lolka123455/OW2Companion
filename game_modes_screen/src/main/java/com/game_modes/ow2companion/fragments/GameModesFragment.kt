package com.game_modes.ow2companion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.game_modes.ow2companion.databinding.FragmentGameModesBinding

class GameModesFragment : Fragment() {

    private lateinit var binding: FragmentGameModesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameModesBinding.inflate(inflater, container, false)
        return binding.root
    }
}