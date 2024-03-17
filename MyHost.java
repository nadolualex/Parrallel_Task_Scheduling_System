/* Implement this class. */

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class MyHost extends Host {
    boolean start = true;

    // ordonez coada dupa prioritate si apoi dupa start
    PriorityBlockingQueue<Task> queue = new PriorityBlockingQueue<>(10, Comparator
            .comparing(Task::getPriority).reversed()
            .thenComparing(Task::getStart));
    Task runningTask;
    @Override
    public void run() {

        while (start) {
            // retin taskul cu prioritatea cea mai mare si timpul de start cel mai mic
            Task task = queue.peek();

            /*
            if(task != null || runningTask != null) {
                System.out.println(" TASK " + task);
                System.out.println(" CURRENT TASK " + runningTask);
            }
            */

            // daca nu am niciun task in runningTask, il iau pe cel mai prioritizat
            if(runningTask == null) {
                runningTask = queue.poll();
            }
            else {
                // verific daca e cazul sa schimb task-ul curent cu unul mai prioritizat
                if (task != null && task.getPriority() > runningTask.getPriority() && runningTask.isPreemptible()) {
                    // aici pun runningTask in coada , iar in locul lui pun task
                    swap(task);
                }
                simulate();
            }
        }
    }

    public void swap(Task task) {
        // System.out.println("schimb taskul " + task + " cu " + runningTask);
        Task aux = runningTask;

        // System.out.println("COADA INAINTE " + queue);
        // System.out.println("TEMP " + temp);
        // System.out.println("RUNNING TASK " + runningTask);

        runningTask = task;
        // System.out.println("RUNNING TASK DUPA POLL" + runningTask);

        task = aux;

        queue.poll();
        queue.add(task);
        // System.out.println(" COADA DUPA " + queue);
    }

    public void simulate() {
        // se simuleaza faptul ca trece o secunda
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // se scade timpul ramas pentru taskul curent
        runningTask.setLeft(runningTask.getLeft() - 100L);

        // daca s-a terminat taskul, se scoate din runningTask
        if (runningTask.getLeft() <= 0) {
            runningTask.finish();
            runningTask = null;
        }
    }

    @Override
    public void addTask(Task task) {
        queue.add(task);
    }

    @Override
    public int getQueueSize() {
        if(runningTask == null) {
            return queue.size();
        }
        else {
            return queue.size() + 1;
        }
    }

    @Override
    public long getWorkLeft() {
        long workLeft = 0;
        if(runningTask != null) {
            workLeft += runningTask.getLeft();
        }
        for(Task task : queue) {
            workLeft += task.getLeft();
        }
        return workLeft;
    }

    @Override
    public void shutdown() {
        start = false;
    }
}
