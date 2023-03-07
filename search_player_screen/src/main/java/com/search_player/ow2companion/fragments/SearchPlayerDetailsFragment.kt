package com.search_player.ow2companion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.search_player.ow2companion.databinding.FragmentSearchPlayerDetailsBinding

class SearchPlayerDetailsFragment : Fragment() {

    private val binding: FragmentSearchPlayerDetailsBinding
        get() = _binding!!
    private var _binding: FragmentSearchPlayerDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchPlayerDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBackPressedToSearchPlayer()
    }

    private fun onBackPressedToSearchPlayer(){
        binding.ibBack.setOnClickListener{
            findNavController().navigateUp()
        }
    }

}