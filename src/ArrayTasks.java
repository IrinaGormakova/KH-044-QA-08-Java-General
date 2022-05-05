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

    public void addTask (Task newTask){
        myTasksList.add(newTask);
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
            }  while (flag);
            switch (choice) {
                case 1:
                    myTasksList.stream()
                            .filter(x -> x.getPriority().toString().equals("Planned"))
                            .forEach(System.out::println);
                    System.out.println(" ");
                    break;
                case 2:
                    myTasksList.stream()
                            .filter(x -> x.getPriority().toString().equals("Urgent"))
                            .forEach(System.out::println);
                    System.out.println(" ");
                    break;
                case 3:
                    myTasksList.stream()
                            .filter(x -> x.getPriority().toString().equals("Delegated"))
                            .forEach(System.out::println);
                    System.out.println(" ");
                    break;
                case 4:
                    myTasksList.stream()
                            .filter(x -> x.getPriority().toString().equals("Trivial"))
                            .forEach(System.out::println);
                    System.out.println(" ");
                    break;
                default:
                    break;
            }
    }
    }

