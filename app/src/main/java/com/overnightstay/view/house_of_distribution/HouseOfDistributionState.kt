package com.overnightstay.view.house_of_distribution

sealed class HouseOfDistributionState {
    data class Stage1_1<out T>(val data: T) : HouseOfDistributionState()
    data class Stage1_2<out T>(val data: T) : HouseOfDistributionState()
    data class Stage1_3<out T>(val data: T) : HouseOfDistributionState()
    data class Stage1_4<out T>(val data: T) : HouseOfDistributionState()
    data class Stage1_5<out T>(val data: T) : HouseOfDistributionState()
    data class Stage2_1<out T>(val data: T) : HouseOfDistributionState()
    data class Stage2_2<out T>(val data: T) : HouseOfDistributionState()
    data class Stage2_3<out T>(val data: T) : HouseOfDistributionState()
    data class Stage2_4<out T>(val data: T) : HouseOfDistributionState()
    data class Stage2_5<out T>(val data: T) : HouseOfDistributionState()
    data class Stage2_6<out T>(val data: T) : HouseOfDistributionState()
    data class Stage2_7<out T>(val data: T) : HouseOfDistributionState()
    data class Stage2_8<out T>(val data: T) : HouseOfDistributionState()
    data class Stage2_9<out T>(val data: T) : HouseOfDistributionState()
    data class Stage2_10<out T>(val data: T) : HouseOfDistributionState()
    data class Stage2_11<out T>(val data: T) : HouseOfDistributionState()
    data class Stage2_12<out T>(val data: T) : HouseOfDistributionState()
    data class Stage2_13<out T>(val data: T) : HouseOfDistributionState()
    data class Stage2_14<out T>(val data: T) : HouseOfDistributionState()
    data class Stage2_15<out T>(val data: T) : HouseOfDistributionState()
    data class Stage2_16<out T>(val data: T) : HouseOfDistributionState()
    data class Stage2_17<out T>(val data: T) : HouseOfDistributionState()
    data class Stage2_18<out T>(val data: T) : HouseOfDistributionState()
    data class Stage2_19<out T>(val data: T) : HouseOfDistributionState()
    data class Stage3_1<out T>(val data: T) : HouseOfDistributionState()
    data class Stage3_2<out T>(val data: T) : HouseOfDistributionState()
    data class Stage3_3<out T>(val data: T) : HouseOfDistributionState()
    data class Stage3_4<out T>(val data: T) : HouseOfDistributionState()
    data class Stage3_5<out T>(val data: T) : HouseOfDistributionState()
    data class Stage3_6<out T>(val data: T) : HouseOfDistributionState()
    data class Stage3_7<out T>(val data: T) : HouseOfDistributionState()
    data class Stage3_8<out T>(val data: T) : HouseOfDistributionState()
    data class Stage3_9<out T>(val data: T) : HouseOfDistributionState()
    data class Stage3_10<out T>(val data: T) : HouseOfDistributionState()
    data class Stage3_11<out T>(val data: T) : HouseOfDistributionState()
    data class Stage3_12<out T>(val data: T) : HouseOfDistributionState()
    data class Stage3_13<out T>(val data: T) : HouseOfDistributionState()
    data class Stage3_14<out T>(val data: T) : HouseOfDistributionState()
    data class Stage4_1<out T>(val data: T) : HouseOfDistributionState()
    data class Stage4_2<out T>(val data: T) : HouseOfDistributionState()
    data class Stage4_3<out T>(val data: T) : HouseOfDistributionState()
    data class StageFinish<out T>(val data: T) : HouseOfDistributionState()
    data class Error(val error: Throwable) : HouseOfDistributionState()
    data object Loading : HouseOfDistributionState()
}
