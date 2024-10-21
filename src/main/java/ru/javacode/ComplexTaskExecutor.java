package ru.javacode;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ComplexTaskExecutor {
    private final CyclicBarrier barrier;
    private final ExecutorService executor;

    public ComplexTaskExecutor(int tasks) {
        this.barrier = new CyclicBarrier(tasks, ()->{
            System.out.println("Barrier reached.");
        });
        this.executor = Executors.newFixedThreadPool(tasks);
    }

    public void executeTasks(int tasks){
        for(int i = 0; i < tasks; i++){
            executor.submit(()->{
                try{
                    new ComplexTask().execute();
                    barrier.await();
                }catch (InterruptedException | BrokenBarrierException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
    }

    public void shutdown(){
        this.executor.shutdown();
    }
}