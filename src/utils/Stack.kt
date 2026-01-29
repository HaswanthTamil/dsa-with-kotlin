package utils

class Stack <T> {
    val stack = mutableListOf<T>()

    fun push(elem: T) = stack.addLast(elem)
    fun pop() = stack.removeLast()
    fun peek() = stack.last()
    fun isEmpty() = stack.isEmpty()
}
