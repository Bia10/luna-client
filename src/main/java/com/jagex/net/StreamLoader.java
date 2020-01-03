package com.jagex.net;

import com.jagex.unrefactored.Class13;

public final class StreamLoader {

    public StreamLoader(byte abyte0[]) {
        JagBuffer jagBuffer = new JagBuffer(abyte0);
        int i = jagBuffer.readTriByte();
        int j = jagBuffer.readTriByte();
        if (j != i) {
            byte abyte1[] = new byte[i];
            Class13.method225(abyte1, i, abyte0, j, 6);
            aByteArray726 = abyte1;
            jagBuffer = new JagBuffer(aByteArray726);
            aBoolean732 = true;
        } else {
            aByteArray726 = abyte0;
            aBoolean732 = false;
        }
        dataSize = jagBuffer.readUnsignedShort();
        anIntArray728 = new int[dataSize];
        anIntArray729 = new int[dataSize];
        anIntArray730 = new int[dataSize];
        anIntArray731 = new int[dataSize];
        int k = jagBuffer.currentOffset + dataSize * 10;
        for (int l = 0; l < dataSize; l++) {
            anIntArray728[l] = jagBuffer.readDWord();
            anIntArray729[l] = jagBuffer.readTriByte();
            anIntArray730[l] = jagBuffer.readTriByte();
            anIntArray731[l] = k;
            k += anIntArray730[l];
        }
    }

    @SuppressWarnings("null")
    public byte[] getDataForName(String s) {
        byte abyte0[] = null; // was a parameter
        int i = 0;
        s = s.toUpperCase();
        for (int j = 0; j < s.length(); j++)
            i = (i * 61 + s.charAt(j)) - 32;

        for (int k = 0; k < dataSize; k++)
            if (anIntArray728[k] == i) {
                if (abyte0 == null)
                    abyte0 = new byte[anIntArray729[k]];
                if (!aBoolean732) {
                    Class13.method225(abyte0, anIntArray729[k], aByteArray726, anIntArray730[k], anIntArray731[k]);
                } else {
                    System.arraycopy(aByteArray726, anIntArray731[k], abyte0, 0, anIntArray729[k]);

                }
                return abyte0;
            }

        return null;
    }

    private final byte[] aByteArray726;
    private final int dataSize;
    private final int[] anIntArray728;
    private final int[] anIntArray729;
    private final int[] anIntArray730;
    private final int[] anIntArray731;
    private final boolean aBoolean732;
}
