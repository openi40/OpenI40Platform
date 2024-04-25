package com.openi40.ignite.datastreamfactories;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class StreamingConsumerUtil {
	public static class StreamingConsumer<T> implements Consumer<T> {
		private Stream.Builder<T> builder=Stream.builder();
		@Override
		public void accept(T t) {
			builder.accept(t);
			
		}
	};
	public StreamingConsumerUtil() {
		
	}

}
