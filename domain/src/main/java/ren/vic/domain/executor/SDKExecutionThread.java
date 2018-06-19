package ren.vic.domain.executor;

import io.reactivex.Scheduler;

public interface SDKExecutionThread {
    Scheduler getScheduler();
}
