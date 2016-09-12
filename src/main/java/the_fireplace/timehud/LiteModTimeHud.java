package the_fireplace.timehud;

import com.google.common.collect.Maps;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mumfrey.liteloader.Configurable;
import com.mumfrey.liteloader.HUDRenderListener;
import com.mumfrey.liteloader.modconfig.ConfigPanel;
import com.mumfrey.liteloader.modconfig.ConfigStrategy;
import com.mumfrey.liteloader.modconfig.ExposableOptions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.io.File;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Map;

@ExposableOptions(strategy = ConfigStrategy.Unversioned, filename="timehud.json")
public class LiteModTimeHud implements Configurable, HUDRenderListener {
	public static LiteModTimeHud instance;
	@Expose
	@SerializedName("location")
	public String location = "top-left";
	@Expose
	@SerializedName("format")
	public String format = "24HH:MMBRNAME DATE, YEAR";
	@Expose
	@SerializedName("real_time")
	public boolean real_time = false;
	@Expose
	@SerializedName("need_clock")
	public boolean needclock = false;

	public LiteModTimeHud(){
		instance = this;
	}

	public static final String MODNAME="Time HUD";

	public static Map<Object, String> formats = Maps.newHashMap();
	public static Map<Object, String> locations = Maps.newHashMap();

	@Override
	public Class<? extends ConfigPanel> getConfigPanelClass() {
		return null;
	}

	@Override
	public void onPreRenderHUD(int screenWidth, int screenHeight) {

	}

