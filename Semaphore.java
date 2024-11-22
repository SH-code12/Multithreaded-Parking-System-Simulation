
public class Semaphore {
    private int  permits =0 ;
    public Semaphore(int permits) {
        this.permits = permits;
    }
    public void acquire() {
        synchronized (this) {
            this.permits -= 1;
        }
    }
    public void release() {
        synchronized (this) {
            this.permits += 1;
        }
    }
    public int availablePermits(){
        return permits;
    }
}
