package com.solmin.sample


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerTabStrip
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.solmin.sample.common.InfinitePagerAdapter


/**
 * MainFragment.
 */
class MainFragment : Fragment() {
    private lateinit var mViewPager: ViewPager

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_main, container, false)

        val tabStrip: PagerTabStrip = v.findViewById(R.id.pagerTabStrip)
        mViewPager = v.findViewById(R.id.mainViewPager)

        val pageTitle = arrayOf(
                resources.getString(R.string.page_title_animals),
                resources.getString(R.string.page_title_nature),
                resources.getString(R.string.page_title_food)
        )

        // adapterの作成
        val adapter: FragmentPagerAdapter = object : InfinitePagerAdapter(fragmentManager, pageTitle.size) {
            override fun getItem(position: Int): Fragment {
                return when (convertToDummyPosition(position)) {
                    0 -> ContentsFragment.newInstance(ContentsFragment.Type.ANIMALS)
                    1 -> ContentsFragment.newInstance(ContentsFragment.Type.NATURE)
                    2 -> ContentsFragment.newInstance(ContentsFragment.Type.FOOD)
                    else -> Fragment() // for Dummy
                }
            }
            override fun getPageTitle(position: Int): CharSequence = pageTitle[convertToDummyPosition(position)]
        }

        mViewPager.adapter = adapter

        // tabStripの設定
        tabStrip.tabIndicatorColor = resources.getColor(R.color.tabStripColor)

        return v
    }

    companion object {
        fun newInstance(): MainFragment = MainFragment()
    }

}