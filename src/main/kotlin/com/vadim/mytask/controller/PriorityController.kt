package com.vadim.mytask.controller

import com.vadim.mytask.dto.Priority
import com.vadim.mytask.service.PriorityService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/priorities")
class PriorityController(
    private val priorityService: PriorityService
) {
    @GetMapping
    fun getAll(): List<Priority> = priorityService.getAll()
}