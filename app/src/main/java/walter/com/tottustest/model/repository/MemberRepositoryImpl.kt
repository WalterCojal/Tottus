package walter.com.tottustest.model.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import walter.com.tottustest.infraestructure.model.ApiError
import walter.com.tottustest.infraestructure.model.Result
import walter.com.tottustest.model.dao.MemberDao
import walter.com.tottustest.model.entity.Member

/**
 * Project name: TottusTest
 * Created by Walter Cojal on 11/5/20.
 */
class MemberRepositoryImpl(private val dao: MemberDao): IMemberRepository {

    override fun insert(member: Member) = flow {
        try {
            dao.insert(member).apply {
                emit(Result.Success(this))
            }
        } catch (e: Exception) {
            emit(Result.Error(ApiError(0, e.localizedMessage ?: "")))
        }
    }

    override fun delete(member: Member) = flow {
        try {
            dao.delete(member).apply {
                emit(Result.Success(Any()))
            }
        } catch (e: Exception) {
            emit(Result.Error(ApiError(0, e.localizedMessage ?: "")))
        }
    }

    override fun getTeamMembers(teamId: Int): LiveData<List<Member>> = dao.getTeamMembers(teamId)
}