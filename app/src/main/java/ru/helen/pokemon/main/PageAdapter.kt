package ru.helen.pokemon.main

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import ru.helen.pokemon.pagefragment.PageFragment

/**
 * PageAdapter
 */
class PageAdapter(val fm: FragmentManager, var context: Context ): FragmentPagerAdapter(fm) {
    val PAGE_COUNT = 2
    private val tabTitles = arrayOf("Discover", "Pokedex")

    override fun getItem(position: Int): Fragment {
        return PageFragment.newInstance(position + 1)
    }

    override fun getCount(): Int {
        return PAGE_COUNT;
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }
}