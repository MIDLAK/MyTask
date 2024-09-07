package com.vadim.mytask.controller

import com.vadim.mytask.dto.Task
import com.vadim.mytask.service.TaskService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/task")
class TaskController(
    private val taskService: TaskService
) {
    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Int): Task = taskService.getById(id);

    @PostMapping
    fun create(@RequestBody task: Task): Int = taskService.create(task)
}