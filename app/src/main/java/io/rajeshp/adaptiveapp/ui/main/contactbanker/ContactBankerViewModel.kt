package io.rajeshp.adaptiveapp.ui.main.contactbanker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactBankerViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Contact Banker Fragment"
    }
    val text: LiveData<String> = _text
}