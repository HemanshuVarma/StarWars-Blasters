package com.varma.hemanshu.starwars_blasters.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.varma.hemanshu.starwars_blasters.ui.model.MatchDetails
import com.varma.hemanshu.starwars_blasters.ui.model.PlayerInfo
import com.varma.hemanshu.starwars_blasters.ui.repository.StarWarsRepoImpl
import kotlinx.coroutines.launch

class StarWarsViewModel(
    private val context: Application
) : AndroidViewModel(context) {

    private val _playersList: MutableLiveData<List<PlayerInfo?>?> = MutableLiveData()
    val playersList: LiveData<List<PlayerInfo?>?> get() = _playersList

    var selectedMatchId: MutableLiveData<Int?> = MutableLiveData()
    private val repo: StarWarsRepoImpl = StarWarsRepoImpl()

    fun getPlayersData() {
        viewModelScope.launch {
//            SWApi.retrofitService.getMatchInfo()
            viewModelScope.launch {
                val sortedPlayers =
                    repo.getPlayersList(context.applicationContext)?.data?.sortedWith(
                        compareByDescending<PlayerInfo?> { it?.totalPlay }.thenBy { it?.name })

                _playersList.postValue(sortedPlayers)
            }
        }
    }


    private fun findPlayerById(id: Int): PlayerInfo? {
        return _playersList.value?.find { it?.id == id }
    }

    fun fetchPlayerDetails(id: Int): List<MatchDetails?>? {
        return findPlayerById(id)?.listGamePlay
    }

}