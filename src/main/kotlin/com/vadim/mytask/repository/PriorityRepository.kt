package com.vadim.mytask.repository

import com.vadim.mytask.entity.PriorityEntity
import org.springframework.data.repository.CrudRepository

// важны соглашения об именовании
interface PriorityRepository : CrudRepository<PriorityEntity, Int> {
    fun findByOrderByPlevelDesc(): List<PriorityEntity>
}