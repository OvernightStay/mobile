package com.overnightstay.view

import android.animation.ObjectAnimator
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.bumptech.glide.Glide
import com.overnightstay.R
import com.overnightstay.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private var progressStatus = 0
    private val maxTime = 9000L
    private var handler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val catGif = binding.cat
        Glide.with(this).load(R.drawable.img_cat_gif).into(catGif)

        val anim = ObjectAnimator.ofFloat(binding.cat, View.TRANSLATION_X, 800F)
        anim.duration = maxTime
        anim.start()

        val vectorAnimHouse = AnimatedVectorDrawableCompat.create(this, R.drawable.anim_house_splash)
        binding.house.setImageDrawable(vectorAnimHouse)
        vectorAnimHouse?.start()

        val vectorAnimSmoke = AnimatedVectorDrawableCompat.create(this, R.drawable.anim_smoke_splash)
        binding.smoke.setImageDrawable(vectorAnimSmoke)
        vectorAnimSmoke?.start()

        lifecycleScope.launch(Dispatchers.Main) {
            while (progressStatus < 100){
                progressStatus +=1
                delay(maxTime / 115)
                handler.post {
                    binding.progress.progress = progressStatus
                }
            }
            startActivity(Intent(this@SplashScreen, AuthActivity::class.java))
            finish()
        }.start()
    }
}