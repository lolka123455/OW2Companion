package com.search_player.ow2companion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.search_player.ow2companion.adapters.SearchPlayerDetailsAdapter
import com.search_player.ow2companion.databinding.FragmentSearchPlayerDetailsBinding
import com.search_player.ow2companion.network.models.SearchPlayer
import com.search_player.ow2companion.viewmodels.SearchPlayerDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchPlayerDetailsFragment : Fragment(), SearchPlayerDetailsAdapter.PlayerItemListener {

    private val binding: FragmentSearchPlayerDetailsBinding
        get() = _binding!!
    private var _binding: FragmentSearchPlayerDetailsBinding? = null

    private lateinit var adapter : SearchPlayerDetailsAdapter

    private var listAllSimilarPlayersFounded = listOf<SearchPlayer>()

    private val viewModel: SearchPlayerDetailsViewModel by viewModel()

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
        setInitialData()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = SearchPlayerDetailsAdapter(this)
        binding.rvFoundedPlayer.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFoundedPlayer.adapter

        observeSimilarPlayersFounded()
    }

    // TODO не сделано что после паузы в печатанье текста отправлялся запрос потому поиск пока что не работает

    private fun setInitialData() {
        binding.etSearchNamePlayer.text?.toString()
            ?.let { viewModel.getSimilarPlayersFounded(it) }
    }

    private fun observeSimilarPlayersFounded(){
        lifecycleScope.launchWhenCreated {
            viewModel.similarPlayersFounded.collect {
                listAllSimilarPlayersFounded = it
                adapter.allHeroesList = listAllSimilarPlayersFounded
            }
        }
    }

    override fun onClickedHero(playerName: String) {
        Toast.makeText(context,"Переход", Toast.LENGTH_SHORT).show()
    }

    private fun onBackPressedToSearchPlayer() {
        binding.ibBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}