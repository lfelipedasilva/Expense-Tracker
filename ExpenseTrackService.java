package ExpenseTracker;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

public class ExpenseTrackService {
    private String doc = "C:\\Users\\TECO\\IdeaProjects\\ProjetosGithub\\src\\ExpenseTracker" +
            "\\ExpenseTrackArchives\\ExpensesHistory.csv";

    public ExpenseTrackService() {
    }

    public void addExpense(Expense expense) throws IOException, RuntimeException{
        HashSet<Integer> hashSet = new HashSet<>();

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(doc, true))) {

            try(BufferedReader br = new BufferedReader(new FileReader(doc))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] split = line.split(",");
                    hashSet.add(Integer.valueOf(split[0]));
                }
            }

            if (hashSet.contains(expense.getId())) {
                throw new RuntimeException();
            } else {
                bf.write(expense.toString());
                bf.newLine();
            }

        }


    }

    public void listExpenses() throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader(doc))) {
            String line;
            System.out.println("ID  |  DATE  |   DESCRIPTION   |  AMOUNT");
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                System.out.println(split[0] + " |  " + split[1] + " |  " + split[2] + " |  " + split[3]);

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public Double summary() throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader(doc))) {
            String line;
            Double total = 0.0;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                total += Double.valueOf(split[3]);
            }
            return total;
        }
    }

    public Double summary(int month) throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader(doc))) {
            String line;
            Double total = 0.0;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                LocalDate tempDate = LocalDate.parse(split[1]);
                if (tempDate.getMonthValue() == month) {
                    total += Double.valueOf(split[3]);
                }
            }
            return total;
        }
    }

    public void deleteExpense(Integer id) throws IOException{

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(doc, true))) {
            List<String> lines = Files.readAllLines(Paths.get(doc));
            HashSet<Integer> hashSet = new HashSet<>();
            String[] split;
            for (String line : lines) {
                split = line.split(",");
                hashSet.add(Integer.valueOf(split[0]));
            }
            if (hashSet.contains(id)) {
                lines.removeIf(item -> item.startsWith(id + ","));
            } else {
                throw new IllegalArgumentException();
            }

            Files.write(Paths.get(doc), lines);

        }

    }

}
