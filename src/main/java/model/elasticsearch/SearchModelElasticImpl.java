package model.elasticsearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jodd.util.StringUtil;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetRequest;
import org.elasticsearch.action.get.MultiGetResponse;
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
import org.elasticsearch.client.RestClientBuilder.HttpClientConfigCallback;
import org.elasticsearch.client.RestClientBuilder.RequestConfigCallback;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import model.serialization.SerializerUtils;

public class SearchModelElasticImpl implements SearchModel
{
    private RestHighLevelClient client;
    private String username;
    private String password;
    private String host;
    private int port;
    private String protocol;




    public SearchModelElasticImpl() {
    }


    public void init() {
        RestClientBuilder builder = RestClient.builder(new HttpHost(this.host, this.port, this.protocol));
        builder.setRequestConfigCallback(new RequestConfigCallback() {
            @Override
            public Builder customizeRequestConfig(Builder requestConfigBuilder) {
                return requestConfigBuilder.setConnectTimeout(2000).setSocketTimeout(10000);
            }
        });
        if (StringUtil.isNotBlank(this.username)) {
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(this.username, this.password));
            builder.setHttpClientConfigCallback(new HttpClientConfigCallback() {
                @Override
                public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                    return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                }
            });
        }

        this.client = new RestHighLevelClient(builder);
    }

    public void destroy() {
        try {
            this.client.close();
        } catch (IOException var2) {
        }

    }

    @Override
    public <T> T get(String table, String id, Class<T> clazz) {
        GetRequest request = new GetRequest(table, id);

        try {
            GetResponse response = this.client.get(request, RequestOptions.DEFAULT);
            if (response.isExists()) {
                String json = response.getSourceAsString();
                return SerializerUtils.deserialize(json, clazz);
            } else {
                return null;
            }
        } catch (IOException var7) {
            return null;
        }
    }

    @Override
    public <T> Map<String, T> mGet(String table, Class<T> clazz, String... ids) {
        MultiGetRequest mRequest = new MultiGetRequest();
        String[] var5 = ids;
        int var6 = ids.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            String id = var5[var7];
            mRequest.add(table, id);
        }

        try {
            MultiGetResponse mResponses = this.client.mget(mRequest, RequestOptions.DEFAULT);
            Map<String, T> ret = new HashMap(mResponses.getResponses().length);
            MultiGetItemResponse[] var16 = mResponses.getResponses();
            int var17 = var16.length;

            for(int var9 = 0; var9 < var17; ++var9) {
                MultiGetItemResponse mResponse = var16[var9];
                GetResponse response = mResponse.getResponse();
                if (response.isExists()) {
                    String json = response.getSourceAsString();
                    ret.put(response.getId(), SerializerUtils.deserialize(json, clazz));
                } else {
                    ret.put(response.getId(), (T) null);
                }
            }

            return ret;
        } catch (IOException var13) {
            return Collections.emptyMap();
        }
    }

    @Override
    public boolean save(String table, String id, Object data) {
        IndexRequest request = new IndexRequest(table);
        request.id(id);
        String json = SerializerUtils.serializeAsString(data);
        request.source(json, XContentType.JSON);

        try {
            IndexResponse indexResponse = this.client.index(request, RequestOptions.DEFAULT);
            return indexResponse.status() == RestStatus.CREATED;
        } catch (IOException var7) {
            return false;
        }
    }

    @Override
    public boolean saveOrupdate(String table, String id, Object data) {
        UpdateRequest request = new UpdateRequest(table, id);
        request.timeout(TimeValue.timeValueSeconds(2L));
        String json = SerializerUtils.serializeAsString(data);
        request.upsert(json, XContentType.JSON);

        try {
            UpdateResponse response = this.client.update(request, RequestOptions.DEFAULT);
            return response.status() == RestStatus.CREATED;
        } catch (IOException var7) {
            return false;
        }
    }

    @Override
    public boolean update(String table, String id, Object data) {
        UpdateRequest request = new UpdateRequest(table, id);
        request.timeout(TimeValue.timeValueSeconds(2L));
        String json = SerializerUtils.serializeAsString(data);
        request.doc(json, XContentType.JSON);

        try {
            UpdateResponse response = this.client.update(request, RequestOptions.DEFAULT);
            return response.status() == RestStatus.CREATED;
        } catch (IOException var7) {
            return false;
        }
    }

    @Override
    public boolean delete(String table, String id) {
        DeleteRequest request = new DeleteRequest(table, id);

        try {
            DeleteResponse response = this.client.delete(request, RequestOptions.DEFAULT);
            return response.status() == RestStatus.OK;
        } catch (IOException var5) {
            return false;
        }
    }

    @Override
    public <T> List<T> search(String table, int size, Class<T> clazz, QueryBuilder... builders) {
        SearchSourceBuilder sourceBuilder = SearchSourceBuilder.searchSource();
        if (builders.length == 1) {
            sourceBuilder.query(builders[0]);
        } else {
            BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
            QueryBuilder[] var7 = builders;
            int var8 = builders.length;

            for(int var9 = 0; var9 < var8; ++var9) {
                QueryBuilder builder = var7[var9];
                boolBuilder.should(builder);
            }

            sourceBuilder.query(boolBuilder);
        }

        sourceBuilder.size(size);
        sourceBuilder.timeout(TimeValue.timeValueSeconds(2L));
        SearchRequest searchRequest = new SearchRequest(new String[]{table});
        searchRequest.source(sourceBuilder);

        try {
            SearchResponse searchResponse = this.client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = searchResponse.getHits();
            SearchHit[] searchHits = hits.getHits();
            List<T> ret = new ArrayList(searchHits.length);
            SearchHit[] var11 = searchHits;
            int var12 = searchHits.length;

            for(int var13 = 0; var13 < var12; ++var13) {
                SearchHit hit = var11[var13];
                String json = hit.getSourceAsString();
                T obj = SerializerUtils.deserialize(json, clazz);
                ret.add(obj);
            }

            return ret;
        } catch (IOException var17) {
            return Collections.emptyList();
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
