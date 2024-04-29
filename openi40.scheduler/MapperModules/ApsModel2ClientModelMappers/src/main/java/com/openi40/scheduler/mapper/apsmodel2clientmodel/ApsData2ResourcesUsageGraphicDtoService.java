package com.openi40.scheduler.mapper.apsmodel2clientmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.client.graphs.model.GraphicContextNodeDto;
import com.openi40.scheduler.client.graphs.model.ResourcesUsageGraphDto;
import com.openi40.scheduler.client.graphs.model.ResourcesUsageGraphDto.BaseGroupUsageDto;
import com.openi40.scheduler.client.graphs.model.ResourcesUsageGraphDto.ResourceUsageDto;
import com.openi40.scheduler.client.graphs.model.ResourcesUsageGraphDto.ResourcesGroupUsageDto;
import com.openi40.scheduler.client.graphs.model.ResourcesUsageGraphDto.UsageDataItemDto;
import com.openi40.scheduler.client.graphs.model.ResourcesUsageGraphDto.WorkcenterUsageDto;
import com.openi40.scheduler.client.model.ClientDto;
import com.openi40.scheduler.client.model.time.UsageTimeSegmentDto;
import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.engine.contextualplugarch.IContextualBusinessLogicFactory;
import com.openi40.scheduler.engine.contextualplugarch.ILazyContextualBusinessLogicFactoryLoader;
import com.openi40.scheduler.engine.timesheet.ITimesheetLogic;
import com.openi40.scheduler.mapper.AbstractReflectorMapper;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.Department;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.companystructure.WorkCenter;
import com.openi40.scheduler.model.equipment.Group;
import com.openi40.scheduler.model.equipment.ResourceGroup;
import com.openi40.scheduler.model.time.TimesheetReservation;
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
@Service
public class ApsData2ResourcesUsageGraphicDtoService extends AbstractReflectorMapper<ApsData, ResourcesUsageGraphDto>
		implements IMapper<ApsData, ResourcesUsageGraphDto> {
	ILazyContextualBusinessLogicFactoryLoader lcbFLoader=null;
	@Autowired
	public ApsData2ResourcesUsageGraphicDtoService(@Autowired AutowireCapableBeanFactory beanFactory,ILazyContextualBusinessLogicFactoryLoader lcbFLoader) {
		super(beanFactory, ApsData.class, ResourcesUsageGraphDto.class, ApsModel2ClientModelConfiguration.typeMap);
		this.lcbFLoader=lcbFLoader;		
	}
	void basicCopy(IApsObject object,ClientDto dto) {
		dto.setId(object.getId());
		dto.setCode(object.getCode());
		dto.setDescription(object.getDescription());
	}
	void copyGroup(Group group,BaseGroupUsageDto groupDto, IContextualBusinessLogicFactory cf) {
		basicCopy(group,groupDto);
		resourcesCopy(group.getResources(),groupDto.getResources(),cf);
	}
	private void resourcesCopy(List<ITimesheetAllocableObject> resources, List<ResourceUsageDto> resourcesDtos, IContextualBusinessLogicFactory cf) {
		for (ITimesheetAllocableObject rc : resources) {
			ResourceUsageDto rcUsageDto=new ResourceUsageDto();
			basicCopy(rc, rcUsageDto);
			resourcesDtos.add(rcUsageDto);
			ITimesheetLogic timesheetLogic = cf.create(ITimesheetLogic.class, rc, rc.getContext());
			List<TimesheetReservation> reservations = timesheetLogic.getReservations(rc);
			TreeMap<Long, List<UsageTimeSegmentDto>> usages=new TreeMap<Long, List<UsageTimeSegmentDto>>();
			for (TimesheetReservation reservation : reservations) {
				UsageTimeSegmentDto usageSegment=new UsageTimeSegmentDto();
				usageSegment.setReservedTime(reservation.getReservedTime());
				usageSegment.setUsageQty(1.0);
				usageSegment.setUtcStartDateTime(reservation.getStartDateTime().getTime());
				usageSegment.setUtcEndDateTime(reservation.getEndDateTime().getTime());
				if (!usages.containsKey(usageSegment.getUtcStartDateTime())) {
					usages.put(usageSegment.getUtcStartDateTime(), new ArrayList<UsageTimeSegmentDto>());
				}
				usages.get(usageSegment.getUtcStartDateTime()).add(usageSegment);
			}
			for(List<UsageTimeSegmentDto> usageList:usages.values()) {
				rcUsageDto.getUsages().addAll(usageList);
			}
		}
	}
	@Override
	public void copyValue(ApsData originalObject, ResourcesUsageGraphDto targetObject, IEntitiesFactory entityFactory,
			Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		IContextualBusinessLogicFactory cf = this.lcbFLoader.getComponentFactory();
		
		for(ProductiveCompany pc:originalObject.getProductiveCompanies()) {
			GraphicContextNodeDto<UsageDataItemDto> pcGraphs=new GraphicContextNodeDto<ResourcesUsageGraphDto.UsageDataItemDto>();
			targetObject.getRoots().add(pcGraphs);
			basicCopy(pc, pcGraphs);
			for(Plant plant:pc.getPlants()) {
				GraphicContextNodeDto<UsageDataItemDto> plantDto=new GraphicContextNodeDto<ResourcesUsageGraphDto.UsageDataItemDto>();
				pcGraphs.getNodes().add(plantDto);
				basicCopy(plant, plantDto);
				for(Department dep:plant.getDepartments()) {
					GraphicContextNodeDto<UsageDataItemDto> depDto=new GraphicContextNodeDto<ResourcesUsageGraphDto.UsageDataItemDto>();
					basicCopy(dep, depDto);
					plantDto.getNodes().add(depDto);
					depDto.setGraphicContent(new UsageDataItemDto());
					
					for(WorkCenter wc:dep.getWorkCenters()) {
						WorkcenterUsageDto item = new WorkcenterUsageDto();
						copyGroup(wc,item,cf);
						depDto.getGraphicContent().getWorkcenters().add(item);
					}
					
					for(ResourceGroup rg:dep.getSecondaryResourceGroups()) {
						ResourcesGroupUsageDto item = new ResourcesGroupUsageDto();
						copyGroup(rg, item,cf);
						depDto.getGraphicContent().getSecondaryResourceGroups().add(item);
					}
				}
			}
		}
	}
	
}