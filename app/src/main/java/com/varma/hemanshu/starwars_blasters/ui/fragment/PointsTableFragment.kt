package com.varma.hemanshu.starwars_blasters.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.varma.hemanshu.starwars_blasters.R
import com.varma.hemanshu.starwars_blasters.adapter.PointsTableAdapter
import com.varma.hemanshu.starwars_blasters.databinding.FragmentPointsTableBinding
import com.varma.hemanshu.starwars_blasters.ui.viewmodel.StarWarsViewModel

private const val TAG = "PointsTableFragment"

class PointsTableFragment : Fragment() {

    private lateinit var binding: FragmentPointsTableBinding
    private val viewModel: StarWarsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = FragmentPointsTableBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPtsTable.layoutManager = LinearLayoutManager(context)
        setObservers()
        viewModel.getPlayersData()
    }

    private fun setObservers() {

        viewModel.playersList.observe(viewLifecycleOwner) { response ->
            response?.let {
                binding.rvPtsTable.adapter = PointsTableAdapter(it) { clickedItemInfo ->

                    viewModel.selectedMatchId.value = clickedItemInfo?.tag.toString().toInt()
                    Log.d(
                        TAG,
                        "Selected Player ID to navigate: $${
                            clickedItemInfo?.tag.toString().toInt()
                        }"
                    )
                    findNavController().navigate(R.id.action_pointsTableFragment_to_matchDetailsFragment)
                }
            }
        }
    }

}