import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EditTaskMenu {
    public static void showByIDMenu(int ID) {
        boolean flag;
        byte choice = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            flag = false;

            try {
                System.out.println("What do you want to do with task ID " + ID + "?");
                System.out.println("1 - Change priority");
                System.out.println("2 - Change execution data");
                System.out.println("3 - Change body of task");
                System.out.println("4 - Change status");
                System.out.println("5 - Change assignee");
                System.out.println("6 - Delete task");
                System.out.println("7 - Exit");
                System.out.println("\n");
                choice = Byte.parseByte(br.readLine());
                if (choice < 1 || choice > 7) throw new MenuExceptions("Incorrect menu item");
            } catch (NumberFormatException | IOException e) {
                System.out.println("Entered number is incorrect");
                System.out.println(e.getMessage());
                flag = true;
            } catch (MenuExceptions e1) {
                System.out.println(e1.getMessage());
                flag = true;
            }
            switch (choice) {
                case 1:
                    System.out.println("Change priority");
                    break;
                case 2:
                    System.out.println("Change execution data");
                    break;
                case 3:
                    System.out.println("Change body of task");
                    break;
                case 4:
                    System.out.println("Change status");
                    break;
                case 5:
                    System.out.println("Change assignee");
                    break;
                case 6:
                    System.out.println("Delete task");
                    break;
                default:
                    break;
            }// end switch
        } while (flag || choice != 7);

        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
