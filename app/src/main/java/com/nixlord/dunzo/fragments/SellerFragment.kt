package com.nixlord.dunzo.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.phoenixoverlord.pravega.extensions.Firebase
import com.google.firebase.firestore.Query
import com.nixlord.dunzo.ProductActivity
import com.nixlord.dunzo.R
import com.nixlord.dunzo.model.Seller
import kotlinx.android.synthetic.main.fragment_recyclerview.*
import kotlinx.android.synthetic.main.seller_item.view.*

class SellerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFirestoreRecyclerView(Firebase.firestore.collection("seller"))
    }

    private fun setupFirestoreRecyclerView (query: Query) {
        val firestoreOptions = FirestoreRecyclerOptions.Builder<Seller>()
            .setLifecycleOwner(this)
            .setQuery(query, Seller::class.java)
            .build()

        val firestoreRecyclerAdapter = object : FirestoreRecyclerAdapter<Seller, SellerHolder>(firestoreOptions) {
            override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SellerHolder {
                return SellerHolder(
                    LayoutInflater.from(p0.context).inflate(R.layout.seller_item, p0, false)
                )
            }
            override fun onBindViewHolder(holder: SellerHolder, position: Int, model: Seller) {
                holder.bindItems(model)
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = firestoreRecyclerAdapter
    }

    inner class SellerHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(seller : Seller) {
            itemView.storeName.text = seller.name
            itemView.storeAddress.text = seller.address
            itemView.storePhoneNumber.text = seller.phoneNo

            itemView.setOnClickListener{
                (activity).let{
                    val intent = Intent (it, ProductActivity::class.java)
                    intent.putExtra("Parameter1", seller.id)
                    intent.putExtra("CallingFragment", "SellerFragment")
                    it!!.startActivity(intent)
                }
            }
        }
    }

}