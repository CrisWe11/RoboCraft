package work.sqybass.robocraft.setup;

import net.minecraftforge.fml.common.Mod;

/**
 * @Author Bass
 * @Date 2021/10/1 11:30
 */
@Mod.EventBusSubscriber
public class Config {
    public static Integer computationFurnaceMaxTransfer = 200;
    public static Integer computationFurnaceCapacity = 10000;
    public static Integer computationFurnaceWithRedstone = 500;
    public static Integer computationFurnaceTicks = 20*20;
}
