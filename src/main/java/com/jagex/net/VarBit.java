package com.jagex.net;

import java.util.logging.Logger;

// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public final class VarBit {

    private static Logger logger = Logger.getLogger("RS2Logger");
	public static void unpackConfig(StreamLoader streamLoader)
	{
		JagBuffer jagBuffer = new JagBuffer(streamLoader.getDataForName("varbit.dat"));
		int cacheSize = jagBuffer.readUnsignedShort();
		if(cache == null)
			cache = new VarBit[cacheSize];
		for(int j = 0; j < cacheSize; j++)
		{
			if(cache[j] == null)
				cache[j] = new VarBit();
			cache[j].readValues(jagBuffer);
			if(cache[j].aBoolean651)
				Varp.cache[cache[j].anInt648].aBoolean713 = true;
		}

		if(jagBuffer.currentOffset != jagBuffer.buffer.length)
            logger.warning("varbit load mismatch");
	}

	private void readValues(JagBuffer jagBuffer)
	{
		do
		{
			int j = jagBuffer.readUnsignedByte();
			if(j == 0)
				return;
			if(j == 1)
			{
				anInt648 = jagBuffer.readUnsignedShort();
				anInt649 = jagBuffer.readUnsignedByte();
				anInt650 = jagBuffer.readUnsignedByte();
			} else
			if(j == 10)
				jagBuffer.readString();
			else
			if(j == 2)
				aBoolean651 = true;
			else
			if(j == 3)
				jagBuffer.readDWord();
			else
			if(j == 4)
				jagBuffer.readDWord();
			else
                logger.warning("Error unrecognised config code: " + j);
		} while(true);
	}

	private VarBit()
	{
		aBoolean651 = false;
	}

	public static VarBit cache[];
	public int anInt648;
	public int anInt649;
	public int anInt650;
	private boolean aBoolean651;
}
