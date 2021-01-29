//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package model.random;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import jodd.bean.BeanUtil;

public class GameRandomUtils {
    public GameRandomUtils() {
    }

    public static int nextInt(int max) {
        return ThreadLocalRandom.current().nextInt(max);
    }

    public static long nextLong(long max) {
        return ThreadLocalRandom.current().nextLong(max);
    }

    public static boolean satisfy(Integer per, int max) {
        if (null != per && per != 0 && per < max) {
            if (per < 0) {
                return false;
            } else {
                int seed = nextInt(max);
                return seed < per;
            }
        } else {
            return true;
        }
    }

    public static boolean satisfy(Long per, int max) {
        if (null != per && per != 0L) {
            if (per >= (long)max) {
                return true;
            } else if (per < 0L) {
                return false;
            } else {
                int seed = nextInt(max);
                return (long)seed < per;
            }
        } else {
            return false;
        }
    }

    public static boolean satisfy(Long per, long max) {
        if (null != per && per != 0L && per < max) {
            if (per < 0L) {
                return false;
            } else {
                Long value = max;
                int seed = nextInt(value.intValue());
                return (long)seed < per;
            }
        } else {
            return true;
        }
    }

    public static int nextInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public static long nextLong(long min, long max) {
        return ThreadLocalRandom.current().nextLong(min, max);
    }

    public static <T> T nextElement(List<T> list, String fieldName, Integer perMax) {
        Integer seed = nextInt(perMax);
        Integer per = 0;

        try {
            Iterator var5 = list.iterator();

            while(var5.hasNext()) {
                T o = (T) var5.next();
                Integer value = (Integer)BeanUtil.silent.getProperty(o, fieldName);
                per = per + value;
                if (seed < per) {
                    return o;
                }
            }
        } catch (Exception var8) {

        }

        return null;
    }

    public static <T> T nextElement(List<T> list, String fieldName) {
        if (null == list) {
            return null;
        } else if (list.size() == 1) {
            return list.get(0);
        } else {
            try {
                int perMax = 0;

                Integer value;
                for(Iterator var3 = list.iterator(); var3.hasNext(); perMax += value) {
                    T o = (T) var3.next();
                    value = (Integer)BeanUtil.silent.getProperty(o, fieldName);
                }

                return nextElement(list, fieldName, perMax);
            } catch (Exception var6) {
                return null;
            }
        }
    }

    public static <T> T nextElement(List<T> list) {
        if (null != list) {
            if (list.size() == 1) {
                return list.get(0);
            } else {
                int index = nextInt(list.size());
                return list.get(index);
            }
        } else {
            return null;
        }
    }

    public static String nextElement(String[] array) {
        if (array.length == 1) {
            return array[0];
        } else {
            int index = nextInt(array.length);
            return array[index];
        }
    }

    public static int nextIndex(Integer[] pers, int seedMax) {
        int seed = nextInt(seedMax);
        int flg = 0;

        for(int i = 0; i < pers.length; ++i) {
            flg += pers[i];
            if (seed < flg) {
                return i;
            }
        }

        return -1;
    }

    public static int nextIndex(List<Integer> pers, int seedMax) {
        int seed = nextInt(seedMax);
        int flg = 0;

        for(int i = 0; i < pers.size(); ++i) {
            flg += (Integer)pers.get(i);
            if (seed < flg) {
                return i;
            }
        }

        return -1;
    }

    public static <K, V> K nextKey(Map<K, V> map) {
        Set<K> keySet = map.keySet();
        List<K> list = new ArrayList(keySet);
        int index = nextInt(list.size());
        return list.get(index);
    }

    public static <T> List<T> nextList(List<T> list, Integer amount) {
        if (list.size() <= amount) {
            return list;
        } else {
            List<T> temp = new ArrayList(list);
            Collections.shuffle(temp);
            return temp.subList(0, amount);
        }
    }

    public static void main(String[] args)
    {
        List<TestBean> randomList = Arrays.asList(
                new TestBean("1", 1000),
                new TestBean("2", 2000),
                new TestBean("3", 2000),
                new TestBean("4", 5000)
        );
        for (int i = 0; i < 20; i++)
        {
            TestBean random = GameRandomUtils.nextElement(randomList, "weight", 10000);
            System.out.println(random);
        }

    }
}
