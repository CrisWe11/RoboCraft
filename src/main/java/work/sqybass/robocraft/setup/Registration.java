package work.sqybass.robocraft.setup;

import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import work.sqybass.robocraft.RoboCraft;
import work.sqybass.robocraft.blocks.ComputationFurnace;
import work.sqybass.robocraft.blocks.ComputationFurnaceContainer;
import work.sqybass.robocraft.blocks.ComputationFurnaceTile;

/**
 * @Author Bass
 * @Date 2021/10/1 11:48
 */
public class Registration {

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RoboCraft.MODID);
    private static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, RoboCraft.MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RoboCraft.MODID);
    private static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, RoboCraft.MODID);

    public static void init(){
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<ComputationFurnace> COMPUTATION_FURNACE = BLOCKS.register("computation_furnace", ComputationFurnace::new);
    public static final RegistryObject<TileEntityType<ComputationFurnaceTile>> COMPUTATION_FURNACE_TILE = TILE_ENTITIES.register("computation_furnace_tile",() -> TileEntityType.Builder.create(ComputationFurnaceTile::new, COMPUTATION_FURNACE.get()).build(null));
    public static final RegistryObject<Item> COMPUTATION_FURNACE_ITEM = ITEMS.register("computation_furnace", () -> new BlockItem(COMPUTATION_FURNACE.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<ContainerType<ComputationFurnaceContainer>> COMPUTATION_FURNACE_CONTAINER = CONTAINERS.register("computation_furnace_container", () -> IForgeContainerType.create((windowId, inv, data)->{
        BlockPos pos = data.readBlockPos();
        World world = inv.player.getEntityWorld();
        return new ComputationFurnaceContainer(windowId, world, pos, inv, inv.player);
    }));
}
