//Hayden Prater
//010931631

import java.util.concurrent.Semaphore;

public class Museum {

    // Arguments parsers
// Shared semaphores, mutexes, variables
// Create Visitor objects using for-loop
// Call start() method
//    For (…) {
// Insert here
//    }
// Sleep the program thread using Thread.sleep()
// Insert here
    public static void main(String[] args) {

        if (args.length == 2) {
            try {
                System.out.println("Using arguments from command line\n"
                        + "Sleep time = " + args[0] + "\n"
                        + "Number of Users = " + args[1] + "\n");
            } catch (NumberFormatException e) {
                System.err.println("Arguments sleep-time (" + args[0] + ") or number of users (" + args[1] + ") must be integers.");
                System.exit(1);
            }
        }
    }
}

class Visitor implements Runnable {
// Class attributes here, including:
// id
// Semaphores, mutexes for each room
// variables for counting visitors

// Class constructor
        Visitor( ) {
        // Insert here
        }

// Get waiting room
        private void getWaitingRoom() {
        // Insert here
        }

// Get dinosaur room
        private void getDinosaurRoom() {
        // Insert Here
        }

// Zoology Room
        private void getZoologyRoom() {
        // Insert Here
        }

// Gift Room
        private void getGiftRoom() {
        // Insert Here
        }

// Exit Museum
        private void exitMuseum() {
            // Insert Here
        }

// Implement run() method
        public void run() {
        // Time to call all implemented sub-methods
        }
}
