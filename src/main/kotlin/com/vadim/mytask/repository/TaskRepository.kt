package com.vadim.mytask.repository

import com.vadim.mytask.entity.TaskEntity
import org.springframework.cglib.core.Local
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.repository.CrudRepository
import java.time.LocalDateTime

interface TaskRepository: CrudRepository<TaskEntity, Int> {
    fun findByDueDateBetween(pageable: Pageable, startOfDay: LocalDateTime, endOfDay: LocalDateTime): List<TaskEntity>
}