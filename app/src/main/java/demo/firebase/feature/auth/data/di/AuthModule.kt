package demo.firebase.feature.auth.data.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import demo.firebase.feature.auth.data.firebase.AuthRepository
import demo.firebase.feature.auth.data.repo.AuthRepositoryImpl

@InstallIn(SingletonComponent::class)
@Module
class AuthModule {
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl
}