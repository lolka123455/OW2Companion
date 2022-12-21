package com.list_of_heroes.ow2companion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.list_of_heroes.ow2companion.databinding.FragmentListOfHeroesBinding

class ListOfHeroesFragment : Fragment() {

    private lateinit var binding: FragmentListOfHeroesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListOfHeroesBinding.inflate(inflater, container, false)
        return binding.root
    }
}