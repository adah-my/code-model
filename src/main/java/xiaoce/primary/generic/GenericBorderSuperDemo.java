package xiaoce.primary.generic;

import lombok.Data;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * ? super Father
 * 下边界通配符
 */
public class GenericBorderSuperDemo
{
    public static void main(String[] args)
    {
        /**
         * List<? super Human> 接收：只能指向父类型List，比如List<Creature>、List<Primate>
         * List<? super Human> 取出：只能转Object
         * List<? super Human> 存入：只能存Human及其子类
         */

        ArrayList<? super Human> humanList = new ArrayList<>();
        // 可以存东西了
        humanList.add(new Human("人类"));

        // 只能指向Human及其父类型的List：灵长类、生物类
//        humanList = new ArrayList<Chinese>();  // error
        humanList = new ArrayList<Primate>();
        humanList = new ArrayList<Creature>();

        // 牛逼，简单泛型和extends都搞不定的存入问题让你super整的服服帖帖
        humanList.add(new Human("女性"));
        humanList.add(new Chinese("中国人"));

        // super: 也不是啦，我虽然能存东西了，但规定只能存入Human及其子类型元素
//        humanList.add(new Primate("灵长类"));
//        humanList.add(new Creature("外星生物"));
//        humanList.add("无关类型，比如String");

        // 简单类型&extends：哈哈哈，你这啥玩意，怎么只能往Object自动转型
        Object object = humanList.get(0);


    }


    @Data
    static class Creature{
        private String name;
        public Creature(String name){
            this.name = name;
        }
    }

    static class Primate extends Creature{

        public Primate(String name)
        {
            super(name);
        }
    }

    static class Human extends Primate
    {
        public Human(String name)
        {
            super(name);
        }
    }

    static class Chinese extends Human
    {
        public Chinese(String name)
        {
            super(name);
        }
    }

    static class Japanese extends Human
    {
        public Japanese(String name)
        {
            super(name);
        }
    }


}
