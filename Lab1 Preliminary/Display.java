import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Display extends JPanel {
    public Display() { this.setBackground(new Color(220, 220, 220)); }

    @Override
    public Dimension getPreferredSize() { return super.getPreferredSize(); }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        List<Customer> customers = Common.GetCustomers();
        List<Store> stores = Common.GetStores();

        for(Customer customer : customers) 
        {
            customer.draw((Graphics2D) g);
        }

        for(Store store : stores) 
        {
            store.draw((Graphics2D) g);
        }
    }
}
