package com.gustavofc97.applydigital.navigation

sealed class TopLevelDestination(
    val route: String
) {
    data object Home : TopLevelDestination(
        route = "home"
    )

}
