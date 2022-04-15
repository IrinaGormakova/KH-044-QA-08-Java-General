import java.io.*;


public class App {
    public static void main(String[] args) {
        boolean flag;
        byte choice = 0;
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
                System.out.println("Select necessary action for execution or exit");
                System.out.println("1 - Create new task");
                System.out.println("2 - Edit/Review tasks");
                System.out.println("3 - Exit");
                choice = Byte.parseByte(br.readLine());
                if (choice < 1 || choice > 3) throw new MenuExceptions("Incorrect menu item");
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
                    myArrayTask.addTask();
                    break;
                case 2:
                    EditMenu.showEditMenu();
                    break;
                default:
                    break;
            }// end switch
        } while (flag || choice != 3);

        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Serialization
        try {
            //binary
            ObjectOutputStream ostream =
                    new ObjectOutputStream(new FileOutputStream(file));
            ostream.writeObject(myArrayTask);
            ostream.close();

        } catch (IOException e) {
            System.err.println(e);
        }
        //end Serialization

    }// main
}//class
