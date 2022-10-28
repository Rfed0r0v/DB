package com.example.realmdatabase

import io.realm.Realm
import io.realm.kotlin.deleteFromRealm
import java.util.*


class ContactRepositoryImpl(
    //Обязательное добавление Realm, с помощью которого мы будем делать запросы
    private val realm: Realm
) : ContactRepository {
    override fun addContact(name: String, surname: String, number: String) {
        // создаем объект с помощью REALM
        realm.executeTransaction {
            it.createObject(Contact::class.java, UUID.randomUUID().toString()).apply {
                this.name = name
                this.surname = surname
                this.number = number
            }
        }
    }

    override fun getContact(): List<Contact> {
        return realm.where(Contact::class.java).findAll()
    }

    override fun deleteContact(contact: Contact) {

        realm.executeTransaction {
            realm.where(Contact::class.java).equalTo("id", contact.id ).findFirst()
                ?.deleteFromRealm()
//            return  realm.where(Contact::class.java).findAll()
        }

    }


    override fun changeContact(name: String, surname: String, number: String, contact: Contact) {
        realm.executeTransaction {
            val dataObjectTransaction = realm.where(Contact::class.java).equalTo("id", contact.id ).findFirst()
            dataObjectTransaction?.name =name
            dataObjectTransaction?.surname =surname
            dataObjectTransaction?.number=number
        }

    }

}

//realm.query<Person>("dog == NULL LIMIT(1)")
//    .first()
//    .find()
//    ?.also { personWithoutDog ->
//        // Add a dog in a transaction
//        realm.writeBlocking {
//            findLatest(personWithoutDog)?.dog = Dog().apply { name = "Laika"; age = 3 }
//        }
//    }