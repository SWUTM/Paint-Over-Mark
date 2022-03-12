package net.mcreator.paintovermark.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.state.Property;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.paintovermark.item.SprayCanItem;
import net.mcreator.paintovermark.item.BrushItem;
import net.mcreator.paintovermark.block.PaintOverBlock;
import net.mcreator.paintovermark.PaintOverMarkMod;

import java.util.Map;

public class WhiteBadMarksOnBlockRightClickedProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				PaintOverMarkMod.LOGGER.warn("Failed to load dependency world for procedure WhiteBadMarksOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				PaintOverMarkMod.LOGGER.warn("Failed to load dependency x for procedure WhiteBadMarksOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				PaintOverMarkMod.LOGGER.warn("Failed to load dependency y for procedure WhiteBadMarksOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				PaintOverMarkMod.LOGGER.warn("Failed to load dependency z for procedure WhiteBadMarksOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				PaintOverMarkMod.LOGGER.warn("Failed to load dependency entity for procedure WhiteBadMarksOnBlockRightClicked!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem() == SprayCanItem.block) {
			{
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				BlockState _bs = PaintOverBlock.block.getDefaultState();
				BlockState _bso = world.getBlockState(_bp);
				for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
					Property _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
					if (_property != null && _bs.get(_property) != null)
						try {
							_bs = _bs.with(_property, (Comparable) entry.getValue());
						} catch (Exception e) {
						}
				}
				world.setBlockState(_bp, _bs, 3);
			}
			if (world instanceof World && !world.isRemote()) {
				((World) world).playSound(null, new BlockPos((int) x, (int) y, (int) z),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("paint_over_mark:spray_can")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1);
			} else {
				((World) world).playSound(x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("paint_over_mark:spray_can")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
			}
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.LARGE_SMOKE, x, y, z, (int) 1000, 2, 2, 2, 1);
			}
		} else if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == BrushItem.block) {
			{
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				BlockState _bs = Blocks.AIR.getDefaultState();
				BlockState _bso = world.getBlockState(_bp);
				for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
					Property _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
					if (_property != null && _bs.get(_property) != null)
						try {
							_bs = _bs.with(_property, (Comparable) entry.getValue());
						} catch (Exception e) {
						}
				}
				world.setBlockState(_bp, _bs, 3);
			}
			if (world instanceof World && !world.isRemote()) {
				((World) world).playSound(null, new BlockPos((int) x, (int) y, (int) z),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("paint_over_mark:brush")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1);
			} else {
				((World) world).playSound(x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("paint_over_mark:brush")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
			}
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.BUBBLE, x, y, z, (int) 1000, 1, 1, 1, 1);
			}
		}
	}
}
