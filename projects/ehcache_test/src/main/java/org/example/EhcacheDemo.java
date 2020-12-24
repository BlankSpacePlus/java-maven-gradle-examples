package org.example;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhcacheDemo {

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        // 创建Cache管理器
        CacheManager manager = CacheManager.create(path + "/src/main/resources/ehcache.xml");
        // 获取指定Cache
        Cache cache = manager.getCache("a");
        // 把一个元素添加到Cache中
        cache.put(new Element("name", "Sam"));
        // 根据Key获取缓存元素
        Element ele = cache.get("name");
        System.out.println("name is " + ele.getObjectValue());
        // 刷新缓存
        cache.flush();
        // 关闭缓存管理器
        manager.shutdown();
    }

}
