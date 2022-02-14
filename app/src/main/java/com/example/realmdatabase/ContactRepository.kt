package com.example.realmdatabase

interface ContactRepository {

    fun addContact(name: String, surname: String, number: String)

    fun getContact(): List<Contact>
}