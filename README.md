LaserReflection
===============

Adds block reflection to IC2 Mining Lasers - Child mod of Ic2, Lazerz

This is a very small child mod for IC2 that simply allows reflection of laser beams off of block using both configurable config as well as IMC for other mods. 


Other Modders
===============

To add one of your blocks to the reflection list, simply the following lines to your load method.

if (Loader.isModLoaded("LaserReflection")) 
			FMLInterModComms.sendMessage("LaserReflection", "registerReflector", Integer.toString(MyReflectionBlock.blockID));
			
Changing MyReflectionBlock.blockID to your block's ID.

Meta blocks => Simply add a ':' between the id and the meta value.
  Ex: FMLInterModComms.sendMessage("LaserReflection", "registerReflector", MetaBlockSomething.blockID + ":" + 3);
  With 3 being the meta value
  
  Leaving the meta ':4' section blank (Like first example) will automaticly add a -1, being interpreted as a wildcard and allowing all meta values for that block.
  
  
If you have any questions or comments feel free to contact me here or on the IC2 Forum.
