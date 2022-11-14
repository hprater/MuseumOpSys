//Hayden Prater
//010931631

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Museum implements Runnable{
    private final static Random RANDOM = new Random();
    public static int visitors = 0;
    public static int time = 0;
    public static Semaphore waitingRoomSem;
    public static Semaphore dinosaurRoomSem;
    public static Semaphore zoologyRoomSem;
    public static Semaphore giftRoomSem;

    public static void main(String[] args) {

        if (args.length == 2) {
            try {
                Museum.time = Integer.parseInt(args[0]) * 100;
                Museum.visitors = Integer.parseInt(args[1]);
                Museum.waitingRoomSem = new Semaphore(40);
                Museum.dinosaurRoomSem = new Semaphore(20);
                Museum.zoologyRoomSem = new Semaphore(25);
                Museum.giftRoomSem = new Semaphore(30);
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
            try {
                Thread.sleep(RANDOM.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
// Class attributes here, including:
// id
// Semaphores, mutexes for each room
// variables for counting visitor
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
                Museum.waitingRoomSem.acquire();
                int waiting = 40 - Museum.waitingRoomSem.availablePermits();
                System.out.println("\tVisitor # " + myId + " enters the waiting area and is waiting to enter the museum. We have " + waiting + " waiting.");
                Museum.dinosaurRoomSem.acquire();
                Museum.waitingRoomSem.release();
                int dinoRoom = 20 - Museum.dinosaurRoomSem.availablePermits();
                System.out.println("\t\tVisitor # " + myId + " enters the dinosaur room. There are " + dinoRoom + " watching dinosaurs!");
                Thread.sleep(RANDOM.nextInt(1000 * 10));
                Museum.zoologyRoomSem.acquire();
                Museum.dinosaurRoomSem.release();
                int zoologyRoom = 25 - Museum.zoologyRoomSem.availablePermits();
                System.out.println("\t\t\tVisitor # " + myId + " enters the zoology room. There are " + zoologyRoom + " enjoying animals!");
                Thread.sleep(RANDOM.nextInt(1000 * 15));
                Museum.giftRoomSem.acquire();
                Museum.zoologyRoomSem.release();
                int gift = 30 - Museum.giftRoomSem.availablePermits();
                System.out.println("\t\t\t\tVisitor # " + myId + " enters Gift room. There are " + gift + " looking to buy!");
                Thread.sleep(RANDOM.nextInt(1000 * 30));
                Museum.giftRoomSem.release();
                System.out.println("\t\t\t\t\tVisitor # " + myId + " exits the system");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
}

