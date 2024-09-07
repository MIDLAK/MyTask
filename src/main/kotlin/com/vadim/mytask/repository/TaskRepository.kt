package com.vadim.mytask.repository

import com.vadim.mytask.entity.TaskEntity
import org.springframework.data.repository.CrudRepository

interface TaskRepository: CrudRepository<TaskEntity, Int>