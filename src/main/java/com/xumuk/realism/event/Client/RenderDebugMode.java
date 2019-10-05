package com.xumuk.realism.event.Client;

import org.lwjgl.opengl.GL11;

import com.xumuk.realism.capability.playerCAP.IPlayerCap;
import com.xumuk.realism.capability.playerCAP.PlayerCapProvider;
import com.xumuk.realism.capability.worldCAP.DateProvider;
import com.xumuk.realism.capability.worldCAP.IDate;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderDebugMode {


	static Minecraft mc = Minecraft.getMinecraft();

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void calendar(RenderGameOverlayEvent.Post event) {
		World world = mc.world;

		IDate date = world.getCapability(DateProvider.DATE, null);
		IPlayerCap cap = Minecraft.getMinecraft().player.getCapability(PlayerCapProvider.LEVEL_CAP, null);
		Biome biome = world.getBiome(Minecraft.getMinecraft().player.getPosition());
		if ( event.getType() != ElementType.HOTBAR) return;
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		FontRenderer fontrenderer = Minecraft.getMinecraft().fontRenderer;

		int x = event.getResolution().getScaledWidth() / 2 + 10;
		int y = event.getResolution().getScaledHeight() - 49;
		
		fontrenderer.drawString("Day: "  + date.getDay(), x - 240, y - 90, 0xFFFFFF);
		fontrenderer.drawString("Month: "  + this.getMonthName(), x- 240, y - 100, 0xFFFFFF);
		fontrenderer.drawString("Year: "  + date.getYear(), x- 240, y - 110, 0xFFFFFF);
		fontrenderer.drawString("Hour: " + world.getWorldTime()/1000, x- 240, y - 80, 0xFFFFFF);
		fontrenderer.drawString("World Temp: " + biome.temperature, x- 240, y - 70, 0xFFFFFF);
		fontrenderer.drawString("Biome: " + biome.getBiomeName(), x- 240, y - 60, 0xFFFFFF);
		//fontrenderer.drawString("Temp Body: " + cap.getTempBody(), x+ 180, y - 120, 0x000000);
	//	fontrenderer.drawString("Water Level: " + cap.getWaterLevel(), x+ 180, y - 130, 0x000000);

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}


	public String getMonthName() {
		Minecraft mc = Minecraft.getMinecraft();
		World world = mc.world;
		IDate date = world.getCapability(DateProvider.DATE, null);
		String[] monthName = new String[] {"January","February","March","April","May","June","Jule","August","September","October","November","December"};

		return monthName[date.getMonth()];
	}
}

