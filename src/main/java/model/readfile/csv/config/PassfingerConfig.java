package model.readfile.csv.config;

import model.readfile.csv.config.bean.PassfingerConfigBean;
import model.readfile.csv.config.impl.PassfingerConfigImpl;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author muyi
 * @description:
 * @date 2020-11-05 10:45:49
 */
public interface PassfingerConfig {

    static PassfingerConfig getInstance(){
        return PassfingerConfigImpl.getInstance();
    }

    /**
     * 读取配置文件中的关卡
     * @return
     */
    ConcurrentHashMap<String, PassfingerConfigBean> getLevelsFromFile();
    
}
