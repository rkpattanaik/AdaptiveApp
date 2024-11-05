package io.rajeshp.adaptiveapp.ui.main.more

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.rajeshp.adaptiveapp.data.MenuRepo

class MoreViewModel : ViewModel() {
    private val _menuItems = MutableLiveData<List<String>>()
    val menuItems: LiveData<List<String>> = _menuItems

    private val _quickAccessItems = MutableLiveData<List<String>>()
    val quickAccessItems: LiveData<List<String>> = _quickAccessItems

    init {
        _menuItems.value = MenuRepo.More.menu()
        _quickAccessItems.value = MenuRepo.More.quickAccess()
    }
}