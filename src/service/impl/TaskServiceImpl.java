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
    public long addTask(Task task) {

        long result = dao.insert(task);

        if (result == 0) {
         throw new RuntimeException("Data failed to insert");
        }

        return result;
    }


    /*
    * Update task
    *
    * @param task Task
    * @return void
    * */
    public int updateTask(Task task) {

        int result = dao.update(task);

        if(result == 0){
            throw new RuntimeException("Data failed to update");
        }

        return result;
    }


    /*
    * Delete task
    *
    * @param id Id
    * @return void
    * */
    public int deleteTask(long id) {

        int result = dao.delete(id);

        if(result == 0) {
            throw new RuntimeException("Failed to delete the data");
        }

        return result;
    }

}
