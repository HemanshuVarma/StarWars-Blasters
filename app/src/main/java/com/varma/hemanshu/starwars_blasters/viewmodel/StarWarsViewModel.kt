package com.varma.hemanshu.starwars_blasters.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varma.hemanshu.starwars_blasters.model.MatchDetails
import com.varma.hemanshu.starwars_blasters.model.PlayerInfo
import com.varma.hemanshu.starwars_blasters.repository.StarWarsRepo
import com.varma.hemanshu.starwars_blasters.utils.UiState
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

const val TAG = "StarWarsViewModel"

class StarWarsViewModel(
    private val repo: StarWarsRepo
) : ViewModel() {

    private val _playersList: MutableLiveData<List<PlayerInfo?>?> = MutableLiveData()
    val playersList: LiveData<List<PlayerInfo?>?> get() = _playersList

    var selectedMatchId: MutableLiveData<Int?> = MutableLiveData()

    private val _playersInfoList: MutableLiveData<UiState<List<PlayerInfo>>> = MutableLiveData()
    val playerInfoList: LiveData<UiState<List<PlayerInfo>>> get() = _playersInfoList

    private val _matchDetails: MutableLiveData<UiState<List<MatchDetails>>> = MutableLiveData()
    val matchDetails: LiveData<UiState<List<MatchDetails>>> get() = _matchDetails

    init {
        getData()
    }
//    fun getPlayersData() {
//        viewModelScope.launch {
////            SWApi.retrofitService.getMatchInfo()
//            val sortedPlayers =
//                repo.getPlayersList(context.applicationContext)?.data?.sortedWith(
//                    compareByDescending<PlayerInfo?> { it?.totalPlay }.thenBy { it?.name })
//
//            _playersList.postValue(sortedPlayers)
//        }
//    }

    private fun findPlayerById(id: Int): PlayerInfo? {
        return _playersList.value?.find { it?.id == id }
    }

    fun fetchPlayerDetails(id: Int): List<MatchDetails?>? {
        return findPlayerById(id)?.listGamePlay
    }

    private fun getData() {
        viewModelScope.launch {
            _playersInfoList.value = UiState.Loading
            _matchDetails.value = UiState.Loading

            val playersDeferred = async { repo.getPlayerInfo() }
            val matchesDeferred = async { repo.getMatchDetailsInfo() }

            val players = playersDeferred.await()
            val matchList = matchesDeferred.await()

            playersMatchList(players, matchList)

            _playersInfoList.value = players
            _matchDetails.value = matchList

        }
    }

    private fun playersMatchList(
        playersList: UiState<List<PlayerInfo>>,
        matchList: UiState<List<MatchDetails>>
    ) {
        Log.d(TAG, "Players List: $playersList")
        Log.d(TAG, "Match List: $matchList")

        if (playersList is UiState.Success && matchList is UiState.Success) {
            val playersInfoList = playersList.data
            val matchDetailsList = matchList.data
        }
    }

}