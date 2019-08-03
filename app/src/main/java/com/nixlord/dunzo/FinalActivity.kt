package com.nixlord.dunzo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.Tasks
import com.nixlord.dunzo.model.Product
import com.nixlord.dunzo.model.Seller
import com.phoenixoverlord.pravega.extensions.Firebase
import com.phoenixoverlord.pravega.extensions.Firebase.firestore
import com.phoenixoverlord.pravega.extensions.logDebug
import kotlinx.android.synthetic.main.activity_final.*
import kotlinx.android.synthetic.main.fragment_recyclerview.*
import java.util.ArrayList

class FinalActivity : AppCompatActivity() {

    private var ProductKey = "ProductKey"
    private var product = Product()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)

        val productId = intent.getStringExtra(ProductKey)

        Firebase.firestore.collection("product")
            .document(productId!!)
            .get()
            .addOnSuccessListener {productSnapshot ->
                product = productSnapshot.toObject(Product::class.java)!!

                productName.text = product.name
                productCost.text = product.price
                productType.text = product.type

                /*val storesPromises = product.stores.map{ firestore.document("stores/$it").get() }
                Tasks.whenAll(storesPromises)
                    .addOnSuccessListener {
                        val stores = storesPromises.mapNotNull { it.result }
                            .mapNotNull { it.toObject(Seller::class.java) }

                    }*/
            }
    }
}