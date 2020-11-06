package walter.com.tottustest.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Project name: TottusTest
 * Created by Walter Cojal on 11/5/20.
 */
@Entity(tableName = "members")
data class Member (
    @PrimaryKey(autoGenerate = true) var id: Int,
    var team_id: Int,
    var name: String,
    var email: String
)