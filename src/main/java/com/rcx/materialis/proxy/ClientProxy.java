package com.rcx.materialis.proxy;

import com.rcx.materialis.MaterialisRegistry;
import com.rcx.materialis.render.TexturedOutlineRenderInfoDeserializer;
import com.rcx.materialis.render.TriColorRenderInfoDeserializer;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import slimeknights.tconstruct.library.client.material.MaterialRenderInfoLoader;
import slimeknights.tconstruct.smeltery.block.BlockMolten;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent preEvent) {
		super.preInit(preEvent);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}

	@Override
	public void postInit(FMLPostInitializationEvent postEvent) {
		super.postInit(postEvent);
	    MaterialRenderInfoLoader.addRenderInfo("tri_color", TriColorRenderInfoDeserializer.class);
	    MaterialRenderInfoLoader.addRenderInfo("textured_outline", TexturedOutlineRenderInfoDeserializer.class);
	}

	@Override
	public void registerItems(RegistryEvent.Register<Item> event) {
		super.registerItems(event);
	}

	@Override
	public void registerModels(ModelRegistryEvent event) {
		super.registerModels(event);
		for (ItemBlock block : MaterialisRegistry.blocks) {
			if (block.getBlock() instanceof BlockMolten) {
				ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation(block.getRegistryName(), "normal"));
				ModelLoader.setCustomStateMapper(block.getBlock(), new StateMapperBase() {
					@Override
					public ModelResourceLocation getModelResourceLocation(IBlockState state) {
						return new ModelResourceLocation(block.getRegistryName(), "normal");
					}});
				continue;
			}
			ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
		}

		for (Item item : MaterialisRegistry.items) {
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
	}
}
