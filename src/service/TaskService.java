package service;

import model.Task;

import java.util.List;

/*
* Task service
* */
public interface TaskService {
    /*
    * Get all task
    *
    * @return Task
    * */
    List<Task> getAllTask();

    /*
    * Add task
    *
    * @param task Task
    * @return void
    * */
    void addTask(Task task);

    /*
    * Update task
    *
    * @param task Task
    * @return void
    *
    * */
    void updateTask(Task task);

    /*
    * Delete task
    *
    * @param id Id
    * @return void
    * */
    void deleteTask(int id);

}
