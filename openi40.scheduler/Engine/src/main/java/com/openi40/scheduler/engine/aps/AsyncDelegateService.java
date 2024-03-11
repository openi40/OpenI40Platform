package com.openi40.scheduler.engine.aps;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncDelegateService {
	@Async	
	public <T> CompletableFuture<T> runAsync( Supplier< T> asyncClosuer) {
		return new CompletableFuture<T>().completeAsync(asyncClosuer);
	}
}
