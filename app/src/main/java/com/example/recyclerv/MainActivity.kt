package com.example.recyclerv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerv.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val binding:ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val todos = mutableListOf(
            "bangun","sikat gigi","makan","tidur","jalan","kaki","renangf","gembira"
        )
        binding.btnNew.setOnClickListener{
            todos.add(binding.newtxt.text.toString())
            viewAdapter.notifyDataSetChanged()
        }

        viewManager = LinearLayoutManager(this)
        viewAdapter = TodoAdapter(todos)

        binding.myRecyclerView.apply{
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = viewAdapter
        }
        binding.root
    }
}