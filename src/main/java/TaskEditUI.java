import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TaskEditUI {
    public static void showByIDMenu(Task myTask, SendEmails myEmailSender, ArrayList<Task> currentList) {
        boolean flag;
        byte choice = 0;

        TaskCreate taskEdit = new TaskCreate(myTask);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            flag = false;

            try {
                System.out.println("What do you want to change in task ID " + myTask.getID() + "?");
                System.out.println("1 - Change priority");
                System.out.println("2 - Change execution data");
                System.out.println("3 - Change body of task");
                System.out.println("4 - Change title");
                System.out.println("5 - Change status");
                System.out.println("6 - Change assignee");
                System.out.println("7 - Delete task");
                System.out.println("8 - Exit");
                choice = Byte.parseByte(br.readLine());
                if (choice < 1 || choice > 8) throw new MenuExceptions("Incorrect menu item");
            } catch (NumberFormatException | IOException e) {
                System.out.println("Entered number is incorrect");
                System.out.println(e.getMessage());
                flag = true;
            } catch (MenuExceptions e1) {
                System.out.println(e1.getMessage());
                flag = true;
            }
            switch (choice) {
                case 1:
                    System.out.println("Current priority: " + myTask.getPriority());
                    while (true) {
                        System.out.println("Choose task priority:");
                        System.out.println("(Enter number of priority)");
                        System.out.println("[1]-PLANNED | [2]-URGENT | [3]-DELEGATED | [4]-TRIVIAL");
                        try {
                            if (taskEdit.setPriority(br.readLine())) break;
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 2:
                    System.out.println("Current execution date: " + myTask.getExecutionDate());
                    while (true) { // Date
                        System.out.println("Enter execution date:");
                        System.out.println("Date format (yyyy-mm-dd)");
                        try {
                            if (taskEdit.setExecutDate(br.readLine())) break;
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 3:
                    System.out.println("Current task description: " + myTask.getBody());
                    while (true) { // Description
                        System.out.println("Enter task description:");
                        try {
                            if (taskEdit.setBody(br.readLine())) break;
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 4:
                    System.out.println("Current task title: " + myTask.getTitle());
                    while (true) { // Title
                        System.out.println("Enter task title:");
                        try {
                            if (taskEdit.setTitle(br.readLine())) break;
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 5:
                    System.out.println("Current task status: " + myTask.getStatus());
                    while (true) {
                        System.out.println("Choose task status:");
                        System.out.println("(Enter number of status)");
                        System.out.println("[1]-NEW | [2]-IN_PROGRESS | [3]-DONE | [4]-CLOSED");
                        try {
                            if (taskEdit.setStatus(br.readLine())) break;
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 6:
                    System.out.println("Current assignee name:" + myTask.getAssignee());
                    while (true) { // Assignee
                        System.out.println("Enter Assignee name (3-30 symbols):");
                        try {
                            if (taskEdit.setAssignee(br.readLine())) break;
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    System.out.println("Current assignee's email:" + myTask.getEmail());
                    while (true) { // Email
                        System.out.println("Enter assignee's email or press ENTER to continue without changing:");
                        try {
                            String s = br.readLine();
                            if (s.equals("") || taskEdit.setEmail(s)) break;
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    myEmailSender.sendNotification(myTask);
                    break;
                case 7:
                    System.out.println("Deleting task...");
                    if (currentList.remove(myTask)) {
                        System.out.println("Task was deleted successfully");
                    } else {
                        System.out.println("Something went wrong");
                    }
                    break;
                default:
                    break;
            }// end switch
        } while (flag || choice != 8);

    }
}
