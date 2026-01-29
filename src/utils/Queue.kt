package utils

class Queue <T> {
    val queue = mutableListOf<T>()

    fun enqueue(elem: T) = queue.addLast(elem)
    fun dequeue() = queue.removeFirst()
    fun peek() = queue[0]
    fun isEmpty() = queue.isEmpty()
}