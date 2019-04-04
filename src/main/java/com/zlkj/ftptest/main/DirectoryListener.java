package com.zlkj.ftptest.main;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @Author sunny
 * @Date 2019-04-04 15:32
 */
@Component
public class DirectoryListener {

    public static void directoryListener() {
        //初始化ftp连接池
        FtpClientConfig clientConfig = new FtpClientConfig("192.168.20.43", 21, "ftpuser", "ftpuser");

        FtpClientFactory ftpClientFactory = new FtpClientFactory(clientConfig);
        try {
            ftpClientFactory.makeClient();
            FTPClientPool pool = new FTPClientPool(ftpClientFactory, 20);
            FTPClient client = pool.borrowClient();
            FTPUtil.connectFtp(client);

            //监听磁盘下面的文件夹变化，如果发生过变化就调用线程池去上传文件

            // 监控目录
            String rootDir = "E:\\traintest";
            // 轮询间隔 5 秒
            long interval = TimeUnit.SECONDS.toMillis(1);
            // 创建过滤器
            IOFileFilter directories = FileFilterUtils.and(
                    FileFilterUtils.directoryFileFilter(),
                    HiddenFileFilter.VISIBLE);
//        IOFileFilter files = FileFilterUtils.and(
//                FileFilterUtils.fileFileFilter(),
//                FileFilterUtils.suffixFileFilter(".txt"));
            IOFileFilter files = FileFilterUtils.and(
                    FileFilterUtils.fileFileFilter());
            IOFileFilter filter = FileFilterUtils.or(directories, files);
//        IOFileFilter filter = FileFilterUtils.and(directories);
            // 使用过滤器
            FileAlterationObserver observer = new FileAlterationObserver(new File(rootDir), filter);
            //不使用过滤器
            //FileAlterationObserver observer = new FileAlterationObserver(new File(rootDir));
            observer.addListener(new FileListener());
            //创建文件变化监听器
            FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
            // 开始监控
            try {
                monitor.start();
                System.out.println("监听器启动了");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
