package com.example.modul4

import android.util.Log

object Navigation {
    const val ROUTE_LIST = "makeup_list"
    const val ROUTE_DETAIL = "makeup_detail/{makeupId}"

    const val ARG_MAKEUP_ID = "makeupId"

    fun createDetailRoute(makeupId: String): String {
        val route = "makeup_detail/$makeupId"
        Log.d("Navigation", "Created route: $route")
        return route
    }
}