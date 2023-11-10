import java.util.List;

public class CheapestStrategy extends Strategy
{
    public CheapestStrategy()
    {
        
    }

    @Override
    public String getType()
    {
        return "Ch";
    }

    @Override
    public void FindTargetStore(Customer customer)
    {
        int minPrice = Integer.MAX_VALUE;
        List<Store> stores = Common.GetStores();
        for(Store store : stores)
        {
            if(store.productToSell.GetProductType() == customer.shoppingCart.get(0).GetProductType())
            {
                int price = store.GetPrice();
                if(price < minPrice)
                {
                    minPrice = price;
                    targetStore = store;
                }
            }
            
        }
        this.SetTargetStore(targetStore);
    }

    @Override
    public void OutOfStockError()
    {

    }
}
