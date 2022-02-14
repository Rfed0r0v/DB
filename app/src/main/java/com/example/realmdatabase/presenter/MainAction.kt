package com.example.realmdatabase.presenter

import com.example.realmdatabase.Contact

interface MainAction {

    fun onAddContact(contacts: List<Contact>)
}