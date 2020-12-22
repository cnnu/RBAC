package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Customer;
import cn.wolfcode.mapper.CustomerMapper;
import cn.wolfcode.query.CustomerQueryObject;
import cn.wolfcode.query.QueryObject;
import cn.wolfcode.service.ICustomerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public PageInfo findAll(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Customer> customerList = customerMapper.findAll(qo);
        return new PageInfo(customerList);
    }

    @Override
    public PageInfo findByStatus(CustomerQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Customer> customerList = customerMapper.findByStatus(qo);
        return new PageInfo(customerList);
    }
}
