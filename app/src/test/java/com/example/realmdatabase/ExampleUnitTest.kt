package com.example.realmdatabase

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    val number = "+88000000001"
    val contact = FakeContact(
        name = "Name1",
        surname = "Surname1",
        phone = "+88000000002"
    )
    val contactRepository = FakeContactRepository()

    @Test
    fun testAddContact() {

        contactRepository.addContact(contact)
        val list = contactRepository.getAllContacts()
        val lastContact = list.last()

        assertEquals(contact, lastContact)
        assertNotEquals(number, lastContact.phone)
    }

    @Test
    fun testDeleteContact() {
        val contact1 = FakeContact(
            name = "Name1",
            surname = "Surname1",
            phone = "88005553535"
        )
        val contact2 = FakeContact(
            name = "Name2",
            surname = "Surname2",
            phone = "88005553536"
        )

        contactRepository.addContact(contact1)
        contactRepository.addContact(contact2)
        contactRepository.deleteContact(contact2)
        val list = contactRepository.getAllContacts()
        val lastContact = list.last()

        assertEquals(contact1, lastContact)
    }

    @Test
    fun testSearchContact() {
        val contactfirst = FakeContact(
            name = "111111",
            surname = "11111",
            phone = "11111111"
        )
        val contactSecond = FakeContact(
            name = "2222222",
            surname = "2222222",
            phone = "2222222"
        )
        val contactThird = FakeContact(
            name = "33333",
            surname = "33333",
            phone = "33333"
        )

        contactRepository.addContact(contactfirst)
        contactRepository.addContact(contactSecond)
        contactRepository.contactsShown("2")
        val list = contactRepository.contactsShown("2")
        assertEquals(1, list.size)
    }
}