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
package com.openi40.platform.persistence.input.channel;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class StreamUtils {

	private static class WrapperFactory<T> {
		int count = 0;
		List<T> wrappingArray = null;

		Wrapper<T> create(T info, int batchSize) {
			Wrapper<T> wrapper = new Wrapper<T>();
			wrapper.index = ++count;
			wrapper.info = info;
			if (wrappingArray == null || wrappingArray.size() >= batchSize) {
				wrappingArray = new ArrayList<>();
			}
			wrappingArray.add(info);
			wrapper.fullSample = wrappingArray.size() == batchSize;
			wrapper.wrappingArray = wrappingArray;
			return wrapper;
		}
	}

	private static class Wrapper<T> {
		int index = 0;
		T info = null;
		List<T> wrappingArray = null;
		boolean fullSample = false;
	}

	

	private static class RemainingSupplier<T> implements Supplier<T> {
		WrapperFactory<T> wFactory = null;
		List<T> transformed = null;
		Function<List<T>, List<T>> processFunction = null;

		RemainingSupplier(WrapperFactory<T> wFactory, Function<List<T>, List<T>> processFunction) {
			this.wFactory = wFactory;
			this.processFunction = processFunction;
		}

		@Override
		public T get() {
			if (transformed == null) {
				transformed = this.wFactory.wrappingArray != null ? processFunction.apply(wFactory.wrappingArray)
						: new ArrayList<>();
			}
			return transformed != null && transformed.size() > 0 ? transformed.remove(0) : null;
		}
	}

	private static <T, X> Stream<X> streamRemaining(WrapperFactory<T> wFactory, Function<T, X> mappingFunction,
			Function<List<T>, List<T>> processFunction) {
		return Stream.generate(new RemainingSupplier<T>(wFactory, processFunction)).takeWhile(entry -> entry != null)
				.map(mappingFunction);
	}

	public static <T, X> Stream<X> batchProcessStream(int batchSize, Stream<T> stream, Function<T, X> mappingFunction,
			Function<List<T>, List<T>> processFunction) {
		WrapperFactory<T> wFactory = new WrapperFactory<T>();
		return Stream.concat(stream.map(entry -> wFactory.create(entry, batchSize)).filter(entry -> entry.fullSample)
				.map(entry -> entry.wrappingArray).map(processFunction).flatMap(entry -> entry.stream())
				.map(mappingFunction), streamRemaining(wFactory, mappingFunction, processFunction));

	}

	
}
