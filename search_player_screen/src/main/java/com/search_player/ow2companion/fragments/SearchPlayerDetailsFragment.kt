package com.search_player.ow2companion.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    private lateinit var adapter: SearchPlayerDetailsAdapter

    private var similarPlayersList = listOf<SearchPlayer>()

    private val viewModel: SearchPlayerDetailsViewModel by viewModel()

    // Store the last searched query
    private var lastSearchQuery: String? = null

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
        setupRecyclerView()
        watchSearchText()
    }

    private fun setupRecyclerView() {
        adapter = SearchPlayerDetailsAdapter(this)
        binding.rvFoundedPlayer.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFoundedPlayer.adapter = adapter

        observeSimilarPlayersFounded()
    }

    private fun setInitialData(name: String) {
        val modifiedName = changeMinusToHash(name)
        viewModel.getSimilarPlayersFounded(modifiedName)
    }

    /**
     * Replaces all occurrences of '#' with '-' in the given [input] string.
     *
     * @param input The string to modify.
     * @return A new string with all '#' replaced by '-'.
     */
    private fun changeMinusToHash(input: String): String {
        return input.replace("#", "-")
    }

    private fun watchSearchText() {
        binding.etSearchNamePlayer.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchText = s?.toString()?.trim()
                if (searchText.isNullOrEmpty()) {
                    // Clear the list when the search text is empty
                    adapter.allHeroesList = emptyList()
                } else {
                    handleNonEmptySearchText(searchText)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun handleNonEmptySearchText(searchText: String) {
        if (searchText != lastSearchQuery) {
            // Fetch new data only if the search query has changed
            fetchNewData(searchText)
        } else {
            // If the search query is the same as the last one, display the previously fetched data
            displayPreviouslyFetchedData()
        }
    }

    private fun fetchNewData(searchText: String) {
        setInitialData(searchText)
        lastSearchQuery = searchText
    }

    private fun displayPreviouslyFetchedData() {
        adapter.allHeroesList = similarPlayersList
    }

    private fun observeSimilarPlayersFounded() {
        lifecycleScope.launchWhenCreated {
            viewModel.similarPlayersFounded.collect {
                adapter.allHeroesList = it
            }
        }
    }

    // Тут мы берем то что вписал пользователь применяем changeMinusToHash и прокидываем
    override fun onClickedPlayer(playerName: String) {
        val bundle = Bundle()
        val inputText = binding.etSearchNamePlayer.text.toString().trim()
        val modifiedName = changeMinusToHash(inputText)
        bundle.putString("player", modifiedName)
        navigateToPersonalPlayerInfoDetailsFragment(bundle)
    }

    private fun navigateToPersonalPlayerInfoDetailsFragment(bundle: Bundle) {
        findNavController().navigate(
            com.navigation.ow2companion
                .R.id.action_searchPlayerDetailsFragment_to_personalPlayerInfoDetailsFragment,
            bundle
        )
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