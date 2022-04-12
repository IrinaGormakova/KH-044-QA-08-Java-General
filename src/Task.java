import java.time.LocalDate;

public class Task {
    public static int currentID;

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
    }

    @Override
    public String toString() {
        return "ID: "+ID+"; priority: "+priority+"; status: "+status+"; created: "+creationDate+
                "\n"+body+"\nAssignee: "+assignee+"email: "+email+"production date:"+executionDate;
    }
}
