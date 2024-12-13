# Worker Service

This is a simple service that simulates processing event from Kafka and sends response back to queue.

* Consumes message from topic: `worker-task-topic`
* Publishes result to topic: `orchestrator-task-result-topic`


Feel free to modify this service or update Kafka message by adding more fields as needed.
