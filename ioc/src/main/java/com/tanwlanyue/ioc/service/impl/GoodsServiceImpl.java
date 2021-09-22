package com.tanwlanyue.ioc.service.impl;

import com.tanwlanyue.ioc.annotation.Autowired;
import com.tanwlanyue.ioc.annotation.Service;
import com.tanwlanyue.ioc.service.GoodsService;
import com.tanwlanyue.ioc.service.StockService;

/**
 * @author zhanglei211 on 2021/9/18.
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private StockService stockServiceImpl;

    @Override
    public void addCount(int count) {
        stockServiceImpl.addCount(count);
    }
}
