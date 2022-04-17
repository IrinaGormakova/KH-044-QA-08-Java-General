import java.io.Serializable;
import java.util.ArrayList;

public class ArrayTasks implements Serializable {
    ArrayList<Task> myTasksList = new ArrayList<>();
    public int currentID = 0;

    public void addTask (){
        Task newTask=new Task(currentID);
        System.out.println("New task ID "+newTask.getID()+" was created");
        newTask.changePriority();
       // newTask.changeEmail();

        //...continue
        myTasksList.add(newTask);
        currentID++;
    }
    public void ReviewAllTasks(){
        Task reviewAll = new Task(currentID);
        for(int i = 0; i < reviewAll.getID(); i++) {
            System.out.println(reviewAll.getID());
        }
        myTasksList.add(reviewAll);
    }
    public void ReviewTasksbyPriority(){
        Task byPriority = new Task(currentID);
        for(int i = 0; i < byPriority.getID(); i++) {
            System.out.println(byPriority.getPriority());
        }
        myTasksList.add(byPriority);
    }
}
