package com.flknlabs.mvvmexample.Login.View

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flknlabs.mvvmexample.Login.Model.Blog
import com.flknlabs.mvvmexample.Login.ViewModel.MainViewModel
import com.flknlabs.mvvmexample.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var viewManager = LinearLayoutManager(this)
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        button.setOnClickListener {
            addData()
        }

        initialiseAdapter()
    }
    private fun initialiseAdapter(){
        recycler.layoutManager = viewManager
        observeData()
    }

    private fun observeData(){
        viewModel.lst.observe(this) {
            Log.i("data", it.toString())
            recycler.adapter = NoteRecyclerAdapter(viewModel, it, this)
        }
    }

    private fun addData(){
        val title = titletxt.text.toString()
        if(title.isBlank()){
            Toast.makeText(this,"Enter value!",Toast.LENGTH_LONG).show()
        }else{
            val blog = Blog(title)
            viewModel.add(blog)
            titletxt.text.clear()
            recycler.adapter?.notifyDataSetChanged()
        }

    }
}