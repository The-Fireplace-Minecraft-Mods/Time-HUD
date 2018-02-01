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
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if (eventArgs.getModID().equals(TimeHud.MODID))
			TimeHud.syncConfig();
	}
}
