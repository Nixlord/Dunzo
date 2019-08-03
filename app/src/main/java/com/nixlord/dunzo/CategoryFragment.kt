package com.nixlord.dunzo

import android.app.DownloadManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class CategoryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFirestoreRecyclerView(categoryCollection)
    }

    private fun setupFirestoreRecyclerView (query: DownloadManager.Query) {
        val firestoreOptions = FirestoreRecyclerOptions.Builder<Category>()
            .setLifecycleOwner(this)
            .setQuery(query, Category::class.java)
            .build()

        val firestoreRecyclerAdapter = object : FirestoreRecyclerAdapter<Category, CategoryHolder>(firestoreOptions) {
            override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CategoryHolder {
                return CategoryHolder(
                    LayoutInflater.from(p0.context).inflate(R.layout.category_item_view, p0, false)
                )
            }
            override fun onBindViewHolder(holder: CategoryHolder, position: Int, model: Category) {
                holder.bindItems(model)
            }
        }
        productCategoryRecyclerView.layoutManager = GridLayoutManager(context, 2, 1, false)
        productCategoryRecyclerView.adapter = firestoreRecyclerAdapter
    }

    inner class CategoryHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(category : Category) {
            itemView.apply {
                category_name.text = category.name

                Glide.with(base)
                    .load(storage.child("category/${category.image}"))
                    .into(itemView.category_image)

                arrayOf(category_image, category_name).forEach {
                    it.setOnClickListener {
                        replaceFragment(
                            R.id.fragment_container,
                            SubCategoryFragment.newInstance(category.ID)
                        )
                    }
                }
            }
        }
    }

}