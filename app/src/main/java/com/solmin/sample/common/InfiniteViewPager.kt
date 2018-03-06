package com.solmin.sample.common

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet

/**
 * Created by h_sol on 2018/03/04.
 */
class InfiniteViewPager : ViewPager {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private var mInfinitePagerAdapter: InfinitePagerAdapter? = null

    private var offsetAmount: Int = 0

    override fun setAdapter(adapter: PagerAdapter) {
        super.setAdapter(adapter)
        mInfinitePagerAdapter = adapter as InfinitePagerAdapter
        currentItem = 1 // 初期表示位置
        offsetAmount = mInfinitePagerAdapter?.realCount!! * 100
    }

    override fun setCurrentItem(item: Int) {
        setCurrentItem(item, false)
    }

    override fun setCurrentItem(item: Int, smoothScroll: Boolean) {
        super.setCurrentItem(
                item % mInfinitePagerAdapter?.realCount!! + offsetAmount,
                smoothScroll
        )
    }

    override fun getCurrentItem(): Int {
        // Viewの表示などで利用されるので、値を誤魔化す
        return super.getCurrentItem() % mInfinitePagerAdapter?.realCount!!
    }
}