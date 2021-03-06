package the_fireplace.timehud;

import com.google.common.collect.Maps;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import the_fireplace.timehud.config.*;

import java.util.Map;

@Mod(modid=TimeHud.MODID, name=TimeHud.MODNAME, guiFactory = "the_fireplace.timehud.config.TimeHudGuiFactory", clientSideOnly=true, canBeDeactivated = true, updateJSON = "https://bitbucket.org/The_Fireplace/minecraft-mod-updates/raw/master/timehud.json", acceptedMinecraftVersions = "[1.11.2,)")
public class TimeHud {
	public static final String MODID="timehud";
	public static final String MODNAME="Time HUD";

	@Mod.Instance(MODID)
	public static TimeHud instance;

	public static Map<Object, String> formats = Maps.newHashMap();
	public static Map<Object, String> igformats = Maps.newHashMap();
	public KeyHandler keyHandler;

	public static Configuration config;
	//In-Game Time
	public static Property IGHUD_PROPERTY;
	public static Property FORMAT_PROPERTY;
	public static Property NEEDCLOCK_PROPERTY;
	public static Property CLOCKX_PROPERTY;
	public static Property CLOCKY_PROPERTY;
	public static Property XALIGNMENT_PROPERTY;
	public static Property YALIGNMENT_PROPERTY;
	public static Property FONTSCALE_PROPERTY;
	public static Property FONTCOLOR_PROPERTY;
	//Real Time
	public static Property REAL_PROPERTY;
	public static Property RFORMAT_PROPERTY;
	public static Property RCLOCKX_PROPERTY;
	public static Property RCLOCKY_PROPERTY;
	public static Property RXALIGNMENT_PROPERTY;
	public static Property RYALIGNMENT_PROPERTY;
	public static Property RFONTSCALE_PROPERTY;
	public static Property RFONTCOLOR_PROPERTY;

	public static void syncConfig(){
		//In-Game Time
		ConfigValues.IGHUD = IGHUD_PROPERTY.getBoolean();
		ConfigValues.FORMAT = FORMAT_PROPERTY.getString();
		ConfigValues.NEEDCLOCK = NEEDCLOCK_PROPERTY.getBoolean();
		ConfigValues.CLOCKX = CLOCKX_PROPERTY.getInt();
		ConfigValues.CLOCKY = CLOCKY_PROPERTY.getInt();
		ConfigValues.XALIGNMENT = XJust.valueOf(XALIGNMENT_PROPERTY.getString());
		ConfigValues.YALIGNMENT = YJust.valueOf(YALIGNMENT_PROPERTY.getString());
		ConfigValues.FONTSCALE = FONTSCALE_PROPERTY.getDouble();
		ConfigValues.FONTCOLOR = FONTCOLOR_PROPERTY.getInt();
		//Real Time
		ConfigValues.REAL = REAL_PROPERTY.getBoolean();
		ConfigValues.RFORMAT = RFORMAT_PROPERTY.getString();
		ConfigValues.RCLOCKX = RCLOCKX_PROPERTY.getInt();
		ConfigValues.RCLOCKY = RCLOCKY_PROPERTY.getInt();
		ConfigValues.RXALIGNMENT = XJust.valueOf(RXALIGNMENT_PROPERTY.getString());
		ConfigValues.RYALIGNMENT = YJust.valueOf(RYALIGNMENT_PROPERTY.getString());
		ConfigValues.RFONTSCALE = RFONTSCALE_PROPERTY.getDouble();
		ConfigValues.RFONTCOLOR = RFONTCOLOR_PROPERTY.getInt();

		if(config.hasChanged())
			config.save();
	}

