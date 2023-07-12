package kr.co.move.part4plus.movieapp.library.storage.prefs

import javax.inject.Inject

class InMemoryStorageProvider @Inject constructor() : StorageProvider {

    private val cache = mutableMapOf<String, String>()

    override
    fun save(key: String, value: String): Boolean {
        cache[key] = value
        return true
    }

    override
    fun get(key: String, default: String?): String? {
        return cache[key] ?: default
    }

    override
    fun remove(key: String): Boolean {
        cache.remove(key)
        return true
    }

    override
    fun clear() {
        cache.clear()
    }
}
