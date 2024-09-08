package com.vadim.mytask.service

import com.vadim.mytask.dto.Task
import com.vadim.mytask.entity.TaskEntity
import com.vadim.mytask.exception.PriorityNotFoundException
import com.vadim.mytask.exception.TaskNotFoundException
import com.vadim.mytask.repository.PriorityRepository
import com.vadim.mytask.repository.TaskRepository
import jakarta.transaction.Transactional
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Service
class TaskServiceImpl(
    private val taskRepository: TaskRepository,
    private val priorityRepository: PriorityRepository
): TaskService {
    @Transactional
    override fun create(task: Task): Int {
        priorityRepository.findByIdOrNull(task.priority.id) ?: throw PriorityNotFoundException(task.priority.id)
        val taskEntity = taskRepository.save(task.toEntity())
        return taskEntity.id
    }

    @Transactional
    override fun update(id: Int, task: Task) {
        val updatedTask = taskRepository.findByIdOrNull(id = id) ?: throw TaskNotFoundException(id)

        updatedTask.title = task.title
        updatedTask.description = task.description
        updatedTask.dueDate = task.dueDate
        updatedTask.createdDate = task.createdDate
        updatedTask.updatedDate = task.updatedDate
        updatedTask.deletedDate = task.deletedDate
        updatedTask.statusId = task.statusId

        val priorityEntity =
            priorityRepository.findByIdOrNull(task.priority.id) ?: throw PriorityNotFoundException(task.priority.id)
        updatedTask.priority = priorityEntity

        taskRepository.save(updatedTask)
    }

    override fun getById(id: Int): Task =
        taskRepository.findByIdOrNull(id)?.toDto() ?: throw TaskNotFoundException(id)

    @Transactional
    override fun delete(id: Int) {
        val deletingTask = taskRepository.findByIdOrNull(id) ?: throw TaskNotFoundException(id)
        taskRepository.deleteById(deletingTask.id)
    }

    override fun getByDueDate(dueDate: LocalDate, sort: Sort): List<Task> {
        val startOfDay = dueDate.atStartOfDay()
        val endOfDay = dueDate.atTime(LocalTime.MAX)
        return taskRepository.findByDueDateBetween(startOfDay, endOfDay, sort).map { it.toDto() }
    }

}