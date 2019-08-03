package com.nixlord.dunzo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query
import com.nixlord.dunzo.R
import com.nixlord.dunzo.model.Type
import com.nixlord.dunzo.model.Seller
import com.phoenixoverlord.pravega.extensions.Firebase
import com.phoenixoverlord.pravega.extensions.logDebug
import kotlinx.android.synthetic.main.type_item.*
import kotlinx.android.synthetic.main.fragment_recyclerview.*
import kotlinx.android.synthetic.main.type_item.catItem
import kotlinx.android.synthetic.main.type_item.view.*

class TypeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFirestoreRecyclerView(Firebase.firestore.collection("categories"))
    }

    private fun setupFirestoreRecyclerView (query: Query) {
        val firestoreOptions = FirestoreRecyclerOptions.Builder<Type>()
            .setLifecycleOwner(this)
            .setQuery(query, Type::class.java)
            .build()

        val firestoreRecyclerAdapter = object : FirestoreRecyclerAdapter<Type, TypeHolder>(firestoreOptions) {
            override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TypeHolder {
                return TypeHolder(
                    LayoutInflater.from(p0.context).inflate(R.layout.type_item, p0, false)
                )
            }
            override fun onBindViewHolder(holder: TypeHolder, position: Int, model: Type) {
                holder.bindItems(model)
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = firestoreRecyclerAdapter
    }

    inner class TypeHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(category : Type) {
            itemView.catItem.text = category.name
//            itemView.apply {
//                catItem.text = category.name
//            }

            itemView.setOnClickListener {
                logDebug("Clicked")
            }
        }
    }

}