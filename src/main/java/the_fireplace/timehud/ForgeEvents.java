package the_fireplace.timehud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import the_fireplace.timehud.config.ConfigValues;

import java.time.LocalDateTime;

public class ForgeEvents {
	@SubscribeEvent
	public void guiRender(TickEvent.RenderTickEvent t){
		Minecraft mc = Minecraft.getMinecraft();
		if(mc.inGameHasFocus){
			ScaledResolution res = new ScaledResolution(mc);
			int width = res.getScaledWidth();
			int height = res.getScaledHeight();
			int x=0, y=0;
			String[] loc = ConfigValues.LOCATION.split("-");
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
			String d2 = ConfigValues.FORMAT;
			if(ConfigValues.REAL){
				if(d2.contains("MONTH"))
					d2 = d2.replace("MONTH", String.valueOf(LocalDateTime.now().getMonthValue()));
				if(d2.contains("DATE"))
					d2 = d2.replace("DATE", String.valueOf(LocalDateTime.now().getDayOfMonth()));
				if(d2.contains("YEAR"))
					d2 = d2.replace("YEAR", String.valueOf(LocalDateTime.now().getYear()));
				if(d2.contains("12HH")) {
					int hour = LocalDateTime.now().getHour();
					if(hour > 12)
						hour -= 12;
					d2 = d2.replace("12HH", String.valueOf(hour));
				}
				if(d2.contains("24HH")) {
					d2 = d2.replace("24HH", String.valueOf(LocalDateTime.now().getHour()));
				}
				if(d2.contains("MM"))
					d2 = d2.replace("MM", String.valueOf(LocalDateTime.now().getMinute()));
				if(d2.contains("SS"))
					d2 = d2.replace("SS", String.valueOf(LocalDateTime.now().getSecond()));
				if(d2.contains("NAME"))
					d2 = d2.replace("NAME", LocalDateTime.now().getMonth().name());
				if(d2.contains("ZZ")) {
					if(LocalDateTime.now().getHour() > 11)
						d2 = d2.replace("ZZ", "PM");
					else
						d2 = d2.replace("ZZ", "AM");
				}
			}else{
				long month = 1, day = 1, year = 1;
				long hour = 6, minute = 0, second = 0;
				long daylength = 24000;
				long worldtime = mc.theWorld.getWorldTime();
				long daycount = (long)Math.floor(worldtime/daylength);
				long remainingticks = worldtime%daylength;
				String[] names = new String[]{StatCollector.translateToLocal("january"), StatCollector.translateToLocal("february"), StatCollector.translateToLocal("march"), StatCollector.translateToLocal("april"), StatCollector.translateToLocal("may"), StatCollector.translateToLocal("june"), StatCollector.translateToLocal("july"), StatCollector.translateToLocal("august"), StatCollector.translateToLocal("september"), StatCollector.translateToLocal("october"), StatCollector.translateToLocal("november"), StatCollector.translateToLocal("december")};
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
			mc.ingameGUI.drawString(Minecraft.getMinecraft().fontRendererObj, d3[1], x, y+12, -1);
			}
		}
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if(eventArgs.modID.equals(TimeHud.MODID))
			TimeHud.syncConfig();
	}
}
