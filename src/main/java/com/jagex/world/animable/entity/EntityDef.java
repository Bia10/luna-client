package com.jagex.world.animable.entity;

import com.jagex.Client;
import com.jagex.net.JagBuffer;
import com.jagex.net.StreamLoader;
import com.jagex.net.VarBit;
import com.jagex.unrefactored.Class36;
import com.jagex.world.MRUNodes;
import com.jagex.world.animable.Model;

// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public final class EntityDef {

    public static EntityDef forID(int i) {
        for (int j = 0; j < 20; j++)
            if (cache[j].type == i)
                return cache[j];

        anInt56 = (anInt56 + 1) % 20;
        EntityDef entityDef = cache[anInt56] = new EntityDef();
        jagBuffer.currentOffset = streamIndices[i];
        entityDef.type = i;
        entityDef.readValues(jagBuffer);

        switch (i) {
            case 509:
                entityDef.npcSizeX = 270;
                entityDef.npcSizeY = 270;
                entityDef.name = "Soul Sucker";
                entityDef.description = "Gains power from leeching off of the souls of the undead.".getBytes();
                entityDef.combatLevel = 329;
                break;
            case 997:
                entityDef.npcSizeX = 500;
                entityDef.npcSizeY = 400;
                entityDef.name = "Mutant Spider";
                entityDef.description = "Must've been flushed down the toilet!".getBytes();
                entityDef.combatLevel = 457;
                entityDef.npcColor = 250;
                break;
            case 977:
                entityDef.name = "Mutant Spider Baby";
                entityDef.description = "I wonder where the mother is?".getBytes();
                entityDef.combatLevel = 34;
                entityDef.npcColor = 250;
                break;
            case 536:
                entityDef.name = "Hot Dancer";
                entityDef.description = "Probably didn't have her father in her life.".getBytes();
                break;
            case 3222:
                entityDef.name = "Drunk Dancer";
                entityDef.description = "Probably beats on his wife.".getBytes();
                break;
            case 111:
                entityDef.combatLevel = 115;
                break;

        }
        return entityDef;
    }

    public Model method160() {
        if (childrenIDs != null) {
            EntityDef entityDef = method161();
            if (entityDef == null)
                return null;
            return entityDef.method160();
        }
        if (anIntArray73 == null)
            return null;
        boolean flag1 = false;
        for (int i = 0; i < anIntArray73.length; i++)
            if (!Model.method463(anIntArray73[i]))
                flag1 = true;

        if (flag1)
            return null;
        Model aclass30_sub2_sub4_sub6s[] = new Model[anIntArray73.length];
        for (int j = 0; j < anIntArray73.length; j++)
            aclass30_sub2_sub4_sub6s[j] = Model.method462(anIntArray73[j]);

        Model model;
        if (aclass30_sub2_sub4_sub6s.length == 1)
            model = aclass30_sub2_sub4_sub6s[0];
        else
            model = new Model(aclass30_sub2_sub4_sub6s.length, aclass30_sub2_sub4_sub6s);
        if (anIntArray76 != null) {
            for (int k = 0; k < anIntArray76.length; k++)
                model.method476(anIntArray76[k], anIntArray70[k]);

        }
        return model;
    }

    public EntityDef method161() {
        int j = -1;
        if (anInt57 != -1) {
            VarBit varBit = VarBit.cache[anInt57];
            int k = varBit.anInt648;
            int l = varBit.anInt649;
            int i1 = varBit.anInt650;
            int j1 = Client.anIntArray1232[i1 - l];
            j = clientInstance.variousSettings[k] >> l & j1;
        } else if (anInt59 != -1)
            j = clientInstance.variousSettings[anInt59];
        if (j < 0 || j >= childrenIDs.length || childrenIDs[j] == -1)
            return null;
        return forID(childrenIDs[j]);
    }

    public static void unpackConfig(StreamLoader streamLoader) {
        jagBuffer = new JagBuffer(streamLoader.getDataForName("npc.dat"));
        JagBuffer stream2 = new JagBuffer(streamLoader.getDataForName("npc.idx"));
        int totalNPCs = stream2.readUnsignedShort();
        streamIndices = new int[totalNPCs];
        int i = 2;
        for (int j = 0; j < totalNPCs; j++) {
            streamIndices[j] = i;
            i += stream2.readUnsignedShort();
        }

        cache = new EntityDef[20];
        for (int k = 0; k < 20; k++)
            cache[k] = new EntityDef();

    }

    public static void nullLoader() {
        mruNodes = null;
        streamIndices = null;
        cache = null;
        jagBuffer = null;
    }

    public Model method164(int j, int k, int ai[]) {
        if (childrenIDs != null) {
            EntityDef entityDef = method161();
            if (entityDef == null)
                return null;
            return entityDef.method164(j, k, ai);
        }
        Model model = (Model) mruNodes.insertFromCache(type);
        if (model == null) {
            boolean flag = false;
            for (int i1 = 0; i1 < anIntArray94.length; i1++)
                if (!Model.method463(anIntArray94[i1]))
                    flag = true;

            if (flag)
                return null;
            Model aclass30_sub2_sub4_sub6s[] = new Model[anIntArray94.length];
            for (int j1 = 0; j1 < anIntArray94.length; j1++)
                aclass30_sub2_sub4_sub6s[j1] = Model.method462(anIntArray94[j1]);

            if (aclass30_sub2_sub4_sub6s.length == 1)
                model = aclass30_sub2_sub4_sub6s[0];
            else
                model = new Model(aclass30_sub2_sub4_sub6s.length, aclass30_sub2_sub4_sub6s);
            if (anIntArray76 != null) {
                for (int k1 = 0; k1 < anIntArray76.length; k1++)
                    model.method476(anIntArray76[k1], anIntArray70[k1]);

            }
            model.method469();
            model.method479(64 + npcColor, 850 + anInt92, -30, -50, -30, true);
            mruNodes.removeFromCache(model, type);
        }
        Model model_1 = Model.aModel_1621;
        model_1.method464(model, Class36.method532(k) & Class36.method532(j));
        if (k != -1 && j != -1)
            model_1.method471(ai, j, k);
        else if (k != -1)
            model_1.method470(k);
        if (npcSizeX != 128 || npcSizeY != 128)
            model_1.method478(npcSizeX, npcSizeX, npcSizeY);
        model_1.method466();
        model_1.anIntArrayArray1658 = null;
        model_1.anIntArrayArray1657 = null;
        if (aByte68 == 1)
            model_1.aBoolean1659 = true;
        return model_1;
    }

    private void readValues(JagBuffer jagBuffer) {
        do {
            int i = jagBuffer.readUnsignedByte();
            if (i == 0)
                return;
            if (i == 1) {
                int j = jagBuffer.readUnsignedByte();
                anIntArray94 = new int[j];
                for (int j1 = 0; j1 < j; j1++)
                    anIntArray94[j1] = jagBuffer.readUnsignedShort();

            } else if (i == 2)
                name = jagBuffer.readString();
            else if (i == 3)
                description = jagBuffer.readBytes();
            else if (i == 12)
                aByte68 = jagBuffer.readSignedByte();
            else if (i == 13)
                standAnimation = jagBuffer.readUnsignedShort();
            else if (i == 14)
                walkAnimation = jagBuffer.readUnsignedShort();
            else if (i == 17) {
                walkAnimation = jagBuffer.readUnsignedShort();
                anInt58 = jagBuffer.readUnsignedShort();
                anInt83 = jagBuffer.readUnsignedShort();
                anInt55 = jagBuffer.readUnsignedShort();
            } else if (i >= 30 && i < 40) {
                if (actions == null)
                    actions = new String[5];
                actions[i - 30] = jagBuffer.readString();
                if (actions[i - 30].equalsIgnoreCase("hidden"))
                    actions[i - 30] = null;
            } else if (i == 40) {
                int k = jagBuffer.readUnsignedByte();
                anIntArray76 = new int[k];
                anIntArray70 = new int[k];
                for (int k1 = 0; k1 < k; k1++) {
                    anIntArray76[k1] = jagBuffer.readUnsignedShort();
                    anIntArray70[k1] = jagBuffer.readUnsignedShort();
                }

            } else if (i == 60) {
                int l = jagBuffer.readUnsignedByte();
                anIntArray73 = new int[l];
                for (int l1 = 0; l1 < l; l1++)
                    anIntArray73[l1] = jagBuffer.readUnsignedShort();

            } else if (i == 90)
                jagBuffer.readUnsignedShort();
            else if (i == 91)
                jagBuffer.readUnsignedShort();
            else if (i == 92)
                jagBuffer.readUnsignedShort();
            else if (i == 93)
                aBoolean87 = false;
            else if (i == 95)
                combatLevel = jagBuffer.readUnsignedShort();
            else if (i == 97)
                npcSizeX = jagBuffer.readUnsignedShort();
            else if (i == 98)
                npcSizeY = jagBuffer.readUnsignedShort();
            else if (i == 99)
                aBoolean93 = true;
            else if (i == 100)
                npcColor = jagBuffer.readSignedByte();
            else if (i == 101)
                anInt92 = jagBuffer.readSignedByte() * 5;
            else if (i == 102)
                anInt75 = jagBuffer.readUnsignedShort();
            else if (i == 103)
                anInt79 = jagBuffer.readUnsignedShort();
            else if (i == 106) {
                anInt57 = jagBuffer.readUnsignedShort();
                if (anInt57 == 65535)
                    anInt57 = -1;
                anInt59 = jagBuffer.readUnsignedShort();
                if (anInt59 == 65535)
                    anInt59 = -1;
                int i1 = jagBuffer.readUnsignedByte();
                childrenIDs = new int[i1 + 1];
                for (int i2 = 0; i2 <= i1; i2++) {
                    childrenIDs[i2] = jagBuffer.readUnsignedShort();
                    if (childrenIDs[i2] == 65535)
                        childrenIDs[i2] = -1;
                }

            } else if (i == 107)
                aBoolean84 = false;
        } while (true);
    }

    private EntityDef() {
        anInt55 = -1;
        anInt57 = -1;
        anInt58 = -1;
        anInt59 = -1;
        combatLevel = -1;
        walkAnimation = -1;
        aByte68 = 1;
        anInt75 = -1;
        standAnimation = -1;
        type = -1L;
        anInt79 = 32;
        anInt83 = -1;
        aBoolean84 = true;
        npcSizeY = 128;
        aBoolean87 = true;
        npcSizeX = 128;
        aBoolean93 = false;
    }

    public int anInt55;
    private static int anInt56;
    private int anInt57;
    public int anInt58;
    private int anInt59;
    private static JagBuffer jagBuffer;
    public int combatLevel;
    public String name;
    public String actions[];
    public int walkAnimation;
    public byte aByte68;
    private int[] anIntArray70;
    private static int[] streamIndices;
    private int[] anIntArray73;
    public int anInt75;
    private int[] anIntArray76;
    public int standAnimation;
    public long type;
    public int anInt79;
    private static EntityDef[] cache;
    public static Client clientInstance;
    public int anInt83;
    public boolean aBoolean84;
    private int npcColor;
    private int npcSizeY;
    public boolean aBoolean87;
    public int childrenIDs[];
    public byte description[];
    private int npcSizeX;
    private int anInt92;
    public boolean aBoolean93;
    private int[] anIntArray94;
    public static MRUNodes mruNodes = new MRUNodes(30);

}
