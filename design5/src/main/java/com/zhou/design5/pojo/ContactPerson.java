package com.zhou.design5.pojo;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title ContactPerson
 * @description 手机通讯录联系人类
 * @date 2019/12/11 21:02
 * @modified by:
 */
public class ContactPerson implements Comparable<ContactPerson> {
    private int eid;
    /**
     * 联系人姓名
     */
    private String name;
    /**
     * 联系人电话组，初始默认两个电话
     */
    private String phoneNumber;
    /**
     * 联系人的电子邮件
     */
    private String email;

    public ContactPerson() {
        this(null, null, null);
    }

    public ContactPerson(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getEid() {
        return eid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int compareTo(ContactPerson o) {
        String regex = "^\\w.*";
        if (this.getName().matches(regex) || o.getName().matches(regex)) {
            return this.getName().compareTo(o.getName());
        } else {
            return genPinYin(this.getName()).compareTo(genPinYin(o.getName()));
        }
    }

    public static String genPinYin(String input) {
        if (input == null || input.trim().equals("")) {
            return "";
        }
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        //多音字预先转换 这里可以处理一下多音字
        char[] chars = input.trim().toCharArray();
        StringBuilder output = new StringBuilder();
        try {
            for (char c : chars) {
                if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(c, format);
                    output.append(temp[0]);
                } else {
                    output.append(Character.toString(c));
                }
            }
        } catch (Exception e) {
            System.err.println("拼音转换出现未知异常：" + input);
        }
        return output.toString();
    }
}
