package com.vadim.mytask.service

import com.vadim.mytask.dto.Task
import java.time.LocalDate
import java.time.LocalDateTime

interface TaskService {
    fun getById(id: Int): Task
    fun create(task: Task): Int
    fun update(id: Int, task: Task)
    fun delete(id: Int)
    fun getByDueDate(dueDate: LocalDate): List<Task>
}