package com.tooploox.recruitment.worker.service

import com.tooploox.recruitment.worker.logger
import com.tooploox.recruitment.worker.message.WorkerResultMessage
import com.tooploox.recruitment.worker.message.WorkerTaskMessage
import com.tooploox.recruitment.worker.queue.TaskResultSender
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit
import kotlin.random.Random

@Service
class WorkerService(private val taskResultSender: TaskResultSender) {
    private val log = logger()

    fun process(message: WorkerTaskMessage) {
        log.info("Worker starts processing message: {}", message)

        // Simulate 1 - 3 seconds of processing time
        val delay = Random.nextLong(1000, 3001)
        TimeUnit.MILLISECONDS.sleep(delay)

        // Simulate response
        val simulatedResponse: String = if (message.input != null) {
            "${message.input} -> [${message.taskName}]"
        } else {
            "[${message.taskName}]"
        }

        val result = WorkerResultMessage(taskName = message.taskName, result = simulatedResponse)
        log.info("Processing finished with result: {}", result)

        taskResultSender.send(result)
    }
}