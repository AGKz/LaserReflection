package agkz.mods.laserReflection.common;

import ic2.api.event.LaserEvent.LaserHitsBlockEvent;
import ic2.core.item.tool.EntityMiningLaser;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import agkz.mods.laserReflection.LaserReflection;

public class ReflectionLaserEvent {
	
	@ForgeSubscribe
	public void laserHitBlock(LaserHitsBlockEvent event) {
		World world = event.world;
		int x = event.x, y = event.y, z = event.z;
		
		int blockID = world.getBlockId(x, y, z);
		
		if (LaserReflection.rConfig.shouldLightPortal && !(world.provider.dimensionId > 0) && blockID == Block.obsidian.blockID) 
			LaserReflection.trySidesForPortal(world, x, y, z);
		
		IdMeta blockIdMeta = new IdMeta(blockID, world.getBlockMetadata(x, y, z));
		
		if (!LaserReflection.instance.reflectors.doesReflect(blockIdMeta)) return;
		
		if (event.lasershot instanceof EntityMiningLaser && !event.lasershot.getEntityData().getBoolean("reflected")) {
			
			EntityMiningLaser laser = (EntityMiningLaser)event.lasershot;
			int side  = event.side;
			
			double newMotionY = side == 0 || side == 1 ? laser.motionY*-1 : laser.motionY;
			double newMotionZ = side == 2 || side == 3 ? laser.motionZ*-1 : laser.motionZ;
			double newMotionX = side == 4 || side == 5 ? laser.motionX*-1 : laser.motionX;
			
			laser.setLaserHeading(newMotionX, newMotionY, newMotionZ, 0.9D);
			
			laser.getEntityData().setBoolean("reflected", true);
			event.removeBlock = false;
		}
	}
}
