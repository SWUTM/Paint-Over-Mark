package net.mcreator.paintovermark.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Entity;

import net.mcreator.paintovermark.block.GreenBadMarksBlock;
import net.mcreator.paintovermark.PaintOverMarkMod;

import java.util.Map;

public class GreenBadMarksUpdateTickProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				PaintOverMarkMod.LOGGER.warn("Failed to load dependency world for procedure GreenBadMarksUpdateTick!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				PaintOverMarkMod.LOGGER.warn("Failed to load dependency x for procedure GreenBadMarksUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				PaintOverMarkMod.LOGGER.warn("Failed to load dependency y for procedure GreenBadMarksUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				PaintOverMarkMod.LOGGER.warn("Failed to load dependency z for procedure GreenBadMarksUpdateTick!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == GreenBadMarksBlock.block) {
			if (world instanceof World && !world.isRemote()) {
				((World) world).playSound(null, new BlockPos((int) x, (int) y, (int) z),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("paint_over_mark:sirena21")),
						SoundCategory.NEUTRAL, (float) 100, (float) 1);
			} else {
				((World) world).playSound(x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("paint_over_mark:sirena21")),
						SoundCategory.NEUTRAL, (float) 100, (float) 1, false);
			}
			new Object() {
				private int ticks = 0;
				private float waitTicks;
				private IWorld world;

				public void start(IWorld world, int waitTicks) {
					this.waitTicks = waitTicks;
					MinecraftForge.EVENT_BUS.register(this);
					this.world = world;
				}

				@SubscribeEvent
				public void tick(TickEvent.ServerTickEvent event) {
					if (event.phase == TickEvent.Phase.END) {
						this.ticks += 1;
						if (this.ticks >= this.waitTicks)
							run();
					}
				}

				private void run() {
					if (world instanceof ServerWorld) {
						Entity entityToSpawn = new TNTEntity(EntityType.TNT, (World) world);
						entityToSpawn.setLocationAndAngles(x, (y + 27), z, world.getRandom().nextFloat() * 360F, 0);
						if (entityToSpawn instanceof MobEntity)
							((MobEntity) entityToSpawn).onInitialSpawn((ServerWorld) world,
									world.getDifficultyForLocation(entityToSpawn.getPosition()), SpawnReason.MOB_SUMMONED, (ILivingEntityData) null,
									(CompoundNBT) null);
						world.addEntity(entityToSpawn);
					}
					MinecraftForge.EVENT_BUS.unregister(this);
				}
			}.start(world, (int) 480);
		}
	}
}
