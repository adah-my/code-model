package xiaoce.middle.basal.optional;

import lombok.*;
import java.io.Serializable;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 烦人的空指针
 */
public class NullPointerTest
{
    /**
     * 需求：根据用户名查找该用户所在的部门的名称
     *
     * @param args
     */
    public static void main(String[] args)
    {
        // 正确
        String departmentNameOfUser = getDepartmentNameOfUser("test");
        System.out.println(departmentNameOfUser);

        // 错误用法1，这样还是会出现空指针，因为get()后返回的就是裸露的值
        String name = Optional.ofNullable(getUserByName("test")).get().getData().getDepartment().getName();

        /**
         * 错误用法2，把isPresent()当作空指针的方法，又回归以前的if嵌套，毫无意义
         * 个人觉得isPresent()根本不应该暴露出来，只有在Optional内部会使用，普通调用者根本不需要
         */
        Optional<ResultTO<User>> result = Optional.ofNullable(getUserByName("test"));
        if (result.isPresent()){
            User user = result.get().getData();
            if (user != null){
                // ...继续if判断空指针
            }
        }
    }

    /**
     * 假设这是A-Service的服务
     * 这一步很烦人
     *
     * 你会发现，如果一个POJO的层级过深，且恰好作为返回值返回时，调用者将苦不堪言，为了避免空指针不得不写一大堆if判断，也就是被迫做空指针探测
     * @param username
     * @return
     */
    public static String getDepartmentNameOfUser(String username){
        ResultTO<User> resultTO = getUserByName(username);

        // 1.被迫探测空指针
//        if (resultTO != null){
//            User user = resultTO.getData();
//            if (user != null){
//                Department department = user.getDepartment();
//                if (department != null){
//                    return department.getName();
//                }
//            }
//        }
//        return "未知部门";

        // 2.稍微好看一点的写法, 逻辑稍微清晰了一点，但是很啰嗦
//        if (resultTO == null){
//            return "ResultTO为空";
//        }
//        User user = resultTO.getData();
//        if (user == null){
//            return "User为空";
//        }
//        Department department = user.getDepartment();
//        if (department == null){
//            return "Department为空";
//        }
//        return department.getName();

        // 3.有了NullWrapper后的重构写法 ：简洁、优雅 -> Optional
//        return NullWrapper.ofNullable(getUserByName(username))
//                .map(ResultTO::getData)
//                .map(User::getDepartment)
//                .map(Department::getName)
//                .orElse("未知部门");

        /**
         *  4.JDK8 的Optional
         */
        return Optional.ofNullable(getUserByName(username))
                .map(ResultTO::getData)
                .map(User::getDepartment)
                .map(Department::getName)
                .orElse("未知部门");


    }

    

    /**
     * 假设这是B-Service的服务（不用关注具体逻辑，就是随机模拟返回值，可能为null）
     * @param username
     * @return
     */
    private static ResultTO<User> getUserByName(String username)
    {
        if (username == null || "".equals(username)){
            return null;
        }

        Department department;
        User user;

        if (ThreadLocalRandom.current().nextBoolean()){
            department = new Department("总裁办", 10086);
        }else{
            department = null;
        }

        if (ThreadLocalRandom.current().nextBoolean()){
            user = new User("周董", 10, department);
            user.setDepartment(department);
        }else{
            user = null;
        }
        return ResultTO.buildSuccess(user);
    }



    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class User{
        private String name;
        private Integer age;
        private Department department;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Department{
        private String name;
        private Integer code;
    }

    @Getter
    @Setter
    static class ResultTO<T> implements Serializable{
        private Boolean success;
        private String message;
        private T data;

        public static <T> ResultTO<T> buildSuccess(T data){
            ResultTO<T> result = new ResultTO<>();
            result.setSuccess(true);
            result.setMessage("success");
            result.setData(data);
            return result;
        }

        public static <T> ResultTO<T> buildFailed(String message){
            ResultTO<T> result = new ResultTO<>();
            result.setSuccess(false);
            result.setMessage("message");
            result.setData(null);
            return result;
        }
    }

}
