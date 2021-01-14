package com.rcx.materialis.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.shared.client.ParticleEffect;
import slimeknights.tconstruct.tools.TinkerTools;

public class TraitIntangible extends AbstractTrait {

	public static String id = "intangible";
	public static float chance = 0.3f;

	public TraitIntangible() {
		super(id, 0xBDBDBD);
	}

	@Override
	public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {
		if(target.isEntityAlive() && wasHit && random.nextFloat() < chance) {
			float damage = 1.5f;
			if(damage > 0) {
				DamageSource damageSource;
				if(player instanceof EntityPlayer)
					damageSource = DamageSource.causePlayerDamage((EntityPlayer) player);
				else
					damageSource = DamageSource.causeMobDamage(player);
				damageSource.setDamageBypassesArmor();
				damageSource.setDamageIsAbsolute();

				if(attackEntitySecondary(damageSource, damage, target, true, false)) {
					TinkerTools.proxy.spawnEffectParticle(ParticleEffect.Type.HEART_ARMOR, target, 1);
				}
			}
		}
	}
}
