// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import java.applet.AppletContext;
import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.zip.CRC32;

import sign.signlink;

public final class client extends RSApplet {

    private static String intToKOrMilLongName(int i)
    {
        String s = String.valueOf(i);
        for(int k = s.length() - 3; k > 0; k -= 3)
            s = s.substring(0, k) + "," + s.substring(k);
        if(s.length() > 8)
            s = "@gre@" + s.substring(0, s.length() - 8) + " million @whi@(" + s + ")";
        else
        if(s.length() > 4)
            s = "@cya@" + s.substring(0, s.length() - 4) + "K @whi@(" + s + ")";
        return " " + s;
    }

    private void stopMidi()
    {
        signlink.midifade = 0;
        signlink.midi = "stop";
    }

    private void connectServer()
    {
/*      int j = 5;
        expectedCRCs[8] = 0;
        int k = 0;
        while(expectedCRCs[8] == 0)
        {
            String s = "Unknown problem";
            drawLoadingText(20, (byte)4, "Connecting to web server");
            try
            {
                DataInputStream datainputstream = openJagGrabInputStream("crc" + (int)(Math.random() * 99999999D) + "-" + 317);
                Stream class30_sub2_sub2 = new Stream(new byte[40], 891);
                datainputstream.readFully(class30_sub2_sub2.buffer, 0, 40);
                datainputstream.close();
                for(int i1 = 0; i1 < 9; i1++)
                    expectedCRCs[i1] = class30_sub2_sub2.readDWord();

                int j1 = class30_sub2_sub2.readDWord();
                int k1 = 1234;
                for(int l1 = 0; l1 < 9; l1++)
                    k1 = (k1 << 1) + expectedCRCs[l1];

                if(j1 != k1)
                {
                    s = "checksum problem";
                    expectedCRCs[8] = 0;
                }
            }
            catch(EOFException _ex)
            {
                s = "EOF problem";
                expectedCRCs[8] = 0;
            }
            catch(IOException _ex)
            {
                s = "connection problem";
                expectedCRCs[8] = 0;
            }
            catch(Exception _ex)
            {
                s = "logic problem";
                expectedCRCs[8] = 0;
                if(!signlink.reporterror)
                    return;
            }
            if(expectedCRCs[8] == 0)
            {
                k++;
                for(int l = j; l > 0; l--)
                {
                    if(k >= 10)
                    {
                        drawLoadingText(10, (byte)4, "Game updated - please reload page");
                        l = 10;
                    } else
                    {
                        drawLoadingText(10, (byte)4, s + " - Will retry in " + l + " secs.");
                    }
                    try
                    {
                        Thread.sleep(1000L);
                    }
                    catch(Exception _ex) { }
                }

                j *= 2;
                if(j > 60)
                    j = 60;
                aBoolean872 = !aBoolean872;
            }
        }
 */
    }
    
    private boolean menuHasAddFriend(int j)
    {
        if(j < 0)
            return false;
        int k = menuActionID[j];
        if(k >= 2000)
            k -= 2000;
        return k == 337;
    }

    private void drawChatArea()
    {
        aRSImageProducer_1166.initDrawingArea();
        Texture.lineOffsets = anIntArray1180;
        chatBack.method361(0, 0);
        if(messagePromptRaised)
        {
            chatTextDrawingArea.drawTextHMidVTop(aString1121, 239, 40, 0);
            chatTextDrawingArea.drawTextHMidVTop(promptInput + "*", 239, 60, 128);
        } else
        if(inputDialogState == 1)
        {
            chatTextDrawingArea.drawTextHMidVTop("Enter amount:", 239, 40, 0);
            chatTextDrawingArea.drawTextHMidVTop(amountOrNameInput + "*", 239, 60, 128);
        } else
        if(inputDialogState == 2)
        {
            chatTextDrawingArea.drawTextHMidVTop("Enter name:", 239, 40, 0);
            chatTextDrawingArea.drawTextHMidVTop(amountOrNameInput + "*", 239, 60, 128);
        } else
        if(aString844 != null)
        {
            chatTextDrawingArea.drawTextHMidVTop(aString844, 239, 40, 0);
            chatTextDrawingArea.drawTextHMidVTop("Click to continue", 239, 60, 128);
        } else
        if(backDialogID != -1)
            drawInterface(0, 0, RSInterface.interfaceCache[backDialogID], 0);
        else
        if(dialogID != -1)
        {
            drawInterface(0, 0, RSInterface.interfaceCache[dialogID], 0);
        } else
        {
            TextDrawingArea textDrawingArea = plainFont;
            int j = 0;
            DrawingArea.setDrawingArea(77, 0, 463, 0);
            for(int k = 0; k < 100; k++)
                if(chatMessages[k] != null)
                {
                    int l = chatTypes[k];
                    int i1 = (70 - j * 14) + anInt1089;
                    String s1 = chatNames[k];
                    byte byte0 = 0;
                    if(s1 != null && s1.startsWith("@cr1@"))
                    {
                        s1 = s1.substring(5);
                        byte0 = 1;
                    }
                    if(s1 != null && s1.startsWith("@cr2@"))
                    {
                        s1 = s1.substring(5);
                        byte0 = 2;
                    }
                    if(l == 0)
                    {
                        if(i1 > 0 && i1 < 110)
                            textDrawingArea.drawTextHLeftVTop(chatMessages[k], 4, i1, 0);
                        j++;
                    }
                    if((l == 1 || l == 2) && (l == 1 || publicChatMode == 0 || publicChatMode == 1 && isFriendOrSelf(s1)))
                    {
                        if(i1 > 0 && i1 < 110)
                        {
                            int j1 = 4;
                            if(byte0 == 1)
                            {
                                modIcons[0].method361(j1, i1 - 12);
                                j1 += 14;
                            }
                            if(byte0 == 2)
                            {
                                modIcons[1].method361(j1, i1 - 12);
                                j1 += 14;
                            }
                            textDrawingArea.drawTextHLeftVTop(s1 + ":", j1, i1, 0);
                            j1 += textDrawingArea.getFormattedStringWidth(s1) + 8;
                            textDrawingArea.drawTextHLeftVTop(chatMessages[k], j1, i1, 255);
                        }
                        j++;
                    }
                    if((l == 3 || l == 7) && splitPrivateChat == 0 && (l == 7 || privateChatMode == 0 || privateChatMode == 1 && isFriendOrSelf(s1)))
                    {
                        if(i1 > 0 && i1 < 110)
                        {
                            int k1 = 4;
                            textDrawingArea.drawTextHLeftVTop("From", k1, i1, 0);
                            k1 += textDrawingArea.getFormattedStringWidth("From ");
                            if(byte0 == 1)
                            {
                                modIcons[0].method361(k1, i1 - 12);
                                k1 += 14;
                            }
                            if(byte0 == 2)
                            {
                                modIcons[1].method361(k1, i1 - 12);
                                k1 += 14;
                            }
                            textDrawingArea.drawTextHLeftVTop(s1 + ":", k1, i1, 0);
                            k1 += textDrawingArea.getFormattedStringWidth(s1) + 8;
                            textDrawingArea.drawTextHLeftVTop(chatMessages[k], k1, i1, 0x800000);
                        }
                        j++;
                    }
                    if(l == 4 && (tradeMode == 0 || tradeMode == 1 && isFriendOrSelf(s1)))
                    {
                        if(i1 > 0 && i1 < 110)
                            textDrawingArea.drawTextHLeftVTop(s1 + " " + chatMessages[k], 4, i1, 0x800080);
                        j++;
                    }
                    if(l == 5 && splitPrivateChat == 0 && privateChatMode < 2)
                    {
                        if(i1 > 0 && i1 < 110)
                            textDrawingArea.drawTextHLeftVTop(chatMessages[k], 4, i1, 0x800000);
                        j++;
                    }
                    if(l == 6 && splitPrivateChat == 0 && privateChatMode < 2)
                    {
                        if(i1 > 0 && i1 < 110)
                        {
                            textDrawingArea.drawTextHLeftVTop("To " + s1 + ":", 4, i1, 0);
                            textDrawingArea.drawTextHLeftVTop(chatMessages[k], 12 + textDrawingArea.getFormattedStringWidth("To " + s1), i1, 0x800000);
                        }
                        j++;
                    }
                    if(l == 8 && (tradeMode == 0 || tradeMode == 1 && isFriendOrSelf(s1)))
                    {
                        if(i1 > 0 && i1 < 110)
                            textDrawingArea.drawTextHLeftVTop(s1 + " " + chatMessages[k], 4, i1, 0x7e3200);
                        j++;
                    }
                }

            DrawingArea.defaultDrawingAreaSize();
            anInt1211 = j * 14 + 7;
            if(anInt1211 < 78)
                anInt1211 = 78;
            method30(77, anInt1211 - anInt1089 - 77, 0, 463, anInt1211);
            String s;
            if(localPlayer != null && localPlayer.name != null)
                s = localPlayer.name;
            else
                s = TextClass.formatName(myUsername);
            textDrawingArea.drawTextHLeftVTop(s + ":", 4, 90, 0);
            textDrawingArea.drawTextHLeftVTop(inputString + "*", 6 + textDrawingArea.getFormattedStringWidth(s + ": "), 90, 255);
            DrawingArea.drawHLine(77, 0, 479, 0);
        }
        if(menuOpen && menuScreenArea == 2)
            drawMenu();
        aRSImageProducer_1166.drawGraphics(357, super.gameGraphics, 17);
        gameScreenCanvas.initDrawingArea();
        Texture.lineOffsets = anIntArray1182;
    }

    public void init()
    {
        nodeID = Integer.parseInt(getParameter("nodeid"));
        portOffset = Integer.parseInt(getParameter("portoff"));
        String s = getParameter("lowmem");
        if(s != null && s.equals("1"))
            setLowMem();
        else
            setHighMem();
        String s1 = getParameter("free");
        isMembers = !(s1 != null && s1.equals("1"));
        initClientFrame(765, 503);
    }

    public void startRunnable(Runnable runnable, int i)
    {
        if(i > 10)
            i = 10;
        if(signlink.mainapp != null)
        {
            signlink.startthread(runnable, i);
        } else
        {
            super.startRunnable(runnable, i);
        }
    }

    public Socket openSocket(int i)
        throws IOException
    {
        if(signlink.mainapp != null)
            return signlink.opensocket(i);
        else
            return new Socket(InetAddress.getByName(getCodeBase().getHost()), i);
    }

    private void processMenuClick()
    {
        if(activeInterfaceType != 0)
            return;
        int j = super.clickType;
        if(spellSelected == 1 && super.clickX >= 516 && super.clickY >= 160 && super.clickX <= 765 && super.clickY <= 205)
            j = 0;
        if(menuOpen)
        {
            if(j != 1)
            {
                int k = super.mouseX;
                int j1 = super.mouseY;
                if(menuScreenArea == 0)
                {
                    k -= 4;
                    j1 -= 4;
                }
                if(menuScreenArea == 1)
                {
                    k -= 553;
                    j1 -= 205;
                }
                if(menuScreenArea == 2)
                {
                    k -= 17;
                    j1 -= 357;
                }
                if(k < menuOffsetX - 10 || k > menuOffsetX + menuWidth + 10 || j1 < menuOffsetY - 10 || j1 > menuOffsetY + menuHeight + 10)
                {
                    menuOpen = false;
                    if(menuScreenArea == 1)
                        needDrawTabArea = true;
                    if(menuScreenArea == 2)
                        inputTaken = true;
                }
            }
            if(j == 1)
            {
                int l = menuOffsetX;
                int k1 = menuOffsetY;
                int i2 = menuWidth;
                int k2 = super.clickX;
                int l2 = super.clickY;
                if(menuScreenArea == 0)
                {
                    k2 -= 4;
                    l2 -= 4;
                }
                if(menuScreenArea == 1)
                {
                    k2 -= 553;
                    l2 -= 205;
                }
                if(menuScreenArea == 2)
                {
                    k2 -= 17;
                    l2 -= 357;
                }
                int i3 = -1;
                for(int j3 = 0; j3 < menuActionRow; j3++)
                {
                    int k3 = k1 + 31 + (menuActionRow - 1 - j3) * 15;
                    if(k2 > l && k2 < l + i2 && l2 > k3 - 13 && l2 < k3 + 3)
                        i3 = j3;
                }

                if(i3 != -1)
                    doAction(i3);
                menuOpen = false;
                if(menuScreenArea == 1)
                    needDrawTabArea = true;
                if(menuScreenArea == 2)
                {
                    inputTaken = true;
                }
            }
        } else
        {
            if(j == 1 && menuActionRow > 0)
            {
                int i1 = menuActionID[menuActionRow - 1];
                if(i1 == 632 || i1 == 78 || i1 == 867 || i1 == 431 || i1 == 53 || i1 == 74 || i1 == 454 || i1 == 539 || i1 == 493 || i1 == 847 || i1 == 447 || i1 == 1125)
                {
                    int l1 = menuActionCmd2[menuActionRow - 1];
                    int j2 = menuActionCmd3[menuActionRow - 1];
                    RSInterface class9 = RSInterface.interfaceCache[j2];
                    if(class9.aBoolean259 || class9.aBoolean235)
                    {
                        aBoolean1242 = false;
                        anInt989 = 0;
                        anInt1084 = j2;
                        anInt1085 = l1;
                        activeInterfaceType = 2;
                        anInt1087 = super.clickX;
                        anInt1088 = super.clickY;
                        if(RSInterface.interfaceCache[j2].parentID == openInterfaceId)
                            activeInterfaceType = 1;
                        if(RSInterface.interfaceCache[j2].parentID == backDialogID)
                            activeInterfaceType = 3;
                        return;
                    }
                }
            }
            if(j == 1 && (anInt1253 == 1 || menuHasAddFriend(menuActionRow - 1)) && menuActionRow > 2)
                j = 2;
            if(j == 1 && menuActionRow > 0)
                doAction(menuActionRow - 1);
            if(j == 2 && menuActionRow > 0)
                processMenuHovering();
        }
    }

    private void saveMidi(boolean flag, byte abyte0[])
    {
        signlink.midifade = flag ? 1 : 0;
        signlink.midisave(abyte0, abyte0.length);
    }

    private void loadRegion()
    {
        try
        {
            lastRegionId = -1;
            stationaryGraphicQueue.removeAll();
            projectileQueue.removeAll();
            Texture.clearTextureCache();
            resetModelCaches();
            worldController.initToNull();
            System.gc();
            for(int z = 0; z < 4; z++)
                currentCollisionMap[z].reset();

            for(int z = 0; z < 4; z++)
            {
                for(int x = 0; x < 104; x++)
                {
                    for(int y = 0; y < 104; y++)
                        byteGroundArray[z][x][y] = 0;

                }

            }

            ObjectManager objectManager = new ObjectManager(byteGroundArray, intGroundArray);
            int dataLength = terrainData.length;
            stream.putOpcode(0);
            if(!loadGeneratedMap)
            {
                for(int pointer = 0; pointer < dataLength; pointer++)
                {
                    int offsetX = (mapCoordinates[pointer] >> 8) * 64 - baseX;
                    int offsetY = (mapCoordinates[pointer] & 0xff) * 64 - baseY;
                    byte data[] = terrainData[pointer];
                    if(data != null)
                        objectManager.loadTerrainBlock(data, offsetY, offsetX, (regionX - 6) * 8, (regionY - 6) * 8, currentCollisionMap);
                }

                for(int pointer = 0; pointer < dataLength; pointer++)
                {
                    int offsetX = (mapCoordinates[pointer] >> 8) * 64 - baseX;
                    int offsetY = (mapCoordinates[pointer] & 0xff) * 64 - baseY;
                    byte data[] = terrainData[pointer];
                    if(data == null && regionY < 800)
                        objectManager.clearRegion(offsetY, 64, 64, offsetX);
                }

                anticheat2++;
                if(anticheat2 > 160)
                {
                    anticheat2 = 0;
                    stream.putOpcode(238);
                    stream.put(96);
                }
                stream.putOpcode(0);
                for(int pointer = 0; pointer < dataLength; pointer++)
                {
                    byte data[] = objectData[pointer];
                    if(data != null)
                    {
                        int offsetX = (mapCoordinates[pointer] >> 8) * 64 - baseX;
                        int offsetY = (mapCoordinates[pointer] & 0xff) * 64 - baseY;
                        objectManager.loadObjectBlock(offsetX, currentCollisionMap, offsetY, worldController, data);
                    }
                }

            }
            if(loadGeneratedMap)
            {
                for(int z = 0; z < 4; z++)
                {
                    for(int x = 0; x < 13; x++)
                    {
                        for(int y = 0; y < 13; y++)
                        {
                            int data = constructMapTiles[z][x][y];
                            if(data != -1)
                            {
                                int tileZ = data >> 24 & 3;
                                int tileRotation = data >> 1 & 3;
                                int tileX = data >> 14 & 0x3ff;
                                int tileY = data >> 3 & 0x7ff;
                                int tileCoordinates = (tileX / 8 << 8) + tileY / 8;
                                for(int pointer = 0; pointer < mapCoordinates.length; pointer++)
                                {
                                    if(mapCoordinates[pointer] != tileCoordinates || terrainData[pointer] == null)
                                        continue;
                                    objectManager.loadTerrainSubblock(tileZ, tileRotation, currentCollisionMap, x * 8, (tileX & 7) * 8, terrainData[pointer], (tileY & 7) * 8, z, y * 8);
                                    break;
                                }

                            }
                        }

                    }

                }

                for(int x = 0; x < 13; x++)
                {
                    for(int y = 0; y < 13; y++)
                    {
                        int displayMap = constructMapTiles[0][x][y];
                        if(displayMap == -1)
                            objectManager.clearRegion(y * 8, 8, 8, x * 8);
                    }

                }

                stream.putOpcode(0);
                for(int z = 0; z < 4; z++)
                {
                    for(int x = 0; x < 13; x++)
                    {
                        for(int y = 0; y < 13; y++)
                        {
                            int bits = constructMapTiles[z][x][y];
                            if(bits != -1)
                            {
                                int tileZ = bits >> 24 & 3;
                                int tileRotation = bits >> 1 & 3;
                                int tileX = bits >> 14 & 0x3ff;
                                int tileY = bits >> 3 & 0x7ff;
                                int tileCoorindates = (tileX / 8 << 8) + tileY / 8;
                                for(int pointer = 0; pointer < mapCoordinates.length; pointer++)
                                {
                                    if(mapCoordinates[pointer] != tileCoorindates || objectData[pointer] == null)
                                        continue;
                                    objectManager.loadObjectSubblock(currentCollisionMap, worldController, tileZ, x * 8, (tileY & 7) * 8, z, objectData[pointer], (tileX & 7) * 8, tileRotation, y * 8);
                                    break;
                                }

                            }
                        }

                    }

                }

            }
            stream.putOpcode(0);
            objectManager.addTiles(currentCollisionMap, worldController);
            gameScreenCanvas.initDrawingArea();
            stream.putOpcode(0);
            int z = ObjectManager.setZ;
            if(z > plane)
                z = plane;
            if(z < plane - 1)
                z = plane - 1;
            if(lowMemory)
                worldController.setHeightLevel(ObjectManager.setZ);
            else
                worldController.setHeightLevel(0);
            for(int x = 0; x < 104; x++)
            {
                for(int y = 0; y < 104; y++)
                    spawnGroundItem(x, y);

            }

            loadedRegions++;
            if(loadedRegions > 98)
            {
                loadedRegions = 0;
                stream.putOpcode(150);
            }
            clearObjectSpawnRequests();
        }
        catch(Exception exception) { }
        ObjectDef.modelCache.unlinkAll();
        if(super.gameFrame != null)
        {
            stream.putOpcode(210);
            stream.putInt(0x3f008edd);
        }
        if(lowMemory && signlink.cache_dat != null)
        {
            int modelCount = onDemandFetcher.getVersionCount(0);
            for(int model = 0; model < modelCount; model++)
            {
                int modelIndex = onDemandFetcher.getModelIndex(model);
                if((modelIndex & 0x79) == 0)
                    Model.resetModel(model);
            }

        }
        System.gc();
        Texture.resetTextures();
        onDemandFetcher.clearPassiveRequests();
        int x1 = (regionX - 6) / 8 - 1;
        int x2 = (regionX + 6) / 8 + 1;
        int y1 = (regionY - 6) / 8 - 1;
        int y2 = (regionY + 6) / 8 + 1;
        if(tutorialIsland)
        {
            x1 = 49;
            x2 = 50;
            y1 = 49;
            y2 = 50;
        }
        for(int x = x1; x <= x2; x++)
        {
            for(int y = y1; y <= y2; y++)
                if(x == x1 || x == x2 || y == y1 || y == y2)
                {
                    int mapIndex1 = onDemandFetcher.getMapIndex(0, y, x);
                    if(mapIndex1 != -1)
                        onDemandFetcher.passiveRequest(mapIndex1, 3);
                    int mapIndex2 = onDemandFetcher.getMapIndex(1, y, x);
                    if(mapIndex2 != -1)
                        onDemandFetcher.passiveRequest(mapIndex2, 3);
                }

        }

    }

    private void resetModelCaches()
    {
        ObjectDef.modelCache.unlinkAll();
        ObjectDef.mruNodes2.unlinkAll();
        EntityDef.mruNodes.unlinkAll();
        ItemDef.mruNodes2.unlinkAll();
        ItemDef.mruNodes1.unlinkAll();
        Player.mruNodes.unlinkAll();
        SpotAnim.modelCache.unlinkAll();
    }

    private void method24(int i)
    {
        int ai[] = aClass30_Sub2_Sub1_Sub1_1263.myPixels;
        int j = ai.length;
        for(int k = 0; k < j; k++)
            ai[k] = 0;

        for(int l = 1; l < 103; l++)
        {
            int i1 = 24628 + (103 - l) * 512 * 4;
            for(int k1 = 1; k1 < 103; k1++)
            {
                if((byteGroundArray[i][k1][l] & 0x18) == 0)
                    worldController.drawMinimapTile(k1, l, i, ai, i1);
                if(i < 3 && (byteGroundArray[i + 1][k1][l] & 8) != 0)
                    worldController.drawMinimapTile(k1, l, i + 1, ai, i1);
                i1 += 4;
            }

        }

        int j1 = ((238 + (int)(Math.random() * 20D)) - 10 << 16) + ((238 + (int)(Math.random() * 20D)) - 10 << 8) + ((238 + (int)(Math.random() * 20D)) - 10);
        int l1 = (238 + (int)(Math.random() * 20D)) - 10 << 16;
        aClass30_Sub2_Sub1_Sub1_1263.method343();
        for(int i2 = 1; i2 < 103; i2++)
        {
            for(int j2 = 1; j2 < 103; j2++)
            {
                if((byteGroundArray[i][j2][i2] & 0x18) == 0)
                    method50(i2, j1, j2, l1, i);
                if(i < 3 && (byteGroundArray[i + 1][j2][i2] & 8) != 0)
                    method50(i2, j1, j2, l1, i + 1);
            }

        }

        gameScreenCanvas.initDrawingArea();
        anInt1071 = 0;
        for(int k2 = 0; k2 < 104; k2++)
        {
            for(int l2 = 0; l2 < 104; l2++)
            {
                int i3 = worldController.getGroundDecorationUID(k2, l2, plane);
                if(i3 != 0)
                {
                    i3 = i3 >> 14 & 0x7fff;
                    int j3 = ObjectDef.forID(i3).anInt746;
                    if(j3 >= 0)
                    {
                        int k3 = k2;
                        int l3 = l2;
                        if(j3 != 22 && j3 != 29 && j3 != 34 && j3 != 36 && j3 != 46 && j3 != 47 && j3 != 48)
                        {
                            byte byte0 = 104;
                            byte byte1 = 104;
                            int ai1[][] = currentCollisionMap[plane].clippingData;
                            for(int i4 = 0; i4 < 10; i4++)
                            {
                                int j4 = (int)(Math.random() * 4D);
                                if(j4 == 0 && k3 > 0 && k3 > k2 - 3 && (ai1[k3 - 1][l3] & 0x1280108) == 0)
                                    k3--;
                                if(j4 == 1 && k3 < byte0 - 1 && k3 < k2 + 3 && (ai1[k3 + 1][l3] & 0x1280180) == 0)
                                    k3++;
                                if(j4 == 2 && l3 > 0 && l3 > l2 - 3 && (ai1[k3][l3 - 1] & 0x1280102) == 0)
                                    l3--;
                                if(j4 == 3 && l3 < byte1 - 1 && l3 < l2 + 3 && (ai1[k3][l3 + 1] & 0x1280120) == 0)
                                    l3++;
                            }

                        }
                        aClass30_Sub2_Sub1_Sub1Array1140[anInt1071] = mapFunctions[j3];
                        anIntArray1072[anInt1071] = k3;
                        anIntArray1073[anInt1071] = l3;
                        anInt1071++;
                    }
                }
            }

        }

    }

    private void spawnGroundItem(int i, int j)
    {
        NodeList class19 = groundArray[plane][i][j];
        if(class19 == null)
        {
            worldController.removeGroundItemTile(i, j, plane);
            return;
        }
        int k = 0xfa0a1f01;
        Object obj = null;
        for(Item item = (Item)class19.reverseGetFirst(); item != null; item = (Item)class19.reverseGetNext())
        {
            ItemDef itemDef = ItemDef.forID(item.itemId);
            int l = itemDef.value;
            if(itemDef.stackable)
                l *= item.itemCount + 1;
//	notifyItemSpawn(item, i + baseX, j + baseY);
	
            if(l > k)
            {
                k = l;
                obj = item;
            }
        }

        class19.insertTail(((Node) (obj)));
        Object obj1 = null;
        Object obj2 = null;
        for(Item class30_sub2_sub4_sub2_1 = (Item)class19.reverseGetFirst(); class30_sub2_sub4_sub2_1 != null; class30_sub2_sub4_sub2_1 = (Item)class19.reverseGetNext())
        {
            if(class30_sub2_sub4_sub2_1.itemId != ((Item) (obj)).itemId && obj1 == null)
                obj1 = class30_sub2_sub4_sub2_1;
            if(class30_sub2_sub4_sub2_1.itemId != ((Item) (obj)).itemId && class30_sub2_sub4_sub2_1.itemId != ((Item) (obj1)).itemId && obj2 == null)
                obj2 = class30_sub2_sub4_sub2_1;
        }

        int i1 = i + (j << 7) + 0x60000000;
        worldController.addGroundItemTile(i, j, plane, getFloorDrawHeight(plane, j * 128 + 64, i * 128 + 64), i1, ((Animable) (obj)), ((Animable) (obj1)), ((Animable) (obj2)));
    }

    private void method26(boolean flag)
    {
        for(int j = 0; j < npcCount; j++)
        {
            NPC npc = npcArray[npcIndices[j]];
            int k = 0x20000000 + (npcIndices[j] << 14);
            if(npc == null || !npc.isVisible() || npc.npcDefinition.aBoolean93 != flag)
                continue;
            int l = npc.x >> 7;
            int i1 = npc.y >> 7;
            if(l < 0 || l >= 104 || i1 < 0 || i1 >= 104)
                continue;
            if(npc.boundaryDimension == 1 && (npc.x & 0x7f) == 64 && (npc.y & 0x7f) == 64)
            {
                if(anIntArrayArray929[l][i1] == anInt1265)
                    continue;
                anIntArrayArray929[l][i1] = anInt1265;
            }
            if(!npc.npcDefinition.aBoolean84)
                k += 0x80000000;
            worldController.addEntityA(plane, npc.x, npc.y, getFloorDrawHeight(plane, npc.y, npc.x), npc.currentRotation, npc, k, (npc.boundaryDimension - 1) * 64 + 60, npc.aBoolean1541);
        }
    }

    private boolean replayWave()
    {
            return signlink.wavereplay();
    }

    private void loadError()
    {
        String s = "ondemand";//was a constant parameter
        System.out.println(s);
        try
        {
            getAppletContext().showDocument(new URL(getCodeBase(), "loaderror_" + s + ".html"));
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        do
            try
            {
                Thread.sleep(1000L);
            }
            catch(Exception _ex) { }
        while(true);
    }

    private void buildInterfaceMenu(int i, RSInterface class9, int k, int l, int i1, int j1)
    {
        if(class9.type != 0 || class9.children == null || class9.aBoolean266)
            return;
        if(k < i || i1 < l || k > i + class9.width || i1 > l + class9.height)
            return;
        int k1 = class9.children.length;
        for(int l1 = 0; l1 < k1; l1++)
        {
            int i2 = class9.childX[l1] + i;
            int j2 = (class9.childY[l1] + l) - j1;
            RSInterface class9_1 = RSInterface.interfaceCache[class9.children[l1]];
            i2 += class9_1.x;
            j2 += class9_1.y;
            if((class9_1.anInt230 >= 0 || class9_1.anInt216 != 0) && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height)
                if(class9_1.anInt230 >= 0)
                    anInt886 = class9_1.anInt230;
                else
                    anInt886 = class9_1.id;
            if(class9_1.type == 0)
            {
                buildInterfaceMenu(i2, class9_1, k, j2, i1, class9_1.scrollPosition);
                if(class9_1.scrollMax > class9_1.height)
                    method65(i2 + class9_1.width, class9_1.height, k, i1, class9_1, j2, true, class9_1.scrollMax);
            } else
            {
                if(class9_1.atActionType == 1 && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height)
                {
                    boolean flag = false;
                    if(class9_1.contentType != 0)
                        flag = buildFriendsListMenu(class9_1);
                    if(!flag)
                    {
                        //System.out.println("1"+class9_1.tooltip + ", " + class9_1.interfaceID);
                        menuActionName[menuActionRow] = class9_1.tooltip + ", " + class9_1.id;
                        menuActionID[menuActionRow] = 315;
                        menuActionCmd3[menuActionRow] = class9_1.id;
                        menuActionRow++;
                    }
                }
                if(class9_1.atActionType == 2 && spellSelected == 0 && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height)
                {
                    String s = class9_1.selectedActionName;
                    if(s.indexOf(" ") != -1)
                        s = s.substring(0, s.indexOf(" "));
                    menuActionName[menuActionRow] = s + " @gre@" + class9_1.spellName;
                    menuActionID[menuActionRow] = 626;
                    menuActionCmd3[menuActionRow] = class9_1.id;
                    menuActionRow++;
                }
                if(class9_1.atActionType == 3 && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height)
                {
                    menuActionName[menuActionRow] = "Close";
                    menuActionID[menuActionRow] = 200;
                    menuActionCmd3[menuActionRow] = class9_1.id;
                    menuActionRow++;
                }
                if(class9_1.atActionType == 4 && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height)
                {
                    //System.out.println("2"+class9_1.tooltip + ", " + class9_1.interfaceID);
                    menuActionName[menuActionRow] = class9_1.tooltip + ", " + class9_1.id;
                    menuActionID[menuActionRow] = 169;
                    menuActionCmd3[menuActionRow] = class9_1.id;
                    menuActionRow++;
                }
                if(class9_1.atActionType == 5 && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height)
                {
                    //System.out.println("3"+class9_1.tooltip + ", " + class9_1.interfaceID);
                    menuActionName[menuActionRow] = class9_1.tooltip + ", " + class9_1.id;
                    menuActionID[menuActionRow] = 646;
                    menuActionCmd3[menuActionRow] = class9_1.id;
                    menuActionRow++;
                }
                if(class9_1.atActionType == 6 && !aBoolean1149 && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height)
                {
                    //System.out.println("4"+class9_1.tooltip + ", " + class9_1.interfaceID);
                    menuActionName[menuActionRow] = class9_1.tooltip + ", " + class9_1.id;
                    menuActionID[menuActionRow] = 679;
                    menuActionCmd3[menuActionRow] = class9_1.id;
                    menuActionRow++;
                }
                if(class9_1.type == 2)
                {
                    int k2 = 0;
                    for(int l2 = 0; l2 < class9_1.height; l2++)
                    {
                        for(int i3 = 0; i3 < class9_1.width; i3++)
                        {
                            int j3 = i2 + i3 * (32 + class9_1.invSpritePadX);
                            int k3 = j2 + l2 * (32 + class9_1.invSpritePadY);
                            if(k2 < 20)
                            {
                                j3 += class9_1.spritesX[k2];
                                k3 += class9_1.spritesY[k2];
                            }
                            if(k >= j3 && i1 >= k3 && k < j3 + 32 && i1 < k3 + 32)
                            {
                                mouseInvInterfaceIndex = k2;
                                lastActiveInvInterface = class9_1.id;
                                if(class9_1.inventoryItemId[k2] > 0)
                                {
                                    ItemDef itemDef = ItemDef.forID(class9_1.inventoryItemId[k2] - 1);
                                    if(itemSelected == 1 && class9_1.isInventoryInterface)
                                    {
                                        if(class9_1.id != anInt1284 || k2 != anInt1283)
                                        {
                                            menuActionName[menuActionRow] = "Use " + selectedItemName + " with @lre@" + itemDef.name;
                                            menuActionID[menuActionRow] = 870;
                                            menuActionCmd1[menuActionRow] = itemDef.id;
                                            menuActionCmd2[menuActionRow] = k2;
                                            menuActionCmd3[menuActionRow] = class9_1.id;
                                            menuActionRow++;
                                        }
                                    } else
                                    if(spellSelected == 1 && class9_1.isInventoryInterface)
                                    {
                                        if((spellUsableOn & 0x10) == 16)
                                        {
                                            menuActionName[menuActionRow] = spellTooltip + " @lre@" + itemDef.name;
                                            menuActionID[menuActionRow] = 543;
                                            menuActionCmd1[menuActionRow] = itemDef.id;
                                            menuActionCmd2[menuActionRow] = k2;
                                            menuActionCmd3[menuActionRow] = class9_1.id;
                                            menuActionRow++;
                                        }
                                    } else
                                    {
                                        if(class9_1.isInventoryInterface)
                                        {
                                            for(int l3 = 4; l3 >= 3; l3--)
                                                if(itemDef.actions != null && itemDef.actions[l3] != null)
                                                {
                                                    menuActionName[menuActionRow] = itemDef.actions[l3] + " @lre@" + itemDef.name;
                                                    if(l3 == 3)
                                                        menuActionID[menuActionRow] = 493;
                                                    if(l3 == 4)
                                                        menuActionID[menuActionRow] = 847;
                                                    menuActionCmd1[menuActionRow] = itemDef.id;
                                                    menuActionCmd2[menuActionRow] = k2;
                                                    menuActionCmd3[menuActionRow] = class9_1.id;
                                                    menuActionRow++;
                                                } else
                                                if(l3 == 4)
                                                {
                                                    menuActionName[menuActionRow] = "Drop @lre@" + itemDef.name;
                                                    menuActionID[menuActionRow] = 847;
                                                    menuActionCmd1[menuActionRow] = itemDef.id;
                                                    menuActionCmd2[menuActionRow] = k2;
                                                    menuActionCmd3[menuActionRow] = class9_1.id;
                                                    menuActionRow++;
                                                }

                                        }
                                        if(class9_1.usableItemInterface)
                                        {
                                            menuActionName[menuActionRow] = "Use @lre@" + itemDef.name;
                                            menuActionID[menuActionRow] = 447;
                                            menuActionCmd1[menuActionRow] = itemDef.id;
                                            menuActionCmd2[menuActionRow] = k2;
                                            menuActionCmd3[menuActionRow] = class9_1.id;
                                            menuActionRow++;
                                        }
                                        if(class9_1.isInventoryInterface && itemDef.actions != null)
                                        {
                                            for(int i4 = 2; i4 >= 0; i4--)
                                                if(itemDef.actions[i4] != null)
                                                {
                                                    menuActionName[menuActionRow] = itemDef.actions[i4] + " @lre@" + itemDef.name;
                                                    if(i4 == 0)
                                                        menuActionID[menuActionRow] = 74;
                                                    if(i4 == 1)
                                                        menuActionID[menuActionRow] = 454;
                                                    if(i4 == 2)
                                                        menuActionID[menuActionRow] = 539;
                                                    menuActionCmd1[menuActionRow] = itemDef.id;
                                                    menuActionCmd2[menuActionRow] = k2;
                                                    menuActionCmd3[menuActionRow] = class9_1.id;
                                                    menuActionRow++;
                                                }

                                        }
                                        if(class9_1.actions != null)
                                        {
                                            for(int j4 = 4; j4 >= 0; j4--)
                                                if(class9_1.actions[j4] != null)
                                                {
                                                    menuActionName[menuActionRow] = class9_1.actions[j4] + " @lre@" + itemDef.name;
                                                    if(j4 == 0)
                                                        menuActionID[menuActionRow] = 632;
                                                    if(j4 == 1)
                                                        menuActionID[menuActionRow] = 78;
                                                    if(j4 == 2)
                                                        menuActionID[menuActionRow] = 867;
                                                    if(j4 == 3)
                                                        menuActionID[menuActionRow] = 431;
                                                    if(j4 == 4)
                                                        menuActionID[menuActionRow] = 53;
                                                    menuActionCmd1[menuActionRow] = itemDef.id;
                                                    menuActionCmd2[menuActionRow] = k2;
                                                    menuActionCmd3[menuActionRow] = class9_1.id;
                                                    menuActionRow++;
                                                }

                                        }
                                        menuActionName[menuActionRow] = "Examine @lre@" + itemDef.name + " @gre@(@whi@" + (class9_1.inventoryItemId[k2] - 1) + "@gre@)";
                                        menuActionID[menuActionRow] = 1125;
                                        menuActionCmd1[menuActionRow] = itemDef.id;
                                        menuActionCmd2[menuActionRow] = k2;
                                        menuActionCmd3[menuActionRow] = class9_1.id;
                                        menuActionRow++;
                                    }
                                }
                            }
                            k2++;
                        }

                    }

                }
            }
        }

    }

    private void method30(int j, int k, int l, int i1, int j1)
    {
        scrollBar1.method361(i1, l);
        scrollBar2.method361(i1, (l + j) - 16);
        DrawingArea.method336(j - 32, l + 16, i1, anInt1002, 16);
        int k1 = ((j - 32) * j) / j1;
        if(k1 < 8)
            k1 = 8;
        int l1 = ((j - 32 - k1) * k) / (j1 - j);
        DrawingArea.method336(k1, l + 16 + l1, i1, anInt1063, 16);
        DrawingArea.method341(l + 16 + l1, anInt902, k1, i1);
        DrawingArea.method341(l + 16 + l1, anInt902, k1, i1 + 1);
        DrawingArea.drawHLine(l + 16 + l1, anInt902, 16, i1);
        DrawingArea.drawHLine(l + 17 + l1, anInt902, 16, i1);
        DrawingArea.method341(l + 16 + l1, anInt927, k1, i1 + 15);
        DrawingArea.method341(l + 17 + l1, anInt927, k1 - 1, i1 + 14);
        DrawingArea.drawHLine(l + 15 + l1 + k1, anInt927, 16, i1);
        DrawingArea.drawHLine(l + 14 + l1 + k1, anInt927, 15, i1 + 1);
    }

    private void updateNPCs(Stream stream, int amount)
    {
        anInt839 = 0;
        actorsAwaitingUpdatePointer = 0;
        updateNPCMovement(stream);
        updateNPCList(amount, stream);
        updateNPCBlock(stream);
        for(int k = 0; k < anInt839; k++)
        {
            int npcId = anIntArray840[k];
            if(npcArray[npcId].lastUpdateTime != loopCycle)
            {
                npcArray[npcId].npcDefinition = null;
                npcArray[npcId] = null;
            }
        }

        if(stream.currentOffset != amount)
        {
            signlink.reporterror(myUsername + " size mismatch in getnpcpos - pos:" + stream.currentOffset + " psize:" + amount);
            throw new RuntimeException("eek");
        }
        for(int n = 0; n < npcCount; n++)
            if(npcArray[npcIndices[n]] == null)
            {
                signlink.reporterror(myUsername + " null entry in npc list - pos:" + n + " size:" + npcCount);
                throw new RuntimeException("eek");
            }

    }

    private void processChatModeClick()
    {
        if(super.clickType == 1)
        {
            if(super.clickX >= 6 && super.clickX <= 106 && super.clickY >= 467 && super.clickY <= 499)
            {
                publicChatMode = (publicChatMode + 1) % 4;
                updateChatSettings = true;
                inputTaken = true;
                stream.putOpcode(95);
                stream.put(publicChatMode);
                stream.put(privateChatMode);
                stream.put(tradeMode);
            }
            if(super.clickX >= 135 && super.clickX <= 235 && super.clickY >= 467 && super.clickY <= 499)
            {
                privateChatMode = (privateChatMode + 1) % 3;
                updateChatSettings = true;
                inputTaken = true;
                stream.putOpcode(95);
                stream.put(publicChatMode);
                stream.put(privateChatMode);
                stream.put(tradeMode);
            }
            if(super.clickX >= 273 && super.clickX <= 373 && super.clickY >= 467 && super.clickY <= 499)
            {
                tradeMode = (tradeMode + 1) % 3;
                updateChatSettings = true;
                inputTaken = true;
                stream.putOpcode(95);
                stream.put(publicChatMode);
                stream.put(privateChatMode);
                stream.put(tradeMode);
            }
            if(super.clickX >= 412 && super.clickX <= 512 && super.clickY >= 467 && super.clickY <= 499)
                if(openInterfaceId == -1)
                {
                    clearTopInterfaces();
                    reportAbuseInput = "";
                    canMute = false;
                    for(int i = 0; i < RSInterface.interfaceCache.length; i++)
                    {
                        if(RSInterface.interfaceCache[i] == null || RSInterface.interfaceCache[i].contentType != 600)
                            continue;
                        reportAbuseInterfaceID = openInterfaceId = RSInterface.interfaceCache[i].parentID;
                        break;
                    }

                } else
                {
                    pushMessage("Please close the interface you have open before using 'report abuse'", 0, "");
                }
            anInt940++;
            if(anInt940 > 1386)
            {
                anInt940 = 0;
                stream.putOpcode(165);
                stream.put(0);
                int j = stream.currentOffset;
                stream.put(139);
                stream.put(150);
                stream.putShort(32131);
                stream.put((int)(Math.random() * 256D));
                stream.putShort(3250);
                stream.put(177);
                stream.putShort(24859);
                stream.put(119);
                if((int)(Math.random() * 2D) == 0)
                    stream.putShort(47234);
                if((int)(Math.random() * 2D) == 0)
                    stream.put(21);
                stream.putSizeByte(stream.currentOffset - j);
            }
        }
    }

    private void handleInterfaceSetting(int i)
    {
        int j = Varp.cache[i].anInt709;
        if(j == 0)
            return;
        int k = variousSettings[i];
        if(j == 1)
        {
            if(k == 1)
                Texture.calculatePalette(0.90000000000000002D);
            if(k == 2)
                Texture.calculatePalette(0.80000000000000004D);
            if(k == 3)
                Texture.calculatePalette(0.69999999999999996D);
            if(k == 4)
                Texture.calculatePalette(0.59999999999999998D);
            ItemDef.mruNodes1.unlinkAll();
            welcomeScreenRaised = true;
        }
        if(j == 3)
        {
            boolean flag1 = musicEnabled;
            if(k == 0)
            {
                adjustVolume(musicEnabled, 0);
                musicEnabled = true;
            }
            if(k == 1)
            {
                adjustVolume(musicEnabled, -400);
                musicEnabled = true;
            }
            if(k == 2)
            {
                adjustVolume(musicEnabled, -800);
                musicEnabled = true;
            }
            if(k == 3)
            {
                adjustVolume(musicEnabled, -1200);
                musicEnabled = true;
            }
            if(k == 4)
                musicEnabled = false;
            if(musicEnabled != flag1 && !lowMemory)
            {
                if(musicEnabled)
                {
                    nextSong = currentSong;
                    songChanging = true;
                    onDemandFetcher.request(2, nextSong);
                } else
                {
                    stopMidi();
                }
                prevSong = 0;
            }
        }
        if(j == 4)
        {
            if(k == 0)
            {
                aBoolean848 = true;
                setWaveVolume(0);
            }
            if(k == 1)
            {
                aBoolean848 = true;
                setWaveVolume(-400);
            }
            if(k == 2)
            {
                aBoolean848 = true;
                setWaveVolume(-800);
            }
            if(k == 3)
            {
                aBoolean848 = true;
                setWaveVolume(-1200);
            }
            if(k == 4)
                aBoolean848 = false;
        }
        if(j == 5)
            anInt1253 = k;
        if(j == 6)
            anInt1249 = k;
        if(j == 8)
        {
            splitPrivateChat = k;
            inputTaken = true;
        }
        if(j == 9)
            anInt913 = k;
    }

    private void updateEntities()
    {
        try{
            int anInt974 = 0;
            for(int j = -1; j < playerCount + npcCount; j++)
        {
            Object obj;
            if(j == -1)
                obj = localPlayer;
            else
            if(j < playerCount)
                obj = playerArray[playerId[j]];
            else
                obj = npcArray[npcIndices[j - playerCount]];
            if(obj == null || !((Entity) (obj)).isVisible())
                continue;
            if(obj instanceof NPC)
            {
                EntityDef entityDef = ((NPC)obj).npcDefinition;
                if(entityDef.childrenIDs != null)
                    entityDef = entityDef.method161();
                if(entityDef == null)
                    continue;
            }
            if(j < playerCount)
            {
                int l = 30;
                Player player = (Player)obj;
                if(player.headIcon != 0)
                {
                    npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height + 15);
                    if(spriteDrawX > -1)
                    {
                        for(int i2 = 0; i2 < 8; i2++)
                            if((player.headIcon & 1 << i2) != 0)
                            {
                                headIcons[i2].drawSprite(spriteDrawX - 12, spriteDrawY - l);
                                l -= 25;
                            }

                    }
                }
                if(j >= 0 && hintIconType == 10 && hintIconPlayerId == playerId[j])
                {
                    npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height + 15);
                    if(spriteDrawX > -1)
                        headIcons[7].drawSprite(spriteDrawX - 12, spriteDrawY - l);
                }
            } else
            {
                EntityDef entityDef_1 = ((NPC)obj).npcDefinition;
                if(entityDef_1.anInt75 >= 0 && entityDef_1.anInt75 < headIcons.length)
                {
                    npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height + 15);
                    if(spriteDrawX > -1)
                        headIcons[entityDef_1.anInt75].drawSprite(spriteDrawX - 12, spriteDrawY - 30);
                }
                if(hintIconType == 1 && hintIconNpcId == npcIndices[j - playerCount] && loopCycle % 20 < 10)
                {
                    npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height + 15);
                    if(spriteDrawX > -1)
                        headIcons[2].drawSprite(spriteDrawX - 12, spriteDrawY - 28);
                }
            }
            if(((Entity) (obj)).textSpoken != null && (j >= playerCount || publicChatMode == 0 || publicChatMode == 3 || publicChatMode == 1 && isFriendOrSelf(((Player)obj).name)))
            {
                npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height);
                if(spriteDrawX > -1 && anInt974 < anInt975)
                {
                    anIntArray979[anInt974] = chatTextDrawingArea.getStringWidth(((Entity) (obj)).textSpoken) / 2;
                    anIntArray978[anInt974] = chatTextDrawingArea.charHeight;
                    anIntArray976[anInt974] = spriteDrawX;
                    anIntArray977[anInt974] = spriteDrawY;
                    anIntArray980[anInt974] = ((Entity) (obj)).chatColour;
                    anIntArray981[anInt974] = ((Entity) (obj)).chatEffect;
                    anIntArray982[anInt974] = ((Entity) (obj)).textCycle;
                    aStringArray983[anInt974++] = ((Entity) (obj)).textSpoken;
                    if(anInt1249 == 0 && ((Entity) (obj)).chatEffect >= 1 && ((Entity) (obj)).chatEffect <= 3)
                    {
                        anIntArray978[anInt974] += 10;
                        anIntArray977[anInt974] += 5;
                    }
                    if(anInt1249 == 0 && ((Entity) (obj)).chatEffect == 4)
                        anIntArray979[anInt974] = 60;
                    if(anInt1249 == 0 && ((Entity) (obj)).chatEffect == 5)
                        anIntArray978[anInt974] += 5;
                }
            }
            if(((Entity) (obj)).loopCycleStatus > loopCycle)
            { try{
                npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height + 15);
                if(spriteDrawX > -1)
                {
                    int i1 = (((Entity) (obj)).currentHealth * 30) / ((Entity) (obj)).maxHealth;
                    if(i1 > 30)
                        i1 = 30;
                    DrawingArea.method336(5, spriteDrawY - 3, spriteDrawX - 15, 65280, i1);
                    DrawingArea.method336(5, spriteDrawY - 3, (spriteDrawX - 15) + i1, 0xff0000, 30 - i1);
                }
            }catch(Exception e){ }
            }
            for(int j1 = 0; j1 < 4; j1++)
                if(((Entity) (obj)).hitsLoopCycle[j1] > loopCycle)
                {
                    npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height / 2);
                    if(spriteDrawX > -1)
                    {
                        if(j1 == 1)
                            spriteDrawY -= 20;
                        if(j1 == 2)
                        {
                            spriteDrawX -= 15;
                            spriteDrawY -= 10;
                        }
                        if(j1 == 3)
                        {
                            spriteDrawX += 15;
                            spriteDrawY -= 10;
                        }
                        hitMarks[((Entity) (obj)).hitMarkTypes[j1]].drawSprite(spriteDrawX - 12, spriteDrawY - 12);
                        aTextDrawingArea_1270.drawTextHMidVTop(String.valueOf(((Entity) (obj)).hitArray[j1]), spriteDrawX, spriteDrawY + 4, 0);
                        aTextDrawingArea_1270.drawTextHMidVTop(String.valueOf(((Entity) (obj)).hitArray[j1]), spriteDrawX - 1, spriteDrawY + 3, 0xffffff);
                    }
                }

        }
        for(int k = 0; k < anInt974; k++)
        {
            int k1 = anIntArray976[k];
            int l1 = anIntArray977[k];
            int j2 = anIntArray979[k];
            int k2 = anIntArray978[k];
            boolean flag = true;
            while(flag) 
            {
                flag = false;
                for(int l2 = 0; l2 < k; l2++)
                    if(l1 + 2 > anIntArray977[l2] - anIntArray978[l2] && l1 - k2 < anIntArray977[l2] + 2 && k1 - j2 < anIntArray976[l2] + anIntArray979[l2] && k1 + j2 > anIntArray976[l2] - anIntArray979[l2] && anIntArray977[l2] - anIntArray978[l2] < l1)
                    {
                        l1 = anIntArray977[l2] - anIntArray978[l2];
                        flag = true;
                    }

            }
            spriteDrawX = anIntArray976[k];
            spriteDrawY = anIntArray977[k] = l1;
            String s = aStringArray983[k];
            if(anInt1249 == 0)
            {
                int i3 = 0xffff00;
                if(anIntArray980[k] < 6)
                    i3 = anIntArray965[anIntArray980[k]];
                if(anIntArray980[k] == 6)
                    i3 = anInt1265 % 20 >= 10 ? 0xffff00 : 0xff0000;
                if(anIntArray980[k] == 7)
                    i3 = anInt1265 % 20 >= 10 ? 65535 : 255;
                if(anIntArray980[k] == 8)
                    i3 = anInt1265 % 20 >= 10 ? 0x80ff80 : 45056;
                if(anIntArray980[k] == 9)
                {
                    int j3 = 150 - anIntArray982[k];
                    if(j3 < 50)
                        i3 = 0xff0000 + 1280 * j3;
                    else
                    if(j3 < 100)
                        i3 = 0xffff00 - 0x50000 * (j3 - 50);
                    else
                    if(j3 < 150)
                        i3 = 65280 + 5 * (j3 - 100);
                }
                if(anIntArray980[k] == 10)
                {
                    int k3 = 150 - anIntArray982[k];
                    if(k3 < 50)
                        i3 = 0xff0000 + 5 * k3;
                    else
                    if(k3 < 100)
                        i3 = 0xff00ff - 0x50000 * (k3 - 50);
                    else
                    if(k3 < 150)
                        i3 = (255 + 0x50000 * (k3 - 100)) - 5 * (k3 - 100);
                }
                if(anIntArray980[k] == 11)
                {
                    int l3 = 150 - anIntArray982[k];
                    if(l3 < 50)
                        i3 = 0xffffff - 0x50005 * l3;
                    else
                    if(l3 < 100)
                        i3 = 65280 + 0x50005 * (l3 - 50);
                    else
                    if(l3 < 150)
                        i3 = 0xffffff - 0x50000 * (l3 - 100);
                }
                if(anIntArray981[k] == 0)
                {
                    chatTextDrawingArea.drawTextHMidVTop(s, spriteDrawX, spriteDrawY + 1, 0);
                    chatTextDrawingArea.drawTextHMidVTop(s, spriteDrawX, spriteDrawY, i3);
                }
                if(anIntArray981[k] == 1)
                {
                    chatTextDrawingArea.drawTextHRMidVTopWaving(s, spriteDrawX, spriteDrawY + 1, 0, anInt1265);
                    chatTextDrawingArea.drawTextHRMidVTopWaving(s, spriteDrawX, spriteDrawY, i3, anInt1265);
                }
                if(anIntArray981[k] == 2)
                {
                    chatTextDrawingArea.drawTextHRMidVTopWaving2(s, spriteDrawX, spriteDrawY + 1, 0, anInt1265);
                    chatTextDrawingArea.drawTextHRMidVTopWaving2(s, spriteDrawX, spriteDrawY, i3, anInt1265);
                }
                if(anIntArray981[k] == 3)
                {
                    chatTextDrawingArea.drawTextHRMidVTopWaving(s, spriteDrawX, spriteDrawY + 1, 0, 150 - anIntArray982[k], anInt1265);
                    chatTextDrawingArea.drawTextHRMidVTopWaving(s, spriteDrawX, spriteDrawY, i3, 150 - anIntArray982[k], anInt1265);
                }
                if(anIntArray981[k] == 4)
                {
                    int i4 = chatTextDrawingArea.getStringWidth(s);
                    int k4 = ((150 - anIntArray982[k]) * (i4 + 100)) / 150;
                    DrawingArea.setDrawingArea(334, spriteDrawX - 50, spriteDrawX + 50, 0);
                    chatTextDrawingArea.drawTextHLeftVTop(s, (spriteDrawX + 50) - k4, spriteDrawY + 1, 0);
                    chatTextDrawingArea.drawTextHLeftVTop(s, (spriteDrawX + 50) - k4, spriteDrawY, i3);
                    DrawingArea.defaultDrawingAreaSize();
                }
                if(anIntArray981[k] == 5)
                {
                    int j4 = 150 - anIntArray982[k];
                    int l4 = 0;
                    if(j4 < 25)
                        l4 = j4 - 25;
                    else
                    if(j4 > 125)
                        l4 = j4 - 125;
                    DrawingArea.setDrawingArea(spriteDrawY + 5, 0, 512, spriteDrawY - chatTextDrawingArea.charHeight - 1);
                    chatTextDrawingArea.drawTextHMidVTop(s, spriteDrawX, spriteDrawY + 1 + l4, 0);
                    chatTextDrawingArea.drawTextHMidVTop(s, spriteDrawX, spriteDrawY + l4, i3);
                    DrawingArea.defaultDrawingAreaSize();
                }
            } else
            {
                chatTextDrawingArea.drawTextHMidVTop(s, spriteDrawX, spriteDrawY + 1, 0);
                chatTextDrawingArea.drawTextHMidVTop(s, spriteDrawX, spriteDrawY, 0xffff00);
            }
        }
    }catch(Exception e){ }

    }

    private void deleteFriend(long l)
    {
        try
        {
            if(l == 0L)
                return;
            for(int i = 0; i < friendsCount; i++)
            {
                if(friendsListAsLongs[i] != l)
                    continue;
                friendsCount--;
                needDrawTabArea = true;
                for(int j = i; j < friendsCount; j++)
                {
                    friendsList[j] = friendsList[j + 1];
                    friendsWorldIds[j] = friendsWorldIds[j + 1];
                    friendsListAsLongs[j] = friendsListAsLongs[j + 1];
                }

                stream.putOpcode(215);
                stream.putLong(l);
                break;
            }
        }
        catch(RuntimeException runtimeexception)
        {
            signlink.reporterror("18622, " + false + ", " + l + ", " + runtimeexception.toString());
            throw new RuntimeException();
        }
    }

    private void drawTabArea()
    {
        aRSImageProducer_1163.initDrawingArea();
        Texture.lineOffsets = anIntArray1181;
        invBack.method361(0, 0);
        if(inventoryOverlayInterfaceID != -1)
            drawInterface(0, 0, RSInterface.interfaceCache[inventoryOverlayInterfaceID], 0);
        else
        if(tabInterfaceIDs[tabID] != -1)
            drawInterface(0, 0, RSInterface.interfaceCache[tabInterfaceIDs[tabID]], 0);
        if(menuOpen && menuScreenArea == 1)
            drawMenu();
        aRSImageProducer_1163.drawGraphics(205, super.gameGraphics, 553);
        gameScreenCanvas.initDrawingArea();
        Texture.lineOffsets = anIntArray1182;
    }

    private void method37(int j)
    {
        if(!lowMemory)
        {
            if(Texture.textureLastUsed[17] >= j)
            {
                Background background = Texture.textureImages[17];
                int k = background.imageWidth * background.anInt1453 - 1;
                int j1 = background.imageWidth * animationTimePassed * 2;
                byte abyte0[] = background.imagePixels;
                byte abyte3[] = aByteArray912;
                for(int i2 = 0; i2 <= k; i2++)
                    abyte3[i2] = abyte0[i2 - j1 & k];

                background.imagePixels = abyte3;
                aByteArray912 = abyte0;
                Texture.resetTexture(17);
                anInt854++;
                if(anInt854 > 1235)
                {
                    anInt854 = 0;
                    stream.putOpcode(226);
                    stream.put(0);
                    int l2 = stream.currentOffset;
                    stream.putShort(58722);
                    stream.put(240);
                    stream.putShort((int)(Math.random() * 65536D));
                    stream.put((int)(Math.random() * 256D));
                    if((int)(Math.random() * 2D) == 0)
                        stream.putShort(51825);
                    stream.put((int)(Math.random() * 256D));
                    stream.putShort((int)(Math.random() * 65536D));
                    stream.putShort(7130);
                    stream.putShort((int)(Math.random() * 65536D));
                    stream.putShort(61657);
                    stream.putSizeByte(stream.currentOffset - l2);
                }
            }
            if(Texture.textureLastUsed[24] >= j)
            {
                Background background_1 = Texture.textureImages[24];
                int l = background_1.imageWidth * background_1.anInt1453 - 1;
                int k1 = background_1.imageWidth * animationTimePassed * 2;
                byte abyte1[] = background_1.imagePixels;
                byte abyte4[] = aByteArray912;
                for(int j2 = 0; j2 <= l; j2++)
                    abyte4[j2] = abyte1[j2 - k1 & l];

                background_1.imagePixels = abyte4;
                aByteArray912 = abyte1;
                Texture.resetTexture(24);
            }
            if(Texture.textureLastUsed[34] >= j)
            {
                Background background_2 = Texture.textureImages[34];
                int i1 = background_2.imageWidth * background_2.anInt1453 - 1;
                int l1 = background_2.imageWidth * animationTimePassed * 2;
                byte abyte2[] = background_2.imagePixels;
                byte abyte5[] = aByteArray912;
                for(int k2 = 0; k2 <= i1; k2++)
                    abyte5[k2] = abyte2[k2 - l1 & i1];

                background_2.imagePixels = abyte5;
                aByteArray912 = abyte2;
                Texture.resetTexture(34);
            }
        }
    }

    private void method38()
    {
        for(int i = -1; i < playerCount; i++)
        {
            int j;
            if(i == -1)
                j = localPlayerId;
            else
                j = playerId[i];
            Player player = playerArray[j];
            if(player != null && player.textCycle > 0)
            {
                player.textCycle--;
                if(player.textCycle == 0)
                    player.textSpoken = null;
            }
        }

        for(int k = 0; k < npcCount; k++)
        {
            int l = npcIndices[k];
            NPC npc = npcArray[l];
            if(npc != null && npc.textCycle > 0)
            {
                npc.textCycle--;
                if(npc.textCycle == 0)
                    npc.textSpoken = null;
            }
        }

    }

    private void calcCameraPos()
    {
        int i = anInt1098 * 128 + 64;
        int j = anInt1099 * 128 + 64;
        int k = getFloorDrawHeight(plane, j, i) - anInt1100;
        if(cameraPositionX < i)
        {
            cameraPositionX += anInt1101 + ((i - cameraPositionX) * anInt1102) / 1000;
            if(cameraPositionX > i)
                cameraPositionX = i;
        }
        if(cameraPositionX > i)
        {
            cameraPositionX -= anInt1101 + ((cameraPositionX - i) * anInt1102) / 1000;
            if(cameraPositionX < i)
                cameraPositionX = i;
        }
        if(cameraPositionZ < k)
        {
            cameraPositionZ += anInt1101 + ((k - cameraPositionZ) * anInt1102) / 1000;
            if(cameraPositionZ > k)
                cameraPositionZ = k;
        }
        if(cameraPositionZ > k)
        {
            cameraPositionZ -= anInt1101 + ((cameraPositionZ - k) * anInt1102) / 1000;
            if(cameraPositionZ < k)
                cameraPositionZ = k;
        }
        if(cameraPositionY < j)
        {
            cameraPositionY += anInt1101 + ((j - cameraPositionY) * anInt1102) / 1000;
            if(cameraPositionY > j)
                cameraPositionY = j;
        }
        if(cameraPositionY > j)
        {
            cameraPositionY -= anInt1101 + ((cameraPositionY - j) * anInt1102) / 1000;
            if(cameraPositionY < j)
                cameraPositionY = j;
        }
        i = anInt995 * 128 + 64;
        j = anInt996 * 128 + 64;
        k = getFloorDrawHeight(plane, j, i) - cameraOffsetZ;
        int l = i - cameraPositionX;
        int i1 = k - cameraPositionZ;
        int j1 = j - cameraPositionY;
        int k1 = (int)Math.sqrt(l * l + j1 * j1);
        int l1 = (int)(Math.atan2(i1, k1) * 325.94900000000001D) & 0x7ff;
        int i2 = (int)(Math.atan2(l, j1) * -325.94900000000001D) & 0x7ff;
        if(l1 < 128)
            l1 = 128;
        if(l1 > 383)
            l1 = 383;
        if(cameraCurveY < l1)
        {
            cameraCurveY += anInt998 + ((l1 - cameraCurveY) * anInt999) / 1000;
            if(cameraCurveY > l1)
                cameraCurveY = l1;
        }
        if(cameraCurveY > l1)
        {
            cameraCurveY -= anInt998 + ((cameraCurveY - l1) * anInt999) / 1000;
            if(cameraCurveY < l1)
                cameraCurveY = l1;
        }
        int j2 = i2 - cameraCurveZ;
        if(j2 > 1024)
            j2 -= 2048;
        if(j2 < -1024)
            j2 += 2048;
        if(j2 > 0)
        {
            cameraCurveZ += anInt998 + (j2 * anInt999) / 1000;
            cameraCurveZ &= 0x7ff;
        }
        if(j2 < 0)
        {
            cameraCurveZ -= anInt998 + (-j2 * anInt999) / 1000;
            cameraCurveZ &= 0x7ff;
        }
        int k2 = i2 - cameraCurveZ;
        if(k2 > 1024)
            k2 -= 2048;
        if(k2 < -1024)
            k2 += 2048;
        if(k2 < 0 && j2 > 0 || k2 > 0 && j2 < 0)
            cameraCurveZ = i2;
    }

    private void drawMenu()
    {
        int i = menuOffsetX;
        int j = menuOffsetY;
        int k = menuWidth;
        int l = menuHeight;
        int i1 = 0x5d5447;
        DrawingArea.method336(l, j, i, i1, k);
        DrawingArea.method336(16, j + 1, i + 1, 0, k - 2);
        DrawingArea.fillPixels(i + 1, k - 2, l - 19, 0, j + 18);
        chatTextDrawingArea.drawTextHLeftVTop("Choose Option", i + 3, j + 14, i1);
        int j1 = super.mouseX;
        int k1 = super.mouseY;
        if(menuScreenArea == 0)
        {
            j1 -= 4;
            k1 -= 4;
        }
        if(menuScreenArea == 1)
        {
            j1 -= 553;
            k1 -= 205;
        }
        if(menuScreenArea == 2)
        {
            j1 -= 17;
            k1 -= 357;
        }
        for(int l1 = 0; l1 < menuActionRow; l1++)
        {
            int i2 = j + 31 + (menuActionRow - 1 - l1) * 15;
            int j2 = 0xffffff;
            if(j1 > i && j1 < i + k && k1 > i2 - 13 && k1 < i2 + 3)
                j2 = 0xffff00;
            chatTextDrawingArea.drawShadowTextHLeftVTop(menuActionName[l1], i + 3, i2, j2, true);
        }

    }

    private void addFriend(long l)
    {
        try
        {
            if(l == 0L)
                return;
            if(friendsCount >= 100 && membershipStatus != 1)
            {
                pushMessage("Your friendlist is full. Max of 100 for free users, and 200 for members", 0, "");
                return;
            }
            if(friendsCount >= 200)
            {
                pushMessage("Your friendlist is full. Max of 100 for free users, and 200 for members", 0, "");
                return;
            }
            String s = TextClass.formatName(TextClass.nameForLong(l));
            for(int i = 0; i < friendsCount; i++)
                if(friendsListAsLongs[i] == l)
                {
                    pushMessage(s + " is already on your friend list", 0, "");
                    return;
                }
            for(int j = 0; j < ignoreCount; j++)
                if(ignoreListAsLongs[j] == l)
                {
                    pushMessage("Please remove " + s + " from your ignore list first", 0, "");
                    return;
                }

            if(s.equals(localPlayer.name))
            {
                return;
            } else
            {
                friendsList[friendsCount] = s;
                friendsListAsLongs[friendsCount] = l;
                friendsWorldIds[friendsCount] = 0;
                friendsCount++;
                needDrawTabArea = true;
                stream.putOpcode(188);
                stream.putLong(l);
                return;
            }
        }
        catch(RuntimeException runtimeexception)
        {
            signlink.reporterror("15283, " + (byte)68 + ", " + l + ", " + runtimeexception.toString());
        }
        throw new RuntimeException();
    }

    private int getFloorDrawHeight(int z, int y, int x)
    {
        int groundX = x >> 7;
        int groundY = y >> 7;
        if(groundX < 0 || groundY < 0 || groundX > 103 || groundY > 103)
            return 0;
        int groundZ = z;
        if(groundZ < 3 && (byteGroundArray[1][groundX][groundY] & 2) == 2)
            groundZ++;
        int k1 = x & 0x7f;
        int l1 = y & 0x7f;
        int i2 = intGroundArray[groundZ][groundX][groundY] * (128 - k1) + intGroundArray[groundZ][groundX + 1][groundY] * k1 >> 7;
        int j2 = intGroundArray[groundZ][groundX][groundY + 1] * (128 - k1) + intGroundArray[groundZ][groundX + 1][groundY + 1] * k1 >> 7;
        
        return i2 * (128 - l1) + j2 * l1 >> 7;
    }

    private static String intToKOrMil(int j)
    {
        if(j < 0x186a0)
            return String.valueOf(j);
        if(j < 0x989680)
            return j / 1000 + "K";
        else
            return j / 0xf4240 + "M";
    }

    private void logout()
    {
        try
        {
            if(socket != null)
                socket.close();
        }
        catch(Exception _ex) { }
        socket = null;
        loggedIn = false;
        loginScreenState = 0;
 //       myUsername = "";
 //       myPassword = "";
        resetModelCaches();
        worldController.initToNull();
        for(int i = 0; i < 4; i++)
            currentCollisionMap[i].reset();

        System.gc();
        stopMidi();
        currentSong = -1;
        nextSong = -1;
        prevSong = 0;
    }

    private void method45()
    {
        aBoolean1031 = true;
        for(int j = 0; j < 7; j++)
        {
            anIntArray1065[j] = -1;
            for(int k = 0; k < IDK.length; k++)
            {
                if(IDK.cache[k].aBoolean662 || IDK.cache[k].anInt657 != j + (aBoolean1047 ? 0 : 7))
                    continue;
                anIntArray1065[j] = k;
                break;
            }

        }

    }

    private void updateNPCList(int amount, Stream stream)
    {
        while(stream.bitPosition + 21 < amount * 8)
        {
            int npcId = stream.readBits(14);
            if(npcId == 16383)
                break;
            if(npcArray[npcId] == null)
                npcArray[npcId] = new NPC();
            NPC npc = npcArray[npcId];
            npcIndices[npcCount++] = npcId;
            npc.lastUpdateTime = loopCycle;
            int y = stream.readBits(5);
            if(y > 15)
                y -= 32;
            int x = stream.readBits(5);
            if(x > 15)
                x -= 32;
            int clearWaypointQueue = stream.readBits(1);
            npc.npcDefinition = EntityDef.forID(stream.readBits(12));
            int furtherUpdateRequired = stream.readBits(1);
            if(furtherUpdateRequired == 1)
                playersAwaitingUpdate[actorsAwaitingUpdatePointer++] = npcId;
            npc.boundaryDimension = npc.npcDefinition.boundaryDimension;
            npc.degreesToTurn = npc.npcDefinition.degreesToTurn;
            npc.walkAnimationId = npc.npcDefinition.walkAnimationId;
            npc.turnAboutAnimationId = npc.npcDefinition.turnAboutAnimationId;
            npc.turnRightAnimationId = npc.npcDefinition.turnRightAnimationId;
            npc.turnLeftAnimationId = npc.npcDefinition.turnLeftAnimationId;
            npc.standAnimationId = npc.npcDefinition.standAnimationId;
            npc.setPos(localPlayer.smallX[0] + x, localPlayer.smallY[0] + y, clearWaypointQueue == 1);
        }
        stream.finishBitAccess();
    }

    public void processGameLoop()
    {
        if(rsAlreadyLoaded || loadingError || genericLoadingError)
            return;
        loopCycle++;
        if(!loggedIn)
            processLoginScreenInput();
        else
            mainGameProcessor();
        processOnDemandQueue();
    }

    private void method47(boolean flag)
    {
        if(localPlayer.x >> 7 == destX && localPlayer.y >> 7 == destY)
            destX = 0;
        int j = playerCount;
        if(flag)
            j = 1;
        for(int l = 0; l < j; l++)
        {
            Player player;
            int i1;
            if(flag)
            {
                player = localPlayer;
                i1 = localPlayerId << 14;
            } else
            {
                player = playerArray[playerId[l]];
                i1 = playerId[l] << 14;
            }
            if(player == null || !player.isVisible())
                continue;
            player.aBoolean1699 = (lowMemory && playerCount > 50 || playerCount > 200) && !flag && player.anInt1517 == player.standAnimationId;
            int j1 = player.x >> 7;
            int k1 = player.y >> 7;
            if(j1 < 0 || j1 >= 104 || k1 < 0 || k1 >= 104)
                continue;
            if(player.playerModel != null && loopCycle >= player.anInt1707 && loopCycle < player.anInt1708)
            {
                player.aBoolean1699 = false;
                player.anInt1709 = getFloorDrawHeight(plane, player.y, player.x);
                worldController.addEntity(player.anInt1719, player.anInt1720, plane, player.x, player.y, player.anInt1709, player.currentRotation, player.anInt1722, player.anInt1721, player, i1);
                continue;
            }
            if((player.x & 0x7f) == 64 && (player.y & 0x7f) == 64)
            {
                if(anIntArrayArray929[j1][k1] == anInt1265)
                    continue;
                anIntArrayArray929[j1][k1] = anInt1265;
            }
            player.anInt1709 = getFloorDrawHeight(plane, player.y, player.x);
            worldController.addEntityA(plane, player.x, player.y, player.anInt1709, player.currentRotation, player, i1, 60, player.aBoolean1541);
        }

    }

    private boolean promptUserForInput(RSInterface class9)
    {
        int j = class9.contentType;
        if(friendListStatus == 2)
        {
            if(j == 201)
            {
                inputTaken = true;
                inputDialogState = 0;
                messagePromptRaised = true;
                promptInput = "";
                friendsListAction = 1;
                aString1121 = "Enter name of friend to add to list";
            }
            if(j == 202)
            {
                inputTaken = true;
                inputDialogState = 0;
                messagePromptRaised = true;
                promptInput = "";
                friendsListAction = 2;
                aString1121 = "Enter name of friend to delete from list";
            }
        }
        if(j == 205)
        {
            anInt1011 = 250;
            return true;
        }
        if(j == 501)
        {
            inputTaken = true;
            inputDialogState = 0;
            messagePromptRaised = true;
            promptInput = "";
            friendsListAction = 4;
            aString1121 = "Enter name of player to add to list";
        }
        if(j == 502)
        {
            inputTaken = true;
            inputDialogState = 0;
            messagePromptRaised = true;
            promptInput = "";
            friendsListAction = 5;
            aString1121 = "Enter name of player to delete from list";
        }
        if(j >= 300 && j <= 313)
        {
            int k = (j - 300) / 2;
            int j1 = j & 1;
            int i2 = anIntArray1065[k];
            if(i2 != -1)
            {
                do
                {
                    if(j1 == 0 && --i2 < 0)
                        i2 = IDK.length - 1;
                    if(j1 == 1 && ++i2 >= IDK.length)
                        i2 = 0;
                } while(IDK.cache[i2].aBoolean662 || IDK.cache[i2].anInt657 != k + (aBoolean1047 ? 0 : 7));
                anIntArray1065[k] = i2;
                aBoolean1031 = true;
            }
        }
        if(j >= 314 && j <= 323)
        {
            int l = (j - 314) / 2;
            int k1 = j & 1;
            int j2 = anIntArray990[l];
            if(k1 == 0 && --j2 < 0)
                j2 = anIntArrayArray1003[l].length - 1;
            if(k1 == 1 && ++j2 >= anIntArrayArray1003[l].length)
                j2 = 0;
            anIntArray990[l] = j2;
            aBoolean1031 = true;
        }
        if(j == 324 && !aBoolean1047)
        {
            aBoolean1047 = true;
            method45();
        }
        if(j == 325 && aBoolean1047)
        {
            aBoolean1047 = false;
            method45();
        }
        if(j == 326)
        {
            stream.putOpcode(101);
            stream.put(aBoolean1047 ? 0 : 1);
            for(int i1 = 0; i1 < 7; i1++)
                stream.put(anIntArray1065[i1]);

            for(int l1 = 0; l1 < 5; l1++)
                stream.put(anIntArray990[l1]);

            return true;
        }
        if(j == 613)
            canMute = !canMute;
        if(j >= 601 && j <= 612)
        {
            clearTopInterfaces();
            if(reportAbuseInput.length() > 0)
            {
                stream.putOpcode(218);
                stream.putLong(TextClass.longForName(reportAbuseInput));
                stream.put(j - 601);
                stream.put(canMute ? 1 : 0);
            }
        }
        return false;
    }

    private void updatePlayersBlock(Stream stream)
    {
        for(int p = 0; p < actorsAwaitingUpdatePointer; p++)
        {
            int pId = playersAwaitingUpdate[p];
            Player player = playerArray[pId];
            int updateType = stream.getUnsignedByte();
            if((updateType & 0x40) != 0)
                updateType += stream.getUnsignedByte() << 8;
            updatePlayer(stream, updateType, player, pId);
        }

    }

    private void method50(int i, int k, int l, int i1, int j1)
    {
        int k1 = worldController.getWallObjectUID(l, i, j1);
        if(k1 != 0)
        {
            int l1 = worldController.getConfig(k1, l, i, j1);
            int k2 = l1 >> 6 & 3;
            int i3 = l1 & 0x1f;
            int k3 = k;
            if(k1 > 0)
                k3 = i1;
            int ai[] = aClass30_Sub2_Sub1_Sub1_1263.myPixels;
            int k4 = 24624 + l * 4 + (103 - i) * 512 * 4;
            int i5 = k1 >> 14 & 0x7fff;
            ObjectDef class46_2 = ObjectDef.forID(i5);
            if(class46_2.anInt758 != -1)
            {
                Background background_2 = mapScenes[class46_2.anInt758];
                if(background_2 != null)
                {
                    int i6 = (class46_2.sizeX * 4 - background_2.imageWidth) / 2;
                    int j6 = (class46_2.sizeY * 4 - background_2.anInt1453) / 2;
                    background_2.method361(48 + l * 4 + i6, 48 + (104 - i - class46_2.sizeY) * 4 + j6);
                }
            } else
            {
                if(i3 == 0 || i3 == 2)
                    if(k2 == 0)
                    {
                        ai[k4] = k3;
                        ai[k4 + 512] = k3;
                        ai[k4 + 1024] = k3;
                        ai[k4 + 1536] = k3;
                    } else
                    if(k2 == 1)
                    {
                        ai[k4] = k3;
                        ai[k4 + 1] = k3;
                        ai[k4 + 2] = k3;
                        ai[k4 + 3] = k3;
                    } else
                    if(k2 == 2)
                    {
                        ai[k4 + 3] = k3;
                        ai[k4 + 3 + 512] = k3;
                        ai[k4 + 3 + 1024] = k3;
                        ai[k4 + 3 + 1536] = k3;
                    } else
                    if(k2 == 3)
                    {
                        ai[k4 + 1536] = k3;
                        ai[k4 + 1536 + 1] = k3;
                        ai[k4 + 1536 + 2] = k3;
                        ai[k4 + 1536 + 3] = k3;
                    }
                if(i3 == 3)
                    if(k2 == 0)
                        ai[k4] = k3;
                    else
                    if(k2 == 1)
                        ai[k4 + 3] = k3;
                    else
                    if(k2 == 2)
                        ai[k4 + 3 + 1536] = k3;
                    else
                    if(k2 == 3)
                        ai[k4 + 1536] = k3;
                if(i3 == 2)
                    if(k2 == 3)
                    {
                        ai[k4] = k3;
                        ai[k4 + 512] = k3;
                        ai[k4 + 1024] = k3;
                        ai[k4 + 1536] = k3;
                    } else
                    if(k2 == 0)
                    {
                        ai[k4] = k3;
                        ai[k4 + 1] = k3;
                        ai[k4 + 2] = k3;
                        ai[k4 + 3] = k3;
                    } else
                    if(k2 == 1)
                    {
                        ai[k4 + 3] = k3;
                        ai[k4 + 3 + 512] = k3;
                        ai[k4 + 3 + 1024] = k3;
                        ai[k4 + 3 + 1536] = k3;
                    } else
                    if(k2 == 2)
                    {
                        ai[k4 + 1536] = k3;
                        ai[k4 + 1536 + 1] = k3;
                        ai[k4 + 1536 + 2] = k3;
                        ai[k4 + 1536 + 3] = k3;
                    }
            }
        }
        k1 = worldController.getInteractibleObjectUID(l, i, j1);
        if(k1 != 0)
        {
            int i2 = worldController.getConfig(k1, l, i, j1);
            int l2 = i2 >> 6 & 3;
            int j3 = i2 & 0x1f;
            int l3 = k1 >> 14 & 0x7fff;
            ObjectDef class46_1 = ObjectDef.forID(l3);
            if(class46_1.anInt758 != -1)
            {
                Background background_1 = mapScenes[class46_1.anInt758];
                if(background_1 != null)
                {
                    int j5 = (class46_1.sizeX * 4 - background_1.imageWidth) / 2;
                    int k5 = (class46_1.sizeY * 4 - background_1.anInt1453) / 2;
                    background_1.method361(48 + l * 4 + j5, 48 + (104 - i - class46_1.sizeY) * 4 + k5);
                }
            } else
            if(j3 == 9)
            {
                int l4 = 0xeeeeee;
                if(k1 > 0)
                    l4 = 0xee0000;
                int ai1[] = aClass30_Sub2_Sub1_Sub1_1263.myPixels;
                int l5 = 24624 + l * 4 + (103 - i) * 512 * 4;
                if(l2 == 0 || l2 == 2)
                {
                    ai1[l5 + 1536] = l4;
                    ai1[l5 + 1024 + 1] = l4;
                    ai1[l5 + 512 + 2] = l4;
                    ai1[l5 + 3] = l4;
                } else
                {
                    ai1[l5] = l4;
                    ai1[l5 + 512 + 1] = l4;
                    ai1[l5 + 1024 + 2] = l4;
                    ai1[l5 + 1536 + 3] = l4;
                }
            }
        }
        k1 = worldController.getGroundDecorationUID(l, i, j1);
        if(k1 != 0)
        {
            int j2 = k1 >> 14 & 0x7fff;
            ObjectDef class46 = ObjectDef.forID(j2);
            if(class46.anInt758 != -1)
            {
                Background background = mapScenes[class46.anInt758];
                if(background != null)
                {
                    int i4 = (class46.sizeX * 4 - background.imageWidth) / 2;
                    int j4 = (class46.sizeY * 4 - background.anInt1453) / 2;
                    background.method361(48 + l * 4 + i4, 48 + (104 - i - class46.sizeY) * 4 + j4);
                }
            }
        }
    }

    private void loadTitleScreen()
    {
        aBackground_966 = new Background(titleStreamLoader, "titlebox", 0);
        aBackground_967 = new Background(titleStreamLoader, "titlebutton", 0);
        aBackgroundArray1152s = new Background[12];
        int j = 0;
        try
        {
            j = Integer.parseInt(getParameter("fl_icon"));
        }
        catch(Exception _ex) { }
        if(j == 0)
        {
            for(int k = 0; k < 12; k++)
                aBackgroundArray1152s[k] = new Background(titleStreamLoader, "runes", k);

        } else
        {
            for(int l = 0; l < 12; l++)
                aBackgroundArray1152s[l] = new Background(titleStreamLoader, "runes", 12 + (l & 3));

        }
        aClass30_Sub2_Sub1_Sub1_1201 = new Sprite(128, 265);
        aClass30_Sub2_Sub1_Sub1_1202 = new Sprite(128, 265);
        System.arraycopy(aRSImageProducer_1110.anIntArray315, 0, aClass30_Sub2_Sub1_Sub1_1201.myPixels, 0, 33920);

        System.arraycopy(aRSImageProducer_1111.anIntArray315, 0, aClass30_Sub2_Sub1_Sub1_1202.myPixels, 0, 33920);

        anIntArray851 = new int[256];
        for(int k1 = 0; k1 < 64; k1++)
            anIntArray851[k1] = k1 * 0x40000;

        for(int l1 = 0; l1 < 64; l1++)
            anIntArray851[l1 + 64] = 0xff0000 + 1024 * l1;

        for(int i2 = 0; i2 < 64; i2++)
            anIntArray851[i2 + 128] = 0xffff00 + 4 * i2;

        for(int j2 = 0; j2 < 64; j2++)
            anIntArray851[j2 + 192] = 0xffffff;

        anIntArray852 = new int[256];
        for(int k2 = 0; k2 < 64; k2++)
            anIntArray852[k2] = k2 * 1024;

        for(int l2 = 0; l2 < 64; l2++)
            anIntArray852[l2 + 64] = 65280 + 4 * l2;

        for(int i3 = 0; i3 < 64; i3++)
            anIntArray852[i3 + 128] = 65535 + 0x40000 * i3;

        for(int j3 = 0; j3 < 64; j3++)
            anIntArray852[j3 + 192] = 0xffffff;

        anIntArray853 = new int[256];
        for(int k3 = 0; k3 < 64; k3++)
            anIntArray853[k3] = k3 * 4;

        for(int l3 = 0; l3 < 64; l3++)
            anIntArray853[l3 + 64] = 255 + 0x40000 * l3;

        for(int i4 = 0; i4 < 64; i4++)
            anIntArray853[i4 + 128] = 0xff00ff + 1024 * i4;

        for(int j4 = 0; j4 < 64; j4++)
            anIntArray853[j4 + 192] = 0xffffff;

        anIntArray850 = new int[256];
        anIntArray1190 = new int[32768];
        anIntArray1191 = new int[32768];
        randomizeBackground(null);
        anIntArray828 = new int[32768];
        anIntArray829 = new int[32768];
        drawLoadingText(10, "Connecting to fileserver");
        if(!currentlyDrawingFlames)
        {
            drawFlames = true;
            currentlyDrawingFlames = true;
            startRunnable(this, 2);
        }
    }

    private static void setHighMem()
    {
        WorldController.lowMemory = false;
        Texture.lowMem = false;
        lowMemory = false;
        ObjectManager.lowMem = false;
        ObjectDef.lowMem = false;
    }

    public static void main(String args[])
    {
        try
        {
            System.out.println("RS2 user client - release #" + 317);
            if(args.length != 5)
            {
                System.out.println("Usage: node-id, port-offset, [lowmem/highmem], [free/members], storeid");
                return;
            }
            nodeID = Integer.parseInt(args[0]);
            portOffset = Integer.parseInt(args[1]);
            if(args[2].equals("lowmem"))
                setLowMem();
            else
            if(args[2].equals("highmem"))
            {
                setHighMem();
            } else
            {
                System.out.println("Usage: node-id, port-offset, [lowmem/highmem], [free/members], storeid");
                return;
            }
            if(args[3].equals("free"))
                isMembers = false;
            else
            if(args[3].equals("members"))
            {
                isMembers = true;
            } else
            {
                System.out.println("Usage: node-id, port-offset, [lowmem/highmem], [free/members], storeid");
                return;
            }
            signlink.storeid = Integer.parseInt(args[4]);
            signlink.startpriv(InetAddress.getLocalHost());
            client client1 = new client();
            client1.createClientFrame(765, 503);
        }
        catch(Exception exception)
        {
        }
    }

    private void loadingStages()
    {
        if(lowMemory && loadingStage == 2 && ObjectManager.anInt131 != plane)
        {
            gameScreenCanvas.initDrawingArea();
            plainFont.drawTextHMidVTop("Loading - please wait.", 257, 151, 0);
            plainFont.drawTextHMidVTop("Loading - please wait.", 256, 150, 0xffffff);
            gameScreenCanvas.drawGraphics(4, super.gameGraphics, 4);
            loadingStage = 1;
            aLong824 = System.currentTimeMillis();
        }
        if(loadingStage == 1)
        {
            int j = method54();
            if(j != 0 && System.currentTimeMillis() - aLong824 > 0x57e40L)
            {
                signlink.reporterror(myUsername + " glcfb " + serverSessionKey + "," + j + "," + lowMemory + "," + decompressors[0] + "," + onDemandFetcher.getNodeCount() + "," + plane + "," + regionX + "," + regionY);
                aLong824 = System.currentTimeMillis();
            }
        }
        if(loadingStage == 2 && plane != lastRegionId)
        {
            lastRegionId = plane;
            method24(plane);
        }
    }

    private int method54()
    {
        for(int i = 0; i < terrainData.length; i++)
        {
            if(terrainData[i] == null && anIntArray1235[i] != -1)
                return -1;
            if(objectData[i] == null && anIntArray1236[i] != -1)
                return -2;
        }

        boolean flag = true;
        for(int j = 0; j < terrainData.length; j++)
        {
            byte abyte0[] = objectData[j];
            if(abyte0 != null)
            {
                int k = (mapCoordinates[j] >> 8) * 64 - baseX;
                int l = (mapCoordinates[j] & 0xff) * 64 - baseY;
                if(loadGeneratedMap)
                {
                    k = 10;
                    l = 10;
                }
                flag &= ObjectManager.method189(k, abyte0, l);
            }
        }

        if(!flag)
            return -3;
        if(loadingMap)
        {
            return -4;
        } else
        {
            loadingStage = 2;
            ObjectManager.anInt131 = plane;
            loadRegion();
            stream.putOpcode(121);
            return 0;
        }
    }

    private void method55()
    {
        for(Projectile class30_sub2_sub4_sub4 = (Projectile)projectileQueue.reverseGetFirst(); class30_sub2_sub4_sub4 != null; class30_sub2_sub4_sub4 = (Projectile)projectileQueue.reverseGetNext())
            if(class30_sub2_sub4_sub4.anInt1597 != plane || loopCycle > class30_sub2_sub4_sub4.anInt1572)
                class30_sub2_sub4_sub4.unlink();
            else
            if(loopCycle >= class30_sub2_sub4_sub4.anInt1571)
            {
                if(class30_sub2_sub4_sub4.anInt1590 > 0)
                {
                    NPC npc = npcArray[class30_sub2_sub4_sub4.anInt1590 - 1];
                    if(npc != null && npc.x >= 0 && npc.x < 13312 && npc.y >= 0 && npc.y < 13312)
                        class30_sub2_sub4_sub4.trackTarget(loopCycle, npc.y, getFloorDrawHeight(class30_sub2_sub4_sub4.anInt1597, npc.y, npc.x) - class30_sub2_sub4_sub4.anInt1583, npc.x);
                }
                if(class30_sub2_sub4_sub4.anInt1590 < 0)
                {
                    int j = -class30_sub2_sub4_sub4.anInt1590 - 1;
                    Player player;
                    if(j == playerListId)
                        player = localPlayer;
                    else
                        player = playerArray[j];
                    if(player != null && player.x >= 0 && player.x < 13312 && player.y >= 0 && player.y < 13312)
                        class30_sub2_sub4_sub4.trackTarget(loopCycle, player.y, getFloorDrawHeight(class30_sub2_sub4_sub4.anInt1597, player.y, player.x) - class30_sub2_sub4_sub4.anInt1583, player.x);
                }
                class30_sub2_sub4_sub4.method456(animationTimePassed);
                worldController.addEntityA(plane, (int)class30_sub2_sub4_sub4.aDouble1585, (int)class30_sub2_sub4_sub4.aDouble1586, (int)class30_sub2_sub4_sub4.aDouble1587, class30_sub2_sub4_sub4.anInt1595, class30_sub2_sub4_sub4, -1, 60, false);
            }

    }

    public AppletContext getAppletContext()
    {
        if(signlink.mainapp != null)
            return signlink.mainapp.getAppletContext();
        else
            return super.getAppletContext();
    }

    private void drawLogo()
    {
        byte abyte0[] = titleStreamLoader.getDataForName("title.dat");
        Sprite sprite = new Sprite(abyte0, this);
        aRSImageProducer_1110.initDrawingArea();
        sprite.method346(0, 0);
        aRSImageProducer_1111.initDrawingArea();
        sprite.method346(-637, 0);
        aRSImageProducer_1107.initDrawingArea();
        sprite.method346(-128, 0);
        aRSImageProducer_1108.initDrawingArea();
        sprite.method346(-202, -371);
        aRSImageProducer_1109.initDrawingArea();
        sprite.method346(-202, -171);
        aRSImageProducer_1112.initDrawingArea();
        sprite.method346(0, -265);
        aRSImageProducer_1113.initDrawingArea();
        sprite.method346(-562, -265);
        aRSImageProducer_1114.initDrawingArea();
        sprite.method346(-128, -171);
        aRSImageProducer_1115.initDrawingArea();
        sprite.method346(-562, -171);
        int ai[] = new int[sprite.myWidth];
        for(int j = 0; j < sprite.myHeight; j++)
        {
            for(int k = 0; k < sprite.myWidth; k++)
                ai[k] = sprite.myPixels[(sprite.myWidth - k - 1) + sprite.myWidth * j];

            System.arraycopy(ai, 0, sprite.myPixels, sprite.myWidth * j, sprite.myWidth);

        }

        aRSImageProducer_1110.initDrawingArea();
        sprite.method346(382, 0);
        aRSImageProducer_1111.initDrawingArea();
        sprite.method346(-255, 0);
        aRSImageProducer_1107.initDrawingArea();
        sprite.method346(254, 0);
        aRSImageProducer_1108.initDrawingArea();
        sprite.method346(180, -371);
        aRSImageProducer_1109.initDrawingArea();
        sprite.method346(180, -171);
        aRSImageProducer_1112.initDrawingArea();
        sprite.method346(382, -265);
        aRSImageProducer_1113.initDrawingArea();
        sprite.method346(-180, -265);
        aRSImageProducer_1114.initDrawingArea();
        sprite.method346(254, -171);
        aRSImageProducer_1115.initDrawingArea();
            sprite.method346(-180, -171);
            sprite = new Sprite(titleStreamLoader, "logo", 0);
            aRSImageProducer_1107.initDrawingArea();
            sprite.drawSprite(382 - sprite.myWidth / 2 - 128, 18);
            sprite = null;
            Object obj = null;
            Object obj1 = null;
            System.gc();

    }

    private void processOnDemandQueue()
    {
        do
        {
            OnDemandData onDemandData;
            do
            {
                onDemandData = onDemandFetcher.getNextNode();
                if(onDemandData == null)
                    return;
                if(onDemandData.dataType == 0)
                {
                    Model.loadModelHeader(onDemandData.buffer, onDemandData.id);
                    if((onDemandFetcher.getModelIndex(onDemandData.id) & 0x62) != 0)
                    {
                        needDrawTabArea = true;
                        if(backDialogID != -1)
                            inputTaken = true;
                    }
                }
                if(onDemandData.dataType == 1 && onDemandData.buffer != null)
                    Class36.method529(onDemandData.buffer);
                if(onDemandData.dataType == 2 && onDemandData.id == nextSong && onDemandData.buffer != null)
                    saveMidi(songChanging, onDemandData.buffer);
                if(onDemandData.dataType == 3 && loadingStage == 1)
                {
                    for(int i = 0; i < terrainData.length; i++)
                    {
                        if(anIntArray1235[i] == onDemandData.id)
                        {
                            terrainData[i] = onDemandData.buffer;
                            if(onDemandData.buffer == null)
                                anIntArray1235[i] = -1;
                            break;
                        }
                        if(anIntArray1236[i] != onDemandData.id)
                            continue;
                        objectData[i] = onDemandData.buffer;
                        if(onDemandData.buffer == null)
                            anIntArray1236[i] = -1;
                        break;
                    }

                }
            } while(onDemandData.dataType != 93 || !onDemandFetcher.method564(onDemandData.id));
            ObjectManager.method173(new Stream(onDemandData.buffer), onDemandFetcher);
        } while(true);
    }

    private void calcFlamesPosition()
    {
        char c = '\u0100';
        for(int j = 10; j < 117; j++)
        {
            int k = (int)(Math.random() * 100D);
            if(k < 50)
                anIntArray828[j + (c - 2 << 7)] = 255;
        }
        for(int l = 0; l < 100; l++)
        {
            int i1 = (int)(Math.random() * 124D) + 2;
            int k1 = (int)(Math.random() * 128D) + 128;
            int k2 = i1 + (k1 << 7);
            anIntArray828[k2] = 192;
        }

        for(int j1 = 1; j1 < c - 1; j1++)
        {
            for(int l1 = 1; l1 < 127; l1++)
            {
                int l2 = l1 + (j1 << 7);
                anIntArray829[l2] = (anIntArray828[l2 - 1] + anIntArray828[l2 + 1] + anIntArray828[l2 - 128] + anIntArray828[l2 + 128]) / 4;
            }

        }

        anInt1275 += 128;
        if(anInt1275 > anIntArray1190.length)
        {
            anInt1275 -= anIntArray1190.length;
            int i2 = (int)(Math.random() * 12D);
            randomizeBackground(aBackgroundArray1152s[i2]);
        }
        for(int j2 = 1; j2 < c - 1; j2++)
        {
            for(int i3 = 1; i3 < 127; i3++)
            {
                int k3 = i3 + (j2 << 7);
                int i4 = anIntArray829[k3 + 128] - anIntArray1190[k3 + anInt1275 & anIntArray1190.length - 1] / 5;
                if(i4 < 0)
                    i4 = 0;
                anIntArray828[k3] = i4;
            }

        }

        System.arraycopy(anIntArray969, 1, anIntArray969, 0, c - 1);

        anIntArray969[c - 1] = (int)(Math.sin((double)loopCycle / 14D) * 16D + Math.sin((double)loopCycle / 15D) * 14D + Math.sin((double)loopCycle / 16D) * 12D);
        if(anInt1040 > 0)
            anInt1040 -= 4;
        if(anInt1041 > 0)
            anInt1041 -= 4;
        if(anInt1040 == 0 && anInt1041 == 0)
        {
            int l3 = (int)(Math.random() * 2000D);
            if(l3 == 0)
                anInt1040 = 1024;
            if(l3 == 1)
                anInt1041 = 1024;
        }
    }

    private boolean saveWave(byte abyte0[], int i)
    {
        return abyte0 == null || signlink.wavesave(abyte0, i);
    }

    private void loadInterface(int i)
    {
        RSInterface rsInterface = RSInterface.interfaceCache[i];
        for(int j = 0; j < rsInterface.children.length; j++)
        {
            if(rsInterface.children[j] == -1)
                break;
            RSInterface child = RSInterface.interfaceCache[rsInterface.children[j]];
            if(child.type == 1)
                loadInterface(child.id);
            child.animationFrame = 0;
            child.animationDuration = 0;
        }
    }

    private void drawHeadIcon()
    {
        if(hintIconType != 2)
            return;
        calcEntityScreenPos((hintIconX - baseX << 7) + hintIconDrawTileX, hintIconDrawHeight * 2, (hintIconY - baseY << 7) + hintIconDrawTileY);
        if(spriteDrawX > -1 && loopCycle % 20 < 10)
            headIcons[2].drawSprite(spriteDrawX - 12, spriteDrawY - 28);
    }

    private void mainGameProcessor()
    {
        if(systemUpdateTime > 1)
            systemUpdateTime--;
        if(anInt1011 > 0)
            anInt1011--;
        for(int j = 0; j < 5; j++)
            if(!parsePacket())
                break;

        if(!loggedIn)
            return;
        synchronized(mouseDetection.syncObject)
        {
            if(flagged)
            {
                if(super.clickType != 0 || mouseDetection.coordsIndex >= 40)
                {
                    stream.putOpcode(45);
                    stream.put(0);
                    int j2 = stream.currentOffset;
                    int j3 = 0;
                    for(int j4 = 0; j4 < mouseDetection.coordsIndex; j4++)
                    {
                        if(j2 - stream.currentOffset >= 240)
                            break;
                        j3++;
                        int l4 = mouseDetection.coordsY[j4];
                        if(l4 < 0)
                            l4 = 0;
                        else
                        if(l4 > 502)
                            l4 = 502;
                        int k5 = mouseDetection.coordsX[j4];
                        if(k5 < 0)
                            k5 = 0;
                        else
                        if(k5 > 764)
                            k5 = 764;
                        int i6 = l4 * 765 + k5;
                        if(mouseDetection.coordsY[j4] == -1 && mouseDetection.coordsX[j4] == -1)
                        {
                            k5 = -1;
                            l4 = -1;
                            i6 = 0x7ffff;
                        }
                        if(k5 == anInt1237 && l4 == anInt1238)
                        {
                            if(anInt1022 < 2047)
                                anInt1022++;
                        } else
                        {
                            int j6 = k5 - anInt1237;
                            anInt1237 = k5;
                            int k6 = l4 - anInt1238;
                            anInt1238 = l4;
                            if(anInt1022 < 8 && j6 >= -32 && j6 <= 31 && k6 >= -32 && k6 <= 31)
                            {
                                j6 += 32;
                                k6 += 32;
                                stream.putShort((anInt1022 << 12) + (j6 << 6) + k6);
                                anInt1022 = 0;
                            } else
                            if(anInt1022 < 8)
                            {
                                stream.writeDWordBigEndian(0x800000 + (anInt1022 << 19) + i6);
                                anInt1022 = 0;
                            } else
                            {
                                stream.putInt(0xc0000000 + (anInt1022 << 19) + i6);
                                anInt1022 = 0;
                            }
                        }
                    }

                    stream.putSizeByte(stream.currentOffset - j2);
                    if(j3 >= mouseDetection.coordsIndex)
                    {
                        mouseDetection.coordsIndex = 0;
                    } else
                    {
                        mouseDetection.coordsIndex -= j3;
                        for(int i5 = 0; i5 < mouseDetection.coordsIndex; i5++)
                        {
                            mouseDetection.coordsX[i5] = mouseDetection.coordsX[i5 + j3];
                            mouseDetection.coordsY[i5] = mouseDetection.coordsY[i5 + j3];
                        }

                    }
                }
            } else
            {
                mouseDetection.coordsIndex = 0;
            }
        }
        if(super.clickType != 0)
        {
            long l = (super.clickTime - aLong1220) / 50L;
            if(l > 4095L)
                l = 4095L;
            aLong1220 = super.clickTime;
            int k2 = super.clickY;
            if(k2 < 0)
                k2 = 0;
            else
            if(k2 > 502)
                k2 = 502;
            int k3 = super.clickX;
            if(k3 < 0)
                k3 = 0;
            else
            if(k3 > 764)
                k3 = 764;
            int k4 = k2 * 765 + k3;
            int j5 = 0;
            if(super.clickType == 2)
                j5 = 1;
            int l5 = (int)l;
            stream.putOpcode(241);
            stream.putInt((l5 << 20) + (j5 << 19) + k4);
        }
        if(anInt1016 > 0)
            anInt1016--;
        if(super.keyStatus[1] == 1 || super.keyStatus[2] == 1 || super.keyStatus[3] == 1 || super.keyStatus[4] == 1)
            aBoolean1017 = true;
        if(aBoolean1017 && anInt1016 <= 0)
        {
            anInt1016 = 20;
            aBoolean1017 = false;
            stream.putOpcode(86);
            stream.putShort(anInt1184);
            stream.putShortA(minimapInt1);
        }
        if(super.awtFocus && !aBoolean954)
        {
            aBoolean954 = true;
            stream.putOpcode(3);
            stream.put(1);
        }
        if(!super.awtFocus && aBoolean954)
        {
            aBoolean954 = false;
            stream.putOpcode(3);
            stream.put(0);
        }
        loadingStages();
        updateSpawnedObjects();
        method90();
        anInt1009++;
        if(anInt1009 > 750)
            dropClient();
        updatePlayerInstances();
        method95();
        method38();
        animationTimePassed++;
        if(crossType != 0)
        {
            crossIndex += 20;
            if(crossIndex >= 400)
                crossType = 0;
        }
        if(atInventoryInterfaceType != 0)
        {
            atInventoryLoopCycle++;
            if(atInventoryLoopCycle >= 15)
            {
                if(atInventoryInterfaceType == 2)
                    needDrawTabArea = true;
                if(atInventoryInterfaceType == 3)
                    inputTaken = true;
                atInventoryInterfaceType = 0;
            }
        }
        if(activeInterfaceType != 0)
        {
            anInt989++;
            if(super.mouseX > anInt1087 + 5 || super.mouseX < anInt1087 - 5 || super.mouseY > anInt1088 + 5 || super.mouseY < anInt1088 - 5)
                aBoolean1242 = true;
            if(super.mouseButton == 0)
            {
                if(activeInterfaceType == 2)
                    needDrawTabArea = true;
                if(activeInterfaceType == 3)
                    inputTaken = true;
                activeInterfaceType = 0;
                if(aBoolean1242 && anInt989 >= 5)
                {
                    lastActiveInvInterface = -1;
                    processRightClick();
                    if(lastActiveInvInterface == anInt1084 && mouseInvInterfaceIndex != anInt1085)
                    {
                        RSInterface class9 = RSInterface.interfaceCache[anInt1084];
                        int j1 = 0;
                        if(anInt913 == 1 && class9.contentType == 206)
                            j1 = 1;
                        if(class9.inventoryItemId[mouseInvInterfaceIndex] <= 0)
                            j1 = 0;
                        if(class9.aBoolean235)
                        {
                            int l2 = anInt1085;
                            int l3 = mouseInvInterfaceIndex;
                            class9.inventoryItemId[l3] = class9.inventoryItemId[l2];
                            class9.inventoryStackSize[l3] = class9.inventoryStackSize[l2];
                            class9.inventoryItemId[l2] = -1;
                            class9.inventoryStackSize[l2] = 0;
                        } else
                        if(j1 == 1)
                        {
                            int i3 = anInt1085;
                            for(int i4 = mouseInvInterfaceIndex; i3 != i4;)
                                if(i3 > i4)
                                {
                                    class9.swapInventoryItems(i3, i3 - 1);
                                    i3--;
                                } else
                                if(i3 < i4)
                                {
                                    class9.swapInventoryItems(i3, i3 + 1);
                                    i3++;
                                }

                        } else
                        {
                            class9.swapInventoryItems(anInt1085, mouseInvInterfaceIndex);
                        }
                        stream.putOpcode(214);
                        stream.putLEShortA(anInt1084);
                        stream.putByteC(j1);
                        stream.putLEShortA(anInt1085);
                        stream.putLEShort(mouseInvInterfaceIndex);
                    }
                } else
                if((anInt1253 == 1 || menuHasAddFriend(menuActionRow - 1)) && menuActionRow > 2)
                    processMenuHovering();
                else
                if(menuActionRow > 0)
                    doAction(menuActionRow - 1);
                atInventoryLoopCycle = 10;
                super.clickType = 0;
            }
        }
        if(WorldController.clickedTileX != -1)
        {
            int k = WorldController.clickedTileX;
            int k1 = WorldController.clickedTileY;
            boolean flag = doWalkTo(0, 0, 0, 0, localPlayer.smallY[0], 0, 0, k1, localPlayer.smallX[0], true, k);
            WorldController.clickedTileX = -1;
            if(flag)
            {
                crossX = super.clickX;
                crossY = super.clickY;
                crossType = 1;
                crossIndex = 0;
            }
        }
        if(super.clickType == 1 && aString844 != null)
        {
            aString844 = null;
            inputTaken = true;
            super.clickType = 0;
        }
        processMenuClick();
        processMinimapClick();
        processTabClick();
        processChatModeClick();
        if(super.mouseButton == 1 || super.clickType == 1)
            anInt1213++;
        if(loadingStage == 2)
            method108();
        if(loadingStage == 2 && inCutscene)
            calcCameraPos();
        for(int i1 = 0; i1 < 5; i1++)
            anIntArray1030[i1]++;

        manageTextInput();
        super.idleTime++;
        if(super.idleTime > 4500)
        {
            anInt1011 = 250;
            super.idleTime -= 500;
            stream.putOpcode(202);
        }
        anInt988++;
        if(anInt988 > 500)
        {
            anInt988 = 0;
            int l1 = (int)(Math.random() * 8D);
            if((l1 & 1) == 1)
                anInt1278 += anInt1279;
            if((l1 & 2) == 2)
                anInt1131 += anInt1132;
            if((l1 & 4) == 4)
                anInt896 += anInt897;
        }
        if(anInt1278 < -50)
            anInt1279 = 2;
        if(anInt1278 > 50)
            anInt1279 = -2;
        if(anInt1131 < -55)
            anInt1132 = 2;
        if(anInt1131 > 55)
            anInt1132 = -2;
        if(anInt896 < -40)
            anInt897 = 1;
        if(anInt896 > 40)
            anInt897 = -1;
        anInt1254++;
        if(anInt1254 > 500)
        {
            anInt1254 = 0;
            int i2 = (int)(Math.random() * 8D);
            if((i2 & 1) == 1)
                minimapRotation += anInt1210;
            if((i2 & 2) == 2)
                minimapZoom += anInt1171;
        }
        if(minimapRotation < -60)
            anInt1210 = 2;
        if(minimapRotation > 60)
            anInt1210 = -2;
        if(minimapZoom < -20)
            anInt1171 = 1;
        if(minimapZoom > 10)
            anInt1171 = -1;
        anInt1010++;
        if(anInt1010 > 50)
            stream.putOpcode(0);
        try
        {
            if(socket != null && stream.currentOffset > 0)
            {
                socket.write(stream.currentOffset, stream.buffer);
                stream.currentOffset = 0;
                anInt1010 = 0;
            }
        }
        catch(IOException _ex)
        {
            dropClient();
        }
        catch(Exception exception)
        {
            logout();
        }
    }

    private void clearObjectSpawnRequests()
    {
        GameObjectSpawnRequest spawnRequest = (GameObjectSpawnRequest)spawnObjectList.reverseGetFirst();
        for(; spawnRequest != null; spawnRequest = (GameObjectSpawnRequest)spawnObjectList.reverseGetNext())
            if(spawnRequest.delayUntilRespawn == -1)
            {
                spawnRequest.delayUntilSpawn = 0;
                configureSpawnRequest(spawnRequest);
            } else
            {
                spawnRequest.unlink();
            }

    }

    private void resetImageProducers()
    {
        if(aRSImageProducer_1107 != null)
            return;
        super.fullGameScreen = null;
        aRSImageProducer_1166 = null;
        aRSImageProducer_1164 = null;
        aRSImageProducer_1163 = null;
        gameScreenCanvas = null;
        aRSImageProducer_1123 = null;
        aRSImageProducer_1124 = null;
        aRSImageProducer_1125 = null;
        aRSImageProducer_1110 = new RSImageProducer(128, 265, getGameComponent());
        DrawingArea.setAllPixelsToZero();
        aRSImageProducer_1111 = new RSImageProducer(128, 265, getGameComponent());
        DrawingArea.setAllPixelsToZero();
        aRSImageProducer_1107 = new RSImageProducer(509, 171, getGameComponent());
        DrawingArea.setAllPixelsToZero();
        aRSImageProducer_1108 = new RSImageProducer(360, 132, getGameComponent());
        DrawingArea.setAllPixelsToZero();
        aRSImageProducer_1109 = new RSImageProducer(360, 200, getGameComponent());
        DrawingArea.setAllPixelsToZero();
        aRSImageProducer_1112 = new RSImageProducer(202, 238, getGameComponent());
        DrawingArea.setAllPixelsToZero();
        aRSImageProducer_1113 = new RSImageProducer(203, 238, getGameComponent());
        DrawingArea.setAllPixelsToZero();
        aRSImageProducer_1114 = new RSImageProducer(74, 94, getGameComponent());
        DrawingArea.setAllPixelsToZero();
        aRSImageProducer_1115 = new RSImageProducer(75, 94, getGameComponent());
        DrawingArea.setAllPixelsToZero();
        if(titleStreamLoader != null)
        {
            drawLogo();
            loadTitleScreen();
        }
        welcomeScreenRaised = true;
    }

    void drawLoadingText(int i, String s)
    {
        anInt1079 = i;
        aString1049 = s;
        resetImageProducers();
        if(titleStreamLoader == null)
        {
            super.drawLoadingText(i, s);
            return;
        }
        aRSImageProducer_1109.initDrawingArea();
        char c = '\u0168';
        char c1 = '\310';
        byte byte1 = 20;
        chatTextDrawingArea.drawTextHMidVTop("RuneScape is loading - please wait...", c / 2, c1 / 2 - 26 - byte1, 0xffffff);
        int j = c1 / 2 - 18 - byte1;
        DrawingArea.fillPixels(c / 2 - 152, 304, 34, 0x8c1111, j);
        DrawingArea.fillPixels(c / 2 - 151, 302, 32, 0, j + 1);
        DrawingArea.method336(30, j + 2, c / 2 - 150, 0x8c1111, i * 3);
        DrawingArea.method336(30, j + 2, (c / 2 - 150) + i * 3, 0, 300 - i * 3);
        chatTextDrawingArea.drawTextHMidVTop(s, c / 2, (c1 / 2 + 5) - byte1, 0xffffff);
        aRSImageProducer_1109.drawGraphics(171, super.gameGraphics, 202);
        if(welcomeScreenRaised)
        {
            welcomeScreenRaised = false;
            if(!currentlyDrawingFlames)
            {
                aRSImageProducer_1110.drawGraphics(0, super.gameGraphics, 0);
                aRSImageProducer_1111.drawGraphics(0, super.gameGraphics, 637);
            }
            aRSImageProducer_1107.drawGraphics(0, super.gameGraphics, 128);
            aRSImageProducer_1108.drawGraphics(371, super.gameGraphics, 202);
            aRSImageProducer_1112.drawGraphics(265, super.gameGraphics, 0);
            aRSImageProducer_1113.drawGraphics(265, super.gameGraphics, 562);
            aRSImageProducer_1114.drawGraphics(171, super.gameGraphics, 128);
            aRSImageProducer_1115.drawGraphics(171, super.gameGraphics, 562);
        }
    }

    private void method65(int i, int j, int k, int l, RSInterface class9, int i1, boolean flag,
                          int j1)
    {
        int anInt992;
        if(aBoolean972)
            anInt992 = 32;
        else
            anInt992 = 0;
        aBoolean972 = false;
        if(k >= i && k < i + 16 && l >= i1 && l < i1 + 16)
        {
            class9.scrollPosition -= anInt1213 * 4;
            if(flag)
            {
                needDrawTabArea = true;
            }
        } else
        if(k >= i && k < i + 16 && l >= (i1 + j) - 16 && l < i1 + j)
        {
            class9.scrollPosition += anInt1213 * 4;
            if(flag)
            {
                needDrawTabArea = true;
            }
        } else
        if(k >= i - anInt992 && k < i + 16 + anInt992 && l >= i1 + 16 && l < (i1 + j) - 16 && anInt1213 > 0)
        {
            int l1 = ((j - 32) * j) / j1;
            if(l1 < 8)
                l1 = 8;
            int i2 = l - i1 - 16 - l1 / 2;
            int j2 = j - 32 - l1;
            class9.scrollPosition = ((j1 - j) * i2) / j2;
            if(flag)
                needDrawTabArea = true;
            aBoolean972 = true;
        }
    }

    private boolean method66(int i, int j, int k)
    {
        int i1 = i >> 14 & 0x7fff;
        int j1 = worldController.getConfig(i, k, j, plane);
        if(j1 == -1)
            return false;
        int k1 = j1 & 0x1f;
        int l1 = j1 >> 6 & 3;
        if(k1 == 10 || k1 == 11 || k1 == 22)
        {
            ObjectDef class46 = ObjectDef.forID(i1);
            int i2;
            int j2;
            if(l1 == 0 || l1 == 2)
            {
                i2 = class46.sizeX;
                j2 = class46.sizeY;
            } else
            {
                i2 = class46.sizeY;
                j2 = class46.sizeX;
            }
            int k2 = class46.anInt768;
            if(l1 != 0)
                k2 = (k2 << l1 & 0xf) + (k2 >> 4 - l1);
            doWalkTo(2, 0, j2, 0, localPlayer.smallY[0], i2, k2, j, localPlayer.smallX[0], false, k);
        } else
        {
            doWalkTo(2, l1, 0, k1 + 1, localPlayer.smallY[0], 0, 0, j, localPlayer.smallX[0], false, k);
        }
        crossX = super.clickX;
        crossY = super.clickY;
        crossType = 2;
        crossIndex = 0;
        return true;
    }

    private StreamLoader streamLoaderForName(int i, String s, String s1, int j, int k)
    {
        byte abyte0[] = null;
        int l = 5;
        try
        {
            if(decompressors[0] != null)
                abyte0 = decompressors[0].decompress(i);
        }
        catch(Exception _ex) { }
        if(abyte0 != null)
        {
    //        aCRC32_930.reset();
    //        aCRC32_930.update(abyte0);
    //        int i1 = (int)aCRC32_930.getValue();
    //        if(i1 != j)
        }
        if(abyte0 != null)
        {
            StreamLoader streamLoader = new StreamLoader(abyte0);
            return streamLoader;
        }
        int j1 = 0;
        while(abyte0 == null)
        {
            String s2 = "Unknown error";
            drawLoadingText(k, "Requesting " + s);
            Object obj = null;
            try
            {
                int k1 = 0;
                DataInputStream datainputstream = openJagGrabInputStream(s1 + j);
                byte abyte1[] = new byte[6];
                datainputstream.readFully(abyte1, 0, 6);
                Stream stream = new Stream(abyte1);
                stream.currentOffset = 3;
                int i2 = stream.read3Bytes() + 6;
                int j2 = 6;
                abyte0 = new byte[i2];
                System.arraycopy(abyte1, 0, abyte0, 0, 6);

                while(j2 < i2) 
                {
                    int l2 = i2 - j2;
                    if(l2 > 1000)
                        l2 = 1000;
                    int j3 = datainputstream.read(abyte0, j2, l2);
                    if(j3 < 0)
                    {
                        s2 = "Length error: " + j2 + "/" + i2;
                        throw new IOException("EOF");
                    }
                    j2 += j3;
                    int k3 = (j2 * 100) / i2;
                    if(k3 != k1)
                        drawLoadingText(k, "Loading " + s + " - " + k3 + "%");
                    k1 = k3;
                }
                datainputstream.close();
                try
                {
                    if(decompressors[0] != null)
                        decompressors[0].method234(abyte0.length, abyte0, i);
                }
                catch(Exception _ex)
                {
                    decompressors[0] = null;
                }
   /*             if(abyte0 != null)
                {
                    aCRC32_930.reset();
                    aCRC32_930.update(abyte0);
                    int i3 = (int)aCRC32_930.getValue();
                    if(i3 != j)
                    {
                        abyte0 = null;
                        j1++;
                        s2 = "Checksum error: " + i3;
                    }
                }
  */
            }
            catch(IOException ioexception)
            {
                if(s2.equals("Unknown error"))
                    s2 = "Connection error";
                abyte0 = null;
            }
            catch(NullPointerException _ex)
            {
                s2 = "Null error";
                abyte0 = null;
                if(!signlink.reporterror)
                    return null;
            }
            catch(ArrayIndexOutOfBoundsException _ex)
            {
                s2 = "Bounds error";
                abyte0 = null;
                if(!signlink.reporterror)
                    return null;
            }
            catch(Exception _ex)
            {
                s2 = "Unexpected error";
                abyte0 = null;
                if(!signlink.reporterror)
                    return null;
            }
            if(abyte0 == null)
            {
                for(int l1 = l; l1 > 0; l1--)
                {
                    if(j1 >= 3)
                    {
                        drawLoadingText(k, "Game updated - please reload page");
                        l1 = 10;
                    } else
                    {
                        drawLoadingText(k, s2 + " - Retrying in " + l1);
                    }
                    try
                    {
                        Thread.sleep(1000L);
                    }
                    catch(Exception _ex) { }
                }

                l *= 2;
                if(l > 60)
                    l = 60;
                aBoolean872 = !aBoolean872;
            }

        }

        StreamLoader streamLoader_1 = new StreamLoader(abyte0);
            return streamLoader_1;
    }

    private void dropClient()
    {
        if(anInt1011 > 0)
        {
            logout();
            return;
        }
        gameScreenCanvas.initDrawingArea();
        plainFont.drawTextHMidVTop("Connection lost", 257, 144, 0);
        plainFont.drawTextHMidVTop("Connection lost", 256, 143, 0xffffff);
        plainFont.drawTextHMidVTop("Please wait - attempting to reestablish", 257, 159, 0);
        plainFont.drawTextHMidVTop("Please wait - attempting to reestablish", 256, 158, 0xffffff);
        gameScreenCanvas.drawGraphics(4, super.gameGraphics, 4);
        minimapState = 0;
        destX = 0;
        RSSocket rsSocket = socket;
        loggedIn = false;
        loginFailures = 0;
        login(myUsername, myPassword, true);
        if(!loggedIn)
            logout();
        try
        {
            rsSocket.close();
        }
        catch(Exception _ex)
        {
        }
    }

    private void doAction(int i)
    {
        if(i < 0)
            return;
        if(inputDialogState != 0)
        {
            inputDialogState = 0;
            inputTaken = true;
        }
        int j = menuActionCmd2[i];
        int k = menuActionCmd3[i];
        int l = menuActionID[i];
        int i1 = menuActionCmd1[i];
        if(l >= 2000)
            l -= 2000;
        if(l == 582)
        {
            NPC npc = npcArray[i1];
            if(npc != null)
            {
                doWalkTo(2, 0, 1, 0, localPlayer.smallY[0], 1, 0, npc.smallY[0], localPlayer.smallX[0], false, npc.smallX[0]);
                crossX = super.clickX;
                crossY = super.clickY;
                crossType = 2;
                crossIndex = 0;
                stream.putOpcode(57);
                stream.putShortA(anInt1285);
                stream.putShortA(i1);
                stream.putLEShort(anInt1283);
                stream.putShortA(anInt1284);
            }
        }
        if(l == 234)
        {
            boolean flag1 = doWalkTo(2, 0, 0, 0, localPlayer.smallY[0], 0, 0, k, localPlayer.smallX[0], false, j);
            if(!flag1)
                flag1 = doWalkTo(2, 0, 1, 0, localPlayer.smallY[0], 1, 0, k, localPlayer.smallX[0], false, j);
            crossX = super.clickX;
            crossY = super.clickY;
            crossType = 2;
            crossIndex = 0;
            stream.putOpcode(236);
            stream.putLEShort(k + baseY);
            stream.putShort(i1);
            stream.putLEShort(j + baseX);
        }
        if(l == 62 && method66(i1, k, j))
        {
            stream.putOpcode(192);
            stream.putShort(anInt1284);
            stream.putLEShort(i1 >> 14 & 0x7fff);
            stream.putLEShortA(k + baseY);
            stream.putLEShort(anInt1283);
            stream.putLEShortA(j + baseX);
            stream.putShort(anInt1285);
        }
        if(l == 511)
        {
            boolean flag2 = doWalkTo(2, 0, 0, 0, localPlayer.smallY[0], 0, 0, k, localPlayer.smallX[0], false, j);
            if(!flag2)
                flag2 = doWalkTo(2, 0, 1, 0, localPlayer.smallY[0], 1, 0, k, localPlayer.smallX[0], false, j);
            crossX = super.clickX;
            crossY = super.clickY;
            crossType = 2;
            crossIndex = 0;
            stream.putOpcode(25);
            stream.putLEShort(anInt1284);
            stream.putShortA(anInt1285);
            stream.putShort(i1);
            stream.putShortA(k + baseY);
            stream.putLEShortA(anInt1283);
            stream.putShort(j + baseX);
        }
        if(l == 74)
        {
            stream.putOpcode(122);
            stream.putLEShortA(k);
            stream.putShortA(j);
            stream.putLEShort(i1);
            atInventoryLoopCycle = 0;
            atInventoryInterface = k;
            atInventoryIndex = j;
            atInventoryInterfaceType = 2;
            if(RSInterface.interfaceCache[k].parentID == openInterfaceId)
                atInventoryInterfaceType = 1;
            if(RSInterface.interfaceCache[k].parentID == backDialogID)
                atInventoryInterfaceType = 3;
        }
        if(l == 315)
        {
            RSInterface class9 = RSInterface.interfaceCache[k];
            boolean flag8 = true;
            if(class9.contentType > 0)
                flag8 = promptUserForInput(class9);
            if(flag8)
            {
                stream.putOpcode(185);
                stream.putShort(k);
            }
        }
        if(l == 561)
        {
            Player player = playerArray[i1];
            if(player != null)
            {
                doWalkTo(2, 0, 1, 0, localPlayer.smallY[0], 1, 0, player.smallY[0], localPlayer.smallX[0], false, player.smallX[0]);
                crossX = super.clickX;
                crossY = super.clickY;
                crossType = 2;
                crossIndex = 0;
                anInt1188 += i1;
                if(anInt1188 >= 90)
                {
                    stream.putOpcode(136);
                    anInt1188 = 0;
                }
                stream.putOpcode(128);
                stream.putShort(i1);
            }
        }
        if(l == 20)
        {
            NPC class30_sub2_sub4_sub1_sub1_1 = npcArray[i1];
            if(class30_sub2_sub4_sub1_sub1_1 != null)
            {
                doWalkTo(2, 0, 1, 0, localPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub1_1.smallY[0], localPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub1_1.smallX[0]);
                crossX = super.clickX;
                crossY = super.clickY;
                crossType = 2;
                crossIndex = 0;
                stream.putOpcode(155);
                stream.putLEShort(i1);
            }
        }
        if(l == 779)
        {
            Player class30_sub2_sub4_sub1_sub2_1 = playerArray[i1];
            if(class30_sub2_sub4_sub1_sub2_1 != null)
            {
                doWalkTo(2, 0, 1, 0, localPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub2_1.smallY[0], localPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub2_1.smallX[0]);
                crossX = super.clickX;
                crossY = super.clickY;
                crossType = 2;
                crossIndex = 0;
                stream.putOpcode(153);
                stream.putLEShort(i1);
            }
        }
        if(l == 516)
            if(!menuOpen)
                worldController.request2DTrace(super.clickX - 4, super.clickY - 4);
            else
                worldController.request2DTrace(j - 4, k - 4);
        if(l == 1062)
        {
            anInt924 += baseX;
            if(anInt924 >= 113)
            {
                stream.putOpcode(183);
                stream.writeDWordBigEndian(0xe63271);
                anInt924 = 0;
            }
            method66(i1, k, j);
            stream.putOpcode(228);
            stream.putShortA(i1 >> 14 & 0x7fff);
            stream.putShortA(k + baseY);
            stream.putShort(j + baseX);
        }
        if(l == 679 && !aBoolean1149)
        {
            stream.putOpcode(40);
            stream.putShort(k);
            aBoolean1149 = true;
        }
        if(l == 431)
        {
            stream.putOpcode(129);
            stream.putShortA(j);
            stream.putShort(k);
            stream.putShortA(i1);
            atInventoryLoopCycle = 0;
            atInventoryInterface = k;
            atInventoryIndex = j;
            atInventoryInterfaceType = 2;
            if(RSInterface.interfaceCache[k].parentID == openInterfaceId)
                atInventoryInterfaceType = 1;
            if(RSInterface.interfaceCache[k].parentID == backDialogID)
                atInventoryInterfaceType = 3;
        }
        if(l == 337 || l == 42 || l == 792 || l == 322)
        {
            String s = menuActionName[i];
            int k1 = s.indexOf("@whi@");
            if(k1 != -1)
            {
                long l3 = TextClass.longForName(s.substring(k1 + 5).trim());
                if(l == 337)
                    addFriend(l3);
                if(l == 42)
                    addIgnore(l3);
                if(l == 792)
                    deleteFriend(l3);
                if(l == 322)
                    deleteIgnore(l3);
            }
        }
        if(l == 53)
        {
            stream.putOpcode(135);
            stream.putLEShort(j);
            stream.putShortA(k);
            stream.putLEShort(i1);
            atInventoryLoopCycle = 0;
            atInventoryInterface = k;
            atInventoryIndex = j;
            atInventoryInterfaceType = 2;
            if(RSInterface.interfaceCache[k].parentID == openInterfaceId)
                atInventoryInterfaceType = 1;
            if(RSInterface.interfaceCache[k].parentID == backDialogID)
                atInventoryInterfaceType = 3;
        }
        if(l == 539)
        {
            stream.putOpcode(16);
            stream.putShortA(i1);
            stream.putLEShortA(j);
            stream.putLEShortA(k);
            atInventoryLoopCycle = 0;
            atInventoryInterface = k;
            atInventoryIndex = j;
            atInventoryInterfaceType = 2;
            if(RSInterface.interfaceCache[k].parentID == openInterfaceId)
                atInventoryInterfaceType = 1;
            if(RSInterface.interfaceCache[k].parentID == backDialogID)
                atInventoryInterfaceType = 3;
        }
        if(l == 484 || l == 6)
        {
            String s1 = menuActionName[i];
            int l1 = s1.indexOf("@whi@");
            if(l1 != -1)
            {
                s1 = s1.substring(l1 + 5).trim();
                String s7 = TextClass.formatName(TextClass.nameForLong(TextClass.longForName(s1)));
                boolean flag9 = false;
                for(int j3 = 0; j3 < playerCount; j3++)
                {
                    Player class30_sub2_sub4_sub1_sub2_7 = playerArray[playerId[j3]];
                    if(class30_sub2_sub4_sub1_sub2_7 == null || class30_sub2_sub4_sub1_sub2_7.name == null || !class30_sub2_sub4_sub1_sub2_7.name.equalsIgnoreCase(s7))
                        continue;
                    doWalkTo(2, 0, 1, 0, localPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub2_7.smallY[0], localPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub2_7.smallX[0]);
                    if(l == 484)
                    {
                        stream.putOpcode(139);
                        stream.putLEShort(playerId[j3]);
                    }
                    if(l == 6)
                    {
                        anInt1188 += i1;
                        if(anInt1188 >= 90)
                        {
                            stream.putOpcode(136);
                            anInt1188 = 0;
                        }
                        stream.putOpcode(128);
                        stream.putShort(playerId[j3]);
                    }
                    flag9 = true;
                    break;
                }

                if(!flag9)
                    pushMessage("Unable to find " + s7, 0, "");
            }
        }
        if(l == 870)
        {
            stream.putOpcode(53);
            stream.putShort(j);
            stream.putShortA(anInt1283);
            stream.putLEShortA(i1);
            stream.putShort(anInt1284);
            stream.putLEShort(anInt1285);
            stream.putShort(k);
            atInventoryLoopCycle = 0;
            atInventoryInterface = k;
            atInventoryIndex = j;
            atInventoryInterfaceType = 2;
            if(RSInterface.interfaceCache[k].parentID == openInterfaceId)
                atInventoryInterfaceType = 1;
            if(RSInterface.interfaceCache[k].parentID == backDialogID)
                atInventoryInterfaceType = 3;
        }
        if(l == 847)
        {
            stream.putOpcode(87);
            stream.putShortA(i1);
            stream.putShort(k);
            stream.putShortA(j);
            atInventoryLoopCycle = 0;
            atInventoryInterface = k;
            atInventoryIndex = j;
            atInventoryInterfaceType = 2;
            if(RSInterface.interfaceCache[k].parentID == openInterfaceId)
                atInventoryInterfaceType = 1;
            if(RSInterface.interfaceCache[k].parentID == backDialogID)
                atInventoryInterfaceType = 3;
        }
        if(l == 626)
        {
            RSInterface class9_1 = RSInterface.interfaceCache[k];
            spellSelected = 1;
            anInt1137 = k;
            spellUsableOn = class9_1.spellUsableOn;
            itemSelected = 0;
            needDrawTabArea = true;
            String s4 = class9_1.selectedActionName;
            if(s4.indexOf(" ") != -1)
                s4 = s4.substring(0, s4.indexOf(" "));
            String s8 = class9_1.selectedActionName;
            if(s8.indexOf(" ") != -1)
                s8 = s8.substring(s8.indexOf(" ") + 1);
            spellTooltip = s4 + " " + class9_1.spellName + " " + s8;
            if(spellUsableOn == 16)
            {
                needDrawTabArea = true;
                tabID = 3;
                tabAreaAltered = true;
            }
            return;
        }
        if(l == 78)
        {
            stream.putOpcode(117);
            stream.putLEShortA(k);
            stream.putLEShortA(i1);
            stream.putLEShort(j);
            atInventoryLoopCycle = 0;
            atInventoryInterface = k;
            atInventoryIndex = j;
            atInventoryInterfaceType = 2;
            if(RSInterface.interfaceCache[k].parentID == openInterfaceId)
                atInventoryInterfaceType = 1;
            if(RSInterface.interfaceCache[k].parentID == backDialogID)
                atInventoryInterfaceType = 3;
        }
        if(l == 27)
        {
            Player class30_sub2_sub4_sub1_sub2_2 = playerArray[i1];
            if(class30_sub2_sub4_sub1_sub2_2 != null)
            {
                doWalkTo(2, 0, 1, 0, localPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub2_2.smallY[0], localPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub2_2.smallX[0]);
                crossX = super.clickX;
                crossY = super.clickY;
                crossType = 2;
                crossIndex = 0;
                anInt986 += i1;
                if(anInt986 >= 54)
                {
                    stream.putOpcode(189);
                    stream.put(234);
                    anInt986 = 0;
                }
                stream.putOpcode(73);
                stream.putLEShort(i1);
            }
        }
        if(l == 213)
        {
            boolean flag3 = doWalkTo(2, 0, 0, 0, localPlayer.smallY[0], 0, 0, k, localPlayer.smallX[0], false, j);
            if(!flag3)
                flag3 = doWalkTo(2, 0, 1, 0, localPlayer.smallY[0], 1, 0, k, localPlayer.smallX[0], false, j);
            crossX = super.clickX;
            crossY = super.clickY;
            crossType = 2;
            crossIndex = 0;
            stream.putOpcode(79);
            stream.putLEShort(k + baseY);
            stream.putShort(i1);
            stream.putShortA(j + baseX);
        }
        if(l == 632)
        {
            stream.putOpcode(145);
            stream.putShortA(k);
            stream.putShortA(j);
            stream.putShortA(i1);
            atInventoryLoopCycle = 0;
            atInventoryInterface = k;
            atInventoryIndex = j;
            atInventoryInterfaceType = 2;
            if(RSInterface.interfaceCache[k].parentID == openInterfaceId)
                atInventoryInterfaceType = 1;
            if(RSInterface.interfaceCache[k].parentID == backDialogID)
                atInventoryInterfaceType = 3;
        }
        if(l == 493)
        {
            stream.putOpcode(75);
            stream.putLEShortA(k);
            stream.putLEShort(j);
            stream.putShortA(i1);
            atInventoryLoopCycle = 0;
            atInventoryInterface = k;
            atInventoryIndex = j;
            atInventoryInterfaceType = 2;
            if(RSInterface.interfaceCache[k].parentID == openInterfaceId)
                atInventoryInterfaceType = 1;
            if(RSInterface.interfaceCache[k].parentID == backDialogID)
                atInventoryInterfaceType = 3;
        }
        if(l == 652)
        {
            boolean flag4 = doWalkTo(2, 0, 0, 0, localPlayer.smallY[0], 0, 0, k, localPlayer.smallX[0], false, j);
            if(!flag4)
                flag4 = doWalkTo(2, 0, 1, 0, localPlayer.smallY[0], 1, 0, k, localPlayer.smallX[0], false, j);
            crossX = super.clickX;
            crossY = super.clickY;
            crossType = 2;
            crossIndex = 0;
            stream.putOpcode(156);
            stream.putShortA(j + baseX);
            stream.putLEShort(k + baseY);
            stream.putLEShortA(i1);
        }
        if(l == 94)
        {
            boolean flag5 = doWalkTo(2, 0, 0, 0, localPlayer.smallY[0], 0, 0, k, localPlayer.smallX[0], false, j);
            if(!flag5)
                flag5 = doWalkTo(2, 0, 1, 0, localPlayer.smallY[0], 1, 0, k, localPlayer.smallX[0], false, j);
            crossX = super.clickX;
            crossY = super.clickY;
            crossType = 2;
            crossIndex = 0;
            stream.putOpcode(181);
            stream.putLEShort(k + baseY);
            stream.putShort(i1);
            stream.putLEShort(j + baseX);
            stream.putShortA(anInt1137);
        }
        if(l == 646)
        {
            stream.putOpcode(185);
            stream.putShort(k);
            RSInterface class9_2 = RSInterface.interfaceCache[k];
            if(class9_2.valueIndexArray != null && class9_2.valueIndexArray[0][0] == 5)
            {
                int i2 = class9_2.valueIndexArray[0][1];
                if(variousSettings[i2] != class9_2.anIntArray212[0])
                {
                    variousSettings[i2] = class9_2.anIntArray212[0];
                    handleInterfaceSetting(i2);
                    needDrawTabArea = true;
                }
            }
        }
        if(l == 225)
        {
            NPC class30_sub2_sub4_sub1_sub1_2 = npcArray[i1];
            if(class30_sub2_sub4_sub1_sub1_2 != null)
            {
                doWalkTo(2, 0, 1, 0, localPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub1_2.smallY[0], localPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub1_2.smallX[0]);
                crossX = super.clickX;
                crossY = super.clickY;
                crossType = 2;
                crossIndex = 0;
                anInt1226 += i1;
                if(anInt1226 >= 85)
                {
                    stream.putOpcode(230);
                    stream.put(239);
                    anInt1226 = 0;
                }
                stream.putOpcode(17);
                stream.putLEShortA(i1);
            }
        }
        if(l == 965)
        {
            NPC class30_sub2_sub4_sub1_sub1_3 = npcArray[i1];
            if(class30_sub2_sub4_sub1_sub1_3 != null)
            {
                doWalkTo(2, 0, 1, 0, localPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub1_3.smallY[0], localPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub1_3.smallX[0]);
                crossX = super.clickX;
                crossY = super.clickY;
                crossType = 2;
                crossIndex = 0;
                anInt1134++;
                if(anInt1134 >= 96)
                {
                    stream.putOpcode(152);
                    stream.put(88);
                    anInt1134 = 0;
                }
                stream.putOpcode(21);
                stream.putShort(i1);
            }
        }
        if(l == 413)
        {
            NPC class30_sub2_sub4_sub1_sub1_4 = npcArray[i1];
            if(class30_sub2_sub4_sub1_sub1_4 != null)
            {
                doWalkTo(2, 0, 1, 0, localPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub1_4.smallY[0], localPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub1_4.smallX[0]);
                crossX = super.clickX;
                crossY = super.clickY;
                crossType = 2;
                crossIndex = 0;
                stream.putOpcode(131);
                stream.putLEShortA(i1);
                stream.putShortA(anInt1137);
            }
        }
        if(l == 200)
            clearTopInterfaces();
        if(l == 1025)
        {
            NPC class30_sub2_sub4_sub1_sub1_5 = npcArray[i1];
            if(class30_sub2_sub4_sub1_sub1_5 != null)
            {
                EntityDef entityDef = class30_sub2_sub4_sub1_sub1_5.npcDefinition;
                if(entityDef.childrenIDs != null)
                    entityDef = entityDef.method161();
                if(entityDef != null)
                {
                    String s9;
                    if(entityDef.description != null)
                        s9 = new String(entityDef.description);
                    else
                        s9 = "It's a " + entityDef.name + ".";
                    pushMessage(s9, 0, "");
                }
            }
        }
        if(l == 900)
        {
            method66(i1, k, j);
            stream.putOpcode(252);
            stream.putLEShortA(i1 >> 14 & 0x7fff);
            stream.putLEShort(k + baseY);
            stream.putShortA(j + baseX);
        }
        if(l == 412)
        {
            NPC class30_sub2_sub4_sub1_sub1_6 = npcArray[i1];
            if(class30_sub2_sub4_sub1_sub1_6 != null)
            {
                doWalkTo(2, 0, 1, 0, localPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub1_6.smallY[0], localPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub1_6.smallX[0]);
                crossX = super.clickX;
                crossY = super.clickY;
                crossType = 2;
                crossIndex = 0;
                stream.putOpcode(72);
                stream.putShortA(i1);
            }
        }
        if(l == 365)
        {
            Player class30_sub2_sub4_sub1_sub2_3 = playerArray[i1];
            if(class30_sub2_sub4_sub1_sub2_3 != null)
            {
                doWalkTo(2, 0, 1, 0, localPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub2_3.smallY[0], localPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub2_3.smallX[0]);
                crossX = super.clickX;
                crossY = super.clickY;
                crossType = 2;
                crossIndex = 0;
                stream.putOpcode(249);
                stream.putShortA(i1);
                stream.putLEShort(anInt1137);
            }
        }
        if(l == 729)
        {
            Player class30_sub2_sub4_sub1_sub2_4 = playerArray[i1];
            if(class30_sub2_sub4_sub1_sub2_4 != null)
            {
                doWalkTo(2, 0, 1, 0, localPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub2_4.smallY[0], localPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub2_4.smallX[0]);
                crossX = super.clickX;
                crossY = super.clickY;
                crossType = 2;
                crossIndex = 0;
                stream.putOpcode(39);
                stream.putLEShort(i1);
            }
        }
        if(l == 577)
        {
            Player class30_sub2_sub4_sub1_sub2_5 = playerArray[i1];
            if(class30_sub2_sub4_sub1_sub2_5 != null)
            {
                doWalkTo(2, 0, 1, 0, localPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub2_5.smallY[0], localPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub2_5.smallX[0]);
                crossX = super.clickX;
                crossY = super.clickY;
                crossType = 2;
                crossIndex = 0;
                stream.putOpcode(139);
                stream.putLEShort(i1);
            }
        }
        if(l == 956 && method66(i1, k, j))
        {
            stream.putOpcode(35);
            stream.putLEShort(j + baseX);
            stream.putShortA(anInt1137);
            stream.putShortA(k + baseY);
            stream.putLEShort(i1 >> 14 & 0x7fff);
        }
        if(l == 567)
        {
            boolean flag6 = doWalkTo(2, 0, 0, 0, localPlayer.smallY[0], 0, 0, k, localPlayer.smallX[0], false, j);
            if(!flag6)
                flag6 = doWalkTo(2, 0, 1, 0, localPlayer.smallY[0], 1, 0, k, localPlayer.smallX[0], false, j);
            crossX = super.clickX;
            crossY = super.clickY;
            crossType = 2;
            crossIndex = 0;
            stream.putOpcode(23);
            stream.putLEShort(k + baseY);
            stream.putLEShort(i1);
            stream.putLEShort(j + baseX);
        }
        if(l == 867)
        {
            if((i1 & 3) == 0)
                anInt1175++;
            if(anInt1175 >= 59)
            {
                stream.putOpcode(200);
                stream.putShort(25501);
                anInt1175 = 0;
            }
            stream.putOpcode(43);
            stream.putLEShort(k);
            stream.putShortA(i1);
            stream.putShortA(j);
            atInventoryLoopCycle = 0;
            atInventoryInterface = k;
            atInventoryIndex = j;
            atInventoryInterfaceType = 2;
            if(RSInterface.interfaceCache[k].parentID == openInterfaceId)
                atInventoryInterfaceType = 1;
            if(RSInterface.interfaceCache[k].parentID == backDialogID)
                atInventoryInterfaceType = 3;
        }
        if(l == 543)
        {
            stream.putOpcode(237);
            stream.putShort(j);
            stream.putShortA(i1);
            stream.putShort(k);
            stream.putShortA(anInt1137);
            atInventoryLoopCycle = 0;
            atInventoryInterface = k;
            atInventoryIndex = j;
            atInventoryInterfaceType = 2;
            if(RSInterface.interfaceCache[k].parentID == openInterfaceId)
                atInventoryInterfaceType = 1;
            if(RSInterface.interfaceCache[k].parentID == backDialogID)
                atInventoryInterfaceType = 3;
        }
        if(l == 606)
        {
            String s2 = menuActionName[i];
            int j2 = s2.indexOf("@whi@");
            if(j2 != -1)
                if(openInterfaceId == -1)
                {
                    clearTopInterfaces();
                    reportAbuseInput = s2.substring(j2 + 5).trim();
                    canMute = false;
                    for(int i3 = 0; i3 < RSInterface.interfaceCache.length; i3++)
                    {
                        if(RSInterface.interfaceCache[i3] == null || RSInterface.interfaceCache[i3].contentType != 600)
                            continue;
                        reportAbuseInterfaceID = openInterfaceId = RSInterface.interfaceCache[i3].parentID;
                        break;
                    }

                } else
                {
                    pushMessage("Please close the interface you have open before using 'report abuse'", 0, "");
                }
        }
        if(l == 491)
        {
            Player player = playerArray[i1];
            if(player != null)
            {
                doWalkTo(2, 0, 1, 0, localPlayer.smallY[0], 1, 0, player.smallY[0], localPlayer.smallX[0], false, player.smallX[0]);
                crossX = super.clickX;
                crossY = super.clickY;
                crossType = 2;
                crossIndex = 0;
                stream.putOpcode(14);
                stream.putShortA(anInt1284);
                stream.putShort(i1);
                stream.putShort(anInt1285);
                stream.putLEShort(anInt1283);
            }
        }
        if(l == 639)
        {
            String s3 = menuActionName[i];
            int k2 = s3.indexOf("@whi@");
            if(k2 != -1)
            {
                long l4 = TextClass.longForName(s3.substring(k2 + 5).trim());
                int target = -1;
                for(int f = 0; f < friendsCount; f++)
                {
                    if(friendsListAsLongs[f] != l4)
                        continue;
                    target = f;
                    break;
                }

                if(target != -1 && friendsWorldIds[target] > 0)
                {
                    inputTaken = true;
                    inputDialogState = 0;
                    messagePromptRaised = true;
                    promptInput = "";
                    friendsListAction = 3;
                    privateMessageTarget = friendsListAsLongs[target];
                    aString1121 = "Enter message to send to " + friendsList[target];
                }
            }
        }
        if(l == 454)
        {
            stream.putOpcode(41);
            stream.putShort(i1);
            stream.putShortA(j);
            stream.putShortA(k);
            atInventoryLoopCycle = 0;
            atInventoryInterface = k;
            atInventoryIndex = j;
            atInventoryInterfaceType = 2;
            if(RSInterface.interfaceCache[k].parentID == openInterfaceId)
                atInventoryInterfaceType = 1;
            if(RSInterface.interfaceCache[k].parentID == backDialogID)
                atInventoryInterfaceType = 3;
        }
        if(l == 478)
        {
            NPC class30_sub2_sub4_sub1_sub1_7 = npcArray[i1];
            if(class30_sub2_sub4_sub1_sub1_7 != null)
            {
                doWalkTo(2, 0, 1, 0, localPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub1_7.smallY[0], localPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub1_7.smallX[0]);
                crossX = super.clickX;
                crossY = super.clickY;
                crossType = 2;
                crossIndex = 0;
                if((i1 & 3) == 0)
                    anInt1155++;
                if(anInt1155 >= 53)
                {
                    stream.putOpcode(85);
                    stream.put(66);
                    anInt1155 = 0;
                }
                stream.putOpcode(18);
                stream.putLEShort(i1);
            }
        }
        if(l == 113)
        {
            method66(i1, k, j);
            stream.putOpcode(70);
            stream.putLEShort(j + baseX);
            stream.putShort(k + baseY);
            stream.putLEShortA(i1 >> 14 & 0x7fff);
        }
        if(l == 872)
        {
            method66(i1, k, j);
            stream.putOpcode(234);
            stream.putLEShortA(j + baseX);
            stream.putShortA(i1 >> 14 & 0x7fff);
            stream.putLEShortA(k + baseY);
        }
        if(l == 502)
        {
            method66(i1, k, j);
            stream.putOpcode(132);
            stream.putLEShortA(j + baseX);
            stream.putShort(i1 >> 14 & 0x7fff);
            stream.putShortA(k + baseY);
        }
        if(l == 1125)
        {
            ItemDef itemDef = ItemDef.forID(i1);
            RSInterface class9_4 = RSInterface.interfaceCache[k];
            String s5;
            if(class9_4 != null && class9_4.inventoryStackSize[j] >= 0x186a0)
                s5 = class9_4.inventoryStackSize[j] + " x " + itemDef.name;
            else
            if(itemDef.description != null)
                s5 = new String(itemDef.description);
            else
                s5 = "It's a " + itemDef.name + ".";
            pushMessage(s5, 0, "");
        }
        if(l == 169)
        {
            stream.putOpcode(185);
            stream.putShort(k);
            RSInterface class9_3 = RSInterface.interfaceCache[k];
            if(class9_3.valueIndexArray != null && class9_3.valueIndexArray[0][0] == 5)
            {
                int l2 = class9_3.valueIndexArray[0][1];
                variousSettings[l2] = 1 - variousSettings[l2];
                handleInterfaceSetting(l2);
                needDrawTabArea = true;
            }
        }
        if(l == 447)
        {
            itemSelected = 1;
            anInt1283 = j;
            anInt1284 = k;
            anInt1285 = i1;
            selectedItemName = ItemDef.forID(i1).name;
            spellSelected = 0;
            needDrawTabArea = true;
            return;
        }
        if(l == 1226)
        {
            int j1 = i1 >> 14 & 0x7fff;
            ObjectDef class46 = ObjectDef.forID(j1);
            String s10;
            if(class46.description != null)
                s10 = new String(class46.description);
            else
                s10 = "It's a " + class46.name + ".";
            pushMessage(s10, 0, "");
        }
        if(l == 244)
        {
            boolean flag7 = doWalkTo(2, 0, 0, 0, localPlayer.smallY[0], 0, 0, k, localPlayer.smallX[0], false, j);
            if(!flag7)
                flag7 = doWalkTo(2, 0, 1, 0, localPlayer.smallY[0], 1, 0, k, localPlayer.smallX[0], false, j);
            crossX = super.clickX;
            crossY = super.clickY;
            crossType = 2;
            crossIndex = 0;
            stream.putOpcode(253);
            stream.putLEShort(j + baseX);
            stream.putLEShortA(k + baseY);
            stream.putShortA(i1);
        }
        if(l == 1448)
        {
            ItemDef itemDef_1 = ItemDef.forID(i1);
            String s6;
            if(itemDef_1.description != null)
                s6 = new String(itemDef_1.description);
            else
                s6 = "It's a " + itemDef_1.name + ".";
            pushMessage(s6, 0, "");
        }
        itemSelected = 0;
            spellSelected = 0;
            needDrawTabArea = true;

    }

    private void method70()
    {
        inTutorial = 0;
        int j = (localPlayer.x >> 7) + baseX;
        int k = (localPlayer.y >> 7) + baseY;
        if(j >= 3053 && j <= 3156 && k >= 3056 && k <= 3136)
            inTutorial = 1;
        if(j >= 3072 && j <= 3118 && k >= 9492 && k <= 9535)
            inTutorial = 1;
        if(inTutorial == 1 && j >= 3139 && j <= 3199 && k >= 3008 && k <= 3062)
            inTutorial = 0;
    }

    public void run()
    {
        if(drawFlames)
        {
            drawFlames();
        } else
        {
            super.run();
        }
    }

    private void build3dScreenMenu()
    {
        if(itemSelected == 0 && spellSelected == 0)
        {
            menuActionName[menuActionRow] = "Walk here";
            menuActionID[menuActionRow] = 516;
            menuActionCmd2[menuActionRow] = super.mouseX;
            menuActionCmd3[menuActionRow] = super.mouseY;
            menuActionRow++;
        }
        int j = -1;
        for(int k = 0; k < Model.anInt1687; k++)
        {
            int l = Model.anIntArray1688[k];
            int i1 = l & 0x7f;
            int j1 = l >> 7 & 0x7f;
            int k1 = l >> 29 & 3;
            int l1 = l >> 14 & 0x7fff;
            if(l == j)
                continue;
            j = l;
            if(k1 == 2 && worldController.getConfig(l, i1, j1, plane) >= 0)
            {
                ObjectDef class46 = ObjectDef.forID(l1);
                if(class46.childrenIDs != null)
                    class46 = class46.method580();
                if(class46 == null)
                    continue;
                if(itemSelected == 1)
                {
                    menuActionName[menuActionRow] = "Use " + selectedItemName + " with @cya@" + class46.name;
                    menuActionID[menuActionRow] = 62;
                    menuActionCmd1[menuActionRow] = l;
                    menuActionCmd2[menuActionRow] = i1;
                    menuActionCmd3[menuActionRow] = j1;
                    menuActionRow++;
                } else
                if(spellSelected == 1)
                {
                    if((spellUsableOn & 4) == 4)
                    {
                        menuActionName[menuActionRow] = spellTooltip + " @cya@" + class46.name;
                        menuActionID[menuActionRow] = 956;
                        menuActionCmd1[menuActionRow] = l;
                        menuActionCmd2[menuActionRow] = i1;
                        menuActionCmd3[menuActionRow] = j1;
                        menuActionRow++;
                    }
                } else
                {
                    if(class46.actions != null)
                    {
                        for(int i2 = 4; i2 >= 0; i2--)
                            if(class46.actions[i2] != null)
                            {
                                menuActionName[menuActionRow] = class46.actions[i2] + " @cya@" + class46.name;
                                if(i2 == 0)
                                    menuActionID[menuActionRow] = 502;
                                if(i2 == 1)
                                    menuActionID[menuActionRow] = 900;
                                if(i2 == 2)
                                    menuActionID[menuActionRow] = 113;
                                if(i2 == 3)
                                    menuActionID[menuActionRow] = 872;
                                if(i2 == 4)
                                    menuActionID[menuActionRow] = 1062;
                                menuActionCmd1[menuActionRow] = l;
                                menuActionCmd2[menuActionRow] = i1;
                                menuActionCmd3[menuActionRow] = j1;
                                menuActionRow++;
                            }

                    }
                    menuActionName[menuActionRow] = "Examine @cya@" + class46.name + " @gre@(@whi@" + l1 + "@gre@) (@whi@" + (i1 + baseX) + "," + (j1 + baseY) + "@gre@)";
                    menuActionID[menuActionRow] = 1226;
                    menuActionCmd1[menuActionRow] = class46.type << 14;
                    menuActionCmd2[menuActionRow] = i1;
                    menuActionCmd3[menuActionRow] = j1;
                    menuActionRow++;
                }
            }
            if(k1 == 1)
            {
                NPC npc = npcArray[l1];
                if(npc.npcDefinition.boundaryDimension == 1 && (npc.x & 0x7f) == 64 && (npc.y & 0x7f) == 64)
                {
                    for(int j2 = 0; j2 < npcCount; j2++)
                    {
                        NPC npc2 = npcArray[npcIndices[j2]];
                        if(npc2 != null && npc2 != npc && npc2.npcDefinition.boundaryDimension == 1 && npc2.x == npc.x && npc2.y == npc.y)
                            buildAtNPCMenu(npc2.npcDefinition, npcIndices[j2], j1, i1);
                    }

                    for(int l2 = 0; l2 < playerCount; l2++)
                    {
                        Player player = playerArray[playerId[l2]];
                        if(player != null && player.x == npc.x && player.y == npc.y)
                            buildAtPlayerMenu(i1, playerId[l2], player, j1);
                    }

                }
                buildAtNPCMenu(npc.npcDefinition, l1, j1, i1);
            }
            if(k1 == 0)
            {
                Player player = playerArray[l1];
                if((player.x & 0x7f) == 64 && (player.y & 0x7f) == 64)
                {
                    for(int k2 = 0; k2 < npcCount; k2++)
                    {
                        NPC class30_sub2_sub4_sub1_sub1_2 = npcArray[npcIndices[k2]];
                        if(class30_sub2_sub4_sub1_sub1_2 != null && class30_sub2_sub4_sub1_sub1_2.npcDefinition.boundaryDimension == 1 && class30_sub2_sub4_sub1_sub1_2.x == player.x && class30_sub2_sub4_sub1_sub1_2.y == player.y)
                            buildAtNPCMenu(class30_sub2_sub4_sub1_sub1_2.npcDefinition, npcIndices[k2], j1, i1);
                    }

                    for(int i3 = 0; i3 < playerCount; i3++)
                    {
                        Player class30_sub2_sub4_sub1_sub2_2 = playerArray[playerId[i3]];
                        if(class30_sub2_sub4_sub1_sub2_2 != null && class30_sub2_sub4_sub1_sub2_2 != player && class30_sub2_sub4_sub1_sub2_2.x == player.x && class30_sub2_sub4_sub1_sub2_2.y == player.y)
                            buildAtPlayerMenu(i1, playerId[i3], class30_sub2_sub4_sub1_sub2_2, j1);
                    }

                }
                buildAtPlayerMenu(i1, l1, player, j1);
            }
            if(k1 == 3)
            {
                NodeList class19 = groundArray[plane][i1][j1];
                if(class19 != null)
                {
                    for(Item item = (Item)class19.getFirst(); item != null; item = (Item)class19.getNext())
                    {
                        ItemDef itemDef = ItemDef.forID(item.itemId);
                        if(itemSelected == 1)
                        {
                            menuActionName[menuActionRow] = "Use " + selectedItemName + " with @lre@" + itemDef.name;
                            menuActionID[menuActionRow] = 511;
                            menuActionCmd1[menuActionRow] = item.itemId;
                            menuActionCmd2[menuActionRow] = i1;
                            menuActionCmd3[menuActionRow] = j1;
                            menuActionRow++;
                        } else
                        if(spellSelected == 1)
                        {
                            if((spellUsableOn & 1) == 1)
                            {
                                menuActionName[menuActionRow] = spellTooltip + " @lre@" + itemDef.name;
                                menuActionID[menuActionRow] = 94;
                                menuActionCmd1[menuActionRow] = item.itemId;
                                menuActionCmd2[menuActionRow] = i1;
                                menuActionCmd3[menuActionRow] = j1;
                                menuActionRow++;
                            }
                        } else
                        {
                            for(int j3 = 4; j3 >= 0; j3--)
                                if(itemDef.groundActions != null && itemDef.groundActions[j3] != null)
                                {
                                    menuActionName[menuActionRow] = itemDef.groundActions[j3] + " @lre@" + itemDef.name;
                                    if(j3 == 0)
                                        menuActionID[menuActionRow] = 652;
                                    if(j3 == 1)
                                        menuActionID[menuActionRow] = 567;
                                    if(j3 == 2)
                                        menuActionID[menuActionRow] = 234;
                                    if(j3 == 3)
                                        menuActionID[menuActionRow] = 244;
                                    if(j3 == 4)
                                        menuActionID[menuActionRow] = 213;
                                    menuActionCmd1[menuActionRow] = item.itemId;
                                    menuActionCmd2[menuActionRow] = i1;
                                    menuActionCmd3[menuActionRow] = j1;
                                    menuActionRow++;
                                } else
                                if(j3 == 2)
                                {
                                    menuActionName[menuActionRow] = "Take @lre@" + itemDef.name;
                                    menuActionID[menuActionRow] = 234;
                                    menuActionCmd1[menuActionRow] = item.itemId;
                                    menuActionCmd2[menuActionRow] = i1;
                                    menuActionCmd3[menuActionRow] = j1;
                                    menuActionRow++;
                                }

                            menuActionName[menuActionRow] = "Examine @lre@" + itemDef.name + " @gre@(@whi@" + item.itemId + "@gre@)";
                            menuActionID[menuActionRow] = 1448;
                            menuActionCmd1[menuActionRow] = item.itemId;
                            menuActionCmd2[menuActionRow] = i1;
                            menuActionCmd3[menuActionRow] = j1;
                            menuActionRow++;
                        }
                    }

                }
            }
        }
    }

    public void cleanUpForQuit()
    {
        signlink.reporterror = false;
        try
        {
            if(socket != null)
                socket.close();
        }
        catch(Exception _ex) { }
        socket = null;
        stopMidi();
        if(mouseDetection != null)
            mouseDetection.running = false;
        mouseDetection = null;
        onDemandFetcher.disable();
        onDemandFetcher = null;
        textStream = null;
        stream = null;
        aStream_847 = null;
        inStream = null;
        mapCoordinates = null;
        terrainData = null;
        objectData = null;
        anIntArray1235 = null;
        anIntArray1236 = null;
        intGroundArray = null;
        byteGroundArray = null;
        worldController = null;
        currentCollisionMap = null;
        wayPoints = null;
        distanceValues = null;
        walkingQueueX = null;
        walkingQueueY = null;
        aByteArray912 = null;
        aRSImageProducer_1163 = null;
        aRSImageProducer_1164 = null;
        gameScreenCanvas = null;
        aRSImageProducer_1166 = null;
        aRSImageProducer_1123 = null;
        aRSImageProducer_1124 = null;
        aRSImageProducer_1125 = null;
        backLeftIP1 = null;
        backLeftIP2 = null;
        backRightIP1 = null;
        backRightIP2 = null;
        backTopIP1 = null;
        backVmidIP1 = null;
        backVmidIP2 = null;
        backVmidIP3 = null;
        backVmidIP2_2 = null;
        invBack = null;
        mapBack = null;
        chatBack = null;
        backBase1 = null;
        backBase2 = null;
        backHmid1 = null;
        sideIcons = null;
        redStone1 = null;
        redStone2 = null;
        redStone3 = null;
        redStone1_2 = null;
        redStone2_2 = null;
        redStone1_3 = null;
        redStone2_3 = null;
        redStone3_2 = null;
        redStone1_4 = null;
        redStone2_4 = null;
        compass = null;
        hitMarks = null;
        headIcons = null;
        crosses = null;
        mapDotItem = null;
        mapDotNPC = null;
        mapDotPlayer = null;
        mapDotFriend = null;
        mapDotTeam = null;
        mapScenes = null;
        mapFunctions = null;
        anIntArrayArray929 = null;
        playerArray = null;
        playerId = null;
        playersAwaitingUpdate = null;
        playerBuffer = null;
        anIntArray840 = null;
        npcArray = null;
        npcIndices = null;
        groundArray = null;
        spawnObjectList = null;
        projectileQueue = null;
        stationaryGraphicQueue = null;
        menuActionCmd2 = null;
        menuActionCmd3 = null;
        menuActionID = null;
        menuActionCmd1 = null;
        menuActionName = null;
        variousSettings = null;
        anIntArray1072 = null;
        anIntArray1073 = null;
        aClass30_Sub2_Sub1_Sub1Array1140 = null;
        aClass30_Sub2_Sub1_Sub1_1263 = null;
        friendsList = null;
        friendsListAsLongs = null;
        friendsWorldIds = null;
        aRSImageProducer_1110 = null;
        aRSImageProducer_1111 = null;
        aRSImageProducer_1107 = null;
        aRSImageProducer_1108 = null;
        aRSImageProducer_1109 = null;
        aRSImageProducer_1112 = null;
        aRSImageProducer_1113 = null;
        aRSImageProducer_1114 = null;
        aRSImageProducer_1115 = null;
        nullLoader();
        ObjectDef.nullLoader();
        EntityDef.nullLoader();
        ItemDef.nullLoader();
        Flo.cache = null;
        IDK.cache = null;
        RSInterface.interfaceCache = null;
        DummyClass.cache = null;
        Animation.anims = null;
        SpotAnim.cache = null;
        SpotAnim.modelCache = null;
        Varp.cache = null;
        super.fullGameScreen = null;
        Player.mruNodes = null;
        Texture.nullLoader();
        WorldController.nullLoader();
        Model.nullLoader();
        Class36.nullLoader();
        System.gc();
    }

    private void printDebug()
    {
        System.out.println("============");
        System.out.println("flame-cycle:" + anInt1208);
        if(onDemandFetcher != null)
            System.out.println("Od-cycle:" + onDemandFetcher.onDemandCycle);
        System.out.println("loop-cycle:" + loopCycle);
        System.out.println("draw-cycle:" + anInt1061);
        System.out.println("ptype:" + packetOpcode);
        System.out.println("psize:" + packetSize);
        if(socket != null)
            socket.printDebug();
        super.debugRequested = true;
    }

    Component getGameComponent()
    {
        if(signlink.mainapp != null)
            return signlink.mainapp;
        if(super.gameFrame != null)
            return super.gameFrame;
        else
            return this;
    }

    private void manageTextInput()
    {
        do
        {
            int c = readCharacter();
            if(c == -1)
                break;
            if(openInterfaceId != -1 && openInterfaceId == reportAbuseInterfaceID)
            {
                if(c == 8 && reportAbuseInput.length() > 0)
                    reportAbuseInput = reportAbuseInput.substring(0, reportAbuseInput.length() - 1);
                if((c >= 97 && c <= 122 || c >= 65 && c <= 90 || c >= 48 && c <= 57 || c == 32) && reportAbuseInput.length() < 12)
                    reportAbuseInput += (char)c;
            } else
            if(messagePromptRaised)
            {
                if(c >= 32 && c <= 122 && promptInput.length() < 80)
                {
                	// Player pressed an enterable character
                    promptInput += (char)c;
                    inputTaken = true;
                }
                if(c == 8 && promptInput.length() > 0)
                {
                	// Player pressed backspace
                    promptInput = promptInput.substring(0, promptInput.length() - 1);
                    inputTaken = true;
                }
                if(c == 13 || c == 10)
                {
                	// Player pressed enter
                    messagePromptRaised = false;
                    inputTaken = true;
                    if(friendsListAction == 1)
                    {
                        long nameLong = TextClass.longForName(promptInput);
                        addFriend(nameLong);
                    }
                    if(friendsListAction == 2 && friendsCount > 0)
                    {
                        long nameLong = TextClass.longForName(promptInput);
                        deleteFriend(nameLong);
                    }
                    if(friendsListAction == 3 && promptInput.length() > 0)
                    {
                        stream.putOpcode(126);
                        stream.put(0);
                        int originalOffset = stream.currentOffset;
                        stream.putLong(privateMessageTarget);
                        TextInput.writeToStream(promptInput, stream);
                        stream.putSizeByte(stream.currentOffset - originalOffset);
                        promptInput = TextInput.processText(promptInput);
                        promptInput = Censor.doCensor(promptInput);
                        pushMessage(promptInput, 6, TextClass.formatName(TextClass.nameForLong(privateMessageTarget)));
                        if(privateChatMode == 2)
                        {
                            privateChatMode = 1;
                            updateChatSettings = true;
                            stream.putOpcode(95);
                            stream.put(publicChatMode);
                            stream.put(privateChatMode);
                            stream.put(tradeMode);
                        }
                    }
                    if(friendsListAction == 4 && ignoreCount < 100)
                    {
                        long nameLong = TextClass.longForName(promptInput);
                        addIgnore(nameLong);
                    }
                    if(friendsListAction == 5 && ignoreCount > 0)
                    {
                        long nameLong = TextClass.longForName(promptInput);
                        deleteIgnore(nameLong);
                    }
                }
            } else
            if(inputDialogState == 1)
            {
                if(c >= 48 && c <= 57 && amountOrNameInput.length() < 10)
                {
                    amountOrNameInput += (char)c;
                    inputTaken = true;
                }
                if(c == 8 && amountOrNameInput.length() > 0)
                {
                    amountOrNameInput = amountOrNameInput.substring(0, amountOrNameInput.length() - 1);
                    inputTaken = true;
                }
                if(c == 13 || c == 10)
                {
                    if(amountOrNameInput.length() > 0)
                    {
                        int bankAmount = 0;
                        try
                        {
                            bankAmount = Integer.parseInt(amountOrNameInput);
                        }
                        catch(Exception _ex) { }
                        stream.putOpcode(208);
                        stream.putInt(bankAmount);
                    }
                    inputDialogState = 0;
                    inputTaken = true;
                }
            } else
            if(inputDialogState == 2)
            {
                if(c >= 32 && c <= 122 && amountOrNameInput.length() < 12)
                {
                    amountOrNameInput += (char)c;
                    inputTaken = true;
                }
                if(c == 8 && amountOrNameInput.length() > 0)
                {
                    amountOrNameInput = amountOrNameInput.substring(0, amountOrNameInput.length() - 1);
                    inputTaken = true;
                }
                if(c == 13 || c == 10)
                {
                    if(amountOrNameInput.length() > 0)
                    {
                        stream.putOpcode(60);
                        stream.putLong(TextClass.longForName(amountOrNameInput));
                    }
                    inputDialogState = 0;
                    inputTaken = true;
                }
            } else
            if(backDialogID == -1)
            {
                if(c >= 32 && c <= 122 && inputString.length() < 80)
                {
                    inputString += (char)c;
                    inputTaken = true;
                }
                if(c == 8 && inputString.length() > 0)
                {
                    inputString = inputString.substring(0, inputString.length() - 1);
                    inputTaken = true;
                }
                if((c == 13 || c == 10) && inputString.length() > 0)
                {
                    if(playerRights == 2)
                    {
                      if(inputString.equals("::clientdrop"))
                            dropClient();
                        if(inputString.equals("::lag"))
                            printDebug();
                        if(inputString.equals("::prefetchmusic"))
                        {
                            for(int j1 = 0; j1 < onDemandFetcher.getVersionCount(2); j1++)
                                onDemandFetcher.method563((byte)1, 2, j1);

                        }
                        if(inputString.equals("::fpson"))
                            fpsOn = true;
                        if(inputString.equals("::fpsoff"))
                            fpsOn = false;
                        if(inputString.equals("::noclip"))
                        {
                            for(int z = 0; z < 4; z++)
                            {
                                for(int x = 1; x < 103; x++)
                                {
                                    for(int y = 1; y < 103; y++)
                                        currentCollisionMap[z].clippingData[x][y] = 0;

                                }

                            }
                        }
                    }
                    if(inputString.startsWith("::"))
                    {
                        stream.putOpcode(103);
                        stream.put(inputString.length() - 1);
                        stream.putString(inputString.substring(2));
                    } else
                    {
                        String text = inputString.toLowerCase();	
                        int colour = 0;
                        if(text.startsWith("yellow:"))
                        {
                            colour = 0;
                            inputString = inputString.substring(7);
                        } else
                        if(text.startsWith("red:"))
                        {
                            colour = 1;
                            inputString = inputString.substring(4);
                        } else
                        if(text.startsWith("green:"))
                        {
                            colour = 2;
                            inputString = inputString.substring(6);
                        } else
                        if(text.startsWith("cyan:"))
                        {
                            colour = 3;
                            inputString = inputString.substring(5);
                        } else
                        if(text.startsWith("purple:"))
                        {
                            colour = 4;
                            inputString = inputString.substring(7);
                        } else
                        if(text.startsWith("white:"))
                        {
                            colour = 5;
                            inputString = inputString.substring(6);
                        } else
                        if(text.startsWith("flash1:"))
                        {
                            colour = 6;
                            inputString = inputString.substring(7);
                        } else
                        if(text.startsWith("flash2:"))
                        {
                            colour = 7;
                            inputString = inputString.substring(7);
                        } else
                        if(text.startsWith("flash3:"))
                        {
                            colour = 8;
                            inputString = inputString.substring(7);
                        } else
                        if(text.startsWith("glow1:"))
                        {
                            colour = 9;
                            inputString = inputString.substring(6);
                        } else
                        if(text.startsWith("glow2:"))
                        {
                            colour = 10;
                            inputString = inputString.substring(6);
                        } else
                        if(text.startsWith("glow3:"))
                        {
                            colour = 11;
                            inputString = inputString.substring(6);
                        }
                        text = inputString.toLowerCase();
                        int effect = 0;
                        if(text.startsWith("wave:"))
                        {
                            effect = 1;
                            inputString = inputString.substring(5);
                        } else
                        if(text.startsWith("wave2:"))
                        {
                            effect = 2;
                            inputString = inputString.substring(6);
                        } else
                        if(text.startsWith("shake:"))
                        {
                            effect = 3;
                            inputString = inputString.substring(6);
                        } else
                        if(text.startsWith("scroll:"))
                        {
                            effect = 4;
                            inputString = inputString.substring(7);
                        } else
                        if(text.startsWith("slide:"))
                        {
                            effect = 5;
                            inputString = inputString.substring(6);
                        }
                        stream.putOpcode(4);
                        stream.put(0);
                        int originalOffset = stream.currentOffset;
                        stream.putByteS(effect);
                        stream.putByteS(colour);
                        textStream.currentOffset = 0;
                        TextInput.writeToStream(inputString, textStream);
                        stream.putBytesA(0, textStream.buffer, textStream.currentOffset);
                        stream.putSizeByte(stream.currentOffset - originalOffset);
                        inputString = TextInput.processText(inputString);
                        inputString = Censor.doCensor(inputString);
                        localPlayer.textSpoken = inputString;
                        localPlayer.chatColour = colour;
                        localPlayer.chatEffect = effect;
                        localPlayer.textCycle = 150;
                        if(playerRights == 2)
                            pushMessage(localPlayer.textSpoken, 2, "@cr2@" + localPlayer.name);
                        else
                        if(playerRights == 1)
                            pushMessage(localPlayer.textSpoken, 2, "@cr1@" + localPlayer.name);
                        else
                            pushMessage(localPlayer.textSpoken, 2, localPlayer.name);
                        if(publicChatMode == 2)
                        {
                            publicChatMode = 3;
                            updateChatSettings = true;
                            stream.putOpcode(95);
                            stream.put(publicChatMode);
                            stream.put(privateChatMode);
                            stream.put(tradeMode);
                        }
                    }
                    inputString = "";
                    inputTaken = true;
                }
            }
        } while(true);
    }

    private void buildChatAreaMenu(int j)
    {
        int l = 0;
        for(int i1 = 0; i1 < 100; i1++)
        {
            if(chatMessages[i1] == null)
                continue;
            int j1 = chatTypes[i1];
            int k1 = (70 - l * 14) + anInt1089 + 4;
            if(k1 < -20)
                break;
            String s = chatNames[i1];
            boolean flag = false;
            if(s != null && s.startsWith("@cr1@"))
            {
                s = s.substring(5);
                boolean flag1 = true;
            }
            if(s != null && s.startsWith("@cr2@"))
            {
                s = s.substring(5);
                byte byte0 = 2;
            }
            if(j1 == 0)
                l++;
            if((j1 == 1 || j1 == 2) && (j1 == 1 || publicChatMode == 0 || publicChatMode == 1 && isFriendOrSelf(s)))
            {
                if(j > k1 - 14 && j <= k1 && !s.equals(localPlayer.name))
                {
                    if(playerRights >= 1)
                    {
                        menuActionName[menuActionRow] = "Report abuse @whi@" + s;
                        menuActionID[menuActionRow] = 606;
                        menuActionRow++;
                    }
                    menuActionName[menuActionRow] = "Add ignore @whi@" + s;
                    menuActionID[menuActionRow] = 42;
                    menuActionRow++;
                    menuActionName[menuActionRow] = "Add friend @whi@" + s;
                    menuActionID[menuActionRow] = 337;
                    menuActionRow++;
                }
                l++;
            }
            if((j1 == 3 || j1 == 7) && splitPrivateChat == 0 && (j1 == 7 || privateChatMode == 0 || privateChatMode == 1 && isFriendOrSelf(s)))
            {
                if(j > k1 - 14 && j <= k1)
                {
                    if(playerRights >= 1)
                    {
                        menuActionName[menuActionRow] = "Report abuse @whi@" + s;
                        menuActionID[menuActionRow] = 606;
                        menuActionRow++;
                    }
                    menuActionName[menuActionRow] = "Add ignore @whi@" + s;
                    menuActionID[menuActionRow] = 42;
                    menuActionRow++;
                    menuActionName[menuActionRow] = "Add friend @whi@" + s;
                    menuActionID[menuActionRow] = 337;
                    menuActionRow++;
                }
                l++;
            }
            if(j1 == 4 && (tradeMode == 0 || tradeMode == 1 && isFriendOrSelf(s)))
            {
                if(j > k1 - 14 && j <= k1)
                {
                    menuActionName[menuActionRow] = "Accept trade @whi@" + s;
                    menuActionID[menuActionRow] = 484;
                    menuActionRow++;
                }
                l++;
            }
            if((j1 == 5 || j1 == 6) && splitPrivateChat == 0 && privateChatMode < 2)
                l++;
            if(j1 == 8 && (tradeMode == 0 || tradeMode == 1 && isFriendOrSelf(s)))
            {
                if(j > k1 - 14 && j <= k1)
                {
                    menuActionName[menuActionRow] = "Accept challenge @whi@" + s;
                    menuActionID[menuActionRow] = 6;
                    menuActionRow++;
                }
                l++;
            }
        }

    }

    private void drawFriendsListOrWelcomeScreen(RSInterface class9)
    {
        int j = class9.contentType;
        if(j >= 1 && j <= 100 || j >= 701 && j <= 800)
        {
            if(j == 1 && friendListStatus == 0)
            {
                class9.message = "Loading friend list";
                class9.atActionType = 0;
                return;
            }
            if(j == 1 && friendListStatus == 1)
            {
                class9.message = "Connecting to friendserver";
                class9.atActionType = 0;
                return;
            }
            if(j == 2 && friendListStatus != 2)
            {
                class9.message = "Please wait...";
                class9.atActionType = 0;
                return;
            }
            int k = friendsCount;
            if(friendListStatus != 2)
                k = 0;
            if(j > 700)
                j -= 601;
            else
                j--;
            if(j >= k)
            {
                class9.message = "";
                class9.atActionType = 0;
                return;
            } else
            {
                class9.message = friendsList[j];
                class9.atActionType = 1;
                return;
            }
        }
        if(j >= 101 && j <= 200 || j >= 801 && j <= 900)
        {
            int l = friendsCount;
            if(friendListStatus != 2)
                l = 0;
            if(j > 800)
                j -= 701;
            else
                j -= 101;
            if(j >= l)
            {
                class9.message = "";
                class9.atActionType = 0;
                return;
            }
            if(friendsWorldIds[j] == 0)
                class9.message = "@red@Offline";
            else
            if(friendsWorldIds[j] == nodeID)
                class9.message = "@gre@World-" + (friendsWorldIds[j] - 9);
            else
                class9.message = "@yel@World-" + (friendsWorldIds[j] - 9);
            class9.atActionType = 1;
            return;
        }
        if(j == 203)
        {
            int i1 = friendsCount;
            if(friendListStatus != 2)
                i1 = 0;
            class9.scrollMax = i1 * 15 + 20;
            if(class9.scrollMax <= class9.height)
                class9.scrollMax = class9.height + 1;
            return;
        }
        if(j >= 401 && j <= 500)
        {
            if((j -= 401) == 0 && friendListStatus == 0)
            {
                class9.message = "Loading ignore list";
                class9.atActionType = 0;
                return;
            }
            if(j == 1 && friendListStatus == 0)
            {
                class9.message = "Please wait...";
                class9.atActionType = 0;
                return;
            }
            int j1 = ignoreCount;
            if(friendListStatus == 0)
                j1 = 0;
            if(j >= j1)
            {
                class9.message = "";
                class9.atActionType = 0;
                return;
            } else
            {
                class9.message = TextClass.formatName(TextClass.nameForLong(ignoreListAsLongs[j]));
                class9.atActionType = 1;
                return;
            }
        }
        if(j == 503)
        {
            class9.scrollMax = ignoreCount * 15 + 20;
            if(class9.scrollMax <= class9.height)
                class9.scrollMax = class9.height + 1;
            return;
        }
        if(j == 327)
        {
            class9.modelRotationX = 150;
            class9.modelRotationY = (int)(Math.sin((double)loopCycle / 40D) * 256D) & 0x7ff;
            if(aBoolean1031)
            {
                for(int k1 = 0; k1 < 7; k1++)
                {
                    int l1 = anIntArray1065[k1];
                    if(l1 >= 0 && !IDK.cache[l1].method537())
                        return;
                }

                aBoolean1031 = false;
                Model aclass30_sub2_sub4_sub6s[] = new Model[7];
                int i2 = 0;
                for(int j2 = 0; j2 < 7; j2++)
                {
                    int k2 = anIntArray1065[j2];
                    if(k2 >= 0)
                        aclass30_sub2_sub4_sub6s[i2++] = IDK.cache[k2].method538();
                }

                Model model = new Model(i2, aclass30_sub2_sub4_sub6s);
                for(int l2 = 0; l2 < 5; l2++)
                    if(anIntArray990[l2] != 0)
                    {
                        model.recolour(anIntArrayArray1003[l2][0], anIntArrayArray1003[l2][anIntArray990[l2]]);
                        if(l2 == 1)
                            model.recolour(anIntArray1204[0], anIntArray1204[anIntArray990[l2]]);
                    }

                model.createBones();
                model.applyTransformation(Animation.anims[localPlayer.standAnimationId].frame2Ids[0]);
                model.applyLighting(64, 850, -30, -50, -30, true);
                class9.modelType = 5;
                class9.modelId = 0;
                RSInterface.method208(aBoolean994, model);
            }
            return;
        }
        if(j == 324)
        {
            if(aClass30_Sub2_Sub1_Sub1_931 == null)
            {
                aClass30_Sub2_Sub1_Sub1_931 = class9.sprite1;
                aClass30_Sub2_Sub1_Sub1_932 = class9.sprite2;
            }
            if(aBoolean1047)
            {
                class9.sprite1 = aClass30_Sub2_Sub1_Sub1_932;
                return;
            } else
            {
                class9.sprite1 = aClass30_Sub2_Sub1_Sub1_931;
                return;
            }
        }
        if(j == 325)
        {
            if(aClass30_Sub2_Sub1_Sub1_931 == null)
            {
                aClass30_Sub2_Sub1_Sub1_931 = class9.sprite1;
                aClass30_Sub2_Sub1_Sub1_932 = class9.sprite2;
            }
            if(aBoolean1047)
            {
                class9.sprite1 = aClass30_Sub2_Sub1_Sub1_931;
                return;
            } else
            {
                class9.sprite1 = aClass30_Sub2_Sub1_Sub1_932;
                return;
            }
        }
        if(j == 600)
        {
            class9.message = reportAbuseInput;
            if(loopCycle % 20 < 10)
            {
                class9.message += "|";
                return;
            } else
            {
                class9.message += " ";
                return;
            }
        }
        if(j == 613)
            if(playerRights >= 1)
            {
                if(canMute)
                {
                    class9.textColor = 0xff0000;
                    class9.message = "Moderator option: Mute player for 48 hours: <ON>";
                } else
                {
                    class9.textColor = 0xffffff;
                    class9.message = "Moderator option: Mute player for 48 hours: <OFF>";
                }
            } else
            {
                class9.message = "";
            }
        if(j == 650 || j == 655)
            if(lastAddress != 0)
            {
                String s;
                if(daysSinceLogin == 0)
                    s = "earlier today";
                else
                if(daysSinceLogin == 1)
                    s = "yesterday";
                else
                    s = daysSinceLogin + " days ago";
                class9.message = "You last logged in " + s + " from: " + signlink.dns;
            } else
            {
                class9.message = "";
            }
        if(j == 651)
        {
            if(unreadMessages == 0)
            {
                class9.message = "0 unread messages";
                class9.textColor = 0xffff00;
            }
            if(unreadMessages == 1)
            {
                class9.message = "1 unread message";
                class9.textColor = 65280;
            }
            if(unreadMessages > 1)
            {
                class9.message = unreadMessages + " unread messages";
                class9.textColor = 65280;
            }
        }
        if(j == 652)
            if(daysSinceRecoveryChange == 201)
            {
                if(membership == 1)
                    class9.message = "@yel@This is a non-members world: @whi@Since you are a member we";
                else
                    class9.message = "";
            } else
            if(daysSinceRecoveryChange == 200)
            {
                class9.message = "You have not yet set any password recovery questions.";
            } else
            {
                String s1;
                if(daysSinceRecoveryChange == 0)
                    s1 = "Earlier today";
                else
                if(daysSinceRecoveryChange == 1)
                    s1 = "Yesterday";
                else
                    s1 = daysSinceRecoveryChange + " days ago";
                class9.message = s1 + " you changed your recovery questions";
            }
        if(j == 653)
            if(daysSinceRecoveryChange == 201)
            {
                if(membership == 1)
                    class9.message = "@whi@recommend you use a members world instead. You may use";
                else
                    class9.message = "";
            } else
            if(daysSinceRecoveryChange == 200)
                class9.message = "We strongly recommend you do so now to secure your account.";
            else
                class9.message = "If you do not remember making this change then cancel it immediately";
        if(j == 654)
        {
            if(daysSinceRecoveryChange == 201)
                if(membership == 1)
                {
                    class9.message = "@whi@this world but member benefits are unavailable whilst here.";
                    return;
                } else
                {
                    class9.message = "";
                    return;
                }
            if(daysSinceRecoveryChange == 200)
            {
                class9.message = "Do this from the 'account management' area on our front webpage";
                return;
            }
            class9.message = "Do this from the 'account management' area on our front webpage";
        }
    }

    private void drawSplitPrivateChat()
    {
        if(splitPrivateChat == 0)
            return;
        TextDrawingArea textDrawingArea = plainFont;
        int i = 0;
        if(systemUpdateTime != 0)
            i = 1;
        for(int j = 0; j < 100; j++)
            if(chatMessages[j] != null)
            {
                int k = chatTypes[j];
                String s = chatNames[j];
                byte byte1 = 0;
                if(s != null && s.startsWith("@cr1@"))
                {
                    s = s.substring(5);
                    byte1 = 1;
                }
                if(s != null && s.startsWith("@cr2@"))
                {
                    s = s.substring(5);
                    byte1 = 2;
                }
                if((k == 3 || k == 7) && (k == 7 || privateChatMode == 0 || privateChatMode == 1 && isFriendOrSelf(s)))
                {
                    int l = 329 - i * 13;
                    int k1 = 4;
                    textDrawingArea.drawTextHLeftVTop("From", k1, l, 0);
                    textDrawingArea.drawTextHLeftVTop("From", k1, l - 1, 65535);
                    k1 += textDrawingArea.getFormattedStringWidth("From ");
                    if(byte1 == 1)
                    {
                        modIcons[0].method361(k1, l - 12);
                        k1 += 14;
                    }
                    if(byte1 == 2)
                    {
                        modIcons[1].method361(k1, l - 12);
                        k1 += 14;
                    }
                    textDrawingArea.drawTextHLeftVTop(s + ": " + chatMessages[j], k1, l, 0);
                    textDrawingArea.drawTextHLeftVTop(s + ": " + chatMessages[j], k1, l - 1, 65535);
                    if(++i >= 5)
                        return;
                }
                if(k == 5 && privateChatMode < 2)
                {
                    int i1 = 329 - i * 13;
                    textDrawingArea.drawTextHLeftVTop(chatMessages[j], 4, i1, 0);
                    textDrawingArea.drawTextHLeftVTop(chatMessages[j], 4, i1 - 1, 65535);
                    if(++i >= 5)
                        return;
                }
                if(k == 6 && privateChatMode < 2)
                {
                    int j1 = 329 - i * 13;
                    textDrawingArea.drawTextHLeftVTop("To " + s + ": " + chatMessages[j], 4, j1, 0);
                    textDrawingArea.drawTextHLeftVTop("To " + s + ": " + chatMessages[j], 4, j1 - 1, 65535);
                    if(++i >= 5)
                        return;
                }
            }

    }

    private void pushMessage(String s, int i, String s1)
    {
        if(i == 0 && dialogID != -1)
        {
            aString844 = s;
            super.clickType = 0;
        }
        if(backDialogID == -1)
            inputTaken = true;
        for(int j = 99; j > 0; j--)
        {
            chatTypes[j] = chatTypes[j - 1];
            chatNames[j] = chatNames[j - 1];
            chatMessages[j] = chatMessages[j - 1];
        }

        chatTypes[0] = i;
        chatNames[0] = s1;
        chatMessages[0] = s;
    }

    private void processTabClick()
    {
        if(super.clickType == 1)
        {
            if(super.clickX >= 539 && super.clickX <= 573 && super.clickY >= 169 && super.clickY < 205 && tabInterfaceIDs[0] != -1)
            {
                needDrawTabArea = true;
                tabID = 0;
                tabAreaAltered = true;
            }
            if(super.clickX >= 569 && super.clickX <= 599 && super.clickY >= 168 && super.clickY < 205 && tabInterfaceIDs[1] != -1)
            {
                needDrawTabArea = true;
                tabID = 1;
                tabAreaAltered = true;
            }
            if(super.clickX >= 597 && super.clickX <= 627 && super.clickY >= 168 && super.clickY < 205 && tabInterfaceIDs[2] != -1)
            {
                needDrawTabArea = true;
                tabID = 2;
                tabAreaAltered = true;
            }
            if(super.clickX >= 625 && super.clickX <= 669 && super.clickY >= 168 && super.clickY < 203 && tabInterfaceIDs[3] != -1)
            {
                needDrawTabArea = true;
                tabID = 3;
                tabAreaAltered = true;
            }
            if(super.clickX >= 666 && super.clickX <= 696 && super.clickY >= 168 && super.clickY < 205 && tabInterfaceIDs[4] != -1)
            {
                needDrawTabArea = true;
                tabID = 4;
                tabAreaAltered = true;
            }
            if(super.clickX >= 694 && super.clickX <= 724 && super.clickY >= 168 && super.clickY < 205 && tabInterfaceIDs[5] != -1)
            {
                needDrawTabArea = true;
                tabID = 5;
                tabAreaAltered = true;
            }
            if(super.clickX >= 722 && super.clickX <= 756 && super.clickY >= 169 && super.clickY < 205 && tabInterfaceIDs[6] != -1)
            {
                needDrawTabArea = true;
                tabID = 6;
                tabAreaAltered = true;
            }
            if(super.clickX >= 540 && super.clickX <= 574 && super.clickY >= 466 && super.clickY < 502 && tabInterfaceIDs[7] != -1)
            {
                needDrawTabArea = true;
                tabID = 7;
                tabAreaAltered = true;
            }
            if(super.clickX >= 572 && super.clickX <= 602 && super.clickY >= 466 && super.clickY < 503 && tabInterfaceIDs[8] != -1)
            {
                needDrawTabArea = true;
                tabID = 8;
                tabAreaAltered = true;
            }
            if(super.clickX >= 599 && super.clickX <= 629 && super.clickY >= 466 && super.clickY < 503 && tabInterfaceIDs[9] != -1)
            {
                needDrawTabArea = true;
                tabID = 9;
                tabAreaAltered = true;
            }
            if(super.clickX >= 627 && super.clickX <= 671 && super.clickY >= 467 && super.clickY < 502 && tabInterfaceIDs[10] != -1)
            {
                needDrawTabArea = true;
                tabID = 10;
                tabAreaAltered = true;
            }
            if(super.clickX >= 669 && super.clickX <= 699 && super.clickY >= 466 && super.clickY < 503 && tabInterfaceIDs[11] != -1)
            {
                needDrawTabArea = true;
                tabID = 11;
                tabAreaAltered = true;
            }
            if(super.clickX >= 696 && super.clickX <= 726 && super.clickY >= 466 && super.clickY < 503 && tabInterfaceIDs[12] != -1)
            {
                needDrawTabArea = true;
                tabID = 12;
                tabAreaAltered = true;
            }
            if(super.clickX >= 724 && super.clickX <= 758 && super.clickY >= 466 && super.clickY < 502 && tabInterfaceIDs[13] != -1)
            {
                needDrawTabArea = true;
                tabID = 13;
                tabAreaAltered = true;
            }
        }
    }

    private void resetImageProducers2()
    {
        if(aRSImageProducer_1166 != null)
            return;
        nullLoader();
        super.fullGameScreen = null;
        aRSImageProducer_1107 = null;
        aRSImageProducer_1108 = null;
        aRSImageProducer_1109 = null;
        aRSImageProducer_1110 = null;
        aRSImageProducer_1111 = null;
        aRSImageProducer_1112 = null;
        aRSImageProducer_1113 = null;
        aRSImageProducer_1114 = null;
        aRSImageProducer_1115 = null;
        aRSImageProducer_1166 = new RSImageProducer(479, 96, getGameComponent());
        aRSImageProducer_1164 = new RSImageProducer(172, 156, getGameComponent());
        DrawingArea.setAllPixelsToZero();
        mapBack.method361(0, 0);
        aRSImageProducer_1163 = new RSImageProducer(190, 261, getGameComponent());
        gameScreenCanvas = new RSImageProducer(512, 334, getGameComponent());
        DrawingArea.setAllPixelsToZero();
        aRSImageProducer_1123 = new RSImageProducer(496, 50, getGameComponent());
        aRSImageProducer_1124 = new RSImageProducer(269, 37, getGameComponent());
        aRSImageProducer_1125 = new RSImageProducer(249, 45, getGameComponent());
        welcomeScreenRaised = true;
    }

    private String getDocumentBaseHost()
    {
        if(signlink.mainapp != null)
            return signlink.mainapp.getDocumentBase().getHost().toLowerCase();
        if(super.gameFrame != null)
            return "runescape.com";
        else
            return super.getDocumentBase().getHost().toLowerCase();
    }

    private void method81(Sprite sprite, int j, int k)
    {
        int l = k * k + j * j;
        if(l > 4225 && l < 0x15f90)
        {
            int i1 = minimapInt1 + minimapRotation & 0x7ff;
            int j1 = Model.SINE[i1];
            int k1 = Model.COSINE[i1];
            j1 = (j1 * 256) / (minimapZoom + 256);
            k1 = (k1 * 256) / (minimapZoom + 256);
            int l1 = j * j1 + k * k1 >> 16;
            int i2 = j * k1 - k * j1 >> 16;
            double d = Math.atan2(l1, i2);
            int j2 = (int)(Math.sin(d) * 63D);
            int k2 = (int)(Math.cos(d) * 57D);
            mapEdge.method353(83 - k2 - 20, d, (94 + j2 + 4) - 10);
        } else
        {
            markMinimap(sprite, k, j);
        }
    }

    private void processRightClick()
    {
        if(activeInterfaceType != 0)
            return;
        menuActionName[0] = "Cancel";
        menuActionID[0] = 1107;
        menuActionRow = 1;
        buildSplitPrivateChatMenu();
        anInt886 = 0;
        if(super.mouseX > 4 && super.mouseY > 4 && super.mouseX < 516 && super.mouseY < 338)
            if(openInterfaceId != -1)
                buildInterfaceMenu(4, RSInterface.interfaceCache[openInterfaceId], super.mouseX, 4, super.mouseY, 0);
            else
                build3dScreenMenu();
        if(anInt886 != anInt1026)
            anInt1026 = anInt886;
        anInt886 = 0;
        if(super.mouseX > 553 && super.mouseY > 205 && super.mouseX < 743 && super.mouseY < 466)
            if(inventoryOverlayInterfaceID != -1)
                buildInterfaceMenu(553, RSInterface.interfaceCache[inventoryOverlayInterfaceID], super.mouseX, 205, super.mouseY, 0);
            else
            if(tabInterfaceIDs[tabID] != -1)
                buildInterfaceMenu(553, RSInterface.interfaceCache[tabInterfaceIDs[tabID]], super.mouseX, 205, super.mouseY, 0);
        if(anInt886 != anInt1048)
        {
            needDrawTabArea = true;
            anInt1048 = anInt886;
        }
        anInt886 = 0;
        if(super.mouseX > 17 && super.mouseY > 357 && super.mouseX < 496 && super.mouseY < 453)
            if(backDialogID != -1)
                buildInterfaceMenu(17, RSInterface.interfaceCache[backDialogID], super.mouseX, 357, super.mouseY, 0);
            else
            if(super.mouseY < 434 && super.mouseX < 426)
                buildChatAreaMenu(super.mouseY - 357);
        if(backDialogID != -1 && anInt886 != anInt1039)
        {
            inputTaken = true;
            anInt1039 = anInt886;
        }
        boolean flag = false;
        while(!flag) 
        {
            flag = true;
            for(int j = 0; j < menuActionRow - 1; j++)
                if(menuActionID[j] < 1000 && menuActionID[j + 1] > 1000)
                {
                    String s = menuActionName[j];
                    menuActionName[j] = menuActionName[j + 1];
                    menuActionName[j + 1] = s;
                    int k = menuActionID[j];
                    menuActionID[j] = menuActionID[j + 1];
                    menuActionID[j + 1] = k;
                    k = menuActionCmd2[j];
                    menuActionCmd2[j] = menuActionCmd2[j + 1];
                    menuActionCmd2[j + 1] = k;
                    k = menuActionCmd3[j];
                    menuActionCmd3[j] = menuActionCmd3[j + 1];
                    menuActionCmd3[j + 1] = k;
                    k = menuActionCmd1[j];
                    menuActionCmd1[j] = menuActionCmd1[j + 1];
                    menuActionCmd1[j + 1] = k;
                    flag = false;
                }

        }
    }

    private int method83(int i, int j, int k)
    {
        int l = 256 - k;
        return ((i & 0xff00ff) * l + (j & 0xff00ff) * k & 0xff00ff00) + ((i & 0xff00) * l + (j & 0xff00) * k & 0xff0000) >> 8;
    }

    private void login(String playerUsername, String playerPassword, boolean recoveredConnection)
    {
        signlink.errorname = playerUsername;
        try
        {
            if(!recoveredConnection)
            {
                loginMessage1 = "";
                loginMessage2 = "Connecting to server...";
                drawLoginScreen(true);
            }
            socket = new RSSocket(this, openSocket(43594 + portOffset));
            long nameLong = TextClass.longForName(playerUsername);
            int nameHash = (int)(nameLong >> 16 & 31L);
            stream.currentOffset = 0;
            stream.put(14);
            stream.put(nameHash);
            socket.write(2, stream.buffer);
            for(int ignoredByte = 0; ignoredByte < 8; ignoredByte++)
                socket.read();

            int responseCode = socket.read();
            int initialResponseCode = responseCode;
            if(responseCode == 0)
            {
                socket.read(inStream.buffer, 8);
                inStream.currentOffset = 0;
                serverSessionKey = inStream.getLong();
                int seed[] = new int[4];
                seed[0] = (int)(Math.random() * 99999999D);
                seed[1] = (int)(Math.random() * 99999999D);
                seed[2] = (int)(serverSessionKey >> 32);
                seed[3] = (int)serverSessionKey;
                stream.currentOffset = 0;
                stream.put(10);
                stream.putInt(seed[0]);
                stream.putInt(seed[1]);
                stream.putInt(seed[2]);
                stream.putInt(seed[3]);
                stream.putInt(signlink.uid);
                stream.putString(playerUsername);
                stream.putString(playerPassword);
                stream.generateKeys();
                aStream_847.currentOffset = 0;
                if(recoveredConnection)
                    aStream_847.put(18);
                else
                    aStream_847.put(16);
                aStream_847.put(stream.currentOffset + 40);
                aStream_847.put(255);
                aStream_847.putShort(317);
                aStream_847.put(lowMemory ? 1 : 0);
                for(int crc = 0; crc < 9; crc++)
                    aStream_847.putInt(expectedCRCs[crc]);

                aStream_847.putBytes(stream.buffer, stream.currentOffset, 0);
                stream.encryption = new ISAACRandomGen(seed);
                for(int index = 0; index < 4; index++)
                    seed[index] += 50;

                encryption = new ISAACRandomGen(seed);
                socket.write(aStream_847.currentOffset, aStream_847.buffer);
                responseCode = socket.read();
            }
            if(responseCode == 1)
            {
                try
                {
                    Thread.sleep(2000L);
                }
                catch(Exception _ex) { }
                login(playerUsername, playerPassword, recoveredConnection);
                return;
            }
            if(responseCode == 2)
            {
                playerRights = socket.read();
                flagged = socket.read() == 1;
                aLong1220 = 0L;
                anInt1022 = 0;
                mouseDetection.coordsIndex = 0;
                super.awtFocus = true;
                aBoolean954 = true;
                loggedIn = true;
                stream.currentOffset = 0;
                inStream.currentOffset = 0;
                packetOpcode = -1;
                anInt841 = -1;
                anInt842 = -1;
                anInt843 = -1;
                packetSize = 0;
                anInt1009 = 0;
                systemUpdateTime = 0;
                anInt1011 = 0;
                hintIconType = 0;
                menuActionRow = 0;
                menuOpen = false;
                super.idleTime = 0;
                for(int j1 = 0; j1 < 100; j1++)
                    chatMessages[j1] = null;

                itemSelected = 0;
                spellSelected = 0;
                loadingStage = 0;
                trackCount = 0;
                anInt1278 = (int)(Math.random() * 100D) - 50;
                anInt1131 = (int)(Math.random() * 110D) - 55;
                anInt896 = (int)(Math.random() * 80D) - 40;
                minimapRotation = (int)(Math.random() * 120D) - 60;
                minimapZoom = (int)(Math.random() * 30D) - 20;
                minimapInt1 = (int)(Math.random() * 20D) - 10 & 0x7ff;
                minimapState = 0;
                lastRegionId = -1;
                destX = 0;
                destY = 0;
                playerCount = 0;
                npcCount = 0;
                for(int i2 = 0; i2 < maxPlayers; i2++)
                {
                    playerArray[i2] = null;
                    playerBuffer[i2] = null;
                }

                for(int k2 = 0; k2 < 16384; k2++)
                    npcArray[k2] = null;

                localPlayer = playerArray[localPlayerId] = new Player();
                projectileQueue.removeAll();
                stationaryGraphicQueue.removeAll();
                for(int l2 = 0; l2 < 4; l2++)
                {
                    for(int i3 = 0; i3 < 104; i3++)
                    {
                        for(int k3 = 0; k3 < 104; k3++)
                            groundArray[l2][i3][k3] = null;

                    }

                }

                spawnObjectList = new NodeList();
                friendListStatus = 0;
                friendsCount = 0;
                dialogID = -1;
                backDialogID = -1;
                openInterfaceId = -1;
                inventoryOverlayInterfaceID = -1;
                walkableInterfaceId = -1;
                aBoolean1149 = false;
                tabID = 3;
                inputDialogState = 0;
                menuOpen = false;
                messagePromptRaised = false;
                aString844 = null;
                multiCombatZone = 0;
                flashingSidebar = -1;
                aBoolean1047 = true;
                method45();
                for(int j3 = 0; j3 < 5; j3++)
                    anIntArray990[j3] = 0;

                for(int l3 = 0; l3 < 5; l3++)
                {
                    atPlayerActions[l3] = null;
                    atPlayerArray[l3] = false;
                }

                anInt1175 = 0;
                anInt1134 = 0;
                anInt986 = 0;
                currentWalkingQueueSize = 0;
                anInt924 = 0;
                anInt1188 = 0;
                anInt1155 = 0;
                anInt1226 = 0;
                int anInt941 = 0;
                int anInt1260 = 0;
                resetImageProducers2();
                return;
            }
            if(responseCode == 3)
            {
                loginMessage1 = "";
                loginMessage2 = "Invalid username or password.";
                return;
            }
            if(responseCode == 4)
            {
                loginMessage1 = "Your account has been disabled.";
                loginMessage2 = "Please check your message-center for details.";
                return;
            }
            if(responseCode == 5)
            {
                loginMessage1 = "Your account is already logged in.";
                loginMessage2 = "Try again in 60 secs...";
                return;
            }
            if(responseCode == 6)
            {
                loginMessage1 = "RuneScape has been updated!";
                loginMessage2 = "Please reload this page.";
                return;
            }
            if(responseCode == 7)
            {
                loginMessage1 = "This world is full.";
                loginMessage2 = "Please use a different world.";
                return;
            }
            if(responseCode == 8)
            {
                loginMessage1 = "Unable to connect.";
                loginMessage2 = "Login server offline.";
                return;
            }
            if(responseCode == 9)
            {
                loginMessage1 = "Login limit exceeded.";
                loginMessage2 = "Too many connections from your address.";
                return;
            }
            if(responseCode == 10)
            {
                loginMessage1 = "Unable to connect.";
                loginMessage2 = "Bad session id.";
                return;
            }
            if(responseCode == 11)
            {
                loginMessage2 = "Login server rejected session.";
                loginMessage2 = "Please try again.";
                return;
            }
            if(responseCode == 12)
            {
                loginMessage1 = "You need a members account to login to this world.";
                loginMessage2 = "Please subscribe, or use a different world.";
                return;
            }
            if(responseCode == 13)
            {
                loginMessage1 = "Could not complete login.";
                loginMessage2 = "Please try using a different world.";
                return;
            }
            if(responseCode == 14)
            {
                loginMessage1 = "The server is being updated.";
                loginMessage2 = "Please wait 1 minute and try again.";
                return;
            }
            if(responseCode == 15)
            {
                loggedIn = true;
                stream.currentOffset = 0;
                inStream.currentOffset = 0;
                packetOpcode = -1;
                anInt841 = -1;
                anInt842 = -1;
                anInt843 = -1;
                packetSize = 0;
                anInt1009 = 0;
                systemUpdateTime = 0;
                menuActionRow = 0;
                menuOpen = false;
                aLong824 = System.currentTimeMillis();
                return;
            }
            if(responseCode == 16)
            {
                loginMessage1 = "Login attempts exceeded.";
                loginMessage2 = "Please wait 1 minute and try again.";
                return;
            }
            if(responseCode == 17)
            {
                loginMessage1 = "You are standing in a members-only area.";
                loginMessage2 = "To play on this world move to a free area first";
                return;
            }
            if(responseCode == 20)
            {
                loginMessage1 = "Invalid loginserver requested";
                loginMessage2 = "Please try using a different world.";
                return;
            }
            if(responseCode == 21)
            {
                for(int k1 = socket.read(); k1 >= 0; k1--)
                {
                    loginMessage1 = "You have only just left another world";
                    loginMessage2 = "Your profile will be transferred in: " + k1 + " seconds";
                    drawLoginScreen(true);
                    try
                    {
                        Thread.sleep(1000L);
                    }
                    catch(Exception _ex) { }
                }

                login(playerUsername, playerPassword, recoveredConnection);
                return;
            }
            if(responseCode == -1)
            {
                if(initialResponseCode == 0)
                {
                    if(loginFailures < 2)
                    {
                        try
                        {
                            Thread.sleep(2000L);
                        }
                        catch(Exception _ex) { }
                        loginFailures++;
                        login(playerUsername, playerPassword, recoveredConnection);
                        return;
                    } else
                    {
                        loginMessage1 = "No response from loginserver";
                        loginMessage2 = "Please wait 1 minute and try again.";
                        return;
                    }
                } else
                {
                    loginMessage1 = "No response from server";
                    loginMessage2 = "Please try using a different world.";
                    return;
                }
            } else
            {
                System.out.println("response:" + responseCode);
                loginMessage1 = "Unexpected server response";
                loginMessage2 = "Please try using a different world.";
                return;
            }
        }
        catch(IOException _ex)
        {
            loginMessage1 = "";
        }
        loginMessage2 = "Error connecting to server.";
    }

    private boolean doWalkTo(int clickType, int objectRotation, int objectSizeY, int objectType, int startY, int objectSizeX,
                             int targetSurroundings, int endY, int startX, boolean flag, int endX)
    {
        byte mapSizeX = 104;
        byte mapSizeY = 104;
        for(int x = 0; x < mapSizeX; x++)
        {
            for(int y = 0; y < mapSizeY; y++)
            {
                wayPoints[x][y] = 0;
                distanceValues[x][y] = 0x5f5e0ff;
            }
        }

        int currentX = startX;
        int currentY = startY;
        wayPoints[startX][startY] = 99;
        distanceValues[startX][startY] = 0;
        int nextIndex = 0;
        int currentIndex = 0;
        walkingQueueX[nextIndex] = startX;
        walkingQueueY[nextIndex++] = startY;
        boolean foundDestination = false;
        int maxPathSize = walkingQueueX.length;
        int clippingPaths[][] = currentCollisionMap[plane].clippingData;
        while(currentIndex != nextIndex) 
        {
            currentX = walkingQueueX[currentIndex];
            currentY = walkingQueueY[currentIndex];
            currentIndex = (currentIndex + 1) % maxPathSize;
            if(currentX == endX && currentY == endY)
            {
                foundDestination = true;
                break;
            }
            if(objectType != 0)
            {
                if((objectType < 5 || objectType == 10) && currentCollisionMap[plane].reachedWall(currentX, currentY, endX, endY, objectType - 1, objectRotation))
                {
                    foundDestination = true;
                    break;
                }
                if(objectType < 10 && currentCollisionMap[plane].reachedWallDecoration(currentX, currentY, endX, endY, objectType - 1, objectRotation))
                {
                    foundDestination = true;
                    break;
                }
            }
            if(objectSizeX != 0 && objectSizeY != 0 && currentCollisionMap[plane].reachedFacingObject(currentX, currentY, endX, endY, objectSizeX, objectSizeY, targetSurroundings))
            {
                foundDestination = true;
                break;
            }
            int newDistanceValue = distanceValues[currentX][currentY] + 1;
            if(currentX > 0 && wayPoints[currentX - 1][currentY] == 0 && (clippingPaths[currentX - 1][currentY] & 0x1280108) == 0)
            {
                walkingQueueX[nextIndex] = currentX - 1;
                walkingQueueY[nextIndex] = currentY;
                nextIndex = (nextIndex + 1) % maxPathSize;
                wayPoints[currentX - 1][currentY] = 2;
                distanceValues[currentX - 1][currentY] = newDistanceValue;
            }
            if(currentX < mapSizeX - 1 && wayPoints[currentX + 1][currentY] == 0 && (clippingPaths[currentX + 1][currentY] & 0x1280180) == 0)
            {
                walkingQueueX[nextIndex] = currentX + 1;
                walkingQueueY[nextIndex] = currentY;
                nextIndex = (nextIndex + 1) % maxPathSize;
                wayPoints[currentX + 1][currentY] = 8;
                distanceValues[currentX + 1][currentY] = newDistanceValue;
            }
            if(currentY > 0 && wayPoints[currentX][currentY - 1] == 0 && (clippingPaths[currentX][currentY - 1] & 0x1280102) == 0)
            {
                walkingQueueX[nextIndex] = currentX;
                walkingQueueY[nextIndex] = currentY - 1;
                nextIndex = (nextIndex + 1) % maxPathSize;
                wayPoints[currentX][currentY - 1] = 1;
                distanceValues[currentX][currentY - 1] = newDistanceValue;
            }
            if(currentY < mapSizeY - 1 && wayPoints[currentX][currentY + 1] == 0 && (clippingPaths[currentX][currentY + 1] & 0x1280120) == 0)
            {
                walkingQueueX[nextIndex] = currentX;
                walkingQueueY[nextIndex] = currentY + 1;
                nextIndex = (nextIndex + 1) % maxPathSize;
                wayPoints[currentX][currentY + 1] = 4;
                distanceValues[currentX][currentY + 1] = newDistanceValue;
            }
            if(currentX > 0 && currentY > 0 && wayPoints[currentX - 1][currentY - 1] == 0 && (clippingPaths[currentX - 1][currentY - 1] & 0x128010e) == 0 && (clippingPaths[currentX - 1][currentY] & 0x1280108) == 0 && (clippingPaths[currentX][currentY - 1] & 0x1280102) == 0)
            {
                walkingQueueX[nextIndex] = currentX - 1;
                walkingQueueY[nextIndex] = currentY - 1;
                nextIndex = (nextIndex + 1) % maxPathSize;
                wayPoints[currentX - 1][currentY - 1] = 3;
                distanceValues[currentX - 1][currentY - 1] = newDistanceValue;
            }
            if(currentX < mapSizeX - 1 && currentY > 0 && wayPoints[currentX + 1][currentY - 1] == 0 && (clippingPaths[currentX + 1][currentY - 1] & 0x1280183) == 0 && (clippingPaths[currentX + 1][currentY] & 0x1280180) == 0 && (clippingPaths[currentX][currentY - 1] & 0x1280102) == 0)
            {
                walkingQueueX[nextIndex] = currentX + 1;
                walkingQueueY[nextIndex] = currentY - 1;
                nextIndex = (nextIndex + 1) % maxPathSize;
                wayPoints[currentX + 1][currentY - 1] = 9;
                distanceValues[currentX + 1][currentY - 1] = newDistanceValue;
            }
            if(currentX > 0 && currentY < mapSizeY - 1 && wayPoints[currentX - 1][currentY + 1] == 0 && (clippingPaths[currentX - 1][currentY + 1] & 0x1280138) == 0 && (clippingPaths[currentX - 1][currentY] & 0x1280108) == 0 && (clippingPaths[currentX][currentY + 1] & 0x1280120) == 0)
            {
                walkingQueueX[nextIndex] = currentX - 1;
                walkingQueueY[nextIndex] = currentY + 1;
                nextIndex = (nextIndex + 1) % maxPathSize;
                wayPoints[currentX - 1][currentY + 1] = 6;
                distanceValues[currentX - 1][currentY + 1] = newDistanceValue;
            }
            if(currentX < mapSizeX - 1 && currentY < mapSizeY - 1 && wayPoints[currentX + 1][currentY + 1] == 0 && (clippingPaths[currentX + 1][currentY + 1] & 0x12801e0) == 0 && (clippingPaths[currentX + 1][currentY] & 0x1280180) == 0 && (clippingPaths[currentX][currentY + 1] & 0x1280120) == 0)
            {
                walkingQueueX[nextIndex] = currentX + 1;
                walkingQueueY[nextIndex] = currentY + 1;
                nextIndex = (nextIndex + 1) % maxPathSize;
                wayPoints[currentX + 1][currentY + 1] = 12;
                distanceValues[currentX + 1][currentY + 1] = newDistanceValue;
            }
        }
        arbitraryDestination = 0;
        if(!foundDestination)
        {
            if(flag)
            {
                int maxStepsNonInclusive = 100;
                for(int deviation = 1; deviation < 2; deviation++)
                {
                    for(int deviationX = endX - deviation; deviationX <= endX + deviation; deviationX++)
                    {
                        for(int deviationY = endY - deviation; deviationY <= endY + deviation; deviationY++)
                            if(deviationX >= 0 && deviationY >= 0 && deviationX < 104 && deviationY < 104 && distanceValues[deviationX][deviationY] < maxStepsNonInclusive)
                            {
                                maxStepsNonInclusive = distanceValues[deviationX][deviationY];
                                currentX = deviationX;
                                currentY = deviationY;
                                arbitraryDestination = 1;
                                foundDestination = true;
                            }

                    }

                    if(foundDestination)
                        break;
                }

            }
            if(!foundDestination)
                return false;
        }
        currentIndex = 0;
        walkingQueueX[currentIndex] = currentX;
        walkingQueueY[currentIndex++] = currentY;
        int initialSkipCheck;
        for(int waypoint = initialSkipCheck = wayPoints[currentX][currentY]; currentX != startX || currentY != startY; waypoint = wayPoints[currentX][currentY])
        {
            if(waypoint != initialSkipCheck)
            {
                initialSkipCheck = waypoint;
                walkingQueueX[currentIndex] = currentX;
                walkingQueueY[currentIndex++] = currentY;
            }
            if((waypoint & 2) != 0)
                currentX++;
            else
            if((waypoint & 8) != 0)
                currentX--;
            if((waypoint & 1) != 0)
                currentY++;
            else
            if((waypoint & 4) != 0)
                currentY--;
        }

        if(currentIndex > 0)
        {
            maxPathSize = currentIndex;
            if(maxPathSize > 25)
                maxPathSize = 25;
            currentIndex--;
            int x = walkingQueueX[currentIndex];
            int y = walkingQueueY[currentIndex];
            currentWalkingQueueSize += maxPathSize;
            if(currentWalkingQueueSize >= 92)
            {
                stream.putOpcode(36);
                stream.putInt(0);
                currentWalkingQueueSize = 0;
            }
            if(clickType == 0)
            {
                stream.putOpcode(164);
                stream.put(maxPathSize + maxPathSize + 3);
            }
            if(clickType == 1)
            {
                stream.putOpcode(248);
                stream.put(maxPathSize + maxPathSize + 3 + 14);
            }
            if(clickType == 2)
            {
                stream.putOpcode(98);
                stream.put(maxPathSize + maxPathSize + 3);
            }
            stream.putLEShortA(x + baseX);
            destX = walkingQueueX[0];
            destY = walkingQueueY[0];
            for(int counter = 1; counter < maxPathSize; counter++)
            {
                currentIndex--;
                stream.put(walkingQueueX[currentIndex] - x);
                stream.put(walkingQueueY[currentIndex] - y);
            }

            stream.putLEShort(y + baseY);
            stream.putByteC(super.keyStatus[5] != 1 ? 0 : 1);
            return true;
        }
        return clickType != 1;
    }

    private void updateNPCBlock(Stream stream)
    {
        for(int n = 0; n < actorsAwaitingUpdatePointer; n++)
        {
            int npcId = playersAwaitingUpdate[n];
            NPC npc = npcArray[npcId];
            int updateType = stream.getUnsignedByte();
            if((updateType & 0x10) != 0)
            {
                int animationId = stream.getUnsignedShort();
                if(animationId == 65535)
                    animationId = -1;
                int animationDelay = stream.getUnsignedByte();
                if(animationId == npc.animation && animationId != -1)
                {
                    int l2 = Animation.anims[animationId].anInt365;
                    if(l2 == 1)
                    {
                        npc.anInt1527 = 0;
                        npc.anInt1528 = 0;
                        npc.animationDelay = animationDelay;
                        npc.anInt1530 = 0;
                    }
                    if(l2 == 2)
                        npc.anInt1530 = 0;
                } else
                if(animationId == -1 || npc.animation == -1 || Animation.anims[animationId].anInt359 >= Animation.anims[npc.animation].anInt359)
                {
                    npc.animation = animationId;
                    npc.anInt1527 = 0;
                    npc.anInt1528 = 0;
                    npc.animationDelay = animationDelay;
                    npc.anInt1530 = 0;
                    npc.anInt1542 = npc.pathLength;
                }
            }
            if((updateType & 8) != 0)
            {
                int hitDamage = stream.getUnsignedByteA();
                int hitType = stream.getUnsignedByteC();
                npc.updateHitData(hitType, hitDamage, loopCycle);
                npc.loopCycleStatus = loopCycle + 300;
                npc.currentHealth = stream.getUnsignedByteA();
                npc.maxHealth = stream.getUnsignedByte();
            }
            if((updateType & 0x80) != 0)
            {
                npc.graphicId = stream.getUnsignedLEShort();
                int delay = stream.getInt();
                npc.graphicHeight = delay >> 16;
                npc.graphicEndCycle = loopCycle + (delay & 0xffff);
                npc.currentAnimation = 0;
                npc.anInt1522 = 0;
                if(npc.graphicEndCycle > loopCycle)
                    npc.currentAnimation = -1;
                if(npc.graphicId == 65535)
                    npc.graphicId = -1;
            }
            if((updateType & 0x20) != 0)
            {
                npc.interactingEntity = stream.getUnsignedLEShort();
                if(npc.interactingEntity == 65535)
                    npc.interactingEntity = -1;
            }
            if((updateType & 1) != 0)
            {
                npc.textSpoken = stream.getString();
                npc.textCycle = 100;	
            }
            if((updateType & 0x40) != 0)
            {
                int hitDamage = stream.getUnsignedByteC();
                int hitType = stream.getUnsignedByteS();
                npc.updateHitData(hitType, hitDamage, loopCycle);
                npc.loopCycleStatus = loopCycle + 300;
                npc.currentHealth = stream.getUnsignedByteS();
                npc.maxHealth = stream.getUnsignedByteC();
            }
            if((updateType & 2) != 0)
            {
                npc.npcDefinition = EntityDef.forID(stream.getUnsignedShortA());
                npc.boundaryDimension = npc.npcDefinition.boundaryDimension;
                npc.degreesToTurn = npc.npcDefinition.degreesToTurn;
                npc.walkAnimationId = npc.npcDefinition.walkAnimationId;
                npc.turnAboutAnimationId = npc.npcDefinition.turnAboutAnimationId;
                npc.turnRightAnimationId = npc.npcDefinition.turnRightAnimationId;
                npc.turnLeftAnimationId = npc.npcDefinition.turnLeftAnimationId;
                npc.standAnimationId = npc.npcDefinition.standAnimationId;
            }
            if((updateType & 4) != 0)
            {
                npc.faceTowardX = stream.getUnsignedShort();
                npc.faceTowardY = stream.getUnsignedShort();
            }
        }
    }

    private void buildAtNPCMenu(EntityDef entityDef, int i, int j, int k)
    {
        if(menuActionRow >= 400)
            return;
        if(entityDef.childrenIDs != null)
            entityDef = entityDef.method161();
        if(entityDef == null)
            return;
        if(!entityDef.aBoolean84)
            return;
        String s = entityDef.name;
        if(entityDef.combatLevel != 0)
            s = s + combatDiffColor(localPlayer.combatLevel, entityDef.combatLevel) + " (level-" + entityDef.combatLevel + ")";
        if(itemSelected == 1)
        {
            menuActionName[menuActionRow] = "Use " + selectedItemName + " with @yel@" + s;
            menuActionID[menuActionRow] = 582;
            menuActionCmd1[menuActionRow] = i;
            menuActionCmd2[menuActionRow] = k;
            menuActionCmd3[menuActionRow] = j;
            menuActionRow++;
            return;
        }
        if(spellSelected == 1)
        {
            if((spellUsableOn & 2) == 2)
            {
                menuActionName[menuActionRow] = spellTooltip + " @yel@" + s;
                menuActionID[menuActionRow] = 413;
                menuActionCmd1[menuActionRow] = i;
                menuActionCmd2[menuActionRow] = k;
                menuActionCmd3[menuActionRow] = j;
                menuActionRow++;
            }
        } else
        {
            if(entityDef.actions != null)
            {
                for(int l = 4; l >= 0; l--)
                    if(entityDef.actions[l] != null && !entityDef.actions[l].equalsIgnoreCase("attack"))
                    {
                        menuActionName[menuActionRow] = entityDef.actions[l] + " @yel@" + s;
                        if(l == 0)
                            menuActionID[menuActionRow] = 20;
                        if(l == 1)
                            menuActionID[menuActionRow] = 412;
                        if(l == 2)
                            menuActionID[menuActionRow] = 225;
                        if(l == 3)
                            menuActionID[menuActionRow] = 965;
                        if(l == 4)
                            menuActionID[menuActionRow] = 478;
                        menuActionCmd1[menuActionRow] = i;
                        menuActionCmd2[menuActionRow] = k;
                        menuActionCmd3[menuActionRow] = j;
                        menuActionRow++;
                    }

            }
            if(entityDef.actions != null)
            {
                for(int i1 = 4; i1 >= 0; i1--)
                    if(entityDef.actions[i1] != null && entityDef.actions[i1].equalsIgnoreCase("attack"))
                    {
                        char c = '\0';
                        if(entityDef.combatLevel > localPlayer.combatLevel)
                            c = '\u07D0';
                        menuActionName[menuActionRow] = entityDef.actions[i1] + " @yel@" + s;
                        if(i1 == 0)
                            menuActionID[menuActionRow] = 20 + c;
                        if(i1 == 1)
                            menuActionID[menuActionRow] = 412 + c;
                        if(i1 == 2)
                            menuActionID[menuActionRow] = 225 + c;
                        if(i1 == 3)
                            menuActionID[menuActionRow] = 965 + c;
                        if(i1 == 4)
                            menuActionID[menuActionRow] = 478 + c;
                        menuActionCmd1[menuActionRow] = i;
                        menuActionCmd2[menuActionRow] = k;
                        menuActionCmd3[menuActionRow] = j;
                        menuActionRow++;
                    }

            }
            menuActionName[menuActionRow] = "Examine @yel@" + s + " @gre@(@whi@" + entityDef.type + "@gre@)";
            menuActionID[menuActionRow] = 1025;
            menuActionCmd1[menuActionRow] = i;
            menuActionCmd2[menuActionRow] = k;
            menuActionCmd3[menuActionRow] = j;
            menuActionRow++;
        }
    }

    private void buildAtPlayerMenu(int i, int j, Player player, int k)
    {
        if(player == localPlayer)
            return;
        if(menuActionRow >= 400)
            return;
        String s;
        if(player.skill == 0)
            s = player.name + combatDiffColor(localPlayer.combatLevel, player.combatLevel) + " (level-" + player.combatLevel + ")";
        else
            s = player.name + " (skill-" + player.skill + ")";
        if(itemSelected == 1)
        {
            menuActionName[menuActionRow] = "Use " + selectedItemName + " with @whi@" + s;
            menuActionID[menuActionRow] = 491;
            menuActionCmd1[menuActionRow] = j;
            menuActionCmd2[menuActionRow] = i;
            menuActionCmd3[menuActionRow] = k;
            menuActionRow++;
        } else
        if(spellSelected == 1)
        {
            if((spellUsableOn & 8) == 8)
            {
                menuActionName[menuActionRow] = spellTooltip + " @whi@" + s;
                menuActionID[menuActionRow] = 365;
                menuActionCmd1[menuActionRow] = j;
                menuActionCmd2[menuActionRow] = i;
                menuActionCmd3[menuActionRow] = k;
                menuActionRow++;
            }
        } else
        {
            for(int l = 4; l >= 0; l--)
                if(atPlayerActions[l] != null)
                {
                    menuActionName[menuActionRow] = atPlayerActions[l] + " @whi@" + s;
                    char c = '\0';
                    if(atPlayerActions[l].equalsIgnoreCase("attack"))
                    {
                        if(player.combatLevel > localPlayer.combatLevel)
                            c = '\u07D0';
                        if(localPlayer.team != 0 && player.team != 0)
                            if(localPlayer.team == player.team)
                                c = '\u07D0';
                            else
                                c = '\0';
                    } else
                    if(atPlayerArray[l])
                        c = '\u07D0';
                    if(l == 0)
                        menuActionID[menuActionRow] = 561 + c;
                    if(l == 1)
                        menuActionID[menuActionRow] = 779 + c;
                    if(l == 2)
                        menuActionID[menuActionRow] = 27 + c;
                    if(l == 3)
                        menuActionID[menuActionRow] = 577 + c;
                    if(l == 4)
                        menuActionID[menuActionRow] = 729 + c;
                    menuActionCmd1[menuActionRow] = j;
                    menuActionCmd2[menuActionRow] = i;
                    menuActionCmd3[menuActionRow] = k;
                    menuActionRow++;
                }

        }
        for(int i1 = 0; i1 < menuActionRow; i1++)
            if(menuActionID[i1] == 516)
            {
                menuActionName[i1] = "Walk here @whi@" + s;
                return;
            }

    }

    private void configureSpawnRequest(GameObjectSpawnRequest spawnRequest)
    {
        int uid = 0;
        int id = -1;
        int type = 0;
        int face = 0;
        if(spawnRequest.objectType == 0)
            uid = worldController.getWallObjectUID(spawnRequest.x, spawnRequest.y, spawnRequest.z);
        if(spawnRequest.objectType == 1)
            uid = worldController.getWallDecorationUID(spawnRequest.x, spawnRequest.y, spawnRequest.z);
        if(spawnRequest.objectType == 2)
            uid = worldController.getInteractibleObjectUID(spawnRequest.x, spawnRequest.y, spawnRequest.z);
        if(spawnRequest.objectType == 3)
            uid = worldController.getGroundDecorationUID(spawnRequest.x, spawnRequest.y, spawnRequest.z);
        if(uid != 0)
        {
            int config = worldController.getConfig(uid, spawnRequest.x, spawnRequest.y, spawnRequest.z);
            id = uid >> 14 & 0x7fff;
            type = config & 0x1f;
            face = config >> 6;
        }
        spawnRequest.id = id;
        spawnRequest.type = type;
        spawnRequest.face = face;
    }

    private void method90()
    {
        for(int i = 0; i < trackCount; i++)
            if(trackDelay[i] <= 0)
            {
                boolean flag1 = false;
                try
                {
                    if(trackIds[i] == anInt874 && trackLoop[i] == anInt1289)
                    {
                        if(!replayWave())
                            flag1 = true;
                    } else
                    {
                        Stream stream = Sounds.method241(trackLoop[i], trackIds[i]);
                        if(System.currentTimeMillis() + (long)(stream.currentOffset / 22) > aLong1172 + (long)(anInt1257 / 22))
                        {
                            anInt1257 = stream.currentOffset;
                            aLong1172 = System.currentTimeMillis();
                            if(saveWave(stream.buffer, stream.currentOffset))
                            {
                                anInt874 = trackIds[i];
                                anInt1289 = trackLoop[i];
                            } else
                            {
                                flag1 = true;
                            }
                        }
                    }
                }
                catch(Exception exception) { }
                if(!flag1 || trackDelay[i] == -5)
                {
                    trackCount--;
                    for(int j = i; j < trackCount; j++)
                    {
                        trackIds[j] = trackIds[j + 1];
                        trackLoop[j] = trackLoop[j + 1];
                        trackDelay[j] = trackDelay[j + 1];
                    }

                    i--;
                } else
                {
                    trackDelay[i] = -5;
                }
            } else
            {
                trackDelay[i]--;
            }

        if(prevSong > 0)
        {
            prevSong -= 20;
            if(prevSong < 0)
                prevSong = 0;
            if(prevSong == 0 && musicEnabled && !lowMemory)
            {
                nextSong = currentSong;
                songChanging = true;
                onDemandFetcher.request(2, nextSong);
            }
        }
    }

    void startUp()
    {
        drawLoadingText(20, "Starting up");
        if(signlink.sunjava)
            super.minDelay = 5;
        if(aBoolean993)
        {
 //           rsAlreadyLoaded = true;
 //           return;
        }
        aBoolean993 = true;
        boolean flag = true;
        String s = getDocumentBaseHost();
        if(s.endsWith("jagex.com"))
            flag = true;
        if(s.endsWith("runescape.com"))
            flag = true;
        if(s.endsWith("192.168.1.2"))
            flag = true;
        if(s.endsWith("192.168.1.229"))
            flag = true;
        if(s.endsWith("192.168.1.228"))
            flag = true;
        if(s.endsWith("192.168.1.227"))
            flag = true;
        if(s.endsWith("192.168.1.226"))
            flag = true;
        if(s.endsWith("127.0.0.1"))
            flag = true;
        if(!flag)
        {
            genericLoadingError = true;
            return;
        }
        if(signlink.cache_dat != null)
        {
            for(int i = 0; i < 5; i++)
                decompressors[i] = new Decompressor(signlink.cache_dat, signlink.cache_idx[i], i + 1);

        }
        try
        {
            connectServer();
            titleStreamLoader = streamLoaderForName(1, "title screen", "title", expectedCRCs[1], 25);
            aTextDrawingArea_1270 = new TextDrawingArea(false, "p11_full", titleStreamLoader);
            plainFont = new TextDrawingArea(false, "p12_full", titleStreamLoader);
            chatTextDrawingArea = new TextDrawingArea(false, "b12_full", titleStreamLoader);
            TextDrawingArea aTextDrawingArea_1273 = new TextDrawingArea(true, "q8_full", titleStreamLoader);
            drawLogo();
            loadTitleScreen();
            StreamLoader streamLoader = streamLoaderForName(2, "config", "config", expectedCRCs[2], 30);
            StreamLoader streamLoader_1 = streamLoaderForName(3, "interface", "interface", expectedCRCs[3], 35);
            StreamLoader streamLoader_2 = streamLoaderForName(4, "2d graphics", "media", expectedCRCs[4], 40);
            StreamLoader streamLoader_3 = streamLoaderForName(6, "textures", "textures", expectedCRCs[6], 45);
            StreamLoader streamLoader_4 = streamLoaderForName(7, "chat system", "wordenc", expectedCRCs[7], 50);
            StreamLoader streamLoader_5 = streamLoaderForName(8, "sound effects", "sounds", expectedCRCs[8], 55);
            byteGroundArray = new byte[4][104][104];
            intGroundArray = new int[4][105][105];
            worldController = new WorldController(intGroundArray);
            for(int j = 0; j < 4; j++)
                currentCollisionMap[j] = new CollisionMap();

            aClass30_Sub2_Sub1_Sub1_1263 = new Sprite(512, 512);
            StreamLoader streamLoader_6 = streamLoaderForName(5, "update list", "versionlist", expectedCRCs[5], 60);
            drawLoadingText(60, "Connecting to update server");
            onDemandFetcher = new OnDemandFetcher();
            onDemandFetcher.start(streamLoader_6, this);
            Class36.method528(onDemandFetcher.getAnimCount());
            Model.init(onDemandFetcher.getVersionCount(0), onDemandFetcher);
            if(!lowMemory)
            {
                nextSong = 0;
                try
                {
                    nextSong = Integer.parseInt(getParameter("music"));
                }
                catch(Exception _ex) { }
                songChanging = true;
                onDemandFetcher.request(2, nextSong);
                while(onDemandFetcher.getNodeCount() > 0)
                {
                    processOnDemandQueue();
                    try
                    {
                        Thread.sleep(100L);
                    }
                    catch(Exception _ex) { }
                    if(onDemandFetcher.anInt1349 > 3)
                    {
                        loadError();
                        return;
                    }
                }
            }
            drawLoadingText(65, "Requesting animations");
            int k = onDemandFetcher.getVersionCount(1);
            for(int i1 = 0; i1 < k; i1++)
                onDemandFetcher.request(1, i1);

            while(onDemandFetcher.getNodeCount() > 0)
            {
                int j1 = k - onDemandFetcher.getNodeCount();
                if(j1 > 0)
                    drawLoadingText(65, "Loading animations - " + (j1 * 100) / k + "%");
                processOnDemandQueue();
                try
                {
                    Thread.sleep(100L);
                }
                catch(Exception _ex) { }
                if(onDemandFetcher.anInt1349 > 3)
                {
                    loadError();
                    return;
                }
            }
            drawLoadingText(70, "Requesting models");
            k = onDemandFetcher.getVersionCount(0);
            for(int k1 = 0; k1 < k; k1++)
            {
                int l1 = onDemandFetcher.getModelIndex(k1);
                if((l1 & 1) != 0)
                    onDemandFetcher.request(0, k1);
            }

            k = onDemandFetcher.getNodeCount();
            while(onDemandFetcher.getNodeCount() > 0)
            {
                int i2 = k - onDemandFetcher.getNodeCount();
                if(i2 > 0)
                    drawLoadingText(70, "Loading models - " + (i2 * 100) / k + "%");
                processOnDemandQueue();
                try
                {
                    Thread.sleep(100L);
                }
                catch(Exception _ex) { }
            }
            if(decompressors[0] != null)
            {
                drawLoadingText(75, "Requesting maps");
                onDemandFetcher.request(3, onDemandFetcher.getMapIndex(0, 48, 47));
                onDemandFetcher.request(3, onDemandFetcher.getMapIndex(1, 48, 47));
                onDemandFetcher.request(3, onDemandFetcher.getMapIndex(0, 48, 48));
                onDemandFetcher.request(3, onDemandFetcher.getMapIndex(1, 48, 48));
                onDemandFetcher.request(3, onDemandFetcher.getMapIndex(0, 48, 49));
                onDemandFetcher.request(3, onDemandFetcher.getMapIndex(1, 48, 49));
                onDemandFetcher.request(3, onDemandFetcher.getMapIndex(0, 47, 47));
                onDemandFetcher.request(3, onDemandFetcher.getMapIndex(1, 47, 47));
                onDemandFetcher.request(3, onDemandFetcher.getMapIndex(0, 47, 48));
                onDemandFetcher.request(3, onDemandFetcher.getMapIndex(1, 47, 48));
                onDemandFetcher.request(3, onDemandFetcher.getMapIndex(0, 148, 48));
                onDemandFetcher.request(3, onDemandFetcher.getMapIndex(1, 148, 48));
                k = onDemandFetcher.getNodeCount();
                while(onDemandFetcher.getNodeCount() > 0)
                {
                    int j2 = k - onDemandFetcher.getNodeCount();
                    if(j2 > 0)
                        drawLoadingText(75, "Loading maps - " + (j2 * 100) / k + "%");
                    processOnDemandQueue();
                    try
                    {
                        Thread.sleep(100L);
                    }
                    catch(Exception _ex) { }
                }
            }
            k = onDemandFetcher.getVersionCount(0);
            for(int k2 = 0; k2 < k; k2++)
            {
                int l2 = onDemandFetcher.getModelIndex(k2);
                byte byte0 = 0;
                if((l2 & 8) != 0)
                    byte0 = 10;
                else
                if((l2 & 0x20) != 0)
                    byte0 = 9;
                else
                if((l2 & 0x10) != 0)
                    byte0 = 8;
                else
                if((l2 & 0x40) != 0)
                    byte0 = 7;
                else
                if((l2 & 0x80) != 0)
                    byte0 = 6;
                else
                if((l2 & 2) != 0)
                    byte0 = 5;
                else
                if((l2 & 4) != 0)
                    byte0 = 4;
                if((l2 & 1) != 0)
                    byte0 = 3;
                if(byte0 != 0)
                    onDemandFetcher.method563(byte0, 0, k2);
            }

            onDemandFetcher.method554(isMembers);
            if(!lowMemory)
            {
                int l = onDemandFetcher.getVersionCount(2);
                for(int i3 = 1; i3 < l; i3++)
                    if(onDemandFetcher.method569(i3))
                        onDemandFetcher.method563((byte)1, 2, i3);

            }
            drawLoadingText(80, "Unpacking media");
            invBack = new Background(streamLoader_2, "invback", 0);
            chatBack = new Background(streamLoader_2, "chatback", 0);
            mapBack = new Background(streamLoader_2, "mapback", 0);
            backBase1 = new Background(streamLoader_2, "backbase1", 0);
            backBase2 = new Background(streamLoader_2, "backbase2", 0);
            backHmid1 = new Background(streamLoader_2, "backhmid1", 0);
            for(int j3 = 0; j3 < 13; j3++)
                sideIcons[j3] = new Background(streamLoader_2, "sideicons", j3);

            compass = new Sprite(streamLoader_2, "compass", 0);
            mapEdge = new Sprite(streamLoader_2, "mapedge", 0);
            mapEdge.method345();
            try
            {
                for(int k3 = 0; k3 < 100; k3++)
                    mapScenes[k3] = new Background(streamLoader_2, "mapscene", k3);

            }
            catch(Exception _ex) { }
            try
            {
                for(int l3 = 0; l3 < 100; l3++)
                    mapFunctions[l3] = new Sprite(streamLoader_2, "mapfunction", l3);

            }
            catch(Exception _ex) { }
            try
            {
                for(int i4 = 0; i4 < 20; i4++)
                    hitMarks[i4] = new Sprite(streamLoader_2, "hitmarks", i4);

            }
            catch(Exception _ex) { }
            try
            {
                for(int j4 = 0; j4 < 20; j4++)
                    headIcons[j4] = new Sprite(streamLoader_2, "headicons", j4);

            }
            catch(Exception _ex) { }
            mapFlag = new Sprite(streamLoader_2, "mapmarker", 0);
            mapMarker = new Sprite(streamLoader_2, "mapmarker", 1);
            for(int k4 = 0; k4 < 8; k4++)
                crosses[k4] = new Sprite(streamLoader_2, "cross", k4);

            mapDotItem = new Sprite(streamLoader_2, "mapdots", 0);
            mapDotNPC = new Sprite(streamLoader_2, "mapdots", 1);
            mapDotPlayer = new Sprite(streamLoader_2, "mapdots", 2);
            mapDotFriend = new Sprite(streamLoader_2, "mapdots", 3);
            mapDotTeam = new Sprite(streamLoader_2, "mapdots", 4);
            scrollBar1 = new Background(streamLoader_2, "scrollbar", 0);
            scrollBar2 = new Background(streamLoader_2, "scrollbar", 1);
            redStone1 = new Background(streamLoader_2, "redstone1", 0);
            redStone2 = new Background(streamLoader_2, "redstone2", 0);
            redStone3 = new Background(streamLoader_2, "redstone3", 0);
            redStone1_2 = new Background(streamLoader_2, "redstone1", 0);
            redStone1_2.method358();
            redStone2_2 = new Background(streamLoader_2, "redstone2", 0);
            redStone2_2.method358();
            redStone1_3 = new Background(streamLoader_2, "redstone1", 0);
            redStone1_3.method359();
            redStone2_3 = new Background(streamLoader_2, "redstone2", 0);
            redStone2_3.method359();
            redStone3_2 = new Background(streamLoader_2, "redstone3", 0);
            redStone3_2.method359();
            redStone1_4 = new Background(streamLoader_2, "redstone1", 0);
            redStone1_4.method358();
            redStone1_4.method359();
            redStone2_4 = new Background(streamLoader_2, "redstone2", 0);
            redStone2_4.method358();
            redStone2_4.method359();
            for(int l4 = 0; l4 < 2; l4++)
                modIcons[l4] = new Background(streamLoader_2, "mod_icons", l4);

            Sprite sprite = new Sprite(streamLoader_2, "backleft1", 0);
            backLeftIP1 = new RSImageProducer(sprite.myWidth, sprite.myHeight, getGameComponent());
            sprite.method346(0, 0);
            sprite = new Sprite(streamLoader_2, "backleft2", 0);
            backLeftIP2 = new RSImageProducer(sprite.myWidth, sprite.myHeight, getGameComponent());
            sprite.method346(0, 0);
            sprite = new Sprite(streamLoader_2, "backright1", 0);
            backRightIP1 = new RSImageProducer(sprite.myWidth, sprite.myHeight, getGameComponent());
            sprite.method346(0, 0);
            sprite = new Sprite(streamLoader_2, "backright2", 0);
            backRightIP2 = new RSImageProducer(sprite.myWidth, sprite.myHeight, getGameComponent());
            sprite.method346(0, 0);
            sprite = new Sprite(streamLoader_2, "backtop1", 0);
            backTopIP1 = new RSImageProducer(sprite.myWidth, sprite.myHeight, getGameComponent());
            sprite.method346(0, 0);
            sprite = new Sprite(streamLoader_2, "backvmid1", 0);
            backVmidIP1 = new RSImageProducer(sprite.myWidth, sprite.myHeight, getGameComponent());
            sprite.method346(0, 0);
            sprite = new Sprite(streamLoader_2, "backvmid2", 0);
            backVmidIP2 = new RSImageProducer(sprite.myWidth, sprite.myHeight, getGameComponent());
            sprite.method346(0, 0);
            sprite = new Sprite(streamLoader_2, "backvmid3", 0);
            backVmidIP3 = new RSImageProducer(sprite.myWidth, sprite.myHeight, getGameComponent());
            sprite.method346(0, 0);
            sprite = new Sprite(streamLoader_2, "backhmid2", 0);
            backVmidIP2_2 = new RSImageProducer(sprite.myWidth, sprite.myHeight, getGameComponent());
            sprite.method346(0, 0);
            int i5 = (int)(Math.random() * 21D) - 10;
            int j5 = (int)(Math.random() * 21D) - 10;
            int k5 = (int)(Math.random() * 21D) - 10;
            int l5 = (int)(Math.random() * 41D) - 20;
            for(int i6 = 0; i6 < 100; i6++)
            {
                if(mapFunctions[i6] != null)
                    mapFunctions[i6].method344(i5 + l5, j5 + l5, k5 + l5);
                if(mapScenes[i6] != null)
                    mapScenes[i6].method360(i5 + l5, j5 + l5, k5 + l5);
            }

            drawLoadingText(83, "Unpacking textures");
            Texture.unpackTextures(streamLoader_3);
            Texture.calculatePalette(0.80000000000000004D);
            Texture.resetTextures();
            drawLoadingText(86, "Unpacking config");
            Animation.unpackConfig(streamLoader);
            ObjectDef.unpackConfig(streamLoader);
            Flo.unpackConfig(streamLoader);
            ItemDef.unpackConfig(streamLoader);
            EntityDef.unpackConfig(streamLoader);
            IDK.unpackConfig(streamLoader);
            SpotAnim.unpackConfig(streamLoader);
            Varp.unpackConfig(streamLoader);
            VarBit.unpackConfig(streamLoader);
            ItemDef.isMembers = isMembers;
            if(!lowMemory)
            {
                drawLoadingText(90, "Unpacking sounds");
                byte abyte0[] = streamLoader_5.getDataForName("sounds.dat");
                Stream stream = new Stream(abyte0);
                Sounds.unpack(stream);
            }
            drawLoadingText(95, "Unpacking interfaces");
            TextDrawingArea aclass30_sub2_sub1_sub4s[] = {
                    aTextDrawingArea_1270, plainFont, chatTextDrawingArea, aTextDrawingArea_1273
            };
            RSInterface.unpack(streamLoader_1, aclass30_sub2_sub1_sub4s, streamLoader_2);
            drawLoadingText(100, "Preparing game engine");
            for(int j6 = 0; j6 < 33; j6++)
            {
                int k6 = 999;
                int i7 = 0;
                for(int k7 = 0; k7 < 34; k7++)
                {
                    if(mapBack.imagePixels[k7 + j6 * mapBack.imageWidth] == 0)
                    {
                        if(k6 == 999)
                            k6 = k7;
                        continue;
                    }
                    if(k6 == 999)
                        continue;
                    i7 = k7;
                    break;
                }

                anIntArray968[j6] = k6;
                anIntArray1057[j6] = i7 - k6;
            }

            for(int l6 = 5; l6 < 156; l6++)
            {
                int j7 = 999;
                int l7 = 0;
                for(int j8 = 25; j8 < 172; j8++)
                {
                    if(mapBack.imagePixels[j8 + l6 * mapBack.imageWidth] == 0 && (j8 > 34 || l6 > 34))
                    {
                        if(j7 == 999)
                            j7 = j8;
                        continue;
                    }
                    if(j7 == 999)
                        continue;
                    l7 = j8;
                    break;
                }

                anIntArray1052[l6 - 5] = j7 - 25;
                anIntArray1229[l6 - 5] = l7 - j7;
            }

            Texture.setBounds(479, 96);
            anIntArray1180 = Texture.lineOffsets;
            Texture.setBounds(190, 261);
            anIntArray1181 = Texture.lineOffsets;
            Texture.setBounds(512, 334);
            anIntArray1182 = Texture.lineOffsets;
            int ai[] = new int[9];
            for(int i8 = 0; i8 < 9; i8++)
            {
                int k8 = 128 + i8 * 32 + 15;
                int l8 = 600 + k8 * 3;
                int i9 = Texture.SINE[k8];
                ai[i8] = l8 * i9 >> 16;
            }

            WorldController.method310(500, 800, 512, 334, ai);
            Censor.loadConfig(streamLoader_4);
            mouseDetection = new MouseDetection(this);
            startRunnable(mouseDetection, 10);
            ObjectInstance.clientInstance = this;
            ObjectDef.clientInstance = this;
            EntityDef.clientInstance = this;
            return;
        }
        catch(Exception exception)
        {
            signlink.reporterror("loaderror " + aString1049 + " " + anInt1079);
        }
        loadingError = true;
    }

    private void updatePlayerList(Stream stream, int i)
    {
        while(stream.bitPosition + 10 < i * 8)
        {
            int nextPlayer = stream.readBits(11);
            if(nextPlayer == 2047)
                break;
            if(playerArray[nextPlayer] == null)
            {
                playerArray[nextPlayer] = new Player();
                if(playerBuffer[nextPlayer] != null)
                    playerArray[nextPlayer].updatePlayerAppearance(playerBuffer[nextPlayer]);
            }
            playerId[playerCount++] = nextPlayer;
            Player player = playerArray[nextPlayer];
            player.lastUpdateTime = loopCycle;
            int chunkInUpdateList = stream.readBits(1);
            if(chunkInUpdateList == 1)
                playersAwaitingUpdate[actorsAwaitingUpdatePointer++] = nextPlayer;
            int clearWaypointQueue = stream.readBits(1);
            int x = stream.readBits(5);
            if(x > 15)
                x -= 32;
            int y = stream.readBits(5);
            if(y > 15)
                y -= 32;
            player.setPos(localPlayer.smallX[0] + y, localPlayer.smallY[0] + x, clearWaypointQueue == 1);
        }
        stream.finishBitAccess();
    }

    private void processMinimapClick()
    {
        if(minimapState != 0)
            return;
        if(super.clickType == 1)
        {
            int i = super.clickX - 25 - 550;
            int j = super.clickY - 5 - 4;
            if(i >= 0 && j >= 0 && i < 146 && j < 151)
            {
                i -= 73;
                j -= 75;
                int k = minimapInt1 + minimapRotation & 0x7ff;
                int sine = Texture.SINE[k];
                int cosine = Texture.COSINE[k];
                sine = sine * (minimapZoom + 256) >> 8;
                cosine = cosine * (minimapZoom + 256) >> 8;
                int k1 = j * sine + i * cosine >> 11;
                int l1 = j * cosine - i * sine >> 11;
                int i2 = localPlayer.x + k1 >> 7;
                int j2 = localPlayer.y - l1 >> 7;
                boolean flag1 = doWalkTo(1, 0, 0, 0, localPlayer.smallY[0], 0, 0, j2, localPlayer.smallX[0], true, i2);
                if(flag1)
                {
                    stream.put(i);
                    stream.put(j);
                    stream.putShort(minimapInt1);
                    stream.put(57);
                    stream.put(minimapRotation);
                    stream.put(minimapZoom);
                    stream.put(89);
                    stream.putShort(localPlayer.x);
                    stream.putShort(localPlayer.y);
                    stream.put(arbitraryDestination);
                    stream.put(63);
                }
            }
            mouseClickCounter++;
            if(mouseClickCounter > 1151)
            {
                mouseClickCounter = 0;
                stream.putOpcode(246);
                stream.put(0);
                int l = stream.currentOffset;
                if((int)(Math.random() * 2D) == 0)
                    stream.put(101);
                stream.put(197);
                stream.putShort((int)(Math.random() * 65536D));
                stream.put((int)(Math.random() * 256D));
                stream.put(67);
                stream.putShort(14214);
                if((int)(Math.random() * 2D) == 0)
                    stream.putShort(29487);
                stream.putShort((int)(Math.random() * 65536D));
                if((int)(Math.random() * 2D) == 0)
                    stream.put(220);
                stream.put(180);
                stream.putSizeByte(stream.currentOffset - l);
            }
        }
    }

    private String interfaceIntToString(int value)
    {
        if(value < 999999999)
            return String.valueOf(value);
        else
            return "*";
    }

    private void showErrorScreen()
    {
        Graphics g = getGameComponent().getGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, 765, 503);
        setFrameRate(1);
        if(loadingError)
        {
            currentlyDrawingFlames = false;
            g.setFont(new Font("Helvetica", 1, 16));
            g.setColor(Color.yellow);
            int currentPositionY = 35;
            g.drawString("Sorry, an error has occured whilst loading RuneScape", 30, currentPositionY);
            currentPositionY += 50;
            g.setColor(Color.white);
            g.drawString("To fix this try the following (in order):", 30, currentPositionY);
            currentPositionY += 50;
            g.setColor(Color.white);
            g.setFont(new Font("Helvetica", 1, 12));
            g.drawString("1: Try closing ALL open web-browser windows, and reloading", 30, currentPositionY);
            currentPositionY += 30;
            g.drawString("2: Try clearing your web-browsers cache from tools->internet options", 30, currentPositionY);
            currentPositionY += 30;
            g.drawString("3: Try using a different game-world", 30, currentPositionY);
            currentPositionY += 30;
            g.drawString("4: Try rebooting your computer", 30, currentPositionY);
            currentPositionY += 30;
            g.drawString("5: Try selecting a different version of Java from the play-game menu", 30, currentPositionY);
        }
        if(genericLoadingError)
        {
            currentlyDrawingFlames = false;
            g.setFont(new Font("Helvetica", 1, 20));
            g.setColor(Color.white);
            g.drawString("Error - unable to load game!", 50, 50);
            g.drawString("To play RuneScape make sure you play from", 50, 100);
            g.drawString("http://www.runescape.com", 50, 150);
        }
        if(rsAlreadyLoaded)
        {
            currentlyDrawingFlames = false;
            g.setColor(Color.yellow);
            int currentPositionY = 35;
            g.drawString("Error a copy of RuneScape already appears to be loaded", 30, currentPositionY);
            currentPositionY += 50;
            g.setColor(Color.white);
            g.drawString("To fix this try the following (in order):", 30, currentPositionY);
            currentPositionY += 50;
            g.setColor(Color.white);
            g.setFont(new Font("Helvetica", 1, 12));
            g.drawString("1: Try closing ALL open web-browser windows, and reloading", 30, currentPositionY);
            currentPositionY += 30;
            g.drawString("2: Try rebooting your computer, and reloading", 30, currentPositionY);
            currentPositionY += 30;
        }
    }

    public URL getCodeBase()
    {
        if(signlink.mainapp != null)
            return signlink.mainapp.getCodeBase();
        try
        {
            if(super.gameFrame != null)
                return new URL("http://127.0.0.1:" + (80 + portOffset));
        }
        catch(Exception _ex) { }
        return super.getCodeBase();
    }

    private void method95()
    {
        for(int j = 0; j < npcCount; j++)
        {
            int k = npcIndices[j];
            NPC npc = npcArray[k];
            if(npc != null)
                updateEntity(npc);
        }
    }

    private void updateEntity(Entity entity)
    {
        if(entity.x < 128 || entity.y < 128 || entity.x >= 13184 || entity.y >= 13184)
        {
            entity.animation = -1;
            entity.graphicId = -1;
            entity.speedToStart = 0;
            entity.speedToEnd = 0;
            entity.x = entity.smallX[0] * 128 + entity.boundaryDimension * 64;
            entity.y = entity.smallY[0] * 128 + entity.boundaryDimension * 64;
            entity.resetPath();
        }
        if(entity == localPlayer && (entity.x < 1536 || entity.y < 1536 || entity.x >= 11776 || entity.y >= 11776))
        {
            entity.animation = -1;
            entity.graphicId = -1;
            entity.speedToStart = 0;
            entity.speedToEnd = 0;
            entity.x = entity.smallX[0] * 128 + entity.boundaryDimension * 64;
            entity.y = entity.smallY[0] * 128 + entity.boundaryDimension * 64;
            entity.resetPath();
        }
        if(entity.speedToStart > loopCycle)
            method97(entity);
        else
        if(entity.speedToEnd >= loopCycle)
            method98(entity);
        else
            processWalkingStep(entity);
        method100(entity);
        appendAnimation(entity);
    }

    private void method97(Entity entity)
    {
        int i = entity.speedToStart - loopCycle;
        int j = entity.startX * 128 + entity.boundaryDimension * 64;
        int k = entity.startY * 128 + entity.boundaryDimension * 64;
        entity.x += (j - entity.x) / i;
        entity.y += (k - entity.y) / i;
        entity.anInt1503 = 0;
        if(entity.direction == 0)
            entity.turnDirection = 1024;
        if(entity.direction == 1)
            entity.turnDirection = 1536;
        if(entity.direction == 2)
            entity.turnDirection = 0;
        if(entity.direction == 3)
            entity.turnDirection = 512;
    }

    private void method98(Entity entity)
    {
        if(entity.speedToEnd == loopCycle || entity.animation == -1 || entity.animationDelay != 0 || entity.anInt1528 + 1 > Animation.anims[entity.animation].getFrameLength(entity.anInt1527))
        {
            int i = entity.speedToEnd - entity.speedToStart;
            int j = loopCycle - entity.speedToStart;
            int k = entity.startX * 128 + entity.boundaryDimension * 64;
            int l = entity.startY * 128 + entity.boundaryDimension * 64;
            int i1 = entity.endX * 128 + entity.boundaryDimension * 64;
            int j1 = entity.endY * 128 + entity.boundaryDimension * 64;
            entity.x = (k * (i - j) + i1 * j) / i;
            entity.y = (l * (i - j) + j1 * j) / i;
        }
        entity.anInt1503 = 0;
        if(entity.direction == 0)
            entity.turnDirection = 1024;
        if(entity.direction == 1)
            entity.turnDirection = 1536;
        if(entity.direction == 2)
            entity.turnDirection = 0;
        if(entity.direction == 3)
            entity.turnDirection = 512;
        entity.currentRotation = entity.turnDirection;
    }

    private void processWalkingStep(Entity entity)
    {
        entity.anInt1517 = entity.standAnimationId;
        if(entity.pathLength == 0)
        {
            entity.anInt1503 = 0;
            return;
        }
        if(entity.animation != -1 && entity.animationDelay == 0)
        {
            Animation animation = Animation.anims[entity.animation];
            if(entity.anInt1542 > 0 && animation.anInt363 == 0)
            {
                entity.anInt1503++;
                return;
            }
            if(entity.anInt1542 <= 0 && animation.priority == 0)
            {
                entity.anInt1503++;
                return;
            }
        }
        int x1 = entity.x;
        int y1 = entity.y;
        int x2 = entity.smallX[entity.pathLength - 1] * 128 + entity.boundaryDimension * 64;
        int y2 = entity.smallY[entity.pathLength - 1] * 128 + entity.boundaryDimension * 64;
        if(x2 - x1 > 256 || x2 - x1 < -256 || y2 - y1 > 256 || y2 - y1 < -256)
        {
            entity.x = x2;
            entity.y = y2;
            return;
        }
        if(x1 < x2)
        {
            if(y1 < y2)
                entity.turnDirection = 1280;
            else
            if(y1 > y2)
                entity.turnDirection = 1792;
            else
                entity.turnDirection = 1536;
        } else
        if(x1 > x2)
        {
            if(y1 < y2)
                entity.turnDirection = 768;
            else
            if(y1 > y2)
                entity.turnDirection = 256;
            else
                entity.turnDirection = 512;
        } else
        if(y1 < y2)
            entity.turnDirection = 1024;
        else
            entity.turnDirection = 0;
        int rotationDifference = entity.turnDirection - entity.currentRotation & 0x7ff;
        if(rotationDifference > 1024)
            rotationDifference -= 2048;
        int anim = entity.turnAboutAnimationId;
        if(rotationDifference >= -256 && rotationDifference <= 256)
            anim = entity.walkAnimationId;
        else
        if(rotationDifference >= 256 && rotationDifference < 768)
            anim = entity.turnLeftAnimationId;
        else
        if(rotationDifference >= -768 && rotationDifference <= -256)
            anim = entity.turnRightAnimationId;
        if(anim == -1)
            anim = entity.walkAnimationId;
        entity.anInt1517 = anim;
        int k1 = 4;
        if(entity.currentRotation != entity.turnDirection && entity.interactingEntity == -1 && entity.degreesToTurn != 0)
            k1 = 2;
        if(entity.pathLength > 2)
            k1 = 6;
        if(entity.pathLength > 3)
            k1 = 8;
        if(entity.anInt1503 > 0 && entity.pathLength > 1)
        {
            k1 = 8;
            entity.anInt1503--;
        }
        if(entity.pathRun[entity.pathLength - 1])
            k1 <<= 1;
        if(k1 >= 8 && entity.anInt1517 == entity.walkAnimationId && entity.runAnimationId != -1)
            entity.anInt1517 = entity.runAnimationId;
        if(x1 < x2)
        {
            entity.x += k1;
            if(entity.x > x2)
                entity.x = x2;
        } else
        if(x1 > x2)
        {
            entity.x -= k1;
            if(entity.x < x2)
                entity.x = x2;
        }
        if(y1 < y2)
        {
            entity.y += k1;
            if(entity.y > y2)
                entity.y = y2;
        } else
        if(y1 > y2)
        {
            entity.y -= k1;
            if(entity.y < y2)
                entity.y = y2;
        }
        if(entity.x == x2 && entity.y == y2)
        {
            entity.pathLength--;
            if(entity.anInt1542 > 0)
                entity.anInt1542--;
        }
    }

    private void method100(Entity entity)
    {
        if(entity.degreesToTurn == 0)
            return;
        if(entity.interactingEntity != -1 && entity.interactingEntity < 32768)
        {
            NPC npc = npcArray[entity.interactingEntity];
            if(npc != null)
            {
                int i1 = entity.x - npc.x;
                int k1 = entity.y - npc.y;
                if(i1 != 0 || k1 != 0)
                    entity.turnDirection = (int)(Math.atan2(i1, k1) * 325.94900000000001D) & 0x7ff;
            }
        }
        if(entity.interactingEntity >= 32768)
        {
            int j = entity.interactingEntity - 32768;
            if(j == playerListId)
                j = localPlayerId;
            Player player = playerArray[j];
            if(player != null)
            {
                int l1 = entity.x - player.x;
                int i2 = entity.y - player.y;
                if(l1 != 0 || i2 != 0)
                    entity.turnDirection = (int)(Math.atan2(l1, i2) * 325.94900000000001D) & 0x7ff;
            }
        }
        if((entity.faceTowardX != 0 || entity.faceTowardY != 0) && (entity.pathLength == 0 || entity.anInt1503 > 0))
        {
            int k = entity.x - (entity.faceTowardX - baseX - baseX) * 64;
            int j1 = entity.y - (entity.faceTowardY - baseY - baseY) * 64;
            if(k != 0 || j1 != 0)
                entity.turnDirection = (int)(Math.atan2(k, j1) * 325.94900000000001D) & 0x7ff;
            entity.faceTowardX = 0;
            entity.faceTowardY = 0;
        }
        int l = entity.turnDirection - entity.currentRotation & 0x7ff;
        if(l != 0)
        {
            if(l < entity.degreesToTurn || l > 2048 - entity.degreesToTurn)
                entity.currentRotation = entity.turnDirection;
            else
            if(l > 1024)
                entity.currentRotation -= entity.degreesToTurn;
            else
                entity.currentRotation += entity.degreesToTurn;
            entity.currentRotation &= 0x7ff;
            if(entity.anInt1517 == entity.standAnimationId && entity.currentRotation != entity.turnDirection)
            {
                if(entity.standTurnAnimationId != -1)
                {
                    entity.anInt1517 = entity.standTurnAnimationId;
                    return;
                }
                entity.anInt1517 = entity.walkAnimationId;
            }
        }
    }

    private void appendAnimation(Entity entity)
    {
        entity.aBoolean1541 = false;
        if(entity.anInt1517 != -1)
        {
            Animation animation = Animation.anims[entity.anInt1517];
            entity.anInt1519++;
            if(entity.anInt1518 < animation.frameCount && entity.anInt1519 > animation.getFrameLength(entity.anInt1518))
            {
                entity.anInt1519 = 0;
                entity.anInt1518++;
            }
            if(entity.anInt1518 >= animation.frameCount)
            {
                entity.anInt1519 = 0;
                entity.anInt1518 = 0;
            }
        }
        if(entity.graphicId != -1 && loopCycle >= entity.graphicEndCycle)
        {
            if(entity.currentAnimation < 0)
                entity.currentAnimation = 0;
            Animation animation_1 = SpotAnim.cache[entity.graphicId].sequences;
            for(entity.anInt1522++; entity.currentAnimation < animation_1.frameCount && entity.anInt1522 > animation_1.getFrameLength(entity.currentAnimation); entity.currentAnimation++)
                entity.anInt1522 -= animation_1.getFrameLength(entity.currentAnimation);

            if(entity.currentAnimation >= animation_1.frameCount && (entity.currentAnimation < 0 || entity.currentAnimation >= animation_1.frameCount))
                entity.graphicId = -1;
        }
        if(entity.animation != -1 && entity.animationDelay <= 1)
        {
            Animation animation_2 = Animation.anims[entity.animation];
            if(animation_2.anInt363 == 1 && entity.anInt1542 > 0 && entity.speedToStart <= loopCycle && entity.speedToEnd < loopCycle)
            {
                entity.animationDelay = 1;
                return;
            }
        }
        if(entity.animation != -1 && entity.animationDelay == 0)
        {
            Animation animation_3 = Animation.anims[entity.animation];
            for(entity.anInt1528++; entity.anInt1527 < animation_3.frameCount && entity.anInt1528 > animation_3.getFrameLength(entity.anInt1527); entity.anInt1527++)
                entity.anInt1528 -= animation_3.getFrameLength(entity.anInt1527);

            if(entity.anInt1527 >= animation_3.frameCount)
            {
                entity.anInt1527 -= animation_3.frameStep;
                entity.anInt1530++;
                if(entity.anInt1530 >= animation_3.anInt362)
                    entity.animation = -1;
                if(entity.anInt1527 < 0 || entity.anInt1527 >= animation_3.frameCount)
                    entity.animation = -1;
            }
            entity.aBoolean1541 = animation_3.aBoolean358;
        }
        if(entity.animationDelay > 0)
            entity.animationDelay--;
    }

    private void drawGameScreen()
    {
        if(welcomeScreenRaised)
        {
            welcomeScreenRaised = false;
            backLeftIP1.drawGraphics(4, super.gameGraphics, 0);
            backLeftIP2.drawGraphics(357, super.gameGraphics, 0);
            backRightIP1.drawGraphics(4, super.gameGraphics, 722);
            backRightIP2.drawGraphics(205, super.gameGraphics, 743);
            backTopIP1.drawGraphics(0, super.gameGraphics, 0);
            backVmidIP1.drawGraphics(4, super.gameGraphics, 516);
            backVmidIP2.drawGraphics(205, super.gameGraphics, 516);
            backVmidIP3.drawGraphics(357, super.gameGraphics, 496);
            backVmidIP2_2.drawGraphics(338, super.gameGraphics, 0);
            needDrawTabArea = true;
            inputTaken = true;
            tabAreaAltered = true;
            updateChatSettings = true;
            if(loadingStage != 2)
            {
                gameScreenCanvas.drawGraphics(4, super.gameGraphics, 4);
                aRSImageProducer_1164.drawGraphics(4, super.gameGraphics, 550);
            }
        }
        if(loadingStage == 2)
            method146();
        if(menuOpen && menuScreenArea == 1)
            needDrawTabArea = true;
        if(inventoryOverlayInterfaceID != -1)
        {
            boolean flag1 = method119(animationTimePassed, inventoryOverlayInterfaceID);
            if(flag1)
                needDrawTabArea = true;
        }
        if(atInventoryInterfaceType == 2)
            needDrawTabArea = true;
        if(activeInterfaceType == 2)
            needDrawTabArea = true;
        if(needDrawTabArea)
        {
            drawTabArea();
            needDrawTabArea = false;
        }
        if(backDialogID == -1)
        {
            aClass9_1059.scrollPosition = anInt1211 - anInt1089 - 77;
            if(super.mouseX > 448 && super.mouseX < 560 && super.mouseY > 332)
                method65(463, 77, super.mouseX - 17, super.mouseY - 357, aClass9_1059, 0, false, anInt1211);
            int i = anInt1211 - 77 - aClass9_1059.scrollPosition;
            if(i < 0)
                i = 0;
            if(i > anInt1211 - 77)
                i = anInt1211 - 77;
            if(anInt1089 != i)
            {
                anInt1089 = i;
                inputTaken = true;
            }
        }
        if(backDialogID != -1)
        {
            boolean flag2 = method119(animationTimePassed, backDialogID);
            if(flag2)
                inputTaken = true;
        }
        if(atInventoryInterfaceType == 3)
            inputTaken = true;
        if(activeInterfaceType == 3)
            inputTaken = true;
        if(aString844 != null)
            inputTaken = true;
        if(menuOpen && menuScreenArea == 2)
            inputTaken = true;
        if(inputTaken)
        {
            drawChatArea();
            inputTaken = false;
        }
        if(loadingStage == 2)
        {
            drawMinimap();
            aRSImageProducer_1164.drawGraphics(4, super.gameGraphics, 550);
        }
        if(flashingSidebar != -1)
            tabAreaAltered = true;
        if(tabAreaAltered)
        {
            if(flashingSidebar != -1 && flashingSidebar == tabID)
            {
                flashingSidebar = -1;
                stream.putOpcode(120);
                stream.put(tabID);
            }
            tabAreaAltered = false;
            aRSImageProducer_1125.initDrawingArea();
            backHmid1.method361(0, 0);
            if(inventoryOverlayInterfaceID == -1)
            {
                if(tabInterfaceIDs[tabID] != -1)
                {
                    if(tabID == 0)
                        redStone1.method361(22, 10);
                    if(tabID == 1)
                        redStone2.method361(54, 8);
                    if(tabID == 2)
                        redStone2.method361(82, 8);
                    if(tabID == 3)
                        redStone3.method361(110, 8);
                    if(tabID == 4)
                        redStone2_2.method361(153, 8);
                    if(tabID == 5)
                        redStone2_2.method361(181, 8);
                    if(tabID == 6)
                        redStone1_2.method361(209, 9);
                }
                if(tabInterfaceIDs[0] != -1 && (flashingSidebar != 0 || loopCycle % 20 < 10))
                    sideIcons[0].method361(29, 13);
                if(tabInterfaceIDs[1] != -1 && (flashingSidebar != 1 || loopCycle % 20 < 10))
                    sideIcons[1].method361(53, 11);
                if(tabInterfaceIDs[2] != -1 && (flashingSidebar != 2 || loopCycle % 20 < 10))
                    sideIcons[2].method361(82, 11);
                if(tabInterfaceIDs[3] != -1 && (flashingSidebar != 3 || loopCycle % 20 < 10))
                    sideIcons[3].method361(115, 12);
                if(tabInterfaceIDs[4] != -1 && (flashingSidebar != 4 || loopCycle % 20 < 10))
                    sideIcons[4].method361(153, 13);
                if(tabInterfaceIDs[5] != -1 && (flashingSidebar != 5 || loopCycle % 20 < 10))
                    sideIcons[5].method361(180, 11);
                if(tabInterfaceIDs[6] != -1 && (flashingSidebar != 6 || loopCycle % 20 < 10))
                    sideIcons[6].method361(208, 13);
            }
            aRSImageProducer_1125.drawGraphics(160, super.gameGraphics, 516);
            aRSImageProducer_1124.initDrawingArea();
            backBase2.method361(0, 0);
            if(inventoryOverlayInterfaceID == -1)
            {
                if(tabInterfaceIDs[tabID] != -1)
                {
                    if(tabID == 7)
                        redStone1_3.method361(42, 0);
                    if(tabID == 8)
                        redStone2_3.method361(74, 0);
                    if(tabID == 9)
                        redStone2_3.method361(102, 0);
                    if(tabID == 10)
                        redStone3_2.method361(130, 1);
                    if(tabID == 11)
                        redStone2_4.method361(173, 0);
                    if(tabID == 12)
                        redStone2_4.method361(201, 0);
                    if(tabID == 13)
                        redStone1_4.method361(229, 0);
                }
                if(tabInterfaceIDs[8] != -1 && (flashingSidebar != 8 || loopCycle % 20 < 10))
                    sideIcons[7].method361(74, 2);
                if(tabInterfaceIDs[9] != -1 && (flashingSidebar != 9 || loopCycle % 20 < 10))
                    sideIcons[8].method361(102, 3);
                if(tabInterfaceIDs[10] != -1 && (flashingSidebar != 10 || loopCycle % 20 < 10))
                    sideIcons[9].method361(137, 4);
                if(tabInterfaceIDs[11] != -1 && (flashingSidebar != 11 || loopCycle % 20 < 10))
                    sideIcons[10].method361(174, 2);
                if(tabInterfaceIDs[12] != -1 && (flashingSidebar != 12 || loopCycle % 20 < 10))
                    sideIcons[11].method361(201, 2);
                if(tabInterfaceIDs[13] != -1 && (flashingSidebar != 13 || loopCycle % 20 < 10))
                    sideIcons[12].method361(226, 2);
            }
            aRSImageProducer_1124.drawGraphics(466, super.gameGraphics, 496);
            gameScreenCanvas.initDrawingArea();
        }
        if(updateChatSettings)
        {
            updateChatSettings = false;
            aRSImageProducer_1123.initDrawingArea();
            backBase1.method361(0, 0);
            plainFont.drawShadowTextHMidVTop(0xffffff, 55, "Public chat", 28, true);
            if(publicChatMode == 0)
                plainFont.drawShadowTextHMidVTop(65280, 55, "On", 41, true);
            if(publicChatMode == 1)
                plainFont.drawShadowTextHMidVTop(0xffff00, 55, "Friends", 41, true);
            if(publicChatMode == 2)
                plainFont.drawShadowTextHMidVTop(0xff0000, 55, "Off", 41, true);
            if(publicChatMode == 3)
                plainFont.drawShadowTextHMidVTop(65535, 55, "Hide", 41, true);
            plainFont.drawShadowTextHMidVTop(0xffffff, 184, "Private chat", 28, true);
            if(privateChatMode == 0)
                plainFont.drawShadowTextHMidVTop(65280, 184, "On", 41, true);
            if(privateChatMode == 1)
                plainFont.drawShadowTextHMidVTop(0xffff00, 184, "Friends", 41, true);
            if(privateChatMode == 2)
                plainFont.drawShadowTextHMidVTop(0xff0000, 184, "Off", 41, true);
            plainFont.drawShadowTextHMidVTop(0xffffff, 324, "Trade/compete", 28, true);
            if(tradeMode == 0)
                plainFont.drawShadowTextHMidVTop(65280, 324, "On", 41, true);
            if(tradeMode == 1)
                plainFont.drawShadowTextHMidVTop(0xffff00, 324, "Friends", 41, true);
            if(tradeMode == 2)
                plainFont.drawShadowTextHMidVTop(0xff0000, 324, "Off", 41, true);
            plainFont.drawShadowTextHMidVTop(0xffffff, 458, "Report abuse", 33, true);
            aRSImageProducer_1123.drawGraphics(453, super.gameGraphics, 0);
            gameScreenCanvas.initDrawingArea();
        }
        animationTimePassed = 0;
    }

    private boolean buildFriendsListMenu(RSInterface class9)
    {
        int i = class9.contentType;
        if(i >= 1 && i <= 200 || i >= 701 && i <= 900)
        {
            if(i >= 801)
                i -= 701;
            else
            if(i >= 701)
                i -= 601;
            else
            if(i >= 101)
                i -= 101;
            else
                i--;
            menuActionName[menuActionRow] = "Remove @whi@" + friendsList[i];
            menuActionID[menuActionRow] = 792;
            menuActionRow++;
            menuActionName[menuActionRow] = "Message @whi@" + friendsList[i];
            menuActionID[menuActionRow] = 639;
            menuActionRow++;
            return true;
        }
        if(i >= 401 && i <= 500)
        {
            menuActionName[menuActionRow] = "Remove @whi@" + class9.message;
            menuActionID[menuActionRow] = 322;
            menuActionRow++;
            return true;
        } else
        {
            return false;
        }
    }

    private void renderStationaryGraphics()
    {
        StationaryGraphic stationaryGraphic = (StationaryGraphic)stationaryGraphicQueue.reverseGetFirst();
        for(; stationaryGraphic != null; stationaryGraphic = (StationaryGraphic)stationaryGraphicQueue.reverseGetNext())
            if(stationaryGraphic.z != plane || stationaryGraphic.transformationCompleted)
                stationaryGraphic.unlink();
            else
            if(loopCycle >= stationaryGraphic.stationaryGraphicLoopCycle)
            {
                stationaryGraphic.animationStep(animationTimePassed);
                if(stationaryGraphic.transformationCompleted)
                    stationaryGraphic.unlink();
                else
                    worldController.addEntityA(stationaryGraphic.z, stationaryGraphic.x, stationaryGraphic.y, stationaryGraphic.drawHeight, 0, stationaryGraphic, -1, 60, false);
            }

    }

    private void drawInterface(int j, int k, RSInterface class9, int l)
    {
        if(class9.type != 0 || class9.children == null)
            return;
        if(class9.aBoolean266 && anInt1026 != class9.id && anInt1048 != class9.id && anInt1039 != class9.id)
            return;
        int i1 = DrawingArea.topX;
        int j1 = DrawingArea.topY;
        int k1 = DrawingArea.bottomX;
        int l1 = DrawingArea.bottomY;
        DrawingArea.setDrawingArea(l + class9.height, k, k + class9.width, l);
        int i2 = class9.children.length;
        for(int j2 = 0; j2 < i2; j2++)
        {
            int k2 = class9.childX[j2] + k;
            int l2 = (class9.childY[j2] + l) - j;
            RSInterface class9_1 = RSInterface.interfaceCache[class9.children[j2]];
            k2 += class9_1.x;
            l2 += class9_1.y;
            if(class9_1.contentType > 0)
                drawFriendsListOrWelcomeScreen(class9_1);
            if(class9_1.type == 0)
            {
                if(class9_1.scrollPosition > class9_1.scrollMax - class9_1.height)
                    class9_1.scrollPosition = class9_1.scrollMax - class9_1.height;
                if(class9_1.scrollPosition < 0)
                    class9_1.scrollPosition = 0;
                drawInterface(class9_1.scrollPosition, k2, class9_1, l2);
                if(class9_1.scrollMax > class9_1.height)
                    method30(class9_1.height, class9_1.scrollPosition, l2, k2 + class9_1.width, class9_1.scrollMax);
            } else
            if(class9_1.type != 1)
                if(class9_1.type == 2)
                {
                    int i3 = 0;
                    for(int l3 = 0; l3 < class9_1.height; l3++)
                    {
                        for(int l4 = 0; l4 < class9_1.width; l4++)
                        {
                            int k5 = k2 + l4 * (32 + class9_1.invSpritePadX);
                            int j6 = l2 + l3 * (32 + class9_1.invSpritePadY);
                            if(i3 < 20)
                            {
                                k5 += class9_1.spritesX[i3];
                                j6 += class9_1.spritesY[i3];
                            }
                            if(class9_1.inventoryItemId[i3] > 0)
                            {
                                int k6 = 0;
                                int j7 = 0;
                                int j9 = class9_1.inventoryItemId[i3] - 1;
                                if(k5 > DrawingArea.topX - 32 && k5 < DrawingArea.bottomX && j6 > DrawingArea.topY - 32 && j6 < DrawingArea.bottomY || activeInterfaceType != 0 && anInt1085 == i3)
                                {
                                    int l9 = 0;
                                    if(itemSelected == 1 && anInt1283 == i3 && anInt1284 == class9_1.id)
                                        l9 = 0xffffff;
                                    Sprite class30_sub2_sub1_sub1_2 = ItemDef.getSprite(j9, class9_1.inventoryStackSize[i3], l9);
                                    if(class30_sub2_sub1_sub1_2 != null)
                                    {
                                        if(activeInterfaceType != 0 && anInt1085 == i3 && anInt1084 == class9_1.id)
                                        {
                                            k6 = super.mouseX - anInt1087;
                                            j7 = super.mouseY - anInt1088;
                                            if(k6 < 5 && k6 > -5)
                                                k6 = 0;
                                            if(j7 < 5 && j7 > -5)
                                                j7 = 0;
                                            if(anInt989 < 5)
                                            {
                                                k6 = 0;
                                                j7 = 0;
                                            }
                                            class30_sub2_sub1_sub1_2.drawSprite1(k5 + k6, j6 + j7);
                                            if(j6 + j7 < DrawingArea.topY && class9.scrollPosition > 0)
                                            {
                                                int i10 = (animationTimePassed * (DrawingArea.topY - j6 - j7)) / 3;
                                                if(i10 > animationTimePassed * 10)
                                                    i10 = animationTimePassed * 10;
                                                if(i10 > class9.scrollPosition)
                                                    i10 = class9.scrollPosition;
                                                class9.scrollPosition -= i10;
                                                anInt1088 += i10;
                                            }
                                            if(j6 + j7 + 32 > DrawingArea.bottomY && class9.scrollPosition < class9.scrollMax - class9.height)
                                            {
                                                int j10 = (animationTimePassed * ((j6 + j7 + 32) - DrawingArea.bottomY)) / 3;
                                                if(j10 > animationTimePassed * 10)
                                                    j10 = animationTimePassed * 10;
                                                if(j10 > class9.scrollMax - class9.height - class9.scrollPosition)
                                                    j10 = class9.scrollMax - class9.height - class9.scrollPosition;
                                                class9.scrollPosition += j10;
                                                anInt1088 -= j10;
                                            }
                                        } else
                                        if(atInventoryInterfaceType != 0 && atInventoryIndex == i3 && atInventoryInterface == class9_1.id)
                                            class30_sub2_sub1_sub1_2.drawSprite1(k5, j6);
                                        else
                                            class30_sub2_sub1_sub1_2.drawSprite(k5, j6);
                                        if(class30_sub2_sub1_sub1_2.anInt1444 == 33 || class9_1.inventoryStackSize[i3] != 1)
                                        {
                                            int k10 = class9_1.inventoryStackSize[i3];
                                            aTextDrawingArea_1270.drawTextHLeftVTop(intToKOrMil(k10), k5 + 1 + k6, j6 + 10 + j7, 0);
                                            aTextDrawingArea_1270.drawTextHLeftVTop(intToKOrMil(k10), k5 + k6, j6 + 9 + j7, 0xffff00);
                                        }
                                    }
                                }
                            } else
                            if(class9_1.sprites != null && i3 < 20)
                            {
                                Sprite class30_sub2_sub1_sub1_1 = class9_1.sprites[i3];
                                if(class30_sub2_sub1_sub1_1 != null)
                                    class30_sub2_sub1_sub1_1.drawSprite(k5, j6);
                            }
                            i3++;
                        }

                    }

                } else
                if(class9_1.type == 3)
                {
                    boolean flag = false;
                    if(anInt1039 == class9_1.id || anInt1048 == class9_1.id || anInt1026 == class9_1.id)
                        flag = true;
                    int j3;
                    if(interfaceIsSelected(class9_1))
                    {
                        j3 = class9_1.anInt219;
                        if(flag && class9_1.anInt239 != 0)
                            j3 = class9_1.anInt239;
                    } else
                    {
                        j3 = class9_1.textColor;
                        if(flag && class9_1.anInt216 != 0)
                            j3 = class9_1.anInt216;
                    }
                    if(class9_1.aByte254 == 0)
                    {
                        if(class9_1.aBoolean227)
                            DrawingArea.method336(class9_1.height, l2, k2, j3, class9_1.width);
                        else
                            DrawingArea.fillPixels(k2, class9_1.width, class9_1.height, j3, l2);
                    } else
                    if(class9_1.aBoolean227)
                        DrawingArea.method335(j3, l2, class9_1.width, class9_1.height, 256 - (class9_1.aByte254 & 0xff), k2);
                    else
                        DrawingArea.method338(l2, class9_1.height, 256 - (class9_1.aByte254 & 0xff), j3, class9_1.width, k2);
                } else
                if(class9_1.type == 4)
                {
                    TextDrawingArea textDrawingArea = class9_1.textDrawingAreas;
                    String s = class9_1.message;
                    boolean flag1 = false;
                    if(anInt1039 == class9_1.id || anInt1048 == class9_1.id || anInt1026 == class9_1.id)
                        flag1 = true;
                    int i4;
                    if(interfaceIsSelected(class9_1))
                    {
                        i4 = class9_1.anInt219;
                        if(flag1 && class9_1.anInt239 != 0)
                            i4 = class9_1.anInt239;
                        if(class9_1.aString228.length() > 0)
                            s = class9_1.aString228;
                    } else
                    {
                        i4 = class9_1.textColor;
                        if(flag1 && class9_1.anInt216 != 0)
                            i4 = class9_1.anInt216;
                    }
                    if(class9_1.atActionType == 6 && aBoolean1149)
                    {
                        s = "Please wait...";
                        i4 = class9_1.textColor;
                    }
                    if(DrawingArea.width == 479)
                    {
                        if(i4 == 0xffff00)
                            i4 = 255;
                        if(i4 == 49152)
                            i4 = 0xffffff;
                    }
                    for(int l6 = l2 + textDrawingArea.charHeight; s.length() > 0; l6 += textDrawingArea.charHeight)
                    {
                        if(s.indexOf("%") != -1)
                        {
                            do
                            {
                                int k7 = s.indexOf("%1");
                                if(k7 == -1)
                                    break;
                                s = s.substring(0, k7) + interfaceIntToString(extractInterfaceValues(class9_1, 0)) + s.substring(k7 + 2);
                            } while(true);
                            do
                            {
                                int l7 = s.indexOf("%2");
                                if(l7 == -1)
                                    break;
                                s = s.substring(0, l7) + interfaceIntToString(extractInterfaceValues(class9_1, 1)) + s.substring(l7 + 2);
                            } while(true);
                            do
                            {
                                int i8 = s.indexOf("%3");
                                if(i8 == -1)
                                    break;
                                s = s.substring(0, i8) + interfaceIntToString(extractInterfaceValues(class9_1, 2)) + s.substring(i8 + 2);
                            } while(true);
                            do
                            {
                                int j8 = s.indexOf("%4");
                                if(j8 == -1)
                                    break;
                                s = s.substring(0, j8) + interfaceIntToString(extractInterfaceValues(class9_1, 3)) + s.substring(j8 + 2);
                            } while(true);
                            do
                            {
                                int k8 = s.indexOf("%5");
                                if(k8 == -1)
                                    break;
                                s = s.substring(0, k8) + interfaceIntToString(extractInterfaceValues(class9_1, 4)) + s.substring(k8 + 2);
                            } while(true);
                        }
                        int l8 = s.indexOf("\\n");
                        String s1;
                        if(l8 != -1)
                        {
                            s1 = s.substring(0, l8);
                            s = s.substring(l8 + 2);
                        } else
                        {
                            s1 = s;
                            s = "";
                        }
                        if(class9_1.aBoolean223)
                            textDrawingArea.drawShadowTextHMidVTop(i4, k2 + class9_1.width / 2, s1, l6, class9_1.aBoolean268);
                        else
                            textDrawingArea.drawShadowTextHLeftVTop(s1, k2, l6, i4, class9_1.aBoolean268);
                    }

                } else
                if(class9_1.type == 5)
                {
                    Sprite sprite;
                    if(interfaceIsSelected(class9_1))
                        sprite = class9_1.sprite2;
                    else
                        sprite = class9_1.sprite1;
                    if(sprite != null)
                        sprite.drawSprite(k2, l2);
                } else
                if(class9_1.type == 6)
                {
                    int k3 = Texture.centreX;
                    int j4 = Texture.centreY;
                    Texture.centreX = k2 + class9_1.width / 2;
                    Texture.centreY = l2 + class9_1.height / 2;
                    int i5 = Texture.SINE[class9_1.modelRotationX] * class9_1.modelZoom >> 16;
                    int l5 = Texture.COSINE[class9_1.modelRotationX] * class9_1.modelZoom >> 16;
                    boolean flag2 = interfaceIsSelected(class9_1);
                    int i7;
                    if(flag2)
                        i7 = class9_1.anInt258;
                    else
                        i7 = class9_1.animationId;
                    Model model;
                    if(i7 == -1)
                    {
                        model = class9_1.method209(-1, -1, flag2);
                    } else
                    {
                        Animation animation = Animation.anims[i7];
                        model = class9_1.method209(animation.anIntArray354[class9_1.animationFrame], animation.frame2Ids[class9_1.animationFrame], flag2);
                    }
                    if(model != null)
                        model.renderSingle(class9_1.modelRotationY, 0, class9_1.modelRotationX, 0, i5, l5);
                    Texture.centreX = k3;
                    Texture.centreY = j4;
                } else
                if(class9_1.type == 7)
                {
                    TextDrawingArea textDrawingArea_1 = class9_1.textDrawingAreas;
                    int k4 = 0;
                    for(int j5 = 0; j5 < class9_1.height; j5++)
                    {
                        for(int i6 = 0; i6 < class9_1.width; i6++)
                        {
                            if(class9_1.inventoryItemId[k4] > 0)
                            {
                                ItemDef itemDef = ItemDef.forID(class9_1.inventoryItemId[k4] - 1);
                                String s2 = itemDef.name;
                                if(itemDef.stackable || class9_1.inventoryStackSize[k4] != 1)
                                    s2 = s2 + " x" + intToKOrMilLongName(class9_1.inventoryStackSize[k4]);
                                int i9 = k2 + i6 * (115 + class9_1.invSpritePadX);
                                int k9 = l2 + j5 * (12 + class9_1.invSpritePadY);
                                if(class9_1.aBoolean223)
                                    textDrawingArea_1.drawShadowTextHMidVTop(class9_1.textColor, i9 + class9_1.width / 2, s2, k9, class9_1.aBoolean268);
                                else
                                    textDrawingArea_1.drawShadowTextHLeftVTop(s2, i9, k9, class9_1.textColor, class9_1.aBoolean268);
                            }
                            k4++;
                        }

                    }

                }
        }

        DrawingArea.setDrawingArea(l1, i1, k1, j1);
    }

    private void randomizeBackground(Background background)
    {
        int j = 256;
        for(int k = 0; k < anIntArray1190.length; k++)
            anIntArray1190[k] = 0;

        for(int l = 0; l < 5000; l++)
        {
            int i1 = (int)(Math.random() * 128D * (double)j);
            anIntArray1190[i1] = (int)(Math.random() * 256D);
        }

        for(int j1 = 0; j1 < 20; j1++)
        {
            for(int k1 = 1; k1 < j - 1; k1++)
            {
                for(int i2 = 1; i2 < 127; i2++)
                {
                    int k2 = i2 + (k1 << 7);
                    anIntArray1191[k2] = (anIntArray1190[k2 - 1] + anIntArray1190[k2 + 1] + anIntArray1190[k2 - 128] + anIntArray1190[k2 + 128]) / 4;
                }

            }

            int ai[] = anIntArray1190;
            anIntArray1190 = anIntArray1191;
            anIntArray1191 = ai;
        }

        if(background != null)
        {
            int l1 = 0;
            for(int j2 = 0; j2 < background.anInt1453; j2++)
            {
                for(int l2 = 0; l2 < background.imageWidth; l2++)
                    if(background.imagePixels[l1++] != 0)
                    {
                        int i3 = l2 + 16 + background.anInt1454;
                        int j3 = j2 + 16 + background.anInt1455;
                        int k3 = i3 + (j3 << 7);
                        anIntArray1190[k3] = 0;
                    }

            }

        }
    }

    private void updatePlayer(Stream stream, int updateType, Player player, int playerId)
    {
        if((updateType & 0x400) != 0)
        {
            player.startX = stream.getUnsignedByteS();
            player.startY = stream.getUnsignedByteS();
            player.endX = stream.getUnsignedByteS();
            player.endY = stream.getUnsignedByteS();
            player.speedToStart = stream.getUnsignedShortA() + loopCycle;
            player.speedToEnd = stream.getUnsignedLEShortA() + loopCycle;
            player.direction = stream.getUnsignedByteS();
            player.resetPath();
        }
        if((updateType & 0x100) != 0)
        {
            player.graphicId = stream.getUnsignedShort();
            int delay = stream.getInt();
            player.graphicHeight = delay >> 16;
            player.graphicEndCycle = loopCycle + (delay & 0xffff);
            player.currentAnimation = 0;
            player.anInt1522 = 0;
            if(player.graphicEndCycle > loopCycle)
                player.currentAnimation = -1;
            if(player.graphicId == 65535)
                player.graphicId = -1;
        }
        if((updateType & 8) != 0)
        {
            int animationId = stream.getUnsignedShort();
            if(animationId == 65535)
                animationId = -1;
            int animationDelay = stream.getUnsignedByteC();
            if(animationId == player.animation && animationId != -1)
            {
                int i3 = Animation.anims[animationId].anInt365;
                if(i3 == 1)
                {
                    player.anInt1527 = 0;
                    player.anInt1528 = 0;
                    player.animationDelay = animationDelay;
                    player.anInt1530 = 0;
                }
                if(i3 == 2)
                    player.anInt1530 = 0;
            } else
            if(animationId == -1 || player.animation == -1 || Animation.anims[animationId].anInt359 >= Animation.anims[player.animation].anInt359)
            {
                player.animation = animationId;
                player.anInt1527 = 0;
                player.anInt1528 = 0;
                player.animationDelay = animationDelay;
                player.anInt1530 = 0;
                player.anInt1542 = player.pathLength;
            }
        }
        if((updateType & 4) != 0)
        {
            player.textSpoken = stream.getString();
            if(player.textSpoken.charAt(0) == '~')
            {
                player.textSpoken = player.textSpoken.substring(1);
                pushMessage(player.textSpoken, 2, player.name);
            } else
            if(player == localPlayer)
                pushMessage(player.textSpoken, 2, player.name);
            player.chatColour = 0;
            player.chatEffect = 0;
            player.textCycle = 150;
        }
        if((updateType & 0x80) != 0)
        {
            int colourAndEffect = stream.getUnsignedShort();
            int rights = stream.getUnsignedByte();
            int messageLength = stream.getUnsignedByteC();
            int originalOffset = stream.currentOffset;
            if(player.name != null && player.visible)
            {
                long nameAsLong = TextClass.longForName(player.name);
                boolean ignored = false;
                if(rights <= 1)
                {
                    for(int p = 0; p < ignoreCount; p++)
                    {
                        if(ignoreListAsLongs[p] != nameAsLong)
                            continue;
                        ignored = true;
                        break;
                    }

                }
                if(!ignored && inTutorial == 0)
                    try
                    {
                        textStream.currentOffset = 0;
                        stream.getBytes(messageLength, 0, textStream.buffer);
                        textStream.currentOffset = 0;
                        String text = TextInput.readFromStream(messageLength, textStream);
                        text = Censor.doCensor(text);
                        player.textSpoken = text;
                        player.chatColour = colourAndEffect >> 8;
	                    player.rights = rights;

                        //entityMessage(player);
	
                        player.chatEffect = colourAndEffect & 0xff;
                        player.textCycle = 150;
                        if(rights == 2 || rights == 3)
                            pushMessage(text, 1, "@cr2@" + player.name);
                        else
                        if(rights == 1)
                            pushMessage(text, 1, "@cr1@" + player.name);
                        else
                            pushMessage(text, 2, player.name);
                    }
                    catch(Exception exception)
                    {
                        signlink.reporterror("cde2");
                    }
            }
            stream.currentOffset = originalOffset + messageLength;
        }
        if((updateType & 1) != 0)
        {
            player.interactingEntity = stream.getUnsignedShort();
            if(player.interactingEntity == 65535)
                player.interactingEntity = -1;
        }
        if((updateType & 0x10) != 0)
        {
            int appearanceBufferSize = stream.getUnsignedByteC();
            byte _appearanceBuffer[] = new byte[appearanceBufferSize];
            Stream appearanceBuffer = new Stream(_appearanceBuffer);
            stream.readBytes(appearanceBufferSize, 0, _appearanceBuffer);
            playerBuffer[playerId] = appearanceBuffer;
            player.updatePlayerAppearance(appearanceBuffer);
        }
        if((updateType & 2) != 0)
        {
            player.faceTowardX = stream.getUnsignedShortA();
            player.faceTowardY = stream.getUnsignedShort();
        }
        if((updateType & 0x20) != 0)
        {
            int hitDamage = stream.getUnsignedByte();
            int hitType = stream.getUnsignedByteA();
            player.updateHitData(hitType, hitDamage, loopCycle);
            player.loopCycleStatus = loopCycle + 300;
            player.currentHealth = stream.getUnsignedByteC();
            player.maxHealth = stream.getUnsignedByte();
        }
        if((updateType & 0x200) != 0)
        {
            int hitDamage = stream.getUnsignedByte();
            int hitType = stream.getUnsignedByteS();
            player.updateHitData(hitType, hitDamage, loopCycle);
            player.loopCycleStatus = loopCycle + 300;
            player.currentHealth = stream.getUnsignedByte();
            player.maxHealth = stream.getUnsignedByteC();
        }
    }

    private void method108()
    {
        try
        {
            int j = localPlayer.x + anInt1278;
            int k = localPlayer.y + anInt1131;
            if(anInt1014 - j < -500 || anInt1014 - j > 500 || anInt1015 - k < -500 || anInt1015 - k > 500)
            {
                anInt1014 = j;
                anInt1015 = k;
            }
            if(anInt1014 != j)
                anInt1014 += (j - anInt1014) / 16;
            if(anInt1015 != k)
                anInt1015 += (k - anInt1015) / 16;
            if(super.keyStatus[1] == 1)
                anInt1186 += (-24 - anInt1186) / 2;
            else
            if(super.keyStatus[2] == 1)
                anInt1186 += (24 - anInt1186) / 2;
            else
                anInt1186 /= 2;
            if(super.keyStatus[3] == 1)
                anInt1187 += (12 - anInt1187) / 2;
            else
            if(super.keyStatus[4] == 1)
                anInt1187 += (-12 - anInt1187) / 2;
            else
                anInt1187 /= 2;
              minimapInt1 = minimapInt1 + anInt1186 / 2 & 0x7ff;
              anInt1184 += anInt1187 / 2;
            if(anInt1184 < 128)
                anInt1184 = 128;
            if(anInt1184 > 383)
                anInt1184 = 383;
            int l = anInt1014 >> 7;
            int i1 = anInt1015 >> 7;
            int j1 = getFloorDrawHeight(plane, anInt1015, anInt1014);
            int k1 = 0;
            if(l > 3 && i1 > 3 && l < 100 && i1 < 100)
            {
                for(int l1 = l - 4; l1 <= l + 4; l1++)
                {
                    for(int k2 = i1 - 4; k2 <= i1 + 4; k2++)
                    {
                        int l2 = plane;
                        if(l2 < 3 && (byteGroundArray[1][l1][k2] & 2) == 2)
                            l2++;
                        int i3 = j1 - intGroundArray[l2][l1][k2];
                        if(i3 > k1)
                            k1 = i3;
                    }

                }

            }
            anInt1005++;
            if(anInt1005 > 1512)
            {
                anInt1005 = 0;
                stream.putOpcode(77);
                stream.put(0);
                int i2 = stream.currentOffset;
                stream.put((int)(Math.random() * 256D));
                stream.put(101);
                stream.put(233);
                stream.putShort(45092);
                if((int)(Math.random() * 2D) == 0)
                    stream.putShort(35784);
                stream.put((int)(Math.random() * 256D));
                stream.put(64);
                stream.put(38);
                stream.putShort((int)(Math.random() * 65536D));
                stream.putShort((int)(Math.random() * 65536D));
                stream.putSizeByte(stream.currentOffset - i2);
            }
            int j2 = k1 * 192;
            if(j2 > 0x17f00)
                j2 = 0x17f00;
            if(j2 < 32768)
                j2 = 32768;
            if(j2 > anInt984)
            {
                anInt984 += (j2 - anInt984) / 24;
                return;
            }
            if(j2 < anInt984)
            {
                anInt984 += (j2 - anInt984) / 80;
            }
        }
        catch(Exception _ex)
        {
            signlink.reporterror("glfc_ex " + localPlayer.x + "," + localPlayer.y + "," + anInt1014 + "," + anInt1015 + "," + regionX + "," + regionY + "," + baseX + "," + baseY);
            throw new RuntimeException("eek");
        }
    }

    public void processDrawing()
    {
        if(rsAlreadyLoaded || loadingError || genericLoadingError)
        {
            showErrorScreen();
            return;
        }
        anInt1061++;
        if(!loggedIn)
            drawLoginScreen(false);
        else
            drawGameScreen();
        anInt1213 = 0;
    }

    private boolean isFriendOrSelf(String s)
    {
        if(s == null)
            return false;
        for(int i = 0; i < friendsCount; i++)
            if(s.equalsIgnoreCase(friendsList[i]))
                return true;
        return s.equalsIgnoreCase(localPlayer.name);
    }

    private static String combatDiffColor(int i, int j)
    {
        int k = i - j;
        if(k < -9)
            return "@red@";
        if(k < -6)
            return "@or3@";
        if(k < -3)
            return "@or2@";
        if(k < 0)
            return "@or1@";
        if(k > 9)
            return "@gre@";
        if(k > 6)
            return "@gr3@";
        if(k > 3)
            return "@gr2@";
        if(k > 0)
            return "@gr1@";
        else
            return "@yel@";
    }

    private void setWaveVolume(int i)
    {
        signlink.wavevol = i;
    }

    private void draw3dScreen()
    {
        drawSplitPrivateChat();
        if(crossType == 1)
        {
            crosses[crossIndex / 100].drawSprite(crossX - 8 - 4, crossY - 8 - 4);
            anInt1142++;
            if(anInt1142 > 67)
            {
                anInt1142 = 0;
                stream.putOpcode(78);
            }
        }
        if(crossType == 2)
            crosses[4 + crossIndex / 100].drawSprite(crossX - 8 - 4, crossY - 8 - 4);
        if(walkableInterfaceId != -1)
        {
            method119(animationTimePassed, walkableInterfaceId);
            drawInterface(0, 0, RSInterface.interfaceCache[walkableInterfaceId], 0);
        }
        if(openInterfaceId != -1)
        {
            method119(animationTimePassed, openInterfaceId);
            drawInterface(0, 0, RSInterface.interfaceCache[openInterfaceId], 0);
        }
        method70();
        if(!menuOpen)
        {
            processRightClick();
            drawTooltip();
        } else
        if(menuScreenArea == 0)
            drawMenu();
        if(multiCombatZone == 1)
            headIcons[1].drawSprite(472, 296);
        if(fpsOn)
        {
            char c = '\u01FB';
            int k = 20;
            int i1 = 0xffff00;
            if(super.fps < 15)
                i1 = 0xff0000;
            plainFont.drawTextHRightVTop("Fps:" + super.fps, c, k, i1);
            k += 15;
            Runtime runtime = Runtime.getRuntime();
            int j1 = (int)((runtime.totalMemory() - runtime.freeMemory()) / 1024L);
            i1 = 0xffff00;
            if(j1 > 0x2000000 && lowMemory)
                i1 = 0xff0000;
            plainFont.drawTextHRightVTop("Mem:" + j1 + "k", c, k, 0xffff00);
            k += 15;
        }
        if(systemUpdateTime != 0)
        {
            int j = systemUpdateTime / 50;
            int l = j / 60;
            j %= 60;
            if(j < 10)
                plainFont.drawTextHLeftVTop("System update in: " + l + ":0" + j, 4, 329, 0xffff00);
            else
                plainFont.drawTextHLeftVTop("System update in: " + l + ":" + j, 4, 329, 0xffff00);
            anInt849++;
            if(anInt849 > 75)
            {
                anInt849 = 0;
                stream.putOpcode(148);
            }
        }
    }

    private void addIgnore(long l)
    {
        try
        {
            if(l == 0L)
                return;
            if(ignoreCount >= 100)
            {
                pushMessage("Your ignore list is full. Max of 100 hit", 0, "");
                return;
            }
            String s = TextClass.formatName(TextClass.nameForLong(l));
            for(int j = 0; j < ignoreCount; j++)
                if(ignoreListAsLongs[j] == l)
                {
                    pushMessage(s + " is already on your ignore list", 0, "");
                    return;
                }
            for(int k = 0; k < friendsCount; k++)
                if(friendsListAsLongs[k] == l)
                {
                    pushMessage("Please remove " + s + " from your friend list first", 0, "");
                    return;
                }

            ignoreListAsLongs[ignoreCount++] = l;
            needDrawTabArea = true;
            stream.putOpcode(133);
            stream.putLong(l);
            return;
        }
        catch(RuntimeException runtimeexception)
        {
            signlink.reporterror("45688, " + l + ", " + 4 + ", " + runtimeexception.toString());
        }
        throw new RuntimeException();
    }

    private void updatePlayerInstances()
    {
        for(int p = -1; p < playerCount; p++)
        {
            int id;
            if(p == -1)
                id = localPlayerId;
            else
                id = playerId[p];
            Player player = playerArray[id];
            if(player != null)
                updateEntity(player);
        }

    }

    private void updateSpawnedObjects()
    {
        if(loadingStage == 2)
        {
            for(GameObjectSpawnRequest spawnRequest = (GameObjectSpawnRequest)spawnObjectList.reverseGetFirst(); spawnRequest != null; spawnRequest = (GameObjectSpawnRequest)spawnObjectList.reverseGetNext())
            {
                if(spawnRequest.delayUntilRespawn > 0)
                    spawnRequest.delayUntilRespawn--;
                if(spawnRequest.delayUntilRespawn == 0)
                {
                    if(spawnRequest.id < 0 || ObjectManager.method178(spawnRequest.id, spawnRequest.type))
                    {
                        method142(spawnRequest.y, spawnRequest.z, spawnRequest.face, spawnRequest.type, spawnRequest.x, spawnRequest.objectType, spawnRequest.id);
                        spawnRequest.unlink();
                    }
                } else
                {
                    if(spawnRequest.delayUntilSpawn > 0)
                        spawnRequest.delayUntilSpawn--;
                    if(spawnRequest.delayUntilSpawn == 0 && spawnRequest.x >= 1 && spawnRequest.y >= 1 && spawnRequest.x <= 102 && spawnRequest.y <= 102 && (spawnRequest.id2 < 0 || ObjectManager.method178(spawnRequest.id2, spawnRequest.type2)))
                    {
                        method142(spawnRequest.y, spawnRequest.z, spawnRequest.face2, spawnRequest.type2, spawnRequest.x, spawnRequest.objectType, spawnRequest.id2);
                        spawnRequest.delayUntilSpawn = -1;
                        if(spawnRequest.id2 == spawnRequest.id && spawnRequest.id == -1)
                            spawnRequest.unlink();
                        else
                        if(spawnRequest.id2 == spawnRequest.id && spawnRequest.face2 == spawnRequest.face && spawnRequest.type2 == spawnRequest.type)
                            spawnRequest.unlink();
                    }
                }
            }

        }
    }

    private void processMenuHovering()
    {
        int width = chatTextDrawingArea.getFormattedStringWidth("Choose Option");
        for(int row = 0; row < menuActionRow; row++)
        {
            int rowWidth = chatTextDrawingArea.getFormattedStringWidth(menuActionName[row]);
            if(rowWidth > width)
                width = rowWidth;
        }

        width += 8;
        int height = 15 * menuActionRow + 21;
        if(super.clickX > 4 && super.clickY > 4 && super.clickX < 516 && super.clickY < 338)
        {
            int x = super.clickX - 4 - width / 2;
            if(x + width > 512)
                x = 512 - width;
            if(x < 0)
                x = 0;
            int y = super.clickY - 4;
            if(y + height > 334)
                y = 334 - height;
            if(y < 0)
                y = 0;
            menuOpen = true;
            menuScreenArea = 0;
            menuOffsetX = x;
            menuOffsetY = y;
            menuWidth = width;
            menuHeight = 15 * menuActionRow + 22;
        }
        if(super.clickX > 553 && super.clickY > 205 && super.clickX < 743 && super.clickY < 466)
        {
            int x = super.clickX - 553 - width / 2;
            if(x < 0)
                x = 0;
            else
            if(x + width > 190)
                x = 190 - width;
            int y = super.clickY - 205;
            if(y < 0)
                y = 0;
            else
            if(y + height > 261)
                y = 261 - height;
            menuOpen = true;
            menuScreenArea = 1;
            menuOffsetX = x;
            menuOffsetY = y;
            menuWidth = width;
            menuHeight = 15 * menuActionRow + 22;
        }
        if(super.clickX > 17 && super.clickY > 357 && super.clickX < 496 && super.clickY < 453)
        {
            int x = super.clickX - 17 - width / 2;
            if(x < 0)
                x = 0;
            else
            if(x + width > 479)
                x = 479 - width;
            int y = super.clickY - 357;
            if(y < 0)
                y = 0;
            else
            if(y + height > 96)
                y = 96 - height;
            menuOpen = true;
            menuScreenArea = 2;
            menuOffsetX = x;
            menuOffsetY = y;
            menuWidth = width;
            menuHeight = 15 * menuActionRow + 22;
        }
    }

    private void updateLocalPlayerMovement(Stream stream)
    {
        stream.initBitAccess();
        int currentlyUpdating = stream.readBits(1);
        if(currentlyUpdating == 0)
            return;
        int movementUpdateType = stream.readBits(2);
        if(movementUpdateType == 0)
        {
            playersAwaitingUpdate[actorsAwaitingUpdatePointer++] = localPlayerId;
            return;
        }
        if(movementUpdateType == 1)
        {
            int direction = stream.readBits(3);
            localPlayer.move(false, direction);
            int furtherUpdateRequired = stream.readBits(1);
            if(furtherUpdateRequired == 1)
                playersAwaitingUpdate[actorsAwaitingUpdatePointer++] = localPlayerId;
            return;
        }
        if(movementUpdateType == 2)
        {
            int lastDirection = stream.readBits(3);
            localPlayer.move(true, lastDirection);
            int currentDirection = stream.readBits(3);
            localPlayer.move(true, currentDirection);
            int updateRequired = stream.readBits(1);
            if(updateRequired == 1)
                playersAwaitingUpdate[actorsAwaitingUpdatePointer++] = localPlayerId;
            return;
        }
        if(movementUpdateType == 3)
        {
            plane = stream.readBits(2);
            int clearWaypointQueue = stream.readBits(1);
            int updateRequired = stream.readBits(1);
            if(updateRequired == 1)
                playersAwaitingUpdate[actorsAwaitingUpdatePointer++] = localPlayerId;
            int x = stream.readBits(7);
            int y = stream.readBits(7);
            localPlayer.setPos(y, x, clearWaypointQueue == 1);
        }
    }

    private void nullLoader()
    {
        currentlyDrawingFlames = false;
        while(drawingFlames)
        {
            currentlyDrawingFlames = false;
            try
            {
                Thread.sleep(50L);
            }
            catch(Exception _ex) { }
        }
        aBackground_966 = null;
        aBackground_967 = null;
        aBackgroundArray1152s = null;
        anIntArray850 = null;
        anIntArray851 = null;
        anIntArray852 = null;
        anIntArray853 = null;
        anIntArray1190 = null;
        anIntArray1191 = null;
        anIntArray828 = null;
        anIntArray829 = null;
        aClass30_Sub2_Sub1_Sub1_1201 = null;
        aClass30_Sub2_Sub1_Sub1_1202 = null;
    }

    private boolean method119(int i, int j)
    {
        boolean flag1 = false;
        RSInterface class9 = RSInterface.interfaceCache[j];
        for(int k = 0; k < class9.children.length; k++)
        {
            if(class9.children[k] == -1)
                break;
            RSInterface class9_1 = RSInterface.interfaceCache[class9.children[k]];
            if(class9_1.type == 1)
                flag1 |= method119(i, class9_1.id);
            if(class9_1.type == 6 && (class9_1.animationId != -1 || class9_1.anInt258 != -1))
            {
                boolean flag2 = interfaceIsSelected(class9_1);
                int l;
                if(flag2)
                    l = class9_1.anInt258;
                else
                    l = class9_1.animationId;
                if(l != -1)
                {
                    Animation animation = Animation.anims[l];
                    for(class9_1.animationDuration += i; class9_1.animationDuration > animation.getFrameLength(class9_1.animationFrame);)
                    {
                        class9_1.animationDuration -= animation.getFrameLength(class9_1.animationFrame) + 1;
                        class9_1.animationFrame++;
                        if(class9_1.animationFrame >= animation.frameCount)
                        {
                            class9_1.animationFrame -= animation.frameStep;
                            if(class9_1.animationFrame < 0 || class9_1.animationFrame >= animation.frameCount)
                                class9_1.animationFrame = 0;
                        }
                        flag1 = true;
                    }

                }
            }
        }

        return flag1;
    }

    private int method120()
    {
        int j = 3;
        if(cameraCurveY < 310)
        {
            int k = cameraPositionX >> 7;
            int l = cameraPositionY >> 7;
            int i1 = localPlayer.x >> 7;
            int j1 = localPlayer.y >> 7;
            if((byteGroundArray[plane][k][l] & 4) != 0)
                j = plane;
            int k1;
            if(i1 > k)
                k1 = i1 - k;
            else
                k1 = k - i1;
            int l1;
            if(j1 > l)
                l1 = j1 - l;
            else
                l1 = l - j1;
            if(k1 > l1)
            {
                int i2 = (l1 * 0x10000) / k1;
                int k2 = 32768;
                while(k != i1) 
                {
                    if(k < i1)
                        k++;
                    else
                    if(k > i1)
                        k--;
                    if((byteGroundArray[plane][k][l] & 4) != 0)
                        j = plane;
                    k2 += i2;
                    if(k2 >= 0x10000)
                    {
                        k2 -= 0x10000;
                        if(l < j1)
                            l++;
                        else
                        if(l > j1)
                            l--;
                        if((byteGroundArray[plane][k][l] & 4) != 0)
                            j = plane;
                    }
                }
            } else
            {
                int j2 = (k1 * 0x10000) / l1;
                int l2 = 32768;
                while(l != j1) 
                {
                    if(l < j1)
                        l++;
                    else
                    if(l > j1)
                        l--;
                    if((byteGroundArray[plane][k][l] & 4) != 0)
                        j = plane;
                    l2 += j2;
                    if(l2 >= 0x10000)
                    {
                        l2 -= 0x10000;
                        if(k < i1)
                            k++;
                        else
                        if(k > i1)
                            k--;
                        if((byteGroundArray[plane][k][l] & 4) != 0)
                            j = plane;
                    }
                }
            }
        }
        if((byteGroundArray[plane][localPlayer.x >> 7][localPlayer.y >> 7] & 4) != 0)
            j = plane;
        return j;
    }

    private int method121()
    {
        int j = getFloorDrawHeight(plane, cameraPositionY, cameraPositionX);
        if(j - cameraPositionZ < 800 && (byteGroundArray[plane][cameraPositionX >> 7][cameraPositionY >> 7] & 4) != 0)
            return plane;
        else
            return 3;
    }

    private void deleteIgnore(long l)
    {
        try
        {
            if(l == 0L)
                return;
            for(int j = 0; j < ignoreCount; j++)
                if(ignoreListAsLongs[j] == l)
                {
                    ignoreCount--;
                    needDrawTabArea = true;
                    System.arraycopy(ignoreListAsLongs, j + 1, ignoreListAsLongs, j, ignoreCount - j);

                    stream.putOpcode(74);
                    stream.putLong(l);
                    return;
                }

            return;
        }
        catch(RuntimeException runtimeexception)
        {
            signlink.reporterror("47229, " + 3 + ", " + l + ", " + runtimeexception.toString());
        }
        throw new RuntimeException();
    }

    public String getParameter(String s)
    {
        if(signlink.mainapp != null)
            return signlink.mainapp.getParameter(s);
        else
            return super.getParameter(s);
    }

    private void adjustVolume(boolean flag, int i)
    {
        signlink.midivol = i;
        if(flag)
            signlink.midi = "voladjust";
    }

    private int extractInterfaceValues(RSInterface class9, int j)
    {
        if(class9.valueIndexArray == null || j >= class9.valueIndexArray.length)
            return -2;
        try
        {
            int ai[] = class9.valueIndexArray[j];
            int k = 0;
            int l = 0;
            int i1 = 0;
            do
            {
                int j1 = ai[l++];
                int k1 = 0;
                byte byte0 = 0;
                if(j1 == 0)
                    return k;
                if(j1 == 1)
                    k1 = currentStats[ai[l++]];
                if(j1 == 2)
                    k1 = maxStats[ai[l++]];
                if(j1 == 3)
                    k1 = currentExp[ai[l++]];
                if(j1 == 4)
                {
                    RSInterface class9_1 = RSInterface.interfaceCache[ai[l++]];
                    int k2 = ai[l++];
                    if(k2 >= 0 && k2 < ItemDef.totalItems && (!ItemDef.forID(k2).membersObject || isMembers))
                    {
                        for(int j3 = 0; j3 < class9_1.inventoryItemId.length; j3++)
                            if(class9_1.inventoryItemId[j3] == k2 + 1)
                                k1 += class9_1.inventoryStackSize[j3];

                    }
                }
                if(j1 == 5)
                    k1 = variousSettings[ai[l++]];
                if(j1 == 6)
                    k1 = xpForLevel[maxStats[ai[l++]] - 1];
                if(j1 == 7)
                    k1 = (variousSettings[ai[l++]] * 100) / 46875;
                if(j1 == 8)
                    k1 = localPlayer.combatLevel;
                if(j1 == 9)
                {
                    for(int l1 = 0; l1 < Skills.skillsCount; l1++)
                        if(Skills.skillEnabled[l1])
                            k1 += maxStats[l1];

                }
                if(j1 == 10)
                {
                    RSInterface class9_2 = RSInterface.interfaceCache[ai[l++]];
                    int l2 = ai[l++] + 1;
                    if(l2 >= 0 && l2 < ItemDef.totalItems && (!ItemDef.forID(l2).membersObject || isMembers))
                    {
                        for(int k3 = 0; k3 < class9_2.inventoryItemId.length; k3++)
                        {
                            if(class9_2.inventoryItemId[k3] != l2)
                                continue;
                            k1 = 0x3b9ac9ff;
                            break;
                        }

                    }
                }
                if(j1 == 11)
                    k1 = runEnergy;
                if(j1 == 12)
                    k1 = weight;
                if(j1 == 13)
                {
                    int i2 = variousSettings[ai[l++]];
                    int i3 = ai[l++];
                    k1 = (i2 & 1 << i3) == 0 ? 0 : 1;
                }
                if(j1 == 14)
                {
                    int j2 = ai[l++];
                    VarBit varBit = VarBit.cache[j2];
                    int l3 = varBit.configId;
                    int i4 = varBit.leastSignificantBit;
                    int j4 = varBit.mostSignificantBit;
                    int k4 = anIntArray1232[j4 - i4];
                    k1 = variousSettings[l3] >> i4 & k4;
                }
                if(j1 == 15)
                    byte0 = 1;
                if(j1 == 16)
                    byte0 = 2;
                if(j1 == 17)
                    byte0 = 3;
                if(j1 == 18)
                    k1 = (localPlayer.x >> 7) + baseX;
                if(j1 == 19)
                    k1 = (localPlayer.y >> 7) + baseY;
                if(j1 == 20)
                    k1 = ai[l++];
                if(byte0 == 0)
                {
                    if(i1 == 0)
                        k += k1;
                    if(i1 == 1)
                        k -= k1;
                    if(i1 == 2 && k1 != 0)
                        k /= k1;
                    if(i1 == 3)
                        k *= k1;
                    i1 = 0;
                } else
                {
                    i1 = byte0;
                }
            } while(true);
        }
        catch(Exception _ex)
        {
            return -1;
        }
    }

    private void drawTooltip()
    {
        if(menuActionRow < 2 && itemSelected == 0 && spellSelected == 0)
            return;
        String s;
        if(itemSelected == 1 && menuActionRow < 2)
            s = "Use " + selectedItemName + " with...";
        else
        if(spellSelected == 1 && menuActionRow < 2)
            s = spellTooltip + "...";
        else
            s = menuActionName[menuActionRow - 1];
        if(menuActionRow > 2)
            s = s + "@whi@ / " + (menuActionRow - 2) + " more options";
        chatTextDrawingArea.drawShadowedTextRight(s, 4, 15, 0xffffff, loopCycle / 1000);
    }

    private void drawMinimap()
    {
        aRSImageProducer_1164.initDrawingArea();
        if(minimapState == 2)
        {
            byte abyte0[] = mapBack.imagePixels;
            int ai[] = DrawingArea.pixels;
            int k2 = abyte0.length;
            for(int i5 = 0; i5 < k2; i5++)
                if(abyte0[i5] == 0)
                    ai[i5] = 0;

            compass.method352(33, minimapInt1, anIntArray1057, 256, anIntArray968, 25, 0, 0, 33, 25);
            gameScreenCanvas.initDrawingArea();
            return;
        }
        int i = minimapInt1 + minimapRotation & 0x7ff;
        int j = 48 + localPlayer.x / 32;
        int l2 = 464 - localPlayer.y / 32;
        aClass30_Sub2_Sub1_Sub1_1263.method352(151, i, anIntArray1229, 256 + minimapZoom, anIntArray1052, l2, 5, 25, 146, j);
        compass.method352(33, minimapInt1, anIntArray1057, 256, anIntArray968, 25, 0, 0, 33, 25);
        for(int j5 = 0; j5 < anInt1071; j5++)
        {
            int k = (anIntArray1072[j5] * 4 + 2) - localPlayer.x / 32;
            int i3 = (anIntArray1073[j5] * 4 + 2) - localPlayer.y / 32;
            markMinimap(aClass30_Sub2_Sub1_Sub1Array1140[j5], k, i3);
        }

        for(int k5 = 0; k5 < 104; k5++)
        {
            for(int l5 = 0; l5 < 104; l5++)
            {
                NodeList class19 = groundArray[plane][k5][l5];
                if(class19 != null)
                {
                    int l = (k5 * 4 + 2) - localPlayer.x / 32;
                    int j3 = (l5 * 4 + 2) - localPlayer.y / 32;
                    markMinimap(mapDotItem, l, j3);
                }
            }

        }

        for(int i6 = 0; i6 < npcCount; i6++)
        {
            NPC npc = npcArray[npcIndices[i6]];
            if(npc != null && npc.isVisible())
            {
                EntityDef entityDef = npc.npcDefinition;
                if(entityDef.childrenIDs != null)
                    entityDef = entityDef.method161();
                if(entityDef != null && entityDef.aBoolean87 && entityDef.aBoolean84)
                {
                    int i1 = npc.x / 32 - localPlayer.x / 32;
                    int k3 = npc.y / 32 - localPlayer.y / 32;
                    markMinimap(mapDotNPC, i1, k3);
                }
            }
        }

        for(int j6 = 0; j6 < playerCount; j6++)
        {
            Player player = playerArray[playerId[j6]];
            if(player != null && player.isVisible())
            {
                int j1 = player.x / 32 - localPlayer.x / 32;
                int l3 = player.y / 32 - localPlayer.y / 32;
                boolean flag1 = false;
                long l6 = TextClass.longForName(player.name);
                for(int k6 = 0; k6 < friendsCount; k6++)
                {
                    if(l6 != friendsListAsLongs[k6] || friendsWorldIds[k6] == 0)
                        continue;
                    flag1 = true;
                    break;
                }

                boolean flag2 = false;
                if(localPlayer.team != 0 && player.team != 0 && localPlayer.team == player.team)
                    flag2 = true;
                if(flag1)
                    markMinimap(mapDotFriend, j1, l3);
                else
                if(flag2)
                    markMinimap(mapDotTeam, j1, l3);
                else
                    markMinimap(mapDotPlayer, j1, l3);
            }
        }

        if(hintIconType != 0 && loopCycle % 20 < 10)
        {
            if(hintIconType == 1 && hintIconNpcId >= 0 && hintIconNpcId < npcArray.length)
            {
                NPC class30_sub2_sub4_sub1_sub1_1 = npcArray[hintIconNpcId];
                if(class30_sub2_sub4_sub1_sub1_1 != null)
                {
                    int k1 = class30_sub2_sub4_sub1_sub1_1.x / 32 - localPlayer.x / 32;
                    int i4 = class30_sub2_sub4_sub1_sub1_1.y / 32 - localPlayer.y / 32;
                    method81(mapMarker, i4, k1);
                }
            }
            if(hintIconType == 2)
            {
                int l1 = ((hintIconX - baseX) * 4 + 2) - localPlayer.x / 32;
                int j4 = ((hintIconY - baseY) * 4 + 2) - localPlayer.y / 32;
                method81(mapMarker, j4, l1);
            }
            if(hintIconType == 10 && hintIconPlayerId >= 0 && hintIconPlayerId < playerArray.length)
            {
                Player class30_sub2_sub4_sub1_sub2_1 = playerArray[hintIconPlayerId];
                if(class30_sub2_sub4_sub1_sub2_1 != null)
                {
                    int i2 = class30_sub2_sub4_sub1_sub2_1.x / 32 - localPlayer.x / 32;
                    int k4 = class30_sub2_sub4_sub1_sub2_1.y / 32 - localPlayer.y / 32;
                    method81(mapMarker, k4, i2);
                }
            }
        }
        if(destX != 0)
        {
            int j2 = (destX * 4 + 2) - localPlayer.x / 32;
            int l4 = (destY * 4 + 2) - localPlayer.y / 32;
            markMinimap(mapFlag, j2, l4);
        }
        DrawingArea.method336(3, 78, 97, 0xffffff, 3);
        gameScreenCanvas.initDrawingArea();
    }

    private void npcScreenPos(Entity entity, int i)
    {
        calcEntityScreenPos(entity.x, i, entity.y);

//aryan	entity.entScreenX = spriteDrawX; entity.entScreenY = spriteDrawY;
	    }

    private void calcEntityScreenPos(int i, int j, int l)
    {
        if(i < 128 || l < 128 || i > 13056 || l > 13056)
        {
            spriteDrawX = -1;
            spriteDrawY = -1;
            return;
        }
        int i1 = getFloorDrawHeight(plane, l, i) - j;
        i -= cameraPositionX;
        i1 -= cameraPositionZ;
        l -= cameraPositionY;
        int j1 = Model.SINE[cameraCurveY];
        int k1 = Model.COSINE[cameraCurveY];
        int l1 = Model.SINE[cameraCurveZ];
        int i2 = Model.COSINE[cameraCurveZ];
        int j2 = l * l1 + i * i2 >> 16;
        l = l * i2 - i * l1 >> 16;
        i = j2;
        j2 = i1 * k1 - l * j1 >> 16;
        l = i1 * j1 + l * k1 >> 16;
        i1 = j2;
        if(l >= 50)
        {
            spriteDrawX = Texture.centreX + (i << 9) / l;
            spriteDrawY = Texture.centreY + (i1 << 9) / l;
        } else
        {
            spriteDrawX = -1;
            spriteDrawY = -1;
        }
    }

    private void buildSplitPrivateChatMenu()
    {
        if(splitPrivateChat == 0)
            return;
        int i = 0;
        if(systemUpdateTime != 0)
            i = 1;
        for(int j = 0; j < 100; j++)
            if(chatMessages[j] != null)
            {
                int k = chatTypes[j];
                String s = chatNames[j];
                boolean flag1 = false;
                if(s != null && s.startsWith("@cr1@"))
                {
                    s = s.substring(5);
                    boolean flag2 = true;
                }
                if(s != null && s.startsWith("@cr2@"))
                {
                    s = s.substring(5);
                    byte byte0 = 2;
                }
                if((k == 3 || k == 7) && (k == 7 || privateChatMode == 0 || privateChatMode == 1 && isFriendOrSelf(s)))
                {
                    int l = 329 - i * 13;
                    if(super.mouseX > 4 && super.mouseY - 4 > l - 10 && super.mouseY - 4 <= l + 3)
                    {
                        int i1 = plainFont.getFormattedStringWidth("From:  " + s + chatMessages[j]) + 25;
                        if(i1 > 450)
                            i1 = 450;
                        if(super.mouseX < 4 + i1)
                        {
                            if(playerRights >= 1)
                            {
                                menuActionName[menuActionRow] = "Report abuse @whi@" + s;
                                menuActionID[menuActionRow] = 2606;
                                menuActionRow++;
                            }
                            menuActionName[menuActionRow] = "Add ignore @whi@" + s;
                            menuActionID[menuActionRow] = 2042;
                            menuActionRow++;
                            menuActionName[menuActionRow] = "Add friend @whi@" + s;
                            menuActionID[menuActionRow] = 2337;
                            menuActionRow++;
                        }
                    }
                    if(++i >= 5)
                        return;
                }
                if((k == 5 || k == 6) && privateChatMode < 2 && ++i >= 5)
                    return;
            }

    }

    private void createObjectSpawnRequest(int delayUntilRespawn, int id2, int face2, int type, int y, int type2,
                           int z, int x, int delayUntilSpawn)
    {
        GameObjectSpawnRequest request = null;
        for(GameObjectSpawnRequest request2 = (GameObjectSpawnRequest)spawnObjectList.reverseGetFirst(); request2 != null; request2 = (GameObjectSpawnRequest)spawnObjectList.reverseGetNext())
        {
            if(request2.z != z || request2.x != x || request2.y != y || request2.objectType != type)
                continue;
            
            request = request2;
            break;
        }

        if(request == null)
        {
            request = new GameObjectSpawnRequest();
            request.z = z;
            request.objectType = type;
            request.x = x;
            request.y = y;
            configureSpawnRequest(request);
            spawnObjectList.insertHead(request);
        }
        request.id2 = id2;
        request.type2 = type2;
        request.face2 = face2;
        request.delayUntilSpawn = delayUntilSpawn;
        request.delayUntilRespawn = delayUntilRespawn;
    }

    private boolean interfaceIsSelected(RSInterface class9)
    {
        if(class9.anIntArray245 == null)
            return false;
        for(int i = 0; i < class9.anIntArray245.length; i++)
        {
            int j = extractInterfaceValues(class9, i);
            int k = class9.anIntArray212[i];
            if(class9.anIntArray245[i] == 2)
            {
                if(j >= k)
                    return false;
            } else
            if(class9.anIntArray245[i] == 3)
            {
                if(j <= k)
                    return false;
            } else
            if(class9.anIntArray245[i] == 4)
            {
                if(j == k)
                    return false;
            } else
            if(j != k)
                return false;
        }

        return true;
    }

    private DataInputStream openJagGrabInputStream(String s)
        throws IOException
    {
 //       if(!aBoolean872)
 //           if(signlink.mainapp != null)
 //               return signlink.openurl(s);
 //           else
 //               return new DataInputStream((new URL(getCodeBase(), s)).openStream());
        if(aSocket832 != null)
        {
            try
            {
                aSocket832.close();
            }
            catch(Exception _ex) { }
            aSocket832 = null;
        }
        aSocket832 = openSocket(43595);
        aSocket832.setSoTimeout(10000);
        java.io.InputStream inputstream = aSocket832.getInputStream();
        OutputStream outputstream = aSocket832.getOutputStream();
        outputstream.write(("JAGGRAB /" + s + "\n\n").getBytes());
        return new DataInputStream(inputstream);
    }

    private void doFlamesDrawing()
    {
        char c = '\u0100';
        if(anInt1040 > 0)
        {
            for(int i = 0; i < 256; i++)
                if(anInt1040 > 768)
                    anIntArray850[i] = method83(anIntArray851[i], anIntArray852[i], 1024 - anInt1040);
                else
                if(anInt1040 > 256)
                    anIntArray850[i] = anIntArray852[i];
                else
                    anIntArray850[i] = method83(anIntArray852[i], anIntArray851[i], 256 - anInt1040);

        } else
        if(anInt1041 > 0)
        {
            for(int j = 0; j < 256; j++)
                if(anInt1041 > 768)
                    anIntArray850[j] = method83(anIntArray851[j], anIntArray853[j], 1024 - anInt1041);
                else
                if(anInt1041 > 256)
                    anIntArray850[j] = anIntArray853[j];
                else
                    anIntArray850[j] = method83(anIntArray853[j], anIntArray851[j], 256 - anInt1041);

        } else
        {
            System.arraycopy(anIntArray851, 0, anIntArray850, 0, 256);

        }
        System.arraycopy(aClass30_Sub2_Sub1_Sub1_1201.myPixels, 0, aRSImageProducer_1110.anIntArray315, 0, 33920);

        int i1 = 0;
        int j1 = 1152;
        for(int k1 = 1; k1 < c - 1; k1++)
        {
            int l1 = (anIntArray969[k1] * (c - k1)) / c;
            int j2 = 22 + l1;
            if(j2 < 0)
                j2 = 0;
            i1 += j2;
            for(int l2 = j2; l2 < 128; l2++)
            {
                int j3 = anIntArray828[i1++];
                if(j3 != 0)
                {
                    int l3 = j3;
                    int j4 = 256 - j3;
                    j3 = anIntArray850[j3];
                    int l4 = aRSImageProducer_1110.anIntArray315[j1];
                    aRSImageProducer_1110.anIntArray315[j1++] = ((j3 & 0xff00ff) * l3 + (l4 & 0xff00ff) * j4 & 0xff00ff00) + ((j3 & 0xff00) * l3 + (l4 & 0xff00) * j4 & 0xff0000) >> 8;
                } else
                {
                    j1++;
                }
            }

            j1 += j2;
        }

        aRSImageProducer_1110.drawGraphics(0, super.gameGraphics, 0);
        System.arraycopy(aClass30_Sub2_Sub1_Sub1_1202.myPixels, 0, aRSImageProducer_1111.anIntArray315, 0, 33920);

        i1 = 0;
        j1 = 1176;
        for(int k2 = 1; k2 < c - 1; k2++)
        {
            int i3 = (anIntArray969[k2] * (c - k2)) / c;
            int k3 = 103 - i3;
            j1 += i3;
            for(int i4 = 0; i4 < k3; i4++)
            {
                int k4 = anIntArray828[i1++];
                if(k4 != 0)
                {
                    int i5 = k4;
                    int j5 = 256 - k4;
                    k4 = anIntArray850[k4];
                    int k5 = aRSImageProducer_1111.anIntArray315[j1];
                    aRSImageProducer_1111.anIntArray315[j1++] = ((k4 & 0xff00ff) * i5 + (k5 & 0xff00ff) * j5 & 0xff00ff00) + ((k4 & 0xff00) * i5 + (k5 & 0xff00) * j5 & 0xff0000) >> 8;
                } else
                {
                    j1++;
                }
            }

            i1 += 128 - k3;
            j1 += 128 - k3 - i3;
        }

        aRSImageProducer_1111.drawGraphics(0, super.gameGraphics, 637);
    }

    private void updateOtherPlayerMovement(Stream stream)
    {
        int playersToUpdate = stream.readBits(8);
        if(playersToUpdate < playerCount)
        {
            for(int p = playersToUpdate; p < playerCount; p++)
                anIntArray840[anInt839++] = playerId[p];

        }
        if(playersToUpdate > playerCount)
        {
            signlink.reporterror(myUsername + " Too many players");
            throw new RuntimeException("eek");
        }
        playerCount = 0;
        for(int p = 0; p < playersToUpdate; p++)
        {
            int pId = playerId[p];
            Player player = playerArray[pId];
            int updateRequired = stream.readBits(1);
            if(updateRequired == 0)
            {
                playerId[playerCount++] = pId;
                player.lastUpdateTime = loopCycle;
            } else
            {
                int movementUpdateType = stream.readBits(2);
                if(movementUpdateType == 0)
                {
                    playerId[playerCount++] = pId;
                    player.lastUpdateTime = loopCycle;
                    playersAwaitingUpdate[actorsAwaitingUpdatePointer++] = pId;
                } else
                if(movementUpdateType == 1)
                {
                    playerId[playerCount++] = pId;
                    player.lastUpdateTime = loopCycle;
                    int direction = stream.readBits(3);
                    player.move(false, direction);
                    int furtherUpdateRequired = stream.readBits(1);
                    if(furtherUpdateRequired == 1)
                        playersAwaitingUpdate[actorsAwaitingUpdatePointer++] = pId;
                } else
                if(movementUpdateType == 2)
                {
                    playerId[playerCount++] = pId;
                    player.lastUpdateTime = loopCycle;
                    int lastDirection = stream.readBits(3);
                    player.move(true, lastDirection);
                    int currentDirection = stream.readBits(3);
                    player.move(true, currentDirection);
                    int furtherUpdateRequired = stream.readBits(1);
                    if(furtherUpdateRequired == 1)
                        playersAwaitingUpdate[actorsAwaitingUpdatePointer++] = pId;
                } else
                if(movementUpdateType == 3)
                    anIntArray840[anInt839++] = pId;
            }
        }
    }

    private void drawLoginScreen(boolean flag)
    {
        resetImageProducers();
        aRSImageProducer_1109.initDrawingArea();
        aBackground_966.method361(0, 0);
        char c = '\u0168';
        char c1 = '\310';
        if(loginScreenState == 0)
        {
            int i = c1 / 2 + 80;
            aTextDrawingArea_1270.drawShadowTextHMidVTop(0x75a9a9, c / 2, onDemandFetcher.statusString, i, true);
            i = c1 / 2 - 20;
            chatTextDrawingArea.drawShadowTextHMidVTop(0xffff00, c / 2, "Welcome to RuneScape", i, true);
            i += 30;
            int l = c / 2 - 80;
            int k1 = c1 / 2 + 20;
            aBackground_967.method361(l - 73, k1 - 20);
            chatTextDrawingArea.drawShadowTextHMidVTop(0xffffff, l, "New User", k1 + 5, true);
            l = c / 2 + 80;
            aBackground_967.method361(l - 73, k1 - 20);
            chatTextDrawingArea.drawShadowTextHMidVTop(0xffffff, l, "Existing User", k1 + 5, true);
        }
        if(loginScreenState == 2)
        {
            int j = c1 / 2 - 40;
            if(loginMessage1.length() > 0)
            {
                chatTextDrawingArea.drawShadowTextHMidVTop(0xffff00, c / 2, loginMessage1, j - 15, true);
                chatTextDrawingArea.drawShadowTextHMidVTop(0xffff00, c / 2, loginMessage2, j, true);
                j += 30;
            } else
            {
                chatTextDrawingArea.drawShadowTextHMidVTop(0xffff00, c / 2, loginMessage2, j - 7, true);
                j += 30;
            }
            chatTextDrawingArea.drawShadowTextHLeftVTop("Username: " + myUsername + ((loginScreenCursorPos == 0) & (loopCycle % 40 < 20) ? "@yel@|" : ""), c / 2 - 90, j, 0xffffff, true);
            j += 15;
            chatTextDrawingArea.drawShadowTextHLeftVTop("Password: " + TextClass.asterisksForString(myPassword) + ((loginScreenCursorPos == 1) & (loopCycle % 40 < 20) ? "@yel@|" : ""), c / 2 - 88, j, 0xffffff, true);
            j += 15;
            if(!flag)
            {
                int i1 = c / 2 - 80;
                int l1 = c1 / 2 + 50;
                aBackground_967.method361(i1 - 73, l1 - 20);
                chatTextDrawingArea.drawShadowTextHMidVTop(0xffffff, i1, "Login", l1 + 5, true);
                i1 = c / 2 + 80;
                aBackground_967.method361(i1 - 73, l1 - 20);
                chatTextDrawingArea.drawShadowTextHMidVTop(0xffffff, i1, "Cancel", l1 + 5, true);
            }
        }
        if(loginScreenState == 3)
        {
                        chatTextDrawingArea.drawShadowTextHMidVTop(0xffff00, c / 2, "Create a free account", c1 / 2 - 60, true);
            int k = c1 / 2 - 35;
            chatTextDrawingArea.drawShadowTextHMidVTop(0xffffff, c / 2, "To create a new account you need to", k, true);
            k += 15;
            chatTextDrawingArea.drawShadowTextHMidVTop(0xffffff, c / 2, "go back to the main RuneScape webpage", k, true);
            k += 15;
            chatTextDrawingArea.drawShadowTextHMidVTop(0xffffff, c / 2, "and choose the red 'create account'", k, true);
            k += 15;
            chatTextDrawingArea.drawShadowTextHMidVTop(0xffffff, c / 2, "button at the top right of that page.", k, true);
            k += 15;
            int j1 = c / 2;
            int i2 = c1 / 2 + 50;
            aBackground_967.method361(j1 - 73, i2 - 20);
            chatTextDrawingArea.drawShadowTextHMidVTop(0xffffff, j1, "Cancel", i2 + 5, true);
        }
        aRSImageProducer_1109.drawGraphics(171, super.gameGraphics, 202);
        if(welcomeScreenRaised)
        {
            welcomeScreenRaised = false;
            aRSImageProducer_1107.drawGraphics(0, super.gameGraphics, 128);
            aRSImageProducer_1108.drawGraphics(371, super.gameGraphics, 202);
            aRSImageProducer_1112.drawGraphics(265, super.gameGraphics, 0);
            aRSImageProducer_1113.drawGraphics(265, super.gameGraphics, 562);
            aRSImageProducer_1114.drawGraphics(171, super.gameGraphics, 128);
            aRSImageProducer_1115.drawGraphics(171, super.gameGraphics, 562);
        }
    }

    private void drawFlames()
    {
        drawingFlames = true;
        try
        {
            long l = System.currentTimeMillis();
            int i = 0;
            int j = 20;
            while(currentlyDrawingFlames) 
            {
                anInt1208++;
                calcFlamesPosition();
                calcFlamesPosition();
                doFlamesDrawing();
                if(++i > 10)
                {
                    long l1 = System.currentTimeMillis();
                    int k = (int)(l1 - l) / 10 - j;
                    j = 40 - k;
                    if(j < 5)
                        j = 5;
                    i = 0;
                    l = l1;
                }
                try
                {
                    Thread.sleep(j);
                }
                catch(Exception _ex) { }
            }
        }
        catch(Exception _ex) { }
        drawingFlames = false;
    }

    public void redraw()
    {
        welcomeScreenRaised = true;
    }

    private void method137(Stream stream, int j)
    {
        if(j == 84)
        {
            int positionOffset = stream.getUnsignedByte();
            int x = playerPositionX + (positionOffset >> 4 & 7);
            int y = playerPositionY + (positionOffset & 7);
            int targetItemId = stream.getUnsignedLEShort();
            int targetItemAmount = stream.getUnsignedLEShort();
            int itemCount = stream.getUnsignedLEShort();
            if(x >= 0 && y >= 0 && x < 104 && y < 104)
            {
                NodeList groundItemArray = groundArray[plane][x][y];
                if(groundItemArray != null)
                {
                    for(Item item = (Item)groundItemArray.reverseGetFirst(); item != null; item = (Item)groundItemArray.reverseGetNext())
                    {
                        if(item.itemId != (targetItemId & 0x7fff) || item.itemCount != targetItemAmount)
                            continue;
                        item.itemCount = itemCount;
                        break;
                    }

                    spawnGroundItem(x, y);
                }
            }
            return;
        }
        if(j == 105)
        {
            int positionOffset = stream.getUnsignedByte();
            int x = playerPositionX + (positionOffset >> 4 & 7);
            int y = playerPositionY + (positionOffset & 7);
            int trackId = stream.getUnsignedLEShort();
            int data = stream.getUnsignedByte();
            int boundarySize = data >> 4 & 0xf;
            int loop = data & 7;
            if(localPlayer.smallX[0] >= x - boundarySize && localPlayer.smallX[0] <= x + boundarySize && localPlayer.smallY[0] >= y - boundarySize && localPlayer.smallY[0] <= y + boundarySize && aBoolean848 && !lowMemory && trackCount < 50)
            {
                trackIds[trackCount] = trackId;
                trackLoop[trackCount] = loop;
                trackDelay[trackCount] = Sounds.trackDelays[trackId];
                trackCount++;
            }
        }
        if(j == 215)
        {
            int id = stream.getUnsignedLEShortA();
            int positionOffset = stream.getUnsignedByteS();
            int x = playerPositionX + (positionOffset >> 4 & 7);
            int y = playerPositionY + (positionOffset & 7);
            int playerId = stream.getUnsignedLEShortA();
            int count = stream.getUnsignedLEShort();
            if(x >= 0 && y >= 0 && x < 104 && y < 104 && playerId != playerListId)
            {
                Item item = new Item();
                item.itemId = id;
                item.itemCount = count;
                if(groundArray[plane][x][y] == null)
                    groundArray[plane][x][y] = new NodeList();
                groundArray[plane][x][y].insertHead(item);
                spawnGroundItem(x, y);
            }
            return;
        }
        if(j == 156)
        {
            int positionOffset = stream.getUnsignedByteA();
            int x = playerPositionX + (positionOffset >> 4 & 7);
            int y = playerPositionY + (positionOffset & 7);
            int itemId = stream.getUnsignedLEShort();
            if(x >= 0 && y >= 0 && x < 104 && y < 104)
            {
                NodeList groundItems = groundArray[plane][x][y];
                if(groundItems != null)
                {
                    for(Item item = (Item)groundItems.reverseGetFirst(); item != null; item = (Item)groundItems.reverseGetNext())
                    {
                        if(item.itemId != (itemId & 0x7fff))
                            continue;
                        item.unlink();
                        break;
                    }

                    if(groundItems.reverseGetFirst() == null)
                        groundArray[plane][x][y] = null;
                    spawnGroundItem(x, y);
                }
            }
            return;
        }
        if(j == 160) // Spawn a 4-square object?
        {
            int positionOffset = stream.getUnsignedByteS();
            int x = playerPositionX + (positionOffset >> 4 & 7);
            int y = playerPositionY + (positionOffset & 7);
            int objectData = stream.getUnsignedByteS();
            int objectType = objectData >> 2;
            int orientation = objectData & 3;
            int type = objectTypes[objectType];
            int animationId = stream.getUnsignedLEShortA();
            if(x >= 0 && y >= 0 && x < 103 && y < 103)
            {
                int tileHeightX0Y0 = intGroundArray[plane][x][y];
                int tileHeightX1Y0 = intGroundArray[plane][x + 1][y];
                int tileHeightX1Y1 = intGroundArray[plane][x + 1][y + 1];
                int tileHeightX0Y1 = intGroundArray[plane][x][y + 1];
                if(type == 0)
                {
                    WallObject wallObject = worldController.getWallObject(x, y, plane);
                    if(wallObject != null)
                    {
                        int uid = wallObject.uid >> 14 & 0x7fff;
                        if(objectType == 2)
                        {
                            wallObject.renderable = new ObjectInstance(uid, 4 + orientation, 2, tileHeightX1Y0, tileHeightX1Y1, tileHeightX0Y0, tileHeightX0Y1, animationId, false);
                            wallObject.renderable2 = new ObjectInstance(uid, orientation + 1 & 3, 2, tileHeightX1Y0, tileHeightX1Y1, tileHeightX0Y0, tileHeightX0Y1, animationId, false);
                        } else
                        {
                            wallObject.renderable = new ObjectInstance(uid, orientation, objectType, tileHeightX1Y0, tileHeightX1Y1, tileHeightX0Y0, tileHeightX0Y1, animationId, false);
                        }
                    }
                }
                if(type == 1)
                {
                    WallDecoration wallDecoration = worldController.getWallDecoration(x, y, plane);
                    if(wallDecoration != null)
                        wallDecoration.renderable = new ObjectInstance(wallDecoration.uid >> 14 & 0x7fff, 0, 4, tileHeightX1Y0, tileHeightX1Y1, tileHeightX0Y0, tileHeightX0Y1, animationId, false);
                }
                if(type == 2)
                {
                    InteractiveObject interactiveObject = worldController.getInteractiveObject(x, y, plane);
                    if(objectType == 11)
                        objectType = 10;
                    if(interactiveObject != null)
                        interactiveObject.renderable = new ObjectInstance(interactiveObject.uid >> 14 & 0x7fff, orientation, objectType, tileHeightX1Y0, tileHeightX1Y1, tileHeightX0Y0, tileHeightX0Y1, animationId, false);
                }
                if(type == 3)
                {
                    GroundDecoration groundDecoration = worldController.getGroundDecoration(x, y, plane);
                    if(groundDecoration != null)
                        groundDecoration.renderable = new ObjectInstance(groundDecoration.uid >> 14 & 0x7fff, orientation, 22, tileHeightX1Y0, tileHeightX1Y1, tileHeightX0Y0, tileHeightX0Y1, animationId, false);
                }
            }
            return;
        }
        if(j == 147)
        {
            int positionOffset = stream.getUnsignedByteS();
            int x = playerPositionX + (positionOffset >> 4 & 7);
            int y = playerPositionY + (positionOffset & 7);
            int targetPlayer = stream.getUnsignedLEShort();
            byte byte0 = stream.getByteS();
            int l14 = stream.getUnsignedShort();
            byte byte1 = stream.getByteC();
            int k17 = stream.getUnsignedLEShort();
            int objectData = stream.getUnsignedByteS();
            int objectType = objectData >> 2;
            int objectOrientation = objectData & 3;
            int type = objectTypes[objectType];
            byte byte2 = stream.get();
            int objectId = stream.getUnsignedLEShort();
            byte byte3 = stream.getByteC();
            Player player;
            if(targetPlayer == playerListId)
                player = localPlayer;
            else
                player = playerArray[targetPlayer];
            if(player != null)
            {
                ObjectDef object = ObjectDef.forID(objectId);
                int tileHeightX0Y0 = intGroundArray[plane][x][y];
                int tileHeightX1Y0 = intGroundArray[plane][x + 1][y];
                int tileHeightX1Y1 = intGroundArray[plane][x + 1][y + 1];
                int tileHeightX0Y1 = intGroundArray[plane][x][y + 1];
                Model model = object.getModelAt(objectType, objectOrientation, tileHeightX0Y0, tileHeightX1Y0, tileHeightX1Y1, tileHeightX0Y1, -1);
                if(model != null)
                {
                    createObjectSpawnRequest(k17 + 1, -1, 0, type, y, 0, plane, x, l14 + 1);
                    player.anInt1707 = l14 + loopCycle;
                    player.anInt1708 = k17 + loopCycle;
                    player.playerModel = model;
                    int sizeX = object.sizeX;
                    int sizeY = object.sizeY;
                    if(objectOrientation == 1 || objectOrientation == 3)
                    {
                        sizeX = object.sizeY;
                        sizeY = object.sizeX;
                    }
                    player.anInt1711 = x * 128 + sizeX * 64;
                    player.anInt1713 = y * 128 + sizeY * 64;
                    player.drawHeight = getFloorDrawHeight(plane, player.anInt1713, player.anInt1711);
                    if(byte2 > byte0)
                    {
                        byte byte4 = byte2;
                        byte2 = byte0;
                        byte0 = byte4;
                    }
                    if(byte3 > byte1)
                    {
                        byte byte5 = byte3;
                        byte3 = byte1;
                        byte1 = byte5;
                    }
                    player.anInt1719 = x + byte2;
                    player.anInt1721 = x + byte0;
                    player.anInt1720 = y + byte3;
                    player.anInt1722 = y + byte1;
                }
            }
        }
        if(j == 151)
        {
            int positionOffset = stream.getUnsignedByteA();
            int x = playerPositionX + (positionOffset >> 4 & 7);
            int y = playerPositionY + (positionOffset & 7);
            int objectId = stream.getUnsignedShort();
            int data = stream.getUnsignedByteS();
            int objectType = data >> 2;
            int orientation = data & 3;
            int type = objectTypes[objectType];
            if(x >= 0 && y >= 0 && x < 104 && y < 104)
                createObjectSpawnRequest(-1, objectId, orientation, type, y, objectType, plane, x, 0);
            return;
        }
        if(j == 4)
        {
            int positionOffset = stream.getUnsignedByte();
            int x = playerPositionX + (positionOffset >> 4 & 7);
            int y = playerPositionY + (positionOffset & 7);
            int graphicId = stream.getUnsignedLEShort();
            int drawHeight = stream.getUnsignedByte();
            int delay = stream.getUnsignedLEShort();
            if(x >= 0 && y >= 0 && x < 104 && y < 104)
            {
                x = x * 128 + 64;
                y = y * 128 + 64;
                StationaryGraphic stationaryGraphic = new StationaryGraphic(x, y, plane, getFloorDrawHeight(plane, y, x) - drawHeight, graphicId, loopCycle, delay);
                stationaryGraphicQueue.insertHead(stationaryGraphic);
            }
            return;
        }
        if(j == 44)
        {
            int itemId = stream.getUnsignedShortA();
            int itemAmount = stream.getUnsignedLEShort();
            int positionOffset = stream.getUnsignedByte();
            int x = playerPositionX + (positionOffset >> 4 & 7);
            int y = playerPositionY + (positionOffset & 7);
            if(x >= 0 && y >= 0 && x < 104 && y < 104)
            {
                Item item = new Item();
                item.itemId = itemId;
                item.itemCount = itemAmount;
                if(groundArray[plane][x][y] == null)
                    groundArray[plane][x][y] = new NodeList();
                groundArray[plane][x][y].insertHead(item);
                spawnGroundItem(x, y);
            }
            return;
        }
        if(j == 101)
        {
            int objectData = stream.getUnsignedByteC();
            int objectType = objectData >> 2;
            int face = objectData & 3;
            int type = objectTypes[objectType];
            int positionOffset = stream.getUnsignedByte();
            int x = playerPositionX + (positionOffset >> 4 & 7);
            int y = playerPositionY + (positionOffset & 7);
            if(x >= 0 && y >= 0 && x < 104 && y < 104)
                createObjectSpawnRequest(-1, -1, face, type, y, objectType, plane, x, 0);
            return;
        }
        if(j == 117)
        {
            int projectileAngle = stream.getUnsignedByte();
            int projectileX = playerPositionX + (projectileAngle >> 4 & 7);
            int projectileY = playerPositionY + (projectileAngle & 7);
            int projectileOffsetX = projectileX + stream.get();
            int projectileOffsetY = projectileY + stream.get();
            int projectileTarget = stream.getShort();
            int projectileGraphicId = stream.getUnsignedLEShort();
            int projectileHeightStart = stream.getUnsignedByte() * 4;
            int projectileHeightEnd = stream.getUnsignedByte() * 4;
            int projectileCreatedTime = stream.getUnsignedLEShort();
            int projectileSpeed = stream.getUnsignedLEShort();
            int projectileInitialSlope = stream.getUnsignedByte();
            int projectileDistanceFromSource = stream.getUnsignedByte();
            if(projectileX >= 0 && projectileY >= 0 && projectileX < 104 && projectileY < 104 && projectileOffsetX >= 0 && projectileOffsetY >= 0 && projectileOffsetX < 104 && projectileOffsetY < 104 && projectileGraphicId != 65535)
            {
                projectileX = projectileX * 128 + 64;
                projectileY = projectileY * 128 + 64;
                projectileOffsetX = projectileOffsetX * 128 + 64;
                projectileOffsetY = projectileOffsetY * 128 + 64;
                Projectile projectile = new Projectile(projectileInitialSlope, projectileHeightEnd, projectileCreatedTime + loopCycle, projectileSpeed + loopCycle, projectileDistanceFromSource, plane, getFloorDrawHeight(plane, projectileY, projectileX) - projectileHeightStart, projectileY, projectileX, projectileTarget, projectileGraphicId);
                projectile.trackTarget(projectileCreatedTime + loopCycle, projectileOffsetY, getFloorDrawHeight(plane, projectileOffsetY, projectileOffsetX) - projectileHeightEnd, projectileOffsetX);
                projectileQueue.insertHead(projectile);
            }
        }
    }

    private static void setLowMem()
    {
        WorldController.lowMemory = true;
        Texture.lowMem = true;
        lowMemory = true;
        ObjectManager.lowMem = true;
        ObjectDef.lowMem = true;
    }

    private void updateNPCMovement(Stream stream)
    {
        stream.initBitAccess();
        int npcsToUpdate = stream.readBits(8);
        if(npcsToUpdate < npcCount)
        {
            for(int n = npcsToUpdate; n < npcCount; n++)
                anIntArray840[anInt839++] = npcIndices[n];

        }
        if(npcsToUpdate > npcCount)
        {
            signlink.reporterror(myUsername + " Too many npcs");
            throw new RuntimeException("eek");
        }
        npcCount = 0;
        for(int n = 0; n < npcsToUpdate; n++)
        {
            int npcId = npcIndices[n];
            NPC npc = npcArray[npcId];
            int updateRequired = stream.readBits(1);
            if(updateRequired == 0)
            {
                npcIndices[npcCount++] = npcId;
                npc.lastUpdateTime = loopCycle;
            } else
            {
                int movementUpdateType = stream.readBits(2);
                if(movementUpdateType == 0)
                {
                    npcIndices[npcCount++] = npcId;
                    npc.lastUpdateTime = loopCycle;
                    playersAwaitingUpdate[actorsAwaitingUpdatePointer++] = npcId;
                } else
                if(movementUpdateType == 1)
                {
                    npcIndices[npcCount++] = npcId;
                    npc.lastUpdateTime = loopCycle;
                    int direction = stream.readBits(3);
                    npc.move(false, direction);
                    int furtherUpdateRequired = stream.readBits(1);
                    if(furtherUpdateRequired == 1)
                        playersAwaitingUpdate[actorsAwaitingUpdatePointer++] = npcId;
                } else
                if(movementUpdateType == 2)
                {
                    npcIndices[npcCount++] = npcId;
                    npc.lastUpdateTime = loopCycle;
                    int lastDirection = stream.readBits(3);
                    npc.move(true, lastDirection);
                    int currentDirection = stream.readBits(3);
                    npc.move(true, currentDirection);
                    int furtherUpdateRequired = stream.readBits(1);
                    if(furtherUpdateRequired == 1)
                        playersAwaitingUpdate[actorsAwaitingUpdatePointer++] = npcId;
                } else
                if(movementUpdateType == 3)
                    anIntArray840[anInt839++] = npcId;
            }
        }
    }

    private void processLoginScreenInput()
    {
        if(loginScreenState == 0)
        {
            int i = super.width / 2 - 80;
            int l = super.height / 2 + 20;
            l += 20;
            if(super.clickType == 1 && super.clickX >= i - 75 && super.clickX <= i + 75 && super.clickY >= l - 20 && super.clickY <= l + 20)
            {
                loginScreenState = 3;
                loginScreenCursorPos = 0;
            }
            i = super.width / 2 + 80;
            if(super.clickType == 1 && super.clickX >= i - 75 && super.clickX <= i + 75 && super.clickY >= l - 20 && super.clickY <= l + 20)
            {
                loginMessage1 = "";
                loginMessage2 = "Enter your username & password.";
                loginScreenState = 2;
                loginScreenCursorPos = 0;
            }
        } else
        {
            if(loginScreenState == 2)
            {
                int j = super.height / 2 - 40;
                j += 30;
                j += 25;
                if(super.clickType == 1 && super.clickY >= j - 15 && super.clickY < j)
                    loginScreenCursorPos = 0;
                j += 15;
                if(super.clickType == 1 && super.clickY >= j - 15 && super.clickY < j)
                    loginScreenCursorPos = 1;
                j += 15;
                int i1 = super.width / 2 - 80;
                int k1 = super.height / 2 + 50;
                k1 += 20;
                if(super.clickType == 1 && super.clickX >= i1 - 75 && super.clickX <= i1 + 75 && super.clickY >= k1 - 20 && super.clickY <= k1 + 20)
                {
                    loginFailures = 0;
                    login(myUsername, myPassword, false);
                    if(loggedIn)
                        return;
                }
                i1 = super.width / 2 + 80;
                if(super.clickType == 1 && super.clickX >= i1 - 75 && super.clickX <= i1 + 75 && super.clickY >= k1 - 20 && super.clickY <= k1 + 20)
                {
                    loginScreenState = 0;
 //                   myUsername = "";
 //                   myPassword = "";
                }
                do
                {
                    int l1 = readCharacter();
                    if(l1 == -1)
                        break;
                    boolean flag1 = false;
                    for(int i2 = 0; i2 < validUserPassChars.length(); i2++)
                    {
                        if(l1 != validUserPassChars.charAt(i2))
                            continue;
                        flag1 = true;
                        break;
                    }

                    if(loginScreenCursorPos == 0)
                    {
                        if(l1 == 8 && myUsername.length() > 0)
                            myUsername = myUsername.substring(0, myUsername.length() - 1);
                        if(l1 == 9 || l1 == 10 || l1 == 13)
                            loginScreenCursorPos = 1;
                        if(flag1)
                            myUsername += (char)l1;
                        if(myUsername.length() > 12)
                            myUsername = myUsername.substring(0, 12);
                    } else
                    if(loginScreenCursorPos == 1)
                    {
                        if(l1 == 8 && myPassword.length() > 0)
                            myPassword = myPassword.substring(0, myPassword.length() - 1);
                        if(l1 == 9 || l1 == 10 || l1 == 13)
                            loginScreenCursorPos = 0;
                        if(flag1)
                            myPassword += (char)l1;
                        if(myPassword.length() > 20)
                            myPassword = myPassword.substring(0, 20);
                    }
                } while(true);
                return;
            }
            if(loginScreenState == 3)
            {
                int k = super.width / 2;
                int j1 = super.height / 2 + 50;
                j1 += 20;
                if(super.clickType == 1 && super.clickX >= k - 75 && super.clickX <= k + 75 && super.clickY >= j1 - 20 && super.clickY <= j1 + 20)
                    loginScreenState = 0;
            }
        }
    }

    private void markMinimap(Sprite sprite, int i, int j)
    {
        int k = minimapInt1 + minimapRotation & 0x7ff;
        int l = i * i + j * j;
        if(l > 6400)
            return;
        int i1 = Model.SINE[k];
        int j1 = Model.COSINE[k];
        i1 = (i1 * 256) / (minimapZoom + 256);
        j1 = (j1 * 256) / (minimapZoom + 256);
        int k1 = j * i1 + i * j1 >> 16;
        int l1 = j * j1 - i * i1 >> 16;
        if(l > 2500)
        {
            sprite.method354(mapBack, 83 - l1 - sprite.anInt1445 / 2 - 4, ((94 + k1) - sprite.anInt1444 / 2) + 4);
        } else
        {
            sprite.drawSprite(((94 + k1) - sprite.anInt1444 / 2) + 4, 83 - l1 - sprite.anInt1445 / 2 - 4);
        }
    }

    private void method142(int y, int z, int k, int l, int x, int objectType, int k1
    )
    {
        if(x >= 1 && y >= 1 && x <= 102 && y <= 102)
        {
            if(lowMemory && z != plane)
                return;
            int uid = 0;
            if(objectType == 0)
                uid = worldController.getWallObjectUID(x, y, z);
            if(objectType == 1)
                uid = worldController.getWallDecorationUID(x, y, z);
            if(objectType == 2)
                uid = worldController.getInteractibleObjectUID(x, y, z);
            if(objectType == 3)
                uid = worldController.getGroundDecorationUID(x, y, z);
            if(uid != 0)
            {
                int config = worldController.getConfig(uid, x, y, z);
                int objectId = uid >> 14 & 0x7fff;
                int position = config & 0x1f;
                int orientation = config >> 6;
                if(objectType == 0)
                {
                    worldController.removeWallObject(x, z, y);
                    ObjectDef object = ObjectDef.forID(objectId);
                    if(object.unwalkable)
                        currentCollisionMap[z].unmarkWall(x, y, position, orientation, object.walkable);
                }
                if(objectType == 1)
                    worldController.removeWallDecoration(x, y, z);
                if(objectType == 2)
                {
                    worldController.removeInteractiveObject(x, y, z);
                    ObjectDef object = ObjectDef.forID(objectId);
                    if(x + object.sizeX > 103 || y + object.sizeX > 103 || x + object.sizeY > 103 || y + object.sizeY > 103)
                        return;
                    if(object.unwalkable)
                        currentCollisionMap[z].unmarkSolidOccupant(x, y, object.sizeX, object.sizeY, orientation, object.walkable);
                }
                if(objectType == 3)
                {
                    worldController.removeGroundDecoration(x, y, z);
                    ObjectDef object = ObjectDef.forID(objectId);
                    if(object.unwalkable && object.hasActions)
                        currentCollisionMap[z].unmarkConcealed(x, y);
                }
            }
            if(k1 >= 0)
            {
                int height = z;
                if(height < 3 && (byteGroundArray[1][x][y] & 2) == 2)
                    height++;
                ObjectManager.method188(worldController, k, y, l, height, currentCollisionMap[z], intGroundArray, x, k1, z);
            }
        }
    }

    private void updatePlayers(int i, Stream stream)
    {
        anInt839 = 0;
        actorsAwaitingUpdatePointer = 0;
        updateLocalPlayerMovement(stream);
        updateOtherPlayerMovement(stream);
        updatePlayerList(stream, i);
        updatePlayersBlock(stream);
        for(int k = 0; k < anInt839; k++)
        {
            int l = anIntArray840[k];
            if(playerArray[l].lastUpdateTime != loopCycle)
                playerArray[l] = null;
        }

        if(stream.currentOffset != i)
        {
            signlink.reporterror("Error packet size mismatch in getplayer pos:" + stream.currentOffset + " psize:" + i);
            throw new RuntimeException("eek");
        }
        for(int i1 = 0; i1 < playerCount; i1++)
            if(playerArray[playerId[i1]] == null)
            {
                signlink.reporterror(myUsername + " null entry in pl list - pos:" + i1 + " size:" + playerCount);
                throw new RuntimeException("eek");
            }

    }

    private void setCameraPos(int j, int k, int l, int i1, int j1, int k1)
    {
        int l1 = 2048 - k & 0x7ff;
        int i2 = 2048 - j1 & 0x7ff;
        int j2 = 0;
        int k2 = 0;
        int l2 = j;
        if(l1 != 0)
        {
            int i3 = Model.SINE[l1];
            int k3 = Model.COSINE[l1];
            int i4 = k2 * k3 - l2 * i3 >> 16;
            l2 = k2 * i3 + l2 * k3 >> 16;
            k2 = i4;
        }
        if(i2 != 0)
        {
/* xxx            if(cameratoggle){
            	if(zoom == 0)
                zoom = k2;
              if(lftrit == 0)
                lftrit = j2;
              if(fwdbwd == 0)
                fwdbwd = l2;
              k2 = zoom;
              j2 = lftrit;
              l2 = fwdbwd;
            }
*/
            int j3 = Model.SINE[i2];
            int l3 = Model.COSINE[i2];
            int j4 = l2 * j3 + j2 * l3 >> 16;
            l2 = l2 * l3 - j2 * j3 >> 16;
            j2 = j4;
        }
        cameraPositionX = l - j2;
        cameraPositionZ = i1 - k2;
        cameraPositionY = k1 - l2;
        cameraCurveY = k;
        cameraCurveZ = j1;
    }

    private boolean parsePacket()
    {
        if(socket == null)
            return false;
        try
        {
            int i = socket.available();
            if(i == 0)
                return false;
            if(packetOpcode == -1)
            {
                socket.read(inStream.buffer, 1);
                packetOpcode = inStream.buffer[0] & 0xff;
                if(encryption != null)
                    packetOpcode = packetOpcode - encryption.getNextKey() & 0xff;
                packetSize = SizeConstants.packetSizes[packetOpcode];
                i--;
            }
            if(packetSize == -1)
                if(i > 0)
                {
                    socket.read(inStream.buffer, 1);
                    packetSize = inStream.buffer[0] & 0xff;
                    i--;
                } else
                {
                    return false;
                }
            if(packetSize == -2)
                if(i > 1)
                {
                    socket.read(inStream.buffer, 2);
                    inStream.currentOffset = 0;
                    packetSize = inStream.getUnsignedLEShort();
                    i -= 2;
                } else
                {
                    return false;
                }
            if(i < packetSize)
                return false;
            inStream.currentOffset = 0;
            socket.read(inStream.buffer, packetSize);
            anInt1009 = 0;
            anInt843 = anInt842;
            anInt842 = anInt841;
            anInt841 = packetOpcode;
            if(packetOpcode == 81)
            {
                updatePlayers(packetSize, inStream);
                loadingMap = false;
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 176)
            {
                daysSinceRecoveryChange = inStream.getUnsignedByteC();
                unreadMessages = inStream.getUnsignedLEShortA();
                membership = inStream.getUnsignedByte();
                lastAddress = inStream.getInt1();
                daysSinceLogin = inStream.getUnsignedLEShort();
                if(lastAddress != 0 && openInterfaceId == -1)
                {
                    signlink.dnslookup(TextClass.decodeDNS(lastAddress));
                    clearTopInterfaces();
                    int contentType = 650;
                    if(daysSinceRecoveryChange != 201 || membership == 1)
                        contentType = 655;
                    reportAbuseInput = "";
                    canMute = false;
                    for(int interfaceId = 0; interfaceId < RSInterface.interfaceCache.length; interfaceId++)
                    {
                        if(RSInterface.interfaceCache[interfaceId] == null || RSInterface.interfaceCache[interfaceId].contentType != contentType)
                            continue;
                        openInterfaceId = RSInterface.interfaceCache[interfaceId].parentID;
                        break;
                    }

                }
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 64)
            {
                playerPositionX = inStream.getUnsignedByteC();
                playerPositionY = inStream.getUnsignedByteS();
                for(int x = playerPositionX; x < playerPositionX + 8; x++)
                {
                    for(int y = playerPositionY; y < playerPositionY + 8; y++)
                        if(groundArray[plane][x][y] != null)
                        {
                            groundArray[plane][x][y] = null;
                            spawnGroundItem(x, y);
                        }

                }

                for(GameObjectSpawnRequest spawnRequest = (GameObjectSpawnRequest)spawnObjectList.reverseGetFirst(); spawnRequest != null; spawnRequest = (GameObjectSpawnRequest)spawnObjectList.reverseGetNext())
                    if(spawnRequest.x >= playerPositionX && spawnRequest.x < playerPositionX + 8 && spawnRequest.y >= playerPositionY && spawnRequest.y < playerPositionY + 8 && spawnRequest.z == plane)
                        spawnRequest.delayUntilRespawn = 0;

                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 185)
            {
                int interfaceId = inStream.getUnsignedShortA();
                RSInterface.interfaceCache[interfaceId].modelType = 3;
                if(localPlayer.npcAppearance == null)
                    RSInterface.interfaceCache[interfaceId].modelId = (localPlayer.bodyPartColour[0] << 25) + (localPlayer.bodyPartColour[4] << 20) + (localPlayer.appearance[0] << 15) + (localPlayer.appearance[8] << 10) + (localPlayer.appearance[11] << 5) + localPlayer.appearance[1];
                else
                    RSInterface.interfaceCache[interfaceId].modelId = (int)(0x12345678L + localPlayer.npcAppearance.type);
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 107)
            {
                inCutscene = false;
                for(int l = 0; l < 5; l++)
                    aBooleanArray876[l] = false;

                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 72)
            {
                int interfaceId = inStream.getUnsignedShort();
                RSInterface rsInterface = RSInterface.interfaceCache[interfaceId];
                for(int slot = 0; slot < rsInterface.inventoryItemId.length; slot++)
                {
                    rsInterface.inventoryItemId[slot] = -1;
                    rsInterface.inventoryItemId[slot] = 0;
                }

                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 214)
            {
                ignoreCount = packetSize / 8;
                for(int p = 0; p < ignoreCount; p++)
                    ignoreListAsLongs[p] = inStream.getLong();

                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 166)
            {
                inCutscene = true;
                anInt1098 = inStream.getUnsignedByte();
                anInt1099 = inStream.getUnsignedByte();
                anInt1100 = inStream.getUnsignedLEShort();
                anInt1101 = inStream.getUnsignedByte();
                anInt1102 = inStream.getUnsignedByte();
                if(anInt1102 >= 100)
                {
                    cameraPositionX = anInt1098 * 128 + 64;
                    cameraPositionY = anInt1099 * 128 + 64;
                    cameraPositionZ = getFloorDrawHeight(plane, cameraPositionY, cameraPositionX) - anInt1100;
                }
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 134)
            {
                needDrawTabArea = true;
                int skillId = inStream.getUnsignedByte();
                int skillExperience = inStream.getInt2();
                int skillLevel = inStream.getUnsignedByte();
                currentExp[skillId] = skillExperience;
                currentStats[skillId] = skillLevel;
                maxStats[skillId] = 1;
                for(int level = 0; level < 98; level++)
                    if(skillExperience >= xpForLevel[level])
                        maxStats[skillId] = level + 2;

                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 71)
            {
                int sidebarId = inStream.getUnsignedLEShort();
                int interfaceId = inStream.getUnsignedByteA();
                if(sidebarId == 65535)
                    sidebarId = -1;
                tabInterfaceIDs[interfaceId] = sidebarId;
                needDrawTabArea = true;
                tabAreaAltered = true;
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 74)
            {
                int songId = inStream.getUnsignedShort();
                if(songId == 65535)
                    songId = -1;
                if(songId != currentSong && musicEnabled && !lowMemory && prevSong == 0)
                {
                    nextSong = songId;
                    songChanging = true;
                    onDemandFetcher.request(2, nextSong);
                }
                currentSong = songId;
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 121)
            {
                int nextSong = inStream.getUnsignedShortA();
                int previousSong = inStream.getUnsignedLEShortA();
                if(musicEnabled && !lowMemory)
                {
                    this.nextSong = nextSong;
                    songChanging = false;
                    onDemandFetcher.request(2, this.nextSong);
                    this.prevSong = previousSong;
                }
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 109)
            {
                logout();
                packetOpcode = -1;
                return false;
            }
            if(packetOpcode == 70)
            {
                int x = inStream.getShort();
                int y = inStream.getForceLEShort();
                int interfaceId = inStream.getUnsignedShort();
                RSInterface rsInterface = RSInterface.interfaceCache[interfaceId];
                rsInterface.x = x;
                rsInterface.y = y;
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 73 || packetOpcode == 241)
            {
                
	//mapReset();
            	int playerRegionX = regionX;
                int playerRegionY = regionY;
                if(packetOpcode == 73)
                {
                    playerRegionX = inStream.getUnsignedLEShortA();
                    playerRegionY = inStream.getUnsignedLEShort();
                    
                    System.out.println(playerRegionX + " " + playerRegionY);
                    loadGeneratedMap = false;
                }
                if(packetOpcode == 241)
                {
                    playerRegionY = inStream.getUnsignedLEShortA();
                    inStream.initBitAccess();
                    for(int z = 0; z < 4; z++)
                    {
                        for(int x = 0; x < 13; x++)
                        {
                            for(int y = 0; y < 13; y++)
                            {
                                int tileExists = inStream.readBits(1);
                                if(tileExists == 1)
                                    constructMapTiles[z][x][y] = inStream.readBits(26);
                                else
                                    constructMapTiles[z][x][y] = -1;
                            }

                        }

                    }

                    inStream.finishBitAccess();
                    playerRegionX = inStream.getUnsignedLEShort();
                    loadGeneratedMap = true;
                }
                if(regionX == playerRegionX && regionY == playerRegionY && loadingStage == 2)
                {
                    packetOpcode = -1;
                    return true;
                }
                regionX = playerRegionX;
                regionY = playerRegionY;
                baseX = (regionX - 6) * 8;
                baseY = (regionY - 6) * 8;
                tutorialIsland = (regionX / 8 == 48 || regionX / 8 == 49) && regionY / 8 == 48;
                if(regionX / 8 == 48 && regionY / 8 == 148)
                    tutorialIsland = true;
                loadingStage = 1;
                aLong824 = System.currentTimeMillis();
                gameScreenCanvas.initDrawingArea();
                plainFont.drawTextHMidVTop("Loading - please wait.", 257, 151, 0);
                plainFont.drawTextHMidVTop("Loading - please wait.", 256, 150, 0xffffff);
                gameScreenCanvas.drawGraphics(4, super.gameGraphics, 4);
                if(packetOpcode == 73)
                {
                    int k16 = 0;
                    for(int i21 = (regionX - 6) / 8; i21 <= (regionX + 6) / 8; i21++)
                    {
                        for(int k23 = (regionY - 6) / 8; k23 <= (regionY + 6) / 8; k23++)
                            k16++;

                    }

                    terrainData = new byte[k16][];
                    objectData = new byte[k16][];
                    mapCoordinates = new int[k16];
                    anIntArray1235 = new int[k16];
                    anIntArray1236 = new int[k16];
                    k16 = 0;
                    for(int l23 = (regionX - 6) / 8; l23 <= (regionX + 6) / 8; l23++)
                    {
                        for(int j26 = (regionY - 6) / 8; j26 <= (regionY + 6) / 8; j26++)
                        {
                            mapCoordinates[k16] = (l23 << 8) + j26;
                            if(tutorialIsland && (j26 == 49 || j26 == 149 || j26 == 147 || l23 == 50 || l23 == 49 && j26 == 47))
                            {
                                anIntArray1235[k16] = -1;
                                anIntArray1236[k16] = -1;
                                k16++;
                            } else
                            {
                                int k28 = anIntArray1235[k16] = onDemandFetcher.getMapIndex(0, j26, l23);
                                if(k28 != -1)
                                    onDemandFetcher.request(3, k28);
                                int j30 = anIntArray1236[k16] = onDemandFetcher.getMapIndex(1, j26, l23);
                                if(j30 != -1)
                                    onDemandFetcher.request(3, j30);
                                k16++;
                            }
                        }

                    }

                }
                if(packetOpcode == 241)
                {
                    int l16 = 0;
                    int ai[] = new int[676];
                    for(int i24 = 0; i24 < 4; i24++)
                    {
                        for(int k26 = 0; k26 < 13; k26++)
                        {
                            for(int l28 = 0; l28 < 13; l28++)
                            {
                                int k30 = constructMapTiles[i24][k26][l28];
                                if(k30 != -1)
                                {
                                    int k31 = k30 >> 14 & 0x3ff;
                                    int i32 = k30 >> 3 & 0x7ff;
                                    int k32 = (k31 / 8 << 8) + i32 / 8;
                                    for(int j33 = 0; j33 < l16; j33++)
                                    {
                                        if(ai[j33] != k32)
                                            continue;
                                        k32 = -1;
                                        break;
                                    }

                                    if(k32 != -1)
                                        ai[l16++] = k32;
                                }
                            }

                        }

                    }

                    terrainData = new byte[l16][];
                    objectData = new byte[l16][];
                    mapCoordinates = new int[l16];
                    anIntArray1235 = new int[l16];
                    anIntArray1236 = new int[l16];
                    for(int l26 = 0; l26 < l16; l26++)
                    {
                        int i29 = mapCoordinates[l26] = ai[l26];
                        int l30 = i29 >> 8 & 0xff;
                        int l31 = i29 & 0xff;
                        int j32 = anIntArray1235[l26] = onDemandFetcher.getMapIndex(0, l31, l30);
                        if(j32 != -1)
                            onDemandFetcher.request(3, j32);
                        int i33 = anIntArray1236[l26] = onDemandFetcher.getMapIndex(1, l31, l30);
                        if(i33 != -1)
                            onDemandFetcher.request(3, i33);
                    }

                }
                int _x = baseX - anInt1036;
                int _y = baseY - anInt1037;
                anInt1036 = baseX;
                anInt1037 = baseY;
                for(int n = 0; n < 16384; n++)
                {
                    NPC npc = npcArray[n];
                    if(npc != null)
                    {
                        for(int waypoint = 0; waypoint < 10; waypoint++)
                        {
                            npc.smallX[waypoint] -= _x;
                            npc.smallY[waypoint] -= _y;
                        }

                        npc.x -= _x * 128;
                        npc.y -= _y * 128;
                    }
                }

                for(int p = 0; p < maxPlayers; p++)
                {
                    Player player = playerArray[p];
                    if(player != null)
                    {
                        for(int waypoint = 0; waypoint < 10; waypoint++)
                        {
                            player.smallX[waypoint] -= _x;
                            player.smallY[waypoint] -= _y;
                        }

                        player.x -= _x * 128;
                        player.y -= _y * 128;
                    }
                }

                loadingMap = true;
                byte currentPositionX = 0;
                byte boundaryPositionX = 104;
                byte incrementX = 1;
                if(_x < 0)
                {
                    currentPositionX = 103;
                    boundaryPositionX = -1;
                    incrementX = -1;
                }
                byte currentPositionY = 0;
                byte boundaryPositionY = 104;
                byte incrementY = 1;
                if(_y < 0)
                {
                    currentPositionY = 103;
                    boundaryPositionY = -1;
                    incrementY = -1;
                }
                for(int x = currentPositionX; x != boundaryPositionX; x += incrementX)
                {
                    for(int y = currentPositionY; y != boundaryPositionY; y += incrementY)
                    {
                        int x2 = x + _x;
                        int y2 = y + _y;
                        for(int z = 0; z < 4; z++)
                            if(x2 >= 0 && y2 >= 0 && x2 < 104 && y2 < 104)
                                groundArray[z][x][y] = groundArray[z][x2][y2];
                            else
                                groundArray[z][x][y] = null;

                    }

                }

                for(GameObjectSpawnRequest spawnRequest = (GameObjectSpawnRequest)spawnObjectList.reverseGetFirst(); spawnRequest != null; spawnRequest = (GameObjectSpawnRequest)spawnObjectList.reverseGetNext())
                {
                    spawnRequest.x -= _x;
                    spawnRequest.y -= _y;
                    if(spawnRequest.x < 0 || spawnRequest.y < 0 || spawnRequest.x >= 104 || spawnRequest.y >= 104)
                        spawnRequest.unlink();
                }

                if(destX != 0)
                {
                    destX -= _x;
                    destY -= _y;
                }
                inCutscene = false;
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 208)
            {
                int interfaceId = inStream.getForceLEShort();
                if(interfaceId >= 0)
                    loadInterface(interfaceId);
                walkableInterfaceId = interfaceId;
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 99)
            {
                minimapState = inStream.getUnsignedByte();
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 75)
            {
                int modelId = inStream.getUnsignedShortA();
                int interfaceId = inStream.getUnsignedShortA();
                RSInterface.interfaceCache[interfaceId].modelType = 2;
                RSInterface.interfaceCache[interfaceId].modelId = modelId;
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 114)
            {
                systemUpdateTime = inStream.getUnsignedShort() * 30;
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 60)
            {
                playerPositionY = inStream.getUnsignedByte();
                playerPositionX = inStream.getUnsignedByteC();
                while(inStream.currentOffset < packetSize)
                {
                    int k3 = inStream.getUnsignedByte();
                    method137(inStream, k3);
                }
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 35)
            {
                int parameter = inStream.getUnsignedByte();
                int jitter = inStream.getUnsignedByte();
                int amplitude = inStream.getUnsignedByte();
                int frequency = inStream.getUnsignedByte();
                aBooleanArray876[parameter] = true;
                cameraJitter[parameter] = jitter;
                cameraAmplitude[parameter] = amplitude;
                cameraFrequency[parameter] = frequency;
                anIntArray1030[parameter] = 0;
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 174)
            {
                int trackId = inStream.getUnsignedLEShort();
                int loop = inStream.getUnsignedByte();
                int delay = inStream.getUnsignedLEShort();
                if(aBoolean848 && !lowMemory && trackCount < 50)
                {
                    trackIds[trackCount] = trackId;
                    trackLoop[trackCount] = loop;
                    trackDelay[trackCount] = delay + Sounds.trackDelays[trackId];
                    trackCount++;
                }
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 104)
            {
                int actionId = inStream.getUnsignedByteC();
                int actionAtTop = inStream.getUnsignedByteA();
                String actionText = inStream.getString();
                if(actionId >= 1 && actionId <= 5)
                {
                    if(actionText.equalsIgnoreCase("null"))
                        actionText = null;
                    atPlayerActions[actionId - 1] = actionText;
                    atPlayerArray[actionId - 1] = actionAtTop == 0;
                }
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 78)
            {
                destX = 0;
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 253)
            {
                String message = inStream.getString();
                if(message.endsWith(":tradereq:"))
                {
                    String name = message.substring(0, message.indexOf(":"));
                    long nameAsLong = TextClass.longForName(name);
                    boolean ignored = false;
                    for(int p = 0; p < ignoreCount; p++)
                    {
                        if(ignoreListAsLongs[p] != nameAsLong)
                            continue;
                        ignored = true;
                        break;
                    }

                    if(!ignored && inTutorial == 0)
                        pushMessage("wishes to trade with you.", 4, name);
                } else
                if(message.endsWith(":duelreq:"))
                {
                    String name = message.substring(0, message.indexOf(":"));
                    long nameAsLong = TextClass.longForName(name);
                    boolean ignored = false;
                    for(int p = 0; p < ignoreCount; p++)
                    {
                        if(ignoreListAsLongs[p] != nameAsLong)
                            continue;
                        ignored = true;
                        break;
                    }

                    if(!ignored && inTutorial == 0)
                        pushMessage("wishes to duel with you.", 8, name);
                } else
                if(message.endsWith(":chalreq:"))
                {
                    String name = message.substring(0, message.indexOf(":"));
                    long nameAsLong = TextClass.longForName(name);
                    boolean ignored = false;
                    for(int p = 0; p < ignoreCount; p++)
                    {
                        if(ignoreListAsLongs[p] != nameAsLong)
                            continue;
                        ignored = true;
                        break;
                    }

                    if(!ignored && inTutorial == 0)
                    {
                        String text = message.substring(message.indexOf(":") + 1, message.length() - 9);
                        pushMessage(text, 8, name);
                    }
                } else
                {
                    pushMessage(message, 0, "");
                }
                packetOpcode = -1;
	
                return true;
            }
            if(packetOpcode == 1)
            {
                for(int p = 0; p < playerArray.length; p++)
                    if(playerArray[p] != null)
                        playerArray[p].animation = -1;

                for(int n = 0; n < npcArray.length; n++)
                    if(npcArray[n] != null)
                        npcArray[n].animation = -1;

                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 50)
            {
                long nameAsLong = inStream.getLong();
                int worldId = inStream.getUnsignedByte();
                String name = TextClass.formatName(TextClass.nameForLong(nameAsLong));
                for(int friend = 0; friend < friendsCount; friend++)
                {
                    if(nameAsLong != friendsListAsLongs[friend])
                        continue;
                    if(friendsWorldIds[friend] != worldId)
                    {
                        friendsWorldIds[friend] = worldId;
                        needDrawTabArea = true;
                        if(worldId > 0)
                            pushMessage(name + " has logged in.", 5, "");
                        if(worldId == 0)
                            pushMessage(name + " has logged out.", 5, "");
                    }
                    name = null;
                    break;
                }

                if(name != null && friendsCount < 200)
                {
                    friendsListAsLongs[friendsCount] = nameAsLong;
                    friendsList[friendsCount] = name;
                    friendsWorldIds[friendsCount] = worldId;
                    friendsCount++;
                    needDrawTabArea = true;
                }
                for(boolean orderComplete = false; !orderComplete;)
                {
                    orderComplete = true;
                    for(int friend = 0; friend < friendsCount - 1; friend++)
                        if(friendsWorldIds[friend] != nodeID && friendsWorldIds[friend + 1] == nodeID || friendsWorldIds[friend] == 0 && friendsWorldIds[friend + 1] != 0)
                        {
                            int tempWorld = friendsWorldIds[friend];
                            friendsWorldIds[friend] = friendsWorldIds[friend + 1];
                            friendsWorldIds[friend + 1] = tempWorld;
                            String tempName = friendsList[friend];
                            friendsList[friend] = friendsList[friend + 1];
                            friendsList[friend + 1] = tempName;
                            long tempLong = friendsListAsLongs[friend];
                            friendsListAsLongs[friend] = friendsListAsLongs[friend + 1];
                            friendsListAsLongs[friend + 1] = tempLong;
                            needDrawTabArea = true;
                            orderComplete = false;
                        }
                }

                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 110)
            {
                if(tabID == 12)
                    needDrawTabArea = true;
                runEnergy = inStream.getUnsignedByte();
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 254)
            {
                hintIconType = inStream.getUnsignedByte();
                if(hintIconType == 1)
                    hintIconNpcId = inStream.getUnsignedLEShort();
                if(hintIconType >= 2 && hintIconType <= 6)
                {
                    if(hintIconType == 2)
                    {
                        hintIconDrawTileX = 64;
                        hintIconDrawTileY = 64;
                    }
                    if(hintIconType == 3)
                    {
                        hintIconDrawTileX = 0;
                        hintIconDrawTileY = 64;
                    }
                    if(hintIconType == 4)
                    {
                        hintIconDrawTileX = 128;
                        hintIconDrawTileY = 64;
                    }
                    if(hintIconType == 5)
                    {
                        hintIconDrawTileX = 64;
                        hintIconDrawTileY = 0;
                    }
                    if(hintIconType == 6)
                    {
                        hintIconDrawTileX = 64;
                        hintIconDrawTileY = 128;
                    }
                    hintIconType = 2;
                    hintIconX = inStream.getUnsignedLEShort();
                    hintIconY = inStream.getUnsignedLEShort();
                    hintIconDrawHeight = inStream.getUnsignedByte();
                }
                if(hintIconType == 10)
                    hintIconPlayerId = inStream.getUnsignedLEShort();
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 248)
            {
                int interfaceId = inStream.getUnsignedLEShortA();
                int inventoryInterfaceId = inStream.getUnsignedLEShort();
                if(backDialogID != -1)
                {
                    backDialogID = -1;
                    inputTaken = true;
                }
                if(inputDialogState != 0)
                {
                    inputDialogState = 0;
                    inputTaken = true;
                }
                openInterfaceId = interfaceId;
                inventoryOverlayInterfaceID = inventoryInterfaceId;
                needDrawTabArea = true;
                tabAreaAltered = true;
                aBoolean1149 = false;
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 79)
            {
                int interfaceId = inStream.getUnsignedShort();
                int scrollPosition = inStream.getUnsignedLEShortA();
                RSInterface rsInterface = RSInterface.interfaceCache[interfaceId];
                if(rsInterface != null && rsInterface.type == 0)
                {
                    if(scrollPosition < 0)
                        scrollPosition = 0;
                    if(scrollPosition > rsInterface.scrollMax - rsInterface.height)
                        scrollPosition = rsInterface.scrollMax - rsInterface.height;
                    rsInterface.scrollPosition = scrollPosition;
                }
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 68)
            {
                for(int setting = 0; setting < variousSettings.length; setting++)
                    if(variousSettings[setting] != defaultSettings[setting])
                    {
                        variousSettings[setting] = defaultSettings[setting];
                        handleInterfaceSetting(setting);
                        needDrawTabArea = true;
                    }

                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 196)
            {
                long nameAsLong = inStream.getLong();
                int messageId = inStream.getInt();
                int playerRights = inStream.getUnsignedByte();
                boolean ignored = false;
                for(int message = 0; message < 100; message++)
                {
                    if(privateMessages[message] != messageId)
                        continue;
                    ignored = true;
                    break;
                }

                if(playerRights <= 1)
                {
                    for(int p = 0; p < ignoreCount; p++)
                    {
                        if(ignoreListAsLongs[p] != nameAsLong)
                            continue;
                        ignored = true;
                        break;
                    }

                }
                if(!ignored && inTutorial == 0)
                    try
                    {
                        privateMessages[privateMessagePointer] = messageId;
                        privateMessagePointer = (privateMessagePointer + 1) % 100;
                        String message = TextInput.readFromStream(packetSize - 13, inStream);
                        if(playerRights != 3)
                            message = Censor.doCensor(message);
                        if(playerRights == 2 || playerRights == 3)
                            pushMessage(message, 7, "@cr2@" + TextClass.formatName(TextClass.nameForLong(nameAsLong)));
                        else
                        if(playerRights == 1)
                            pushMessage(message, 7, "@cr1@" + TextClass.formatName(TextClass.nameForLong(nameAsLong)));
                        else
                            pushMessage(message, 3, TextClass.formatName(TextClass.nameForLong(nameAsLong)));
                    }
                    catch(Exception exception1)
                    {
                        signlink.reporterror("cde1");
                    }
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 85)
            {
                playerPositionY = inStream.getUnsignedByteC();
                playerPositionX = inStream.getUnsignedByteC();
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 24)
            {
                flashingSidebar = inStream.getUnsignedByteS();
                if(flashingSidebar == tabID)
                {
                    if(flashingSidebar == 3)
                        tabID = 1;
                    else
                        tabID = 3;
                    needDrawTabArea = true;
                }
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 246)
            {
                int interfaceId = inStream.getUnsignedShort();
                int itemModelZoom = inStream.getUnsignedLEShort();
                int itemId = inStream.getUnsignedLEShort();
                if(itemId == 65535)
                {
                    RSInterface.interfaceCache[interfaceId].modelType = 0;
                    packetOpcode = -1;
                    return true;
                } else
                {
                    ItemDef itemDef = ItemDef.forID(itemId);
                    RSInterface.interfaceCache[interfaceId].modelType = 4;
                    RSInterface.interfaceCache[interfaceId].modelId = itemId;
                    RSInterface.interfaceCache[interfaceId].modelRotationX = itemDef.modelRotationX;
                    RSInterface.interfaceCache[interfaceId].modelRotationY = itemDef.modelRotationY;
                    RSInterface.interfaceCache[interfaceId].modelZoom = (itemDef.modelZoom * 100) / itemModelZoom;
                    packetOpcode = -1;
                    return true;
                }
            }
            if(packetOpcode == 171)
            {
                boolean hiddenUntilHovered = inStream.getUnsignedByte() == 1;
                int interfaceId = inStream.getUnsignedLEShort();
                RSInterface.interfaceCache[interfaceId].aBoolean266 = hiddenUntilHovered;
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 142)
            {
                int interfaceId = inStream.getUnsignedShort();
                loadInterface(interfaceId);
                if(backDialogID != -1)
                {
                    backDialogID = -1;
                    inputTaken = true;
                }
                if(inputDialogState != 0)
                {
                    inputDialogState = 0;
                    inputTaken = true;
                }
                inventoryOverlayInterfaceID = interfaceId;
                needDrawTabArea = true;
                tabAreaAltered = true;
                openInterfaceId = -1;
                aBoolean1149 = false;
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 126)
            {
                String text = inStream.getString();
                int interfaceId = inStream.getUnsignedLEShortA();
                RSInterface.interfaceCache[interfaceId].message = text;
                if(RSInterface.interfaceCache[interfaceId].parentID == tabInterfaceIDs[tabID])
                    needDrawTabArea = true;
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 206)
            {
                publicChatMode = inStream.getUnsignedByte();
                privateChatMode = inStream.getUnsignedByte();
                tradeMode = inStream.getUnsignedByte();
                updateChatSettings = true;
                inputTaken = true;
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 240)
            {
                if(tabID == 12)
                    needDrawTabArea = true;
                weight = inStream.getShort();
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 8)
            {
                int interfaceId = inStream.getUnsignedShortA();
                int interfaceModelId = inStream.getUnsignedLEShort();
                RSInterface.interfaceCache[interfaceId].modelType = 1;
                RSInterface.interfaceCache[interfaceId].modelId = interfaceModelId;
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 122)
            {
                int interfaceId = inStream.getUnsignedShortA();
                int rgb = inStream.getUnsignedShortA();
                int r = rgb >> 10 & 0x1f;
                int g = rgb >> 5 & 0x1f;
                int b = rgb & 0x1f;
                RSInterface.interfaceCache[interfaceId].textColor = (r << 19) + (g << 11) + (b << 3);
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 53)
            {
                needDrawTabArea = true;
                int interfaceId = inStream.getUnsignedLEShort();
                RSInterface rsInterface = RSInterface.interfaceCache[interfaceId];
                int itemCount = inStream.getUnsignedLEShort();
                for(int item = 0; item < itemCount; item++)
                {
                    int stackSize = inStream.getUnsignedByte();
                    if(stackSize == 255)
                        stackSize = inStream.getInt1();
                    rsInterface.inventoryItemId[item] = inStream.getUnsignedShortA();
                    rsInterface.inventoryStackSize[item] = stackSize;
                }

                for(int j25 = itemCount; j25 < rsInterface.inventoryItemId.length; j25++)
                {
                    rsInterface.inventoryItemId[j25] = 0;
                    rsInterface.inventoryStackSize[j25] = 0;
                }

                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 230)
            {
                int modelZoom = inStream.getUnsignedLEShortA();
                int interfaceId = inStream.getUnsignedLEShort();
                int modelRotationX = inStream.getUnsignedLEShort();
                int modelRotationY = inStream.getUnsignedShortA();
                RSInterface.interfaceCache[interfaceId].modelRotationX = modelRotationX;
                RSInterface.interfaceCache[interfaceId].modelRotationY = modelRotationY;
                RSInterface.interfaceCache[interfaceId].modelZoom = modelZoom;
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 221)
            {
                friendListStatus = inStream.getUnsignedByte();
                needDrawTabArea = true;
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 177)
            {
                inCutscene = true;
                anInt995 = inStream.getUnsignedByte();
                anInt996 = inStream.getUnsignedByte();
                cameraOffsetZ = inStream.getUnsignedLEShort();
                anInt998 = inStream.getUnsignedByte();
                anInt999 = inStream.getUnsignedByte();
                if(anInt999 >= 100)
                {
                    int x = anInt995 * 128 + 64;
                    int y = anInt996 * 128 + 64;
                    int z = getFloorDrawHeight(plane, y, x) - cameraOffsetZ;
                    int distanceX = x - cameraPositionX;
                    int distanceZ = z - cameraPositionZ;
                    int distanceY = y - cameraPositionY;
                    int distanceXY = (int)Math.sqrt(distanceX * distanceX + distanceY * distanceY);
                    cameraCurveY = (int)(Math.atan2(distanceZ, distanceXY) * 325.94900000000001D) & 0x7ff;
                    cameraCurveZ = (int)(Math.atan2(distanceX, distanceY) * -325.94900000000001D) & 0x7ff;
                    if(cameraCurveY < 128)
                        cameraCurveY = 128;
                    if(cameraCurveY > 383)
                        cameraCurveY = 383;
                }
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 249)
            {
                membershipStatus = inStream.getUnsignedByteA();
                playerListId = inStream.getUnsignedShortA();
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 65)
            {
                updateNPCs(inStream, packetSize);
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 27)
            {
                messagePromptRaised = false;
                inputDialogState = 1;
                amountOrNameInput = "";
                inputTaken = true;
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 187)
            {
                messagePromptRaised = false;
                inputDialogState = 2;
                amountOrNameInput = "";
                inputTaken = true;
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 97)
            {
                int interfaceId = inStream.getUnsignedLEShort();
                loadInterface(interfaceId);
                if(inventoryOverlayInterfaceID != -1)
                {
                    inventoryOverlayInterfaceID = -1;
                    needDrawTabArea = true;
                    tabAreaAltered = true;
                }
                if(backDialogID != -1)
                {
                    backDialogID = -1;
                    inputTaken = true;
                }
                if(inputDialogState != 0)
                {
                    inputDialogState = 0;
                    inputTaken = true;
                }
                openInterfaceId = interfaceId;
                aBoolean1149 = false;
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 218)
            {
                int interfaceId = inStream.getForceLEShortA();
                dialogID = interfaceId;
                inputTaken = true;
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 87)
            {
                int settingId = inStream.getUnsignedShort();
                int settingValue = inStream.getInt2();
                defaultSettings[settingId] = settingValue;
                if(variousSettings[settingId] != settingValue)
                {
                    variousSettings[settingId] = settingValue;
                    handleInterfaceSetting(settingId);
                    needDrawTabArea = true;
                    if(dialogID != -1)
                        inputTaken = true;
                }
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 36)
            {
                int settingId = inStream.getUnsignedShort();
                byte settingValue = inStream.get();
                defaultSettings[settingId] = settingValue;
                if(variousSettings[settingId] != settingValue)
                {
                    variousSettings[settingId] = settingValue;
                    handleInterfaceSetting(settingId);
                    needDrawTabArea = true;
                    if(dialogID != -1)
                        inputTaken = true;
                }
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 61)
            {
                multiCombatZone = inStream.getUnsignedByte();
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 200)
            {
                int interfaceId = inStream.getUnsignedLEShort();
                int animationId = inStream.getShort();
                RSInterface rsInterface = RSInterface.interfaceCache[interfaceId];
                rsInterface.animationId = animationId;
                if(animationId == -1)
                {
                    rsInterface.animationFrame = 0;
                    rsInterface.animationDuration = 0;
                }
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 219)
            {
                if(inventoryOverlayInterfaceID != -1)
                {
                    inventoryOverlayInterfaceID = -1;
                    needDrawTabArea = true;
                    tabAreaAltered = true;
                }
                if(backDialogID != -1)
                {
                    backDialogID = -1;
                    inputTaken = true;
                }
                if(inputDialogState != 0)
                {
                    inputDialogState = 0;
                    inputTaken = true;
                }
                openInterfaceId = -1;
                aBoolean1149 = false;
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 34)
            {
                needDrawTabArea = true;
                int interfaceId = inStream.getUnsignedLEShort();
                RSInterface rsInterface = RSInterface.interfaceCache[interfaceId];
                while(inStream.currentOffset < packetSize)
                {
                    int itemSlot = inStream.getSmartB();
                    int itemId = inStream.getUnsignedLEShort();
                    int itemAmount = inStream.getUnsignedByte();
                    if(itemAmount == 255)
                        itemAmount = inStream.getInt();
                    if(itemSlot >= 0 && itemSlot < rsInterface.inventoryItemId.length)
                    {
                        rsInterface.inventoryItemId[itemSlot] = itemId;
                        rsInterface.inventoryStackSize[itemSlot] = itemAmount;
                    }
                }
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 105 || packetOpcode == 84 || packetOpcode == 147 || packetOpcode == 215 || packetOpcode == 4 || packetOpcode == 117 || packetOpcode == 156 || packetOpcode == 44 || packetOpcode == 160 || packetOpcode == 101 || packetOpcode == 151)
            {
                method137(inStream, packetOpcode);
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 106)
            {
                tabID = inStream.getUnsignedByteC();
                needDrawTabArea = true;
                tabAreaAltered = true;
                packetOpcode = -1;
                return true;
            }
            if(packetOpcode == 164)
            {
                int interfaceId = inStream.getUnsignedShort();
                loadInterface(interfaceId);
                if(inventoryOverlayInterfaceID != -1)
                {
                    inventoryOverlayInterfaceID = -1;
                    needDrawTabArea = true;
                    tabAreaAltered = true;
                }
                backDialogID = interfaceId;
                inputTaken = true;
                openInterfaceId = -1;
                aBoolean1149 = false;
                packetOpcode = -1;
                return true;
            }
            signlink.reporterror("T1 - " + packetOpcode + "," + packetSize + " - " + anInt842 + "," + anInt843);
            logout();
        }
        catch(IOException _ex)
        {
            dropClient();
        }
        catch(Exception exception)
        {
            String s2 = "T2 - " + packetOpcode + "," + anInt842 + "," + anInt843 + " - " + packetSize + "," + (baseX + localPlayer.smallX[0]) + "," + (baseY + localPlayer.smallY[0]) + " - ";
            for(int j15 = 0; j15 < packetSize && j15 < 50; j15++)
                s2 = s2 + inStream.buffer[j15] + ",";

            signlink.reporterror(s2);
            logout();
        }
        return true;
    }

    private void method146()
    {
        anInt1265++;
        method47(true);
        method26(true);
        method47(false);
        method26(false);
        method55();
        renderStationaryGraphics();
        if(!inCutscene)
        {
            int i = anInt1184;
            if(anInt984 / 256 > i)
                i = anInt984 / 256;
            if(aBooleanArray876[4] && cameraAmplitude[4] + 128 > i)
                i = cameraAmplitude[4] + 128;
            int k = minimapInt1 + anInt896 & 0x7ff;
            setCameraPos(600 + i * 3, i, anInt1014, getFloorDrawHeight(plane, localPlayer.y, localPlayer.x) - 50, k, anInt1015);
        }
        int j;
        if(!inCutscene)
            j = method120();
        else
            j = method121();
        int l = cameraPositionX;
        int i1 = cameraPositionZ;
        int j1 = cameraPositionY;
        int k1 = cameraCurveY;
        int l1 = cameraCurveZ;
        for(int i2 = 0; i2 < 5; i2++)
            if(aBooleanArray876[i2])
            {
                int j2 = (int)((Math.random() * (double)(cameraJitter[i2] * 2 + 1) - (double)cameraJitter[i2]) + Math.sin((double)anIntArray1030[i2] * ((double)cameraFrequency[i2] / 100D)) * (double)cameraAmplitude[i2]);
                if(i2 == 0)
                    cameraPositionX += j2;
                if(i2 == 1)
                    cameraPositionZ += j2;
                if(i2 == 2)
                    cameraPositionY += j2;
                if(i2 == 3)
                    cameraCurveZ = cameraCurveZ + j2 & 0x7ff;
                if(i2 == 4)
                {
                    cameraCurveY += j2;
                    if(cameraCurveY < 128)
                        cameraCurveY = 128;
                    if(cameraCurveY > 383)
                        cameraCurveY = 383;
                }
            }

        int k2 = Texture.textureGetCount;
        Model.aBoolean1684 = true;
            Model.anInt1687 = 0;
            Model.cursorX = super.mouseX - 4;
            Model.cursorY = super.mouseY - 4;
            DrawingArea.setAllPixelsToZero();
//xxx disables graphics            if(graphicsEnabled){
            worldController.render(cameraPositionX, cameraPositionY, cameraCurveZ, cameraPositionZ, j, cameraCurveY);
            worldController.clearInteractiveObjectCache();
            updateEntities();
            drawHeadIcon();
            method37(k2);
            draw3dScreen();
            gameScreenCanvas.drawGraphics(4, super.gameGraphics, 4);
            cameraPositionX = l;
            cameraPositionZ = i1;
            cameraPositionY = j1;
            cameraCurveY = k1;
            cameraCurveZ = l1;
//            }
    }

    private void clearTopInterfaces()
    {
        stream.putOpcode(130);
        if(inventoryOverlayInterfaceID != -1)
        {
            inventoryOverlayInterfaceID = -1;
            needDrawTabArea = true;
            aBoolean1149 = false;
            tabAreaAltered = true;
        }
        if(backDialogID != -1)
        {
            backDialogID = -1;
            inputTaken = true;
            aBoolean1149 = false;
        }
        openInterfaceId = -1;
    }

    private client()
    {
        distanceValues = new int[104][104];
        friendsWorldIds = new int[200];
        groundArray = new NodeList[4][104][104];
        currentlyDrawingFlames = false;
        textStream = new Stream(new byte[5000]);
        npcArray = new NPC[16384];
        npcIndices = new int[16384];
        anIntArray840 = new int[1000];
        aStream_847 = Stream.create();
        aBoolean848 = true;
        openInterfaceId = -1;
        currentExp = new int[Skills.skillsCount];
        aBoolean872 = false;
        cameraJitter = new int[5];
        anInt874 = -1;
        aBooleanArray876 = new boolean[5];
        drawFlames = false;
        reportAbuseInput = "";
        playerListId = -1;
        menuOpen = false;
        inputString = "";
        maxPlayers = 2048;
        localPlayerId = 2047;
        playerArray = new Player[maxPlayers];
        playerId = new int[maxPlayers];
        playersAwaitingUpdate = new int[maxPlayers];
        playerBuffer = new Stream[maxPlayers];
        anInt897 = 1;
        wayPoints = new int[104][104];
        anInt902 = 0x766654;
        aByteArray912 = new byte[16384];
        currentStats = new int[Skills.skillsCount];
        ignoreListAsLongs = new long[100];
        loadingError = false;
        anInt927 = 0x332d25;
        cameraFrequency = new int[5];
        anIntArrayArray929 = new int[104][104];
        chatTypes = new int[100];
        chatNames = new String[100];
        chatMessages = new String[100];
        sideIcons = new Background[13];
        aBoolean954 = true;
        friendsListAsLongs = new long[200];
        currentSong = -1;
        drawingFlames = false;
        spriteDrawX = -1;
        spriteDrawY = -1;
        anIntArray968 = new int[33];
        anIntArray969 = new int[256];
        decompressors = new Decompressor[5];
        variousSettings = new int[2000];
        aBoolean972 = false;
        anInt975 = 50;
        anIntArray976 = new int[anInt975];
        anIntArray977 = new int[anInt975];
        anIntArray978 = new int[anInt975];
        anIntArray979 = new int[anInt975];
        anIntArray980 = new int[anInt975];
        anIntArray981 = new int[anInt975];
        anIntArray982 = new int[anInt975];
        aStringArray983 = new String[anInt975];
        lastRegionId = -1;
        hitMarks = new Sprite[20];
        anIntArray990 = new int[5];
        aBoolean994 = false;
        anInt1002 = 0x23201b;
        amountOrNameInput = "";
        projectileQueue = new NodeList();
        aBoolean1017 = false;
        walkableInterfaceId = -1;
        anIntArray1030 = new int[5];
        aBoolean1031 = false;
        mapFunctions = new Sprite[100];
        dialogID = -1;
        maxStats = new int[Skills.skillsCount];
        defaultSettings = new int[2000];
        aBoolean1047 = true;
        anIntArray1052 = new int[151];
        flashingSidebar = -1;
        stationaryGraphicQueue = new NodeList();
        anIntArray1057 = new int[33];
        aClass9_1059 = new RSInterface();
        mapScenes = new Background[100];
        anInt1063 = 0x4d4233;
        anIntArray1065 = new int[7];
        anIntArray1072 = new int[1000];
        anIntArray1073 = new int[1000];
        loadingMap = false;
        friendsList = new String[200];
        inStream = Stream.create();
        expectedCRCs = new int[9];
        menuActionCmd2 = new int[500];
        menuActionCmd3 = new int[500];
        menuActionID = new int[500];
        menuActionCmd1 = new int[500];
        headIcons = new Sprite[20];
        tabAreaAltered = false;
        aString1121 = "";
        atPlayerActions = new String[5];
        atPlayerArray = new boolean[5];
        constructMapTiles = new int[4][13][13];
        anInt1132 = 2;
        aClass30_Sub2_Sub1_Sub1Array1140 = new Sprite[1000];
        tutorialIsland = false;
        aBoolean1149 = false;
        crosses = new Sprite[8];
        musicEnabled = true;
        needDrawTabArea = false;
        loggedIn = false;
        canMute = false;
        loadGeneratedMap = false;
        inCutscene = false;
        anInt1171 = 1;
        myUsername = "mopar";
        myPassword = "bob";
        genericLoadingError = false;
        reportAbuseInterfaceID = -1;
        spawnObjectList = new NodeList();
        anInt1184 = 128;
        inventoryOverlayInterfaceID = -1;
        stream = Stream.create();
        menuActionName = new String[500];
        cameraAmplitude = new int[5];
        trackIds = new int[50];
        anInt1210 = 2;
        anInt1211 = 78;
        promptInput = "";
        modIcons = new Background[2];
        tabID = 3;
        inputTaken = false;
        songChanging = true;
        anIntArray1229 = new int[151];
        currentCollisionMap = new CollisionMap[4];
        updateChatSettings = false;
        privateMessages = new int[100];
        trackLoop = new int[50];
        aBoolean1242 = false;
        trackDelay = new int[50];
        rsAlreadyLoaded = false;
        welcomeScreenRaised = false;
        messagePromptRaised = false;
        loginMessage1 = "";
        loginMessage2 = "";
        backDialogID = -1;
        anInt1279 = 2;
        walkingQueueX = new int[4000];
        walkingQueueY = new int[4000];
        anInt1289 = -1;
    }

    private int ignoreCount;
    private long aLong824;
    private int[][] distanceValues;
    private int[] friendsWorldIds;
    private NodeList[][][] groundArray;
    private int[] anIntArray828;
    private int[] anIntArray829;
    private volatile boolean currentlyDrawingFlames;
    private Socket aSocket832;
    private int loginScreenState;
    private Stream textStream;
    private NPC[] npcArray;
    private int npcCount;
    private int[] npcIndices;
    private int anInt839;
    private int[] anIntArray840;
    private int anInt841;
    private int anInt842;
    private int anInt843;
    private String aString844;
    private int privateChatMode;
    private Stream aStream_847;
    private boolean aBoolean848;
    private static int anInt849;
    private int[] anIntArray850;
    private int[] anIntArray851;
    private int[] anIntArray852;
    private int[] anIntArray853;
    private static int anInt854;
    private int hintIconType;
    private int openInterfaceId;
    private int cameraPositionX;
    private int cameraPositionZ;
    private int cameraPositionY;
    private int cameraCurveY;
    private int cameraCurveZ;
    private int playerRights;
    private final int[] currentExp;
    private Background redStone1_3;
    private Background redStone2_3;
    private Background redStone3_2;
    private Background redStone1_4;
    private Background redStone2_4;
    private Sprite mapFlag;
    private Sprite mapMarker;
    private boolean aBoolean872;
    private final int[] cameraJitter;
    private int anInt874;
    private final boolean[] aBooleanArray876;
    private int weight;
    private MouseDetection mouseDetection;
    private volatile boolean drawFlames;
    private String reportAbuseInput;
    private int playerListId;
    private boolean menuOpen;
    private int anInt886;
    private String inputString;
    private final int maxPlayers;
    private final int localPlayerId;
    private Player[] playerArray;
    private int playerCount;
    private int[] playerId;
    private int actorsAwaitingUpdatePointer;
    private int[] playersAwaitingUpdate;
    private Stream[] playerBuffer;
    private int anInt896;
    private int anInt897;
    private int friendsCount;
    private int friendListStatus;
    private int[][] wayPoints;
    private final int anInt902;
    private RSImageProducer backLeftIP1;
    private RSImageProducer backLeftIP2;
    private RSImageProducer backRightIP1;
    private RSImageProducer backRightIP2;
    private RSImageProducer backTopIP1;
    private RSImageProducer backVmidIP1;
    private RSImageProducer backVmidIP2;
    private RSImageProducer backVmidIP3;
    private RSImageProducer backVmidIP2_2;
    private byte[] aByteArray912;
    private int anInt913;
    private int crossX;
    private int crossY;
    private int crossIndex;
    private int crossType;
    private int plane;
    private final int[] currentStats;
    private static int anInt924;
    private final long[] ignoreListAsLongs;
    private boolean loadingError;
    private final int anInt927;
    private final int[] cameraFrequency;
    private int[][] anIntArrayArray929;
    private Sprite aClass30_Sub2_Sub1_Sub1_931;
    private Sprite aClass30_Sub2_Sub1_Sub1_932;
    private int hintIconPlayerId;
    private int hintIconX;
    private int hintIconY;
    private int hintIconDrawHeight;
    private int hintIconDrawTileX;
    private int hintIconDrawTileY;
    private static int anInt940;
    private final int[] chatTypes;
    private final String[] chatNames;
    private final String[] chatMessages;
    private int animationTimePassed;
    private WorldController worldController;
    private Background[] sideIcons;
    private int menuScreenArea;
    private int menuOffsetX;
    private int menuOffsetY;
    private int menuWidth;
    private int menuHeight;
    private long privateMessageTarget;
    private boolean aBoolean954;
    private long[] friendsListAsLongs;
    private int currentSong;
    private static int nodeID = 10;
    static int portOffset;
    private static boolean isMembers = true;
    private static boolean lowMemory;
    private volatile boolean drawingFlames;
    private int spriteDrawX;
    private int spriteDrawY;
    private final int[] anIntArray965 = {
        0xffff00, 0xff0000, 65280, 65535, 0xff00ff, 0xffffff
    };
    private Background aBackground_966;
    private Background aBackground_967;
    private final int[] anIntArray968;
    private final int[] anIntArray969;
    final Decompressor[] decompressors;
    public int variousSettings[];
    private boolean aBoolean972;
    private final int anInt975;
    private final int[] anIntArray976;
    private final int[] anIntArray977;
    private final int[] anIntArray978;
    private final int[] anIntArray979;
    private final int[] anIntArray980;
    private final int[] anIntArray981;
    private final int[] anIntArray982;
    private final String[] aStringArray983;
    private int anInt984;
    private int lastRegionId;
    private static int anInt986;
    private Sprite[] hitMarks;
    private int anInt988;
    private int anInt989;
    private final int[] anIntArray990;
    private static boolean aBoolean993;
    private final boolean aBoolean994;
    private int anInt995;
    private int anInt996;
    private int cameraOffsetZ;
    private int anInt998;
    private int anInt999;
    private ISAACRandomGen encryption;
    private Sprite mapEdge;
    private final int anInt1002;
    static final int[][] anIntArrayArray1003 = {
        {
            6798, 107, 10283, 16, 4797, 7744, 5799, 4634, 33697, 22433, 
            2983, 54193
        }, {
            8741, 12, 64030, 43162, 7735, 8404, 1701, 38430, 24094, 10153, 
            56621, 4783, 1341, 16578, 35003, 25239
        }, {
            25238, 8742, 12, 64030, 43162, 7735, 8404, 1701, 38430, 24094, 
            10153, 56621, 4783, 1341, 16578, 35003
        }, {
            4626, 11146, 6439, 12, 4758, 10270
        }, {
            4550, 4537, 5681, 5673, 5790, 6806, 8076, 4574
        }
    };
    private String amountOrNameInput;
    private static int anInt1005;
    private int daysSinceLogin;
    private int packetSize;
    private int packetOpcode;
    private int anInt1009;
    private int anInt1010;
    private int anInt1011;
    private NodeList projectileQueue;
    private int anInt1014;
    private int anInt1015;
    private int anInt1016;
    private boolean aBoolean1017;
    private int walkableInterfaceId;
    private static final int[] xpForLevel;
    private int minimapState;
    private int anInt1022;
    private int loadingStage;
    private Background scrollBar1;
    private Background scrollBar2;
    private int anInt1026;
    private Background backBase1;
    private Background backBase2;
    private Background backHmid1;
    private final int[] anIntArray1030;
    private boolean aBoolean1031;
    private Sprite[] mapFunctions;
    private int baseX;
    private int baseY;
    private int anInt1036;
    private int anInt1037;
    private int loginFailures;
    private int anInt1039;
    private int anInt1040;
    private int anInt1041;
    private int dialogID;
    private final int[] maxStats;
    private final int[] defaultSettings;
    private int membershipStatus;
    private boolean aBoolean1047;
    private int anInt1048;
    private String aString1049;
    private static int loadedRegions;
    private final int[] anIntArray1052;
    private StreamLoader titleStreamLoader;
    private int flashingSidebar;
    private int multiCombatZone;
    private NodeList stationaryGraphicQueue;
    private final int[] anIntArray1057;
    private final RSInterface aClass9_1059;
    private Background[] mapScenes;
    private static int anInt1061;
    private int trackCount;
    private final int anInt1063;
    private int friendsListAction;
    private final int[] anIntArray1065;
    private int mouseInvInterfaceIndex;
    private int lastActiveInvInterface;
    private OnDemandFetcher onDemandFetcher;
    private int regionX;
    private int regionY;
    private int anInt1071;
    private int[] anIntArray1072;
    private int[] anIntArray1073;
    private Sprite mapDotItem;
    private Sprite mapDotNPC;
    private Sprite mapDotPlayer;
    private Sprite mapDotFriend;
    private Sprite mapDotTeam;
    private int anInt1079;
    private boolean loadingMap;
    private String[] friendsList;
    private Stream inStream;
    private int anInt1084;
    private int anInt1085;
    private int activeInterfaceType;
    private int anInt1087;
    private int anInt1088;
    private int anInt1089;
    private final int[] expectedCRCs;
    private int[] menuActionCmd2;
    private int[] menuActionCmd3;
    private int[] menuActionID;
    private int[] menuActionCmd1;
    private Sprite[] headIcons;
    private static int anticheat2;
    private int anInt1098;
    private int anInt1099;
    private int anInt1100;
    private int anInt1101;
    private int anInt1102;
    private boolean tabAreaAltered;
    private int systemUpdateTime;
    private RSImageProducer aRSImageProducer_1107;
    private RSImageProducer aRSImageProducer_1108;
    private RSImageProducer aRSImageProducer_1109;
    private RSImageProducer aRSImageProducer_1110;
    private RSImageProducer aRSImageProducer_1111;
    private RSImageProducer aRSImageProducer_1112;
    private RSImageProducer aRSImageProducer_1113;
    private RSImageProducer aRSImageProducer_1114;
    private RSImageProducer aRSImageProducer_1115;
    private static int mouseClickCounter;
    private int membership;
    private String aString1121;
    private Sprite compass;
    private RSImageProducer aRSImageProducer_1123;
    private RSImageProducer aRSImageProducer_1124;
    private RSImageProducer aRSImageProducer_1125;
    public static Player localPlayer;
    private final String[] atPlayerActions;
    private final boolean[] atPlayerArray;
    private final int[][][] constructMapTiles;
    private final int[] tabInterfaceIDs = {
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
        -1, -1, -1, -1, -1
    };
    private int anInt1131;
    private int anInt1132;
    private int menuActionRow;
    private static int anInt1134;
    private int spellSelected;
    private int anInt1137;
    private int spellUsableOn;
    private String spellTooltip;
    private Sprite[] aClass30_Sub2_Sub1_Sub1Array1140;
    private boolean tutorialIsland;
    private static int anInt1142;
    private Background redStone1;
    private Background redStone2;
    private Background redStone3;
    private Background redStone1_2;
    private Background redStone2_2;
    private int runEnergy;
    private boolean aBoolean1149;
    private Sprite[] crosses;
    private boolean musicEnabled;
    private Background[] aBackgroundArray1152s;
    private boolean needDrawTabArea;
    private int unreadMessages;
    private static int anInt1155;
    private static boolean fpsOn;
    public boolean loggedIn;
    private boolean canMute;
    private boolean loadGeneratedMap;
    private boolean inCutscene;
    static int loopCycle;
    private static final String validUserPassChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"\243$%^&*()-_=+[{]};:'@#~,<.>/?\\| ";
    private RSImageProducer aRSImageProducer_1163;
    private RSImageProducer aRSImageProducer_1164;
    private RSImageProducer gameScreenCanvas;
    private RSImageProducer aRSImageProducer_1166;
    private int daysSinceRecoveryChange;
    private RSSocket socket;
    private int privateMessagePointer;
    private int minimapZoom;
    private int anInt1171;
    private long aLong1172;
    private String myUsername;
    private String myPassword;
    private static int anInt1175;
    private boolean genericLoadingError;
    private final int[] objectTypes = {
        0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 
        2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 
        2, 2, 3
    };
    private int reportAbuseInterfaceID;
    private NodeList spawnObjectList;
    private int[] anIntArray1180;
    private int[] anIntArray1181;
    private int[] anIntArray1182;
    private byte[][] terrainData;
    private int anInt1184;
    private int minimapInt1;
    private int anInt1186;
    private int anInt1187;
    private static int anInt1188;
    private int inventoryOverlayInterfaceID;
    private int[] anIntArray1190;
    private int[] anIntArray1191;
    private Stream stream;
    private int lastAddress;
    private int splitPrivateChat;
    private Background invBack;
    private Background mapBack;
    private Background chatBack;
    private String[] menuActionName;
    private Sprite aClass30_Sub2_Sub1_Sub1_1201;
    private Sprite aClass30_Sub2_Sub1_Sub1_1202;
    private final int[] cameraAmplitude;
    static final int[] anIntArray1204 = {
        9104, 10275, 7595, 3610, 7975, 8526, 918, 38802, 24466, 10145, 
        58654, 5027, 1457, 16565, 34991, 25486
    };
    private static boolean flagged;
    private final int[] trackIds;
    private int anInt1208;
    private int minimapRotation;
    private int anInt1210;
    private int anInt1211;
    private String promptInput;
    private int anInt1213;
    private int[][][] intGroundArray;
    private long serverSessionKey;
    private int loginScreenCursorPos;
    private final Background[] modIcons;
    private long aLong1220;
    private int tabID;
    private int hintIconNpcId;
    private boolean inputTaken;
    private int inputDialogState;
    private static int anInt1226;
    private int nextSong;
    private boolean songChanging;
    private final int[] anIntArray1229;
    private CollisionMap[] currentCollisionMap;
    public static int anIntArray1232[];
    private boolean updateChatSettings;
    private int[] mapCoordinates;
    private int[] anIntArray1235;
    private int[] anIntArray1236;
    private int anInt1237;
    private int anInt1238;
    public final int anInt1239 = 100;
    private final int[] privateMessages;
    private final int[] trackLoop;
    private boolean aBoolean1242;
    private int atInventoryLoopCycle;
    private int atInventoryInterface;
    private int atInventoryIndex;
    private int atInventoryInterfaceType;
    private byte[][] objectData;
    private int tradeMode;
    private int anInt1249;
    private final int[] trackDelay;
    private int inTutorial;
    private final boolean rsAlreadyLoaded;
    private int anInt1253;
    private int anInt1254;
    private boolean welcomeScreenRaised;
    private boolean messagePromptRaised;
    private int anInt1257;
    private byte[][][] byteGroundArray;
    private int prevSong;
    private int destX;
    private int destY;
    private Sprite aClass30_Sub2_Sub1_Sub1_1263;
    private int arbitraryDestination;
    private int anInt1265;
    private String loginMessage1;
    private String loginMessage2;
    private int playerPositionX;
    private int playerPositionY;
    private TextDrawingArea aTextDrawingArea_1270;
    private TextDrawingArea plainFont;
    private TextDrawingArea chatTextDrawingArea;
    private int anInt1275;
    private int backDialogID;
    private int anInt1278;
    private int anInt1279;
    private int[] walkingQueueX;
    private int[] walkingQueueY;
    private int itemSelected;
    private int anInt1283;
    private int anInt1284;
    private int anInt1285;
    private String selectedItemName;
    private int publicChatMode;
    private static int currentWalkingQueueSize;
    private int anInt1289;
	public static int anInt1290;

    static 
    {
        xpForLevel = new int[99];
        int i = 0;
        for(int j = 0; j < 99; j++)
        {
            int l = j + 1;
            int i1 = (int)((double)l + 300D * Math.pow(2D, (double)l / 7D));
            i += i1;
            xpForLevel[j] = i / 4;
        }

        anIntArray1232 = new int[32];
        i = 2;
        for(int k = 0; k < 32; k++)
        {
            anIntArray1232[k] = i - 1;
            i += i;
        }

    }
}
