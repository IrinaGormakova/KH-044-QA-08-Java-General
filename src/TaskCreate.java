import java.time.LocalDate;

public class TaskCreate {
    ArrayTasks TasksList;
    Task task;

//    private String title, description;

    public TaskCreate() {
        task = new Task(111);
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

    boolean setDescription(String description){
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



}
