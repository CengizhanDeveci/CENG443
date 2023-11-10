public class LuxuryProduct extends Product
{
    LuxuryProduct()
    {
        this.type = "Luxury";
    }
    @Override
    public String GetProductType()
    {
        return "L";
    }

}
