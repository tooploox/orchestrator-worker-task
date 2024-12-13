package com.tooploox.recruitment.worker.queue

import com.tooploox.recruitment.worker.message.WorkerResultMessage

interface TaskResultSender {

    fun send(result: WorkerResultMessage)
}