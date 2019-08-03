package com.nixlord.dunzo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.phoenixoverlord.pravega.extensions.Firebase
import com.google.firebase.firestore.Query
import com.nixlord.dunzo.model.Product
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.phoenixoverlord.pravega.extensions.logDebug
import kotlinx.android.synthetic.main.activity_final.view.*
import kotlinx.android.synthetic.main.fragment_recyclerview.*
import kotlinx.android.synthetic.main.product_item.*

class ProductActivity : AppCompatActivity() {

    private var PARAMETER_ONE = "Parameter1"
    private var CALLING_FRAGMENT = "CallingFragment"
    lateinit var query : Query

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_recyclerview)

        val paramOne = intent.getStringExtra(PARAMETER_ONE)
        val paramTwo = intent.getStringExtra(CALLING_FRAGMENT)

        logDebug(paramOne)
        logDebug(paramTwo)

        when(paramTwo){
            "TypeFragment" ->
                    query = Firebase.firestore
                        .collection("product")
                        .whereEqualTo("type", paramOne)

            "SellerFragment" ->
                    query = Firebase.firestore
                        .collection("product")
                        .whereArrayContains("stores", paramOne!!)
        }

        setupFirestoreRecyclerView(query)
    }

    private fun setupFirestoreRecyclerView (query: Query) {
        val firestoreOptions = FirestoreRecyclerOptions.Builder<Product>()
            .setLifecycleOwner(this)
            .setQuery(query, Product::class.java)
            .build()

        val firestoreRecyclerAdapter = object : FirestoreRecyclerAdapter<Product, ProductHolder>(firestoreOptions) {
            override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ProductHolder {
                return ProductHolder(
                    LayoutInflater.from(p0.context).inflate(R.layout.product_item, p0, false)
                )
            }
            override fun onBindViewHolder(holder: ProductHolder, position: Int, model: Product) {
                holder.bindItems(model)
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = firestoreRecyclerAdapter
    }

    inner class ProductHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(product: Product) {
            itemView.productName.text = product.name
            itemView.productCost.text = product.price
            itemView.setOnClickListener {
                logDebug(product.id)
                val intent = Intent(baseContext, FinalActivity::class.java)
                intent.putExtra("ProductKey", product.id)
                startActivity(intent)
            }
        }
    }



}