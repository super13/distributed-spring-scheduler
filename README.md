# distributed-spring-scheduler
A simple extension of spring @Scheduled that can execute tasks on a clustered environment.A new annotation controls.

#### with some strategy:
* leader only
* parallel
* specifcHost

#### eg:
```java
@Scheduled(cron = "0 0/1 * * * ?")
@LeaderTaskType(type = LeaderTaskTypeEnum.LEADER_ONLY)
public void printPerMin(){
    log.info(new Date().toString());

}

@Scheduled(cron = "0 0/1 * * * ?")
@LeaderTaskType(type = LeaderTaskTypeEnum.LEADER_ONLY,specificHost = "192.168.1.3")
//if specificHost set, type discard will be discarded.
public void printPerMin() {
  log.info(new Date().toString());
}
```

it shows a task only been excuted in a leader node.

| nodes        | LEADER_ONLY           | PARALLEL  |specifcHost="192.168.1.3"|
| ------------- |:-------------:| -----:|---:|
| 192.168.1.1(leader)      | 1 | 1 |0|
| 192.168.1.2      | 0      |   1 |0|
| 192.168.1.3 | 0      |   1 |1|
