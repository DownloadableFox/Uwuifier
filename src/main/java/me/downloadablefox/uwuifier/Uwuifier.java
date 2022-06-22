package me.downloadablefox.uwuifier;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Uwuifier implements ModInitializer {
	public static final String ModID = "uwuifier";
	public static final String ModName = "Uwuifier";
	public static final String Version = "1.0.0";

	public static final Logger LOGGER = LoggerFactory.getLogger(ModName);
	@Override
	public void onInitialize() {
		LOGGER.info(String.format("%s was initialized!", ModName));
	}
}
