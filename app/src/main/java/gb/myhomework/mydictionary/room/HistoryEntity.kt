package gb.myhomework.mydictionary.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = arrayOf(Index(value = arrayOf("word"), unique = true)))
class HistoryEntity(
    @field:PrimaryKey
    @field:ColumnInfo(name = "word")
    val word: String,
    @field:ColumnInfo(name = "description")
    val description: String?
)
