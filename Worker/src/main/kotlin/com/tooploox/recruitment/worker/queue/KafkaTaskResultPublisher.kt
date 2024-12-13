package com.tooploox.recruitment.worker.queue

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.tooploox.recruitment.worker.exception.WriteMessageException
import com.tooploox.recruitment.worker.logger
import com.tooploox.recruitment.worker.message.WorkerResultMessage
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

private const val ORCHESTRATOR_TOPIC = "orchestrator-task-result-topic"

@Component
class KafkaTaskResultPublisher(private val kafkaTemplate: KafkaTemplate<String, ByteArray>) : TaskResultSender {
    private val log = logger()
    private val objectMapper = ObjectMapper()

    override fun send(result: WorkerResultMessage) {
        log.info("Publishing message to Orchestrator: {}", result)
        kafkaTemplate.send(ORCHESTRATOR_TOPIC, writeMessage(result))
    }

    private fun writeMessage(message: WorkerResultMessage): ByteArray {
        try {
            return objectMapper.writeValueAsBytes(message)
        } catch (e: JsonProcessingException) {
            log.error("Failed to write message as byte array", e)
            throw WriteMessageException("Failed to write message", e)
        }
    }
}