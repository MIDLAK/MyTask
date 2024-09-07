package com.vadim.mytask.exception

import org.springframework.http.HttpStatus

class TagNotFoundException(tagId: Int): ApiException(
    httpStatus = HttpStatus.NOT_FOUND,
    apiError = ApiError(
        code = "tag_not_found",
        description = "tag width id=$tagId not found"
    )
)