package com.example.modul5.presentation.navigation

object Navigation {
    const val ROUTE_LIST = "makeup_list"
    const val ARG_MAKEUP_ID = "makeupId"
    const val ROUTE_DETAIL = "makeup_detail/{$ARG_MAKEUP_ID}"

    fun createDetailRoute(makeupId: Int): String {
        return "makeup_detail/$makeupId"
    }
}