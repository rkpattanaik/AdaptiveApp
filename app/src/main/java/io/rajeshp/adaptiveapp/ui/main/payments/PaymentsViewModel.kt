package io.rajeshp.adaptiveapp.ui.main.payments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.rajeshp.adaptiveapp.data.MenuRepo

class PaymentsViewModel : ViewModel() {
    private val _menuItems = MutableLiveData<List<String>>()
    val menuItems: LiveData<List<String>> = _menuItems

    private val _quickAccessItems = MutableLiveData<List<String>>()
    val quickAccessItems: LiveData<List<String>> = _quickAccessItems

    init {
        _menuItems.value = MenuRepo.Payments.menu()
        _quickAccessItems.value = MenuRepo.Payments.quickAccess()
    }
}