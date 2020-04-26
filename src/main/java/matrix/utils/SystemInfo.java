package matrix.utils;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

public class SystemInfo {

    private static OperatingSystemMXBean osMBean
            = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    public static int cpu() {
        return (int) Math.round(osMBean.getSystemCpuLoad()*100);
    }

    public static int cpuProcess() {
        return (int) Math.round(osMBean.getProcessCpuLoad()*100);
    }

    public static int ram() {
        return Math.round((float)(osMBean.getFreePhysicalMemorySize()/100000) / (osMBean.getTotalPhysicalMemorySize()/100000)*100);
    }

    public static void info() {

    }
}
