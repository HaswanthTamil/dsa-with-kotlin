class DLLNode(val data: Int, var next: DLLNode?, var prev: DLLNode?) {
    override fun toString(): String = data.toString()
}

fun size(head: DLLNode?): Int {
    if (head == null) return 0
    if (head.next == null) return 1

    var len = 0
    var curr = head
    while (curr != null) {
        curr = curr.next
        len++
    }

    return len
}

fun traversePrintForward(head: DLLNode) {
    var curr: DLLNode? = head
    while (curr != null) {
        print(curr.data)
        if (curr.next != null) print(" -> ")
        curr = curr.next
    }
    println()
}

fun traversePrintBackward(tail: DLLNode) {
    var curr: DLLNode? = tail
    while (curr != null) {
        print(curr.data)
        if (curr.prev != null) print(" -> ")
        curr = curr.prev
    }
    println()
}

fun addFirst(head: DLLNode, newHead: DLLNode): DLLNode {
    head.prev = newHead
    newHead.next = head
    return newHead
}

fun addLast(head: DLLNode?, tail: DLLNode, newTail: DLLNode): DLLNode? {
    tail.next = newTail
    newTail.prev = tail
    newTail.next
    return head
}

fun insertAt(head: DLLNode, newNode: DLLNode, index: Int): DLLNode {
    if (index == 0) {
        return addFirst(head, newNode)
    }

    var curr = head
    var pos = 0

    while (curr.next != null && pos < index - 1) {
        curr = curr.next!!
        pos++
    }

    if (pos != index - 1) throw IndexOutOfBoundsException()

    newNode.next = curr.next
    curr.next = newNode
    return head
}

fun removeFirst(head: DLLNode?): DLLNode? {
    if (head?.next == null) return null

    val newHead = head.next
    newHead!!.prev = null
    head.next = null
    return newHead
}

fun removeLast(head: DLLNode?): DLLNode? {
    if (head == null || head.next == null) return null

    var curr = head
    while (curr?.next != null) curr = curr.next!!

    curr.prev!!.next = null
    curr.prev = null
    return head
}

fun removeAt(index: Int, head: DLLNode?): DLLNode? {
    if (head == null) return null
    if (index == 0) return removeFirst(head)

    var curr = head
    var pos = 0

    while (curr != null && pos < index) {
        curr = curr.next
        pos++
    }

    if (curr == null) throw IndexOutOfBoundsException()

    curr.prev!!.next = curr.next
    curr.next?.prev = curr.prev

    curr.prev = null
    curr.next = null

    return head
}

fun removeValue(data: Int, head: DLLNode?): DLLNode? {
    if (head == null) return null

    if (head.data == data) return removeFirst(head)

    var curr = head.next
    while (curr != null) {
        if (curr.data == data) {
            curr.prev!!.next = curr.next
            curr.next?.prev = curr.prev
            curr.prev = null
            curr.next = null
            return head
        }
        curr = curr.next
    }
    return head
}

fun getFirst(head: DLLNode): Int = head.data

fun getLast(head: DLLNode): Int {
    var curr = head
    while (curr.next != null) curr = curr.next!!
    return curr.data
}

fun getAt(index: Int, head: DLLNode): Int {
    var curr: DLLNode? = head
    var pos = 0

    while (curr != null) {
        if (pos == index) return curr.data
        curr = curr.next
        pos++
    }

    throw IndexOutOfBoundsException()
}

fun contains(data: Int, head: DLLNode?): Boolean {
    var curr = head
    while (curr != null) {
        if (curr.data == data) return true
        curr = curr.next
    }
    return false
}

fun indexOf(data: Int, head: DLLNode?): Int {
    var curr = head
    var pos = 0

    while (curr != null) {
        if (curr.data == data) return pos
        curr = curr.next
        pos++
    }
    return -1
}

fun reverse(head: DLLNode?): DLLNode? {
    var curr = head
    var newHead: DLLNode? = null

    while (curr != null) {
        val next = curr.next
        curr.next = curr.prev
        curr.prev = next
        newHead = curr
        curr = next
    }
    return newHead
}

fun findMiddle(head: DLLNode): DLLNode {
    var slow = head
    var fast = head

    while (fast.next?.next != null) {
        slow = slow.next!!
        fast = fast.next!!.next!!
    }
    return slow
}

fun clear(): DLLNode? = null
