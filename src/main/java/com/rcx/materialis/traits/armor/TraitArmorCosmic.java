package com.rcx.materialis.traits.armor;

import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.Tags;

public class TraitArmorCosmic extends AbstractArmorTrait {

	public TraitArmorCosmic() {
		super("cosmic", 0xFF5555);
	}

	@Override
	public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag) {
		super.applyEffect(rootCompound, modifierTag);
		//extra modifiers
		int plusmod = 5;
		NBTTagCompound toolTag = TagUtil.getToolTag(rootCompound);
		int modifiers = toolTag.getInteger(Tags.FREE_MODIFIERS) + plusmod;
		toolTag.setInteger(Tags.FREE_MODIFIERS, Math.max(0, modifiers));
		TagUtil.setToolTag(rootCompound, toolTag);
	}

	@Override
	public float onHurt(ItemStack armor, EntityPlayer player, DamageSource source, float damage, float newDamage, LivingHurtEvent event) {
		//invincivility
		if (!event.getSource().damageType.equals("infinity")) {
			event.setCanceled(true);
			return 0;
		}
		return damage;
	}

	//not currently working
	/*@Override
	public void onArmorEquipped(ItemStack armor, EntityPlayer player, int slot) {
		//creative flight
		if (slot == 2)
			handleChestplateStateChange(player, true);
		//sanic speed
		if (slot == 0)
			handleBootsStateChange(player, true);
	}

	@Override
	public void onArmorRemoved(ItemStack armor, EntityPlayer player, int slot) {
		//creative flight
		if (slot == 2)
			handleChestplateStateChange(player, false);
		//sanic speed
		if (slot == 0)
			handleBootsStateChange(player, false);
	}

	//copied private methods
	public static void handleChestplateStateChange(EntityLivingBase entity, boolean isNew) {
		//String key = entity.getCachedUniqueIdString() + "|" + entity.world.isRemote;
		if (entity instanceof EntityPlayer) {
			EntityPlayer player = ((EntityPlayer) entity);
			if (isNew) {
				player.capabilities.allowFlying = true;
				//AbilityHandler.entitiesWithFlight.add(key);
			} else {
				if (!player.capabilities.isCreativeMode) {// && AbilityHandler.entitiesWithFlight.contains(key)) {
					player.capabilities.allowFlying = false;
					player.capabilities.isFlying = false;
					//AbilityHandler.entitiesWithFlight.remove(key);
				}
			}
		}
	}

	public static void handleBootsStateChange(EntityLivingBase entity, boolean hasBoots) {
		//String temp_key = entity.getCachedUniqueIdString() + "|" + entity.world.isRemote;
		if (hasBoots) {
			entity.stepHeight = 1.0625F;//Step 17 pixels, Allows for stepping directly from a path to the top of a block next to the path.
			//if (!AbilityHandler.entitiesWithBoots.contains(temp_key)) {
				//AbilityHandler.entitiesWithBoots.add(temp_key);
			//}
		} else {
			//if (AbilityHandler.entitiesWithBoots.contains(temp_key)) {
				entity.stepHeight = 0.5F;
				//AbilityHandler.entitiesWithBoots.remove(temp_key);
			//}
		}
	}*/
}
