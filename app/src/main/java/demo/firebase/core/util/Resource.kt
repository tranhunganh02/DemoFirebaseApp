package demo.firebase.core.util

sealed class Resource<out R> {
    data class Success<out R>(val result: R): Resource<R>()
    data class Failure(val exception: Exception): Resource<Nothing>()
    object Loading: Resource<Nothing>()
}