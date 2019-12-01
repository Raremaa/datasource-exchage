package com.masaiqi.exchage;

import com.masaiqi.exchage.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 配置一个在SpringBoot项目启动后跟随启动的类
 *
 * @author sq.ma
 * @date 2019/12/1 下午3:52
 */
@Component
public class CommandRun implements CommandLineRunner {

    @Autowired
    private MainService mainService;

    @Override
    public void run(String... args) throws Exception {
        mainService.doJobDev();
        mainService.doJobProd();
        mainService.doJobDev();
        mainService.doJobProd();
    }

}
