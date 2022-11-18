package com.flknlabs.mvvmexample.Login.View

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.flknlabs.mvvmexample.Login.Model.Blog
import com.flknlabs.mvvmexample.Login.ViewModel.MainViewModel
import com.flknlabs.mvvmexample.R

class NoteRecyclerAdapter(
    val viewModel: MainViewModel,
    val arrayList: ArrayList<Blog>,
    private val context: Context
): RecyclerView.Adapter<NoteRecyclerAdapter.NotesViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): NoteRecyclerAdapter.NotesViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return NotesViewHolder(root)
    }

    override fun onBindViewHolder(holder: NoteRecyclerAdapter.NotesViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount(): Int {
        if(arrayList.size==0){
            Toast.makeText(context,"List is empty",Toast.LENGTH_LONG).show()
        }
        return arrayList.size
    }

    inner class NotesViewHolder(private val parent: View) : RecyclerView.ViewHolder(parent) {
        fun bind(blog: Blog){
            parent.findViewById<TextView>(R.id.title).text = blog.title
            parent.findViewById<ImageButton>(R.id.delete).setOnClickListener {
                viewModel.remove(blog)
                notifyItemRemoved(arrayList.indexOf(blog))
            }
        }
    }
}