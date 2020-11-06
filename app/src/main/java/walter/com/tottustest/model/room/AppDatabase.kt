package walter.com.tottustest.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import walter.com.tottustest.model.dao.MemberDao
import walter.com.tottustest.model.dao.TeamDao
import walter.com.tottustest.model.dao.UserDao
import walter.com.tottustest.model.entity.Member
import walter.com.tottustest.model.entity.Team
import walter.com.tottustest.model.entity.User

/**
 * Project name: TottusTest
 * Created by Walter Cojal on 11/4/20.
 */

@Database(entities = arrayOf(
    User::class,
    Team::class,
    Member::class
), version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun teamDao(): TeamDao
    abstract fun memberDao(): MemberDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null
        const val DATABASE_NAME = "tottusBd"

        fun newInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(AppDatabase::class.java) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "sineace_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }

}