package dao;

import model.Task;

import java.util.List;

/*
* Task DAO
* */
public interface TaskDAO {

    /*
    * Return all task list
    *
    * @return Task
    * */
    List<Task> findAll();

    /*
    * return task by id
    *
    * @return task
    * */
    Task findById(int id);

    /*
    * Insert new task
    *
    * @param task
    * */
    void insert(Task task);

    /*
    * Update specifict task
    *
    * @param task
    * */
    void update (Task task);

    /*
    * delete task by id
    *
    * @param id
    * */
    void delete(int id);

}
