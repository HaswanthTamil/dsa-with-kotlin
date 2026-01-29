class SLLNode(val data: Int, var next: SLLNode?) {
    override fun toString(): String = data.toString()
}

fun traversePrint(head: SLLNode) {
    var curr: SLLNode? = head
    while (curr != null) {
        print(curr.data)
        if (curr.next != null) print(" -> ")
        curr = curr.next
    }
    println()
}

fun isEmpty(head: SLLNode?) = head == null

fun size(head: SLLNode?): Int {
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

fun addFirst(head: SLLNode, newHead: SLLNode): SLLNode {
    newHead.next = head
    return newHead
}

fun addLast(head: SLLNode, newTail: SLLNode): SLLNode {
    var curr = head
    while (curr.next != null) curr = curr.next!!
    curr.next = newTail
    return head
}

fun insertAt(head: SLLNode, newNode: SLLNode, index: Int): SLLNode {
    if (index == 0) {
        newNode.next = head
        return newNode
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

fun removeFirst(head: SLLNode): SLLNode? {
    val new = head.next
    head.next = null
    return new
}

fun removeLast(head: SLLNode): SLLNode? {
    var curr = head
    if (curr.next == null) return null
    if (curr.next!!.next == null) {
        head.next = null
        return head
    }

    while (curr.next!!.next != null) {
        curr = curr.next!!
    }
    curr.next = null
    return head
}

fun removeAt(index: Int, head: SLLNode): SLLNode {
    if (index == 0) return head.next!!

    var curr = head
    var pos = 0

    while (curr.next != null && pos < index - 1) {
        curr = curr.next!!
        pos++
    }

    if (pos != index - 1 || curr.next == null)
        throw IndexOutOfBoundsException()

    curr.next = curr.next!!.next
    return head
}

fun removeValue(data: Int, head: SLLNode): SLLNode {
    var curr = head
    while (curr.next != null) {
        if (curr.next!!.data == data) {
            curr.next = curr.next!!.next
            return head
        }
        curr = curr.next!!
    }
    return head
}

fun getFirst(head: SLLNode): Int = head.data

fun getLast(head: SLLNode): Int {
    var curr = head
    while (curr.next != null) {
        curr = curr.next!!
    }
    return curr.data
}

fun getAt(index: Int, head: SLLNode): Int {
    var curr: SLLNode? = head
    var pos = 0

    while (curr != null) {
        if (pos == index) return curr.data
        curr = curr.next
        pos++
    }

    throw IndexOutOfBoundsException()
}

fun contains(data: Int, head: SLLNode): Boolean {
    var curr: SLLNode? = head
    if (head.data == data) return true
    while (curr != null) {
        if (curr.data == data) return true
        curr = curr.next
    }
    return false
}

fun indexOf(data: Int, head: SLLNode): Int {
    var curr: SLLNode? = head
    if (head.data == data) return 0
    var pos = 0
    while (curr != null) {
        if (curr.data == data) return pos
        pos++
        curr = curr.next
    }
    return -1
}

fun reverse(head: SLLNode): SLLNode {
    if (head.next == null) return head
    val newHead = reverse(head.next!!)
    head.next?.next = head
    head.next = null

    return newHead
}

fun findMiddle(head: SLLNode): SLLNode {
    var slow = head
    var fast = head
    while (fast.next?.next != null) {
        fast = fast.next?.next!!
        slow = slow.next!!
    }
    return slow
}

fun detectCycle(head: SLLNode): Boolean {
    var slow: SLLNode? = head
    var fast: SLLNode? = head

    while (fast?.next != null) {
        slow = slow!!.next
        fast = fast.next!!.next
        if (slow == fast) return true
    }
    return false
}

//fun removeCycle(head: SLLNode): SLLNode {
//    var curr = head
//    if (curr.next == null) return head
//    curr = curr.next!!
//    while (curr.next != null) {
//        if (curr.next == head) {
//            curr.next = null
//            return head
//        }
//        curr = curr.next!!
//    }
//    return head
//}

fun getNthFromEnd(index: Int, head: SLLNode): Int {
    var fast: SLLNode? = head
    var slow: SLLNode? = head

    var i = 0
    while (i < index) {
        fast = fast?.next ?: throw IndexOutOfBoundsException()
        i++
    }

    while (fast?.next != null) {
        fast = fast.next
        slow = slow!!.next
    }

    return slow!!.data
}

fun merge(firstHead: SLLNode, secondHead: SLLNode): SLLNode {
    var curr = firstHead
    while (curr.next != null) curr = curr.next!!
    curr.next = secondHead
    return firstHead
}

fun sortedMerge(a: SLLNode?, b: SLLNode?): SLLNode? {
    if (a == null) return b
    if (b == null) return a
    var res: SLLNode

    if (a.data <= b.data) {
        res = a
        res.next = sortedMerge(a.next, b)
    } else {
        res = b
        res.next = sortedMerge(a, b.next)
    }

    return res
}

fun mergeSort(head: SLLNode): SLLNode {
    if (head.next == null) return head
    val middle = findMiddle(head)
    val next_to_middle = middle.next
    middle.next = null

    val left = mergeSort(head)
    val right = mergeSort(next_to_middle!!)
    val sorted = sortedMerge(left, right)
    return sorted!!
}

fun isPalindrome(head: SLLNode): Boolean {
    val middle = findMiddle(head)
    val next_to_middle = middle.next

    val rev = reverse(next_to_middle!!)
    var p1: SLLNode? = head
    var p2: SLLNode? = rev

    while (p1 != null && p2 != null) {
        if (p1.data != p2.data) {
            middle.next = reverse(rev)
            return false
        }
        p1 = p1.next
        p2 = p2.next
    }

    middle.next = reverse(rev)
    return true
}

fun removeDuplicates(head: SLLNode): SLLNode {
    var curr: SLLNode? = head

    while (curr?.next != null) {
        if (curr.data == curr.next!!.data) {
            curr.next = curr.next!!.next
        } else {
            curr = curr.next
        }
    }
    return head
}

