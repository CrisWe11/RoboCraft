package work.sqybass.robocraft.setup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import work.sqybass.robocraft.RoboCraft;

/**
 * @Author Bass
 * @Date 2021/10/3 15:01
 */
@Mod.EventBusSubscriber(modid= RoboCraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {

    public static final ItemGroup ITEM_GROUP = new ItemGroup("robo_craft") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Registration.COMPUTATION_FURNACE.get());
        }
    };

    public static void init(final FMLCommonSetupEvent event){

    }
}
