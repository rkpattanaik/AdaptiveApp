package io.rajeshp.adaptiveapp.ui.main.foryou

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.rajeshp.adaptiveapp.data.MenuRepo

class ForYouViewModel : ViewModel() {
    private val _menuItems = MutableLiveData<List<String>>()
    val menuItems: LiveData<List<String>> = _menuItems

    init {
        _menuItems.value = MenuRepo.ForYou.menu()
    }
}