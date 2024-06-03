package de.sotterbeck.hbrscalcreator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class HbrsCalCreatorApplication

fun main(args: Array<String>) {
    runApplication<HbrsCalCreatorApplication>(*args)
}
