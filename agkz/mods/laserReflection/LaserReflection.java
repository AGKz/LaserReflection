package agkz.mods.laserReflection;

import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import agkz.mods.laserReflection.common.IdMeta;
import agkz.mods.laserReflection.common.ReflectionConfig;
import agkz.mods.laserReflection.common.ReflectionLaserEvent;
import agkz.mods.laserReflection.common.ReflectorBlocks;
import agkz.mods.lazerz.common.CommonProxy;

import com.google.common.collect.ImmutableList;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
/**
 * LaserReflection - Child mod to the Lazerz mod. Adds reflection to blocks
 * 
 * Created as a separate mod to simply demonstrate uses of IMCs as well as to allow for independent distribution without the need of adding Lazerz
 * @author AGKz, with credit to IC2 and GregoriusT for the LaserEvents to allow for this type of thing.
 */
@Mod(modid = "LaserReflection", version = "1.0", useMetadata = true)
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class LaserReflection {
	
	@SidedProxy(clientSide="agkz.mods.lazerz.client.ClientProxy", serverSide="agkz.mods.lazerz.common.CommonProxy")
	public static CommonProxy proxy;
	
	// Instance of Lazerz
	@Instance("LaserReflection")
	public static LaserReflection instance;
	
	
	public static Logger logger;
	public static ReflectionConfig rConfig;
	public ReflectorBlocks reflectors;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		MinecraftForge.EVENT_BUS.register(new ReflectionLaserEvent());
		
		reflectors = new ReflectorBlocks();
		
		rConfig = new ReflectionConfig();
		rConfig.setConfig(new Configuration(event.getSuggestedConfigurationFile()));
		rConfig.loadConfig();
	}
	
	@EventHandler
	public void receiveMessages(IMCEvent event) {
		ImmutableList<IMCMessage> currentList = event.getMessages();
		
		for (int i = 0; i < currentList.size(); i++)
		{
			IMCMessage message = currentList.get(i);
			if (message.key.equals("registerReflector")) {
				String idMeta = message.getStringValue();
				reflectors.addReflection(new IdMeta(idMeta));
			} else {
				logger.warning("Received unknown IMC from " + message.getSender() + " with key: " + message.key + "message: " + message.getStringValue());
			}
		}
	}
	
	public static void trySidesForPortal(World world, int x, int y, int z) {
		if (Block.portal.tryToCreatePortal(world, x, y+1, z)) return;
		if (Block.portal.tryToCreatePortal(world, x+1, y, z)) return;
		if (Block.portal.tryToCreatePortal(world, x-1, y, z)) return;
		if (Block.portal.tryToCreatePortal(world, x, y, z-1)) return;
		if (Block.portal.tryToCreatePortal(world, x, y, z+1)) return;
	}
	
}
