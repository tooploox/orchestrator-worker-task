package com.tooploox.recruitment.worker.queue

import com.fasterxml.jackson.databind.ObjectMapper
import com.tooploox.recruitment.worker.exception.ReadMessageException
import com.tooploox.recruitment.worker.logger
import com.tooploox.recruitment.worker.message.WorkerTaskMessage
import com.tooploox.recruitment.worker.service.WorkerService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.io.IOException

@Component
class KafkaMessageConsumer(private val workerService: WorkerService) {
    private val log = logger()
    private val objectMapper = ObjectMapper()

    @KafkaListener(topics = ["worker-task-topic"], groupId = "worker-group")
    fun listener(message: ByteArray) {
        log.info("Received new message from Orchestrator")
        val workerTaskMessage = readMessage(message)

        workerService.process(workerTaskMessage)
    }

    private fun readMessage(message: ByteArray): WorkerTaskMessage {
        try {
            return objectMapper.readValue(message, WorkerTaskMessage::class.java)
        } catch (e: IOException) {
            log.error("Failed to read message from queue", e)
            throw ReadMessageException("Failed to read message", e)
        }
    }
}