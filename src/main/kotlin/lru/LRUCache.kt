package lru

class LRUCache<K, V>(capacity: Int) {
    private val hashTable = hashMapOf<K, NodeV<K, V>>()
    private val fst = Node<K, V>()
    private val lst = Node(fst)

    var capacity = capacity
        set(value) {
            require(value > 0)
            var l = lst.back
            while (l != null && hashTable.size > value) {
                l.removeSelf()
                l = l.back
            }
            field = value
        }

    val size: Int
        get() = hashTable.size

    init {
        require(capacity > 0)

        fst.next = lst
    }

    fun get(k: K): V? {
        val node = hashTable[k] ?: return null

        node.removeSelf()
        fst.putAfter(node)

        return node.value
    }

    fun put(k: K, v: V): Pair<K, V>? {
        val newNode = NodeV(k, v)
        val oldNode = hashTable.replace(k, newNode)
        oldNode?.removeSelf()
        fst.putAfter(newNode)

        hashTable[k] = newNode
        if (hashTable.size > capacity) {
            val last = lst.back!! as NodeV<K, V>
            last.removeSelf()
            hashTable.remove(last.key)
            return last.asPair()
        }
        return null
    }
}