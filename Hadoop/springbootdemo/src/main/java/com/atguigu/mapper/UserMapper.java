package com.atguigu.mapper;

/**
 * @author Vanas
 * @create 2020-04-30 7:28 下午
 */

import com.atguigu.beans.User;
import tk.mybatis.mapper.common.Mapper;

/**
 * Mybatis的Dao接口，用于定义对数据的增删改查方法
 *
 * 如果基于通用Mapper来实现的话，直接继承通用Mapper的Mapper接口，
 * 此接口已经内置了很多常用的CRUD方法，可以直接去使用。
 */
public interface UserMapper extends Mapper<User> {
}
