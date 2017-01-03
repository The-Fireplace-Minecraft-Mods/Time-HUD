package the_fireplace.timehud.config;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.config.GuiConfig;
import the_fireplace.timehud.TimeHud;

public class TimeHudConfigGui extends GuiConfig {
	public TimeHudConfigGui(GuiScreen parentScreen) {
		super(parentScreen, new ConfigElement(TimeHud.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), TimeHud.MODID, false,
				false, GuiConfig.getAbridgedConfigPath(TimeHud.config.toString()));
	}
}
