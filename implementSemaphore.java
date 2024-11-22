
public class implementSemaphore {
    private int  permits ;
    private int maxPermits;

    public implementSemaphore(int permits) {

        this.permits = permits;
        this.maxPermits = permits;
    }
    // Acquire a permit, waiting if necessary until one is available
    public synchronized void acquire()throws InterruptedException {
        while (permits == 0) {
            wait();
        }
        permits--;
    }
    public synchronized void release() {
        if (permits < maxPermits) {
            permits++;
            notify();
        }
    }
    public int availablePermits(){
        return permits;
    }
}


