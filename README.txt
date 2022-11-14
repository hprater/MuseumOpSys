//Hayden Prater
//010931631

Project Description

Class Museum:
In the main we are creating a thread for the program, starting it, initializing variables,
and creating the initial output. For security, I made sure only two arguments can be passed
in from the command line with a try/catch block. In the run() function I check the time in
the system in accordance to the time passed in by the user to make sure we are permitting
customers in the program for the given duration. I then create the visitors with a for loop
and set the sleep time of each visitor tread to a random amount between 0.1 and 20 seconds.
Once the visitor is created (which has an ID for reference) I create the visitor thread and
start the tread. This process is repeated until we reach the max number of visitors passed in 
or the sleep time is met.

Class Visitor:
In the try/catch block we are creating our output for the functionality of the visitors actions
and choosing when to add/remove to the waiting, dinosaur, zoology, and gift room semaphores. Since
each visitor will take a random amount of time in each room times a constant (depending on the room),
I sleep the thread for the correct duration after entering that room. To keep track of how many visitors
are in each particular room I created four variables (waiting, dinoRoom, zoologyRoom, and gift). I took
the max number of visitors allowed in each room and subtracted the number of slots still available in each
semaphore.