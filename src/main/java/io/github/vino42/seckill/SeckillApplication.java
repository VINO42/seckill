package io.github.vino42.seckill;

import cn.hutool.core.date.DateUtil;
import cn.hutool.system.HostInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SeckillApplication {

    public static void main(String[] args) {
        HostInfo hostInfo = new HostInfo();
        String address = hostInfo.getAddress();

        String logHome = String.format("/mnt/logs/%s/%s", "seckill", address);
        String logServceName = String.format("/mnt/logs/%s", "seckill");

        System.setProperty("host.addr", address);
        System.setProperty("log.home", logHome);
        System.setProperty("application.name", "seckill");
        System.setProperty("serviceUpTime", DateUtil.now());
        System.setProperty("log.serviceName", logServceName);

        SpringApplication.run(SeckillApplication.class, args);
    }

}
