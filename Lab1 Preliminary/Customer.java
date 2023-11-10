import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Customer extends Entity
{
    protected Strategy strategy;
    protected ArrayList<Product> shoppingCart = new ArrayList<Product>();

    public Customer(double x, double y, Strategy strategy, ArrayList<Product> shoppingCart) 
    {
        super(x, y);
        this.strategy = strategy;
        this.shoppingCart = shoppingCart;
    }
    
    @Override
    public void draw(Graphics2D g2d) 
    {
        String text = strategy.getType();
        for(int i = 0; i < 3; i++)
        {
            try
            {
                text += "," + shoppingCart.get(i).GetProductType();
            }
            catch (IndexOutOfBoundsException e)
            {
                break;
            }
        }

        drawHelper(g2d, text, Color.GRAY);
    }

    @Override
    public void step() 
    {

        if(shoppingCart.isEmpty())
        {
            position.setX(position.getX() + Common.getCustomerMovementSpeed());
            return;
        }
        else
        {
            if(!strategy.IsTargetStoreSet())
            {
                strategy.FindTargetStore(this);
            }
            
            Store targetStore = strategy.targetStore;
            double distance = this.position.distanceTo(targetStore.getPosition());

            if(distance < Common.getEntityDiameter())
            {
                try
                {
                    targetStore.Sell();
                    shoppingCart.remove(0);
                    strategy.SetTargetStore(null);
                }
                catch (Error e)
                {
                    strategy.OutOfStockError();
                    strategy.SetTargetStore(null);
                }
            }
            else
            {
                double dx = targetStore.getPosition().getX() - this.position.getX();
                double dy = targetStore.getPosition().getY() - this.position.getY();
                double vx = dx / distance * Common.getCustomerMovementSpeed();
                double vy = dy / distance * Common.getCustomerMovementSpeed();
                position.setX(position.getX() + vx);
                position.setY(position.getY() + vy);
            }
        }

        
    }
    
}
