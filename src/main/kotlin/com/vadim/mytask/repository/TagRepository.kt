package com.vadim.mytask.repository

import com.vadim.mytask.entity.TagEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface TagRepository : CrudRepository<TagEntity, Int> {
    @Query("SELECT DISTINCT t FROM TagEntity t JOIN t.tasks")
    fun findAllTagsWithLinkTask(): List<TagEntity>
}