package walter.com.tottustest.model.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import walter.com.tottustest.infraestructure.model.ApiError
import walter.com.tottustest.infraestructure.model.Result
import walter.com.tottustest.model.dao.UserDao
import walter.com.tottustest.model.entity.User
import java.lang.Exception

/**
 * Project name: TottusTest
 * Created by Walter Cojal on 11/5/20.
 */
class UserRepositoryImpl(private val dao: UserDao): IUserRepository {

    override fun addUser(user: User): Flow<Result<Long>> = flow {
        try {
            dao.insert(user).apply {
                emit(Result.Success(this))
            }
        } catch (e: Exception) {
            emit(Result.Error(ApiError(e.hashCode(), e.localizedMessage ?: "")))
        }
    }

    override fun findUserById(id: Int): LiveData<User> = dao.findById(id)
    override fun findUserByEmail(email: String): LiveData<User?> = dao.findByEmail(email)

}