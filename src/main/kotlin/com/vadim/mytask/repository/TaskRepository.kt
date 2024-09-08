package com.vadim.mytask.repository

import com.vadim.mytask.entity.TaskEntity
import org.springframework.cglib.core.Local
import org.springframework.data.domain.Sort
import org.springframework.data.repository.CrudRepository
import java.time.LocalDateTime

interface TaskRepository: CrudRepository<TaskEntity, Int> {
    fun findByDueDateBetween(startOfDay: LocalDateTime, endOfDay: LocalDateTime, sort: Sort): List<TaskEntity>
}