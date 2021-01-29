package xiaoce.primary.generic;

import java.util.HashMap;
import java.util.Map;

public class Dao <T>
{
    private Map<String, T> map = new HashMap<>();

    public void add(String key, T value){
        map.put(key, value);
    }

    public T remove(String key){
        T value = (T) map.remove(key);
        return value;
    }

    public void update(String key, T value){
        map.put(key, value);
    }

    public T get(String key){
        return (T) map.get(key);
    }

    public <T>T  genericMethod(T t){
        System.out.println(t.toString());
        return t;
    }

    public static void main(String[] args)
    {
        Dao<String> stringDao = new Dao<>();
        stringDao.add("add", "asd");
        System.out.println(stringDao.get("add"));
        stringDao.genericMethod(123456L);
    }
}
