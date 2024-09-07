package com.vadim.mytask.service

import com.vadim.mytask.dto.Priority

interface PriorityService {
    fun getAll(): List<Priority>
}