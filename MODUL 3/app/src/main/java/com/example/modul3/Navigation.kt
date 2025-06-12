package com.example.modul3

object Navigation {
    const val ROUTE_LIST = "makeup_list"
    const val ROUTE_DETAIL = "makeup_detail/{makeupId}"

    fun createDetailRoute(makeupId: String): String {
        return "makeup_detail/$makeupId"
    }

    const val ARG_MAKEUP_ID = "makeupId"
}