package work.sqybass.robocraft.blocks;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import work.sqybass.robocraft.RoboCraft;

/**
 * @Author Bass
 * @Date 2021/10/2 12:24
 */
public class ComputationFurnaceScreen extends ContainerScreen<ComputationFurnaceContainer> {

    private ResourceLocation GUI = new ResourceLocation(RoboCraft.MODID, "textures/gui/computation_furnace_gui.png");

    public ComputationFurnaceScreen(ComputationFurnaceContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    /**
     * render widgets like buttons, strings etc.
     * @param mouseX
     * @param mouseY
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        // fontRenderer, text, xpos, ypos, color
        drawString(Minecraft.getInstance().fontRenderer, "Energy: " + container.getEnergy(), 10, 10, 0xffffff);
    }

    /**
     * draw background image
     * @param partialTicks
     * @param mouseX
     * @param mouseY
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(GUI);
        // horizontally middle
        int relX = (this.width - this.xSize) / 2;
        // vertically middle
        int relY = (this.height - this.ySize) / 2;
        this.blit(relX, relY, 0, 0, this.xSize, this.ySize);
    }


}
