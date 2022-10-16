package daemons;

public class DaemonThreads extends Thread {
    public void run(){
        if(Thread.currentThread().isDaemon()){
            System.out.println("daemon thread work");
        }
        else{
            System.out.println("user thread work");
        }
    }
    public static void main(String[] args){
        DaemonThreads t1=new DaemonThreads();//creating thread
        DaemonThreads t2=new DaemonThreads();
        DaemonThreads t3=new DaemonThreads();

        t1.setDaemon(true);//now t1 is daemon thread

        t1.start();//starting threads
        t2.start();
        t3.start();
    }

}
