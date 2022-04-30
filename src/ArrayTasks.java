import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    public void reviewAll()
    {
        myTasksList.stream()
                .forEach(System.out::println);
        }
        public void byPriority(){
            boolean flag;
            byte choice=-1;

            BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Which priority of task u wish to see");
            do {
                flag = false;
                try {
                    System.out.println("1 - Planned");
                    System.out.println("2 - Urgent");
                    System.out.println("3 - Delegated");
                    System.out.println("4 - Trivial");
                    String s=br1.readLine();
                    if (s.equals("")) {
                        choice=0;
                    } else choice = Byte.parseByte(s);
                    if (choice < 0 || choice > 4) throw new MenuExceptions("Incorrect menu item");
                } catch (NumberFormatException | IOException e) {
                    System.out.println("Entered number is incorrect");
                    System.out.println(e.getMessage());
                    flag = true;
                } catch (MenuExceptions e1) {
                    System.out.println(e1.getMessage());
                    flag = true;
                }
            }  while (flag);
            switch (choice) {
                case 1:
                    myTasksList.stream()
                            .filter(x -> x.getPriority().equals("planned"))
                            .forEach(System.out::println);
                    System.out.println(" ");
                    break;
                case 2:
                    myTasksList.stream()
                            .filter(x -> x.getPriority().equals("urgent"))
                            .forEach(System.out::println);
                    System.out.println(" ");
                    break;
                case 3:
                    myTasksList.stream()
                            .filter(x -> x.getPriority().equals("delegated"))
                            .forEach(System.out::println);
                    System.out.println(" ");
                    break;
                case 4:
                    myTasksList.stream()
                            .filter(x -> x.getPriority().equals("trivial"))
                            .forEach(System.out::println);
                    System.out.println(" ");
                    break;
                default:
                    break;
            }
    }
    }

