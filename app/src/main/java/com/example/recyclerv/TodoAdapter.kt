package com.example.recyclerv

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class TodoAdapter(private val dataSet: MutableList<String>) :
    RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val todo_text = itemView.findViewById<TextView>(R.id.todo_item)
        val del_btn = itemView.findViewById<TextView>(R.id.btnDelete)
        val edit_btn = itemView.findViewById<TextView>(R.id.btnEdit)


    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item, viewGroup, false)

        return ViewHolder(view)
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

}
