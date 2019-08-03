package com.nixlord.dunzo

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.nixlord.dunzo.fragments.AddItemFragment
import com.nixlord.dunzo.fragments.CategoryFragment
import com.nixlord.dunzo.fragments.SellerFragment
import com.phoenixoverlord.pravega.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout.addTab(tabLayout.newTab().setText("Category"))
        tabLayout.addTab(tabLayout.newTab().setText("Store"))
        tabLayout.addTab(tabLayout.newTab().setText("Add Item"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        viewPager.adapter = TabLayoutAdapter(supportFragmentManager, tabLayout.tabCount)

        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    inner class TabLayoutAdapter(fm: FragmentManager,
                                 internal var totalTabs: Int) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
            when (position) {
                0 -> return CategoryFragment()
                1 -> return SellerFragment()
                2 -> return AddItemFragment()
                else -> return null
            }
        }

        // this counts total number of tabs
        override fun getCount(): Int {
            return totalTabs
        }

        override fun getPageTitle(position: Int): CharSequence {
            return when (position) {
                0 -> "Category"
                1 -> "Store"
                else -> {
                    return "Add Item"
                }
            }
        }

    }
}
