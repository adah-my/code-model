package model.elasticsearch;

import model.elasticsearch.bean.TestBean;

public class ESTest
{
    public static void main(String[] args)
    {
        SearchModelElasticImpl elastic = new SearchModelElasticImpl();
        elastic.setUsername("elastic");
        elastic.setPassword("123456");
        elastic.setHost("localhost");
        elastic.setPort(9200);
        elastic.setProtocol("http");

        elastic.init();

        boolean save = elastic.save("table", "id1", new TestBean("muyi", 10, "12314@qq.com"));
        boolean save1 = elastic.save("table", "id2", new TestBean("code", 10, "12314@qq.com"));
        System.out.println(save+""+save1);


//        TestBean testBean = elastic.get("table", "id1", TestBean.class);
//        System.out.println(testBean);
//        TestBean testBean2 = elastic.get("table", "id2", TestBean.class);
//        System.out.println(testBean2);

//        TestBean testBean = elastic.get("table", "id1", TestBean.class);

//        elastic.update("table", "id1", new TestBean("muyiupdate", 21, "12314@qq.com.update"));
//        TestBean update = elastic.get("table", "id1", TestBean.class);
//        System.out.println(testBean);
//        System.out.println(update);

//        MatchQueryBuilder query = QueryBuilders.matchQuery("name", "muyi").fuzziness(Fuzziness.ZERO);
//        List<TestBean> tables = elastic.search("table", 2, TestBean.class, query);
//        tables.forEach(System.out::println);

//        elastic.delete("table", "id1");
//        elastic.delete("table", "id2");



    }
}
