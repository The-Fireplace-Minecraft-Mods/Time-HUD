package the_fireplace.timehud.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import the_fireplace.timehud.config.ConfigValues;

/**
 * @author The_Fireplace
 */
public class HUDFontRenderer extends FontRenderer {
	public HUDFontRenderer() {
		super(Minecraft.getMinecraft().gameSettings, new ResourceLocation("textures/font/ascii.png"), Minecraft.getMinecraft().getTextureManager(), false);
	}
	@Override
	protected float renderDefaultChar(int ch, boolean italic)
	{
		int i = ch % 16 * 8;
		int j = ch / 16 * 8;
		int k = italic ? 1 : 0;
		bindTexture(this.locationFontTexture);
		int l = this.charWidth[ch];
		float f = (float)l - 0.01F;
		GlStateManager.glBegin(5);
		GlStateManager.glTexCoord2f((float)i / 128.0F, (float)j / 128.0F);
		GlStateManager.glVertex3f(this.posX + (float)k, this.posY, 0.0F);
		GlStateManager.glTexCoord2f((float)i / 128.0F, ((float)j + 7.99F) / 128.0F);
		GlStateManager.glVertex3f(this.posX - (float)k, this.posY + (7.99F/9.0F)*ConfigValues.FS, 0.0F);
		GlStateManager.glTexCoord2f(((float)i + f - 1.0F) / 128.0F, (float)j / 128.0F);
		GlStateManager.glVertex3f(this.posX + f*(ConfigValues.FS/9.0F) - 1.0F + (float)k, this.posY, 0.0F);
		GlStateManager.glTexCoord2f(((float)i + f - 1.0F) / 128.0F, ((float)j + 7.99F) / 128.0F);
		GlStateManager.glVertex3f(this.posX + f*(ConfigValues.FS/9.0F) - 1.0F - (float)k, this.posY + (7.99F/9.0F)*ConfigValues.FS, 0.0F);
		GlStateManager.glEnd();
		return (float)l;
	}
	@Override
	public int getCharWidth(char character)
	{
		return super.getCharWidth(character)*(ConfigValues.FS/9);
	}
}
