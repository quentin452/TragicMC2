package tragicneko.tragicmc.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

import tragicneko.tragicmc.entity.EntityPet;

public class ContainerPetStatus extends Container {

    private final EntityPet thePet;

    public ContainerPetStatus(EntityPet pet) {
        super();
        this.thePet = pet;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return false;
    }

}
