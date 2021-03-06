package net.minecraft.client.renderer;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class BlockFluidRenderer
{
    private TextureAtlasSprite[] atlasSpritesLava = new TextureAtlasSprite[2];
    private TextureAtlasSprite[] atlasSpritesWater = new TextureAtlasSprite[2];
    private static final String __OBFID = "CL_00002519";

    public BlockFluidRenderer()
    {
        this.initAtlasSprites();
    }

    protected void initAtlasSprites()
    {
        TextureMap var1 = Minecraft.getMinecraft().getTextureMapBlocks();
        this.atlasSpritesLava[0] = var1.getAtlasSprite("minecraft:blocks/lava_still");
        this.atlasSpritesLava[1] = var1.getAtlasSprite("minecraft:blocks/lava_flow");
        this.atlasSpritesWater[0] = var1.getAtlasSprite("minecraft:blocks/water_still");
        this.atlasSpritesWater[1] = var1.getAtlasSprite("minecraft:blocks/water_flow");
    }

    public boolean renderFluid(IBlockAccess blockAccess, IBlockState blockStateIn, BlockPos blockPosIn, WorldRenderer worldRendererIn)
    {
        BlockLiquid var5 = (BlockLiquid)blockStateIn.getBlock();
        var5.setBlockBoundsBasedOnState(blockAccess, blockPosIn);
        TextureAtlasSprite[] var6 = var5.getMaterial() == Material.lava ? this.atlasSpritesLava : this.atlasSpritesWater;
        int var7 = var5.colorMultiplier(blockAccess, blockPosIn);
        float var8 = (float)(var7 >> 16 & 255) / 255.0F;
        float var9 = (float)(var7 >> 8 & 255) / 255.0F;
        float var10 = (float)(var7 & 255) / 255.0F;
        boolean var11 = var5.shouldSideBeRendered(blockAccess, blockPosIn.up(), EnumFacing.UP);
        boolean var12 = var5.shouldSideBeRendered(blockAccess, blockPosIn.down(), EnumFacing.DOWN);
        boolean[] var13 = new boolean[] {var5.shouldSideBeRendered(blockAccess, blockPosIn.north(), EnumFacing.NORTH), var5.shouldSideBeRendered(blockAccess, blockPosIn.south(), EnumFacing.SOUTH), var5.shouldSideBeRendered(blockAccess, blockPosIn.west(), EnumFacing.WEST), var5.shouldSideBeRendered(blockAccess, blockPosIn.east(), EnumFacing.EAST)};

        if (!var11 && !var12 && !var13[0] && !var13[1] && !var13[2] && !var13[3])
        {
            return false;
        }
        else
        {
            boolean var14 = false;
            float var15 = 0.5F;
            float var16 = 1.0F;
            float var17 = 0.8F;
            float var18 = 0.6F;
            Material var19 = var5.getMaterial();
            float var20 = this.getFluidHeight(blockAccess, blockPosIn, var19);
            float var21 = this.getFluidHeight(blockAccess, blockPosIn.south(), var19);
            float var22 = this.getFluidHeight(blockAccess, blockPosIn.east().south(), var19);
            float var23 = this.getFluidHeight(blockAccess, blockPosIn.east(), var19);
            double var24 = (double)blockPosIn.getX();
            double var26 = (double)blockPosIn.getY();
            double var28 = (double)blockPosIn.getZ();
            float var30 = 0.001F;
            TextureAtlasSprite var31;
            float var32;
            float var33;
            float var34;
            float var35;
            float var36;
            float var37;

            if (var11)
            {
                var14 = true;
                var31 = var6[0];
                var32 = (float)BlockLiquid.getFlowDirection(blockAccess, blockPosIn, var19);

                if (var32 > -999.0F)
                {
                    var31 = var6[1];
                }

                var20 -= var30;
                var21 -= var30;
                var22 -= var30;
                var23 -= var30;
                float var38;
                float var39;
                float var40;

                if (var32 < -999.0F)
                {
                    var33 = var31.getInterpolatedU(0.0D);
                    var37 = var31.getInterpolatedV(0.0D);
                    var34 = var33;
                    var38 = var31.getInterpolatedV(16.0D);
                    var35 = var31.getInterpolatedU(16.0D);
                    var39 = var38;
                    var36 = var35;
                    var40 = var37;
                }
                else
                {
                    float var41 = MathHelper.sin(var32) * 0.25F;
                    float var42 = MathHelper.cos(var32) * 0.25F;
                    float var43 = 8.0F;
                    var33 = var31.getInterpolatedU((double)(8.0F + (-var42 - var41) * 16.0F));
                    var37 = var31.getInterpolatedV((double)(8.0F + (-var42 + var41) * 16.0F));
                    var34 = var31.getInterpolatedU((double)(8.0F + (-var42 + var41) * 16.0F));
                    var38 = var31.getInterpolatedV((double)(8.0F + (var42 + var41) * 16.0F));
                    var35 = var31.getInterpolatedU((double)(8.0F + (var42 + var41) * 16.0F));
                    var39 = var31.getInterpolatedV((double)(8.0F + (var42 - var41) * 16.0F));
                    var36 = var31.getInterpolatedU((double)(8.0F + (var42 - var41) * 16.0F));
                    var40 = var31.getInterpolatedV((double)(8.0F + (-var42 - var41) * 16.0F));
                }

                worldRendererIn.setBrightness(var5.getMixedBrightnessForBlock(blockAccess, blockPosIn));
                worldRendererIn.setColorOpaque_F(var16 * var8, var16 * var9, var16 * var10);
                worldRendererIn.addVertexWithUV(var24 + 0.0D, var26 + (double)var20, var28 + 0.0D, (double)var33, (double)var37);
                worldRendererIn.addVertexWithUV(var24 + 0.0D, var26 + (double)var21, var28 + 1.0D, (double)var34, (double)var38);
                worldRendererIn.addVertexWithUV(var24 + 1.0D, var26 + (double)var22, var28 + 1.0D, (double)var35, (double)var39);
                worldRendererIn.addVertexWithUV(var24 + 1.0D, var26 + (double)var23, var28 + 0.0D, (double)var36, (double)var40);

                if (var5.func_176364_g(blockAccess, blockPosIn.up()))
                {
                    worldRendererIn.addVertexWithUV(var24 + 0.0D, var26 + (double)var20, var28 + 0.0D, (double)var33, (double)var37);
                    worldRendererIn.addVertexWithUV(var24 + 1.0D, var26 + (double)var23, var28 + 0.0D, (double)var36, (double)var40);
                    worldRendererIn.addVertexWithUV(var24 + 1.0D, var26 + (double)var22, var28 + 1.0D, (double)var35, (double)var39);
                    worldRendererIn.addVertexWithUV(var24 + 0.0D, var26 + (double)var21, var28 + 1.0D, (double)var34, (double)var38);
                }
            }

            if (var12)
            {
                worldRendererIn.setBrightness(var5.getMixedBrightnessForBlock(blockAccess, blockPosIn.down()));
                worldRendererIn.setColorOpaque_F(var15, var15, var15);
                var32 = var6[0].getMinU();
                var33 = var6[0].getMaxU();
                var34 = var6[0].getMinV();
                var35 = var6[0].getMaxV();
                worldRendererIn.addVertexWithUV(var24, var26, var28 + 1.0D, (double)var32, (double)var35);
                worldRendererIn.addVertexWithUV(var24, var26, var28, (double)var32, (double)var34);
                worldRendererIn.addVertexWithUV(var24 + 1.0D, var26, var28, (double)var33, (double)var34);
                worldRendererIn.addVertexWithUV(var24 + 1.0D, var26, var28 + 1.0D, (double)var33, (double)var35);
                var14 = true;
            }

            for (int var52 = 0; var52 < 4; ++var52)
            {
                int var53 = 0;
                int var54 = 0;

                if (var52 == 0)
                {
                    --var54;
                }

                if (var52 == 1)
                {
                    ++var54;
                }

                if (var52 == 2)
                {
                    --var53;
                }

                if (var52 == 3)
                {
                    ++var53;
                }

                BlockPos var55 = blockPosIn.add(var53, 0, var54);
                var31 = var6[1];

                if (var13[var52])
                {
                    double var44;
                    double var56;
                    double var57;
                    double var58;

                    if (var52 == 0)
                    {
                        var36 = var20;
                        var37 = var23;
                        var56 = var24;
                        var58 = var24 + 1.0D;
                        var57 = var28 + (double)var30;
                        var44 = var28 + (double)var30;
                    }
                    else if (var52 == 1)
                    {
                        var36 = var22;
                        var37 = var21;
                        var56 = var24 + 1.0D;
                        var58 = var24;
                        var57 = var28 + 1.0D - (double)var30;
                        var44 = var28 + 1.0D - (double)var30;
                    }
                    else if (var52 == 2)
                    {
                        var36 = var21;
                        var37 = var20;
                        var56 = var24 + (double)var30;
                        var58 = var24 + (double)var30;
                        var57 = var28 + 1.0D;
                        var44 = var28;
                    }
                    else
                    {
                        var36 = var23;
                        var37 = var22;
                        var56 = var24 + 1.0D - (double)var30;
                        var58 = var24 + 1.0D - (double)var30;
                        var57 = var28;
                        var44 = var28 + 1.0D;
                    }

                    var14 = true;
                    float var46 = var31.getInterpolatedU(0.0D);
                    float var47 = var31.getInterpolatedU(8.0D);
                    float var48 = var31.getInterpolatedV((double)((1.0F - var36) * 16.0F * 0.5F));
                    float var49 = var31.getInterpolatedV((double)((1.0F - var37) * 16.0F * 0.5F));
                    float var50 = var31.getInterpolatedV(8.0D);
                    worldRendererIn.setBrightness(var5.getMixedBrightnessForBlock(blockAccess, var55));
                    float var51 = 1.0F;
                    var51 *= var52 < 2 ? var17 : var18;
                    worldRendererIn.setColorOpaque_F(var16 * var51 * var8, var16 * var51 * var9, var16 * var51 * var10);
                    worldRendererIn.addVertexWithUV(var56, var26 + (double)var36, var57, (double)var46, (double)var48);
                    worldRendererIn.addVertexWithUV(var58, var26 + (double)var37, var44, (double)var47, (double)var49);
                    worldRendererIn.addVertexWithUV(var58, var26 + 0.0D, var44, (double)var47, (double)var50);
                    worldRendererIn.addVertexWithUV(var56, var26 + 0.0D, var57, (double)var46, (double)var50);
                    worldRendererIn.addVertexWithUV(var56, var26 + 0.0D, var57, (double)var46, (double)var50);
                    worldRendererIn.addVertexWithUV(var58, var26 + 0.0D, var44, (double)var47, (double)var50);
                    worldRendererIn.addVertexWithUV(var58, var26 + (double)var37, var44, (double)var47, (double)var49);
                    worldRendererIn.addVertexWithUV(var56, var26 + (double)var36, var57, (double)var46, (double)var48);
                }
            }

            return var14;
        }
    }

    private float getFluidHeight(IBlockAccess blockAccess, BlockPos blockPosIn, Material blockMaterial)
    {
        int var4 = 0;
        float var5 = 0.0F;

        for (int var6 = 0; var6 < 4; ++var6)
        {
            BlockPos var7 = blockPosIn.add(-(var6 & 1), 0, -(var6 >> 1 & 1));

            if (blockAccess.getBlockState(var7.up()).getBlock().getMaterial() == blockMaterial)
            {
                return 1.0F;
            }

            IBlockState var8 = blockAccess.getBlockState(var7);
            Material var9 = var8.getBlock().getMaterial();

            if (var9 == blockMaterial)
            {
                int var10 = ((Integer)var8.getValue(BlockLiquid.LEVEL)).intValue();

                if (var10 >= 8 || var10 == 0)
                {
                    var5 += BlockLiquid.getLiquidHeightPercent(var10) * 10.0F;
                    var4 += 10;
                }

                var5 += BlockLiquid.getLiquidHeightPercent(var10);
                ++var4;
            }
            else if (!var9.isSolid())
            {
                ++var5;
                ++var4;
            }
        }

        return 1.0F - var5 / (float)var4;
    }
}
