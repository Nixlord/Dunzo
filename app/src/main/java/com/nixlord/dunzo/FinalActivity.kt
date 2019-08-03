package com.nixlord.dunzo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Tasks
import com.nixlord.dunzo.model.Product
import com.phoenixoverlord.pravega.extensions.Firebase
import com.phoenixoverlord.pravega.extensions.Firebase.firestore
import kotlinx.android.synthetic.main.activity_final.*

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

                val storesPromises = product.stores.map{ firestore.document("stores/$it").get() }

            }
    }
}