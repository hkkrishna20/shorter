package com.url.shortner.shorturl.config;

public class TenantContext {
	private static ThreadLocal<String> currentTenant = new ThreadLocal<>();
	private static final String DEFAULT_TENANT = "en";

	public static void setCurrentTenant(String tenant) {
		currentTenant.set(tenant);
	}

	public static String getCurrentTenant() {
		String tenant = currentTenant.get();
		return tenant != null ? tenant : DEFAULT_TENANT;
	}

	public static void clear() {
		currentTenant.remove();
	}
}