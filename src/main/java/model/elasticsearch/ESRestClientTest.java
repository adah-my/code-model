package model.elasticsearch;

import model.elasticsearch.bean.TestBean;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.*;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import model.serialization.SerializerUtils;

import java.io.IOException;
import java.util.*;

public class ESRestClientTest
{
    static String host = "localhost";
    static int port = 9200;
    static String protocol = "http";

    static String username = "elastic";
    static String password = "123456";

    static RestHighLevelClient client;

    public static void main(String[] args)
    {
        RestClientBuilder builder = RestClient.builder(new HttpHost(host, port, protocol));
        // 设置连接超时时间
        builder.setRequestConfigCallback( requestConfigBuidler -> requestConfigBuidler.setConnectTimeout(2000).setSocketTimeout(10000));
        // 设置账号密码
        CredentialsProvider provider = new BasicCredentialsProvider();
        provider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
        builder.setHttpClientConfigCallback( httpClientBuidler -> httpClientBuidler.setDefaultCredentialsProvider(provider));

        client = new RestHighLevelClient(builder);

        String index = "index";
        String id = "id";

        // index索引数据
//        for (int i = 0; i < 10; i++)
//        {
//            TestBean data = new TestBean("aaa"+i, i, "123.com");
//            boolean flag = index(index, id+i, data);
//            System.out.println(flag);
//        }

        // 更新数据
//        TestBean data = new TestBean("bbb", 44, "123.com");
//        boolean update = update(index, id, data);
//        System.out.println(update);

        // 获取指定id数据
//        Class<TestBean> clazz = TestBean.class;
//        TestBean testBean = get(index, id, clazz);
//        System.out.println(testBean);

        // 一次获取多个Id的数据
//        Class<TestBean> clazz = TestBean.class;
//        String[] ids = new String[10];
//        for (int i = 0; i < 10; i++) {
//            ids[i] = id+i;
//        }
//        Map<String, TestBean> beanMap = mget(index, clazz, ids);
//        for (Map.Entry<String, TestBean> entry : beanMap.entrySet()){
//            System.out.println(entry.getKey()+":"+entry.getValue().toString());
//        }


        // 查询数据  - 单个字段
//        // 模糊查询
//        MatchQueryBuilder query = QueryBuilders.matchQuery("name", "aaa").fuzziness(Fuzziness.AUTO);
//        // 精确查询
//        // MatchQueryBuilder query = QueryBuilders.matchQuery("name", "aaa").fuzziness(Fuzziness.ZERO);
//        Class<TestBean> clazz = TestBean.class;
//        List<TestBean> testBeans = search(index, 5, clazz, query);
//        testBeans.forEach(System.out::println);

        // 查询数据  - 多个字段
//        MultiMatchQueryBuilder matchQueryBuilder = QueryBuilders.multiMatchQuery("aaa", "name", "email").fuzziness(Fuzziness.AUTO);
//        Class<TestBean> clazz = TestBean.class;
//        List<TestBean> testBeanList = search(index, 5, clazz, matchQueryBuilder);
//        testBeanList.forEach(System.out::println);

        // 删除数据
//        boolean delete = delete(index, id);
//        System.out.println(delete);

        close();
    }

    /**
     * 查询某字段数据
     * @param index 索引
     * @param size 查询数量
     * @param clazz
     * @param builders
     * @param <T>
     * @return
     */
    public static  <T> List<T> search(String index, int size, Class<T> clazz, QueryBuilder... builders){
        SearchSourceBuilder sourceBuilder = SearchSourceBuilder.searchSource();
        if (builders.length == 1){
            sourceBuilder.query(builders[0]);
        }else{
            BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
            int len = builders.length;

            for (int i = 0; i < len; i++)
            {
                QueryBuilder builder = builders[i];
                boolBuilder.should(builder);
            }

            sourceBuilder.query(boolBuilder);
        }
        sourceBuilder.size(size);
        sourceBuilder.timeout(TimeValue.timeValueSeconds(2L));
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.source(sourceBuilder);

        try
        {
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = searchResponse.getHits();
            SearchHit[] searchHits = hits.getHits();
            List<T> res = new ArrayList<>(searchHits.length);
            int len2 = searchHits.length;

            for (int i = 0; i < len2; i++)
            {
                String json = searchHits[i].getSourceAsString();
                T obj = SerializerUtils.deserialize(json, clazz);
                res.add(obj);
            }

            return res;
        } catch (IOException e)
        {
            return Collections.emptyList();
        }
    }



