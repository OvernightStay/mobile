package com.overnightstay.view

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
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

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private var progressStatus = 0
    private val maxTime = 10000L
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val logo = binding.logo
        val parentLayout = binding.splash

        val catGif = binding.cat
        Glide.with(this).load(R.drawable.img_cat_gif).into(catGif)

        parentLayout.post {
            val logoTranslate = -parentLayout.height / 2F + 300
            val widthProgress = binding.downloadBar.width - binding.cat.width

            val animationLogo = ObjectAnimator.ofFloat(logo, View.TRANSLATION_Y, logoTranslate)
            animationLogo.duration = 1000

            val animationCat = ObjectAnimator.ofFloat(binding.cat, View.TRANSLATION_X, widthProgress.toFloat())
            animationCat.duration = maxTime

            val vectorAnimHouse = AnimatedVectorDrawableCompat.create(this, R.drawable.anim_house_splash)
            binding.house.setImageDrawable(vectorAnimHouse)

            val vectorAnimSmoke = AnimatedVectorDrawableCompat.create(this, R.drawable.anim_smoke_splash)
            binding.smoke.setImageDrawable(vectorAnimSmoke)

            val animSmoke = AnimatorInflater.loadAnimator(this, R.animator.animation_smoke)
            animSmoke.setTarget(binding.smoke)

            animationLogo.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}
                override fun onAnimationEnd(animation: Animator) {
                    logo.setImageResource(R.drawable.img_overnightstay_whiteyellow)
                    with(binding) {
                        house.visibility = View.VISIBLE
                        grassLeft.visibility = View.VISIBLE
                        smoke.visibility = View.VISIBLE
                        tree.visibility = View.VISIBLE
                        grassRight.visibility = View.VISIBLE
                        bush.visibility = View.VISIBLE
                        cat.visibility = View.VISIBLE
                        downloadBar.visibility = View.VISIBLE
                        progress.visibility = View.VISIBLE
                        animationCat.start()
                        vectorAnimHouse?.start()
                        vectorAnimSmoke?.start()
                        animSmoke.start()

                        lifecycleScope.launch(Dispatchers.Main) {
                            while (progressStatus < 100){
                                progressStatus +=1
                                delay(maxTime / 110)
                                handler.post {
                                    binding.progress.progress = progressStatus
                                }
                            }
                            startActivity(Intent(this@SplashScreen, AuthActivity::class.java))
                            finish()
                        }.start()
                    }
                }
                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
            })
            animationLogo.start()
        }
    }
}