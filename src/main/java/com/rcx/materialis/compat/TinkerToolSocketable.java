package com.rcx.materialis.compat;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.rcx.materialis.Materialis;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.common.util.LazyOptional;
import slimeknights.tconstruct.library.tools.nbt.IModDataReadOnly;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.api.cad.IPsiBarDisplay;
import vazkii.psi.api.cad.ISocketable;
import vazkii.psi.api.internal.IPlayerData;
import vazkii.psi.api.spell.ISpellAcceptor;
import vazkii.psi.api.spell.Spell;

public class TinkerToolSocketable implements ICapabilityProvider, ISocketable, IPsiBarDisplay, ISpellAcceptor {

	protected final ItemStack tool;
	private final LazyOptional<?> capOptional;
	public static final ResourceLocation SOCKETS = new ResourceLocation(Materialis.modID, "spell_sockets");
	public static final ResourceLocation SELECTED_SPELL = new ResourceLocation(Materialis.modID, "selected_slot");
	public static final ResourceLocation[] SPELL_SLOTS = new ResourceLocation[] {
			new ResourceLocation(Materialis.modID, "spell_slot_0"),
			new ResourceLocation(Materialis.modID, "spell_slot_1"),
			new ResourceLocation(Materialis.modID, "spell_slot_2"),
			new ResourceLocation(Materialis.modID, "spell_slot_3"),
			new ResourceLocation(Materialis.modID, "spell_slot_4"),
			new ResourceLocation(Materialis.modID, "spell_slot_5"),
			new ResourceLocation(Materialis.modID, "spell_slot_6"),
			new ResourceLocation(Materialis.modID, "spell_slot_7"),
			new ResourceLocation(Materialis.modID, "spell_slot_8"),
			new ResourceLocation(Materialis.modID, "spell_slot_9"),
			new ResourceLocation(Materialis.modID, "spell_slot_10"),
			new ResourceLocation(Materialis.modID, "spell_slot_11")
	};

	public TinkerToolSocketable(ItemStack tool) {
		this.tool = tool;
		this.capOptional = LazyOptional.of(() -> this);
	}

	public int getSlots() {
		IModDataReadOnly volatileData = ToolStack.from(tool).getVolatileData();
		if (volatileData.contains(SOCKETS, NBT.TAG_INT))
			return Math.min(volatileData.getInt(SOCKETS), MAX_ASSEMBLER_SLOTS - 1);
		return Math.min(volatileData.getInt(SOCKETS), MAX_ASSEMBLER_SLOTS - 1);

	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		if (getSlots() > 0 && (cap == PsiAPI.SOCKETABLE_CAPABILITY || cap == PsiAPI.PSI_BAR_DISPLAY_CAPABILITY || cap == PsiAPI.SPELL_ACCEPTOR_CAPABILITY)) {
			return capOptional.cast();
		}
		return LazyOptional.empty();
	}

	@Override
	public boolean isSocketSlotAvailable(int slot) {
		return slot < getSlots();
	}

	@Override
	public List<Integer> getRadialMenuSlots() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i <= getSlots(); i++) {
			list.add(i);
		}
		return list;
	}

	@Override
	public ItemStack getBulletInSocket(int slot) {
		IModDataReadOnly persistentData = ToolStack.from(tool).getPersistentData();
		if (!persistentData.contains(SPELL_SLOTS[slot], NBT.TAG_COMPOUND))
			return ItemStack.EMPTY;
		CompoundNBT cmp = persistentData.getCompound(SPELL_SLOTS[slot]);

		if (cmp.isEmpty()) {
			return ItemStack.EMPTY;
		}
		return ItemStack.of(cmp);
	}

	@Override
	public void setBulletInSocket(int slot, ItemStack bullet) {
		CompoundNBT cmp = new CompoundNBT();

		if (bullet.isEmpty()) {
			ToolStack.from(tool).getPersistentData().remove(SPELL_SLOTS[slot]);
			return;
		}
		cmp = bullet.save(cmp);
		ToolStack.from(tool).getPersistentData().put(SPELL_SLOTS[slot], cmp);
	}

	@Override
	public int getSelectedSlot() {
		IModDataReadOnly persistentData = ToolStack.from(tool).getPersistentData();
		if (persistentData.contains(SELECTED_SPELL, NBT.TAG_INT))
			return persistentData.getInt(SELECTED_SPELL);
		return 0;
	}

	@Override
	public void setSelectedSlot(int slot) {
		ToolStack.from(tool).getPersistentData().putInt(SELECTED_SPELL, slot);
	}

	@Override
	public boolean shouldShow(IPlayerData data) {
		return false;
	}

	@Override
	public void setSpell(PlayerEntity player, Spell spell) {
		int slot = getSelectedSlot();
		ItemStack bullet = getBulletInSocket(slot);
		if (!bullet.isEmpty() && ISpellAcceptor.isAcceptor(bullet)) {
			ISpellAcceptor.acceptor(bullet).setSpell(player, spell);
			setBulletInSocket(slot, bullet);
		}
	}

	@Override
	public boolean castableFromSocket() {
		return false;
	}
}
