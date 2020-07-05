package com.codingwithjks.bottomsheet.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codingwithjks.bottomsheet.Adapter.FoodAdapter
import com.codingwithjks.bottomsheet.Listener.Listener
import com.codingwithjks.bottomsheet.Model.Food
import com.codingwithjks.bottomsheet.Network.Network
import com.codingwithjks.bottomsheet.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.bottom_sheet.*
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(),Listener {
    private lateinit var recyclerView:RecyclerView
    private lateinit var foodAdapter: FoodAdapter
    private lateinit var bottomSheet:LinearLayout
    private lateinit var sheetBehaviour: BottomSheetBehavior<View>
    private lateinit var foodList: ArrayList<Food>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView=findViewById(R.id.recyclerview)
        bottomSheet=findViewById(R.id.bottom_sheet)
        sheetBehaviour=BottomSheetBehavior.from(bottomSheet)
        sheetBehaviour.state=BottomSheetBehavior.STATE_HIDDEN
        getDataFromDatabase()
    }

    private fun getDataFromDatabase() {
        val call=Network().getApi.getAllFood()
        call.enqueue(object :Callback<List<Food>>{
            override fun onResponse(call: Call<List<Food>>, response: Response<List<Food>>) {
               if(response.isSuccessful)
               {
                  foodAdapter= FoodAdapter(this@MainActivity, response.body() as ArrayList<Food>,this@MainActivity)
                   recyclerView.apply {
                      setHasFixedSize(true)
                       layoutManager=GridLayoutManager(this@MainActivity,2)
                       adapter=foodAdapter
                       foodList= response.body() as ArrayList<Food>
                   }
               }
            }
            override fun onFailure(call: Call<List<Food>>, t: Throwable) {
                Toast.makeText(applicationContext,"Something went wrong",Toast.LENGTH_SHORT).show()
            }



        })
    }

    override fun onClickListener(position: Int) {
        var food=foodList[position]
       if(sheetBehaviour.state != BottomSheetBehavior.STATE_EXPANDED){
           sheetBehaviour.state=BottomSheetBehavior.STATE_EXPANDED
           var name:TextView=findViewById(R.id.name1)
           var image:ImageView=findViewById(R.id.image1)
           var price:TextView=findViewById(R.id.price1)
           var order: Button=findViewById(R.id.payment)
           order.setOnClickListener {
               val snackbar= Snackbar.make(it,"Your order has been placed",Snackbar.LENGTH_LONG)
               snackbar.setAction("Undo") {
                   snackbar.dismiss()
               }
               snackbar.show()
               sheetBehaviour.state=BottomSheetBehavior.STATE_HIDDEN
           }
           name.text=food.name
           price.text="₹${food.price}"
           Glide.with(applicationContext)
               .load(food.image)
               .into(image)
       }else{

           sheetBehaviour.state=BottomSheetBehavior.STATE_HIDDEN
       }
    }
}