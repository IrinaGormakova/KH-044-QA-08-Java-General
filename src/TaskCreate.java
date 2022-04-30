import java.time.LocalDate;

public class TaskCreate {
    Task task;

    public TaskCreate(int currentID) {
        this.task = new Task(currentID);
    }

    boolean setTitle(String title){
        title = title.trim();
        if ( !title.matches(".{5,200}") ) {
            System.out.println("! Title should be 5 - 200 symbols");
            return false;
        }
        task.setTitle(title);
        return true;
    }

    boolean setDescription(String description){ // !!! SetBody
        description = description.trim();
        if ( !description.matches(".{20,500}") ) {
            System.out.println("! Description should be 20 - 500 symbols");
            return false;
        }
        task.setBody(description);
        return true;
    }

    boolean setExecutDate(String dateStr){
        dateStr = dateStr.trim();
        if ( !dateStr.matches("\\d{4}-\\d{2}-\\d{2}") ) {
            System.out.println("! Wrong date format");
            return false;
        }
        LocalDate executDate;
        try {
            executDate = LocalDate.parse(dateStr);
        }
        catch (Exception e){
            System.out.println("! Date format error");
            System.out.println(e.getMessage());
            return false;
        }
        if(executDate.isBefore(LocalDate.now())){
            System.out.println("! Execution date can`t be less than current date");
            return false;
        }

        task.setExecutionDate(executDate);
        return true;
    }

    boolean setPriority( String priorityNumber ){
        TaskPriority priority;
        int priorityInt;
        try {
            priorityInt = Integer.parseInt(priorityNumber); // !! Exception
        }
        catch (Exception e){
            priorityInt = 1;
        }

        switch (priorityInt){
            case 1 :
                priority = TaskPriority.PLANNED;
                break;
            case 2 :
                priority = TaskPriority.URGENT;
                break;
            case 3 :
                priority = TaskPriority.DELEGATED;
                break;
            case 4 :
                priority = TaskPriority.TRIVIAL;
                break;
            default:
                priority = TaskPriority.PLANNED;
        }
        task.setPriority(priority);
        System.out.println("Task priority set: "+priority);
        return true;
    }
}
