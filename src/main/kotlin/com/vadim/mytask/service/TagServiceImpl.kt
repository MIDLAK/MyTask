package com.vadim.mytask.service

import com.vadim.mytask.dto.Tag
import com.vadim.mytask.dto.Task
import com.vadim.mytask.entity.TagEntity
import com.vadim.mytask.entity.TaskEntity
import com.vadim.mytask.exception.TagNotFoundException
import com.vadim.mytask.repository.TagRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TagServiceImpl(
    private val tagRepository: TagRepository
): TagService {
    override fun getById(id: Int): Tag =
        tagRepository.findByIdOrNull(id)?.toDto() ?: throw TagNotFoundException(id)

    @Transactional
    override fun create(tag: Tag): Int {
        val tagEntity = tagRepository.save(tag.toEntity())
        return tagEntity.id
    }

    @Transactional
    override fun update(id: Int, tag: Tag) {
        val updatedTag = tagRepository.findByIdOrNull(id = id) ?: throw TagNotFoundException(id)
        updatedTag.name = tag.name
        tagRepository.save(updatedTag)
    }

    @Transactional
    override fun delete(id: Int) {
        val deletedTag = tagRepository.findByIdOrNull(id = id) ?: throw TagNotFoundException(id)
        tagRepository.deleteById(deletedTag.id)
    }

    private fun Tag.toEntity(): TagEntity = TagEntity(id = 0, name = this.name)

    private fun TagEntity.toDto(): Tag = Tag(id = this.id, name = this.name)
}