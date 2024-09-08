package com.vadim.mytask.controller

import com.vadim.mytask.dto.Task
import com.vadim.mytask.service.TaskService
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.LocalDateTime

@RestController
@RequestMapping("/task")
class TaskController(
    private val taskService: TaskService
) {
    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Int): Task = taskService.getById(id);

    @GetMapping("/by-date")
    fun getByDueDate(@RequestParam("dueDate") isoDate: String,
                     @RequestParam("sort", defaultValue = "asc") sortStr: String): List<Task> {
        val sort = when(sortStr) {
            "desc" -> Sort.by(Sort.Order.desc("priority.plevel"))
            else -> Sort.by(Sort.Order.asc("priority.plevel"))
        }
        val dueDay = LocalDate.parse(isoDate)
        return taskService.getByDueDate(dueDay, sort)
    }

    @PostMapping
    fun create(@RequestBody task: Task): Int = taskService.create(task)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody task: Task) = taskService.update(id, task)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int) = taskService.delete(id)
}