	@Override
	public void onPostRenderHUD(int width, int height) {
		Minecraft mc = Minecraft.getMinecraft();
		if(mc.inGameHasFocus){
			if(needclock && !hasClock())
				return;
			int x=0, y=0;
			String[] loc = location.split("-");
			if(loc[0].equals("top"))
				y=4;
			if(loc[0].equals("center"))
				y=height/2-6;
			if(loc[0].equals("bottom"))
				y=height-4-12;
			if(loc[1].equals("left"))
				x=4;
			if(loc[1].equals("center"))
				x=width/2-25;
			if(loc[1].equals("right"))
				x=width-4-50;
			String d2 = format;
			if(real_time){
				if(d2.contains("MONTH"))
					d2 = d2.replace("MONTH", String.valueOf(Calendar.getInstance().get(Calendar.MONTH)));
				if(d2.contains("DATE"))
					d2 = d2.replace("DATE", String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
				if(d2.contains("YEAR"))
					d2 = d2.replace("YEAR", String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
				if(d2.contains("12HH")) {
					int hour = Calendar.getInstance().get(Calendar.HOUR);
					if(hour == 0)
						hour = 12;
					d2 = d2.replace("12HH", String.valueOf(hour));
				}
				if(d2.contains("24HH")) {
					d2 = d2.replace("24HH", String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)));
				}
				if(d2.contains("MM"))
					d2 = d2.replace("MM", String.valueOf(Calendar.getInstance().get(Calendar.MINUTE)));
				if(d2.contains("SS"))
					d2 = d2.replace("SS", String.valueOf(Calendar.getInstance().get(Calendar.SECOND)));
				if(d2.contains("NAME"))
					d2 = d2.replace("NAME", getMonthForInt(Calendar.getInstance().get(Calendar.MONTH)));
				if(d2.contains("ZZ")) {
					if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) > 11)
						d2 = d2.replace("ZZ", "PM");
					else
						d2 = d2.replace("ZZ", "AM");
				}
			}else{
				long month = 1, day, year = 1;
				long hour = 6, minute = 0, second = 0;
				long daylength = 24000;
				long worldtime = mc.theWorld.getWorldTime();
				long daycount = (long)Math.floor(worldtime/daylength);
				long remainingticks = worldtime%daylength;
				String[] names = new String[]{I18n.format("january"), I18n.format("february"), I18n.format("march"), I18n.format("april"), I18n.format("may"), I18n.format("june"), I18n.format("july"), I18n.format("august"), I18n.format("september"), I18n.format("october"), I18n.format("november"), I18n.format("december")};
				while(daycount > 365){
					daycount -= 365;
					year++;
				}
				if(daycount > 31){
					daycount -= 31;
					month++;
				}
				if(daycount > 28){
					daycount -= 28;
					month++;
				}
				if(daycount > 31){
					daycount -= 31;
					month++;
				}
				if(daycount > 30){
					daycount -= 30;
					month++;
				}
				if(daycount > 31){
					daycount -= 31;
					month++;
				}
				if(daycount > 30){
					daycount -= 30;
					month++;
				}
				if(daycount > 31){
					daycount -= 31;
					month++;
				}
				if(daycount > 31){
					daycount -= 31;
					month++;
				}
				if(daycount > 30){
					daycount -= 30;
					month++;
				}
				if(daycount > 31){
					daycount -= 31;
					month++;
				}
				if(daycount > 30){
					daycount -= 30;
					month++;
				}
				day = daycount+1;

				while(remainingticks >= 1000){
					remainingticks -= 1000;
					hour++;
					if(hour > 24)
						hour -= 24;
				}
				remainingticks *= 3;//60 ticks per second, allows for even division into minutes
				while (remainingticks >= 50){
					remainingticks -= 50;
					minute++;
				}
				remainingticks *= 6;//360 ticks per second, allows for division into seconds
				while(remainingticks >= 5){
					remainingticks -= 5;
					second++;
				}

				if(d2.contains("MONTH"))
					d2 = d2.replace("MONTH", String.valueOf(month));
				if(d2.contains("DATE"))
					d2 = d2.replace("DATE", String.valueOf(day));
				if(d2.contains("YEAR"))
					d2 = d2.replace("YEAR", String.valueOf(year));
				if(d2.contains("12HH")) {
					long hour2 = hour;
					if(hour2 > 12)
						hour2 -= 12;
					d2 = d2.replace("12HH", String.valueOf(hour2));
				}
				if(d2.contains("24HH")) {
					d2 = d2.replace("24HH", String.valueOf(hour));
				}
				if(d2.contains("MM")) {
					String m = String.valueOf(minute);
					if(m.length() == 1)
						d2 = d2.replace("MM", "0"+m);
					else
						d2 = d2.replace("MM", m);
				}
				if(d2.contains("SS")) {
					String s = String.valueOf(second);
					if(s.length() == 1)
						d2 = d2.replace("SS", "0"+s);
					else
						d2 = d2.replace("SS", s);
				}
				if(d2.contains("NAME"))
					d2 = d2.replace("NAME", names[(int)month-1]);
				if(d2.contains("ZZ")) {
					if(hour > 11 && hour < 24)
						d2 = d2.replace("ZZ", "PM");
					else
						d2 = d2.replace("ZZ", "AM");
				}
			}
			String[] d3 = d2.split("BR");
			mc.ingameGUI.drawString(Minecraft.getMinecraft().fontRendererObj, d3[0], x, y, -1);
			if(d3.length > 1)
				mc.ingameGUI.drawString(Minecraft.getMinecraft().fontRendererObj, d3[1], x, y+12, -1);
		}
	}

	@Override
	public String getVersion() {
		return null;
	}

	@Override
	public void init(File configPath) {
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
	}

	@Override
	public void upgradeSettings(String version, File configPath, File oldConfigPath) {

	}

	@Override
	public String getName() {
		return MODNAME;
	}

	private boolean hasClock(){
		for(ItemStack stack: Minecraft.getMinecraft().thePlayer.inventory.mainInventory)
			if(stack != null)
				if(stack.getItem().equals(Items.CLOCK))
					return true;
		return false;
	}
	private String getMonthForInt(int num) {
		String month = "wrong";
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();
		if (num >= 0 && num <= 11 ) {
			month = months[num];
		}
		return month;
	}
}
