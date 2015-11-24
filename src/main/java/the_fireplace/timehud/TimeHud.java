package the_fireplace.timehud;

import com.google.common.collect.Maps;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import the_fireplace.timehud.config.ConfigValues;
import the_fireplace.timehud.config.FormatEntries;
import the_fireplace.timehud.config.LocationEntries;

import java.util.Map;

@Mod(modid=TimeHud.MODID, name=TimeHud.MODNAME, version=TimeHud.VERSION, guiFactory = "the_fireplace.timehud.config.TimeHudGuiFactory", clientSideOnly=true)
public class TimeHud {
	public static final String MODID="timehud";
	public static final String MODNAME="Time HUD";
	public static final String VERSION="2.0.0.1";

	public static Map formats = Maps.newHashMap();
	public static Map locations = Maps.newHashMap();

	public static Configuration config;
	public static Property LOCATION_PROPERTY;
	public static Property FORMAT_PROPERTY;

	public static void syncConfig(){
		ConfigValues.LOCATION = LOCATION_PROPERTY.getString();
		ConfigValues.FORMAT = FORMAT_PROPERTY.getString();
		if(config.hasChanged()){
			config.save();
		}
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		LOCATION_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.LOCATION_NAME, ConfigValues.LOCATION_DEFAULT, StatCollector.translateToLocal(ConfigValues.LOCATION_NAME+".tooltip"));
		FORMAT_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.FORMAT_NAME, ConfigValues.FORMAT_DEFAULT, StatCollector.translateToLocal(ConfigValues.FORMAT_NAME+".tooltip"));
		LOCATION_PROPERTY.setConfigEntryClass(LocationEntries.class);
		FORMAT_PROPERTY.setConfigEntryClass(FormatEntries.class);
		syncConfig();
		//Example: 1:20:35 PM November 23, 2015
		formats.put("24HH:MM:SS-BR-NAME DATE, YEAR","13:20:35-RETURN-November 23, 2015");
		formats.put("12HH:MM:SS ZZ-BR-NAME DATE, YEAR","1:20:35 PM-RETURN-November 23, 2015");
		formats.put("24HH:MM:SS-BR-MONTH/DATE/YEAR","13:20:35-RETURN-11/23/2015");
		formats.put("12HH:MM:SS ZZ-BR-MONTH/DATE/YEAR","1:20:35 PM-RETURN-11/23/2015");
		formats.put("24HH:MM:SS-BR-DATE/MONTH/YEAR","13:20:35-RETURN-23/11/2015");
		formats.put("12HH:MM:SS ZZ-BR-DATE/MONTH/YEAR","1:20:35 PM-RETURN-23/11/2015");
		//No seconds
		formats.put("24HH:MM-BR-NAME DATE, YEAR","13:20-RETURN-November 23, 2015");
		formats.put("12HH:MM ZZ-BR-NAME DATE, YEAR","1:20 PM-RETURN-November 23, 2015");
		formats.put("24HH:MM-BR-MONTH/DATE/YEAR","13:20-RETURN-11/23/2015");
		formats.put("12HH:MM ZZ-BR-MONTH/DATE/YEAR","1:20 PM-RETURN-11/23/2015");
		formats.put("24HH:MM-BR-DATE/MONTH/YEAR","13:20-RETURN-23/11/2015");
		formats.put("12HH:MM ZZ-BR-DATE/MONTH/YEAR","1:20 PM-RETURN-23/11/2015");
		//No time
		formats.put("NAME DATE, YEAR","November 23, 2015");
		formats.put("MONTH/DATE/YEAR","11/23/2015");
		formats.put("DATE/MONTH/YEAR","23/11/2015");
		//No date, no seconds
		formats.put("24HH:MM","13:20");
		formats.put("12HH:MM ZZ","1:20 PM");
		//No date, with seconds
		formats.put("24HH:MM:SS","13:20:35");
		formats.put("12HH:MM:SS ZZ","1:20:35 PM");

		locations.put("top-left", "Top Left");
		locations.put("top-center", "Top Center");
		locations.put("top-right", "Top Right");
		locations.put("center-left", "Center Left");
		locations.put("center-right", "Center Right");
		locations.put("bottom-left", "Bottom Left");
		locations.put("bottom-right", "Bottom Right");

		FMLCommonHandler.instance().bus().register(new FMLEvents());
	}
}
