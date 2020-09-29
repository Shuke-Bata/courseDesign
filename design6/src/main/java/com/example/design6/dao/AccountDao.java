package com.example.design6.dao;

import com.example.design6.pojo.Account;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title AccountDao
 * @description
 * @date 2019/12/15 15:47
 * @modified by:
 */
@Repository
public interface AccountDao {
    /**
     * 根据指定账号获取账户信息
     *
     * @param account_number 账号
     * @return Account 账户
     */
    public Account getAccount(@Param("account_number") String account_number);

    /**
     * 增加用户账号
     *
     * @param account_number   账号
     * @param account_password 密码
     */
    public void insertAccount(@Param("account_number") String account_number, @Param("account_password") String account_password);

}
