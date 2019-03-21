package io.github.mobileteacher.newsrevision.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import io.github.mobileteacher.newsrevision.fragments.NewsMetaDataFragment
import io.github.mobileteacher.newsrevision.fragments.NewsTextFragment

class AddNewsFragmentPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        if (position == 0)
            return NewsMetaDataFragment()
        return NewsTextFragment()
    }

    override fun getCount() = TOTAL_FRAGMENTS

    companion object {
        const val TOTAL_FRAGMENTS = 2
    }
}