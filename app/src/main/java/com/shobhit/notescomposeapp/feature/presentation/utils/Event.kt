package com.shobhit.notescomposeapp.feature.presentation.utils

sealed class Event{
    class TitleEvent(val title:String):Event()
    class DescEvent(val desc:String):Event()
    class ColorEvent(val color:Int):Event()
    class SaveNote(val title: String,val desc: String,val color:Int):Event()
}