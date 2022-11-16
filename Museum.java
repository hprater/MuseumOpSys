//Hayden Prater
//010931631

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Museum implements Runnable{
    public static int visitors = 0;
    public static int time = 0;

    //Semaphores
    public static Semaphore waitingRoomSem;
    public static Semaphore dinosaurRoomSem;
    public static Semaphore zoologyRoomSem;
    public static Semaphore giftRoomSem;

    //Semaphore permits
    public static int waitingRoomPermits = 40;
    public static int dinosaurRoomPermits = 20;
    public static int zoologyRoomPermits = 25;
    public static int giftRoomPermits = 30;

    //Mutex's
    public static Semaphore waitingRoomMutex = new Semaphore(1);
    public static Semaphore dinosaurRoomMutex = new Semaphore(1);
    public static Semaphore zoologyRoomMutex = new Semaphore(1);
    public static Semaphore giftRoomMutex = new Semaphore(1);
    public static Semaphore waitingRoomMutexRel = new Semaphore(1);
    public static Semaphore dinosaurRoomMutexRel = new Semaphore(1);
    public static Semaphore zoologyRoomMutexRel = new Semaphore(1);
    public static Semaphore giftRoomMutexRel = new Semaphore(1);


    public static void main(String[] args) {

        if (args.length == 2) {
            try {
                Museum.time = Integer.parseInt(args[0]) * 100;
                Museum.visitors = Integer.parseInt(args[1]);
                Museum.waitingRoomSem = new Semaphore(waitingRoomPermits);
                Museum.dinosaurRoomSem = new Semaphore(dinosaurRoomPermits);
                Museum.zoologyRoomSem = new Semaphore(zoologyRoomPermits);
                Museum.giftRoomSem = new Semaphore(giftRoomPermits);
                System.out.println("Using arguments from command line\n"
                        + "Sleep time = " + args[0] + "\n"
                        + "Number of visitors = " + args[1] + "\n");
            } catch (NumberFormatException e) {
                System.err.println("Arguments sleep-time (" + args[0] + ") or number of visitors (" + args[1] + ") must be integers.");
                System.exit(1);
            }
            Runnable program = new Museum();
            Thread programThread = new Thread(program, "program");
            programThread.start();
        }
    }
    public void run() {
        long current;
        long start;
        start = System.currentTimeMillis();
        for (int i = 0; i < visitors; i++) {
            current = System.currentTimeMillis();
            if ((current - start) >= time) {
                break;
            }
            Runnable visitors = new Visitor(i + 1);
            Thread visitorThread = new Thread(visitors, "Visitor" + (i + 1));
            visitorThread.start();
        }
    }
}

class Visitor implements Runnable {
    private final static Random RANDOM = new Random();
    private int myId = 0;

    // Class constructor
      public Visitor(int id ) {
        myId = id;
        }

    // Implement run() method
        public void run() {
            try {
                System.out.println("Visitor # " + myId + " enters the system.");

                Museum.waitingRoomMutex.acquire(); //Protected
                Museum.waitingRoomSem.acquire();
                int waiting = Museum.waitingRoomPermits - Museum.waitingRoomSem.availablePermits();
                System.out.println("\tVisitor # " + myId + " enters the waiting area and is waiting to enter the museum. We have " + waiting + " waiting.");
                Museum.waitingRoomMutex.release(); //Protected


                Museum.dinosaurRoomMutex.acquire(); //Protected
                Museum.dinosaurRoomSem.acquire();
                int dinoRoom = Museum.dinosaurRoomPermits - Museum.dinosaurRoomSem.availablePermits();
                System.out.println("\t\tVisitor # " + myId + " enters the dinosaur room. There are " + dinoRoom + " watching dinosaurs!");
                Museum.dinosaurRoomMutex.release(); //Protected

                Museum.waitingRoomMutexRel.acquire(); //Protected
                Museum.waitingRoomSem.release();
                Museum.waitingRoomMutexRel.release(); //Protected


                Thread.sleep(RANDOM.nextInt(100 * 10)); //Sleep

                Museum.zoologyRoomMutex.acquire(); //Protected
                Museum.zoologyRoomSem.acquire();
                int zoologyRoom = Museum.zoologyRoomPermits - Museum.zoologyRoomSem.availablePermits();
                System.out.println("\t\t\tVisitor # " + myId + " enters the zoology room. There are " + zoologyRoom + " enjoying animals!");
                Museum.zoologyRoomMutex.release(); //Protected

                Museum.dinosaurRoomMutexRel.acquire(); //Protected
                Museum.dinosaurRoomSem.release();
                Museum.dinosaurRoomMutexRel.release(); //Protected


                Thread.sleep(RANDOM.nextInt(100 * 15)); //Sleep

                Museum.giftRoomMutex.acquire(); //Protected
                Museum.giftRoomSem.acquire();
                int gift = Museum.giftRoomPermits - Museum.giftRoomSem.availablePermits();
                System.out.println("\t\t\t\tVisitor # " + myId + " enters Gift room. There are " + gift + " looking to buy!");
                Museum.giftRoomMutex.release(); //Protected

                Museum.zoologyRoomMutexRel.acquire(); //Protected
                Museum.zoologyRoomSem.release();
                Museum.zoologyRoomMutexRel.release(); //Protected


                Thread.sleep(RANDOM.nextInt(100 * 30)); //Sleep

                Museum.giftRoomMutexRel.acquire(); //Protected
                Museum.giftRoomSem.release();
                System.out.println("\t\t\t\t\tVisitor # " + myId + " exits the system");
                Museum.giftRoomMutexRel.release(); //Protected

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
}

