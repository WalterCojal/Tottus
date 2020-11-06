package walter.com.tottustest.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import walter.com.tottustest.infraestructure.base.BaseDao
import walter.com.tottustest.model.entity.Team

/**
 * Project name: TottusTest
 * Created by Walter Cojal on 11/5/20.
 */
@Dao
interface TeamDao: BaseDao<Team> {

    @Query("SELECT * FROM teams LIMIT 1000")
    fun getAllList(): LiveData<List<Team>>

}