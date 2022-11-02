package lru

open class Node<K, V>(
    var back: Node<K, V>? = null,
    var next: Node<K, V>? = null
) {
    fun removeSelf() {
        back?.next = next
        next?.back = back
    }

    fun putAfter(node : Node<K, V>) {
        node.back = this
        node.next = next

        next?.back = node
        next = node
    }
}

data class NodeV<K, V> (
    val key: K,
    val value: V
) : Node<K, V>() {
    fun asPair() = key to value
}