	@SuppressWarnings("NoTranslation")
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		//In-Game Time
		IGHUD_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.IGHUD_NAME, ConfigValues.IGHUD_DEFAULT, I18n.format(ConfigValues.IGHUD_NAME+".tooltip"));
		FORMAT_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.FORMAT_NAME, ConfigValues.FORMAT_DEFAULT, I18n.format(ConfigValues.FORMAT_NAME+".tooltip"));
		NEEDCLOCK_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.NEEDCLOCK_NAME, ConfigValues.NEEDCLOCK_DEFAULT, I18n.format(ConfigValues.NEEDCLOCK_NAME+".tooltip"));
		CLOCKX_PROPERTY = config.get("hidden", ConfigValues.CLOCKX_NAME, ConfigValues.CLOCKX_DEFAULT, I18n.format(ConfigValues.CLOCKX_NAME+".tooltip"));
		CLOCKY_PROPERTY = config.get("hidden", ConfigValues.CLOCKY_NAME, ConfigValues.CLOCKY_DEFAULT, I18n.format(ConfigValues.CLOCKY_NAME+".tooltip"));
		XALIGNMENT_PROPERTY = config.get("hidden", ConfigValues.XALIGNMENT_NAME, ConfigValues.XALIGNMENT_DEFAULT.name(), I18n.format(ConfigValues.XALIGNMENT_NAME+".tooltip"));
		YALIGNMENT_PROPERTY = config.get("hidden", ConfigValues.YALIGNMENT_NAME, ConfigValues.YALIGNMENT_DEFAULT.name(), I18n.format(ConfigValues.YALIGNMENT_NAME+".tooltip"));
		FONTSCALE_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.FONTSCALE_NAME, ConfigValues.FONTSCALE_DEFAULT, I18n.format(ConfigValues.FONTSCALE_NAME+".tooltip"));
		FONTCOLOR_PROPERTY = config.get("hidden", ConfigValues.FONTCOLOR_NAME, ConfigValues.FONTCOLOR_DEFAULT, I18n.format(ConfigValues.FONTCOLOR_NAME+".tooltip"));
		FONTSCALE_PROPERTY.setMinValue(0);
		FONTSCALE_PROPERTY.setMaxValue(10);
		FORMAT_PROPERTY.setConfigEntryClass(FormatEntries.class);
		//Real Time
		REAL_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.REAL_NAME, ConfigValues.REAL_DEFAULT, I18n.format(ConfigValues.REAL_NAME+".tooltip"));
		RFORMAT_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.RFORMAT_NAME, ConfigValues.RFORMAT_DEFAULT, I18n.format(ConfigValues.RFORMAT_NAME+".tooltip"));
		RCLOCKX_PROPERTY = config.get("hidden", ConfigValues.RCLOCKX_NAME, ConfigValues.RCLOCKX_DEFAULT, I18n.format(ConfigValues.RCLOCKX_NAME+".tooltip"));
		RCLOCKY_PROPERTY = config.get("hidden", ConfigValues.RCLOCKY_NAME, ConfigValues.RCLOCKY_DEFAULT, I18n.format(ConfigValues.RCLOCKY_NAME+".tooltip"));
		RXALIGNMENT_PROPERTY = config.get("hidden", ConfigValues.RXALIGNMENT_NAME, ConfigValues.RXALIGNMENT_DEFAULT.name(), I18n.format(ConfigValues.RXALIGNMENT_NAME+".tooltip"));
		RYALIGNMENT_PROPERTY = config.get("hidden", ConfigValues.RYALIGNMENT_NAME, ConfigValues.RYALIGNMENT_DEFAULT.name(), I18n.format(ConfigValues.RYALIGNMENT_NAME+".tooltip"));
		RFONTSCALE_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.RFONTSCALE_NAME, ConfigValues.RFONTSCALE_DEFAULT, I18n.format(ConfigValues.RFONTSCALE_NAME+".tooltip"));
		RFONTCOLOR_PROPERTY = config.get("hidden", ConfigValues.RFONTCOLOR_NAME, ConfigValues.RFONTCOLOR_DEFAULT, I18n.format(ConfigValues.RFONTCOLOR_NAME+".tooltip"));
		RFONTSCALE_PROPERTY.setMinValue(0);
		RFONTSCALE_PROPERTY.setMaxValue(10);
		RFORMAT_PROPERTY.setConfigEntryClass(RFormatEntries.class);
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

		//In-Game HUD Formats
		igformats.putAll(formats);
		igformats.put("7D 2D", "Day 736330");

		MinecraftForge.EVENT_BUS.register(keyHandler = new KeyHandler());
	}
}
