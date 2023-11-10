import java.awt.Color;
import java.awt.Graphics2D;

public class Store extends Entity
{
    protected Product productToSell;
    protected int price;
    protected int stock;

    Store(int x, int y, Product product, int price, int stock) 
    {
        super(x, y);
        this.productToSell = product;
        this.price = price;
        this.stock = stock;
    }

    public int GetPrice() 
    {
        return price;
    }

    public void Sell()
    {
        if(stock > 0)
        {
            stock--;
        }
        else
        {
            throw new Error("Out of stock!");
        }    
    }   

    public double GetDistance(Customer customer)
    {
        return Math.sqrt(Math.pow(customer.getPosition().getX() - position.getX(), 2) + Math.pow(customer.getPosition().getY() - position.getY(), 2));
    }

    @Override
    public void draw(Graphics2D g2d) 
    {
        String text = productToSell.GetProductType() + "-" + price + "-" + stock;
        drawHelper(g2d, text, Color.ORANGE);
    }

    @Override
    public void step()
    {

    }
    
}
