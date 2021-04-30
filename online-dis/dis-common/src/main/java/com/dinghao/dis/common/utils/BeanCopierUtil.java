package com.dinghao.dis.common.utils;



import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;


import java.util.ArrayList;
import java.util.List;

/**
 * 对象转换工具
 * List<?> sources：数据源.
 * @author
 * @param <T>
 */
public class BeanCopierUtil<T> {
    private static Mapper mapper = new DozerBeanMapper();

    public static <T> List<T> copy(List<?> sources, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        if (sources == null) {
            return list;
        }
        for (Object o : sources) {
            T t = copy(o, clazz);
            list.add(t);
        }
        return list;
    }

    public static void copy(Object source, Object target) {
        if (source == null || target == null) {
            return;
        }
        mapper.map(source, target);
    }

    public static <T> T copy(Object source, Class<T> target) {
        if (source == null) {
            return null;
        }
        return mapper.map(source, target);
    }
}
