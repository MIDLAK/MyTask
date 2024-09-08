package com.vadim.mytask.entity

import com.vadim.mytask.dto.Task
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "task")
data class TaskEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
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

    @ManyToOne
    @JoinColumn(name = "priority_id")
    var priority: PriorityEntity
) {
    fun toDto(): Task = Task(
        id = this.id,
        title = this.title,
        description = this.description,
        dueDate = this.dueDate,
        createdDate = this.createdDate,
        updatedDate = this.updatedDate,
        deletedDate = this.deletedDate,
        statusId = this.statusId,
        priority = this.priority.toDto()
    )
}
