package ExpenseTracker;

import java.io.*;
import java.time.LocalDate;
import java.util.HashSet;

public class ExpenseTrackService {
    private String doc = "C:\\Users\\TECO\\IdeaProjects\\ProjetosGithub\\src\\ExpenseTracker" +
            "\\ExpenseTrackArchives\\ExpensesHistory.csv";

    public ExpenseTrackService() {
    }

    public void addExpense(Expense expense) {
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
                throw new RuntimeException("An expense with this ID already exists!");
            } else {
                bf.write(expense.toString());
                bf.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void listExpenses() {
        try(BufferedReader br = new BufferedReader(new FileReader(doc))) {
            String line;
            System.out.println("ID  |  DATE  |   DESCRIPTION   |  AMOUNT");
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                System.out.println(split[0] + " |  " + split[1] + " |  " + split[2] + " |  " + split[3]);

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Double summary() {
        try(BufferedReader br = new BufferedReader(new FileReader(doc))) {
            String line;
            Double total = 0.0;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                total += Double.valueOf(split[3]);
            }
            return total;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Double summary(int month) {
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
