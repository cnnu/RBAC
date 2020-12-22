package cn.wolfcode.mapper;

import cn.wolfcode.domain.Customer;
import cn.wolfcode.query.CustomerQueryObject;
import cn.wolfcode.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CustomerMapper {
    List<Customer> findAll(QueryObject qo);

    List<Customer> findByStatus(CustomerQueryObject qo);
}
