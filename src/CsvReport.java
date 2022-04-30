import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvReport {

    /**
     *
     * @param taskToConvert
     * @return
     */
    private String convertToCSV(Task taskToConvert) {
        String[] data= new String[]{String.valueOf(taskToConvert.getID()),
                taskToConvert.getPriority().toString(),
                taskToConvert.getStatus().toString(),
                taskToConvert.getCreationDate().toString(),
                taskToConvert.getExecutionDate().toString(),
                taskToConvert.getBody(),
                taskToConvert.getAssignee(),
                taskToConvert.getEmail()
        };

        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    private String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " "); //\R matches any Unicode newline sequence
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }


    public String givenTasks_ToCsvFile(ArrayList <Task> dataForReport) {
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm");
        System.out.println(LocalDateTime.now().format(formatter));
        String fileName = "src/reports/report_"+ LocalDateTime.now().format(formatter);
        File csvOutputFile = new File(fileName);

    if (!csvOutputFile.exists()) {
            try {
                csvOutputFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {

            String header= "ID,Priority,Status,CreationDate,ExecutionDate,Body,Assignee,Email";
            pw.println(header);
            dataForReport.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return fileName;
    }
}
