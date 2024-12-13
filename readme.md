# Tooploox Recruitment Task

# Task Description:

Your task is to create a Kotlin application that accepts and orchestrates sets of instructions for execution. Assume there’s a service responsible for parsing user input, so in this task, you’ll only need to process data in a specified format and handle user requests via two services.

# Input

The application receives input in the form of an array of arrays, where each array contains a set of instructions:

1. The inner arrays are sequences of tasks.
1. The outer array is just a container for independent sequences.
1. Instructions in the inner arrays represent a sequence that must be executed in a specific order — the result of one instruction is passed to the next.

## Examples:

- `[[A], [B], [C]]` - Tasks A, B, and C can be executed simultaneously. It’s a list of three sequences with one element each.
Example tasks: `[["wash dishes"], ["clean floors"], ["clean windows"]]`
- `[[A, B, C]]` - Tasks A, B, and C must be executed sequentially. The result of A is passed to B, and the result of B is passed to C.
Example tasks: `[["put clothes in the washing machine", "run the washing machine cycle", "take the clothes out of the washing machine"]]`
- `[[A], [B, C]]` - Tasks A and B can be executed simultaneously, but C waits for the result of B.
Example tasks: `[["vacuum the living room"], ["mow the lawn", "collect the grass clippings"]]`

# Objectives

You are provided with the mock implementation of the Worker Service and a boilerplate for the Receiver/Orchestrator Service you will need to develop. Here’s the expected outcome:

## Receiver/Orchestrator Service

Communicates with the user via a REST interface.

Responsible for receiving and processing input, and for sending individual instructions to the worker service.

Handles task dependencies, ensuring that sequential tasks are executed in the correct order, while independent tasks are ordered asynchronously for potential parallel processing.

Allow the user to check the status of an ordered task sequence.

Logs the received orders along with the execution status of the tasks performed by the worker.

Sends tasks to the worker service via a queue (e.g., Kafka, provided to you as a docker-compose file).


## Worker Service

Receives tasks from the queue.

Executes tasks by simulating a delay (using a sleep/wait function with a random duration between 1 and 3 seconds).

Returns a random result to simulate task completion.

