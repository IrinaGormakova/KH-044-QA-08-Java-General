import java.io.*;
import java.time.LocalDate;
import java.util.regex.*;

public class Task implements Serializable {

    private int ID;
    private String title;
    private TaskPriority priority;
    private TaskStatus status;
    private String body;
    private String assignee;
    private String email;
    private LocalDate creationDate;
    private LocalDate executionDate;

    public Task(int currentID, String assignee, String email) {
        this.priority = TaskPriority.PLANNED;
        this.status = TaskStatus.NEW;
        this.ID = currentID + 1;
        this.creationDate = LocalDate.now();
        this.executionDate = LocalDate.now().plusDays(1);
        this.assignee = assignee;
        this.email = email;
        this.title = "New Task / ID-" + this.ID;
        this.body = "empty";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
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

    @Override
    public String toString() { //  add title
        return "ID: " + ID + " | PRI: " + priority + " | " + title + " | EXT:" + executionDate;
    }

    public String viewAllDetails() {
        return "ID: " + ID + " | Priority: " + priority + " | Status: " + status + " | \n " +
                "Creation date: " + creationDate + "\n " +
                "Execution date: " + executionDate + "\n " +
                "Title: " + title + "\n" +
                "Description: \n " +
                body + "\n " +
                "Assignee name: " + assignee + " | Assignee email: " + email;
    }
}
