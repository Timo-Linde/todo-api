# todo-api

The target of this project is to implement a **TODO** api.
A **TODO** has a name and description.
Each **TODO** can have a list of **TASKS** associated with a **TODO**.
Each **TASK** has only a name.
The API provides a mechanism to add and remove **TASKs** from the **TODO** as well as to update individual **TASKS**.

## REST API Like:
* CREATE TODO: POST /todos
* READ TODO: GET /todos/:id
* UPDATE TODO: PATCH /todos/:id
* DELETE TODO: DELETE /todos/:id
* LIST TODOS: GET /todos

* CREATE TASK: POST /todos/:id/tasks
* READ TASK: GET /todos/:id/tasks/:task-id
* UPDATE TASK: PATCH /todos/:id/tasks/:task-id
* DELETE TASK: DELETE /todos/:id/taks/:task-id
* LIST TASKS: GET /todos/:id/tasks

