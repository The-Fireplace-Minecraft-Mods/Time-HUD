package the_fireplace.timehud.config;

import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.config.IConfigElement;
import the_fireplace.timehud.TimeHud;

/**
 * @author The_Fireplace
 */
public class FormatEntries extends GuiConfigEntries.SelectValueEntry {
	public FormatEntries(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement configElement) {
		super(owningScreen, owningEntryList, configElement, TimeHud.formats);
	}
}
