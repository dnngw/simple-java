package service.impl;

import dao.impl.TaskJdbcDAO;
import model.Task;
import service.TaskService;

import java.util.List;

/*
* Task Service Implementation
* */
public class TaskServiceImpl implements TaskService {

    /*
    * DAO
    * */
    private TaskJdbcDAO dao;

    /*
    * Constructor
    * */
    public TaskServiceImpl(TaskJdbcDAO dao) {
        this.dao = dao;
    }

    /*
    * Get all task
    *
    * @return Task
    * */
    public List<Task> getAllTask() {
        return dao.findAll();
    }

    /*
    * Add task
    *
    * @param task Task
    * @return void
    * */
    public void addTask(Task task) {
        dao.insert(task);
    }


    /*
    * Update task
    *
    * @param task Task
    * @return void
    * */
    public void updateTask(Task task) {
        dao.update(task);
    }


    /*
    * Delete task
    *
    * @param id Id
    * @return void
    * */
    public void deleteTask(int id) {
        dao.delete(id);
    }

}
