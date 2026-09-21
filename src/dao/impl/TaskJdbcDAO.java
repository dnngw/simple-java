package dao.impl;

import dao.TaskDAO;
import model.Task;

import java.sql.*;
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
    public Task findById(long id) {
        String findByIdQuery = "SELECT * FROM Task WHERE id = ?";

        Task data = new Task();

        try(PreparedStatement statement = connection.prepareStatement(findByIdQuery)) {

            statement.setLong(1, id);

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
    public long insert(Task task) {

        String createQuery = "INSERT INTO Task (value) VALUES (?)";

        try(PreparedStatement statement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, task.getValue());

            int rowsAffected = statement.executeUpdate();

            if(rowsAffected == 0 ) {
                throw new SQLException("Failed to insert the data!");
            }

            try(ResultSet result = statement.getGeneratedKeys()) {
                if(result.next()) {
                    return result.getLong(1);
                } else {
                    throw new SQLException("Insert failed, ID cannot be generated");
                }
            }

        }catch(SQLException e) {
            throw new RuntimeException("Failed insert task", e);
        }
    }

    @Override
    public int update(Task task) {

        Task currentData = findById(task.getId());

        if(currentData == null) {
            throw new RuntimeException("Task with id : " + task.getId() + " not found");
        }

        String updateQuery = "UPDATE Task SET value = ? WHERE id = ?";

        try(PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setString(1, task.getValue());
            statement.setLong(2, task.getId());

            int rowsAffected = statement.executeUpdate();

            return rowsAffected;

        }catch(SQLException e) {
            throw new RuntimeException("Failed to update task", e);
        }
    }

    @Override
    public int delete(long id) {

        Task checkData = findById(id);

        if (checkData == null) {
            throw new RuntimeException("Data with id = " + id + " not found");
        }

        String deleteQuery = "DELETE FROM Task WHERE id = ?";

        try(PreparedStatement statement = connection.prepareStatement(deleteQuery)) {

            statement.setLong(1, id);
            int rowsAffected = statement.executeUpdate();

            return rowsAffected;

        }catch(SQLException e) {
            throw new RuntimeException("Failed to delete task", e);
        }

    }

}
