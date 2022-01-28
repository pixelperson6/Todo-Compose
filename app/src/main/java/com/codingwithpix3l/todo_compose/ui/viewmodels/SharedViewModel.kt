package com.codingwithpix3l.todo_compose.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwithpix3l.todo_compose.data.model.TodoTask
import com.codingwithpix3l.todo_compose.data.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SharedViewModel @Inject constructor(private val repository: TodoRepository) :ViewModel() {


    private val _allTasks = MutableStateFlow<List<TodoTask>>(emptyList())

    val allTasks :StateFlow<List<TodoTask>> = _allTasks

    fun getAllTasks(){
        viewModelScope.launch {
            repository.getAllData.collect {

                _allTasks.value = it

            }
        }
    }
}