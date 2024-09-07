package com.vadim.mytask.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "task")
data class TaskEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    var title: String = "",
    var description: String? = "",

    @Column(name = "due_date")
    var dueDate: LocalDateTime? = null,

    @Column(name = "created_date")
    var createdDate: LocalDateTime,

    @Column(name = "updated_date")
    var updatedDate: LocalDateTime? = null,

    @Column(name = "deleted_date")
    var deletedDate: LocalDateTime? = null,

    @Column(name = "status_id")
    var statusId: Int? = null,

    @Column(name = "priority_id")
    var priorityId: Int? = null
)
