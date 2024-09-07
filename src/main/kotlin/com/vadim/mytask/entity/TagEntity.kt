package com.vadim.mytask.entity

import jakarta.persistence.*

@Entity
@Table(name = "tag")
data class TagEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    var name: String = ""
)
