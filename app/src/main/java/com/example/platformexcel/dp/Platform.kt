package com.example.platformexcel.dp

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity("users")
class Platform(
    @PrimaryKey(autoGenerate = true)
    val idUsers: Int =0,
    @ColumnInfo("name_users")
    val name: String
):Parcelable