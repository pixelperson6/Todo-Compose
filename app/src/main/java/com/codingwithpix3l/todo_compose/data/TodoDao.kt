package com.codingwithpix3l.todo_compose.data

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import com.codingwithpix3l.todo_compose.data.model.TodoTask
import kotlinx.coroutines.flow.Flow


@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllData(): Flow<List<TodoTask>>

    @Query("SELECT * FROM todo_table WHERE id=:taskId")
    fun getTaskById(taskId:Int):Flow<TodoTask>

    @Insert(onConflict = IGNORE)
    suspend fun addTask(todoTask: TodoTask)

    @Delete
    suspend fun deleteTask(todoTask: TodoTask)

    @Update
    suspend fun updateTask(todoTask: TodoTask)

    @Query("DELETE FROM todo_table")
    suspend fun deleteAll()


    @Query("SELECT * FROM todo_table WHERE title LIKE :searchQuery OR disc LIKE :searchQuery")
    fun searchTask(searchQuery:String):Flow<List<TodoTask>>


    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END")
    fun sortByLowPriority():Flow<List<TodoTask>>


    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END")
    fun sortByHighPriority():Flow<List<TodoTask>>

}