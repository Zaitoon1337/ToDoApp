package com.example.todoapp.superlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.todoapp.ListHolder
import com.example.todoapp.EXTRA_LIST_INFO
import com.example.todoapp.superlist.data.List
import com.example.todoapp.databinding.ActivityListDetailsBinding

class ListDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListDetailsBinding
    private lateinit var list: List

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Metode med Intent
        //val receivedBook = intent.getParcelableExtra<Book>(EXTRA_BOOK_INFO)

        // Metode uten Intent
        val receivedList = ListHolder.PickedList

        if (receivedList != null){
            list =receivedList
            Log.i("Details view", receivedList.toString())
        }else{
            setResult(RESULT_CANCELED, Intent(EXTRA_LIST_INFO).apply {
                // Legg til info vi vil sende tilbake til Main
            })
            finish() // stenger aktiviteten
        }

        binding.toDo.text = list.title
    }
}