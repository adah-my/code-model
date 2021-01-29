package xiaoce.primary.generic;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * ? extends Father
 * 上边界通配符
 */
public class GenericBorderExtendsDemo
{
    public static void main(String[] args)
    {
        /**
         * List<? extends Human> 指向：只能指向子类型List，比如List<Chinese>, Human是最上边的类
         * List<? extends Human> 取出：接上一条限制，不论指向什么List元素必然是Human及其子类，按Human转
         * List<? extends Human> 存入：简单泛型要是能解决早解决了，还轮到我？直接禁止存入，溜了溜了
         */
        ArrayList<Chinese> chineseArrayList = new ArrayList<>();
        chineseArrayList.add(new Chinese("李健"));
        chineseArrayList.add(new Chinese("李白"));

        ArrayList<Japanese> japaneseArrayList = new ArrayList<>();
        japaneseArrayList.add(new Japanese("asdf"));
        japaneseArrayList.add(new Japanese("ffdf"));

        ArrayList<Human> humans = new ArrayList<>();
        humans.add(new Human("a"));
        /**
         * 与编译器约定，左右两边类型不一致也能赋值，但是有条件：
         * 右边List的参数类型必须是Human的子类
         */
        List<? extends Human> humanList = chineseArrayList;
        humanList = humans;
        System.out.println(humanList.get(0));

        humanList = chineseArrayList;
        Human lee = humanList.get(0);
        Human lbb = humanList.get(1);
        System.out.println(lee+"&"+lbb);

        humanList = japaneseArrayList;
        Human as = humanList.get(0);
        Human bs = humanList.get(1);
        System.out.println(as+"&"+bs);

    }

    @Data
    @AllArgsConstructor
    static class Human{
        private String name;
    }

    public static class Chinese extends Human {
        public Chinese(String name){
            super(name);
        }
    }

    public static class Japanese extends Human {
        public Japanese(String name){
            super(name);
        }
    }
}
