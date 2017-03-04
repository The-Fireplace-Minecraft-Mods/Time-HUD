package the_fireplace.timehud.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL12;
import the_fireplace.timehud.ClientEvents;
import the_fireplace.timehud.KeyHandler;
import the_fireplace.timehud.TimeHud;
import the_fireplace.timehud.config.ConfigValues;
import the_fireplace.timehud.config.XJust;
import the_fireplace.timehud.config.YJust;

import java.awt.*;
import java.io.IOException;
import java.util.Calendar;

import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;

/**
 * @author The_Fireplace
 */
public class GuiClockMoving extends GuiScreen {
	private boolean mouseDown = false;
	private static final int mouseOffset = 8;

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks){
		super.drawScreen(mouseX, mouseY, partialTicks);

		glEnable(32826);
		GlStateManager.pushMatrix();

		RenderHelper.enableGUIStandardItemLighting();

		if(mouseDown){
			GlStateManager.enableBlend();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 0.5F);
			//render transparent clock
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
					d2 = d2.replace("NAME", ClientEvents.getMonthForInt(Calendar.getInstance().get(Calendar.MONTH)));
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

			int screenX = mouseX-mouseOffset;
			int screenY = mouseY-mouseOffset;
			XJust xval;
			if(screenX < width/3)
				xval=XJust.LEFT;
			else if(screenX > width/3*2)
				xval=XJust.RIGHT;
			else
				xval=XJust.CENTER;

			int xPos = screenX;
			int xPos2 = xPos;
			int yPos = screenY;
			if(twoline)
			switch(xval){
				case LEFT:default:
					break;
				case RIGHT:
					xPos2 = (int)(xPos+mc.fontRenderer.getStringWidth(d3[0])*ConfigValues.FONTSCALE-mc.fontRenderer.getStringWidth(d3[1])*ConfigValues.FONTSCALE);
					break;
				case CENTER:
					xPos2 = (int)(xPos+(mc.fontRenderer.getStringWidth(d3[0])*ConfigValues.FONTSCALE-mc.fontRenderer.getStringWidth(d3[1])*ConfigValues.FONTSCALE)/2);
			}

			GlStateManager.scale(ConfigValues.FONTSCALE, ConfigValues.FONTSCALE, 0);

			mc.ingameGUI.drawString(mc.fontRenderer, d3[0], (int)(xPos/ConfigValues.FONTSCALE), (int)(yPos/ConfigValues.FONTSCALE), (255/2 << 24) | (Color.WHITE.getRGB()&0x00ffffff));
			if(twoline)
				mc.ingameGUI.drawString(mc.fontRenderer, d3[1], (int)(xPos2/ConfigValues.FONTSCALE), (int)((yPos/ConfigValues.FONTSCALE) + mc.fontRenderer.FONT_HEIGHT + mc.fontRenderer.FONT_HEIGHT/3), (255/2 << 24) | (Color.WHITE.getRGB()&0x00ffffff));

			GlStateManager.resetColor();
			GlStateManager.disableBlend();
		}

		RenderHelper.disableStandardItemLighting();
		glDisable(GL12.GL_RESCALE_NORMAL);
		glEnable(32826);
		GlStateManager.popMatrix();
	}

	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state)
	{
		ScaledResolution res = new ScaledResolution(mc);
		int width = res.getScaledWidth();
		int height = res.getScaledHeight();
		int screenX = mouseX-mouseOffset;
		int screenY = mouseY-mouseOffset;
		int finalX;
		int finalY;
		XJust xval;
		YJust yval;
		if(screenX < width/3)
			xval=XJust.LEFT;
		else if(screenX > width/3*2)
			xval=XJust.RIGHT;
		else
			xval=XJust.CENTER;
		if(screenY < height/3)
			yval=YJust.TOP;
		else if(screenY > height/3*2)
			yval=YJust.BOTTOM;
		else
			yval=YJust.CENTER;

		switch(xval){
			case LEFT:default:
				finalX=screenX;
				break;
			case CENTER:
				finalX=screenX-width/2;
				break;
			case RIGHT:
				finalX=width-screenX;
		}
		switch(yval){
			case TOP:default:
				finalY=screenY;
				break;
			case CENTER:
				finalY=screenY-height/2;
				break;
			case BOTTOM:
				finalY=height-screenY;
		}
		TimeHud.CLOCKX_PROPERTY.set(finalX);
		TimeHud.CLOCKY_PROPERTY.set(finalY);
		TimeHud.XALIGNMENT_PROPERTY.set(xval.name());
		TimeHud.YALIGNMENT_PROPERTY.set(yval.name());
		TimeHud.syncConfig();
		mouseDown = false;
		super.mouseReleased(mouseX, mouseY, state);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
	{
		mouseDown = true;
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException
	{
		if (keyCode == mc.gameSettings.keyBindInventory.getKeyCode() || keyCode == Keyboard.KEY_RETURN || keyCode == TimeHud.instance.keyHandler.getKeyCode(KeyHandler.MOVECLOCK))
		{
			this.mc.displayGuiScreen(null);

			if (this.mc.currentScreen == null)
			{
				this.mc.setIngameFocus();
			}
		}
		super.keyTyped(typedChar, keyCode);
	}
}