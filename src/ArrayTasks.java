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
}
