package com.tooploox.recruitment.worker.service

import com.tooploox.recruitment.worker.message.WorkerResultMessage
import com.tooploox.recruitment.worker.message.WorkerTaskMessage
import com.tooploox.recruitment.worker.queue.TaskResultSender
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class WorkerServiceTest {

    private val mockTaskResultSender: TaskResultSender = mock()
    private val underTest: WorkerService = WorkerService(mockTaskResultSender)

    @Test
    fun shouldProcessTask() {
        // given
        val message = WorkerTaskMessage(taskName = "A", input = null)
        val expectedResultObject = WorkerResultMessage(taskName = "A", result = "[A]")

        // when
        underTest.process(message)

        // then
        verify(mockTaskResultSender).send(expectedResultObject)
    }

    @Test
    fun shouldProcessTaskWithInput() {
        // given
        val message = WorkerTaskMessage(taskName = "B", input = "[A]")
        val expectedResultObject = WorkerResultMessage(taskName = "B", result = "[A] -> [B]")

        // when
        underTest.process(message)

        // then
        verify(mockTaskResultSender).send(expectedResultObject)
    }
}