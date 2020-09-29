package com.zhou.design5.dao;

import com.zhou.design5.pojo.ContactPerson;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title ContactDao
 * @description
 * @date 2019/12/28 21:41
 * @modified by:
 */
@Repository
public interface ContactDao {
    /**
     * 获取所有联系人
     *
     * @return
     */
    public List<ContactPerson> getContacts();

    /**
     * 增加联系人
     *
     * @param name        姓名
     * @param phoneNumber 电话号码
     * @param email       电子邮件
     */
    public void addContact(@Param("name") String name, @Param("phoneNumber") String phoneNumber,
                           @Param("email") String email);

    /**
     * 更新联系人
     *
     * @param name
     * @param phoneNumber
     * @param email
     */
    public void updateContact(@Param("name") String name, @Param("phoneNumber") String phoneNumber,
                              @Param("email") String email, @Param("eid") int eid);

    /**
     * 根据名字查找
     *
     * @param name
     * @return
     */
    public List<ContactPerson> findByName(@Param("name") String name);

    /**
     * 根据电话查找
     *
     * @param phone
     * @return
     */
    public List<ContactPerson> findByPhone(@Param("phone") String phone);

    /**
     * 根据电子邮件查找
     *
     * @param email
     * @return
     */
    public List<ContactPerson> findByEmail(@Param("email") String email);

    public void deleteContact(@Param("eid") int eid);
}
