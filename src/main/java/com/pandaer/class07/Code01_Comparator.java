package com.pandaer.class07;

import lombok.*;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 比较器： 在系统中往往提供了工程级别的排序算法，如果我们想使用这个工程级别的排序算法，但是这个工程基本的排序算法只支持基本数据类型
 * 这个时候我们可以自定义比较器为这个排序算法提供判断标准，从而达到使用这个排序算法的能力
 */
public class Code01_Comparator {


    /**
     * 年龄升序比较器
     */
    class AgeAescComparator implements Comparator<User> {
        /**
         * 比较规则：
         * 如果返回负数 第一个参数在前面
         * 如果返回正数 第一个参数在后面
         * 如果返回 0  谁前谁后无所谓
         * @return 一个数
         */
        @Override
        public int compare(User o1, User o2) {
            return o1.getAge() - o2.getAge();
        }
    }

    @Test
    public void test01() {
        List<User> list = new ArrayList<>();
        list.add(new User("user1",18,1.8));
        list.add(new User("user2",22,1.5));
        list.add(new User("user3",13,1.2));
        System.out.println("原数组：" + list);
        list.sort(new AgeAescComparator());
        System.out.println("排序后的数组：" + list);
    }


    /**
     * 有序表需要指定比较规则，才能加入元素，对于自定义的不支持比较的类型，同样可以自定义比较器来帮助有序表比较
     */

    @Test
    public void test02() {
        Map<User,String> treeMap = new TreeMap<>(new AgeAescComparator());
        User user1 = new User("user1", 18, 1.8);
        User user2 = new User("user2",22,1.5);
        User user3 = new User("user3",13,1.2);
        treeMap.put(user1,user1.getName());
        treeMap.put(user2,user2.getName());
        treeMap.put(user3,user3.getName());
        System.out.println("有序表================================");
        for (Map.Entry<User, String> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + " ===== " + entry.getValue());
        }
    }




    @AllArgsConstructor
    @Getter
    @Setter
    class User {
        private String name;
        private int age;
        private double length;

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", length=" + length +
                    '}';
        }
    }


}
