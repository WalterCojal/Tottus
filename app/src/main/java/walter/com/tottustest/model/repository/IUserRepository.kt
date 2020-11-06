package walter.com.tottustest.model.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import walter.com.tottustest.infraestructure.model.Result
import walter.com.tottustest.model.entity.User

/**
 * Project name: TottusTest
 * Created by Walter Cojal on 11/4/20.
 */
interface IUserRepository {

    fun addUser(user: User): Flow<Result<Long>>
    fun findUserById(id: Int): LiveData<User>
    fun findUserByEmail(email: String): LiveData<User?>

}