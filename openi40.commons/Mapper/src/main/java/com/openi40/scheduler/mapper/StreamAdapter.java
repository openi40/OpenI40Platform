package com.openi40.scheduler.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
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

public class StreamAdapter<AType,BType> {
	Class<AType> aType=null;
	Class<BType> bType=null;
	IMapper<AType, BType> mapper=null;
	Map environment=new HashMap<>();
	public StreamAdapter(Class<AType> aType,Class<BType> bType,IMapper<AType, BType> mapper) {
		this.aType=aType;
		this.bType=bType;
		this.mapper=mapper;
	}
	public static <AType, BType> StreamAdapter<AType, BType> build(Class<AType> aType,Class<BType>  bType,IMapperFactory mapperFactory ) throws MapperException{
		return new StreamAdapter<AType, BType>(aType,bType,mapperFactory.createMapper(aType, bType));
	}
	public Stream<BType> adapt(Stream<AType> aStream){
		Function<AType,BType> mapperFunction=new Function<AType,BType>() {

			@Override
			public BType apply(AType a) {
				try {
					return mapper.mapInstance(a, bType, DefaultEntitiesFactory.Instance, environment, false);
				} catch (MapperException e) {
					throw new IllegalStateException("While mapping:", e);
				}
			}
		};
		return aStream.map(mapperFunction);
	}
	public List<BType> list(Stream<AType> aStream){
		List<BType> outList=new ArrayList<>();
		adapt(aStream).forEach(new Consumer<BType>() {

			@Override
			public void accept(BType arg0) {
				outList.add(arg0);
				
			}
			
		});
		return outList;
	}
}
