/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openi40.scheduler.client.restapi.lookups;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openi40.scheduler.apsdatacache.IApsDataCacheAggregator;
import com.openi40.scheduler.client.model.material.ProductDto;
import com.openi40.scheduler.client.restapi.genericmodel.AbstractApsLookupResource;
import com.openi40.scheduler.engine.contextualplugarch.ILazyContextualBusinessLogicFactoryLoader;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.model.dao.IProductDao;
import com.openi40.scheduler.model.material.Product;

import io.swagger.annotations.Api;
/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
@RestController
@RequestMapping("schedulerClient/ItemLookupResource")
@Api
public class ItemLookupResource extends AbstractApsLookupResource<Product, IProductDao, ProductDto>{
    @Autowired
    public ItemLookupResource(IApsDataCacheAggregator apsDataCache, IMapperFactory mapperFactory, ILazyContextualBusinessLogicFactoryLoader lazyAutowire,IProductDao dao) {
        super(apsDataCache,mapperFactory,lazyAutowire,dao, Product.class, ProductDto.class);
    }
}
