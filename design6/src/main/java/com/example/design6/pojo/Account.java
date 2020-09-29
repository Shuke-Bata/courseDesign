package com.example.design6.pojo;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title Account
 * @description 账号类
 * @date 2019/12/15 10:54
 * @modified by:
 */
public class Account {
    /**
     * 账号
     */
    private String accountNumber;
    /**
     * 密码
     */
    private String accountPassword;
    /**
     * 账号等级
     */
    private int level;

    public Account() {

    }

    public Account(String accountNumber, String accountPassword) {
        this(accountNumber, accountPassword, 1);
    }

    public Account(String accountNumber, String accountPassword, int level) {
        this.accountNumber = accountNumber;
        this.accountPassword = accountPassword;
        this.level = level;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
