package com.vadim.mytask.entity

import com.vadim.mytask.dto.Priority
import jakarta.persistence.*

@Entity
@Table(name = "priority")
class PriorityEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "priority_id")
    var id: Int = 0,
    val name: String = "",
    val plevel: Int? = null
) {
    fun toDto(): Priority = Priority(
        id = this.id,
        name = this.name,
        plevel = this.plevel ?: 0
    )
}