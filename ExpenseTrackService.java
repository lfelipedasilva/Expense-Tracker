package ExpenseTracker;

import java.io.*;
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
                String linha;
                while ((linha = br.readLine()) != null) {
                    String[] partes = linha.split(",");
                    hashSet.add(Integer.valueOf(partes[0]));
                }
            }

            if (hashSet.contains(expense.getId())) {
                System.out.println("An expense with this ID already exists!");
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
            String linha;
            System.out.println("ID    DATE     DESCRIPTION     AMOUNT");
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                System.out.println(partes[0] + " " + partes[1] + " " + partes[2] + " " + partes[3]);

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
