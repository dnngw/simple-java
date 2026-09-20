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
            System.out.println(e);

        }catch(IOException e) {
            System.out.println(e);
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
                    case 1:
                        System.out.print("Input new task : ");
                        task.setValue(scanner.nextLine());
                        service.addTask(task);

                        break;

                    case 2:
                        System.out.println("List of task");
                        for(Task data : service.getAllTask()) {
                                System.out.print("id : " + data.getId() + " ");
                                System.out.println("task : " + data.getValue());
                        }
                        break;

                    case 3:
                        System.out.print("input task id : ");
                        task.setId(scanner.nextInt());

                        scanner.nextLine();

                        System.out.print("input new value : ");
                        task.setValue(scanner.nextLine());

                        try{
                            service.updateTask(task);
                        }catch(Exception e) {
                            System.out.println(e);
                        }

                        break;

                    case 4:
                        System.out.print("input task id :");
                        task.setId(scanner.nextInt());

                        try {
                            service.deleteTask(task.getId());
                        }catch(Exception e) {
                            System.out.println(e);
                        }

                        scanner.nextLine();

                        break;

                    case 5:
                        System.out.println("Program End!");
                        running = false;
                        break;

                    default:
                        System.out.println("Option not available!");
                }

            }catch(NumberFormatException e) {
                System.out.println("Value invalid!");
            }

        }

    }

}