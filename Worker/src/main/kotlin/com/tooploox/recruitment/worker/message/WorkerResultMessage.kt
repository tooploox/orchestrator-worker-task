package com.tooploox.recruitment.worker.message

import com.fasterxml.jackson.annotation.JsonProperty

data class WorkerResultMessage(
    // This is an example message. Feel free to modify it or add additional fields as needed.
    @JsonProperty("taskName") val taskName: String,
    @JsonProperty("result") val result: String
)
