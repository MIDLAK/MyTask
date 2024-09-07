package com.vadim.mytask.entity

import jakarta.persistence.*

@Entity
@Table(name = "priority")
class PriorityEntity (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    var name: String = "",
    var plevel: Int = 0
)