package com.example.recyclerv

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerv.databinding.ListItemBinding


class TodoAdapter(private val viewModel: TodoViewModel) :
   ListAdapter<Todo, TodoAdapter.ViewHolder>(TodoDiffCallback()) {

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

        // Get element from your viewModel.todos.value!! at this position and replace the
        // contents of the view with that element
        viewHolder.todo_text.text = getItem(viewHolder.adapterPosition).task


        //menghapus
        viewHolder.del_btn.setOnClickListener {
            viewModel.removeTodo(viewHolder.adapterPosition)
//            notifyItemRemoved(position)
//            notifyItemRangeChanged(position,viewModel.todos.value!!.size)
        }

        //Mengedit data
        viewHolder.edit_btn.setOnClickListener{
            val context = viewHolder.itemView.context

            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.edit_item,null)


            //Mengambil data sebelumnya
            val prevText = getItem(viewHolder.adapterPosition).task
            val editText = view.findViewById<TextView>(R.id.editText)
            editText.text = prevText

            //dialog
            var alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("Edit Item").setView(view).setPositiveButton("Update",
                DialogInterface.OnClickListener { dialog, id ->
                    //edit
                    val editedText = editText.text.toString()
//                    viewModel.todos.value?.get(position)?.task = editedText
                    viewModel.updateTodo(viewHolder.adapterPosition, editedText)
                    viewHolder.todo_text.text = editedText
//                    notifyDataSetChanged()
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })

            alertDialog.create().show()
        }
    }

    // Return the size of your viewModel.todos.value!! (invoked by the layout manager)
//    override fun getItemCount() = viewModel.todos.value!!.size
    class ViewHolder(binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val todo_text = binding.todoItem
        val del_btn = binding.btnDelete
        val edit_btn = binding.btnEdit


    }
}
class TodoDiffCallback : DiffUtil.ItemCallback<Todo>(){
    override fun areItemsTheSame(p0: Todo, p1: Todo): Boolean {
       return p0.id == p1.id
    }

    override fun areContentsTheSame(p0: Todo, p1: Todo): Boolean {
       return p0.equals(p1)
    }
}


