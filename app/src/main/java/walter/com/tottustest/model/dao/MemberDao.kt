package walter.com.tottustest.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import walter.com.tottustest.infraestructure.base.BaseDao
import walter.com.tottustest.infraestructure.model.Result
import walter.com.tottustest.model.entity.Member

/**
 * Project name: TottusTest
 * Created by Walter Cojal on 11/5/20.
 */
@Dao
interface MemberDao: BaseDao<Member> {

    @Query("SELECT * FROM members WHERE team_id = :teamId ORDER BY id LIMIT 100")
    fun getTeamMembers(teamId: Int): LiveData<List<Member>>

}