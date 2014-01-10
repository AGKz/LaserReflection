package agkz.mods.laserReflection.common;

import agkz.mods.laserReflection.LaserReflection;
import net.minecraft.block.Block;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class ReflectionConfig {
	public static Configuration config;
	public static boolean shouldLightPortal;

	public void setConfig(Configuration config) {
		this.config = config;
	}
	
	public Configuration getConfig() {
		return this.config;
	}
	
	public void loadConfig() {
		config.load();

		Property lightPortal = config.get("Laser Beam Features", "lightPortal", true);
		lightPortal.comment = "Should mining lasers be-able to light portals? Note: Only lights portals if beam hits one of the bottom blocks";
		shouldLightPortal = lightPortal.getBoolean(true);
		
		Property manualReflectors = config.get("Block Reflectors", "blocks", defaultIdMetas());
		manualReflectors.comment = "What shifted blocks IDs (usually shifted ID += 256 of IDs in a config]) should reflect a laser?";
		LaserReflection.instance.reflectors.setReflectorIDList(IdMeta.fromStringArray(manualReflectors.getStringList()));  

	    config.save();
	}
	
	/**
	 * Set the configurable defaults for blocks that reflect beams
	 * @return
	 */
	private String[] defaultIdMetas() {
		String[] string = new String[6];
			string[0] = new IdMeta(Block.glass.blockID, -1).toString();
			string[1] = new IdMeta(Block.blockDiamond.blockID, -1).toString();
			string[2] = new IdMeta(Block.blockEmerald.blockID, -1).toString();
			string[3] = new IdMeta(Block.blockGold.blockID, -1).toString();
			string[4] = new IdMeta(Block.blockNetherQuartz.blockID, -1).toString();
			string[5] = new IdMeta(Block.bedrock.blockID, -1).toString();
		return string;
	}
}
