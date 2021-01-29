package xiaoce.primary.proxy.myaop.service.impl;

import xiaoce.primary.proxy.myaop.annotation.MyTransactional;
import xiaoce.primary.proxy.myaop.service.UserService;

@MyTransactional
public class UserServiceImpl implements UserService
{
    @Override
    public void getUser()
    {
        System.out.println("service执行。。。");
    }
}
