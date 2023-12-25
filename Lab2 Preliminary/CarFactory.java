import java.lang.Thread;
import java.util.concurrent.Semaphore;

public class CarFactory 
{
    public static void main(String[] args) 
    {
        Semaphore startEngineSemaphore = new Semaphore(1);
        Semaphore startWheelSemaphore = new Semaphore(1);
        Semaphore startGlassSemaphore = new Semaphore(1);
        Semaphore stopSemaphore = new Semaphore(0);

        Thread assemblyLine = new Thread(new AssemblyLine(stopSemaphore, startEngineSemaphore, startWheelSemaphore, startGlassSemaphore));
        Thread engineProductionLine = new Thread(new ProductionLine(startEngineSemaphore, stopSemaphore, Component.ENGINE));
        Thread wheelProductionLine = new Thread(new ProductionLine(startWheelSemaphore, stopSemaphore, Component.WHEEL));
        Thread glassProductionLine = new Thread(new ProductionLine(startGlassSemaphore, stopSemaphore, Component.GLASS));

        engineProductionLine.start();
        wheelProductionLine.start();
        glassProductionLine.start();
        assemblyLine.start();


    }
}
