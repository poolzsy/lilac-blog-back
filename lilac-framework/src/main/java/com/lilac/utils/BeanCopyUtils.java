package com.lilac.utils;

import java.util.List;

public class BeanCopyUtils {
    private BeanCopyUtils() {
    }

    /**
     * 拷贝对象
     *
     * @param source
     * @param clazz
     * @return
     */
    public static <T> T copyBean(Object source, Class<T> clazz) {
        T target = null;
        try {
            target = clazz.newInstance();
            org.springframework.beans.BeanUtils.copyProperties(source, target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return target;
    }

    /**
     * 拷贝列表
     *
     * @param clazz
     * @return
     */
    public static <O,T> List<T> copyBeanList(List<O> list, Class<T> clazz) {
        return list.stream()
                .map(o -> copyBean(o, clazz))
                .toList();
    }
}
