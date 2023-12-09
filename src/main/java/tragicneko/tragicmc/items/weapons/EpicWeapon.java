package tragicneko.tragicmc.items.weapons;

import net.minecraftforge.common.util.EnumHelper;
import tragicneko.tragicmc.doomsday.Doomsday;

public class EpicWeapon extends TragicWeapon {

    private static final ToolMaterial toolEpic = EnumHelper.addToolMaterial("EPIC", 3, 750, 16.0F, 16.0F, 16);

    public EpicWeapon(Doomsday dday) {
        super(toolEpic, dday);
    }
}
