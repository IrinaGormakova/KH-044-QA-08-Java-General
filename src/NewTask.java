

public class NewTask {
    public static void createNewTask() {

        Task newTask=new Task();
        System.out.println("New task ID "+newTask.getID()+" was created");
        newTask.changePriority();
        newTask.changeEmail();
    }//createNewTask
}
