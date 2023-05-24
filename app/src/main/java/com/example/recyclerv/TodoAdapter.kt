package com.example.recyclerv

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerv.databinding.ListItemBinding


class TodoAdapter(private val dataSet: MutableList<String>) :
    RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = ListItemBinding.inflate(inflater)


        return ViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.todo_text.text = dataSet[position]


        //menghapus
        viewHolder.del_btn.setOnClickListener {
            dataSet.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,dataSet.size)
        }

        //Mengedit data
        viewHolder.edit_btn.setOnClickListener{
            val context = viewHolder.itemView.context

            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.edit_item,null)


            //Mengambil data sebelumnya
            val prevText = dataSet[position]
            val editText = view.findViewById<TextView>(R.id.editText)
            editText.text = prevText

            //dialog
            var alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("Edit Item").setView(view).setPositiveButton("Update",
                DialogInterface.OnClickListener { dialog,id ->
                    //edit
                    dataSet[position]= editText.text.toString()
                    notifyDataSetChanged()
                })
                .setNegativeButton("Cancel",DialogInterface.OnClickListener{
                    dialog, which ->
                })
            alertDialog.create().show()
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
    class ViewHolder(binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val todo_text = binding.todoItem
        val del_btn = binding.btnDelete
        val edit_btn = binding.btnEdit


    }
}


