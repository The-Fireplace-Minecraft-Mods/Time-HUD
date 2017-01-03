package the_fireplace.timehud;

import com.google.common.collect.Maps;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import the_fireplace.timehud.config.ConfigValues;
import the_fireplace.timehud.config.FormatEntries;
import the_fireplace.timehud.config.LocationEntries;

import java.util.Map;

@Mod(modid=TimeHud.MODID, name=TimeHud.MODNAME, guiFactory = "the_fireplace.timehud.config.TimeHudGuiFactory", canBeDeactivated = true)
public class TimeHud {
	public static final String MODID="timehud";
	public static final String MODNAME="Time HUD";

	public static Map<Object, String> formats = Maps.newHashMap();
	public static Map<Object, String> locations = Maps.newHashMap();

	public static Configuration config;
	public static Property LOCATION_PROPERTY;
	public static Property FORMAT_PROPERTY;
	public static Property REAL_PROPERTY;
	public static Property NEEDCLOCK_PROPERTY;

	public static void syncConfig(){
		ConfigValues.LOCATION = LOCATION_PROPERTY.getString();
		ConfigValues.FORMAT = FORMAT_PROPERTY.getString();
		ConfigValues.REAL = REAL_PROPERTY.getBoolean();
		ConfigValues.NEEDCLOCK = NEEDCLOCK_PROPERTY.getBoolean();

		if(config.hasChanged())
			config.save();
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		LOCATION_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.LOCATION_NAME, ConfigValues.LOCATION_DEFAULT, I18n.format(ConfigValues.LOCATION_NAME+".tooltip"));
		FORMAT_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.FORMAT_NAME, ConfigValues.FORMAT_DEFAULT, I18n.format(ConfigValues.FORMAT_NAME+".tooltip"));
		REAL_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.REAL_NAME, ConfigValues.REAL_DEFAULT, I18n.format(ConfigValues.REAL_NAME+".tooltip"));
		NEEDCLOCK_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.NEEDCLOCK_NAME, ConfigValues.NEEDCLOCK_DEFAULT, I18n.format(ConfigValues.NEEDCLOCK_NAME+".tooltip"));
		LOCATION_PROPERTY.setConfigEntryClass(LocationEntries.class);
		FORMAT_PROPERTY.setConfigEntryClass(FormatEntries.class);
		syncConfig();
		//Example: 10:45:35 PM December 17, 2015
		formats.put("24HH:MM:SSBRNAME DATE, YEAR","22:45:35-RETURN-December 17, 2015");
		formats.put("12HH:MM:SS ZZBRNAME DATE, YEAR","10:45:35 PM-RETURN-December 17, 2015");
		formats.put("24HH:MM:SSBRMONTH/DATE/YEAR","22:45:35-RETURN-12/17/2015");
		formats.put("12HH:MM:SS ZZBRMONTH/DATE/YEAR","10:45:35 PM-RETURN-12/17/2015");
		formats.put("24HH:MM:SSBRDATE/MONTH/YEAR","22:45:35-RETURN-17/12/2015");
		formats.put("12HH:MM:SS ZZBRDATE/MONTH/YEAR","10:45:35 PM-RETURN-17/12/2015");
		//No seconds
		formats.put("24HH:MMBRNAME DATE, YEAR","22:45-RETURN-December 17, 2015");
		formats.put("12HH:MM ZZBRNAME DATE, YEAR","10:45 PM-RETURN-December 17, 2015");
		formats.put("24HH:MMBRMONTH/DATE/YEAR","22:45-RETURN-12/17/2015");
		formats.put("12HH:MM ZZBRMONTH/DATE/YEAR","10:45 PM-RETURN-12/17/2015");
		formats.put("24HH:MMBRDATE/MONTH/YEAR","22:45-RETURN-17/12/2015");
		formats.put("12HH:MM ZZBRDATE/MONTH/YEAR","10:45 PM-RETURN-17/12/2015");
		//No time
		formats.put("NAME DATE, YEAR","December 17, 2015");
		formats.put("MONTH/DATE/YEAR","12/17/2015");
		formats.put("DATE/MONTH/YEAR","17/12/2015");
		//No date, no seconds
		formats.put("24HH:MM","22:45");
		formats.put("12HH:MM ZZ","10:45 PM");
		//No date, with seconds
		formats.put("24HH:MM:SS","22:45:35");
		formats.put("12HH:MM:SS ZZ","10:45:35 PM");
		//One-line
		formats.put("24HH:MM:SS, NAME DATE, YEAR","22:45:35, December 17, 2015");
		formats.put("12HH:MM:SS ZZ, NAME DATE, YEAR","10:45:35 PM, December 17, 2015");
		formats.put("24HH:MM:SS, MONTH/DATE/YEAR","22:45:35, 12/17/2015");
		formats.put("12HH:MM:SS ZZ, MONTH/DATE/YEAR","10:45:35 PM, 12/17/2015");
		formats.put("24HH:MM:SS, DATE/MONTH/YEAR","22:45:35, 17/12/2015");
		formats.put("12HH:MM:SS ZZ, DATE/MONTH/YEAR","10:45:35 PM, 17/12/2015");
		//One-line, no seconds
		formats.put("24HH:MM, NAME DATE, YEAR","22:45, December 17, 2015");
		formats.put("12HH:MM ZZ, NAME DATE, YEAR","10:45 PM, December 17, 2015");
		formats.put("24HH:MM, MONTH/DATE/YEAR","22:45, 12/17/2015");
		formats.put("12HH:MM ZZ, MONTH/DATE/YEAR","10:45 PM, 12/17/2015");
		formats.put("24HH:MM, DATE/MONTH/YEAR","22:45, 17/12/2015");
		formats.put("12HH:MM ZZ, DATE/MONTH/YEAR","10:45 PM, 17/12/2015");

		locations.put("top-left", "Top Left");
		locations.put("top-center", "Top Center");
		locations.put("top-right", "Top Right");
		locations.put("center-left", "Center Left");
		locations.put("center-right", "Center Right");
		locations.put("bottom-left", "Bottom Left");
		locations.put("bottom-right", "Bottom Right");

		FMLCommonHandler.instance().bus().register(new ClientEvents());
	}
}
