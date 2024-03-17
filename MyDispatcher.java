/* Implement this class. */

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MyDispatcher extends Dispatcher {
    AtomicInteger i = new AtomicInteger(0);
    public MyDispatcher(SchedulingAlgorithm algorithm, List<Host> hosts) {
        super(algorithm, hosts);
    }

    @Override
    public synchronized void addTask(Task task) {
        if (algorithm == SchedulingAlgorithm.ROUND_ROBIN) {
            hosts.get((i.incrementAndGet()) % hosts.size()).addTask(task);
            // System.out.println(" hosts after get " + hosts);
        }

        //System.out.println(" hosts " + hosts);


        if (algorithm == SchedulingAlgorithm.SHORTEST_QUEUE) {
            hosts.stream().min((h1, h2) -> h1.getQueueSize() - h2.getQueueSize()).get().addTask(task);
        }

        if (algorithm == SchedulingAlgorithm.SIZE_INTERVAL_TASK_ASSIGNMENT) {
            if (task.getType()==TaskType.SHORT) {
                hosts.get(0).addTask(task);
            } else if (task.getType()==TaskType.MEDIUM) {
                hosts.get(1).addTask(task);
            } else {
                hosts.get(2).addTask(task);
            }
        }

        if (algorithm == SchedulingAlgorithm.LEAST_WORK_LEFT) {
            hosts.stream().min((h1, h2) -> Math.toIntExact(h1.getWorkLeft() - h2.getWorkLeft())).get().addTask(task);
        }
    }
}
