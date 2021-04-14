package com.example.todoapp

import ListCollectionAdapter
import ListDepositoryManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.superlist.ListDetailsActivity
import com.example.todoapp.superlist.data.List
import com.example.todoapp.databinding.ActivityMainBinding
import com.google.firebase.ktx.Firebase

const val EXTRA_LIST_INFO: String = "com.example.todoapp.book.info"
const val REQUEST_LIST_DETAILS: Int = 719

// Metode uten intents
class ListHolder{
    companion object {
        var PickedList: List? = null
    }
}


//var auth = //Firebase
//val ref = //FirebaseDatabase

//val auth = Firebase.auth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val TAG:String = "todoapp.MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//         auth = Firebase.auth

        binding.toDoListing.layoutManager = LinearLayoutManager(this)
        binding.toDoListing.adapter = ListCollectionAdapter(emptyList<List>(), this::onListClicked)

        ListDepositoryManager.instance.onList = {
            (binding.toDoListing.adapter as ListCollectionAdapter).updateCollection(it)
        }

        ListDepositoryManager.instance.load()


        // På et senere tidspunkt kan vi oppdatere llisten med bøker.
        // bookAdapter.updateCollection((ListOf(Book)))

        binding.saveBt.setOnClickListener {


            val title = binding.toDo.text.toString()

            binding.toDo.setText("")

            addList(title)

            val ipm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            ipm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

        }
    }

    private fun addList(title: String) {
        val list = List(title)
        ListDepositoryManager.instance.addList(list)
    }


    private fun onListClicked(list: List):Unit{
        // Detalje side for bok

        // Metode med intents

        /*val intent = Intent(this, BookDetailsActivity::class.java).apply {
            putExtra(EXTRA_BOOK_INFO, book)
            }*/

        // Metode uten intents

        ListHolder.PickedList = list
        val intent = Intent(this, ListDetailsActivity::class.java)

        startActivity(intent)

        // Hvordan motta ting tilbake gjennom intents
        //startActivityForResult(intent, 719)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_LIST_DETAILS){

        }
    }
    private fun signInAnonymously() {
        auth.signInAnonymously().addOnSuccessListener {
            Log.d(TAG, "Login successful ${it.user.toString()}")
        }.addOnFailureListener {
            Log.e(TAG, "Login failed", it)
        }
    }
}