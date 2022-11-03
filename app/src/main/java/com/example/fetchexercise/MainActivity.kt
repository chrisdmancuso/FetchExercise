package com.example.fetchexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.fetchexercise.API.ItemApi
import com.example.fetchexercise.Helpers.RetrofitHelper
import com.example.fetchexercise.Helpers.ItemAdapter
import com.example.fetchexercise.Models.ItemModel
import com.example.fetchexercise.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    //Various variable declarations
    private lateinit var binding: ActivityMainBinding
    private lateinit var result: List<ItemModel>

    companion object {
        val TAG = "MainActivity"
        var itemArrayList: MutableList<ItemModel> = ArrayList()
        val scope = MainScope()
        val itemApi = RetrofitHelper.getInstance().create(ItemApi::class.java)
    }

    //Function to handle reload and rebinding of listView
    private fun bindData(result: List<ItemModel>, binding: ActivityMainBinding) {
        itemArrayList.clear()
        for (i in result.indices) {
            val item = ItemModel(result[i].id, result[i].listId, result[i].name)
            itemArrayList.add(item)
        }
        binding.listview.adapter = ItemAdapter(this@MainActivity, itemArrayList)
    }

    //Function to handle data retrieval from JSON. Initial sort and binding happens here
    private fun retrieveData(itemApi: ItemApi) = scope.launch {
            result = sortList(itemApi.getItems(), "ALL")
            bindData(result, binding)
//            Log.d(TAG, "Data Binding Complete")
        }

    //Helper function to sortList
    private fun convertToInt(string: String): Int {
        val int = string.replace("Item ", "")
        return int.toInt()
    }

    //Sort function. Accepts list to sort, and string to filter by. Removes null or blank values
    private fun sortList(list: List<ItemModel>, filterBy: String): List<ItemModel> {
        var list = list.filter { !it.name.isNullOrBlank() }
        if (filterBy != "ALL") {
            list = list.filter { it.listId == filterBy }
        }
        return list.sortedWith(compareBy<ItemModel> { it.listId }
            .thenBy { convertToInt(it.name) })
    }

    //onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initial API call here
        retrieveData(itemApi)

        //Radio Group Declared
        val rg: RadioGroup = findViewById(R.id.rgroup)

        //Radio Group Listener. Reloads and rebinds listView based on selection
        rg.setOnCheckedChangeListener { group, checkedId ->
            val rBtn : RadioButton = findViewById(checkedId)
            when(rBtn.id) {
                R.id.rbtn_1 -> {
                    var filteredResult = sortList(result, "ALL")
                    bindData(filteredResult, binding)
                }
                R.id.rbtn_2 -> {
                    var filteredResult = sortList(result, "1")
                    bindData(filteredResult, binding)
                }
                R.id.rbtn_3 -> {
                    var filteredResult = sortList(result, "2")
                    bindData(filteredResult, binding)
                }
                R.id.rbtn_4 -> {
                    var filteredResult = sortList(result, "3")
                    bindData(filteredResult, binding)
                }
                R.id.rbtn_5 -> {
                    var filteredResult = sortList(result, "4")
                    bindData(filteredResult, binding)
                }
            }
        }
    }
}