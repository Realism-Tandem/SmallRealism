package com.xumuk.realism.client.gui;

import static com.xumuk.realism.RealismCore.MODID;

import com.xumuk.realism.client.gui.button.GuiButtonStone;
import com.xumuk.realism.inventory.ContainerStonecutter;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;

public class GuiStonecutter extends SRGuiContainer {

	private static final ResourceLocation BG = new ResourceLocation(MODID, "textures/gui/stonecutter.png");
	private static final ResourceLocation BUTTON = new ResourceLocation(MODID, "textures/gui/stonecutter_button.png");

	private final ResourceLocation buttonTexture;

	public GuiStonecutter(Container container, InventoryPlayer playerInv) {
		super(container, playerInv, BG);
		this.buttonTexture = BUTTON;
		ySize = 184;
	}

	@Override
	public void initGui() {
		super.initGui();
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 5; y++) {
				int bx = (width - xSize) / 2 + 12 + 16 * x;
				int by = (height - ySize) / 2 + 12 + 16 * y;
				addButton(new GuiButtonStone(x + 5 * y, x, y, bx, by, 16, 16, buttonTexture));
			}
		}
	}

	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
		if (clickedMouseButton == 0) {
			for (GuiButton button : this.buttonList) {
				if (button instanceof GuiButtonStone && button.mousePressed(mc, mouseX, mouseY)) {
					GuiScreenEvent.ActionPerformedEvent.Pre event = new GuiScreenEvent.ActionPerformedEvent.Pre(this, button, buttonList);
					if (MinecraftForge.EVENT_BUS.post(event)) break;
					else if (selectedButton == event.getButton()) continue;

					selectedButton = event.getButton();
					event.getButton().mousePressed(mc, mouseX, mouseY);
					actionPerformed(event.getButton());

					MinecraftForge.EVENT_BUS.post(new GuiScreenEvent.ActionPerformedEvent.Post(this, event.getButton(), buttonList));
				}
			}
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		if (inventorySlots instanceof ContainerStonecutter && ((ContainerStonecutter) inventorySlots).isRequiresReset()) {
			for (GuiButton button : buttonList) if (button instanceof GuiButtonStone) button.visible = false;
			((ContainerStonecutter) inventorySlots).setRequiresResetToFalse();
		}
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		GlStateManager.color(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(BUTTON);
		for (GuiButton button : buttonList) {
			if (!button.visible) {
				Gui.drawModalRectWithCustomSizedTexture(button.x, button.y, 0, 0, 16, 16, 16, 16);
			}
		}
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		if (button instanceof GuiButtonStone) {
			((GuiButtonStone) button).onClick();
			button.playPressSound(mc.getSoundHandler());
		}
	}
}