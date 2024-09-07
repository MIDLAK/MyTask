package com.vadim.mytask.dto

import com.vadim.mytask.entity.PriorityEntity
import com.vadim.mytask.entity.TaskEntity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
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
    val priority: Priority
) {
    fun toEntity(): TaskEntity = TaskEntity(
        id = 0,
        title = this.title,
        description = this.description,
        dueDate = this.dueDate,
        createdDate = this.createdDate,
        updatedDate = this.updatedDate,
        deletedDate = this.deletedDate,
        statusId = this.statusId,
        priority = this.priority.toEntity()
    )
}
