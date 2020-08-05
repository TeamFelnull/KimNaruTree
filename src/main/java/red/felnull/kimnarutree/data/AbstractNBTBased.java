package red.felnull.kimnarutree.data;

import net.minecraft.nbt.CompoundNBT;

public abstract class AbstractNBTBased {
    protected String name;
    protected String dummy = "dummy";

    public AbstractNBTBased(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void init(){
        getParentNBT().put(name, getDefaultNBT());
    }

    public CompoundNBT getNBT(){
        return getParentNBT().getCompound(name);
    }

    public abstract CompoundNBT getParentNBT();

    public abstract CompoundNBT getDefaultNBT();

    public void setName(String name){
        if(getParentNBT().contains(name)){
            return;
        }
        getParentNBT().put(name, getNBT());
        getParentNBT().remove(this.name);
    }
}
