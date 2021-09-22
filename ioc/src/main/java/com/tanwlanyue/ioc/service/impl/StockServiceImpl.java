package com.tanwlanyue.ioc.service.impl;

import com.tanwlanyue.ioc.annotation.Service;
import com.tanwlanyue.ioc.service.StockService;

/**
 * @author zhanglei211 on 2021/9/18.
 */
@Service
public class StockServiceImpl implements StockService {
    @Override
    public void addCount(int count) {
        System.out.println("count" + count);
    }
}
