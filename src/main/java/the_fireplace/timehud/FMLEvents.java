package the_fireplace.timehud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import the_fireplace.timehud.config.ConfigValues;

public class FMLEvents {
	@SubscribeEvent
	public void guiRender(RenderGameOverlayEvent event){
		Minecraft mc = Minecraft.getMinecraft();
		if(mc.inGameHasFocus){
			ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
			FontRenderer fr = mc.fontRendererObj;
			int width = res.getScaledWidth();
			int height = res.getScaledHeight();
			int x=0, y=0;
			String[] loc = ConfigValues.LOCATION.split("-");
			if(loc[0].equals("top"))
				y=4;
			if(loc[0].equals("center"))
				y=height/2;
			if(loc[0].equals("bottom"))
				y=height-4;
			if(loc[1].equals("left"))
				x=4;
			if(loc[1].equals("center"))
				x=width/2;
			if(loc[1].equals("right"))
				x=width-4;
			fr.drawString("test",x,y,-1);
		}
	}
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if(eventArgs.modID.equals(TimeHud.MODID))
			TimeHud.syncConfig();
	}
}
