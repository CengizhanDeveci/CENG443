public class FoodProduct extends Product
{
    FoodProduct()
    {
        this.type = "F";
    }

    @Override
    public String GetProductType()
    {
        return "F";
    }
}
