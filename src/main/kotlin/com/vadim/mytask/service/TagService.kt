package com.vadim.mytask.service

import com.vadim.mytask.dto.Tag

interface TagService {
    fun getById(id: Int, sort: String): Tag
    fun getUsed(): List<String>
    fun create(tag: Tag): Int
    fun update(id: Int, tag: Tag)
    fun delete(id: Int)
}