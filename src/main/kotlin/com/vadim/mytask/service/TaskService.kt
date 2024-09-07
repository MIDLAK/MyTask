package com.vadim.mytask.service

import com.vadim.mytask.dto.Task

interface TaskService {
    fun create(task: Task): Int
    fun update(id: Int, task: Task)
    fun delete(id: Int)
}