package dao.impl;

import dao.TaskDAO;
import model.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
* Task Jdbc DAO
* */
public class TaskJdbcDAO implements TaskDAO {
    /*
    * connection
    * */
    private Connection connection;

    /*
    * Constructor
    * */
    public TaskJdbcDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Task> findAll() {
        List<Task> data = new ArrayList<>();
        String getQuery = "SELECT * FROM Task";

        try(PreparedStatement statement = connection.prepareStatement(getQuery)){
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                int id = result.getInt("id");
                String value = result.getString("value");

                Task task = new Task(id, value);
                data.add(task);
            }

        }catch(SQLException e) {
            e.printStackTrace();

        }

        return data;
    }

    @Override
    public Task findById(int id) {
        String findByIdQuery = "SELECT * FROM Task WHERE id = ?";

        Task data = new Task();

        try(PreparedStatement statement = connection.prepareStatement(findByIdQuery)) {

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            while(result.next()) {
                data.setId(result.getInt("id"));
                data.setValue(result.getString("value"));

                return data;
            }

        return null;

        }catch(Exception e) {
            throw new RuntimeException("Something wrong", e);

        }
    }

    @Override
    public void insert(Task task) {

        String createQuery = "INSERT INTO Task (value) VALUES (?)";

        try(PreparedStatement statement = connection.prepareStatement(createQuery)) {

            statement.setString(1, task.getValue());

            statement.executeUpdate();

            System.out.println("Insert data succesfully");

        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Task task) {

        Task currentData = findById(task.getId());

        if(currentData == null) {
            throw new RuntimeException("Task with id : " + task.getId() + "not found");
        }

        String updateQuery = "UPDATE Task SET value = ? WHERE id = ?";

        try(PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setString(1, task.getValue());
            statement.setInt(2, task.getId());

            statement.executeUpdate();

            System.out.print("Data succesfully updated");

        }catch(SQLException e) {
            System.out.println("Data not found");
        }
    }

    @Override
    public void delete(int id) {

        Task checkData = findById(id);

        if (checkData == null) {
            throw new RuntimeException("Data with id = " + id + " not found");
        }

        String deleteQuery = "DELETE FROM Task WHERE id = ?";

        try(PreparedStatement statement = connection.prepareStatement(deleteQuery)) {

            statement.setInt(1, id);
            statement.executeUpdate();

            System.out.println("Data succesfully deleted");

        }catch(SQLException e) {
            e.printStackTrace();
        }

    }

}
