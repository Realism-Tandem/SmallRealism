package com.xumuk.realism.client.button;

import javax.annotation.Nonnull;

import com.xumuk.realism.packets.NetworkHandler;
import com.xumuk.realism.packets.PacketGuiButton;
import com.xumuk.realism.utils.SupplierFunction;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiButtonStone extends GuiButton {
	private final int idX;
	private final int idY;
	private final SupplierFunction render;

	public GuiButtonStone(int id, int idX, int idY, int x, int y, int width, int height, SupplierFunction render) {
		super(id, x, y, width, height, "");
		this.idX = idX;
		this.idY = idY;
		this.render = render;
	}

	public void onClick() {
		if (this.enabled) {
			this.visible = false;
			NetworkHandler.sendToServer(new PacketGuiButton(this.idX, this.idY));
		}
	}

	@Override
	public void drawButton(@Nonnull Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		if (this.visible) {
			render.get();		
			hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
			drawModalRectWithCustomSizedTexture(x, y, 0, 0, 16, 16, 16, 16);
			mouseDragged(mc, mouseX, mouseY);
		}
	}
}