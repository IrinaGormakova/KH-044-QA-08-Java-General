import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskCreateUI {
    static void showUI(ArrayTasks myArrayTask) throws IOException {
        TaskCreate taskCreate = new TaskCreate(myArrayTask.currentID, myArrayTask.getAuthor(), myArrayTask.getEmailAuthor());
        SendEmails emailSender = new SendEmails(myArrayTask.getEmailAuthor(), myArrayTask.getPassword(), myArrayTask.getPassword());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("TASK CREATING: \n");

        while (true) { // Title
            System.out.println("Enter task title:");
            if (taskCreate.setTitle(bufferedReader.readLine())) break;
        }

        while (true) { // Description
            System.out.println("Enter task description:");
            if (taskCreate.setBody(bufferedReader.readLine())) break;
        }

        while (true) { // Date
            System.out.println("Enter execution date:");
            System.out.println("Date format (yyyy-mm-dd)");
            if (taskCreate.setExecutDate(bufferedReader.readLine())) break;
        }

        while (true){
            System.out.println("Choose task priority:");
            System.out.println("(Enter number of priority)");
            System.out.println("[1]-PLANNED (default) | [2]-URGENT | [3]-DELEGATED | [4]-TRIVIAL");
            if (taskCreate.setPriority(bufferedReader.readLine())) break;
        }
    }
}
