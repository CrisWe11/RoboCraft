package work.sqybass.robocraft.datagen;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import work.sqybass.robocraft.setup.Registration;

import java.util.function.Consumer;

/**
 * @Author Bass
 * @Date 2021/10/4 19:15
 */
public class Recipes extends RecipeProvider {
    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        Logger logger = LogManager.getLogger();
        logger.error("Recipes executed!");
        ShapedRecipeBuilder.shapedRecipe(Registration.COMPUTATION_FURNACE.get())
                .patternLine("###")
                .patternLine("#*#")
                .patternLine("###")
                .key('#', Tags.Items.GEMS_DIAMOND)
                .key('*', Tags.Items.DUSTS_REDSTONE)
                .setGroup("robo_craft")
                .addCriterion("diamond", InventoryChangeTrigger.Instance.forItems(Items.DIAMOND))
                .build(consumer);
    }
}
