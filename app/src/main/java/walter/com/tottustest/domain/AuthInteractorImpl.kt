package walter.com.tottustest.domain

import androidx.lifecycle.asFlow
import kotlinx.coroutines.flow.Flow
import walter.com.tottustest.infraestructure.model.Result
import walter.com.tottustest.model.entity.User
import walter.com.tottustest.model.repository.IUserRepository

/**
 * Project name: TottusTest
 * Created by Walter Cojal on 11/5/20.
 */
class AuthInteractorImpl(private val repository: IUserRepository): IAuthInteractor {
    override fun findByEmail(email: String): Flow<User?> = repository.findUserByEmail(email).asFlow()
    override fun register(user: User): Flow<Result<Long>> = repository.addUser(user)
}