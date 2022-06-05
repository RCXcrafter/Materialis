package com.rcx.materialis.modifiers;

import java.util.List;

import com.hollingsworth.arsnouveau.api.spell.Spell;
import com.hollingsworth.arsnouveau.common.spell.casters.ReactiveCaster;
import com.rcx.materialis.util.MaterialisPacketHandler;
import com.rcx.materialis.util.PacketReactiveSwing;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.ModList;
import slimeknights.mantle.util.OffhandCooldownTracker;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.utils.RestrictedCompoundTag;
import slimeknights.tconstruct.library.utils.TooltipKey;
import slimeknights.tconstruct.tools.modifiers.ability.tool.OffhandAttackModifier;

public class ReactiveModifier extends Modifier {

	public static boolean enabled = ModList.get().isLoaded("ars_nouveau");

	public ReactiveModifier() {
		if (enabled) {
			MinecraftForge.EVENT_BUS.addListener(this::leftClick);
			MinecraftForge.EVENT_BUS.addListener(this::leftClickBlock);
		}
	}

	@Override
	public void beforeRemoved(IToolStackView tool, RestrictedCompoundTag tag) {
		tag.remove("ars_nouveau_reactiveCaster");
	}

	private void leftClick(PlayerInteractEvent.LeftClickEmpty event) {
		if (enabled && !event.getItemStack().isEmpty()) {
			ToolStack tool = getHeldTool(event.getEntityLiving(), InteractionHand.MAIN_HAND);
			if (tool != null && tool.getModifierLevel(this) > 0) {
				MaterialisPacketHandler.INSTANCE.sendToServer(new PacketReactiveSwing());
			}
		}
	}

	//mainhand attack that hits nothing
	public void recieveLeftClick(Player player) {
		if (enabled) {
			ToolStack tool = getHeldTool(player, InteractionHand.MAIN_HAND);
			if (tool != null && !tool.isBroken() && tool.getModifierLevel(this) > 0)
				castSpell(tool, player, player.getMainHandItem(), InteractionHand.MAIN_HAND);
		}
	}

	//offhand attack that doesn't hit an entity
	@Override
	public InteractionResult onToolUse(IToolStackView tool, int level, Level world, Player player, InteractionHand hand, EquipmentSlot slotType) {
		if (enabled && !tool.isBroken() && hand == InteractionHand.OFF_HAND && OffhandCooldownTracker.isAttackReady(player) && tool.getVolatileData().getBoolean(OffhandAttackModifier.DUEL_WIELDING)) {
			ItemStack toolStack = player.getItemBySlot(slotType);
			if (tool instanceof ToolStack)
				toolStack = ((ToolStack) tool).createStack();
			castSpell(tool, player, toolStack, InteractionHand.OFF_HAND);
		}
		return InteractionResult.PASS;
	}

	//mainhand attack that hits a block
	private void leftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
		if (enabled) {
			Player player = event.getPlayer();
			if(player.level.isClientSide)
				return;

			ToolStack tool = getHeldTool(player, InteractionHand.MAIN_HAND);
			if (tool != null && !tool.isBroken() && tool.getModifierLevel(this) > 0)
				castSpell(tool, player, event.getItemStack(), InteractionHand.MAIN_HAND);
		}
	}

	//attack that hits an entity
	@Override
	public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
		if (enabled && !tool.isBroken() && context.getPlayerAttacker() != null) {
			ItemStack toolStack = context.getPlayerAttacker().getItemInHand(context.getHand());
			if (tool instanceof ToolStack)
				toolStack = ((ToolStack) tool).createStack();
			castSpell(tool, context.getPlayerAttacker(), toolStack, context.getHand());
		}
		return 0;
	}

	@Override
	public void onAttacked(IToolStackView tool, int level, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
		if (enabled && !tool.isBroken() && context.getEntity() != null && context.getEntity() instanceof Player) {
			ItemStack toolStack = context.getEntity().getItemBySlot(slotType);
			if (tool instanceof ToolStack)
				toolStack = ((ToolStack) tool).createStack();
			castSpell(tool, (Player) context.getEntity(), toolStack, InteractionHand.MAIN_HAND);
		}
	}

	public void castSpell(IToolStackView tool, Player player, ItemStack stack, InteractionHand hand) {
		if (tool.getModifierLevel(this) * .25 >= Math.random() && new ReactiveCaster(stack).getSpell().isValid()){
			ReactiveCaster reactiveCaster = new ReactiveCaster(stack);
			reactiveCaster.castSpell(player.getCommandSenderWorld(), player, hand, null);
		}
	}

	@Override
	public void addInformation(IToolStackView tool, int level, Player player, List<Component> tooltip, TooltipKey key, TooltipFlag flag) {
		if (enabled && tool instanceof ToolStack) {
			Spell spell = new ReactiveCaster(((ToolStack) tool).createStack()).getSpell();
			if (spell.isValid()) {
				tooltip.add(new TextComponent(spell.getDisplayString()));
			}
		}
	}
}