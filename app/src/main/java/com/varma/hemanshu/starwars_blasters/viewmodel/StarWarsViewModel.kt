package com.varma.hemanshu.starwars_blasters.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varma.hemanshu.starwars_blasters.model.MatchDetails
import com.varma.hemanshu.starwars_blasters.model.PlayerInfo
import com.varma.hemanshu.starwars_blasters.repository.StarWarsRepo
import com.varma.hemanshu.starwars_blasters.utils.UiState
import kotlinx.coroutines.launch

class StarWarsViewModel(
    private val repo: StarWarsRepo
) : ViewModel() {

    private val _playersList: MutableLiveData<List<PlayerInfo?>?> = MutableLiveData()
    val playersList: LiveData<List<PlayerInfo?>?> get() = _playersList

    var selectedMatchId: MutableLiveData<Int?> = MutableLiveData()

    private val _playersInfoList: MutableLiveData<UiState<List<PlayerInfo>>> = MutableLiveData()
    val playerInfoList: LiveData<UiState<List<PlayerInfo>>> get() = _playersInfoList

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

    fun getPlayersData() {
        viewModelScope.launch {
            _playersInfoList.value = UiState.Loading
            _playersInfoList.value = repo.getPlayerInfo()
        }
    }


    private fun findPlayerById(id: Int): PlayerInfo? {
        return _playersList.value?.find { it?.id == id }
    }

    fun fetchPlayerDetails(id: Int): List<MatchDetails?>? {
        return findPlayerById(id)?.listGamePlay
    }

}