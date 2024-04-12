package com.openi40.scheduler.inputchannels.dataimporters;

import java.util.function.Consumer;

public interface IDataImporterConsumer<T> extends Consumer<T>{
	public void endOfStream();
}
