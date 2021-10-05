package work.sqybass.robocraft;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import work.sqybass.robocraft.setup.ClientSetup;
import work.sqybass.robocraft.setup.Config;
import work.sqybass.robocraft.setup.ModSetup;
import work.sqybass.robocraft.setup.Registration;


/**
 * @Author Bass
 * @Date 2021/9/30 20:41
 */
@Mod(RoboCraft.MODID)
public class RoboCraft {
    public static final String MODID = "robo_craft";

    private static final Logger logger = LogManager.getLogger();

    public RoboCraft() {
//        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT);
//        FMLJavaModLoadingContext.get().getModEventBus().addListener();
        Registration.init();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModSetup::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
    }
}
