package com.vadim.mytask.exception

import org.springframework.http.HttpStatus

class PriorityNotFoundException(priorityId: Int): ApiException(
    httpStatus = HttpStatus.NOT_FOUND,
    apiError = ApiError(
        code = "priority_not_found",
        description = "priority width id=$priorityId not found"
    )
) {
}