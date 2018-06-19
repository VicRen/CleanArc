package ren.vic.presentation;

import android.os.HandlerThread;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ren.vic.domain.executor.SDKExecutionThread;

public class SDKThread implements SDKExecutionThread {

    private final HandlerThread handlerThread;

    @Inject
    SDKThread() {
        handlerThread = new HandlerThread("SDK");
        handlerThread.start();
    }

    @Override
    public Scheduler getScheduler() {
        Scheduler dbScheduler;
        synchronized(handlerThread) {
            dbScheduler = AndroidSchedulers.from(handlerThread.getLooper());
        }
        return dbScheduler;
    }
}
