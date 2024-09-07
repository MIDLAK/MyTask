package com.vadim.mytask.repository

import com.vadim.mytask.entity.TagEntity
import org.springframework.data.repository.CrudRepository

interface TagRepository : CrudRepository<TagEntity, Int>