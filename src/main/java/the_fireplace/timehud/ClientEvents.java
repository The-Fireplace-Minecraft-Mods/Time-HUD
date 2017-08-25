package the_fireplace.timehud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import the_fireplace.timehud.config.ConfigValues;
import the_fireplace.timehud.gui.GuiClockMoving;

import java.text.DateFormatSymbols;
import java.util.Calendar;

@Mod.EventBusSubscriber(modid = TimeHud.MODID)
public class ClientEvents {
	@SubscribeEvent
	public static void guiRender(TickEvent.RenderTickEvent t){
		Minecraft mc = Minecraft.getMinecraft();
		if((mc.inGameHasFocus && !mc.gameSettings.showDebugInfo) || mc.currentScreen instanceof GuiClockMoving) {
			if (ConfigValues.NEEDCLOCK && !hasClock())
				return;
			ScaledResolution res = new ScaledResolution(mc);
			int width = res.getScaledWidth();
			int height = res.getScaledHeight();
			String d2 = ConfigValues.FORMAT;
			if (ConfigValues.REAL) {
				if (d2.contains("MONTH"))
					d2 = d2.replace("MONTH", String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1));
				if (d2.contains("DATE"))
					d2 = d2.replace("DATE", String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
				if (d2.contains("YEAR"))
					d2 = d2.replace("YEAR", String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
				if (d2.contains("12HH")) {
					int hour = Calendar.getInstance().get(Calendar.HOUR);
					if (hour == 0)
						hour = 12;
					d2 = d2.replace("12HH", String.valueOf(hour));
				}
				if (d2.contains("24HH")) {
					d2 = d2.replace("24HH", String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)));
				}
				if (d2.contains("MM")) {
					String minute = String.valueOf(Calendar.getInstance().get(Calendar.MINUTE));
					if(minute.length() == 1)
						d2 = d2.replace("MM", '0'+minute);
					else
						d2 = d2.replace("MM", minute);
				}
				if (d2.contains("SS")) {
					String second = String.valueOf(Calendar.getInstance().get(Calendar.SECOND));
					if(second.length() == 1)
						d2 = d2.replace("SS", '0'+second);
					else
						d2 = d2.replace("SS", second);
				}
				if (d2.contains("NAME"))
					d2 = d2.replace("NAME", getMonthForInt(Calendar.getInstance().get(Calendar.MONTH)));
				if (d2.contains("ZZ")) {
					if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) > 11)
						d2 = d2.replace("ZZ", "PM");
					else
						d2 = d2.replace("ZZ", "AM");
				}
			} else {
				long month = 1, day, year = 1;
				long hour = 6, minute = 0, second = 0;
				long daylength = 24000;
				long worldtime = mc.world.getWorldTime();
				long daycount = (long) Math.floor((worldtime+daylength/4) / daylength);
				long remainingticks = worldtime % daylength;
				String[] names = new String[]{I18n.format("january"), I18n.format("february"), I18n.format("march"), I18n.format("april"), I18n.format("may"), I18n.format("june"), I18n.format("july"), I18n.format("august"), I18n.format("september"), I18n.format("october"), I18n.format("november"), I18n.format("december")};
				while (daycount > 365) {
					daycount -= 365;
					year++;
				}
				if (daycount > 31) {
					daycount -= 31;
					month++;
				}
				if (daycount > 28) {
					daycount -= 28;
					month++;
				}
				if (daycount > 31) {
					daycount -= 31;
					month++;
				}
				if (daycount > 30) {
					daycount -= 30;
					month++;
				}
				if (daycount > 31) {
					daycount -= 31;
					month++;
				}
				if (daycount > 30) {
					daycount -= 30;
					month++;
				}
				if (daycount > 31) {
					daycount -= 31;
					month++;
				}
				if (daycount > 31) {
					daycount -= 31;
					month++;
				}
				if (daycount > 30) {
					daycount -= 30;
					month++;
				}
				if (daycount > 31) {
					daycount -= 31;
					month++;
				}
				if (daycount > 30) {
					daycount -= 30;
					month++;
				}
				day = daycount + 1;

				while (remainingticks >= 1000) {
					remainingticks -= 1000;
					hour++;
					if (hour > 24)
						hour -= 24;
				}
				remainingticks *= 3;//60 ticks per second, allows for even division into minutes
				while (remainingticks >= 50) {
					remainingticks -= 50;
					minute++;
				}
				remainingticks *= 6;//360 ticks per second, allows for division into seconds
				while (remainingticks >= 5) {
					remainingticks -= 5;
					second++;
				}

				if (d2.contains("MONTH"))
					d2 = d2.replace("MONTH", String.valueOf(month));
				if (d2.contains("DATE"))
					d2 = d2.replace("DATE", String.valueOf(day));
				if (d2.contains("YEAR"))
					d2 = d2.replace("YEAR", String.valueOf(year));
				if (d2.contains("12HH")) {
					long hour2 = hour;
					if (hour2 > 12)
						hour2 -= 12;
					d2 = d2.replace("12HH", String.valueOf(hour2));
				}
				if (d2.contains("24HH")) {
					d2 = d2.replace("24HH", String.valueOf(hour));
				}
				if (d2.contains("MM")) {
					String m = String.valueOf(minute);
					if (m.length() == 1)
						d2 = d2.replace("MM", "0" + m);
					else
						d2 = d2.replace("MM", m);
				}
				if (d2.contains("SS")) {
					String s = String.valueOf(second);
					if (s.length() == 1)
						d2 = d2.replace("SS", "0" + s);
					else
						d2 = d2.replace("SS", s);
				}
				if (d2.contains("NAME"))
					d2 = d2.replace("NAME", names[(int) month - 1]);
				if (d2.contains("ZZ")) {
					if (hour > 11 && hour < 24)
						d2 = d2.replace("ZZ", "PM");
					else
						d2 = d2.replace("ZZ", "AM");
				}
			}
			String[] d3 = d2.split("BR");
			boolean twoline = false;
			if (d3.length > 1)
				twoline=true;

			int xPos = 0;
			int xPos2 = 0;
			int yPos = 0;

			switch(ConfigValues.XALIGNMENT){
				case LEFT:
					xPos = ConfigValues.CLOCKX;
					if(twoline)
						xPos2=xPos;
					break;
				case RIGHT:
					xPos = width-ConfigValues.CLOCKX;
					if(twoline)
						xPos2 = (int)(xPos+mc.fontRenderer.getStringWidth(d3[0])*ConfigValues.FONTSCALE-mc.fontRenderer.getStringWidth(d3[1])*ConfigValues.FONTSCALE);
					break;
				case CENTER:
					xPos = width/2+ ConfigValues.CLOCKX;
					if(twoline)
						xPos2 = (int)(xPos+(mc.fontRenderer.getStringWidth(d3[0])*ConfigValues.FONTSCALE-mc.fontRenderer.getStringWidth(d3[1])*ConfigValues.FONTSCALE)/2);
			}

			switch(ConfigValues.YALIGNMENT){
				case TOP:
					yPos = ConfigValues.CLOCKY;
					break;
				case BOTTOM:
					yPos = height-ConfigValues.CLOCKY;
					break;
				case CENTER:
					yPos = height/2+ConfigValues.CLOCKY;
			}

			GlStateManager.scale(ConfigValues.FONTSCALE, ConfigValues.FONTSCALE, 0);

			mc.ingameGUI.drawString(mc.fontRenderer, d3[0], (int)(xPos/ConfigValues.FONTSCALE), (int)(yPos/ConfigValues.FONTSCALE), ConfigValues.FONTCOLOR);
			if(twoline)
				mc.ingameGUI.drawString(mc.fontRenderer, d3[1], (int)(xPos2/ConfigValues.FONTSCALE), (int)((yPos/ConfigValues.FONTSCALE) + mc.fontRenderer.FONT_HEIGHT + mc.fontRenderer.FONT_HEIGHT/3), ConfigValues.FONTCOLOR);

			GlStateManager.scale(1, 1, 1);//Reset scale so you don't interfere with other mods doing rendering here.
		}
	}
	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if(eventArgs.getModID().equals(TimeHud.MODID))
			TimeHud.syncConfig();
	}
	private static boolean hasClock(){
		for(ItemStack stack:Minecraft.getMinecraft().player.inventory.mainInventory)
			if(stack != null)
				if(stack.getItem().equals(Items.CLOCK))
					return true;
		return false;
	}
	public static String getMonthForInt(int num) {
		String month = "wrong";
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();
		if (num >= 0 && num <= 11 ) {
			month = months[num];
		}
		return month;
	}
}
