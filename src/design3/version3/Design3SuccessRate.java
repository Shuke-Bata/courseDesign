package design3.version3;

import java.util.ArrayList;

/**
 * @author Minghua Zhou 周明华
 * @title
 * @description
 * @updateTime 2019/12/28 20:43
 */
public class Design3SuccessRate {
    private static final int RESULT_VALUE = 24;
    private static final int MAX_CARD_NUMBER = 13;


    public static void main(String[] args) {
        int total = getTotal();
        int solutions = getSolutions();
        System.out.println("Total number of combinations is " + total);
        System.out.println("Total number of combinations with solutions is " + solutions);
        System.out.println("The solution ratio is " + (double)solutions / total);
    }

    /**
     * 获取总的组合方案个数
     *
     * @return
     */
    public static int getTotal() {
        int total = 0;
        // AAAA
        total += 13;
        //AAAB  数字有13*12，花色组合有4*4
        total += 13 * 12 * 4 * 4;
        //AABB 数字组合有13*12/2，花色组合有4*3/2 * 4*3/2
        total += 13 * 12 / 2 * 6 * 6;
        //AABC 数字组合有13*12*11/(3*2) *3，花色组合有4*3/2 *4*4
        total += 13 * 12 * 11 / 3 / 2 * 3 * 4 * 3 / 2 * 4 * 4;
        //ABCD 数字组合有13*12*11*10/4/3/2，花色组合有4*4*4*4
        total += 13 * 12 * 11 * 10 / 4 / 3 / 2 * 4 * 4 * 4 * 4;
        return total;
    }

    /**
     * 获取解决方案个数
     *
     * @return 方案个数
     */
    public static int getSolutions() {
        int solutions = 0;

        //AAAA
        for (int i = 1; i <= MAX_CARD_NUMBER; i++) {
            //四张相同的牌
            ArrayList<Double> nums = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                nums.add((double) i);
            }

            if (solve(nums)) {
                solutions++;
            }
        }
        System.out.println("AAAA: " + solutions);
        // AAAB
        for (int i = 1; i <= MAX_CARD_NUMBER; i++) {
            // 三张相同的牌
            ArrayList<Double> nums = new ArrayList<>();
            nums.add((double) i);
            nums.add((double) i);
            nums.add((double) i);
            for (int j = 1; j <= MAX_CARD_NUMBER; j++) {
                if (i == j) {
                    continue;
                }
                // 一张不同的牌
                nums.add((double) j);

                // 如果可以组成24点 就增加它的花色情况
                if (solve(nums)) {
                    solutions += 4 * 4;
                }
                // 删掉最后一张 不同的牌 换成其他的
                nums.remove(nums.size() - 1);
            }
        }
        System.out.println("AAAB: " + solutions);
        // AABB
        for (int i = 1; i < MAX_CARD_NUMBER; i++) {
            // AA 加入两张相同的牌
            ArrayList<Double> nums = new ArrayList<>();
            nums.add((double) i);
            nums.add((double) i);

            // BB 加入后两张相同的牌
            for (int j = i + 1; j <= MAX_CARD_NUMBER; j++) {
                nums.add((double) j);
                nums.add((double) j);

                // 组成了24点 加上花色
                if (solve(nums)) {
                    solutions += 6 * 6;
                }

                // 改变后两站相同的牌 组合
                nums.remove(nums.size() - 1);
                nums.remove(nums.size() - 1);
            }
        }
        System.out.println("AABB: " + solutions);
        // AABC
        for (int i = 1; i <= MAX_CARD_NUMBER; i++) {
            // AA 加入两张相同的牌
            ArrayList<Double> nums = new ArrayList<>();
            nums.add((double) i);
            nums.add((double) i);

            // 第三牌
            for (int j = 1; j < MAX_CARD_NUMBER; j++) {
                if (j == i) {
                    continue;
                }
                nums.add((double) j);
                // 第四张牌，此张牌必须必上一张牌大，否则会引发重复
                for (int k = j + 1; k <= MAX_CARD_NUMBER; k++) {
                    if (k == i) {
                        continue;
                    }
                    nums.add((double) k);

                    // 为24点 花色组合方式
                    if (solve(nums)) {
                        solutions += 6 * 4 * 4;
                    }

                    // 删掉第四张牌 换新
                    nums.remove(nums.size() - 1);
                }

                // 删掉第三张牌 换新
                nums.remove(nums.size() - 1);
            }
        }
        System.out.println("AABC: " + solutions);
        //ABCD
        for (int i = 1; i <= 10; i++) {
            // 四张牌都不相同
            ArrayList<Double> nums = new ArrayList<>();
            nums.add((double) i);
            for (int j = i + 1; j <= 11; j++) {
                nums.add((double) j);
                for (int k = j + 1; k <= 12; k++) {
                    nums.add((double) k);
                    for (int m = k + 1; m <= 13; m++) {
                        nums.add((double) m);

                        // 不同的花色组合方式
                        if (solve(nums)) {
                            solutions += 4 * 4 * 4 * 4;
                        }

                        // 删除第四张牌 换新
                        nums.remove(nums.size() - 1);
                    }

                    //  删除第三张牌 换新
                    nums.remove(nums.size() - 1);
                }

                //  删除第二张牌 换新
                nums.remove(nums.size() - 1);
            }
        }
        System.out.println("ABCD: " + solutions);
        return solutions;
    }

    /**
     * 判断四个数 是否能组成24点
     *
     * @param nums 4个数字
     * @return true 能
     */
    private static boolean solve(ArrayList<Double> nums) {
        if (nums.size() == 0) {
            return false;
        }
        if (nums.size() == 1) {
            // 结果为24
            return nums.get(0) == RESULT_VALUE;
        }

        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.size(); j++) {
                if (i != j) {
                    ArrayList<Double> nums2 = new ArrayList<>();
                    for (int k = 0; k < nums.size(); k++) {
                        // 除开已选择的两个数字
                        if (k != i && k != j) {
                            nums2.add(nums.get(k));
                        }
                    }
                    // 四种运算
                    for (int k = 0; k < 4; k++) {
                        // 避免乘法和加法的交换律
                        if (k < 2 && j > i) {
                            continue;
                        }

                        if (k == 0) {
                            nums2.add(nums.get(i) + nums.get(j));
                        }
                        if (k == 1) {
                            nums2.add(nums.get(i) * nums.get(j));
                        }
                        if (k == 2) {
                            nums2.add(nums.get(i) - nums.get(j));
                        }
                        if (k == 3) {
                            if (nums.get(j) != 0) {
                                nums2.add(nums.get(i) / nums.get(j));
                            } else {
                                continue;
                            }
                        }
                        if (solve(nums2)) {
                            return true;
                        }
                        nums2.remove(nums2.size() - 1);
                    }
                }
            }
        }
        return false;
    }

}
