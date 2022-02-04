package com.codingwithpix3l.todo_compose.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwithpix3l.todo_compose.data.model.Priority
import com.codingwithpix3l.todo_compose.data.model.TodoTask
import com.codingwithpix3l.todo_compose.data.repository.TodoRepository
import com.codingwithpix3l.todo_compose.util.Constant.MAX_TITLE_LENGTH
import com.codingwithpix3l.todo_compose.util.RequestState
import com.codingwithpix3l.todo_compose.util.SearchBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class SharedViewModel @Inject constructor(private val repository: TodoRepository) : ViewModel() {


    val id: MutableState<Int> = mutableStateOf(0)
    val title:MutableState<String> = mutableStateOf("")
    val description:MutableState<String> = mutableStateOf("")
    val priority:MutableState<Priority> = mutableStateOf(Priority.LOW)



    val searchBarState : MutableState<SearchBarState> =
        mutableStateOf(SearchBarState.CLOSED)

    val searchTextState : MutableState<String> = mutableStateOf("")

    private val _allTasks = MutableStateFlow<RequestState<List<TodoTask>>>(RequestState.Idle)

    val allTasks: StateFlow<RequestState<List<TodoTask>>> = _allTasks

    fun getAllTasks() {
        _allTasks.value = RequestState.Loading
        try {
            viewModelScope.launch {
                repository.getAllData.collect {
                    _allTasks.value = RequestState.Success(it)
                }
            }
        }catch (e:Exception){
            _allTasks.value = RequestState.Error(e)
        }
    }
    private val _selectedTask :MutableStateFlow<TodoTask?> = MutableStateFlow(null)

    val selectedTask:StateFlow<TodoTask?> = _selectedTask

    fun getSelectedTask(taskId:Int){
        viewModelScope.launch {
            repository.getTaskById(taskId).collect { task->
                _selectedTask.value = task
            }
        }
    }

    fun updateTaskFields(selectedTask:TodoTask?){
        if (selectedTask !=null){
            id.value=selectedTask.id
            title.value=selectedTask.title
            description.value=selectedTask.disc
            priority.value=selectedTask.priority
        }else{
            id.value=0
            title.value=""
            description.value=""
            priority.value=Priority.LOW
        }
    }

    fun updateTitle(newTitle:String){
        if (newTitle.length <= MAX_TITLE_LENGTH){
            title.value = newTitle
        }
    }

}