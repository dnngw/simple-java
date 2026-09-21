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
    Task findById(long id);

    /*
    * Insert new task
    *
    * @param task
    * */
    long insert(Task task);

    /*
    * Update specifict task
    *
    * @param task
    * */
    int update (Task task);

    /*
    * delete task by id
    *
    * @param id
    * */
    int delete(long id);

}
