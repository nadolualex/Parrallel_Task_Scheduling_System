# Parallel Task Scheduling System

This project implements a parallel task scheduling system, comprising a dispatcher (load balancer) and multiple computing nodes. The dispatcher receives tasks and assigns them to nodes based on predefined scheduling policies. Each node executes the tasks assigned to it, preempting lower-priority tasks when necessary. The goal is to efficiently allocate tasks to nodes for parallel execution, considering task priorities and preemptibility.

## Dispatcher

The dispatcher is responsible for task distribution among computing nodes. It operates based on one of the following scheduling policies:

### Round Robin (RR)

Tasks are allocated to nodes sequentially, cycling through each node in order. The next task is assigned to the node whose ID is (last_assigned_node_ID + 1) % total_nodes.

### Shortest Queue (SQ)

Tasks are assigned to the node with the shortest queue, considering both pending and running tasks. If multiple nodes have the same queue length, the task is assigned to the node with the lower ID.

### Size Interval Task Assignment (SITA)

Tasks are categorized into three types: short, medium, and long. Each type corresponds to a specific node. Tasks are assigned to the appropriate node based on their type.

### Least Work Left (LWL)

Tasks are assigned to the node with the least remaining work, considering the total duration of tasks yet to be executed on each node. If multiple nodes have the same workload, the task is assigned to the node with the lower ID.

## Computing Nodes

Each computing node executes tasks assigned to it by the dispatcher. Key features of the computing nodes include:

- **Priority-Based Scheduling**: Tasks are executed based on their priority, with higher-priority tasks preempting lower-priority ones if necessary.
  
- **Task Preemption**: Preemptible tasks can be interrupted to execute higher-priority tasks. Preemption occurs when a higher-priority task is assigned to a node currently running a preemptible task.

## Task Properties

Tasks in the system are characterized by the following properties:

- **ID**: Unique integer identifier ranging from 0 to n-1, where n is the total number of tasks.
  
- **Start Time**: The moment the task enters the system.
  
- **Duration**: The time required to execute the task on any computing node.
  
- **Type** (relevant only for SITA): Indicates whether the task is short, medium, or long.
  
- **Priority**: Integer defining the importance of the task. Higher-priority tasks are scheduled first.
  
- **Preemptibility**: Boolean value specifying whether the task can be preempted by higher-priority tasks.

