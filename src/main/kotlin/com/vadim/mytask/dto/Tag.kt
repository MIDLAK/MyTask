package com.vadim.mytask.dto

import com.vadim.mytask.entity.TagEntity

data class Tag(
    val id: Int? = null,
    val name: String,
    var tasks: List<Task>? = null
) {
    fun toEntity(): TagEntity = TagEntity(
        id = this.id ?: 0,
        name = this.name,
        tasks = this.tasks?.map { it.toEntity() }
    )
}
