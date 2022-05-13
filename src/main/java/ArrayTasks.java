import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArrayTasks implements Serializable {
    ArrayList<Task> myTasksList = new ArrayList<>();
    public int currentID = 0;
    private String author = "Irina Gormakova";
    private String emailAuthor = "work.gormakova@gmail.com";
    private transient String password;

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

    public boolean addTask(Task newTask) {
        currentID++;
        return myTasksList.add(newTask);
    }

    public void reviewAll() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ID;
        System.out.println("Full task list: ");
        myTasksList
                .forEach(System.out::println);
        while (true){
            System.out.println("Which id would you like to see more detailed? Set id number or press ENTER to leave");
            try {
                String str = br.readLine();
                if (str.isBlank()) {
                    break;
                } else ID = Integer.parseInt(str);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                ID = 0;
            }
            int finalID = ID;
            if (myTasksList.stream().noneMatch(x -> x.getID() == finalID)) {
                System.out.println("Task ID " + finalID + " wasn't found");
            } else {
                myTasksList.stream().filter(x -> x.getID() == finalID).forEach(x -> System.out.println(x.viewAllDetails()));
            }
        }
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
                if (myTasksList.stream().noneMatch(x -> x.getPriority() == TaskPriority.PLANNED)) {
                    System.out.println("Tasks with priority planned not created yet");
                    return;
                } else {
                    myTasksList.stream()
                            .filter(x -> x.getPriority().equals(TaskPriority.PLANNED))
                            .forEach(System.out::println);
                    System.out.println(" ");
                }
                break;
            case 2:
                if (myTasksList.stream().noneMatch(x -> x.getPriority() == TaskPriority.URGENT)) {
                    System.out.println("Tasks with priority planned not created yet");
                    return;
                } else {
                    myTasksList.stream()
                            .filter(x -> x.getPriority().equals(TaskPriority.URGENT))
                            .forEach(System.out::println);
                    System.out.println(" ");
                }
                break;
            case 3:
                if (myTasksList.stream().noneMatch(x -> x.getPriority() == TaskPriority.DELEGATED)) {
                    System.out.println("Tasks with priority planned not created yet");
                    return;
                } else {
                    myTasksList.stream()
                            .filter(x -> x.getPriority().equals(TaskPriority.DELEGATED))
                            .forEach(System.out::println);
                    System.out.println(" ");
                }
                break;
            case 4:
                if (myTasksList.stream().noneMatch(x -> x.getPriority() == TaskPriority.TRIVIAL)) {
                    System.out.println("Tasks with priority planned not created yet");
                    return;
                } else {
                    myTasksList.stream()
                            .filter(x -> x.getPriority().equals(TaskPriority.TRIVIAL))
                            .forEach(System.out::println);
                    System.out.println(" ");
                }
                break;
            default:
                break;
        }
        boolean flag1;
        int ID;
        do {
            flag1 = false;
            System.out.println("Which id would you like to see more detailed enter id number or press enter to leave");
            try {
                String str = br1.readLine();
                if (str.isBlank()) {
                    return;
                } else ID = Integer.parseInt(str);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                ID = 0;
            }
            int finalID = ID;
            if (myTasksList.stream().noneMatch(x -> x.getID() == finalID)) {
                System.out.println("Task ID " + finalID + " wasn't found");
                flag1 = true;
            }

        } while (flag1);
        int finalID1 = ID;
        myTasksList.stream().filter(x -> x.getID() == finalID1).forEach(x -> System.out.println(x.viewAllDetails()));
    }


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
                String s = br.readLine();
                if (!s.isBlank()) {
                    modifiedID = Integer.parseInt(s);
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
        } while (flag);
        int finalModifiedID1 = modifiedID;
        return myTasksList.stream().filter(x -> x.getID() == finalModifiedID1).findFirst().get();
    }

    public void editCredentials() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        System.out.println("Please, check current credentials and change them if necessary for further correct work of the program");
        System.out.println("Author: " + getAuthor());
        System.out.println("Enter new Author name or press ENTER to continue without changing");
        try {
            s = br.readLine();
            if (!s.isBlank()) {
                setAuthor(s);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Author's email: " + getEmailAuthor());
        System.out.println("Enter new Author's email or press ENTER to continue without changing");
        try {
            s = br.readLine();
            if (!s.isBlank()) {
                String pattern = "\\w+(\\.\\w+)*@(\\w+\\.)+\\w+";
                Pattern p = Pattern.compile(pattern);

                s = s.trim();
                Matcher m = p.matcher(s);
                if (m.matches()) {
                    setEmailAuthor(s);
                    System.out.println("Author's email has been changed successfully");
                } else {
                    System.out.println("Incorrect email address");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Password: " + getPassword());

        Console console;
        if ((console=System.console())!= null) {
            char [] password = console.readPassword("Enter new Password or press ENTER to continue without changing: ");
            s = String.valueOf(password);
            if (!s.isBlank()) {
                setPassword(s);
                System.out.println("Password has been changed successfully");
            }
        } else System.out.println("Console is null");
    }
}

