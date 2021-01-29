package model.mongodb.two;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class MongoDbUtil2
{

    /**
     * mongo客户端
     */
    private MongoClient client;

    Logger log = LoggerFactory.getLogger(MongoDbUtil2.class);

    private volatile static MongoDbUtil2 instance;

    public static MongoDbUtil2 getInstance(){

        if (instance == null){
            synchronized (MongoDbUtil2.class){
                if (instance == null){
                    instance = new MongoDbUtil2();
                }
            }
        }
        return instance;
    }

    public void close(){
        if (client != null){
            client.close();
        }
    }

    /**
     * 无认证获取连接
     */
    public void mongoClient(){
        try
        {
            client = new MongoClient("localhost", 27017);
        } catch (Exception e)
        {
            log.info("无认证获取连接失败, {}", e);
        }
    }

    /**
     * 有账号密码获取连接认证
     */
    public void certifyMongoClient(){
        try
        {
            // ServerAddress()两个参数分别为 服务器地址 和端口
            ServerAddress serverAddress = new ServerAddress("localhost", 27017);
            ArrayList<ServerAddress> addrs = new ArrayList<>();
            addrs.add(serverAddress);

            // MongoCredential.createScramSha1Credential() 三个参数分别为 用户名 数据库名称 密码
            MongoCredential credential = MongoCredential.createScramSha1Credential("mydb","mydb", "mydb".toCharArray());
            ArrayList<MongoCredential> credentials = new ArrayList<>();
            credentials.add(credential);

            // 通过连接认证获取MongoDB连接
            client = new MongoClient(addrs, credentials);
        } catch (Exception e)
        {
            log.info("需要认证的获取连接失败,{}", e);
        }
    }

    /**
     * 获取数据库连接对象
     * @param databaseName
     * @return
     */
    public MongoDatabase getDatabase(String databaseName){
        if (client == null){
            mongoClient();
        }
        return client.getDatabase(databaseName);
    }

}
