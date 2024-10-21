package ru.javacode;

import static java.lang.Thread.sleep;

public class ComplexTask {
    public void execute(){
        System.out.println("Thread " + Thread.currentThread().getName() + ": Start executing complex task.");

        try {
            sleep(1000);
        }catch(InterruptedException e){}

        System.out.println("Thread " + Thread.currentThread().getName() + ": Complex task completed.");
    }
}