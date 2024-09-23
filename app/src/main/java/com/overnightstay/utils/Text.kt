package com.overnightstay.utils

import android.animation.ValueAnimator
import android.widget.TextView

fun TextView.animateCharacterByCharacter2(text: String, delay: Long = 30L, animator: ValueAnimator) {
    if (text.isEmpty()) return

    animator.removeAllUpdateListeners()

    animator.setIntValues(0, text.length)
//        val charAnimation = ValueAnimator.ofInt(0, text.length)

    animator.apply {
        this.duration = delay * text.length.toLong()
        this.repeatCount = 0
        addUpdateListener {
            val charCount = it.animatedValue as Int
            val animatedText = text.substring(0, charCount)
            this@animateCharacterByCharacter2.text = animatedText
        }
    }
    animator.start()
}