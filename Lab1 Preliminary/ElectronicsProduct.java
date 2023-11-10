public class ElectronicsProduct extends Product
{
    ElectronicsProduct()
    {
        this.type = "Electronics";
    }

    @Override
    public String GetProductType()
    {
        return "E";
    }
}
