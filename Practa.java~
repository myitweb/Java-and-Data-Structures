/* Design a class in java for implementing the operations of queue.( insert, delete, display,exit ) */ 
//  http://www.ds-java.thiyagaraaj.com/programs/queue-example-programs-in-java/simple-example-program-for-queue-in-java-using-array-and-class


import java.io.*;

class QueueAction {

    BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
    int items[];
    int i, front = 0, rear = 0, noOfItems, item, count = 0;

    void getdata() {
        try {
            System.out.println("Enter the Limit :");
            noOfItems = Integer.parseInt(is.readLine());
            items = new int[noOfItems];
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void enqueue() {
        try {
            if (count < noOfItems) {
                System.out.println("Enter Queue Element :");
                item = Integer.parseInt(is.readLine());
                items[rear] = item;
                rear++;
                count++;
            } else {
                System.out.println("Queue Is Full");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void dequeue() {
        if (count != 0) {
            System.out.println("Deleted Item :" + items[front]);
            front++;
            count--;
        } else {
            System.out.println("Queue IS Empty");
        }
        if (rear == noOfItems) {
            rear = 0;
        }
    }

    void display() {
        int m = 0;
        if (count == 0) {
            System.out.println("Queue IS Empty");
        } else {
            for (i = front; m < count; i++, m++) {
                System.out.println(" " + items[i]);
            }
        }
    }
}

class QueueProgram {

    public static void main(String arg[]) {
        DataInputStream get = new DataInputStream(System.in);
        int choice;
        QueueAction queue = new QueueAction();
        queue.getdata();
        System.out.println("Queue\n\n");
        try {
            do {
                System.out.println("1.Enqueue\n2.Dequeue\n3.Display\n4.Exit\n");
                System.out.println("Enter the Choice : ");
                choice = Integer.parseInt(get.readLine());
                switch (choice) {
                    case 1:
                        queue.enqueue();
                        break;
                    case 2:
                        queue.dequeue();
                        break;
                    case 3:
                        queue.display();
                        break;
                }
            } while (choice != 4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
