package org.example;

import java.lang.management.ManagementFactory;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * 运行后可启动jconsole观察一下
 */
public class Main {

    public static void main(String[] args) throws Exception {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName mxBeanName = new ObjectName("com.example:type=QueueSampler");
        Queue<String> queue = new ArrayBlockingQueue<String>(10);
        queue.add("Request-1");
        queue.add("Request-2");
        queue.add("Request-3");
        QueueSampler mxBean = new QueueSampler(queue);
        mbs.registerMBean(mxBean, mxBeanName);
        System.out.println("Waiting...");
        Thread.sleep(Long.MAX_VALUE);
    }

}
