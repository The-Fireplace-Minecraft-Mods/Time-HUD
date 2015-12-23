package the_fireplace.timehud;

import com.google.common.collect.Maps;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import the_fireplace.timehud.config.ConfigValues;
import the_fireplace.timehud.config.FormatEntries;
import the_fireplace.timehud.config.LocationEntries;

import java.util.Map;

@Mod(modid=TimeHud.MODID, name=TimeHud.MODNAME, guiFactory = "the_fireplace.timehud.config.TimeHudGuiFactory", clientSideOnly=true)
public class TimeHud {
	public static final String MODID="timehud";
	public static final String MODNAME="Time HUD";
	public static String VERSION;
	public static final String curseCode = "239110-time-hud";

	public static Map formats = Maps.newHashMap();
	public static Map locations = Maps.newHashMap();

	public static Configuration config;
	public static Property LOCATION_PROPERTY;
	public static Property FORMAT_PROPERTY;
	public static Property REAL_PROPERTY;

	public static void syncConfig(){
		ConfigValues.LOCATION = LOCATION_PROPERTY.getString();
		ConfigValues.FORMAT = FORMAT_PROPERTY.getString();
		ConfigValues.REAL = REAL_PROPERTY.getBoolean();

		if(config.hasChanged())
			config.save();
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		String[] version = event.getModMetadata().version.split("\\.");
		if(version[3].equals("BUILDNUMBER"))//Dev environment
			VERSION = event.getModMetadata().version.replace("BUILDNUMBER", "9001");
		else//Released build
			VERSION = event.getModMetadata().version;
		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		LOCATION_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.LOCATION_NAME, ConfigValues.LOCATION_DEFAULT, StatCollector.translateToLocal(ConfigValues.LOCATION_NAME+".tooltip"));
		FORMAT_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.FORMAT_NAME, ConfigValues.FORMAT_DEFAULT, StatCollector.translateToLocal(ConfigValues.FORMAT_NAME+".tooltip"));
		REAL_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.REAL_NAME, ConfigValues.REAL_DEFAULT, StatCollector.translateToLocal(ConfigValues.REAL_NAME+".tooltip"));
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

		locations.put("top-left", "Top Left");
		locations.put("top-center", "Top Center");
		locations.put("top-right", "Top Right");
		locations.put("center-left", "Center Left");
		locations.put("center-right", "Center Right");
		locations.put("bottom-left", "Bottom Left");
		locations.put("bottom-right", "Bottom Right");

		MinecraftForge.EVENT_BUS.register(new ForgeEvents());
	}
}
