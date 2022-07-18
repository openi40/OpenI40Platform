/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openi40.scheduler.client.restapi.genericmodel;

import static com.openi40.scheduler.client.restapi.genericmodel.AbstractAddSimulatedOrderResource.LOGGER;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.openi40.scheduler.apsdatacache.ApsDataCacheException;
import com.openi40.scheduler.apsdatacache.IApsDataCache;
import com.openi40.scheduler.apsdatacache.IApsDataCacheAggregator;
import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.engine.OpenI40Exception;
import com.openi40.scheduler.engine.contextualplugarch.ILazyContextualBusinessLogicFactoryLoader;
import com.openi40.scheduler.mapper.DefaultEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IApsDataModelDao;
import com.openi40.scheduler.model.dao.ISelector;
/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author architectures@openi40.org
 *
 *
 * @param <ApsType>
 * @param <DaoType>
 * @param <MappedType> 
 */
public abstract class AbstractApsLookupResource<ApsType  extends IApsObject,DaoType extends IApsDataModelDao<ApsType>,MappedType> extends AbstractBaseResource{
        DaoType dao=null;
        Class<ApsType> apsType=null;
        Class<MappedType> mappedType=null;
        protected AbstractApsLookupResource(IApsDataCacheAggregator apsDataCache, IMapperFactory mapperFactory, ILazyContextualBusinessLogicFactoryLoader lazyAutowire,DaoType dao,Class<ApsType> apsType,Class<MappedType> mappedType ) {
            super(apsDataCache,mapperFactory,lazyAutowire);
            this.dao=dao;
            this.apsType=apsType;
            this.mappedType=mappedType;
        }
        protected boolean isCompanyTopologyFilterOk(String companyId,String plantId,String departmentId,ApsType t) {
            return true;
        }
        @GetMapping("lookup/{dataSourceName}/{dataSetId}/{variantId}/")
	public ResponseEntity<List<MappedType>> lookup(@PathVariable("dataSourceName") String dataSourceName,@PathVariable("dataSetId") String dataSetId, @PathVariable("variantId") String variantId,@RequestParam("code") String code,@RequestParam("description")String description,@RequestParam("companyId") String companyId,@RequestParam("plantId")String plantId,@RequestParam("departmentId") String departmentId){
            try {
                final String _code=code!=null?code.trim().toUpperCase():null;
                final String _description=description!=null?description.trim().toUpperCase():null;
				IApsDataCache apsDataCache = apsDataCacheAggregator.getDataCache(dataSourceName);
                ApsData apsData=apsDataCache.getCachedData(dataSetId, variantId);
                ISelector<ApsType> selector=new ISelector<ApsType>(){
                    @Override
                    public boolean isSelectable(ApsType t) {
                        
                        return (_code==null||( t.getCode()!=null && t.getCode().toUpperCase().startsWith(_code)) ) && 
                                (_code==null||( t.getDescription()!=null && t.getDescription().toUpperCase().indexOf(_description)>=0) ) 
                                && isCompanyTopologyFilterOk( companyId, plantId, departmentId, t);
                    }
                };
                HashMap environment = new HashMap();
                IMapper<ApsType, MappedType> mapper = mapperFactory.createMapper(apsType, mappedType);
                List<ApsType> resultList= dao.findBySelector(selector, apsData);
                List<MappedType> mappedList=new ArrayList<MappedType>();
                for (ApsType apstype : resultList) {
                    MappedType mapped = mapper.mapInstance(apstype, this.mappedType, DefaultEntitiesFactory.Instance , environment, true);
                    mappedList.add(mapped);
                }
                return ResponseEntity.ok().body(mappedList);
            } catch (ApsDataCacheException|DataModelDaoException|MapperException ex) {
                String msg="error in lookup/"+dataSetId+"/"+variantId+"/ for type "+ apsType.getName();
                LOGGER.error(msg, ex);
                throw new OpenI40Exception(msg,ex);
            } 
		
				
	}
}
