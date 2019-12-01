package com.masaiqi.exchage.service.impl;

import com.masaiqi.exchage.config.DataSourceChoose;
import com.masaiqi.exchage.config.DataSourceType;
import com.masaiqi.exchage.dao.UserDAO;
import com.masaiqi.exchage.entity.User;
import com.masaiqi.exchage.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author sq.ma
 * @date 2019/12/1 下午4:06
 */
@Service
public class MainServiceImpl implements MainService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @DataSourceChoose(DataSourceType.DB_DEV)
    public void doJobDev() {
        User user = User.builder()
                .introduce("我是dev数据源来的~")
                .name("dec")
                .build();
        userDAO.insert(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @DataSourceChoose(DataSourceType.DB_PROD)
    public void doJobProd() {
        User user = User.builder()
                .introduce("我是prod数据源来的~")
                .name("prod")
                .build();
        userDAO.insert(user);
    }
}
