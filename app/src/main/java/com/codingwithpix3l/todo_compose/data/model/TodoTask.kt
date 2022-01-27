package com.codingwithpix3l.todo_compose.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.codingwithpix3l.todo_compose.util.Constant.DATABASE_TABLE

@Entity(tableName = DATABASE_TABLE)
data class TodoTask(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val title:String,
    val disc: String,
    val priority: Priority
)
