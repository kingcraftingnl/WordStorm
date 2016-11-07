package net.minecraft.client.audio;

import com.google.common.collect.Maps;
import java.util.Map;

public enum SoundCategory
{
    MASTER("master", 0),
    MUSIC("music", 1),
    RECORDS("record", 2),
    WEATHER("weather", 3),
    BLOCKS("block", 4),
    MOBS("hostile", 5),
    ANIMALS("neutral", 6),
    PLAYERS("player", 7),
    AMBIENT("ambient", 8);
    private static final Map NAME_CATEGORY_MAP = Maps.newHashMap();
    private static final Map ID_CATEGORY_MAP = Maps.newHashMap();
    private final String categoryName;
    private final int categoryId;
    private static final String __OBFID = "CL_00001686";

    private SoundCategory(String name, int id)
    {
        this.categoryName = name;
        this.categoryId = id;
    }

    public String getCategoryName()
    {
        return this.categoryName;
    }

    public int getCategoryId()
    {
        return this.categoryId;
    }

    public static SoundCategory getCategory(String name)
    {
        return (SoundCategory)NAME_CATEGORY_MAP.get(name);
    }

    static {
        SoundCategory[] var0 = values();
        int var1 = var0.length;

        for (int var2 = 0; var2 < var1; ++var2)
        {
            SoundCategory var3 = var0[var2];

            if (NAME_CATEGORY_MAP.containsKey(var3.getCategoryName()) || ID_CATEGORY_MAP.containsKey(Integer.valueOf(var3.getCategoryId())))
            {
                throw new Error("Clash in Sound Category ID & Name pools! Cannot insert " + var3);
            }

            NAME_CATEGORY_MAP.put(var3.getCategoryName(), var3);
            ID_CATEGORY_MAP.put(Integer.valueOf(var3.getCategoryId()), var3);
        }
    }
}