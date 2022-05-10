import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskCreateUI {

    static void showUI(ArrayTasks myArrayTask) {
        TaskCreate taskCreate = new TaskCreate(myArrayTask.currentID, myArrayTask.getAuthor(), myArrayTask.getEmailAuthor());
        SendEmails emailSender = new SendEmails(myArrayTask.getEmailAuthor(), myArrayTask.getPassword(), myArrayTask.getAuthor());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("TASK CREATING: \n");


        while (true) { // Title
            System.out.println("Enter task title (5-200 symbols):");
            try {
                if (taskCreate.setTitle(bufferedReader.readLine())) break;
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

        while (true) { // Body
            System.out.println("Enter task description (20-500 symbols):");
            try {
                if (taskCreate.setBody(bufferedReader.readLine())) break;
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }

        while (true) { // Date
            System.out.println("Enter execution date:");
            System.out.println("Date format (yyyy-mm-dd)");
            try {
                if (taskCreate.setExecutDate(bufferedReader.readLine())) break;
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

        while (true) {
            System.out.println("Choose task priority:");
            System.out.println("(Enter number of priority | Or any symbols to set default value)");
            System.out.println("[1]-PLANNED (default) | [2]-URGENT | [3]-DELEGATED | [4]-TRIVIAL");
            try {
                if (taskCreate.setPriority(bufferedReader.readLine())) break;
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

        if(taskCreate.isDelegated()){
            while (true){
                System.out.println("Enter Assignee name (3-30 symbols):");
                try {
                    if (taskCreate.setAssignee(bufferedReader.readLine())) break;
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }

            while (true){
                System.out.println("Enter Assignee email:");
                try {
                    if (taskCreate.setEmail(bufferedReader.readLine())) break;
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        myArrayTask.addTask(taskCreate.task);

        System.out.println("\nYou just created a new task");
        System.out.println("\nTASK DETAILS: \n");
        System.out.println(taskCreate.task.viewAllDetails());
        System.out.println("\n");
    }
}
