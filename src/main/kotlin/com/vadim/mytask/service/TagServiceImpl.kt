package com.vadim.mytask.service

import com.vadim.mytask.dto.Tag
import com.vadim.mytask.dto.Task
import com.vadim.mytask.entity.TagEntity
import com.vadim.mytask.exception.TagNotFoundException
import com.vadim.mytask.repository.TagRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TagServiceImpl(
    private val tagRepository: TagRepository
): TagService {
    override fun getById(id: Int, sort: String): Tag {
        val tag = tagRepository.findByIdOrNull(id)?.toDto() ?: throw TagNotFoundException(id)
        val sortedTasks = when(sort) {
            "desc" -> tag.tasks?.sortedByDescending { it.priority.plevel } ?: listOf()
            else -> tag.tasks?.sortedBy { it.priority.plevel } ?: listOf()
        }
        return tag.copy(tasks = sortedTasks)
    }


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

    override fun getUsed(): List<String> {
        val tags = tagRepository.findAllTagsWithLinkTask().map { it.toDto() }
        val tagsNames: MutableList<String> = mutableListOf()
        for (tag: Tag in tags) {
            tagsNames.add(tag.name)
        }
        return tagsNames
    }
}