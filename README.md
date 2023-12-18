### This is a framework for simple algorithmic tasks for interview.

`ru.girchev.algorithm.AllTasksExecutor` - contains list of all tasks. If run AllTasksExecutor, then starts all
solutions for each task. Task will be success, if at least one solution is success. <p/>
For example:

- You have `ru.girchev.tasks2021.TaskExample`, then
- You write some solutions `ru.girchev.tasks2021.TaskExampleSolution1`
  and `ru.girchev.tasks2021.TaskExampleSolution2`.
- `ru.girchev.tasks2021.TaskExampleSolution1` is success
  and `ru.girchev.tasks2021.TaskExampleSolution2` is wrong.
- `ru.girchev.algorithm.AllTasksExecutor` find and start `ru.girchev.tasks2021.TaskExampleSolution1`
  and `ru.girchev.tasks2021.TaskExampleSolution2`.
- `ru.girchev.algorithm.AllTasksExecutor` will print _✔TaskExample_ - it's mean success

#### Will be printed:

`✔TaskExample`<br/>
`⚠Test1BinaryGap`<br/>
`...`

### How to write your own solution:

1. Choose a task in `ru.girchev.algorithm.AllTasksExecutor`.
2. Create an implementation of task class. You can run your implementation directly, or you can run
   AllTasksExecutor.

#### Will be printed:

`Start all solutions for condition: 2, 2`<br/>
`✔Solution TaskExampleSolution1: 4 time:0`<br/>
`⚠Solution TaskExampleSolution2: 0 time:1000`

where: time 1000 - it's time of execution solution.<br/>
**!!! max time for task execution is 3 sec!!!**

### Also:

Also you can make your own maven project and take this project as dependency, it maybe will be
cleaner.


