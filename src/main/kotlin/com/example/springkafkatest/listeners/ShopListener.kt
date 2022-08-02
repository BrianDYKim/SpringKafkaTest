package com.example.springkafkatest.listeners

import com.example.springkafkatest.entity.Shop
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class ShopListener() {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @KafkaListener(topics = ["test.shop"], groupId = "test-shop-group-1")
    fun consumeShop(shop: Shop) {
        logger.info("[Consumer] ${shop}")

    }
}