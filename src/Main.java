import dao.impl.TaskJdbcDAO;
import model.Task;
import service.impl.TaskServiceImpl;
import util.DBConnection;
import db.MigrationRunner;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/*
* Main
* */
public class Main {
    public static void main(String[] args) {
        // Create db connection
        Connection connection = DBConnection.getConnection();

        // Run the db migration
        try {
            MigrationRunner.run(connection);
        }catch(SQLException e) {
            System.out.println("Failed to running the migration " + e);

        }catch(IOException e) {
            System.out.println("Migration file not found" + e);
        }

        /*
        * Create scanner for input
        * */
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while(running) {
            System.out.println("========== CLI PROGRAM ==========");
            System.out.println("== 1.INPUT TASK                ==");
            System.out.println("== 2.GET ALL TASK              ==");
            System.out.println("== 3.UPDATE TASK               ==");
            System.out.println("== 4.DELETE TASK               ==");
            System.out.println("== 5.CLOSE                     ==");
            System.out.println("=================================");
            System.out.println();

            try {

                TaskJdbcDAO dao = new TaskJdbcDAO(connection);
                TaskServiceImpl service = new TaskServiceImpl(dao);


                System.out.print("Enter the option : ");
                String input = scanner.nextLine();

                int value = Integer.parseInt(input);

                Task task = new Task();

                switch(value) {
                    // Input Task
                    case 1:
                        System.out.print("Input new task : ");
                        task.setValue(scanner.nextLine());
                        try{
                            long result = service.addTask(task);
                            task.setId(result);
                            System.out.println("data input successfully " + task);
                        }catch(Exception e) {
                            System.out.println("Failed to insert data" + e);
                        }

                        break;

                    // Get all task
                    case 2:
                        System.out.println("List of task");
                        try{
                            for(Task data : service.getAllTask()) {
                                System.out.print("id : " + data.getId() + " ");
                                System.out.println("task : " + data.getValue());
                            }
                        }catch (Exception e) {
                            System.out.println("Failed to retrive data" + e);
                        }

                        break;

                     // Update task
                    case 3:
                        System.out.print("input task id : ");
                        String inputId = scanner.nextLine();

                        try {
                            long convertValue = Long.parseLong(inputId);
                            task.setId(convertValue);

                        }catch(NumberFormatException e) {
                            System.out.println("Input invalid " + e.getMessage());
                            continue;
                        }


                        System.out.print("input new value : ");
                        task.setValue(scanner.nextLine());

                        try{
                            int result = service.updateTask(task);
                            System.out.println("Rows affected " + result);

                        }catch(Exception e) {
                            System.out.println(e);
                        }

                        break;

                    // Delete task
                    case 4:
                        System.out.print("input task id :");
                        String deleteId = scanner.nextLine();

                        try{
                            long convertDeleteId = Long.parseLong(deleteId);
                            task.setId(convertDeleteId);
                        }catch(NumberFormatException e) {
                            System.out.println("Invalid input " + e.getMessage());
                            continue;
                        }

                        try {
                            int result =service.deleteTask(task.getId());
                            System.out.println("Rows affected " + result);

                        }catch(Exception e) {
                            System.out.println(e);
                        }

                        break;

                    // Close program
                    case 5:
                        System.out.println("Program End!");
                        running = false;
                        break;

                    // Invalid option
                    default:
                        System.out.println("Option not available!");
                }

            }catch(NumberFormatException e) {
                System.out.println("Value invalid " + e.getMessage());
            }

        }

    }

}