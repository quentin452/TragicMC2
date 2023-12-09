package tragicneko.tragicmc.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.mob.EntityIre;

public class ServerTickEvents {

    @SubscribeEvent
    public void onServerTick(ServerTickEvent event) {
        if (event.phase == Phase.START) {
            if (TragicConfig.allowIre) {
                EntityIre.ireTick++;
                if (EntityIre.ireTick >= 40) {
                    EntityIre.ireTick = 0;
                    EntityIre.ireNetSize = 0;
                }
            }
        }
    }
}
