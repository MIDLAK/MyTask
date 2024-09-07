package com.vadim.mytask.dto

import com.vadim.mytask.entity.PriorityEntity

data class Priority(
    val id: Int,
    val name: String,
    val plevel: Int? = null
) {
    fun toEntity(): PriorityEntity = PriorityEntity(
        id = this.id,
        name = this.name,
        plevel = this.plevel
    )
}
