package com.player_details_info_screen.ow2companion.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.player_details_info_screen.ow2companion.R

class ListOfPLayerDetailsInfoGameModesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_list_of_player_details_info_game_modes,
            container,
            false
        )
    }


}