package com.demo.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation3.runtime.NavKey
import com.demo.R

object PlaceholderKey: NavKey

enum class TopLevelDestination(
    val navKey: NavKey,
    @DrawableRes val icon: Int,
    @StringRes val  title: Int
) {
    HOME(
        navKey = PlaceholderKey,
        icon = R.drawable.home,
        title = R.string.home
    ),
    SEARCH(
        navKey = PlaceholderKey,
        icon = R.drawable.search,
        title = R.string.search
    ),
    PROFILE(
        navKey = PlaceholderKey,
        icon = R.drawable.profile,
        title = R.string.profile
    )
}