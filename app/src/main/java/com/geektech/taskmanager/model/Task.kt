package com.geektech.taskmanager.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var tittle: String? = null,
    var description: String? = null
) : java.io.Serializable
