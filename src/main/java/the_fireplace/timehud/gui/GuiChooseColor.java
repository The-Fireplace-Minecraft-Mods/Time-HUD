package the_fireplace.timehud.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import the_fireplace.timehud.TimeHud;
import the_fireplace.timehud.config.ConfigValues;

import java.io.IOException;
import java.nio.IntBuffer;

@SuppressWarnings("unchecked")
public class GuiChooseColor extends GuiScreen {
	protected int xSize = 176;
	protected int ySize = 110;
	protected int guiLeft;
	protected int guiTop;

	private int selectedColor = ConfigValues.FONTCOLOR;
	private boolean colorPicking = false;
	private IntBuffer screenPixels;

	private static final ResourceLocation resource = new ResourceLocation(TimeHud.MODID, "textures/gui/guichoosecolor.png");

	private GuiButton colorPick;
	private GuiButton done;

	public GuiChooseColor(){
		super();

		height=ySize;
		width=xSize;
	}

	@Override
	public void initGui() {
		this.guiLeft = (this.width - this.xSize) / 2;
		this.guiTop = (this.height - this.ySize) / 2;
		buttonList.add(colorPick = new GuiButton(0, 124, 5, 60, 20, I18n.format("gui.button.color")));
		buttonList.add(done = new GuiButton(1, 124, 85, 60, 20, I18n.format("gui.done")));

		super.initGui();
	}

	@Override
	protected void actionPerformed(GuiButton button){
		if (button.enabled)
		{
			switch(button.id){
				case 0:
					//Enable color pick mode
					selectedColor =ConfigValues.FONTCOLOR;
					colorPick.enabled=false;
					done.enabled=false;
					colorPicking=true;
					break;
				case 1:
					mc.displayGuiScreen(null);
					break;
			}
		}
	}

	@Override
	public void onGuiClosed()
	{
		TimeHud.FONTCOLOR_PROPERTY.set(selectedColor);
		TimeHud.syncConfig();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		guiLeft = (width - xSize) / 2;
		guiTop = (height - ySize) / 2;
		int i = this.guiLeft;
		int j = this.guiTop;
		GlStateManager.translate(guiLeft, guiTop, 0.0f);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(resource);
		drawTexturedModalRect(0, 0, 0, 0, xSize, ySize);
		GlStateManager.disableRescaleNormal();
		RenderHelper.disableStandardItemLighting();
		GlStateManager.disableLighting();
		GlStateManager.disableDepth();
		super.drawScreen(mouseX-guiLeft, mouseY-guiTop, partialTicks);

		drawString(mc.fontRenderer, I18n.format("sample"), 124, 45, selectedColor);
	}

	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick)
	{
		super.mouseClickMove(mouseX-guiLeft, mouseY-guiTop, clickedMouseButton, timeSinceLastClick);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
	{
		if (colorPicking && mouseButton == 0) {
			selectedColor = getPixelRGB(Mouse.getEventX(), Mouse.getEventY());
			colorPicking=false;
			done.enabled=true;
			colorPick.enabled=true;
		}
		super.mouseClicked(mouseX-guiLeft, mouseY-guiTop, mouseButton);
	}

	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state)
	{
		super.mouseReleased(mouseX-guiLeft, mouseY-guiTop, state);
	}

	int getPixelRGB(int x, int y) {
		int[] pixelData;
		int pixels = 1;

		if (screenPixels == null)
			screenPixels = BufferUtils.createIntBuffer(pixels);
		pixelData = new int[pixels];

		GL11.glPixelStorei(GL11.GL_PACK_ALIGNMENT, 1);
		GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
		screenPixels.clear();

		GlStateManager.glReadPixels(x, y, 1, 1, GL12.GL_BGRA, GL12.GL_UNSIGNED_INT_8_8_8_8_REV, screenPixels);

		screenPixels.get(pixelData);
		TextureUtil.processPixelValues(pixelData, 1, 1);

		return pixelData[0] & 0xFFFFFF;
	}
}