package com.overnightstay.view.house_of_distribution.finishminigame

import com.overnightstay.R

enum class FinishGameMemoDistrEnum(val txt: Int) {
    DISTRIBUTION_FINISH_GAME_1(R.string.text_distribution_finish_game_5_1),
    DISTRIBUTION_FINISH_GAME_2(R.string.text_distribution_finish_game_5_2),
    DISTRIBUTION_FINISH_GAME_3(R.string.text_distribution_finish_game_5_3);


    fun next(): FinishGameMemoDistrEnum? {
        val currentIndex = this.ordinal
        val count = FinishGameMemoDistrEnum.entries.count()

        return if (currentIndex < count - 1) {
            FinishGameMemoDistrEnum.entries[currentIndex + 1]
        } else null
    }

}
