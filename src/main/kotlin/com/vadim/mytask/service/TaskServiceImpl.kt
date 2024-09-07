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
        TODO("Not yet implemented")
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

}