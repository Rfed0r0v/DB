package com.example.realmdatabase

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleObserver
import com.example.realmdatabase.databinding.ActivityMainBinding
import com.example.realmdatabase.ext.focusAndShowKeyboard
import com.example.realmdatabase.ext.hideKeyboard
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), LifecycleObserver {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ContactsAdapter()

        viewModel.allContacts.observe(this) {
            adapter.setData(it)
        }

        binding.rvContacts.adapter = adapter



        binding.fabAddContact.setOnClickListener {
//            startActivity(Intent(this, AddContactActivity::class.java))
            binding.etSecond.focusAndShowKeyboard()
        }

        binding.ivEye.setOnClickListener {
            with(binding) {
                if (etSoftInput.inputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                    ivEye.setImageResource(R.drawable.ic_baseline_remove_red_eye_24)
                    etSoftInput.inputType =
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                } else {
                    ivEye.setImageResource(R.drawable.ic_baseline_visibility_off_24)
                    etSoftInput.inputType =
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                }
                etSoftInput.setSelection(etSoftInput.length())
            }
        }
    }
}