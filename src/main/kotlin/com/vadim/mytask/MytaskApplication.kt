package com.vadim.mytask

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MytaskApplication

fun main(args: Array<String>) {
	runApplication<MytaskApplication>(*args)
}
