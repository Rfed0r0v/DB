package com.example.realmdatabase.ui

import android.util.Log
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.realmdatabase.ContactLiveData
import com.example.realmdatabase.ContactRepository
import com.example.realmdatabase.Contact

class MainViewModel(private val contactRepository: ContactRepository) : ViewModel() {

    val allContacts: ContactLiveData
        get() = contactsShown(text = "") as ContactLiveData


    fun addContact(name: String, surname: String, number: String) {

        contactRepository.addContact(name, surname, number)
    }

    private fun getAllContacts(): MutableLiveData<List<Contact>> {
        val list = ContactLiveData()
        val allContacts = contactRepository.getContact()
        list.value = allContacts.subList(0, allContacts.size)
        return list
    }


    fun deleteContact(id: String) {
        val contact = getContactWithId(id)
        if (contact != null) {
            contactRepository.deleteContact(contact)
        }
    }

    fun changeContact (name: String, surname:String, number: String, id: String) {
        val contact = getContactWithId(id)
        if (contact != null) {
            contactRepository.changeContact(name, surname, number, contact)
        }
    }

    fun getContactId (index:Int): String {
        val allContact = contactRepository.getContact()
        val contact = allContact[index]
        return contact.id
    }

    fun getContactWithId (id:String): Contact? {
        val contact = contactRepository.getContact().find {it.id == id}
        return contact
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("MainViewModel", "MainViewModel -> onCleared")
    }

    fun contactsShown(text: String): MutableLiveData<List<Contact>> {
        val list = ContactLiveData()
        val filterContacts = contactRepository.getContact().filter {
            it.name.contains(text) || it.surname.contains(text)|| it.number.contains(text) }
        list.value = filterContacts.subList(0, filterContacts.size)
        return list
    }

}