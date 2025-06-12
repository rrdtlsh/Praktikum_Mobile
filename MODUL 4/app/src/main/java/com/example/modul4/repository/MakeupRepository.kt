package com.example.modul4.repository

import com.example.modul4.model.Makeup

class MakeupRepository {
    fun getMakeupList(): List<Makeup> = Makeup.makeupList
}