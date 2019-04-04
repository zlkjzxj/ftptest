package com.zlkj.ftptest.main;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

/**
 * @Description ftp监听文件并上传 ，这个东西是我们给人家推送，如果是人家自己抓取的话我们就不管了！
 * <p>
 * 1.项目启动时加载此方法开启文件监听器，并初始化ftp连接池 --》不用连接池的话，不停的创建和销毁连接很消耗资源
 * 2.监听到有文件生成的时候ftp连接池取出一个连接，然后上传文件到服务器--》
 * 单个文件上传的话，肯定需要很多连接，连接池的连接数可能不够,造成阻塞，就看他的文件上传速度了，如果低于拷贝速度就会出现这种情况
 * 如果传输速度大于或等于拷贝速度，创建40个连接应该够用了。
 * 暂时按监听文件来走
 * 监听文件夹的话，文件还没创建完毕就传上去了
 * @Author sunny
 * @Date 2019-04-04 17:00
 */
@Configuration
public class FileConfiguration implements CommandLineRunner {
    @Override
    public void run(String... args) {
        DirectoryListener.directoryListener();
    }
}
