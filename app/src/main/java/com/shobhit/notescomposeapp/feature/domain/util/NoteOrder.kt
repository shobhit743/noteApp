package com.shobhit.notescomposeapp.feature.domain.util

data class NoteOrder(val noteType: NoteType, val orderType: OrderType)

enum class NoteType {
        TITLE, DATE, COLOR
}

enum class OrderType{
    ASCENDING, DESCENDING
}