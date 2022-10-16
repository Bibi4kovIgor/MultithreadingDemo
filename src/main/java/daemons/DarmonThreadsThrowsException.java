package daemons;

public class DarmonThreadsThrowsException extends Thread{
    public void run(){
        System.out.println("Name: "+Thread.currentThread().getName());
        System.out.println("Daemon: "+Thread.currentThread().isDaemon());
    }

    public static void main(String[] args){
        DarmonThreadsThrowsException t1=new DarmonThreadsThrowsException();
        DarmonThreadsThrowsException t2=new DarmonThreadsThrowsException();
        t1.start();
        t1.setDaemon(true);//will throw exception here
        t2.start();
    }
}
