package com.searchPlayer.ow2companion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.searchPlayer.ow2companion.databinding.FragmentSearchPlayerBinding

class SearchPlayerFragment : Fragment() {

    private val binding: FragmentSearchPlayerBinding
        get() = _binding!!
    private var _binding: FragmentSearchPlayerBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onSearchClicked()
    }

    private fun onSearchClicked() {
        binding.flSearch.setOnClickListener {
            findNavController().navigate(
                com.navigation.ow2companion
                    .R.id.action_searchPlayerFragment_to_searchPlayerDetailsFragment
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}