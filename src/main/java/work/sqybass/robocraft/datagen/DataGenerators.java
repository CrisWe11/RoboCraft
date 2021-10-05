package work.sqybass.robocraft.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @Author Bass
 * @Date 2021/10/4 18:59
 */

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        Logger logger = LogManager.getLogger();
        logger.error("gatherData() executed!");
        DataGenerator generator = event.getGenerator();
        if(event.includeServer()){
            logger.error("includeServer executed!");
            generator.addProvider(new Recipes(generator));
        }
        if (event.includeClient()){
            logger.error("includeClient executed!");
            generator.addProvider(new BlockStates(generator, event.getExistingFileHelper()));
        }

    }
}
