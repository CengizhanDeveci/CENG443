import java.util.concurrent.Semaphore;

public class ProductionLine implements Runnable
{
    private final static int engineCount = 1;
    private final static int wheelCount = 4;
    private final static int glassCount = 6;

    private final Component component;
    private final Semaphore startSemaphore;
    private final Semaphore stopSemaphore;

    ProductionLine(Semaphore startSemaphore, Semaphore stopSemaphore, Component component) 
    {
        this.component = component;
        this.startSemaphore = startSemaphore;
        this.stopSemaphore = stopSemaphore;
    }

    @Override
    public void run() 
    {
        try
        {
            while (true)
            {
                startSemaphore.acquire();

                if (component == Component.ENGINE) 
                {
                    Actions.startEngineProductionLine();
                    for (int i = 0; i < engineCount; i++) 
                    {
                        Actions.produceEngine();
                    }
                    Actions.stopEngineProductionLine();       
                }
                else if (component == Component.WHEEL) 
                {
                    Actions.startWheelProductionLine();
                    for(int i = 0; i < wheelCount; i++) 
                    {
                        Actions.produceWheel();
                    }
                    Actions.stopWheelProductionLine();
                }
                else if (component == Component.GLASS) 
                {
                    Actions.startGlassProductionLine();
                    for(int i = 0; i < glassCount; i++) 
                    {
                        Actions.produceGlass();
                    }  
                    Actions.stopGlassProductionLine();
                }
                else 
                {
                    System.out.println("Invalid component");
                    return;
                }
                
                stopSemaphore.release();
            }
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
            System.out.println("Interrupted");
        }
    }
}
