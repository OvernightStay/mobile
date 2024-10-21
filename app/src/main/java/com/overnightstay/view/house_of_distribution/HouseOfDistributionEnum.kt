package com.overnightstay.view.house_of_distribution

import com.overnightstay.R

enum class HouseOfDistributionEnum(val stage1: Int, val stage2: Int, val txt: Pair<Int?, Int?>) {
    DISTRIBUTION_STAGE1_1(1, 1, Pair(R.string.text_distribution_stage1_1, null)),
    DISTRIBUTION_STAGE1_2(1, 2, Pair(R.string.text_distribution_stage1_2, null)),
    DISTRIBUTION_STAGE1_3(1, 3, Pair(R.string.text_distribution_stage1_3, null)),
    DISTRIBUTION_STAGE1_4(1, 4, Pair(R.string.text_distribution_stage1_4, null)),
    DISTRIBUTION_STAGE1_5(1, 5, Pair(R.string.text_distribution_stage1_5, null)),

    DISTRIBUTION_STAGE2_1(2, 1, Pair(R.string.text_distribution_stage2_1, null)),
    DISTRIBUTION_STAGE2_2(2, 2, Pair(R.string.text_distribution_stage2_2, null)),
    DISTRIBUTION_STAGE2_3(2, 3, Pair(R.string.text_distribution_stage2_3, null)),
    DISTRIBUTION_STAGE2_4(2, 4, Pair(R.string.text_distribution_stage2_4, null)),
    DISTRIBUTION_STAGE2_5(2, 5, Pair(R.string.text_distribution_stage2_5, null)),
    DISTRIBUTION_STAGE2_6(2, 6, Pair(R.string.text_distribution_stage2_6, null)),
    DISTRIBUTION_STAGE2_7(2, 7, Pair(R.string.text_distribution_stage2_7, null)),
    DISTRIBUTION_STAGE2_8(2, 8, Pair(R.string.text_distribution_stage2_8, null)),
    DISTRIBUTION_STAGE2_9(2, 9, Pair(R.string.text_distribution_stage2_9, null)),
    DISTRIBUTION_STAGE2_10(2, 10, Pair(R.string.text_distribution_stage2_10, null)),
    DISTRIBUTION_STAGE2_11(2, 11, Pair(R.string.text_distribution_stage2_11, null)),
    DISTRIBUTION_STAGE2_12(2, 12, Pair(R.string.text_distribution_stage2_12, null)),
    DISTRIBUTION_STAGE2_13(2, 13, Pair(R.string.text_distribution_stage2_13, null)),
    DISTRIBUTION_STAGE2_14(2, 14, Pair(R.string.text_distribution_stage2_14, null)),
    DISTRIBUTION_STAGE2_15(2, 15, Pair(R.string.text_distribution_stage2_15, null)),
    DISTRIBUTION_STAGE2_16(2, 16, Pair(R.string.text_distribution_stage2_16, null)),
    DISTRIBUTION_STAGE2_17(2, 17, Pair(R.string.text_distribution_stage2_17, null)),
    DISTRIBUTION_STAGE2_18(2, 18, Pair(R.string.text_distribution_stage2_18, null)),
    DISTRIBUTION_STAGE2_19(2, 19, Pair(R.string.text_distribution_stage2_19, null)),

    DISTRIBUTION_STAGE3_1(3, 1, Pair(R.string.text_distribution_stage3_1, null)),
    DISTRIBUTION_STAGE3_2(3, 2, Pair(R.string.text_distribution_stage3_2, null)),
    DISTRIBUTION_STAGE3_3(
        3,
        3,
        Pair(R.string.text_distribution_stage3_3_rb1, R.string.text_distribution_stage3_3_rb2)
    ),
    DISTRIBUTION_STAGE3_4(3, 4, Pair(R.string.text_distribution_stage3_4, null)),
    DISTRIBUTION_STAGE3_5(
        3,
        5,
        Pair(R.string.text_distribution_stage3_5_rb1, R.string.text_distribution_stage3_5_rb2)
    ),
    DISTRIBUTION_STAGE3_6(3, 6, Pair(R.string.text_distribution_stage3_6, null)),
    DISTRIBUTION_STAGE3_7(
        3,
        7,
        Pair(R.string.text_distribution_stage3_7_rb1, R.string.text_distribution_stage3_7_rb2)
    ),
    DISTRIBUTION_STAGE3_8(3, 8, Pair(R.string.text_distribution_stage3_8, null)),
    DISTRIBUTION_STAGE3_9(
        3,
        9,
        Pair(R.string.text_distribution_stage3_9_rb1, R.string.text_distribution_stage3_9_rb2)
    ),
    DISTRIBUTION_STAGE3_10(3, 10, Pair(R.string.text_distribution_stage3_10, null)),
    DISTRIBUTION_STAGE3_11(
        3,
        11,
        Pair(R.string.text_distribution_stage3_11_rb1, R.string.text_distribution_stage3_11_rb2)
    ),
    DISTRIBUTION_STAGE3_12(3, 12, Pair(R.string.text_distribution_stage3_12, null)),
    DISTRIBUTION_STAGE3_13(
        3,
        13,
        Pair(R.string.text_distribution_stage3_13_rb1, R.string.text_distribution_stage3_13_rb2)
    ),
    DISTRIBUTION_STAGE3_14(3, 14, Pair(R.string.text_distribution_stage3_14, null)),

    DISTRIBUTION_STAGE4_1(4, 1, Pair(R.string.text_distribution_stage4_1, null)),
    DISTRIBUTION_STAGE4_2(4, 2, Pair(R.string.text_distribution_stage4_2, null)),
    DISTRIBUTION_STAGE4_3(4, 3, Pair(R.string.text_distribution_stage4_3, null)),
    DISTRIBUTION_STAGE_FINISH(-1, -1, Pair(null, null));

    fun next(): HouseOfDistributionEnum? {
        val currentIndex = this.ordinal
        val count = HouseOfDistributionEnum.entries.count()

        return if (currentIndex < count - 1) {
            HouseOfDistributionEnum.entries[currentIndex + 1]
        } else null
    }

}
