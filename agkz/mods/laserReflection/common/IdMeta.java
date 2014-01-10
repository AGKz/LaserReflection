package agkz.mods.laserReflection.common;

import agkz.mods.laserReflection.LaserReflection;

public class IdMeta {
	
	private int BlockID, Meta;
	
	public IdMeta(int block, int meta) {
		this.BlockID = block;
		this.Meta = meta;
	}
	
	public IdMeta(String idMeta) {
		this.BlockID = 0;
		this.Meta = -1;
		
		try {
			if (idMeta.contains(":")) {
				int index = idMeta.indexOf(':');
				this.Meta = Integer.parseInt(idMeta.substring(index+1));
				this.BlockID = Integer.parseInt(idMeta.substring(0, index));
			} else {
				this.BlockID = Integer.parseInt(idMeta);
			}
		} catch (NumberFormatException e) {
			LaserReflection.logger.warning("Reflector ID parser errored when parsing: " + idMeta + " (Needs to be an integer)");
			e.printStackTrace();
		}
	
	}
	
	@Override
	public String toString() {
		String idMeta;
		if (this.Meta == -1) {
			idMeta = Integer.toString(BlockID);
		} else {
			idMeta = Integer.toString(BlockID).concat(":").concat(Integer.toString(Meta));
		}
		return idMeta;
	}
	
	
	
	public int getBlockID() {
		return this.BlockID;
	}
	public int getBlockMeta() {
		return this.Meta;
	}
	
	public static String[] toStringArray(IdMeta[] idMeta) {
		String[] strings = new String[idMeta.length];
		for (int i = 0; i < idMeta.length; i++) {
			strings[i] = idMeta[i].toString();
		}
		return strings;
	}
	
	public static IdMeta[] fromStringArray(String[] idMetaString) {
		IdMeta[] idMetas = new IdMeta[idMetaString.length];
		for (int i = 0; i < idMetaString.length; i++) {
			idMetas[i] = new IdMeta(idMetaString[i]);
		}
		return idMetas;
	}
	public static boolean equivalent(IdMeta idMeta, IdMeta stored) {
		if (idMeta.getBlockID() == stored.BlockID) {
			if (stored.getBlockMeta() == -1 || idMeta.getBlockMeta() == stored.getBlockMeta()){
				return true;
			}
		}
		return false;
	}
}
