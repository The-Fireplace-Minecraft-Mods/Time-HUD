package the_fireplace.timehud.config;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;
import the_fireplace.timehud.TimeHud;

import java.util.Set;

public class TimeHudGuiFactory implements IModGuiFactory {
	@Override
	public void initialize(Minecraft minecraftInstance) {}

	@Override
	public boolean hasConfigGui() {
		return true;
	}

	@Override
	public GuiScreen createConfigGui(GuiScreen parentScreen) {
		return new GuiConfig(parentScreen, new ConfigElement(TimeHud.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), TimeHud.MODID, false,
				false, GuiConfig.getAbridgedConfigPath(TimeHud.config.toString()));
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}
}
