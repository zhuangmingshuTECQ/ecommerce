package com.mingshu.ecommerce.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE, reason ="Fail to read Csv File")
class CSVException: RuntimeException {
	constructor() : super()
	constructor(message: String?) : super(message)
	constructor(message: String?, cause: Throwable?) : super(message, cause)
	constructor(cause: Throwable?) : super(cause)
	constructor(message: String?, cause: Throwable?, enableSuppression: Boolean, writableStackTrace: Boolean) : super(
		message,
		cause,
		enableSuppression,
		writableStackTrace
	)

}