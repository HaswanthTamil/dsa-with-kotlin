import utils.Queue
import utils.Stack

class GraphNode<T>(var data: T, val id: Int) {
    val neighbours =  mutableSetOf<GraphNode<T>>()

    override fun toString(): String = id.toString()
    override fun hashCode(): Int = id
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is GraphNode<*>) return false
        return this.id == other.id
    }
}

class Edge<T>(val from: GraphNode<T>, val to: GraphNode<T>)

class Graph<T>() {
    val nodes = mutableSetOf<GraphNode<T>>()
    val edges = mutableSetOf<Edge<T>>()

    fun addNode(newNode: GraphNode<T>, newNeighbours: MutableIterable<GraphNode<T>>?): Boolean {
        if (newNeighbours != null) newNode.neighbours.addAll(newNeighbours)
        for (n in newNode.neighbours) n.neighbours.add(newNode)
        return nodes.add(newNode)
    }

    fun removeNode(target: GraphNode<T>): Boolean {
        for (n in target.neighbours) n.neighbours.remove(target)
        target.neighbours.clear()
        return nodes.remove(target)
    }

    fun addEdge(from: GraphNode<T>, to: GraphNode<T>): Boolean {
        from.neighbours.add(to)
        to.neighbours.add(from)
        return edges.add(Edge(from, to))
    }

    fun removeEdge(target: Edge<T>): Boolean {
        target.from.neighbours.remove(target.to)
        target.to.neighbours.remove(target.from)
        return edges.remove(target)
    }

    fun hasEdge(edge: Edge<T>) = edges.contains(edge)

    fun getNeighbours(node: GraphNode<T>) = node.neighbours

    fun containsNode(node: GraphNode<T>) = nodes.contains(node)

    fun nodeCount() = nodes.size

    fun edgeCount() = edges.size

    fun isEmpty() = nodes.isEmpty()

    fun clear() {
        nodes.clear()
        edges.clear()
    }

    fun isConnected(node1: GraphNode<T>, node2: GraphNode<T>) = node1.neighbours.contains(node2)
}

fun <P> dfs(
    start: GraphNode<P>,
    target: GraphNode<P>? = null,
    process: (P) -> Unit
): Boolean {
    val stack = Stack<GraphNode<P>>()
    val seen = mutableSetOf<GraphNode<P>>()

    stack.push(start)
    seen.add(start)

    while (!stack.isEmpty()) {
        val curr = stack.pop()

        process(curr.data)
        if (target != null && curr == target) return true

        for (n in curr.neighbours) {
            if (n !in seen) {
                seen.add(n)
                stack.push(n)
            }
        }
    }

    return target == null
}

fun <P> bfs(
    start: GraphNode<P>,
    target: GraphNode<P>? = null,
    process: (P) -> Unit
): Boolean {
    val next = Queue<GraphNode<P>>()
    val seen = mutableSetOf<GraphNode<P>>()
    var curr = start

    next.enqueue(curr)
    seen.add(curr)

    while (!next.isEmpty()) {
        curr = next.dequeue()
        process(curr.data)
        if (target != null && curr == target) return true

        for (n in curr.neighbours) if (n !in seen) {
            seen.add(n)
            next.enqueue(n)
        }
    }

    return target == null
}


/*
findPath
hasCycle
addEdge(u, v, weight)
getWeight
updateWeight
topologicalSort
shortestPath
minimumSpanningTree
*/