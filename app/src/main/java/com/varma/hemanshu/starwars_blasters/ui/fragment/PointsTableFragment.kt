package com.varma.hemanshu.starwars_blasters.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.varma.hemanshu.starwars_blasters.R
import com.varma.hemanshu.starwars_blasters.adapter.PointsTableAdapter
import com.varma.hemanshu.starwars_blasters.api.SWApi
import com.varma.hemanshu.starwars_blasters.databinding.FragmentPointsTableBinding
import com.varma.hemanshu.starwars_blasters.model.PlayerInfo
import com.varma.hemanshu.starwars_blasters.remote.SWApiService
import com.varma.hemanshu.starwars_blasters.repository.StarWarsRepo
import com.varma.hemanshu.starwars_blasters.repository.StarWarsRepoImpl
import com.varma.hemanshu.starwars_blasters.utils.UiState
import com.varma.hemanshu.starwars_blasters.viewmodel.StarWarsVMFactory
import com.varma.hemanshu.starwars_blasters.viewmodel.StarWarsViewModel

private const val TAG = "PointsTableFragment"

class PointsTableFragment : Fragment() {

    private lateinit var binding: FragmentPointsTableBinding
    private lateinit var viewModel: StarWarsViewModel
    private lateinit var ptsTableAdapter: PointsTableAdapter
    private val repository: StarWarsRepo by lazy { StarWarsRepoImpl(SWApi.retrofitService) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = FragmentPointsTableBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        //init viewmodel factory
        val vmFactory = StarWarsVMFactory(repository)
        viewModel = ViewModelProvider(this, vmFactory)[StarWarsViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPtsTable.layoutManager = LinearLayoutManager(context)
        setAdapter()
        setObservers()
    }

    private fun setObservers() {

        viewModel.playersList.observe(viewLifecycleOwner) { players ->
            players?.let { ptsTableAdapter.submitList(players) }
        }

        viewModel.playerInfoList.observe(viewLifecycleOwner) { playersInfoState ->
            Log.d(TAG, "Live Data Response: $playersInfoState")
            when (playersInfoState) {
                is UiState.Loading -> {
                }

                is UiState.Success -> {

                }

                is UiState.Error -> {

                }
            }

        }
    }

    private fun setAdapter() {
        ptsTableAdapter = PointsTableAdapter(::onPlayerItemClick)
        binding.rvPtsTable.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ptsTableAdapter
        }
    }

    private fun onPlayerItemClick(item: PlayerInfo) {
        viewModel.selectedMatchId.value = item.id.toString().toInt()
        Log.d(
            TAG,
            "Selected Player ID to navigate: $${
                item.id.toString().toInt()
            }"
        )
        findNavController().navigate(R.id.action_pointsTableFragment_to_matchDetailsFragment)
    }
}