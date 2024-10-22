package com.overnightstay.domain.models

import com.overnightstay.R

enum class Stress(val idImg: Int, val pos: Int) {
    GREEN(R.drawable.img_stress, 0),
    YELLOW(R.drawable.img_stress_yellow, 1),
    ORANGE(R.drawable.img_stress_orange, 2),
    RED(R.drawable.img_stress_red, 3);

    fun getNextStress(): Stress? {
        return if (pos < entries.size - 1) entries[pos + 1] else
            if (pos == 3) entries[pos] else null
    }
}