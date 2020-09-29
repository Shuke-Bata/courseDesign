package design2.huffman;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title Data
 * @description 用于存放一个字符及其出现的次数
 * @date 2019/12/23 10:47
 * @modified by:
 */
public class Data implements Comparable<Data> {
    /**
     * 字符
     */
    private char ch;
    /**
     * 字符出现的次数
     */
    private int frequency;

    public Data() {
        this.ch = 0;
        this.frequency = 0;
    }

    public Data(char ch,int frequency) {
        this.ch = ch;
        this.frequency = frequency;
    }

    public char getCh() {
        return ch;
    }

    public void setCh(char ch) {
        this.ch = ch;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    /**
     * 比较字符出现的次数
     *
     * @param o 比较的数据
     * @return -1 小于 0 等于 1 大于
     */
    @Override
    public int compareTo(Data o) {
        if (this.frequency < o.getFrequency()) {
            return -1;
        } else if (this.frequency > o.getFrequency()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Data{" +
                "ch=" + ch +
                ", frequency=" + frequency +
                '}';
    }
}
