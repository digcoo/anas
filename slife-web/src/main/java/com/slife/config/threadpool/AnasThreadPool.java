package com.slife.config.threadpool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by cq on 18-1-19.
 */
@Configuration
public class AnasThreadPool {
    private int corePoolSize = 20;

    private int maxPoolSize = 50;


    @Bean("executorService")
    public ExecutorService newThreadPool() {
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(100);
        ExecutorService executorService = new ThreadPoolExecutor(corePoolSize, maxPoolSize, 1000, TimeUnit.MILLISECONDS, queue,new ThreadFactoryImpl("anas"));
        return executorService;
    }

    class ThreadFactoryImpl implements ThreadFactory{
        private AtomicLong aLong = new AtomicLong(1);

        private String prefix;

        public ThreadFactoryImpl(String prefix) {
            this.prefix = prefix;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName(prefix+"-thread-"+aLong.getAndIncrement());
            return thread;
        }
    }

}
