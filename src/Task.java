import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
    public static int currentID=0;

    private int ID;
    private String priority;
    private String status;
    private String body;
    private String assignee;
    private String email;
    private LocalDate creationDate;
    private LocalDate executionDate;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(LocalDate executionDate) {
        this.executionDate = executionDate;
    }

    public Task(int ID, String priority, String status, String body, String assignee, String email, LocalDate creationDate, LocalDate executionDate) {
        this.ID = ID;
        this.priority = priority;
        this.status = status;
        this.body = body;
        this.assignee = assignee;
        this.email = email;
        this.creationDate = creationDate;
        this.executionDate = executionDate;
    }

    public Task() {
        this.priority = "planned";
        this.status = "new";
        this.ID = currentID + 1;
        this.creationDate = LocalDate.now();

    }

    @Override
    public String toString() {
        return "ID: " + ID + "; priority: " + priority + "; status: " + status + "; created: " + creationDate +
                "\n" + body + "\nAssignee: " + assignee+" email: "+email+"; production date :"+executionDate;
    }

    public void changePriority (){
        boolean flag;
        byte choice=-1;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Do you want to change current priority: "+this.getPriority()+"? Please, select necessary priority or press ENTER to continue without changing");
        do {
            flag = false;
            try {
                System.out.println("1 - Planned");
                System.out.println("2 - Urgent");
                System.out.println("3 - Delegated");
                System.out.println("4 - Trivial");
                System.out.println("\n");
                if (br.readLine().equals("")) {
                    choice = 0;
                } else choice = Byte.parseByte(br.readLine());
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
                this.setPriority("planned");
                break;
            case 2:
                this.setPriority("urgent");
                break;
            case 3:
                this.setPriority("delegated");
                break;
            case 4:
                this.setPriority("trivial");
                break;
            default:
                break;
        }// end switch
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//end changePriority

    public void changeEmail (){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean flag;
        String pattern ="\\w+(\\.\\w+)*@(\\w+\\.)+\\w+";
        Pattern p=Pattern.compile(pattern);
        Matcher m;

        System.out.println("Do you want to set email address for assignee?");
        System.out.println("Please, set email or press ENTER to continue without changing");
        do {
            flag = false;
            try {
                if (!br.readLine().equals("")) {
                   m=p.matcher(br.readLine());
                   if (m.matches()){
                     this.setEmail(br.readLine());
                   } else throw new MenuExceptions("Incorrect email address. Try again or press ENTER to continue without changing");
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
                flag = true;
            }
            catch (MenuExceptions ex) {
                System.out.println(ex.getMessage());
                flag = true;
            }
        }  while (flag);

        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