    /**
     * 一次性获取多个id的值
     * @param index 索引
     * @param clazz 数据类型class
     * @param ids id列表
     * @param <T> 返回类型
     * @return
     */
    private static <T> Map<String, T> mget(String index,Class<T> clazz,  String... ids)
    {
        MultiGetRequest multiGetRequest = new MultiGetRequest();
        for (int i = 0; i < ids.length; i++)
        {
            String id = ids[i];
            multiGetRequest.add(index, id);
        }

        try
        {
            MultiGetResponse multiGetItemResponses = client.mget(multiGetRequest, RequestOptions.DEFAULT);
            HashMap<String, T> res = new HashMap<>(multiGetItemResponses.getResponses().length);
            MultiGetItemResponse[] responses = multiGetItemResponses.getResponses();
            int size = responses.length;

            for (int i = 0; i < size; i++)
            {
                GetResponse response = responses[i].getResponse();
                if (response.isExists()){
                    String json = response.getSourceAsString();
                    res.put(response.getId(), SerializerUtils.deserialize(json, clazz));
                }else{
                    res.put(response.getId(), null);
                }
            }
            return res;
        } catch (IOException e)
        {
            return Collections.emptyMap();
        }

    }

    /**
     * 获取指定Id数据
     * @param index 索引
     * @param id id
     * @param clazz 数据class
     * @param <T> 返回的类型
     * @return
     */
    private static <T> T get(String index, String id, Class<T> clazz)
    {
        GetRequest request = new GetRequest(index, id);
        try
        {
            GetResponse response = client.get(request, RequestOptions.DEFAULT);
            if (response.isExists()){
                String json = response.getSourceAsString();
                return SerializerUtils.deserialize(json, clazz);
            }else{
                return null;
            }
        } catch (IOException e)
        {
            return null;
        }
    }

    private static boolean delete(String index, String id)
    {
        DeleteRequest request = new DeleteRequest(index, id);
        try
        {
            DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
            return response.status() == RestStatus.OK;
        } catch (IOException e)
        {
            return false;
        }
    }

    /**
     * 更新数据
     * @param index
     * @param id
     * @param data
     * @return
     */
    private static boolean update(String index, String id, TestBean data)
    {
        UpdateRequest request = new UpdateRequest(index, id);
        request.timeout(TimeValue.timeValueSeconds(2L));
        String updateJson = SerializerUtils.serializeAsString(data);
        request.doc(updateJson, XContentType.JSON);

        try
        {
            UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
            return response.status() == RestStatus.OK;
        } catch (IOException e)
        {
            return false;
        }
    }

    /**
     * 索引或者修改数据
     * @param index 索引
     * @param id 数据id
     * @param data 数据
     * @return
     */
    private static boolean saveOrupdate(String index, String id, Object data)
    {
        UpdateRequest request = new UpdateRequest(index, id);
        request.timeout(TimeValue.timeValueSeconds(2L));

        String updateJson = SerializerUtils.serializeAsString(data);
        request.upsert(updateJson, XContentType.JSON);
        try
        {
            UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
            return response.status() == RestStatus.CREATED;
        } catch (IOException e)
        {
            return false;
        }
    }

    /**
     * 索引新数据
     * @param index 索引
     * @param id 数据id
     * @param data 数据
     * @return
     */
    private static boolean index(String index, String id, Object data)
    {
        IndexRequest request = new IndexRequest(index);
        request.id(id);
        String json = SerializerUtils.serializeAsString(data);
        request.source(json, XContentType.JSON);
        try
        {
            IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
            return indexResponse.status() == RestStatus.CREATED;
        } catch (IOException e)
        {
            return false;
        }
    }

    /**
     * 关闭连接
     */
    public static void close()
    {
        try{
            client.close();
        }catch (IOException e){

        }
    }
}




























