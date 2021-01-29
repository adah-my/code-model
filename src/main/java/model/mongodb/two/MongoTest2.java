package model.mongodb.two;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.HashMap;

public class MongoTest2
{
    public static void main(String[] args)
    {

        MongoDbUtil2 mongoDbUtil = MongoDbUtil2.getInstance();
        MongoCollection<Document> collection = mongoDbUtil.getDatabase("mydb").getCollection("test");

        // 添加数据
//        testInsert(collection);

        // 查询数据
        // basicDBObject
//        find(collection);

        // find Filters
//        findFilters(collection);

        // 更新数据
        // basicDBObject
//        update(mongoDbUtil, collection);

        // update Filters
//        updateFilters(collection);

        // 删除数据
        // basicDBObject
//        delete(collection);

        // delete Filters
//        deleteFilters(collection);

    }

    private static void deleteFilters(MongoCollection<Document> collection)
    {
        //筛选条件对象
//        Bson bson = Filters.eq("sex", "nv");
//        // 查找到多条数据时，删除找到的第一条
//        collection.deleteOne(bson);

        ArrayList<Bson> list = new ArrayList<>();
        list.add(Filters.eq("sex", "nv"));
        list.add(Filters.gte("age", 10));
        Bson bson1 = Filters.and(list);
        // 删除单条
        collection.deleteOne(bson1);
        // 删除多条
        collection.deleteMany(bson1);
    }

    private static void delete(MongoCollection<Document> collection)
    {
        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.put("name", "木易2号");
        basicDBObject.put("sex", "nan");
        // 删除单条数据
        collection.deleteOne(basicDBObject);
        // 删除多条数据
        collection.deleteMany(basicDBObject);
    }

    private static void updateFilters(MongoCollection<Document> collection)
    {
        //筛选条件
        ArrayList<Bson> list = new ArrayList<>();
        list.add(Filters.eq("sex", "nv"));
        list.add(Filters.lte("age", 20));
        Bson bson = Filters.and(list);

        Document document = new Document();
        document.append("$set", new Document("name", "木易2号"));
        // 更新单条数据
//        collection.updateOne(bson, document);
        // 更新多条数据
        collection.updateMany(bson, document);
    }

    private static void update(MongoDbUtil2 mongoDbUtil, MongoCollection<Document> collection)
    {
        // 更新单条数据
        //筛选条件对象
        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.put("name", "木易");
        basicDBObject.put("age", 10);
        // 更新内容
        Document document = new Document();
        document.append("$set", new Document("sex", "nv"));
//        collection.updateOne(basicDBObject, document);
        // 更新多条数据
        collection.updateMany(basicDBObject, document);
        mongoDbUtil.close();
    }

    private static void findFilters(MongoCollection<Document> collection)
    {
        // 单个筛选条件
        Bson bson = Filters.eq("name", "木易");
        FindIterable<Document> documents = collection.find(bson);
        for (Document document : documents){
            System.out.println(document);
        }
        // 多个筛选条件
        ArrayList<Bson> list = new ArrayList<>();
        list.add(Filters.eq("name", "木易"));
        list.add(Filters.gt("age", 10));
        list.add(Filters.lte("age", 19));
        Bson bsons = Filters.and(list);
        FindIterable<Document> documents1 = collection.find(bsons);
        for (Document document : documents1){
            System.out.println(document);
        }
    }

    private static void find(MongoCollection<Document> collection)
    {
        // 查询集合中所有数据
//        FindIterable<Document> documents = collection.find();
//        for (Document document : documents){
//            System.out.println(document.toJson(JsonWriterSettings.builder().build()));
//        }

        // 查询指定数据
        // basicDBObject
        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.put("name", "木易");
        basicDBObject.put("age", 19);

        FindIterable<Document> documents1 = collection.find(basicDBObject);
        for (Document document : documents1){
            System.out.println(document.toJson());
        }
        // 获取指定数据的第一条数据
        Document first = documents1.first();
    }

    private static void testInsert(MongoCollection<Document> collection)
    {
        // 添加单条数据
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "木易");
        map.put("age", 19);
        map.put("sex", "nan");
        Document document = new Document(map);
        collection.insertOne(document);

        // 添加多条数据
        ArrayList<Document> documents = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            HashMap<String, Object> mMap = new HashMap<>();
            map.put("name", "木易"+i);
            map.put("age", 10);
            map.put("sex", "nan");
            Document doc = new Document(map);
            documents.add(doc);
        }
        collection.insertMany(documents);
    }
}
