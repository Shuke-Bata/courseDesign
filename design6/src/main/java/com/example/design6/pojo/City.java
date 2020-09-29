package com.example.design6.pojo;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title City
 * @description 城市类
 * @date 2019/12/18 0:22
 * @modified by:
 */
public class City implements Comparable<City> {
    /**
     * 城市id
     */
    private int eid;
    /**
     * 城市名称
     */
    private String cityName;

    public City() {
    }

    public City(String cityName) {
        this.cityName = cityName;
    }

    public int getEid() {
        return eid;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public int compareTo(City o) {
        String regex = "^\\w.*";
        if (this.getCityName().matches(regex) || o.getCityName().matches(regex)) {
            return this.getCityName().compareTo(o.getCityName());
        } else {
            return genPinYin(this.getCityName()).compareTo(genPinYin(o.getCityName()));
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
