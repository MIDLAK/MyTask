package com.vadim.mytask.controller

import com.vadim.mytask.dto.Tag
import com.vadim.mytask.service.TagService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tag")
class TagController(
    private val tagService: TagService
) {
    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Int,
                @RequestParam("sort", defaultValue = "asc") sortStr: String): Tag = tagService.getById(id = id, sort = sortStr)

    @GetMapping("/get-used")
    fun getUsedTags(): List<String> = tagService.getUsed()

    @PostMapping
    fun create(@RequestBody tag: Tag): Int = tagService.create(tag = tag)

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Int, @RequestBody tag: Tag) = tagService.update(id = id, tag = tag)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Int) = tagService.delete(id = id)
}