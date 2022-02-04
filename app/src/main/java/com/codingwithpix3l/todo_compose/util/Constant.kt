package com.codingwithpix3l.todo_compose.util

object Constant {
    const val DATABASE_TABLE = "todo_table"
    const val DATABASE_NAME = "todo_database"

    const val LIST_ARGUMENT_KEY = "action"
    const val TASK_ARGUMENT_KEY = "taskId"

    const val LIST_SCREEN = "list/{${LIST_ARGUMENT_KEY}}"
    const val TASK_SCREEN = "task/{${TASK_ARGUMENT_KEY}}"

    const val MAX_TITLE_LENGTH = 20



}