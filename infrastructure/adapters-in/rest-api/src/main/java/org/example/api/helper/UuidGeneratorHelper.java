package org.example.api.helper;

import java.util.UUID;

public interface UuidGeneratorHelper {
	/**
	 * Returns a new {@link UuidGeneratorHelper} that generates UUIDs using {@link UUID#randomUUID}.
	 */
	static UuidGeneratorHelper random() {
		return UUID::randomUUID;
	}

	/**
	 * Returns a new {@link UUID} instance.
	 *
	 * <p>
	 * The returned value is guaranteed to be unique.
	 */
	UUID newUuid();
}