package com.example.characterviewer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.characterViewer.core.model.CharacterModel
import com.example.characterViewer.core.searchService.SearchService
import com.example.characterViewer.core.util.success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(private val searchService: SearchService) : ViewModel() {

    val result
        get() = searchService.getSearchFlow()

    private var _currentCharacter: MutableLiveData<CharacterModel> =
        MutableLiveData()

    val currentCharacter: LiveData<CharacterModel>
        get() = _currentCharacter

    private var _characterList: MutableLiveData<List<CharacterModel>> =
        MutableLiveData()

    val characterList: LiveData<List<CharacterModel>>
        get() = _characterList

    fun load() {
        viewModelScope.launch {
            searchService.search(BuildConfig.API_SEARCH_KEY)
        }
    }

    fun updateCurrentCharacter(currentCharacter: CharacterModel) {
        _currentCharacter.value = currentCharacter
    }

    fun onFilterSearch(filter: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            result.value?.success { characters ->
                if (filter.isNullOrEmpty()) {
                    _characterList.postValue(characters)
                } else {
                    val f = filter.trim().lowercase()

                    _characterList.postValue(characters.filter { x ->
                        x.subTitle.lowercase().contains(f) || x.title.lowercase().contains(f)
                    })
                }
            }
        }
    }

    fun onNewCharacterList(characters: List<CharacterModel>) {
        _characterList.postValue(characters)
    }

    init {
        load()
    }
}