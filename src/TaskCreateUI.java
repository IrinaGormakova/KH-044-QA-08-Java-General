import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskCreateUI {
    static void showUI(ArrayTasks myArrayTask)  {
        TaskCreate taskCreate = new TaskCreate(myArrayTask.currentID);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("TASK CREATING: \n");

        try {
            while (true) { // Title
                System.out.println("Enter task title (5-200 symbols):");
                if (taskCreate.setTitle(bufferedReader.readLine())) break;
            }

            while (true) { // Body
                System.out.println("Enter task description (20-500 symbols):");
                if (taskCreate.setBody(bufferedReader.readLine())) break;
            }

            while (true) { // Date
                System.out.println("Enter execution date:");
                System.out.println("Date format (yyyy-mm-dd)");
                if (taskCreate.setExecutDate(bufferedReader.readLine())) break;
            }

            while (true) {
                System.out.println("Choose task priority:");
                System.out.println("(Enter number of priority | Or any symbols to set default value)");
                System.out.println("[1]-PLANNED (default) | [2]-URGENT | [3]-DELEGATED | [4]-TRIVIAL");
                if (taskCreate.setPriority(bufferedReader.readLine())) break;
            }

            myArrayTask.addTask(taskCreate.task);
        }
        catch (IOException IOex){
            System.out.println("Something happened, New task did not create");
            System.out.println(IOex.getMessage());
        }
    }
}
