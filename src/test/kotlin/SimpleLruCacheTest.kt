import lru.LRUCache
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class SimpleLruCacheTest {
    @Test
    fun testNull() {
        val lruCache = LRUCache<Int, String>(4)
        assertEquals(0, lruCache.size)
        assertNull(lruCache.get(10))
    }

    @Test
    fun testOnly() {
        val lruCache = LRUCache<Int, String>(1)
        lruCache.put(1, "one")
        lruCache.put(2, "one else")
        assertEquals(1, lruCache.size)
        assertNull(lruCache.get(1))
    }

    @Test
    fun testLimit() {
        val lruCache = LRUCache<Int, String>(4)
        lruCache.put(1, "a")
        lruCache.put(2, "b")
        lruCache.put(3, "c")
        lruCache.put(4, "d")
        lruCache.put(5, "e")
        assertEquals(4, lruCache.capacity)
        assertEquals(4, lruCache.size)
        assertNull(lruCache.get(1))
    }

    @Test
    fun testUpdate() {
        val lruCache = LRUCache<Int, String>(2)
        lruCache.put(1, "a")
        lruCache.put(2, "b")
        lruCache.get(1)
        lruCache.put(3, "c")
        assertNotNull(lruCache.get(1))
    }

    @Test
    fun testChangeCapacity() {
        val lruCache = LRUCache<Int, String>(2)
        lruCache.put(1, "a")
        lruCache.put(2, "b")
        lruCache.put(3, "c")
        assertEquals(2, lruCache.size)
        lruCache.capacity = 3
        lruCache.put(4, "d")
        assertEquals(3, lruCache.size)
    }

    @Test
    fun testChange() {
        val lruCache = LRUCache<Int, String>(2)
        lruCache.put(1, "a")
        lruCache.put(2, "b")
        lruCache.put(1, "русский текст")
        assertEquals("русский текст", lruCache.get(1))
    }
}