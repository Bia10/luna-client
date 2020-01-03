package com.jagex.unrefactored;

import com.jagex.net.JagBuffer;

public final class Class18
{

	public Class18(JagBuffer jagBuffer)
	{
		int anInt341 = jagBuffer.readUnsignedByte();
		anIntArray342 = new int[anInt341];
		anIntArrayArray343 = new int[anInt341][];
		for(int j = 0; j < anInt341; j++)
			anIntArray342[j] = jagBuffer.readUnsignedByte();

		for(int k = 0; k < anInt341; k++)
		{
			int l = jagBuffer.readUnsignedByte();
			anIntArrayArray343[k] = new int[l];
			for(int i1 = 0; i1 < l; i1++)
				anIntArrayArray343[k][i1] = jagBuffer.readUnsignedByte();

		}

	}

	public final int[] anIntArray342;
	public final int[][] anIntArrayArray343;
}
