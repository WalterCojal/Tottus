package walter.com.tottustest.model.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Project name: TottusTest
 * Created by Walter Cojal on 11/4/20.
 */
@Entity(tableName = "users")
data class User (
    @PrimaryKey(autoGenerate = true) var id: Int,
    var name: String,
    var lastName: String,
    var email: String,
    var password: String
): Parcelable {

    constructor(parcel: Parcel?): this (
        parcel?.readInt() ?: 0,
        parcel?.readString() ?: "",
        parcel?.readString() ?: "",
        parcel?.readString() ?: "",
        parcel?.readString() ?: ""
    )

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(id)
        dest?.writeString(name)
        dest?.writeString(lastName)
        dest?.writeString(email)
        dest?.writeString(password)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}