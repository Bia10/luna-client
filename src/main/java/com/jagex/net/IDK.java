package com.jagex.net;

import com.jagex.world.animable.Model;

import java.util.logging.Logger;

public final class IDK {

	public static void unpackConfig(StreamLoader streamLoader)
	{
		JagBuffer jagBuffer = new JagBuffer(streamLoader.getDataForName("idk.dat"));
		length = jagBuffer.readUnsignedShort();
		if(cache == null)
			cache = new IDK[length];
		for(int j = 0; j < length; j++)
		{
			if(cache[j] == null)
				cache[j] = new IDK();
			cache[j].readValues(jagBuffer);
		}
	}

    private static Logger logger = Logger.getLogger("RS2Logger");
	private void readValues(JagBuffer jagBuffer)
	{
		do
		{
			int i = jagBuffer.readUnsignedByte();
			if(i == 0)
				return;
			if(i == 1)
				anInt657 = jagBuffer.readUnsignedByte();
			else
			if(i == 2)
			{
				int j = jagBuffer.readUnsignedByte();
				anIntArray658 = new int[j];
				for(int k = 0; k < j; k++)
					anIntArray658[k] = jagBuffer.readUnsignedShort();

			} else
			if(i == 3)
				aBoolean662 = true;
			else
			if(i >= 40 && i < 50)
				anIntArray659[i - 40] = jagBuffer.readUnsignedShort();
			else
			if(i >= 50 && i < 60)
				anIntArray660[i - 50] = jagBuffer.readUnsignedShort();
			else
			if(i >= 60 && i < 70)
				anIntArray661[i - 60] = jagBuffer.readUnsignedShort();
			else
                logger.warning("Error unrecognised config code: " + i);
		} while(true);
	}

	public boolean method537()
	{
		if(anIntArray658 == null)
			return true;
		boolean flag = true;
		for(int j = 0; j < anIntArray658.length; j++)
			if(!Model.method463(anIntArray658[j]))
				flag = false;

		return flag;
	}

	public Model method538()
	{
		if(anIntArray658 == null)
			return null;
		Model aclass30_sub2_sub4_sub6s[] = new Model[anIntArray658.length];
		for(int i = 0; i < anIntArray658.length; i++)
			aclass30_sub2_sub4_sub6s[i] = Model.method462(anIntArray658[i]);

		Model model;
		if(aclass30_sub2_sub4_sub6s.length == 1)
			model = aclass30_sub2_sub4_sub6s[0];
		else
			model = new Model(aclass30_sub2_sub4_sub6s.length, aclass30_sub2_sub4_sub6s);
		for(int j = 0; j < 6; j++)
		{
			if(anIntArray659[j] == 0)
				break;
			model.method476(anIntArray659[j], anIntArray660[j]);
		}

		return model;
	}

	public boolean method539()
	{
		boolean flag1 = true;
		for(int i = 0; i < 5; i++)
			if(anIntArray661[i] != -1 && !Model.method463(anIntArray661[i]))
				flag1 = false;

		return flag1;
	}

	public Model method540()
	{
		Model aclass30_sub2_sub4_sub6s[] = new Model[5];
		int j = 0;
		for(int k = 0; k < 5; k++)
			if(anIntArray661[k] != -1)
				aclass30_sub2_sub4_sub6s[j++] = Model.method462(anIntArray661[k]);

		Model model = new Model(j, aclass30_sub2_sub4_sub6s);
		for(int l = 0; l < 6; l++)
		{
			if(anIntArray659[l] == 0)
				break;
			model.method476(anIntArray659[l], anIntArray660[l]);
		}

		return model;
	}

	private IDK()
	{
		anInt657 = -1;
		anIntArray659 = new int[6];
		anIntArray660 = new int[6];
		aBoolean662 = false;
	}

	public static int length;
	public static IDK cache[];
	public int anInt657;
	private int[] anIntArray658;
	private final int[] anIntArray659;
	private final int[] anIntArray660;
	private final int[] anIntArray661 = {
		-1, -1, -1, -1, -1
	};
	public boolean aBoolean662;
}
