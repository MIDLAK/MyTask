package com.vadim.mytask.exception

import org.springframework.http.HttpStatus

class TaskNotFoundException(taskId: Int): ApiException(
    HttpStatus.NOT_FOUND,
    ApiError(
        code = "task_not_found",
        description = "task width id=$taskId not found"
    )
)