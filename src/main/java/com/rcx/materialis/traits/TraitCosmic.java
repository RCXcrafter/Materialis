package com.rcx.materialis.traits;

import morph.avaritia.handler.AvaritiaEventHandler;
import morph.avaritia.init.ModItems;
import morph.avaritia.util.DamageSourceInfinitySword;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.Tags;

public class TraitCosmic extends AbstractTrait {

	public static String id = "cosmic";

	public TraitCosmic() {
		super(id, 0xFF5555);
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

		//fortune X
		boolean harvest = false;
		boolean weapon = false;
		for(Category category : TagUtil.getCategories(rootCompound)) {
			if(category == Category.HARVEST) {
				harvest = true;
			}
			if(category == Category.WEAPON) {
				weapon = true;
			}
		}
		// weapons get looting
		if(weapon)
			setEnchantmentLevel(rootCompound, Enchantments.LOOTING, 10);
		// harvest tools get fortune
		if(harvest)
			setEnchantmentLevel(rootCompound, Enchantments.FORTUNE, 10);
	}

	@Override
	public void blockHarvestDrops(ItemStack tool, BlockEvent.HarvestDropsEvent event) {
		//fractured ores
		AvaritiaEventHandler.applyLuck(event, 4);
	}

	@Override
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
	}

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

	public static void setEnchantmentLevel(NBTTagCompound rootTag, Enchantment enchantment, int level) {
		NBTTagList enchantments = rootTag.getTagList("ench", 10);
		NBTTagCompound enchTag = new NBTTagCompound();
		int enchId = Enchantment.getEnchantmentID(enchantment);

		int id = -1;
		for(int i = 0; i < enchantments.tagCount(); i++) {
			if(enchantments.getCompoundTagAt(i).getShort("id") == enchId) {
				enchTag = enchantments.getCompoundTagAt(i);
				id = i;
				break;
			}
		}
		enchTag.setShort("id", (short) enchId);
		enchTag.setShort("lvl", (short) level);

		if(id < 0) {
			enchantments.appendTag(enchTag);
		}
		else {
			enchantments.set(id, enchTag);
		}
		rootTag.setTag("ench", enchantments);
	}
}
