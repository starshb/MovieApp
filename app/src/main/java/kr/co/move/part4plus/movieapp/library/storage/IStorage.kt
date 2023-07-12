package kr.co.move.part4plus.movieapp.library.storage

interface IStorage {

    fun <T> save(key: String, value: T): Boolean
    fun exists(key: String): Boolean
    fun <T> get(key: String): T?
    fun remove(key: String): Boolean

    fun <T> save(key: String, value: T, callback: (Boolean) -> Unit)
    fun <T> get(key: String, callback: (data: T?) -> Unit)
    fun remove(key: String, callback: (Boolean) -> Unit)
    fun clear(): Boolean
}
