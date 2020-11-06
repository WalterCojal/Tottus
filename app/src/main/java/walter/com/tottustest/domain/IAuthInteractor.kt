package walter.com.tottustest.domain

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import walter.com.tottustest.infraestructure.model.Result
import walter.com.tottustest.model.entity.User

/**
 * Project name: TottusTest
 * Created by Walter Cojal on 11/5/20.
 */
interface IAuthInteractor {

    fun findByEmail(email: String): Flow<User?>
    fun register(user: User): Flow<Result<Long>>

}