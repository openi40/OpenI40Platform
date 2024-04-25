package com.openi40.dbmodel.easydbbeans;

/**
 * Tag Interface for the implementation of an Aggregation Object Model
 * with naming standard
 * Creation date: (30/06/2001 17.34.41)
 * @author: architectures@openi40.org
 */
public interface AggregationObjectModel extends java.io.Serializable {
/**
 * Returns an InterfaceDescriptor describing the interface of an implementing
 * object of this interface
 * Creation date: (30/06/2001 18.03.55)
 * @return com.zconsultancies.threelayers.persistence.objectmodel.InterfaceDescriptor
 * @exception java.beans.IntrospectionException The exception description.
 */
InterfaceDescriptor thlDescribeInterface() throws java.beans.IntrospectionException;
}
