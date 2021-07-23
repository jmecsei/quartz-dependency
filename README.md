A program célja az lenne, hogy properties file-ból lehessen konfigurálni Job-okat.
```yaml
jobs-config.jobs:
  - className: com.example.quartz.job.Job_A
    cronExpression: /5 * * * * ?
  - className: com.example.quartz.job.Job_B
    cronExpression: /5 * * * * ?
```

Ez tökéletesen működik is annyi trükk van a dologban, hogy kell egy Bean amire lehet dependálni a `QuartzDependencyConfiguration` osztályban. Erre azért van
szükség, hogy a Job-ok hamarabb létre jöjjenek, mint a `SchedulerFactoryBean` osztály.
```java
    @Bean(name = "quartzDependentBean")
    @DependsOn("jobs-from-property-file")
    public Object quartzDependentBean() {
        return new Object();
    }
```

**PROBLÉMA**

Szeretném azt megoldani, hogy ha valaki nem definiál egy Job-ot akkor annak legyen egy auto-config -ja `Job_B_AutoConfiguration`.
De ebben azt esetben ez a config mindig hamarabb fut le mint a `JobsConfiguration#creteJobs()` és hibát kapok.