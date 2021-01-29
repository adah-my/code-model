package model.mongodb.one;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.*;

public class MongoTest
{
 
    public static void main(String[] args) {
        MongoDBUtil mongoDBUtil = MongoDBUtil.getMongoDBUtilInstance();
        
        MongoClient meiyaClient = MongoDBUtil.getMongoClientByCredential("127.0.0.1",27017,"mydb","mydb","mydb");
 
        try {
            MongoCollection<Document> collection = mongoDBUtil.getMongoCollection(meiyaClient,"mydb","collection");
            Map<String,Object> insert = new HashMap<>();
               //1、测试增加
        /*    for (int i = 0; i < 10; i++)
            {
                insert.put("name","muyi"+i);
                insert.put("age","20");
                insert.put("date","2018-04-02T09:44:02.658+0000");
                insert.put("school","广东南山");
                mongoDBUtil.insertDoucument(collection,insert);
            }*/
            //2、测试条件、范围、排序查询
      /*      Map<String,Object> conditions = new HashMap<>();
            conditions.put("name","张元");
            Map<String,Integer> compares = new HashMap<>();
            compares.put(MongoConst.GT.getCompareIdentify(),20);
            compares.put(MongoConst.LTE.getCompareIdentify(),28);
            String opAnd = MongoConst.AND.getCompareIdentify();
            Map<String,Object> sortParams = new HashMap<>();
            sortParams.put("age",-1);
            FindIterable<Document> documents = mongoDBUtil.queryDocument(collection,null,opAnd,"age",compares,sortParams,null,2);
            mongoDBUtil.printDocuments(documents);*/
           //3、in查询
           /* List<String> names = new ArrayList(Arrays.asList("mmmyi","zy","zyy"));
            FindIterable<Document> documents = mongoDBUtil.queryDocumentIn(collection,"name",names);
            mongoDBUtil.printDocuments(documents);*/
            //4 批量删除
         /*   Map<String,Object> conditionParams = new HashMap<>();
            conditionParams.put("school","广东南山");
            long count = mongoDBUtil.deleteDocument(collection,true,conditionParams);
            System.out.println(count);*/
            //更新
            Map<String,Object> queryParams = new HashMap<>();
            queryParams.put("name","xiugai");
            Map<String,Object> updateParams = new HashMap<>();
            updateParams.put("name","木易");
            mongoDBUtil.updateDocument(collection,queryParams,updateParams,true);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
 
    }
}