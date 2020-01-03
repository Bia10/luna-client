package com.jagex.drawing;

import com.jagex.Client;
import com.jagex.net.JagBuffer;
import com.jagex.net.StreamLoader;
import com.jagex.unrefactored.Class36;
import com.jagex.util.TextClass;
import com.jagex.world.MRUNodes;
import com.jagex.world.animable.Model;
import com.jagex.world.animable.entity.EntityDef;
import com.jagex.world.animable.item.ItemDef;
import io.luna.Constants;

// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public final class RSInterface {

    public void swapInventoryItems(int i, int j) {
        int k = inv[i];
        inv[i] = inv[j];
        inv[j] = k;
        k = invStackSizes[i];
        invStackSizes[i] = invStackSizes[j];
        invStackSizes[j] = k;
    }

    public static void unpack(StreamLoader streamLoader, TextDrawingArea textDrawingAreas[], StreamLoader streamLoader_1) {
        aMRUNodes_238 = new MRUNodes(50000);
        JagBuffer jagBuffer = new JagBuffer(streamLoader.getDataForName("data"));
        int i = -1;
        int j = jagBuffer.readUnsignedShort();
        interfaceCache = new RSInterface[j + 10000];
        while (jagBuffer.currentOffset < jagBuffer.buffer.length) {
            int k = jagBuffer.readUnsignedShort();
            if (k == 65535) {
                i = jagBuffer.readUnsignedShort();
                k = jagBuffer.readUnsignedShort();
            }
            RSInterface rsInterface = interfaceCache[k] = new RSInterface();
            rsInterface.id = k;
            rsInterface.parentID = i;
            rsInterface.type = jagBuffer.readUnsignedByte();
            rsInterface.atActionType = jagBuffer.readUnsignedByte();
            rsInterface.contentType = jagBuffer.readUnsignedShort();
            rsInterface.width = jagBuffer.readUnsignedShort();
            rsInterface.height = jagBuffer.readUnsignedShort();
            rsInterface.aByte254 = (byte) jagBuffer.readUnsignedByte();
            rsInterface.mOverInterToTrigger = jagBuffer.readUnsignedByte();
            if (rsInterface.mOverInterToTrigger != 0)
                rsInterface.mOverInterToTrigger = (rsInterface.mOverInterToTrigger - 1 << 8) + jagBuffer.readUnsignedByte();
            else
                rsInterface.mOverInterToTrigger = -1;
            int i1 = jagBuffer.readUnsignedByte();
            if (i1 > 0) {
                rsInterface.anIntArray245 = new int[i1];
                rsInterface.anIntArray212 = new int[i1];
                for (int j1 = 0; j1 < i1; j1++) {
                    rsInterface.anIntArray245[j1] = jagBuffer.readUnsignedByte();
                    rsInterface.anIntArray212[j1] = jagBuffer.readUnsignedShort();
                }

            }
            int k1 = jagBuffer.readUnsignedByte();
            if (k1 > 0) {
                rsInterface.valueIndexArray = new int[k1][];
                for (int l1 = 0; l1 < k1; l1++) {
                    int i3 = jagBuffer.readUnsignedShort();
                    rsInterface.valueIndexArray[l1] = new int[i3];
                    for (int l4 = 0; l4 < i3; l4++)
                        rsInterface.valueIndexArray[l1][l4] = jagBuffer.readUnsignedShort();

                }

            }
            if (rsInterface.type == 0) {
                rsInterface.drawsTransparent = false;
                rsInterface.scrollMax = jagBuffer.readUnsignedShort();
                rsInterface.isMouseoverTriggered = jagBuffer.readUnsignedByte() == 1;
                int i2 = jagBuffer.readUnsignedShort();
                rsInterface.children = new int[i2];
                rsInterface.childX = new int[i2];
                rsInterface.childY = new int[i2];
                for (int j3 = 0; j3 < i2; j3++) {
                    rsInterface.children[j3] = jagBuffer.readUnsignedShort();
                    rsInterface.childX[j3] = jagBuffer.readSignedShort();
                    rsInterface.childY[j3] = jagBuffer.readSignedShort();
                }
            }
            if (rsInterface.type == 1) {
                jagBuffer.readUnsignedShort();
                jagBuffer.readUnsignedByte();
            }
            if (rsInterface.type == 2) {
                rsInterface.inv = new int[rsInterface.width * rsInterface.height];
                rsInterface.invStackSizes = new int[rsInterface.width * rsInterface.height];
                rsInterface.aBoolean259 = jagBuffer.readUnsignedByte() == 1;
                rsInterface.isInventoryInterface = jagBuffer.readUnsignedByte() == 1;
                rsInterface.usableItemInterface = jagBuffer.readUnsignedByte() == 1;
                rsInterface.aBoolean235 = jagBuffer.readUnsignedByte() == 1;
                rsInterface.invSpritePadX = jagBuffer.readUnsignedByte();
                rsInterface.invSpritePadY = jagBuffer.readUnsignedByte();
                rsInterface.spritesX = new int[20];
                rsInterface.spritesY = new int[20];
                rsInterface.sprites = new Sprite[20];
                for (int j2 = 0; j2 < 20; j2++) {
                    int k3 = jagBuffer.readUnsignedByte();
                    if (k3 == 1) {
                        rsInterface.spritesX[j2] = jagBuffer.readSignedShort();
                        rsInterface.spritesY[j2] = jagBuffer.readSignedShort();
                        String s1 = jagBuffer.readString();
                        if (streamLoader_1 != null && s1.length() > 0) {
                            int i5 = s1.lastIndexOf(",");
                            rsInterface.sprites[j2] = method207(Integer.parseInt(s1.substring(i5 + 1)), streamLoader_1,
                                s1.substring(0, i5));
                        }
                    }
                }

                rsInterface.actions = new String[5];
                for (int l3 = 0; l3 < 5; l3++) {
                    rsInterface.actions[l3] = jagBuffer.readString();
                    if (rsInterface.actions[l3].length() == 0)
                        rsInterface.actions[l3] = null;
                }

            }
            if (rsInterface.type == 3)
                rsInterface.aBoolean227 = jagBuffer.readUnsignedByte() == 1;
            if (rsInterface.type == 4 || rsInterface.type == 1) {
                rsInterface.centerText = jagBuffer.readUnsignedByte() == 1;
                int k2 = jagBuffer.readUnsignedByte();
                if (textDrawingAreas != null)
                    rsInterface.textDrawingAreas = textDrawingAreas[k2];
                rsInterface.textShadow = jagBuffer.readUnsignedByte() == 1;
            }
            if (rsInterface.type == 4) {
                rsInterface.message = jagBuffer.readString();
                rsInterface.aString228 = jagBuffer.readString();
            }
            if (rsInterface.type == 1 || rsInterface.type == 3 || rsInterface.type == 4)
                rsInterface.textColor = jagBuffer.readDWord();
            if (rsInterface.type == 3 || rsInterface.type == 4) {
                rsInterface.anInt219 = jagBuffer.readDWord();
                rsInterface.anInt216 = jagBuffer.readDWord();
                rsInterface.anInt239 = jagBuffer.readDWord();
            }
            if (rsInterface.type == 5) {
                rsInterface.drawsTransparent = false;
                String s = jagBuffer.readString();
                if (streamLoader_1 != null && s.length() > 0) {
                    int i4 = s.lastIndexOf(",");
                    rsInterface.sprite1 = method207(Integer.parseInt(s.substring(i4 + 1)), streamLoader_1,
                        s.substring(0, i4));
                }
                s = jagBuffer.readString();
                if (streamLoader_1 != null && s.length() > 0) {
                    int j4 = s.lastIndexOf(",");
                    rsInterface.sprite2 = method207(Integer.parseInt(s.substring(j4 + 1)), streamLoader_1,
                        s.substring(0, j4));
                }
            }
            if (rsInterface.type == 6) {
                int l = jagBuffer.readUnsignedByte();
                if (l != 0) {
                    rsInterface.anInt233 = 1;
                    rsInterface.mediaID = (l - 1 << 8) + jagBuffer.readUnsignedByte();
                }
                l = jagBuffer.readUnsignedByte();
                if (l != 0) {
                    rsInterface.anInt255 = 1;
                    rsInterface.anInt256 = (l - 1 << 8) + jagBuffer.readUnsignedByte();
                }
                l = jagBuffer.readUnsignedByte();
                if (l != 0)
                    rsInterface.anInt257 = (l - 1 << 8) + jagBuffer.readUnsignedByte();
                else
                    rsInterface.anInt257 = -1;
                l = jagBuffer.readUnsignedByte();
                if (l != 0)
                    rsInterface.anInt258 = (l - 1 << 8) + jagBuffer.readUnsignedByte();
                else
                    rsInterface.anInt258 = -1;
                rsInterface.anInt269 = jagBuffer.readUnsignedShort();
                rsInterface.modelRotation1 = jagBuffer.readUnsignedShort();
                rsInterface.modelRotation2 = jagBuffer.readUnsignedShort();
            }
            if (rsInterface.type == 7) {
                rsInterface.inv = new int[rsInterface.width * rsInterface.height];
                rsInterface.invStackSizes = new int[rsInterface.width * rsInterface.height];
                rsInterface.centerText = jagBuffer.readUnsignedByte() == 1;
                int l2 = jagBuffer.readUnsignedByte();
                if (textDrawingAreas != null)
                    rsInterface.textDrawingAreas = textDrawingAreas[l2];
                rsInterface.textShadow = jagBuffer.readUnsignedByte() == 1;
                rsInterface.textColor = jagBuffer.readDWord();
                rsInterface.invSpritePadX = jagBuffer.readSignedShort();
                rsInterface.invSpritePadY = jagBuffer.readSignedShort();
                rsInterface.isInventoryInterface = jagBuffer.readUnsignedByte() == 1;
                rsInterface.actions = new String[5];
                for (int k4 = 0; k4 < 5; k4++) {
                    rsInterface.actions[k4] = jagBuffer.readString();
                    if (rsInterface.actions[k4].length() == 0)
                        rsInterface.actions[k4] = null;
                }

            }
            if (rsInterface.atActionType == 2 || rsInterface.type == 2) {
                rsInterface.selectedActionName = jagBuffer.readString();
                rsInterface.spellName = jagBuffer.readString();
                rsInterface.spellUsableOn = jagBuffer.readUnsignedShort();
            }

            if (rsInterface.type == 8)
                rsInterface.message = jagBuffer.readString();

            if (rsInterface.atActionType == 1 || rsInterface.atActionType == 4 || rsInterface.atActionType == 5 || rsInterface.atActionType == 6) {
                rsInterface.tooltip = jagBuffer.readString();
                if (rsInterface.tooltip.length() == 0) {
                    if (rsInterface.atActionType == 1)
                        rsInterface.tooltip =
                            Constants.DEBUG ? "Ok [id=" + rsInterface.id + ", color=" + rsInterface.textColor + "]" : "Ok";
                    if (rsInterface.atActionType == 4)
                        rsInterface.tooltip =
                            Constants.DEBUG ? "Select [id=" + rsInterface.id + ", color=" + rsInterface.textColor + "]" :
                                "Select";
                    if (rsInterface.atActionType == 5)
                        rsInterface.tooltip =
                            Constants.DEBUG ? "Select, [id=" + rsInterface.id + ", color=" + rsInterface.textColor + "]" :
                                "Select";
                    if (rsInterface.atActionType == 6)
                        rsInterface.tooltip =
                            Constants.DEBUG ? "Continue, [id=" + rsInterface.id + ", color=" + rsInterface.textColor + "]" :
                                "Continue";
                } else if (Constants.DEBUG) {
                    rsInterface.tooltip = rsInterface.tooltip + " " +
                        "[id=" + rsInterface.id + ", color=" + rsInterface.textColor + "]";
                }
            }
        }
        aClass44 = streamLoader;
        aMRUNodes_238 = null;
    }

    public static void addText(int id, String text, TextDrawingArea tda[], int idx, int color, boolean center,
        boolean shadow) {
        RSInterface tab = addTabInterface(id);
        tab.parentID = id;
        tab.id = id;
        tab.type = 4;
        tab.atActionType = 0;
        tab.width = 0;
        tab.height = 11;
        tab.contentType = 0;
        tab.aByte254 = 0;
        tab.mOverInterToTrigger = -1;
        tab.centerText = center;
        tab.textShadow = shadow;
        tab.textDrawingAreas = tda[idx];
        tab.message = text;
        tab.aString228 = "";
        tab.textColor = color;
        tab.anInt219 = 0;
        tab.anInt216 = 0;
        tab.anInt239 = 0;
    }

    public static void addButton(int id, int sid, String spriteName, String tooltip) {
        RSInterface tab = interfaceCache[id] = new RSInterface();
        tab.id = id;
        tab.parentID = id;
        tab.type = 5;
        tab.atActionType = 1;
        tab.contentType = 0;
        tab.aByte254 = (byte) 0;
        tab.mOverInterToTrigger = 52;
        tab.sprite1 = imageLoader(sid, spriteName);
        tab.sprite2 = imageLoader(sid, spriteName);
        tab.width = tab.sprite1.myWidth;
        tab.height = tab.sprite1.myHeight;
        tab.tooltip = tooltip;
    }

    public static void addSprite(int id, int spriteId, String spriteName) {
        RSInterface tab = interfaceCache[id] = new RSInterface();
        tab.id = id;
        tab.parentID = id;
        tab.type = 5;
        tab.atActionType = 0;
        tab.contentType = 0;
        tab.aByte254 = (byte) 0;
        tab.mOverInterToTrigger = 52;
        tab.sprite1 = imageLoader(spriteId, spriteName);
        tab.sprite2 = imageLoader(spriteId, spriteName);
        tab.width = 512;
        tab.height = 334;
    }

    public static void addTransparentSprite(int id, int spriteId, String spriteName) {
        RSInterface tab = interfaceCache[id] = new RSInterface();
        tab.id = id;
        tab.parentID = id;
        tab.type = 5;
        tab.atActionType = 0;
        tab.contentType = 0;
        tab.aByte254 = (byte) 0;
        tab.mOverInterToTrigger = 52;
        tab.sprite1 = imageLoader(spriteId, spriteName);
        tab.sprite2 = imageLoader(spriteId, spriteName);
        tab.width = 512;
        tab.height = 334;
        tab.drawsTransparent = true;
    }

    public static RSInterface addScreenInterface(int id) {
        RSInterface tab = interfaceCache[id] = new RSInterface();
        tab.id = id;
        tab.parentID = id;
        tab.type = 0;
        tab.atActionType = 0;
        tab.contentType = 0;
        tab.width = 512;
        tab.height = 334;
        tab.aByte254 = (byte) 0;
        tab.mOverInterToTrigger = 0;
        return tab;
    }

    public static RSInterface addTabInterface(int id) {
        RSInterface tab = interfaceCache[id] = new RSInterface();
        tab.id = id;// 250
        tab.parentID = id;// 236
        tab.type = 0;// 262
        tab.atActionType = 0;// 217
        tab.contentType = 0;
        tab.width = 512;// 220
        tab.height = 700;// 267
        tab.aByte254 = (byte) 0;
        tab.mOverInterToTrigger = -1;// Int 230
        return tab;
    }

    private static Sprite imageLoader(int i, String s) {
        long l = (TextClass.method585(s) << 8) + i;
        Sprite sprite = (Sprite) aMRUNodes_238.insertFromCache(l);
        if (sprite != null)
            return sprite;
        try {
            sprite = new Sprite(s + " " + i);
            aMRUNodes_238.removeFromCache(sprite, l);
        } catch (Exception exception) {
            return null;
        }
        return sprite;
    }

    public void child(int id, int interID, int x, int y) {
        children[id] = interID;
        childX[id] = x;
        childY[id] = y;
    }

    public void totalChildren(int t) {
        children = new int[t];
        childX = new int[t];
        childY = new int[t];
    }

    private Model method206(int i, int j) {
        Model model = (Model) aMRUNodes_264.insertFromCache((i << 16) + j);
        if (model != null)
            return model;
        if (i == 1)
            model = Model.method462(j);
        if (i == 2)
            model = EntityDef.forID(j).method160();
        if (i == 3)
            model = Client.myPlayer.method453();
        if (i == 4)
            model = ItemDef.forID(j).method202(50);
        if (i == 5)
            model = null;
        if (model != null)
            aMRUNodes_264.removeFromCache(model, (i << 16) + j);
        return model;
    }

    private static Sprite method207(int i, StreamLoader streamLoader, String s) {
        long l = (TextClass.method585(s) << 8) + i;
        Sprite sprite = (Sprite) aMRUNodes_238.insertFromCache(l);
        if (sprite != null)
            return sprite;
        try {
            sprite = new Sprite(streamLoader, s, i);
            aMRUNodes_238.removeFromCache(sprite, l);
        } catch (Exception _ex) {
            return null;
        }
        return sprite;
    }

    public static void method208(boolean flag, Model model) {
        int i = 0;// was parameter
        int j = 5;// was parameter
        if (flag)
            return;
        aMRUNodes_264.unlinkAll();
        if (model != null && j != 4)
            aMRUNodes_264.removeFromCache(model, (j << 16) + i);
    }

    public Model method209(int j, int k, boolean flag) {
        Model model;
        if (flag)
            model = method206(anInt255, anInt256);
        else
            model = method206(anInt233, mediaID);
        if (model == null)
            return null;
        if (k == -1 && j == -1 && model.anIntArray1640 == null)
            return model;
        Model model_1 = new Model(true, Class36.method532(k) & Class36.method532(j), false, model);
        if (k != -1 || j != -1)
            model_1.method469();
        if (k != -1)
            model_1.method470(k);
        if (j != -1)
            model_1.method470(j);
        model_1.method479(64, 768, -50, -10, -50, true);
        return model_1;
    }

    public RSInterface() {
    }

    public static StreamLoader aClass44;
    public boolean drawsTransparent;
    public Sprite sprite1;
    public int anInt208;
    public Sprite sprites[];
    public static RSInterface interfaceCache[];
    public int anIntArray212[];
    public int contentType;// anInt214
    public int spritesX[];
    public int anInt216;
    public int atActionType;
    public String spellName;
    public int anInt219;
    public int width;
    public String tooltip;
    public String selectedActionName;
    public boolean centerText;
    public int scrollPosition;
    public String actions[];
    public int valueIndexArray[][];
    public boolean aBoolean227;
    public String aString228;
    public int mOverInterToTrigger;
    public int invSpritePadX;
    public int textColor;
    public int anInt233;
    public int mediaID;
    public boolean aBoolean235;
    public int parentID;
    public int spellUsableOn;
    private static MRUNodes aMRUNodes_238;
    public int anInt239;
    public int children[];
    public int childX[];
    public boolean usableItemInterface;
    public TextDrawingArea textDrawingAreas;
    public int invSpritePadY;
    public int anIntArray245[];
    public int anInt246;
    public int spritesY[];
    public String message;
    public boolean isInventoryInterface;
    public int id;
    public int invStackSizes[];
    public int inv[];
    public byte aByte254;
    private int anInt255;
    private int anInt256;
    public int anInt257;
    public int anInt258;
    public boolean aBoolean259;
    public Sprite sprite2;
    public int scrollMax;
    public int type;
    public int anInt263;
    private static final MRUNodes aMRUNodes_264 = new MRUNodes(30);
    public int anInt265;
    public boolean isMouseoverTriggered;
    public int height;
    public boolean textShadow;
    public int anInt269;
    public int modelRotation1;
    public int modelRotation2;
    public int childY[];

}
