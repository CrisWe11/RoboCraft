package work.sqybass.robocraft.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import work.sqybass.robocraft.setup.Config;
import work.sqybass.robocraft.setup.Registration;
import work.sqybass.robocraft.utils.MyEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @Author Bass
 * @Date 2021/9/30 21:06
 */
public class ComputationFurnaceTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {
    private ItemStackHandler itemStackHandler = createHandler();
    private MyEnergyStorage energyStorage = createEnergy();

    private LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemStackHandler);
    private LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    private int counter;

    public ComputationFurnaceTile() {
        super(Registration.COMPUTATION_FURNACE_TILE.get());
    }

    @Override
    public void read(CompoundNBT compound) {
        itemStackHandler.deserializeNBT(compound.getCompound("inv"));
        energyStorage.deserializeNBT(compound.getCompound("energy"));

        counter = compound.getInt("counter");
        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt("counter", counter);
        compound.put("inv", itemStackHandler.serializeNBT());
        compound.put("energy", energyStorage.serializeNBT());

        return super.write(compound);
    }

    private ItemStackHandler createHandler() {
        // the number 1 stands for how many slots you will need
        return new ItemStackHandler(1){

            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(!isItemValid(slot,stack)){
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return stack.getItem() == Items.REDSTONE;
            }
        };
    }

    private MyEnergyStorage createEnergy(){
        return new MyEnergyStorage(Config.computationFurnaceCapacity, Config.computationFurnaceMaxTransfer){
            @Override
            protected void onEnergyChanged() {
                markDirty();
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap.equals(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)){
            return this.handler.cast();
        }

        if(cap.equals(CapabilityEnergy.ENERGY)) {
            return this.energy.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void tick() {
        if(world.isRemote)
            return;

        if(counter <= 0 && itemStackHandler.getStackInSlot(0).getItem().equals(Items.REDSTONE)){
            itemStackHandler.extractItem(0,1,false);
            counter = Config.computationFurnaceTicks;
            markDirty();
        }

        if(counter >0){
            if(--counter <= 0){
                energyStorage.addEnergy(Config.computationFurnaceWithRedstone);
            }
            markDirty();
        }

        BlockState blockState = world.getBlockState(pos);
        if(blockState.get(BlockStateProperties.POWERED) != counter > 0){
            world.setBlockState(pos, blockState.with(BlockStateProperties.POWERED, counter>0), Constants.BlockFlags.NOTIFY_NEIGHBORS+Constants.BlockFlags.BLOCK_UPDATE);
        }

        transferEnergy();
    }

    private void transferEnergy(){
        if(energyStorage.getEnergyStored()<=0) return;
        TileEntity te = world.getTileEntity(pos);
        if(te==null) return;

        for (Direction d : Direction.values()){
            te.getCapability(CapabilityEnergy.ENERGY, d).ifPresent(handler->{
                if(!handler.canReceive()) return;
                int transferred = handler.receiveEnergy(Math.min(Config.computationFurnaceMaxTransfer, energyStorage.getEnergyStored()), false);
                energyStorage.consumeEnergy(transferred);
                markDirty();
            });
        }
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("screen.computation_furnace");
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory , PlayerEntity player) {
        return new ComputationFurnaceContainer(i,world,pos,playerInventory,player);
    }
}
