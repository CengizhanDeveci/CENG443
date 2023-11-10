import java.util.List;

public class ClosestStrategy extends Strategy
{
    ClosestStrategy()
    {

    }

    @Override
    public String getType()
    {
        return "Cl";
    }

    private Store prev = null;

    @Override
    public void FindTargetStore(Customer customer) 
    {
        double minDistance = Double.MAX_VALUE;
        List<Store> stores = Common.GetStores();

        for(Store store : stores)
        {
            if(store != prev && store.productToSell.GetProductType() == customer.shoppingCart.get(0).GetProductType())
            {
                double distance = store.GetDistance(customer);
                if(distance < minDistance)
                {
                    minDistance = distance;
                    targetStore = store;
                }
            }
        }
        this.SetTargetStore(targetStore);
    }

    @Override
    public void OutOfStockError() 
    {
        prev = targetStore;
    }
}
