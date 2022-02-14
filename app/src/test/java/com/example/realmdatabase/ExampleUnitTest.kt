package com.example.realmdatabase

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun testViewModel() {
        val contactRepository = ContactRepositoryImpl()

        val phone = "+88005553535"

        val contact = FakeContact(
            name = "Stas",
            surname = "Qmar",
            phone = "88005553535"
        )

        contactRepository.addContact(contact)
        val list = contactRepository.getAllContact()
        val lastContact = list.last()

        assertEquals(contact, lastContact)
        assertNotEquals(phone, lastContact.phone)
    }
}