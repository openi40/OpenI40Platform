package com.openi40.dbmodel.easydbbeans;
/**
 * Describe an aggregation Path beetwen two
 * Class rappresented using the AggregatedObjectModel
 * Creation date: (30/06/2001 17.32.04)
 * @author: architectures@openi40.org
 */
public class AggregationDescriptor implements java.io.Serializable
{
	public final static int ONE2MANY = 0;
	public final static int ONE2ONE = 1;
	protected boolean hasTransactionAwareInterface = false;
	private int aggregation_type = 0;
	private java.lang.String aggregation_name;
	private InterfaceDescriptor aggregatedInterface = null;
	private java.lang.Class AggregatedType;
	/**
	 * AggregationDescriptor constructor comment.
	 */
	protected AggregationDescriptor()
	{
		super();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (30/06/2001 17.45.51)
	 * @return com.zconsultancies.threelayers.persistence.objectmodel.InterfaceDescriptor
	 */
	public InterfaceDescriptor getAggregatedInterface()
	{
		return aggregatedInterface;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (01/07/2001 13.40.16)
	 * @return java.lang.Class
	 */
	public java.lang.Class getAggregatedType()
	{
		return AggregatedType;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (30/06/2001 17.37.04)
	 * @return java.lang.String
	 */
	public java.lang.String getAggregation_name()
	{
		return aggregation_name;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (30/06/2001 17.33.45)
	 * @return int
	 */
	public int getAggregation_type()
	{
		return aggregation_type;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (30/06/2001 17.45.51)
	 * @param newAggregatedInterface com.zconsultancies.threelayers.persistence.objectmodel.InterfaceDescriptor
	 */
	protected void setAggregatedInterface(InterfaceDescriptor newAggregatedInterface)
	{
		aggregatedInterface = newAggregatedInterface;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (01/07/2001 13.40.16)
	 * @param newAggregatedType java.lang.Class
	 */
	protected void setAggregatedType(java.lang.Class newAggregatedType)
	{
		AggregatedType = newAggregatedType;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (30/06/2001 17.37.04)
	 * @param newAggregation_name java.lang.String
	 */
	protected void setAggregation_name(java.lang.String newAggregation_name)
	{
		aggregation_name = newAggregation_name;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (30/06/2001 17.33.45)
	 * @param newAggregation_type int
	 */
	protected void setAggregation_type(int newAggregation_type)
	{
		aggregation_type = newAggregation_type;
	}
	/**
	 * @return
	 */
	public boolean isHasTransactionAwareInterface()
	{
		return hasTransactionAwareInterface;
	}
}
