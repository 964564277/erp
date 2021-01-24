package com.bdqn.bus.service.impl;

import com.bdqn.bus.entity.Customer;
import com.bdqn.bus.dao.CustomerMapper;
import com.bdqn.bus.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 惜年画
 * @since 2021-01-15
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

}
