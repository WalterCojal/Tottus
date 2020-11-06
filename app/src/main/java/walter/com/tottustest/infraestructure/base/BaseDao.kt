package walter.com.tottustest.infraestructure.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

/**
 * Project name: TottusTest
 * Created by Walter Cojal on 11/4/20.
 */
interface BaseDao<T> {

    @Insert
    fun insert(obj: T) : Long
    @Insert
    fun insert(vararg obj: T)
    @Update
    fun update(obj: T)
    @Delete
    fun delete(obj: T)
}