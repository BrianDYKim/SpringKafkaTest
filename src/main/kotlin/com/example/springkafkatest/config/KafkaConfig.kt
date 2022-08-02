package com.example.springkafkatest.config

import com.example.springkafkatest.entity.Shop
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer

/** Kafka Config를 설정하는 Configuration class
 * @param bootstrapServers Kafka CLuster의 모든 broker 정보를 저장하는 리스트
 * @param producerAcks producer가 사용하는 producer akcs 정책. default는 all(-1) 이다.
 */
@Configuration
class KafkaConfig(
    @Value("\${spring.kafka.producer.bootstrap-servers}") private val bootstrapServers: String,
    @Value("\${spring.kafka.producer.acks}") private val producerAcks: String
) {

    @Bean
    fun shopKafkaTemplate(): KafkaTemplate<String, Shop> {
        return KafkaTemplate(shopProducerFactory())
    }

    private fun shopProducerFactory(): ProducerFactory<String, Shop> {
        val configProps = producerConfig()
        return DefaultKafkaProducerFactory(configProps)
    }

    // producer config를 반환해주는 메소드
    private fun producerConfig(): HashMap<String, Any> {
        val configProps: HashMap<String, Any> = hashMapOf()
        configProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        configProps[ProducerConfig.ACKS_CONFIG] = producerAcks
        configProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java

        return configProps
    }
}