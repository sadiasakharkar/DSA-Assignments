class QueueArray {
    int max_size;
    int front;
    int rear;
    int[] data;

    QueueArray(int size) {
        max_size = size;
        data = new int[max_size];
        front = -1;
        rear = -1;
    }

    boolean isFull() {
        if (rear == max_size - 1) {
            return true;
        } else {
            return false;
        }
    }

    boolean isEmpty() {
        if (front == -1 || front > rear) {
            return true;
        } else {
            return false;
        }
    }

    void enqueue(int item) {
        if (isFull()) {
            System.out.println("Queue Overflow - Cannot enqueue " + item);
        } else {
            if (front == -1) {
                front = 0;
            }
            rear = rear + 1;
            data[rear] = item;
            System.out.println("Enqueued: " + item);
        }
    }

    int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue Underflow - Queue is empty");
            return -1;
        } else {
            int item = data[front];
            System.out.println("Dequeued: " + item);
            front = front + 1;

            // reset to initial state when queue becomes empty
            if (front > rear) {
                front = -1;
                rear = -1;
            }

            return item;
        }
    }

    void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
        } else {
            for (int i = front; i <= rear; i++) {
                System.out.print(data[i] + " ");
            }
            System.out.println();
        }
    }
}

public class QueueDemo {
    public static void main(String[] args) {
        QueueArray q = new QueueArray(3);

        q.dequeue();
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.enqueue(40);
        q.display();

        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.display();

        q.enqueue(99);
        q.display();
    }
}
