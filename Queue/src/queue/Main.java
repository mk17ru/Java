package queue;

import queue.ArrayQueueModule;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //randomTest(30);
        for (int i = 0; i < 100; ++i) {
            String type = sc.next();
            if (type.equals("+")) {
                ArrayQueueModule.enqueue(sc.next());
            }
            if (type.equals("-")) {
                System.out.println(ArrayQueueModule.dequeue());
            }
            if (type.equals("?")) {
                System.out.println(ArrayQueueModule.element());
            }
            if (type.equals("s")) {
                System.out.println(ArrayQueueModule.size());
            }
        }
    }

    public static void randomTest(int count) {
        Random random = new Random();
        for (int i = 0; i < count; ++i) {
            int type = random.nextInt();
            if (type == 0) {
                ArrayQueueModule.enqueue(random.nextInt());
            }
            if (type == 1) {
                System.out.println(ArrayQueueModule.dequeue());
            }
            if (type == 2) {
                System.out.println(ArrayQueueModule.element());
            }
            if (type == 3) {
                System.out.println(ArrayQueueModule.size());
            }
        }
    }
}
