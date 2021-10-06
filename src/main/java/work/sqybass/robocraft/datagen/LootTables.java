package work.sqybass.robocraft.datagen;

import net.minecraft.data.DataGenerator;
import work.sqybass.robocraft.setup.Registration;

/**
 * @Author Bass
 * @Date 2021/10/5 20:55
 */
public class LootTables extends BaseLootTableProvider{
    public LootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    /**
     * need to add data into lootTables
     */
    @Override
    protected void addTables() {
        lootTables.put(Registration.COMPUTATION_FURNACE.get(), createStandardTable("computation_furnace", Registration.COMPUTATION_FURNACE.get()));
    }
}
