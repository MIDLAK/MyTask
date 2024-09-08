package com.vadim.mytask.entity

import com.vadim.mytask.dto.Tag
import jakarta.persistence.*

@Entity
@Table(name = "tag")
data class TagEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    val id: Int = 0,
    var name: String = "",

    @ManyToMany
    @JoinTable(
        name = "task_tag",
        joinColumns = [JoinColumn(name = "tag_id")],
        inverseJoinColumns = [JoinColumn(name = "task_id")]
    )
    var tasks: List<TaskEntity>? = listOf()
) {
    fun toDto(): Tag = Tag(
        id = this.id,
        name = this.name,
        tasks = this.tasks?.map { it.toDto() }
    )
}
