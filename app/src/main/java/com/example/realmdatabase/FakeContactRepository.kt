package com.example.realmdatabase

class FakeContactRepository {

    private val contactList = mutableListOf<FakeContact>()

    fun addContact(contact: FakeContact) {
        contactList.add(contact)
    }

    fun getAllContacts(): MutableList<FakeContact> = contactList

    fun deleteContact(contact: FakeContact) {
        contactList.remove(contact)
    }
    fun contactsShown(text: String): List<FakeContact> {
        val filterContacts = contactList.filter {
            it.name.contains(text) || it.surname.contains(text)|| it.phone.contains(text) }
        return filterContacts
    }

}