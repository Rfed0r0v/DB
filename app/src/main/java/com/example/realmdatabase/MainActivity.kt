package com.example.realmdatabase

import android.content.Intent
import android.graphics.Insets.add
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.core.graphics.Insets.add
import androidx.core.view.OneShotPreDrawListener.add
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.*
import com.example.realmdatabase.databinding.ActivityMainBinding
import com.example.realmdatabase.ui.AddContactActivity
import com.example.realmdatabase.ContactsAdapter
import com.example.realmdatabase.EditContactActivity
import com.example.realmdatabase.ui.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), LifecycleObserver {
    // новое использование адаптера
    private lateinit var binding: ActivityMainBinding

    private val defaultLifecycleObserver = object : DefaultLifecycleObserver {
        override fun onCreate(owner: LifecycleOwner) {
            super.onCreate(owner)
            Log.d("Main", "DefaultLifecycleObserver - onCreate")
        }

        override fun onStart(owner: LifecycleOwner) {
            super.onStart(owner)
            Log.d("Main", "DefaultLifecycleObserver - onStart")
        }

        override fun onResume(owner: LifecycleOwner) {
            super.onResume(owner)
            Log.d("Main", "DefaultLifecycleObserver - onResume")
        }
    }

    //    private val viewModel : MainViewModel by viewModel()
    //Привязка ко вью модели с помощью KTX
    private val viewModel by viewModel<MainViewModel> ()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(defaultLifecycleObserver)

        // новое использование адаптера
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ContactsAdapter ({contactToEditIndex ->
            editContact(contactToEditIndex)})

        viewModel.allContacts.observe(this) {
            adapter.setData(it)
        }

        binding.rvContacts.adapter = adapter

        binding.fabAddContact.setOnClickListener {
            startActivity(Intent(this, AddContactActivity::class.java))
        }
//      Использование библиотеки KTX
        binding.etSearch.doAfterTextChanged { text ->  viewModel.contactsShown(text.toString())  }

    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(defaultLifecycleObserver)
    }

    private fun editContact (contactToEditIndex: Int) {
        val id = viewModel.getContactId(contactToEditIndex)
        val intent = Intent(this, EditContactActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }



}