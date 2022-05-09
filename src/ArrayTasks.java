import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;

public class ArrayTasks implements Serializable {
    ArrayList<Task> myTasksList = new ArrayList<>();
    public int currentID = 0;
    private String author = "Irina Gormakova";
    private String emailAuthor = "gormakova.ira@gmail.com";
    private String password = "*********";

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEmailAuthor() {
        return emailAuthor;
    }

    public void setEmailAuthor(String emailAuthor) {
        this.emailAuthor = emailAuthor;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addTask() {


        currentID++;
    }

    public void reviewAll() {
        myTasksList
                .forEach(System.out::println);
    }

    public void byPriority() {
        boolean flag;
        byte choice = -1;

        BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Which priority of task u wish to see");
        do {
            flag = false;
            try {
                System.out.println("1 - Planned");
                System.out.println("2 - Urgent");
                System.out.println("3 - Delegated");
                System.out.println("4 - Trivial");
                choice = Byte.parseByte(br1.readLine());
                if (choice < 1 || choice > 4) throw new MenuExceptions("Incorrect menu item");
            } catch (NumberFormatException | IOException e) {
                System.out.println("Entered number is incorrect");
                System.out.println(e.getMessage());
                flag = true;
            } catch (MenuExceptions e1) {
                System.out.println(e1.getMessage());
                flag = true;
            }
        } while (flag);
        switch (choice) {
            case 1:
                myTasksList.stream()
                        .filter(x -> x.getPriority().equals(TaskPriority.PLANNED))
                        .forEach(System.out::println);
                System.out.println(" ");
                break;
            case 2:
                myTasksList.stream()
                        .filter(x -> x.getPriority().equals(TaskPriority.URGENT))
                        .forEach(System.out::println);
                System.out.println(" ");
                break;
            case 3:
                myTasksList.stream()
                        .filter(x -> x.getPriority().equals(TaskPriority.DELEGATED))
                        .forEach(System.out::println);
                System.out.println(" ");
                break;
            case 4:
                myTasksList.stream()
                        .filter(x -> x.getPriority().equals(TaskPriority.TRIVIAL))
                        .forEach(System.out::println);
                System.out.println(" ");
                break;
            default:
                break;
        }
    }//end byPriority()

    public Task selectByID() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean flag;

        System.out.println("Full task list:");
        myTasksList
                .forEach(System.out::println);
        int modifiedID;
        do {
            flag = false;
            System.out.println("Please, enter ID of task you want to modify or press ENTER to continue without changing");
            try {
                if (!br.readLine().equals("")) {
                    modifiedID = Integer.parseInt(br.readLine());
                } else return null;
            } catch (IOException e) {
                System.out.println(e.getMessage());
                modifiedID = 0;
            }
            int finalModifiedID = modifiedID;
            if (myTasksList.stream().noneMatch(x -> x.getID() == finalModifiedID)) {
                System.out.println("Task ID " + finalModifiedID + " wasn't found");
                flag = true;
            }
        } while(flag);
        int finalModifiedID1 = modifiedID;
        return myTasksList.stream().filter(x -> x.getID() == finalModifiedID1).findFirst().get();
    }


}

