import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskCreateUI {
    static void showUI(ArrayTasks myArrayTask) throws IOException {
        TaskCreate taskCreate = new TaskCreate();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("TASK CREATING: \n");

        while (true) { // Title
            System.out.println("Enter task title:");
            if (taskCreate.setTitle(bufferedReader.readLine())) break;
        }

        while (true) { // Description
            System.out.println("Enter task description:");
            if (taskCreate.setDescription(bufferedReader.readLine())) break;
        }

        while (true) { // Date
            System.out.println("Enter execution date:");
            System.out.println("Date format (yyyy-mm-dd)");
            if (taskCreate.setExecutDate(bufferedReader.readLine())) break;
        }
    }
}
