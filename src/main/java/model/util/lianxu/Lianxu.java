package model.util.lianxu;

/**
 * @author muyi
 * @description:
 * @date 2020-11-24 16:28:22
 */
public class Lianxu {
    public static void main(String[] args) {
        /**
         * 快速判断三个数是否连续
         */
        int[] numbers = new int[]{12, 13, 14};
        System.out.println(Math.abs((numbers[0] - numbers[1]) * (numbers[1] - numbers[2]) * 2 + 1));

        if (Math.abs((numbers[0] - numbers[1]) * (numbers[1] - numbers[2]) * 2 + 1) == 3){
            System.out.println("连续");
        }else{
            System.out.println("不连续");
        }

    }
}
