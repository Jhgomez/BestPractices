package com.demo.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.demo.R

enum class TopLevelDestination(
    @DrawableRes val icon: Int,
    @StringRes val  title: Int
) {
    HOME(icon = R.drawable.home, title = R.string.home ),
    SEARCH(icon = R.drawable.search, title = R.string.search ),
    PROFILE(icon = R.drawable.profile, title = R.string.profile )
}