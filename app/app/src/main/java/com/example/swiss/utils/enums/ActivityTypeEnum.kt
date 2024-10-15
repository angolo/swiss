package com.example.swiss.utils.enums

import androidx.annotation.DrawableRes
import com.example.swiss.R

enum class ActivityTypeEnum(
    val activityName: String,
    @DrawableRes val icon: Int
) {
    //TODO: stringhe
    SWIMMING("Nuoto", R.drawable.icon_swimming),
    CLIMBING_INDOOR("Arrampicata indoor", R.drawable.icon_indoor),
    FOOTBALL("Calcio", R.drawable.icon_football),
    TENNIS("Tennis", R.drawable.icon_tennis),
    RUN("Corsa", R.drawable.icon_run)
}