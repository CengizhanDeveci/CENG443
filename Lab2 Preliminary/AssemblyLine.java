import java.util.concurrent.Semaphore;

public class AssemblyLine implements Runnable
{
    private final Semaphore startSemaphore;
    private final Semaphore engineSemaphore;
    private final Semaphore wheelSemaphore;
    private final Semaphore glassSemaphore;

    AssemblyLine(Semaphore startSemaphore, Semaphore engineSemaphore, Semaphore wheelSemaphore, Semaphore glassSemaphore) 
    {
        this.startSemaphore = startSemaphore;
        this.engineSemaphore = engineSemaphore;
        this.wheelSemaphore = wheelSemaphore;
        this.glassSemaphore = glassSemaphore;
    }

    @Override
    public void run() 
    {
        try
        {
            while (true) 
            {

                startSemaphore.acquire(3);

                Actions.startAssemblyLine();
                Actions.assemble();
                Actions.stopAssemblyLine();

                engineSemaphore.release();
                wheelSemaphore.release();
                glassSemaphore.release();

            }    
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
            System.out.println("Interrupted");
        }
        
    }
}
