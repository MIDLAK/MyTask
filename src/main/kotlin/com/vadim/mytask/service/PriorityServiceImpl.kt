package com.vadim.mytask.service

import com.vadim.mytask.dto.Priority
import com.vadim.mytask.entity.PriorityEntity
import com.vadim.mytask.repository.PriorityRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class PriorityServiceImpl(
    private val priorityRepository: PriorityRepository
) : PriorityService {
    @Cacheable("taskPrioritiesCache")
    override fun getAll(): List<Priority> = priorityRepository.findByOrderByPlevelDesc().map { it.toDto() }
}