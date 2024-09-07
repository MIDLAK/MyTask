package com.vadim.mytask.repository

import com.vadim.mytask.entity.PriorityEntity
import org.springframework.data.repository.CrudRepository

interface PriorityRepository : CrudRepository<PriorityEntity, Int>