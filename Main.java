package ExpenseTracker;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US);
        ExpenseTrackService expenseTrackService = new ExpenseTrackService();

        System.out.println("===========================");
        System.out.println("Expense Manager");
        System.out.println("===========================");

        while (true) {
            try {
                System.out.println("1 - Add Expense \n2 - Delete Expense \n3 - List Expenses \n4 - Summary \n0 - Quit");
                System.out.print("Select your option: ");
                int option = sc.nextInt();
                sc.nextLine();

                switch (option) {
                    case 1:
                        System.out.print("Enter the ID for registration: ");
                        Integer id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter the description: ");
                        String description = sc.nextLine();
                        System.out.print("Enter the amount: ");
                        Double amount = sc.nextDouble();
                        sc.nextLine();
                        expenseTrackService.addExpense(new Expense(id, LocalDate.now(), description, amount));
                        System.out.println("Expense added!");
                        break;

                    case 2:
                        System.out.print("Enter the ID to delete Expense: ");
                        Integer deleteId = sc.nextInt();
                        sc.nextLine();
                        expenseTrackService.deleteExpense(deleteId);
                        System.out.println("Expense deleted successfuly!");
                        break;

                    case 3:
                        expenseTrackService.listExpenses();
                        break;

                    case 4:
                        System.out.print("Enter the month number for view the month total [0 - total summary]: ");
                        int month = sc.nextInt();
                        sc.nextLine();
                        if (month == 0) {
                            System.out.println("Total Expenses: $" + expenseTrackService.summary());
                        } else if (month > 12) {
                            System.out.println("the month entered does not match!");
                        } else {
                            System.out.println("Total expenses for " + Month.of(month).name() + ": $" + expenseTrackService.summary(month));
                        }
                        break;

                    case 0:
                        System.out.println("Exiting the program...");
                        return;
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR! Pls, enter a valid value!");
                sc.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("the ID entered does not exist!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (RuntimeException e) {
                System.out.println("An expense with this ID already exists!");
            }
        }
    }
}
