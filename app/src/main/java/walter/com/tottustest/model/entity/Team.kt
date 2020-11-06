package walter.com.tottustest.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Project name: TottusTest
 * Created by Walter Cojal on 11/5/20.
 */
@Entity(tableName = "teams")
data class Team(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var name: String,
    var description: String
)