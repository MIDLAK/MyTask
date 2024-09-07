package com.vadim.mytask.service

import com.vadim.mytask.dto.Tag

interface TagService {
    fun getById(id: Int): Tag
    fun create(tag: Tag): Int
    fun update(id: Int, tag: Tag)
    fun delete(id: Int)
}