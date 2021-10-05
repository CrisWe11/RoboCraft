package work.sqybass.robocraft.datagen;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import work.sqybass.robocraft.RoboCraft;
import work.sqybass.robocraft.setup.Registration;

import java.util.function.Function;

/**
 * @Author Bass
 * @Date 2021/10/4 18:58
 */
public class BlockStates extends BlockStateProvider {
    public BlockStates(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, RoboCraft.MODID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        Logger logger = LogManager.getLogger();
        logger.error("BlockStates executed!");
        registerComputationFurnace();
    }

    private void registerComputationFurnace() {
        ResourceLocation rl = new ResourceLocation(RoboCraft.MODID, "block/computation_furnace");
        BlockModelBuilder modelBuilder = models().cube("computation_furnace", rl, rl, new ResourceLocation(RoboCraft.MODID, "block/computation_furnace_front"), rl,rl,rl);
        BlockModelBuilder modelBuilderPowered = models().cube("computation_furnace_", rl, rl, new ResourceLocation(RoboCraft.MODID, "block/computation_furnace_powered"), rl,rl,rl);
        orientedBlock(Registration.COMPUTATION_FURNACE.get(), blockState -> {
            if(blockState.get(BlockStateProperties.POWERED))
                return modelBuilderPowered;
            else
                return modelBuilder;
        });
    }

    private void orientedBlock(Block block, Function<BlockState, ModelFile> modelFunc) {
        getVariantBuilder(block).forAllStates(state -> {
            Direction dir = state.get(BlockStateProperties.FACING);
            return ConfiguredModel.builder().modelFile(modelFunc.apply(state))
                    .rotationX(dir.getAxis() == Direction.Axis.Y ? dir.getAxisDirection().getOffset() * -90 : 0)
                    .rotationY(dir.getAxis() != Direction.Axis.Y ? ((dir.getHorizontalIndex() + 2) % 4) * 90 : 0).build();
        });
    }
}
