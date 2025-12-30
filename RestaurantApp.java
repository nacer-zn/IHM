import java.util.Scanner;

class Food {
    String name;
    double basePrice;
    int count;
    double totalPrice;

    Food(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
        this.count = 0;
        this.totalPrice = 0;
    }

    double unitPrice() {
        if (count == 0) return 0;
        return totalPrice / count;
    }
}

public class RestaurantApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Food[] foods = {
                new Food("Pizza", 8.0),
                new Food("Sandwich", 5.0),
                new Food("Tacos", 6.0),
                new Food("Egg Omelet", 4.0)
        };

        System.out.println("Welcome to Striker Fast Food");
        System.out.println("Press 1 to continue");
        System.out.println("Press 0 to exit");

        if (sc.nextInt() != 1) return;

        while (true) {
            System.out.println();
            System.out.println("Main Menu");
            for (int i = 0; i < foods.length; i++)
                System.out.println((i + 1) + ". " + foods[i].name + " - $" + foods[i].basePrice);
            System.out.println("5. Finish Order");

            int choice = sc.nextInt();
            if (choice == 5) break;
            if (choice < 1 || choice > 4) continue;

            Food f = foods[choice - 1];
            double addons = 0;

            System.out.println("Mayonnaise (1 None, 2 Low, 3 Normal, 4 Extra)");
            int m = sc.nextInt();
            if (m == 2) addons += 0.5;
            if (m == 3) addons += 1.0;
            if (m == 4) addons += 1.5;

            System.out.println("Hot Sauce (1 None, 2 Low, 3 Normal, 4 Extra)");
            int h = sc.nextInt();
            if (h == 2) addons += 0.5;
            if (h == 3) addons += 1.0;
            if (h == 4) addons += 1.5;

            System.out.println("Ketchup (1 None, 2 Low, 3 Normal, 4 Extra)");
            int k = sc.nextInt();
            if (k == 2) addons += 0.5;
            if (k == 3) addons += 1.0;
            if (k == 4) addons += 1.5;

            System.out.println("Enter quantity");
            int qty = sc.nextInt();
            if (qty < 1) qty = 1;

            f.count += qty;
            f.totalPrice += (f.basePrice + addons) * qty;
        }

        while (true) {
            System.out.println();
            System.out.println("Order Summary");

            int index = 1;
            Food[] map = new Food[foods.length];
            int mapIndex = 0;
            double total = 0;

            for (Food f : foods) {
                if (f.count > 0) {
                    System.out.println(index + ". " + f.name + " x " + f.count);
                    map[index - 1] = f;
                    index++;
                    total += f.totalPrice;
                }
            }

            System.out.println("Total Price: $" + total);
            System.out.println("Choose order number to edit");
            System.out.println("Press 0 to confirm");

            int edit = sc.nextInt();
            if (edit == 0) break;
            if (edit < 1 || edit >= index) continue;

            Food f = map[edit - 1];
            System.out.println("New quantity for " + f.name);
            int newQty = sc.nextInt();
            if (newQty < 0) newQty = 0;

            f.totalPrice = f.unitPrice() * newQty;
            f.count = newQty;
        }

        System.out.println("Order Confirmed");
        System.out.println("Thank you for choosing Striker Fast Food");
    }
}
