package com.example.demosyno

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demosyno.adapter.RecyclerViewAdapter
import com.example.demosyno.adapter.SwipeDeleteCallback
import com.example.demosyno.model.CountryItem
import com.example.demosyno.viewmodel.CountryViewModel
import com.example.demosyno.viewmodel.CountryViewModelFactory

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnItemClickListener {

    private lateinit var countryViewModel: CountryViewModel
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var recyclerview :RecyclerView
    private lateinit var textview:TextView
    private var checkSort:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerview = findViewById<RecyclerView>(R.id.recycler)
        textview = findViewById<TextView>(R.id.network)
        initViewModel()
        initMainViewModel()
    }
    private fun initViewModel(){

        recyclerview.layoutManager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(applicationContext,DividerItemDecoration.VERTICAL)
        recyclerview.addItemDecoration(decoration)
        recyclerViewAdapter = RecyclerViewAdapter(this)
        val adapter = recyclerViewAdapter
        recyclerview.adapter = adapter
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun initMainViewModel(){
        val repository = (application as MyApplication).countriesRepository
        countryViewModel = ViewModelProvider(this,CountryViewModelFactory(repository)).get(CountryViewModel::class.java)
        countryViewModel.makeCall()
        countryViewModel.getAllRecord().observe(this, Observer <MutableList<CountryItem>> {
            if(it.isEmpty()){
                textview.visibility = View.VISIBLE
            }else{
                textview.visibility = View.INVISIBLE
            }
            recyclerViewAdapter.setListData(it)
            recyclerViewAdapter.notifyDataSetChanged()
        })


        val swipeDeleteCallback = object :SwipeDeleteCallback(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position =viewHolder.adapterPosition
                val tempList: MutableList<CountryItem>? =recyclerViewAdapter.getListData()
                tempList?.removeAt(position)
                recyclerViewAdapter.setListData(tempList)
                recyclerview.adapter?.notifyItemRemoved(position)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerview)
    }

    override fun onItemClick(position: Int, name: String) {
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.sort -> {
                Toast.makeText(this,"Sort Selected",Toast.LENGTH_SHORT).show()
                sortView()
            }
            R.id.profile -> {
                Toast.makeText(this,"Profile Selected",Toast.LENGTH_SHORT).show()
                val intent = Intent(this,ProfileActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun sortView() {
        val tempList: MutableList<CountryItem>? =recyclerViewAdapter.getListData()
        if(tempList?.size != 0 && checkSort == 0){
            checkSort=1
            tempList?.sortByDescending {
                it.country
            }
            recyclerViewAdapter.setListData(tempList)
            recyclerViewAdapter.notifyDataSetChanged()
        }
        else if (tempList?.size != 0 && checkSort == 1){
            checkSort = 0
            tempList?.sortBy {
                it.country
            }
            recyclerViewAdapter.setListData(tempList)
            recyclerViewAdapter.notifyDataSetChanged()
        }
    }
}