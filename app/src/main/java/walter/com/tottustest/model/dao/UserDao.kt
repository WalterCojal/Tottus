package walter.com.tottustest.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import walter.com.tottustest.infraestructure.base.BaseDao
import walter.com.tottustest.model.entity.User

/**
 * Project name: TottusTest
 * Created by Walter Cojal on 11/4/20.
 */
@Dao
interface UserDao: BaseDao<User> {

    @Query("SELECT * FROM users WHERE id = :userId LIMIT 1")
    fun findById(userId: Int): LiveData<User>

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    fun findByEmail(email: String): LiveData<User?>

}