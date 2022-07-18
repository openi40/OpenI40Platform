package com.openi40.scheduler.input.model.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.openi40.scheduler.common.datamodel.ObjectReferenceConstraint;
import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.input.model.companystructure.DepartmentInputDto;
import com.openi40.scheduler.input.model.companystructure.PlantInputDto;
import com.openi40.scheduler.input.model.companystructure.ProductiveCompanyInputDto;
import com.openi40.scheduler.input.model.companystructure.ResourceGroupInputDto;
import com.openi40.scheduler.input.model.companystructure.WarehouseInputDto;
import com.openi40.scheduler.input.model.companystructure.WorkCenterInputDto;
import com.openi40.scheduler.input.model.cycles.ChangeOverMatrixItemInputDto;
import com.openi40.scheduler.input.model.cycles.CycleModelInputDto;
import com.openi40.scheduler.input.model.equipment.MachineInputDto;
import com.openi40.scheduler.input.model.equipment.SecondaryResourceInputDto;
import com.openi40.scheduler.input.model.material.ProductInputDto;
import com.openi40.scheduler.input.model.material.StockSupplyInputDto;
import com.openi40.scheduler.input.model.material.configuration.PlantProductSettingInputDto;
import com.openi40.scheduler.input.model.material.configuration.ProductiveCompanyProductSettingInputDto;
import com.openi40.scheduler.input.model.material.configuration.WarehouseProductSettingInputDto;
import com.openi40.scheduler.input.model.orders.PeggingInputDto;
import com.openi40.scheduler.input.model.orders.PurchaseOrderInputDto;
import com.openi40.scheduler.input.model.orders.PurchaseOrderLineInputDto;
import com.openi40.scheduler.input.model.orders.SalesOrderInputDto;
import com.openi40.scheduler.input.model.orders.SalesOrderLineInputDto;
import com.openi40.scheduler.input.model.orders.WorkOrderInputDto;
import com.openi40.scheduler.input.model.tasks.TaskInputDto;
import com.openi40.scheduler.input.model.tasks.TaskRelationInputDto;
import com.openi40.scheduler.input.model.time.ApsWindowInputDto;
import com.openi40.scheduler.input.model.time.TimesheetMetaInfoInputDto;
/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author architectures@openi40.org
 *
 */
public class DBInputStructureDumper {
	private static final Class<? extends InputDto> defaultEntitiesToSync[] = new Class[] { ApsWindowInputDto.class,
			TimesheetMetaInfoInputDto.class, ProductiveCompanyInputDto.class, PlantInputDto.class,
			DepartmentInputDto.class, WorkCenterInputDto.class, MachineInputDto.class,
			ChangeOverMatrixItemInputDto.class, ResourceGroupInputDto.class, SecondaryResourceInputDto.class,
			WarehouseInputDto.class, ProductInputDto.class, StockSupplyInputDto.class, CycleModelInputDto.class,
			SalesOrderInputDto.class, PurchaseOrderInputDto.class, ProductiveCompanyProductSettingInputDto.class,
			PlantProductSettingInputDto.class, WarehouseProductSettingInputDto.class, SalesOrderLineInputDto.class,
			PurchaseOrderLineInputDto.class, WorkOrderInputDto.class, TaskInputDto.class, TaskRelationInputDto.class,
			PeggingInputDto.class };
	static List<Class> additionals = new ArrayList<>();
	static List<ConstraintData> constraints = new ArrayList<>();
	static String[][] abbreviations = { { "timestamp", "ts" }, { "product", "prd" }, { "specification", "spec" },
			{ "planned", "pld" }, { "secondary_resource", "rc" }, { "meta_info", "meta" }, { "produced", "prdcd" },
			{ "predefined", "pred" }, { "machine", "mac" }, { "delivery", "del" }, { "department", "dept" },
			{ "_date_time", "_dt" }, { "consumer", "consmr" }, { "producer", "prdcr" }, { "supplier", "suplr" },
			{ "purchase", "purch" }, { "operation", "op" }, { "equipment", "equip" }, { "timesheet", "tsheet" },
			{ "change", "chng" }, { "exception", "exc" }, { "consuming", "cons" }, { "warehouse", "whouse" } };
	static HashMap<Class, List<FieldMapping>> dumpedClasses = new HashMap<>();
	static HashMap<Class, List<RelationMapping>> dumpedRelations = new HashMap<>();

