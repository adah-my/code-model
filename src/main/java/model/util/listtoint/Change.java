package model.util.listtoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author muyi
 * @description:
 * @date 2020-11-24 16:19:32
 */
public class Change {
    public static void main(String[] args) {

        int[] ints = new int[]{1,2,3};
        /**
         * int[] -> List
         */
        List<Integer> intChanges = Arrays.stream(ints).boxed().collect(Collectors.toList());
        /**
         * List -> int[]
         */
        int[] listChange = intChanges.stream().mapToInt(Integer::intValue).toArray();


    }


}
