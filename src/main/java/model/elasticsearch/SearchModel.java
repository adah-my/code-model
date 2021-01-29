//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package model.elasticsearch;

import java.util.List;
import java.util.Map;
import org.elasticsearch.index.query.QueryBuilder;

public interface SearchModel {
    boolean save(String var1, String var2, Object var3);

    boolean update(String var1, String var2, Object var3);

    boolean saveOrupdate(String var1, String var2, Object var3);

    boolean delete(String var1, String var2);

    <T> T get(String var1, String var2, Class<T> var3);

    <T> Map<String, T> mGet(String var1, Class<T> var2, String... var3);

    <T> List<T> search(String var1, int var2, Class<T> var3, QueryBuilder... var4);
}
