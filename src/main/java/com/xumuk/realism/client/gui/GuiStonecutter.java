package com.xumuk.realism.client.gui;

import static com.xumuk.realism.RealismCore.MODID;

import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.opengl.GL11;

import com.xumuk.realism.client.button.GuiButtonStone;
import com.xumuk.realism.inventory.ContainerStonecutter;
import com.xumuk.realism.utils.IRock;
import com.xumuk.realism.utils.SRUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;

public class GuiStonecutter extends SRGuiContainer {

	private static final ResourceLocation BG = new ResourceLocation(MODID, "textures/gui/stonecutter.png");
	private static final ResourceLocation BG_LAST = new ResourceLocation(MODID, "textures/gui/stonecutter_last.png");
	private static final ResourceLocation BUTTON = new ResourceLocation(MODID, "textures/gui/stonecutter_button.png");

	private final int guiTextureColor;

	public GuiStonecutter(Container container, InventoryPlayer playerInv) {
		super(container, playerInv, BG);
		ItemStack stack = playerInv.getCurrentItem();
		if (stack.getItem() instanceof IRock)
			guiTextureColor = ((IRock) stack.getItem()).getRockColor(stack.getItemDamage());
		else guiTextureColor = -1;
		ySize = 184;
	}

	@Override
	protected void drawForegroundBackground() {
		boolean flag = guiTextureColor != -1;
		if (flag) {
			int[] color = SRUtils.dec2Rgb(guiTextureColor);
			GL11.glColor4d(color[0]/255.0, color[1]/255.0, color[2]/255.0, 1.0);
		}
		else GlStateManager.color(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(BG_LAST);
		drawTexturedModalRect(guiLeft + 11, guiTop + 11, 0, 0, xSize, ySize);
	}
	
	private void drawButton() {
		boolean flag = guiTextureColor != -1;
		if (flag) {
			int[] color = SRUtils.dec2Rgb(guiTextureColor);
			GL11.glColor4d(color[0]/255.0, color[1]/255.0, color[2]/255.0, 1.0);
		}
		else GlStateManager.color(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(BUTTON);
	}

	@Override
	public void initGui() {
		super.initGui();
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 5; y++) {
				int bx = (width - xSize) / 2 + 12 + 16 * x;
				int by = (height - ySize) / 2 + 12 + 16 * y;
				addButton(new GuiButtonStone(x + 5 * y, y, x, bx, by, 16, 16, this::drawButton));
			}
		}
	}

	@Override
	public void onResize(Minecraft mcIn, int w, int h) {
		List<Integer> list = buttonList.stream().filter(e -> !e.visible).map(e -> e.id).collect(Collectors.toList());
		super.onResize(mcIn, w, h);
		buttonList.stream().filter(e -> list.contains(e.id)).forEach(e -> e.visible = false);
	}

	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
		if (clickedMouseButton == 0) {
			for (GuiButton button : this.buttonList) {
				if (button instanceof GuiButtonStone && button.mousePressed(mc, mouseX, mouseY)) {
					GuiScreenEvent.ActionPerformedEvent.Pre event = new GuiScreenEvent.ActionPerformedEvent.Pre(this,
							button, buttonList);
					if (MinecraftForge.EVENT_BUS.post(event)) break;
					else if (selectedButton == event.getButton()) continue;

					selectedButton = event.getButton();
					event.getButton().mousePressed(mc, mouseX, mouseY);
					actionPerformed(event.getButton());

					MinecraftForge.EVENT_BUS
							.post(new GuiScreenEvent.ActionPerformedEvent.Post(this, event.getButton(), buttonList));
				}
			}
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		if (inventorySlots instanceof ContainerStonecutter
				&& ((ContainerStonecutter) inventorySlots).isRequiresReset()) {
			for (GuiButton button : buttonList) if (button instanceof GuiButtonStone) button.visible = false;
			((ContainerStonecutter) inventorySlots).setRequiresResetToFalse();
		}
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		((GuiButtonStone) button).onClick();
		button.playPressSound(mc.getSoundHandler());
	}
}