package com.xumuk.realism.client.button;

import javax.annotation.Nonnull;

import com.xumuk.realism.RealismCore;
import com.xumuk.realism.packets.PacketGuiButton;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiButtonStone extends GuiButton {
	private final ResourceLocation texture;
	private final int idX;
	private final int idY;

	public GuiButtonStone(int id, int idX, int idY, int x, int y, int width, int height, ResourceLocation texture) {
		super(id, x, y, width, height, "");
		this.texture = texture;
		this.idX = idX;
		this.idY = idY;
	}

	public void onClick() {
		if (this.enabled) {
			this.visible = false;
			RealismCore.network_handler.sendToServer(new PacketGuiButton(this.idX, this.idY));
		}
	}

	@Override
	public void drawButton(@Nonnull Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		if (this.visible) {
			GlStateManager.color(1, 1, 1, 1);
			mc.getTextureManager().bindTexture(texture);

			hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;

			drawModalRectWithCustomSizedTexture(x, y, 0, 0, 16, 16, 16, 16);
			mouseDragged(mc, mouseX, mouseY);
		}
	}
}