import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;
import java.util.ArrayList;

public class Common {
    private static final String title = "Economics 101";
    private static final int windowWidth = 1650;
    private static final int windowHeight = 1000;

    private static final int entityDiameter = 20; //diameter of drawn entity (customer or store)

    private static final int storeNo = 10; //number of stores in the simulation
    private static final int customerNo = 10; //number of customers

    private static final int stockReplenishmentFrequency = 3000; // number of stepAllEntities calls before replenishing all stores

    private static final int foodBottomPrice = 20;
    private static final int foodCeilingPrice = 50;
    private static final int electronicsBottomPrice = 200;
    private static final int electronicsCeilingPrice = 2000;
    private static final int LuxuryBottomPrice = 5000;
    private static final int LuxuryCeilingPrice = 10000;

    private static final int minimumShoppingListLength = 5;
    private static final int maximumShoppingListLength = 10;

    private static final int stockStorageMin = 15; //minimum size of storage available for a store
    private static final int stockStorageMax = 25; //maximum number of storage available for a store

    private static final int customerMovementSpeed = 2;
    private static final Font font =new Font("Verdana", Font.BOLD, 20);

    public static String getTitle() {
        return title;
    }

    public static int getWindowWidth() {
        return windowWidth;
    }

    public static int getWindowHeight() {
        return windowHeight;
    }

    public static int getEntityDiameter(){ return entityDiameter;}

    public static Font getFont() {return font;}

    public static int getCustomerMovementSpeed() {
        return customerMovementSpeed;
    }

    private static List<Store> stores = new ArrayList<Store>();
    private static List<Customer> customers = new ArrayList<Customer>();

    public static List<Store> GetStores() 
    {
        return stores;
    }

    public static List<Customer> GetCustomers() 
    {
        return customers;
    }

    public static int GetStoresCount() 
    {
        return stores.size();
    }

    public static int GetCustomersCount()
    {
        return customers.size();
    }

    public static int randInt(int min, int max) 
    {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static Product ChooseProduct()
    {
        switch(randInt(0,2)){
            case 0:
                //Food
                return new FoodProduct();
            case 1:
                // Electronic
                return new ElectronicsProduct();
            case 2:
                //Luxury
                return new LuxuryProduct();
            default:
                return null;
        }
    }

    public static Strategy ChooseStrategy()
    {
        switch(randInt(0, 2))
        {
            case 0:
                return new CheapestStrategy();
            case 1:
                return new ClosestStrategy();
            case 2:
                return new TravellingStrategy();
            default:
                return null;
        }
    }

    public static Customer CreateCustomer()
    {
        int x = randInt(0, windowWidth - 2 * entityDiameter);
        int y = randInt(0, windowHeight - 2 * entityDiameter);
        int shoppingListLength = randInt(minimumShoppingListLength, maximumShoppingListLength);
        ArrayList<Product> shoppingList = new ArrayList<Product>();
        for(int i = 0; i < shoppingListLength; i++)
        {
            shoppingList.add(ChooseProduct());
        }
        Strategy strategy = ChooseStrategy();
        return new Customer(x, y, strategy, shoppingList);
    }

    private static int storeCount = 0;
    public static Store CreateStore()
    {
        int x = randInt(0, windowWidth - 2 * entityDiameter);
        int y = randInt(0, windowHeight - 2 * entityDiameter);
        int price = 0;
        int stock = randInt(stockStorageMin, stockStorageMax);
        Product product;
        switch(storeCount++ % 3)
        {
            case 0:
                price = randInt(foodBottomPrice, foodCeilingPrice);
                product = new FoodProduct();
                break;
            case 1:
                price = randInt(electronicsBottomPrice, electronicsCeilingPrice);
                product = new ElectronicsProduct();
                break;
            case 2:
                price = randInt(LuxuryBottomPrice, LuxuryCeilingPrice);
                product = new LuxuryProduct();
                break;
            default:
                product = null;
                break;
        }
        return new Store(x, y, product, price, stock);
    }

    static
    {
        for(int i = 0; i < storeNo; i++)
        {
            stores.add(CreateStore());
        }
        for(int i = 0; i < customerNo; i++)
        {
            customers.add(CreateCustomer());
        }
    }

    private static int lastReplenishment = 0;
    public static void stepAllEntities() 
    {
        for(int i = 0; i < customerNo; i++)
        {
            if(customers.get(i).getPosition().getX() < 0 || 
                customers.get(i).getPosition().getX() > windowWidth || 
                customers.get(i).getPosition().getY() < 0 || 
                customers.get(i).getPosition().getY() > windowHeight)
            {
                customers.remove(i);
                customers.add(CreateCustomer());
            }
        }

        for(Customer customer : customers)
        {
            customer.step();
        }

        for(Store store : stores)
        {
            store.step();
        }

        lastReplenishment++;
        if (lastReplenishment == stockReplenishmentFrequency)
        {
            for(Store store : stores)
            {
                store.stock = randInt(stockStorageMin, stockStorageMax);
            }
            lastReplenishment = 0;
        }
    }
}
