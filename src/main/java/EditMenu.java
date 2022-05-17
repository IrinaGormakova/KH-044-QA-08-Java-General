import java.io.*;


public class EditMenu {

    public static void showEditMenu(ArrayTasks myArrayTask) {
        boolean flag;
        byte choice2 = 0;
        SendEmails emailSender = new SendEmails(myArrayTask.getEmailAuthor(), myArrayTask.getPassword(), myArrayTask.getAuthor());
        CsvReport reports = new CsvReport();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            flag = false;

            try {
                System.out.println("Edit menu:");
                System.out.println("[1] - Review all tasks");
                System.out.println("[2] - Review tasks by priority");
                System.out.println("[3] - Edit task by ID");
                System.out.println("[4] - Send reports");
                System.out.println("[5] - Export to file");
                System.out.println("[6] - Remove all tasks");
                System.out.println("[7] - Exit");
                choice2 = Byte.parseByte(br.readLine());
                if (choice2 < 1 || choice2 > 7) throw new MenuExceptions("Incorrect menu item");
            } catch (NumberFormatException | IOException e) {
                System.out.println("Entered number is incorrect");
                flag = true;
            } catch (MenuExceptions e1) {
                System.out.println(e1.getMessage());
                flag = true;
            }
            switch (choice2) {
                case 1:
                    myArrayTask.reviewAll();
                    break;
                case 2:
                    myArrayTask.byPriority();
                    break;
                case 3:
                    Task mTask = myArrayTask.selectByID();
                    if (mTask != null) {
                        TaskEditUI.showByIDMenu(mTask, emailSender, myArrayTask.myTasksList);
                    }
                    break;
                case 4:
                    if (myArrayTask.myTasksList.size() != 0) {
                        System.out.println("Sending report...");
                        emailSender.sendCsvReport(reports.givenTasks_ToCsvFile(myArrayTask.myTasksList));
                    } else
                        System.out.println("Task list is empty");
                    break;
                case 5:
                    if (myArrayTask.myTasksList.size() != 0) {
                    System.out.println("Exporting report to csv file...");
                    System.out.println("File " + reports.givenTasks_ToCsvFile(myArrayTask.myTasksList) + " has been created successfully");
                    } else
                        System.out.println("Task list is empty");
                    break;
                case 6:
                    myArrayTask.deleteList();
                    break;
                default:
                    break;
            }// end switch
        } while (flag || choice2 != 7);

    }
}
