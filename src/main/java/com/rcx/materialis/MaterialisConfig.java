package com.rcx.materialis;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

@Config(modid = Materialis.ID, name = Materialis.ID, category = "")
public class MaterialisConfig {

	@Name("blacklist")
	@LangKey("materialis.config.blacklist")
	@Comment("Blacklist settings")
	public static CategoryBlacklist blacklist = new CategoryBlacklist();

	public static class CategoryBlacklist {

		@RequiresMcRestart
		@LangKey("materialis.config.blacklist.materials")
		@Comment("Add the id of a material to this list to prevent it from being added.")
		public String[] materialBlacklist = new String[] {};

		@RequiresMcRestart
		@LangKey("materialis.config.blacklist.modules")
		@Comment("Add the name of a module to this list to prevent it from being loaded.")
		public String[] moduleBlacklist = new String[] {};

		public Boolean isMaterialBlacklisted(String material) {
			for (String blacklistedMaterial : materialBlacklist) {
				if (blacklistedMaterial.equals(material))
					return true;
			}
			return false;
		}

		public Boolean isModuleBlacklisted(String module) {
			for (String blacklistedModule : moduleBlacklist) {
				if (blacklistedModule.equals(module))
					return true;
			}
			return false;
		}
	}
}
