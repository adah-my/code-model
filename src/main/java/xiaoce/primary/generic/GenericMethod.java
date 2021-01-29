package xiaoce.primary.generic;

import java.util.HashMap;
import java.util.Map;

public class GenericMethod
{
    public <T>T get(String key){
        HashMap<String, T> map = new HashMap<String, T>();
        return map.get(key);
    }
}
