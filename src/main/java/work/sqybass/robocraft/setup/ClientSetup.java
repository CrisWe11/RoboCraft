package work.sqybass.robocraft.setup;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import work.sqybass.robocraft.RoboCraft;
import work.sqybass.robocraft.blocks.ComputationFurnaceScreen;

/**
 * @Author Bass
 * @Date 2021/10/2 19:52
 */
@Mod.EventBusSubscriber(modid = RoboCraft.MODID, value= Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(final FMLClientSetupEvent event){
        ScreenManager.registerFactory(Registration.COMPUTATION_FURNACE_CONTAINER.get(), ComputationFurnaceScreen::new);
    }
}
