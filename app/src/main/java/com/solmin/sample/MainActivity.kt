package com.solmin.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Toolbarの設定
        val toolbar: Toolbar = findViewById(R.id.toolBar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        supportFragmentManager.beginTransaction()
                .replace(R.id.mainFrameLayout, MainFragment.newInstance(), MainFragment::class.simpleName)
                .commit()

        // Camera Btnの設定
        val cameraBtn: View = findViewById<View>(R.id.cameraBtn)
        cameraBtn.setOnClickListener({
            YoYo.with(Techniques.RubberBand)
                    .duration(600)
                    .playOn(it)
        })
    }
}
