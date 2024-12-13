package com.tooploox.recruitment.worker.message

import com.fasterxml.jackson.annotation.JsonProperty

data class WorkerTaskMessage(
    // This is an example message. Feel free to modify it or add additional fields as needed.
    @JsonProperty("taskName") val taskName: String,
    @JsonProperty("input") val input: String? = null  // Result from previous task / instruction
)
