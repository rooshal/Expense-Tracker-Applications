import java.util.*;

class Expense {
    private int expenseId;
    private String expenseName;
    private String category;
    private double amount;

    public Expense(int expenseId, String expenseName, String category, double amount) {
        this.expenseId = expenseId;
        this.expenseName = expenseName;
        this.category = category;
        this.amount = amount;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void displayExpense() {
        System.out.println("ID: " + expenseId +
                " | Name: " + expenseName +
                " | Category: " + category +
                " | Amount: ₹" + amount);
    }
}

class ExpenseManager {
    private ArrayList<Expense> expenses = new ArrayList<>();
    private int nextId = 1;

    public void addExpense(String name, String category, double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be positive!");
            return;
        }

        Expense expense = new Expense(nextId++, name, category, amount);
        expenses.add(expense);
        System.out.println("Expense Added Successfully!");
    }

    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
            return;
        }

        for (Expense e : expenses) {
            e.displayExpense();
        }
    }

    public Expense searchExpense(int id) {
        for (Expense e : expenses) {
            if (e.getExpenseId() == id) {
                return e;
            }
        }
        return null;
    }

    public void editExpense(int id, String name, String category, double amount) {
        Expense e = searchExpense(id);

        if (e != null) {
            e.setExpenseName(name);
            e.setCategory(category);
            e.setAmount(amount);
            System.out.println("Expense Updated Successfully!");
        } else {
            System.out.println("Expense not found!");
        }
    }

    public void deleteExpense(int id) {
        Expense e = searchExpense(id);

        if (e != null) {
            expenses.remove(e);
            System.out.println("Expense Deleted Successfully!");
        } else {
            System.out.println("Expense not found!");
        }
    }

    public void totalSpending() {
        double total = 0;

        for (Expense e : expenses) {
            total += e.getAmount();
        }

        System.out.println("Total Spending: ₹" + total);
    }

    public void categorySummary() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses available.");
            return;
        }

        HashMap<String, Double> summary = new HashMap<>();

        for (Expense e : expenses) {
            summary.put(
                    e.getCategory(),
                    summary.getOrDefault(e.getCategory(), 0.0) + e.getAmount()
            );
        }

        System.out.println("\nCategory-wise Summary:");
        for (Map.Entry<String, Double> entry : summary.entrySet()) {
            System.out.println(entry.getKey() + " : ₹" + entry.getValue());
        }
    }
}

public class MainProject {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ExpenseManager manager = new ExpenseManager();

        while (true) {
            System.out.println("\n===== EXPENSE TRACKER =====");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Search Expense");
            System.out.println("4. Edit Expense");
            System.out.println("5. Delete Expense");
            System.out.println("6. Total Spending");
            System.out.println("7. Category-wise Summary");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Expense Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Category: ");
                    String category = sc.nextLine();

                    System.out.print("Enter Amount: ");
                    double amount = sc.nextDouble();

                    manager.addExpense(name, category, amount);
                    break;

                case 2:
                    manager.viewExpenses();
                    break;

                case 3:
                    System.out.print("Enter Expense ID: ");
                    int searchId = sc.nextInt();

                    Expense found = manager.searchExpense(searchId);

                    if (found != null) {
                        found.displayExpense();
                    } else {
                        System.out.println("Expense not found!");
                    }
                    break;

                case 4:
                    System.out.print("Enter Expense ID to Edit: ");
                    int editId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();

                    System.out.print("Enter New Category: ");
                    String newCategory = sc.nextLine();

                    System.out.print("Enter New Amount: ");
                    double newAmount = sc.nextDouble();

                    manager.editExpense(editId, newName, newCategory, newAmount);
                    break;

                case 5:
                    System.out.print("Enter Expense ID to Delete: ");
                    int deleteId = sc.nextInt();

                    manager.deleteExpense(deleteId);
                    break;

                case 6:
                    manager.totalSpending();
                    break;

                case 7:
                    manager.categorySummary();
                    break;

                case 8:
                    System.out.println("Thank You for Using Expense Tracker!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}
