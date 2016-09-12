package the_fireplace.timehud;

import com.mumfrey.liteloader.client.gui.GuiCheckbox;
import com.mumfrey.liteloader.modconfig.ConfigPanel;
import com.mumfrey.liteloader.modconfig.ConfigPanelHost;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;

/**
 * @author The_Fireplace
 */
public class GuiPanelConfig implements ConfigPanel {

    private GuiTextField location;
    private GuiTextField format;
    private GuiCheckbox real_time;
    private GuiCheckbox need_clock;
    private Minecraft minecraft = Minecraft.getMinecraft();

    @Override
    public String getPanelTitle() {
        return LiteModTimeHud.MODNAME + " Settings";
    }

    @Override
    public int getContentHeight() {
        return -1;
    }

    @Override
    public void onPanelShown(ConfigPanelHost host) {
        this.real_time = new GuiCheckbox(0, 20, 20, I18n.format("cfg.real"));
        this.real_time.checked = LiteModTimeHud.instance.real_time;
        this.need_clock = new GuiCheckbox(1, 20, 40, I18n.format("cfg.needclock"));
        this.need_clock.checked = LiteModTimeHud.instance.needclock;
    }

    @Override
    public void onPanelResize(ConfigPanelHost host) {

    }

    @Override
    public void onPanelHidden() {

    }

    @Override
    public void onTick(ConfigPanelHost host) {

    }

    @Override
    public void drawPanel(ConfigPanelHost host, int mouseX, int mouseY, float partialTicks) {

    }

    @Override
    public void mousePressed(ConfigPanelHost host, int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void mouseReleased(ConfigPanelHost host, int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void mouseMoved(ConfigPanelHost host, int mouseX, int mouseY) {

    }

    @Override
    public void keyPressed(ConfigPanelHost host, char keyChar, int keyCode) {

    }
}
