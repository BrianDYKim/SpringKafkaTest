package com.example.springkafkatest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka

@SpringBootApplication(scanBasePackages = ["com.example"])
@EnableKafka
class SpringKafkaTestApplication

fun main(args: Array<String>) {
    runApplication<SpringKafkaTestApplication>(*args)
}
