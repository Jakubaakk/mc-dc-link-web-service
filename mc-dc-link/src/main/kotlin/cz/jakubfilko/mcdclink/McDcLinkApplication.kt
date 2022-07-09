package cz.jakubfilko.mcdclink

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class McDcLinkApplication

fun main(args: Array<String>) {
    runApplication<McDcLinkApplication>(*args)
}
