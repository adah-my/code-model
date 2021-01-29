package xiaoce.middle.idea.pluging;

public class Pojo
{


    /**
     * code : 200
     * message : 成功
     * data : {"id":199,"status":1,"createTime":"2020-01-13T10:33:02.000GMT+08:00","updateTime":"2020-01-13T10:33:21.000GMT+08:00","isDelete":1,"page":1,"pageSize":10,"userId":49,"templetName":"测试模板","templetId":20,"resumeStyle":3,"name":"bravo","mobile":"182575xxxxx","email":"52xxxxxxx@qq.com","address":"杭州市西湖区"}
     * serverTime : 1579140259345
     */

    private int code;
    private String message;
    private DataBean data;
    private long serverTime;

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public DataBean getData()
    {
        return data;
    }

    public void setData(DataBean data)
    {
        this.data = data;
    }

    public long getServerTime()
    {
        return serverTime;
    }

    public void setServerTime(long serverTime)
    {
        this.serverTime = serverTime;
    }

    public static class DataBean
    {
        /**
         * id : 199
         * status : 1
         * createTime : 2020-01-13T10:33:02.000GMT+08:00
         * updateTime : 2020-01-13T10:33:21.000GMT+08:00
         * isDelete : 1
         * page : 1
         * pageSize : 10
         * userId : 49
         * templetName : 测试模板
         * templetId : 20
         * resumeStyle : 3
         * name : bravo
         * mobile : 182575xxxxx
         * email : 52xxxxxxx@qq.com
         * address : 杭州市西湖区
         */

        private int id;
        private int status;
        private String createTime;
        private String updateTime;
        private int isDelete;
        private int page;
        private int pageSize;
        private int userId;
        private String templetName;
        private int templetId;
        private int resumeStyle;
        private String name;
        private String mobile;
        private String email;
        private String address;

        public int getId()
        {
            return id;
        }

        public void setId(int id)
        {
            this.id = id;
        }

        public int getStatus()
        {
            return status;
        }

        public void setStatus(int status)
        {
            this.status = status;
        }

        public String getCreateTime()
        {
            return createTime;
        }

        public void setCreateTime(String createTime)
        {
            this.createTime = createTime;
        }

        public String getUpdateTime()
        {
            return updateTime;
        }

        public void setUpdateTime(String updateTime)
        {
            this.updateTime = updateTime;
        }

        public int getIsDelete()
        {
            return isDelete;
        }

        public void setIsDelete(int isDelete)
        {
            this.isDelete = isDelete;
        }

        public int getPage()
        {
            return page;
        }

        public void setPage(int page)
        {
            this.page = page;
        }

        public int getPageSize()
        {
            return pageSize;
        }

        public void setPageSize(int pageSize)
        {
            this.pageSize = pageSize;
        }

        public int getUserId()
        {
            return userId;
        }

        public void setUserId(int userId)
        {
            this.userId = userId;
        }

        public String getTempletName()
        {
            return templetName;
        }

        public void setTempletName(String templetName)
        {
            this.templetName = templetName;
        }

        public int getTempletId()
        {
            return templetId;
        }

        public void setTempletId(int templetId)
        {
            this.templetId = templetId;
        }

        public int getResumeStyle()
        {
            return resumeStyle;
        }

        public void setResumeStyle(int resumeStyle)
        {
            this.resumeStyle = resumeStyle;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getMobile()
        {
            return mobile;
        }

        public void setMobile(String mobile)
        {
            this.mobile = mobile;
        }

        public String getEmail()
        {
            return email;
        }

        public void setEmail(String email)
        {
            this.email = email;
        }

        public String getAddress()
        {
            return address;
        }

        public void setAddress(String address)
        {
            this.address = address;
        }
    }
}
