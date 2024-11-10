package com.varma.hemanshu.starwars_blasters.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.varma.hemanshu.starwars_blasters.adapter.PlayerMatchDetailAdapter
import com.varma.hemanshu.starwars_blasters.databinding.FragmentMatchDetailsBinding
import com.varma.hemanshu.starwars_blasters.ui.viewmodel.StarWarsViewModel

private const val TAG = "MatchDetailsFragment"

class MatchDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMatchDetailsBinding
    private val viewModel: StarWarsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMatchDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMatchDetails.layoutManager = LinearLayoutManager(context)
        setupAdapter()
    }

    private fun setupAdapter() {
        val details = viewModel.selectedMatchId.value?.let { viewModel.fetchPlayerDetails(it) }
        binding.rvMatchDetails.adapter =
            viewModel.selectedMatchId.value?.let { PlayerMatchDetailAdapter(details, it) }
    }
}