import java.util.List;
import java.util.ArrayList;

public class TravellingStrategy extends Strategy
{
    TravellingStrategy()
    {

    }

    @Override
    public String getType()
    {
        return "Tr";
    }

    private ArrayList<Store> visitedStores = new ArrayList<Store>();
    

    @Override
    public void FindTargetStore(Customer customer)
    {
        List<Store> stores = Common.GetStores();
        Store closestStore = null;
        double minDistance = Double.MAX_VALUE;
        for(Store store : stores)
        {
            if(!visitedStores.contains(store))
            {
                double distance = store.GetDistance(customer);
                if(distance < minDistance)
                {
                    minDistance = distance;
                    closestStore = store;
                }
            }
        }
        if(closestStore == null)
        {
            visitedStores.clear();
            FindTargetStore(customer);
        }
        else
        {
            visitedStores.add(closestStore);
            this.SetTargetStore(closestStore);
        }
    }

    @Override
    public void OutOfStockError() 
    {
        
    }
}