	static class ConstraintData {
		ObjectReferenceConstraint constraint = null;
		PropertyDescriptor property = null;
		Class type = null;
	}

	private static String manageName(String name) {

		char _buffer[] = name.toCharArray();
		StringBuffer outBuffer = new StringBuffer();
		outBuffer.append(Character.toLowerCase(_buffer[0]));
		for (int i = 1; i < _buffer.length; i++) {
			if (Character.isUpperCase(_buffer[i])) {
				outBuffer.append("_");
			}
			outBuffer.append(Character.toLowerCase(_buffer[i]));
		}
		String outName = outBuffer.toString();
		for (int i = 0; i < abbreviations.length; i++) {
			outName = outName.replace(abbreviations[i][0], abbreviations[i][1]);
		}
		return outName;
	}

	private static class FieldMapping {
		FieldMapping(String p, String f) {
			property = p;
			fieldName = f;
		}

		String property = null;
		String fieldName = null;
	}

	private static class RelationMapping {
		PropertyDescriptor property = null;
		Class relatedType = null;
		String jpaType = null;
		String relationType = null;
		String joinProperty = null;
	}

	private static String dumpClass(Class<? extends InputDto> type) throws IntrospectionException {

		BeanInfo info = Introspector.getBeanInfo(type);
		StringBuffer buffer = new StringBuffer();
		String className = type.getSimpleName();
		String tableName = manageName(type.getSimpleName().replace("InputDto", ""));
		buffer.append("-- table for class " + type.getSimpleName().replace("InputDto", "") + "\r\n");
		buffer.append("CREATE TABLE " + tableName + "(\n");
		buffer.append("code VARCHAR(30) NOT NULL,\n");
		buffer.append("description VARCHAR(253),\n");
		List<FieldMapping> fields = new ArrayList<>();
		fields.add(new FieldMapping("code", "code"));
		fields.add(new FieldMapping("description", "description"));
		List<RelationMapping> relations = new ArrayList<>();
		for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
			if (pd.getReadMethod() != null) {
				ObjectReferenceConstraint constraint = pd.getReadMethod()
						.getAnnotation(ObjectReferenceConstraint.class);
				if (constraint != null) {
					ConstraintData constraintData = new ConstraintData();
					constraintData.constraint = constraint;
					constraintData.type = type;
					constraintData.property = pd;
					constraints.add(constraintData);
				}
			}
			if (pd.getName().equalsIgnoreCase("CODE") || pd.getName().equalsIgnoreCase("DESCRIPTION")
					|| pd.getName().equalsIgnoreCase("ID") || pd.getName().equalsIgnoreCase("CLASS")
					|| pd.getName().equalsIgnoreCase("attributesMap")) {

			} else if (InputDto.class.isAssignableFrom(pd.getPropertyType())) {
				if (!additionals.contains(pd.getPropertyType())) {
					additionals.add(pd.getPropertyType());
					RelationMapping relation = new RelationMapping();
					relation.relatedType = pd.getPropertyType();
					relation.jpaType = pd.getPropertyType().getSimpleName().replace("InputDto", "");
					relation.property = pd;
					relation.relationType = "ONE2ONE";
					relations.add(relation);

				}
			} else if (Collection.class.isAssignableFrom(pd.getPropertyType())) {
				Type genericType = pd.getReadMethod().getGenericReturnType();
				if (genericType instanceof ParameterizedType) {
					ParameterizedType ptype = (ParameterizedType) genericType;
					for (Type parType : ptype.getActualTypeArguments()) {
						if (parType instanceof Class) {
							Class thisType = (Class) parType;
							if (InputDto.class.isAssignableFrom(thisType)) {
								if (!additionals.contains(thisType)) {
									additionals.add(thisType);
									RelationMapping relation = new RelationMapping();
									relation.relatedType = thisType;
									relation.jpaType = pd.getPropertyType().getSimpleName().replace("InputDto", "");
									relation.property = pd;
									relation.relationType = "ONE2MANY";
									relations.add(relation);
								}
							}
						}
					}
				}
			} else {
				String fieldName = manageName(pd.getName());
				fields.add(new FieldMapping(pd.getName(), fieldName));
				buffer.append("-- field " + pd.getName() + "\r\n");
				buffer.append(fieldName + " ");
				if (Enum.class.isAssignableFrom(pd.getPropertyType())) {
					buffer.append("VARCHAR(60)");
				} else if (Integer.class.isAssignableFrom(pd.getPropertyType())) {
					buffer.append("INTEGER");
				} else if (Short.class.isAssignableFrom(pd.getPropertyType())) {
					buffer.append("SHORT");
				} else if (Long.class.isAssignableFrom(pd.getPropertyType())) {
					buffer.append("BIGINT");
				} else if (Number.class.isAssignableFrom(pd.getPropertyType())) {
					buffer.append("DOUBLE PRECISION");
				} else if (String.class.isAssignableFrom(pd.getPropertyType())) {
					buffer.append("VARCHAR(253)");
				} else if (java.util.Date.class.isAssignableFrom(pd.getPropertyType())
						|| java.sql.Date.class.isAssignableFrom(pd.getPropertyType())
						|| java.sql.Timestamp.class.isAssignableFrom(pd.getPropertyType())) {
					buffer.append("TIMESTAMP");
				} else if (Boolean.class.isAssignableFrom(pd.getPropertyType())) {
					buffer.append("BOOLEAN");
				} else if (pd.getPropertyType().isPrimitive()) {
					if (pd.getPropertyType().getSimpleName().equalsIgnoreCase("int")) {
						buffer.append("INTEGER");
					} else if (pd.getPropertyType().getSimpleName().equalsIgnoreCase("short")) {
						buffer.append("SHORT");
					} else if (pd.getPropertyType().getSimpleName().equalsIgnoreCase("long")) {
						buffer.append("BIGINT");
					} else if (pd.getPropertyType().getSimpleName().equalsIgnoreCase("boolean")) {
						buffer.append("BOOLEAN");
					} else if (pd.getPropertyType().getSimpleName().equalsIgnoreCase("float")) {
						buffer.append("FLOAT");
					} else if (pd.getPropertyType().getSimpleName().equalsIgnoreCase("double")) {
						buffer.append("DOUBLE PRECISION");
					}
				}
				buffer.append(",\n");
			}
		}
		buffer.append("PRIMARY KEY (code)\n");
		buffer.append(");\n");
		dumpedClasses.put(type, fields);
		dumpedRelations.put(type, relations);
		System.out.println(buffer);
		return buffer.toString();

	}

	static String dumpConstraint(ConstraintData constraint, int idx) {
		StringBuffer out = new StringBuffer();
		String table = constraint.type.getSimpleName().replace("InputDto", "");
		String targetTable = constraint.constraint.referencedType().getSimpleName().replace("InputDto", "");
		String targetColumn = constraint.property.getName();
		out.append("ALTER TABLE " + manageName(table) + " ADD CONSTRAINT CNSTRNT" + idx + " foreign key ("
				+ manageName(targetColumn) + ") references " + manageName(targetTable) + "(code);\n");
		System.out.println(out);
		return out.toString();
	}

	static final String classStart = "package com.openi40.platform.persistence.input.channel.model;\r\n" + "\r\n"
			+ "import javax.persistence.AttributeOverride;\r\n" + "import javax.persistence.AttributeOverrides;\r\n"
			+ "import javax.persistence.Column;\r\n" + "import javax.persistence.Entity;\r\n"
			+ "import javax.persistence.Id;\r\n" + "import javax.persistence.Table;\r\n"
			+ "import javax.persistence.UniqueConstraint;\r\n"
			+ "import com.openi40.platform.persistence.input.channel.StreamLoadRelated;\r\n"
			+ "import com.openi40.platform.persistence.input.channel.StreamLoadRelated.RelationType;\r\n";

	static String dumpType(Class type, List<FieldMapping> fields) throws IntrospectionException {
		StringBuffer outCode = new StringBuffer();
		outCode.append(classStart);
		outCode.append("import " + type.getName() + ";\r\n");
		outCode.append("@Entity\r\n");
		outCode.append("@Table(name = \"" + manageName(type.getSimpleName().replace("InputDto", "")) + "\")\r\n");
		outCode.append("@AttributeOverrides(value = {\r\n");
		for (int i = 0; i < fields.size(); i++) {
			FieldMapping fm = fields.get(i);
			outCode.append("@AttributeOverride(name = \"" + fm.property + "\", column = @Column(name = \""
					+ fm.fieldName + "\"))");
			if (i < fields.size() - 1) {
				outCode.append(",");
			}
			outCode.append("\r\n");
		}
		outCode.append("})\r\n");
		outCode.append("public class " + type.getSimpleName().replace("InputDto", "") + " extends "
				+ type.getSimpleName() + "{\r\n");
		outCode.append("@Id\r\n" + "	@Override\r\n" + "	public String getCode() {\r\n" + "		\r\n"
				+ "		return super.getCode();\r\n" + "	}\r\n" + "	@Override\r\n"
				+ "	public void setCode(String code) {\r\n" + "		\r\n" + "		super.setCode(code);\r\n"
				+ "	}\r\n");
		List<RelationMapping> relations = dumpedRelations.get(type);
		if (relations != null) {
			for (RelationMapping relation : relations) {
				/*
				 * public Class<InputDto> overriddenType(); public Class<InputDto> loadType();
				 * public String joinProperty(); public RelationType relationType(); public
				 * String[] orderOptions() default {};
				 */
				outCode.append("@StreamLoadRelated(overriddenType=" + relation.relatedType.getName()
						+ ".class, loadType=" + relation.jpaType + ".class,relationType=RelationType."
						+ relation.relationType + ",joinProperty=\"" + findRelationField(relation, type) + "\")\r\n");
				outCode.append("@Override\r\npublic void " + relation.property.getWriteMethod().getName() + "(");
				if (relation.relationType != null) {
					switch (relation.relationType) {
					case "ONE2ONE": {
						outCode.append(relation.relatedType.getName() + " p){\r\n");
					}
						break;
					case "ONE2MANY": {
						outCode.append(relation.property.getPropertyType().getName() + "<"
								+ relation.relatedType.getName() + "> p){\r\n");
					}
						break;
					}
				}
				outCode.append("super." + relation.property.getWriteMethod().getName() + "(p);\r\n");
				outCode.append("}\r\n");
			}
		}
		outCode.append("}\r\n");
		System.out.println(outCode.toString());
		return outCode.toString();
	}

	static String findRelationField(RelationMapping relation, Class actualType) throws IntrospectionException {
		BeanInfo relatedTypeInfo = Introspector.getBeanInfo(relation.relatedType);
		for (PropertyDescriptor pd : relatedTypeInfo.getPropertyDescriptors()) {
			if (pd.getReadMethod() != null) {
				ObjectReferenceConstraint constraint = pd.getReadMethod()
						.getAnnotation(ObjectReferenceConstraint.class);
				if (constraint != null && constraint.referencedType().equals(actualType)) {
					return pd.getName();
				}
			}
		}
		return "";
	}

	static String getAbbreviationRulesRemark() {
		StringBuffer out = new StringBuffer();
		out.append("-- abbreviation rules:\r\n");
		for (String[] words : abbreviations) {
			out.append("-- " + words[0] + " --> " + words[1] + "\r\n");
		}
		out.append("-- end abbreviation rules\r\n");
		return out.toString();
	}

	public static void main(String[] args) throws IntrospectionException, IOException {
		File file = new File("generated");
		file.mkdir();
		FileOutputStream fos = new FileOutputStream("generated/create.sql");
		PrintWriter pw = new PrintWriter(fos);
		pw.print(getAbbreviationRulesRemark());
		for (Class type : defaultEntitiesToSync) {
			pw.print(dumpClass(type));
		}

		for (int index = 0; index < additionals.size(); index++) {
			pw.print(dumpClass(additionals.get(index)));
		}
		pw.flush();
		fos.flush();
		fos.close();
		fos = new FileOutputStream("generated/alter.sql");
		pw = new PrintWriter(fos);
		pw.print(getAbbreviationRulesRemark());
		int idx = 0;
		for (ConstraintData constraint : constraints) {
			idx++;
			pw.print(dumpConstraint(constraint, idx));
		}
		pw.flush();
		fos.flush();
		fos.close();
		dumpedClasses.forEach((type, fields) -> {
			String fileName = "generated/" + type.getSimpleName().replace("InputDto", "") + ".java";
			try {
				FileOutputStream fos1 = new FileOutputStream(fileName);
				PrintWriter pw1 = new PrintWriter(fos1);
				pw1.print(dumpType(type, fields));
				pw1.flush();
				pw1.close();
				fos1.flush();
				fos1.close();
			} catch (IOException | IntrospectionException exc) {
				exc.printStackTrace();
			}
		});
	}

}
