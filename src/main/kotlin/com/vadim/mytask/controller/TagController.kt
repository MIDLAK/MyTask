package com.vadim.mytask.controller

import com.vadim.mytask.dto.Tag
import com.vadim.mytask.service.TagService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tag")
class TagController(
    private val tagService: TagService
) {
    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Int): Tag = tagService.getById(id = id)

    @PostMapping
    fun create(@RequestBody tag: Tag): Int = tagService.create(tag = tag)

    @PutMapping
    fun update(@PathVariable("id") id: Int, @RequestBody tag: Tag) = tagService.update(id = id, tag = tag)
}