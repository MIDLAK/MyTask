package com.vadim.mytask.dto

import java.time.LocalDateTime

data class Task(
    val id: Int? = null,
    val title: String,
    val description: String? = null,
    val dueDate: LocalDateTime? = null,
    val createdDate: LocalDateTime,
    val updatedDate: LocalDateTime? = null,
    val deletedDate: LocalDateTime? = null,

    val statusId: Int? = null,
    val priorityId: Int? = null,
)
