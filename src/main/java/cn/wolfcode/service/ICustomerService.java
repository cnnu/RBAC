package cn.wolfcode.service;

import cn.wolfcode.query.CustomerQueryObject;
import cn.wolfcode.query.QueryObject;
import com.github.pagehelper.PageInfo;

public interface ICustomerService {
    PageInfo findAll(QueryObject qo);

    PageInfo findByStatus(CustomerQueryObject qo);
}
