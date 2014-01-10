package agkz.mods.laserReflection.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import agkz.mods.laserReflection.LaserReflection;

public class ReflectorBlocks {
	private List<IdMeta> reflectors;
	
	public ReflectorBlocks() {
		reflectors = new ArrayList<IdMeta>();
	}
	public IdMeta[] getReflectorIDList() {
		IdMeta[] reflectorIDs = new IdMeta[reflectors.size()];
		for (int i = 0; i < reflectors.size(); i++) {
			reflectorIDs[i] = reflectors.get(i);
		}
			
		return reflectorIDs;
	}
	public void setReflectorIDList(IdMeta[] list) {
		for (int i = 0; i < list.length; i++)
			addReflection(list[i]);
	}
	
	public void addReflection(IdMeta idMeta) {
		reflectors.add(idMeta);
		LaserReflection.logger.info("Added: " + idMeta.toString());
	}
	public boolean doesReflect(IdMeta idMeta) {
		for (int i = 0; i < reflectors.size(); i++) {
			IdMeta storedIdMeta = reflectors.get(i);
			if (IdMeta.equivalent(idMeta, storedIdMeta)) {
				LaserReflection.logger.info("Reflects!");
				return true;
			}
		}
		return false;
	}
}
