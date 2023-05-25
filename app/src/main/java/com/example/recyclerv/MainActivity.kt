package com.example.recyclerv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerv.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
//    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewAdapter: TodoAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewModel: TodoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val binding:ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(TodoViewModel::class.java)


        viewManager = LinearLayoutManager(this)
        viewAdapter = TodoAdapter(viewModel)

        binding.myRecyclerView.apply{
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = viewAdapter
        }
        binding.btnNew.setOnClickListener{
            viewModel.addTodo(binding.newtxt.text.toString())
//            viewAdapter.notifyDataSetChanged()
        }
        viewModel.todos.observe(this, Observer {
            list -> viewAdapter.submitList(list.toMutableList())
        })
        binding.root
    }
}