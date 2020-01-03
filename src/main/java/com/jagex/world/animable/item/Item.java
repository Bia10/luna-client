package com.jagex.world.animable.item;

import com.jagex.world.animable.Animable;
import com.jagex.world.animable.Model;

public final class Item extends Animable {

    @Override
    public final Model getRotatedModel() {
        ItemDef itemDef = ItemDef.forID(ID);
        return itemDef.method201(anInt1559);
    }

    public Item() {
    }

    public int ID;
    public int x;
    public int y;
    public int anInt1559;
}
