package net.mcreator.paintovermark.procedures;

import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.network.PacketBuffer;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.paintovermark.gui.AttentionGui;
import net.mcreator.paintovermark.PaintOverMarkMod;

import java.util.Map;
import java.util.HashMap;

import io.netty.buffer.Unpooled;

public class Attention21Procedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
			Entity entity = event.getPlayer();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", entity.getPosX());
			dependencies.put("y", entity.getPosY());
			dependencies.put("z", entity.getPosZ());
			dependencies.put("world", entity.world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			executeProcedure(dependencies);
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				PaintOverMarkMod.LOGGER.warn("Failed to load dependency world for procedure Attention21!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				PaintOverMarkMod.LOGGER.warn("Failed to load dependency x for procedure Attention21!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				PaintOverMarkMod.LOGGER.warn("Failed to load dependency y for procedure Attention21!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				PaintOverMarkMod.LOGGER.warn("Failed to load dependency z for procedure Attention21!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				PaintOverMarkMod.LOGGER.warn("Failed to load dependency entity for procedure Attention21!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		{
			Entity _ent = entity;
			if (_ent instanceof ServerPlayerEntity) {
				BlockPos _bpos = new BlockPos((int) x, (int) y, (int) z);
				NetworkHooks.openGui((ServerPlayerEntity) _ent, new INamedContainerProvider() {
					@Override
					public ITextComponent getDisplayName() {
						return new StringTextComponent("Attention");
					}

					@Override
					public Container createMenu(int id, PlayerInventory inventory, PlayerEntity player) {
						return new AttentionGui.GuiContainerMod(id, inventory, new PacketBuffer(Unpooled.buffer()).writeBlockPos(_bpos));
					}
				}, _bpos);
			}
		}
	}
}
