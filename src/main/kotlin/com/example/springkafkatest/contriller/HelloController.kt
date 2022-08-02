package com.example.springkafkatest.contriller

import com.example.springkafkatest.entity.Shop
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kafka")
class HelloController(
    private val shopKafkaTemplate: KafkaTemplate<String, Shop>
) {

    @GetMapping("/insert")
    fun insertShop(): String {
        val testShop = Shop("1", "test1")
        shopKafkaTemplate.send("test.shop", testShop)

        return "ok"
    }

    @GetMapping("/insert2")
    fun insertShop2(@RequestParam id: String, @RequestParam name: String): String {
        val testShop = Shop(id, name)
        shopKafkaTemplate.send("test.shop", testShop)

        return "ok"
    }
}