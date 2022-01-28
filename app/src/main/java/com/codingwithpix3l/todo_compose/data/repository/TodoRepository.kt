package com.codingwithpix3l.todo_compose.data.repository

import com.codingwithpix3l.todo_compose.data.TodoDao
import com.codingwithpix3l.todo_compose.data.model.TodoTask
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class TodoRepository @Inject constructor(private val todoDao: TodoDao) {

    val getAllData:Flow<List<TodoTask>> = todoDao.getAllData()
    val sortByLowPriority:Flow<List<TodoTask>> = todoDao.sortByLowPriority()
    val sortByHighPriority:Flow<List<TodoTask>> = todoDao.sortByHighPriority()

    fun getTaskById(tasId:Int) = todoDao.getTaskById(taskId = tasId)

    fun searchDatabase(searchQuery:String) = todoDao.searchTask(searchQuery = searchQuery)

    suspend fun addTask(todoTask: TodoTask) {
        todoDao.addTask(todoTask)
    }

    suspend fun updateTask(todoTask: TodoTask){
        todoDao.updateTask(todoTask)
    }

    suspend fun deleteTask(todoTask: TodoTask){
        todoDao.deleteTask(todoTask)
    }

    suspend fun deleteAll(){
        todoDao.deleteAll()
    }



}