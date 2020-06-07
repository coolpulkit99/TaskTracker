package com.example.lenovo.tasktracker

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.*

class MyListAdapter(private val context: Activity, private val title: ArrayList<String>
//                    ,private val completed: ArrayList<Int>
)
    : ArrayAdapter<String>(context, R.layout.item, title) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.item, null, true)
//        if(position in completed)
//            rowView.setBackgroundColor(Color.GREEN)
        val titleText = rowView.findViewById(R.id.title) as TextView
        val imageView = rowView.findViewById(R.id.icon) as ImageView

        titleText.text = title.get(position)
//
//        imageView.setOnClickListener(){view->
//            var parent:LinearLayout = view.parent as LinearLayout
//            parent.setBackgroundColor( Color.GREEN)
////            completed.add()
//            Toast.makeText(getContext(), "Clicked on button ", Toast.LENGTH_LONG).show()
//
//        }

        return rowView
    }
}