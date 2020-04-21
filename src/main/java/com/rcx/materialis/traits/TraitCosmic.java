package com.rcx.materialis.traits;

import morph.avaritia.handler.AvaritiaEventHandler;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.Tags;

public class TraitCosmic extends AbstractTrait {

	public TraitCosmic() {
		super("cosmic", 0xFF5555);
	}

	@Override
	public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag) {
		//extra modifiers
		int plusmod = 5;
		NBTTagCompound toolTag = TagUtil.getToolTag(rootCompound);
		int modifiers = toolTag.getInteger(Tags.FREE_MODIFIERS) + plusmod;
		toolTag.setInteger(Tags.FREE_MODIFIERS, Math.max(0, modifiers));
		TagUtil.setToolTag(rootCompound, toolTag);
	}

	@Override
	public void blockHarvestDrops(ItemStack tool, BlockEvent.HarvestDropsEvent event) {
		//fortune and fractured ores
		AvaritiaEventHandler.applyLuck(event, 4);
	}

	/*@Override//currently not working
	public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {
		//instakill
		if (player.world.isRemote) {
            return;
        }
        if (target instanceof EntityPlayer) {
            EntityPlayer pvp = (EntityPlayer) target;
            if (AvaritiaEventHandler.isInfinite(pvp)) {
            	target.attackEntityFrom(new DamageSourceInfinitySword(player).setDamageBypassesArmor(), 4.0F);
                return;
            }
            if (pvp.getHeldItem(EnumHand.MAIN_HAND) != null && pvp.getHeldItem(EnumHand.MAIN_HAND).getItem() == ModItems.infinity_sword && pvp.isHandActive()) {
                return;
            }
        }

        target.getCombatTracker().trackDamage(new DamageSourceInfinitySword(player), target.getHealth(), target.getHealth());
        target.setHealth(0);
        target.onDeath(new EntityDamageSource("infinity", player));
	}*/

	@Override
	public void miningSpeed(ItemStack tool, PlayerEvent.BreakSpeed event) {
		//mining slowdown prevention
		if (!event.getEntityLiving().onGround) {
            event.setNewSpeed(event.getNewSpeed() * 5);
        }
        if (!event.getEntityLiving().isInsideOfMaterial(Material.WATER) && !EnchantmentHelper.getAquaAffinityModifier(event.getEntityLiving())) {
            event.setNewSpeed(event.getNewSpeed() * 5);
        }
	}
}
