public abstract class Strategy
{
    protected Store targetStore;
    protected Boolean isTargetStoreSet = false;

    public abstract String getType();

    public Boolean IsTargetStoreSet()
    {
        return isTargetStoreSet;
    }

    public void SetTargetStore(Store targetStore)
    {
        
        this.targetStore = targetStore;
        if(targetStore == null)
        {
            isTargetStoreSet = false;
        }
        else
        {
            isTargetStoreSet = true;
        }
    }

    public abstract void OutOfStockError();

    public abstract void FindTargetStore(Customer customer);
}
