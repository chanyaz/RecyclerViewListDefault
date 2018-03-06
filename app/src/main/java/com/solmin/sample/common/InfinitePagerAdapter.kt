package com.solmin.sample.common

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by h_sol on 2018/03/04.
 */
abstract class InfinitePagerAdapter(fm: FragmentManager?, val realCount: Int) : FragmentPagerAdapter(fm) {
    abstract override fun getItem(position: Int): Fragment

    override fun getCount(): Int = 1000 // 最大スクロールできる回数

    /**
     * 実際のPositionをダミーのPositionに変更する。
     */
    protected fun convertToDummyPosition(realPosition: Int): Int {
        return realPosition % realCount
    }
}