package com.zlkj.ftptest.main;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * 文件变化监听器
 * <p>
 * 在Apache的Commons-IO中有关于文件的监控功能的代码. 文件监控的原理如下：
 * 由文件监控类FileAlterationMonitor中的线程不停的扫描文件观察器FileAlterationObserver，
 * 如果有文件的变化，则根据相关的文件比较器，判断文件时新增，还是删除，还是更改。（默认为1000毫秒执行一次扫描）
 *
 * @author Administrator
 */
public class FileListener extends FileAlterationListenerAdaptor {
    private Logger log = LoggerFactory.getLogger(FileListener.class);


    /**
     * 文件创建执行
     */
    @Override
    public void onFileCreate(File file) {
        log.info("[新建]:" + file.getAbsolutePath());
        //监听到文件创建，然后调用线程池把文件传递到ftp
        try {
            FTPUtil.upload(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 文件创建修改
     */
    @Override
    public void onFileChange(File file) {
        log.info("[修改]:" + file.getAbsolutePath());
    }

    /**
     * 文件删除
     */
    @Override
    public void onFileDelete(File file) {
        log.info("[删除]:" + file.getAbsolutePath());
    }

    /**
     * 目录创建
     */
    @Override
    public void onDirectoryCreate(File directory) {
        log.info("[新建]:" + directory.getAbsolutePath());
    }

    /**
     * 目录修改
     */
    @Override
    public void onDirectoryChange(File directory) {
        log.info("[修改]:" + directory.getAbsolutePath());
    }

    /**
     * 目录删除
     */
    @Override
    public void onDirectoryDelete(File directory) {
        log.info("[删除]:" + directory.getAbsolutePath());
    }

    @Override
    public void onStart(FileAlterationObserver observer) {
        // TODO Auto-generated method stub
        super.onStart(observer);
    }

    @Override
    public void onStop(FileAlterationObserver observer) {
        // TODO Auto-generated method stub
        super.onStop(observer);
    }

}