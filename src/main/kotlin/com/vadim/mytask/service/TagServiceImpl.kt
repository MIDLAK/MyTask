package com.vadim.mytask.service

import com.vadim.mytask.dto.Tag
import com.vadim.mytask.dto.Task
import com.vadim.mytask.entity.TagEntity
import com.vadim.mytask.entity.TaskEntity
import com.vadim.mytask.repository.TagRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TagServiceImpl(
    private val tagRepository: TagRepository
): TagService {
    override fun getById(id: Int): Tag =
        tagRepository.findByIdOrNull(id)?.toDto() ?: throw RuntimeException("tag not found")

    override fun create(tag: Tag): Int {
        val tagEntity = tagRepository.save(tag.toEntity())
        return tagEntity.id
    }

    override fun update(id: Int, tag: Tag) {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int) {
        TODO("Not yet implemented")
    }

    private fun Tag.toEntity(): TagEntity = TagEntity(id = 0, name = this.name)

    private fun TagEntity.toDto(): Tag = Tag(id = this.id, name = this.name)
}