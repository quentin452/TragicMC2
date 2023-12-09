package tragicneko.tragicmc.entity.alpha;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.entity.boss.TragicBoss;

public class EntityAdmin extends TragicBoss {

    // Make sure that when it acquires a target that it tries to stay within ~40 blocks of it
    // This will make sure it's easier to keep track of as well as ensure that you can do certain phases of it's AI
    // without worrying about it's location

    // Stats
    // Health: 6000
    // Attack (normal hit): 16
    // Damage (normal-projectile): 4.5 armor-piercing
    // Damage (slow-moving projectile): 8 armor-piercing
    // Damage (annhilator beam): 100 armor-piercing, this might make it viable to use in a mob battle against other mobs
    // Speed: 0.223 (invulnerable mode), 0.375 (flying speed), 0.425 (ghost mode speed)
    // knockback resistance: 0 (can be knocked away and towards things)
    // Armor value: 24 (maximum value so that the strongest weapons are required)
    // Follow Range: 64

    // Description of AI:
    // Floats around near it's target and fires large slow moving projectiles that can pass through walls, these
    // projectiles will also inflict the Hacked effect
    // While invulnerable it only does this part of it's AI
    // If "damaged" enough it'll become stunned for a long period of time, this is to allow you to escape it for a while
    // to actually do stuff in the Dimension

    // When a spirit catcher is used on it, it becomes vulnerable
    // It'll then teleport away (despawn) and drop an Administrator passcode

    // When you enter the Dimension it'll already be vulnerable
    // While in this phase it'll spawn in control towers that shock you when you get near them randomly
    // You must "mine" these towers, causing them to overload at which point they'll make the Admin vulnerable for a
    // little while
    // You continue this until it's health is lowered, it's health will only stay lowered if you do enough damage during
    // a phase, otherwise you must redo it
    // This will take it's health down to half when completed, each tower phase will take 1/6th of it's total health and
    // it'll remove the towers during each
    // of these

    // When you successfully overload and damage it 3 times, it'll become separated from it's body module (which will
    // stay in place during this phase)
    // It will gain an instakill beam that has a long charge rate, it'll become fixed on one point and fire the beam
    // after a few seconds, instantly killing
    // anything within it
    // To damage it in this phase, you must use the towers it left behind to "shock" it with Directed Lightning, this
    // will need to be done until it's dropped
    // down to no health in which it goes into sleep mode, at certain phases in this pattern it'll go into recovery mode
    // and attempt to regenerate health,
    // it must be attacked normally at this time to prevent that
    // also at certain phases it'll create a singularity which will alter your active effects and change your current
    // health and hunger amounts, in other words,
    // it'll basically hack your stats (won't affect inventory and current equipped items but the effects will actually
    // occur

    // New block, it'll have three states, one active and one inactive similarly to how I originally wanted to do the
    // Digital Sea and one "broken"
    // The broken block state will signify that part of the tower has been destroyed, there will also be core states
    // core states will be in the middle of the tower, if an active block is not touching a core block it'll become
    // inactive

    // Sounds will be referencing Windows notifications ("Your computer is about to restart to finish installing
    // updates", "Powering down...", "Powering up.",
    // "That command does not exist", "File not found in database, would you like to continue? Y/N?", "No files match
    // search query", etc.)
    // "That file is corrupted, would you like to attempt recovery?", "Memory allocation is inadequate for this
    // operation.", "Doing this requires you to power off
    // your computer", "System32 has been restored, that was close."
    // Could have one set of male and one set of female-ish sounds (since I would be recording both), this could be
    // determined on spawn if male or female voice
    // Move to a "corrupt" set of the same sounds when in "ghost" form

    private ControlTower[] towers;
    private byte state = 0; // 0 is invincible, 1 is body module in tact, 2 is ghost form, 3 is powered off, 4 is
                            // restarted
    // "Resuming system-wide clean-up algorithms... Stand by."

    public EntityAdmin(World par1World) {
        super(par1World);
        this.towers = new ControlTower[8];
    }

    private void updateTowers() {
        for (ControlTower ct : towers) ct.update();
    }

    private void generateTowers() {
        // uses a modified version of the structure gen to find flat areas to generate towers
        // generates towers early in it's vulnerable stage
        // does it three times, the third time overloads it and fluxes it into an etheric state
        // towers[0] = new ControlTower(this, cand);
    }

    private void clearTowers() {
        for (ControlTower ct : towers) ct.kill();
        towers = new ControlTower[8];
    }

    @Override
    public void setAir(int i) {}

    @Override
    protected void despawnEntity() {}

    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    public void fall(float f) {}

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return TragicEntities.Synapse;
    }

    protected static class ControlTower {

        private final World worldObj;
        private final EntityAdmin admin;
        private final byte sizeH = 16;
        private final byte sizeW = 4;
        private final byte sizeL = 4;
        private byte state = 0; // 0 = inactive, 1 = active, 2 = overloaded
        private final int[] origin;
        private float activePer;
        private static final float LIMIT = 56.735F;

        protected ControlTower(EntityAdmin admin, int[] ori) {
            this.admin = admin;
            this.worldObj = admin.worldObj;
            this.origin = ori;
            this.activePer = 100F;
        }

        public boolean isOverloaded() {
            return state == 2;
        }

        public boolean isActive() {
            return state == 1;
        }

        public void update() {

            if (this.state == 0 && this.shouldActivate()) {
                this.state = 1;
            } else if (this.state == 1) {
                updateComposition();
                updateState();
                damageEntities();
            }
        }

        private boolean shouldActivate() {
            return this.activePer == 100F && this.admin.ticksExisted > 0; // change to activate at a certain phase of
                                                                          // it's ai
        }

        public void updateComposition() {
            // iterate through composition values
            // match with world mappings for those values (with origin offset) to determine if
            // majority of the blocks in the tower are still active
            this.activePer = 100F;
        }

        private void updateState() {
            if (this.activePer > LIMIT) this.state = 1;
            else this.state = 2;
        }

        private void kill() {
            // delete all of the blocks that make up the tower, for when the Admin is killed
        }

        private void damageEntities() {
            // damages all nearby entities with directed lightning
        }
    }
}
