package demo.firebase.feature.auth.data.firebase

import com.google.firebase.auth.FirebaseUser
import demo.firebase.core.util.Resource

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signup(name: String, email: String, password: String): Resource<FirebaseUser>
    fun logout()
}