package com.openi40.scheduler.outputchannels.streamoutputs;

public interface IOutputTransactionWrapper {
	public void begin() throws OutputTransactionException;
	public void commit() throws OutputTransactionException;
	public void rollback() throws OutputTransactionException;
	public void close() throws OutputTransactionException;
}
