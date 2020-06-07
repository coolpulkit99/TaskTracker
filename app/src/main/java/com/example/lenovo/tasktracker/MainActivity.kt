package com.example.lenovo.tasktracker

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextUtils
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
//    private lateinit var linearLayoutManager: LinearLayoutManager
//    lateinit var recyclerView:RecyclerView;

    val tasks = ArrayList<String>()
    val completed = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        linearLayoutManager = LinearLayoutManager(this)
//        recyclerView.layoutManager = linearLayoutManager
//    val arrayAdapter: ArrayAdapter<*>
//    val users = arrayOf(
//            "Virat Kohli", "Rohit Sharma", "Steve Smith",
//            "Kane Williamson", "Ross Taylor"
//    )
//
//    // access the listView from xml file
//    var mListView = findViewById<ListView>(R.id.listView)
//    arrayAdapter = ArrayAdapter(this,
//            android.R.layout.item, users)
//    mListView.adapter = arrayAdapter

        val sharedPref = this?.getPreferences(Context.MODE_PRIVATE) ?: return
        val defaultValue = resources.getString(R.string.data_default)
        val highScore = sharedPref.getString(getString(R.string.data_key), defaultValue)
        fillTasks(tasks,highScore);
//    tasks.add("Demo task(Tap to delete)");
        val myListAdapter = MyListAdapter(this,tasks)
    listView.adapter = myListAdapter
//taskAdd.setOnClickListener()

    listView.setOnItemClickListener(){adapterView, view, position, id ->
//        Toast.makeText(this, "Click on item at ", Toast.LENGTH_LONG).show()
        val itemAtPos = adapterView.getItemAtPosition(position)
        val itemIdAtPos = adapterView.getItemIdAtPosition(position)
//        Toast.makeText(this, "Click on item at $itemAtPos its item id $itemIdAtPos", Toast.LENGTH_LONG).show()
        tasks.removeAt(itemIdAtPos.toInt())
updateDataSaved(tasks)

        val myListAdapter = MyListAdapter(this,tasks)
        listView.adapter = myListAdapter
    }



}

    fun updateDataSaved(tasks:ArrayList<String>){
        var datatoupdate=TextUtils.join(",",tasks);
        if(datatoupdate.trim().compareTo("")==0)
            datatoupdate=null
        val sharedPref = this?.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(getString(R.string.data_key), datatoupdate)
            commit()
        }
    }
     fun refreshList(x: Int):Unit{
//        tasks.removeAt(x);
//        val myListAdapter = MyListAdapter(this,tasks)
//        listView.adapter = myListAdapter

    }
    fun fillTasks(tasks:ArrayList<String>, taskStr:String){
        if(taskStr!=null)
        tasks.addAll(ArrayList<String>(taskStr.split(",")))
    }

    fun addTaskToList(x: View): Unit {
        if(taskType.text.toString()!=null && taskType.text.toString().compareTo("")!=0)
        {
            tasks.add(taskType.text.toString())
            updateDataSaved(tasks)
            taskType.text=null
            val adapter=listView.adapter as MyListAdapter
            adapter.notifyDataSetChanged()
        }
        else
            Toast.makeText(this,"Please type in a task",Toast.LENGTH_LONG).show();
//        val myListAdapter = MyListAdapter(this,tasks)
//        listView.adapter = myListAdapter

    }

}

