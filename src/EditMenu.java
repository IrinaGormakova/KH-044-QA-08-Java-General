import java.io.*;


public class  EditMenu {
    public static void showEditMenu() {
        boolean flag;
        byte choice2=0;
        final String fileName = "ArrayTasks.dat";

        //Deserialization
        File file = new File(fileName);
        ArrayTasks myArrayTask = null;
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                ObjectInputStream istream = new ObjectInputStream(new FileInputStream(file));
                myArrayTask = (ArrayTasks) istream.readObject();
                istream.close();
            } catch (ClassNotFoundException | IOException e) {
                System.err.println(e.getMessage());
            }
        }
        if (myArrayTask == null) myArrayTask = new ArrayTasks();
        //end Deserialization
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            flag = false;

            try {
                System.out.println("Edit menu:");
                System.out.println("1 - Review all tasks");
                System.out.println("2 - Review tasks by priority");
                System.out.println("3 - Edit task by ID");
                System.out.println("4 - Send reports");
                System.out.println("5 - Export to file");
                System.out.println("6 - Exit");
                System.out.println("\n");
                choice2 = Byte.parseByte(br.readLine());
                if (choice2 < 1 || choice2 > 6) throw new MenuExceptions("Incorrect menu item");
            } catch (NumberFormatException | IOException e) {
                System.out.println("Entered number is incorrect");
                System.out.println(e.getMessage());
                flag = true;
            } catch (MenuExceptions e1) {
                System.out.println(e1.getMessage());
                flag = true;
            }
            switch (choice2) {
                case 1:
                    myArrayTask.ReviewAllTasks();
                    break;
                case 2:
                    myArrayTask.ReviewTasksbyPriority();
                    break;
                case 3:
                    System.out.println("Edit task by ID");
                    break;
                case 4:
                    System.out.println("Send reports");
                    break;
                case 5:
                    System.out.println("Export to file");
                    break;
                default:
                    break;
            }// end switch
        } while (flag || choice2 != 6);

    }
}
