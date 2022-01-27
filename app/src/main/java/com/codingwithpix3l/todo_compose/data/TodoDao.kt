package com.codingwithpix3l.todo_compose.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import com.codingwithpix3l.todo_compose.data.model.TodoTask
import kotlinx.coroutines.flow.Flow


@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllData(): Flow<List<TodoTask>>

    @Query("SELECT * FROM todo_table WHERE id=:taskId")
    fun getTaskById(taskId:Int):Flow<TodoTask>

    @Delete
    suspend fun delete(todoTask: TodoTask)

    @Update
    suspend fun update(todoTask: TodoTask)

    @Query("DELETE FROM todo_table")
    suspend fun deleteAll()


    @Query("SELECT * FROM todo_table WHERE title LIKE :searchQuery OR disc LIKE :searchQuery")
    fun searchTask(searchQuery:String)


    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END")
    fun sortByLowPriority():Flow<List<TodoTask>>


    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END")
    fun sortByHighPriority():Flow<List<TodoTask>>

}