package work.sqybass.robocraft.utils;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.EnergyStorage;

/**
 * @Author Bass
 * @Date 2021/10/1 11:11
 */
public class MyEnergyStorage extends EnergyStorage implements INBTSerializable<CompoundNBT> {
    public MyEnergyStorage(int cap, int maxTransfer) {
        super(cap, maxTransfer);
    }

    protected void onEnergyChanged() {

    }

    public void setEnergy(int energy) {
        this.energy = energy;
        onEnergyChanged();
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT tag = new CompoundNBT();
        tag.putInt("energy", getEnergyStored());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        setEnergy(nbt.getInt("energy"));
    }

    /**
     * return false if energy is insufficient
     *
     * @param energy
     * @return
     */
    public boolean consumeEnergy(int energy) {
        if (this.energy == 0) {
            return false;
        }

        this.energy -= energy;
        if (this.energy <= 0) {
            this.energy = 0;
        }
        onEnergyChanged();
        return true;
    }

    /**
     * return false if energy reaches capacity
     *
     * @param energy
     * @return
     */
    public boolean addEnergy(int energy) {
        if (this.energy == this.capacity) {
            return false;
        }

        this.energy += energy;
        if (this.energy > this.capacity) {
            this.energy = this.capacity;
        }
        onEnergyChanged();
        return true;
    }
}
