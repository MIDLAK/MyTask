package com.vadim.mytask.service

import com.vadim.mytask.dto.Task
import com.vadim.mytask.entity.TaskEntity
import com.vadim.mytask.repository.TaskRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TaskServiceImpl(
    private val taskRepository: TaskRepository
): TaskService {
    @Transactional
    override fun create(task: Task): Int {
        val taskEntity = taskRepository.save(task.toEntity())
        return taskEntity.id
    }

    @Transactional
    override fun update(id: Int, task: Task) {
        val updatedTask = taskRepository.findByIdOrNull(id = id) ?: throw RuntimeException("task not found")

        updatedTask.title = task.title
        updatedTask.description = task.description
        updatedTask.dueDate = task.dueDate
        updatedTask.createdDate = task.createdDate
        updatedTask.updatedDate = task.updatedDate
        updatedTask.deletedDate = task.deletedDate

        taskRepository.save(updatedTask)
    }

    override fun getById(id: Int): Task =
        taskRepository.findByIdOrNull(id)?.toDto() ?: throw RuntimeException("task not found")

    override fun delete(id: Int) {
        TODO("Not yet implemented")
    }

    private fun Task.toEntity(): TaskEntity = TaskEntity(
        id = 0,
        title = this.title,
        description = this.description,
        dueDate = this.dueDate,
        createdDate = this.createdDate,
        updatedDate = this.updatedDate,
        deletedDate = this.deletedDate,
        statusId = this.statusId,
        priorityId = this.priorityId
    )


    private fun TaskEntity.toDto(): Task = Task(
        id = this.id,
        title = this.title,
        description = this.description,
        dueDate = this.dueDate,
        createdDate = this.createdDate,
        updatedDate = this.updatedDate,
        deletedDate = this.deletedDate,
        statusId = this.statusId,
        priorityId = this.priorityId
    )
}