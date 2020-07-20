package com.example.databinding.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note (

    val title: String,
    val body: String
){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}