import java.time.LocalDate;

public class TaskCreate {
    Task task;

    public TaskCreate(int currentID, String assignee, String email) {
        this.task = new Task(currentID, assignee, email);
    }


    boolean setTitle(String title) {
        title = title.trim();
        if (!title.matches(".{5,200}")) {
            System.out.println("! Title should be 5-200 symbols");
            return false;
        }
        task.setTitle(title);
        return true;
    }

    boolean setBody(String body) {
        body = body.trim();
        if (!body.matches(".{20,500}")) {
            System.out.println("! Description should be 20-500 symbols");
            return false;
        }
        task.setBody(body);
        return true;
    }

    boolean setExecutDate(String dateStr) {
        dateStr = dateStr.trim();
        if (!dateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("! Wrong date format");
            return false;
        }
        LocalDate executDate;
        try {
            executDate = LocalDate.parse(dateStr);
        } catch (Exception e) {
            System.out.println("! Date format error");
            System.out.println(e.getMessage());
            return false;
        }
        if (executDate.isBefore(LocalDate.now())) {
            System.out.println("! Execution date can`t be less than current date");
            return false;
        }

        task.setExecutionDate(executDate);
        return true;
    }

    boolean setPriority(String priorityNumber) {
        TaskPriority priority;
        int priorityInt;
        try {
            priorityInt = Integer.parseInt(priorityNumber);
        } catch (Exception e) {
            priorityInt = 0; // 0 = choose default value if catch exception
        }

        switch (priorityInt) {
            case 1:
                priority = TaskPriority.PLANNED;
                break;
            case 2:
                priority = TaskPriority.URGENT;
                break;
            case 3:
                priority = TaskPriority.DELEGATED;
                break;
            case 4:
                priority = TaskPriority.TRIVIAL;
                break;
            default:
                priority = task.getPriority();
                break;
        }
        task.setPriority(priority);
        System.out.println("Task priority set: " + priority);
        return true;
    }

    TaskPriority getPriority() {
        return task.getPriority();
    }

    boolean isDelegated() {
        if (task.getPriority() == TaskPriority.DELEGATED) return true;
        return false;
    }

    boolean setAssignee(String assigneeName) {
        task.setAssignee(assigneeName);
        System.out.println("Assignee has been changed successfully");
        if (!task.getPriority().toString().equals("Delegated")) {
            task.setPriority(TaskPriority.DELEGATED);
            System.out.println("Task priority has been changed to DELEGATED automatically");
        }
        System.out.println("Please, set correct email for Assignee " + task.getAssignee());

//add method setEmail

        return true;
    }

}
