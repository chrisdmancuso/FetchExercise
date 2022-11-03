package com.example.fetchexercise.Helpers

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.fetchexercise.Models.ItemModel
import com.example.fetchexercise.R

//Adapter Class to handle creating instances of listView items and populate with retrieved data
class ItemAdapter(private val context: Activity, private val itemList: List<ItemModel>)
    : ArrayAdapter<ItemModel>(context, R.layout.snippet_list_item, itemList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.snippet_list_item, null)

        val tvListId : TextView = view.findViewById(R.id.tv_list_id)
        val tvName : TextView = view.findViewById(R.id.tv_name)

        tvListId.text = "ListId: ${itemList[position].listId}"
        tvName.text = "Name: ${itemList[position].name}"

        return view
    }
}