package tragicneko.tragicmc.client;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.MusicTicker.MusicType;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.client.event.sound.PlaySoundEvent17;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.dimension.SynapseWorldProvider;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCore;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class TragicMusicTicker implements IUpdatePlayerListBox {

	private final Minecraft mc;
	private Random rand = new Random();

	private ISound currentTrack;
	private int buffer = 100;

	public static TragicMusic collisionTrack = new TragicMusic(new ResourceLocation("tragicmc:music.dimension.bog"), 200, 800);
	public static TragicMusic collisionCreative = new TragicMusic(new ResourceLocation("tragicmc:music.dimension.catacombs"), 1200, 3600);
	public static TragicMusic synapseTrack = new TragicMusic(new ResourceLocation("tragicmc:music.dimension.sanctuary"), 200, 600);
	public static TragicMusic synapseOverlord = new TragicMusic(new ResourceLocation("tragicmc:music.dimension.prime"), 0, 0);

	public TragicMusicTicker(Minecraft mc)
	{
		this.mc = mc;
	}

	@Override
	public void update() {
		MusicType musictype = mc.func_147109_W();
		if (mc.theWorld != null)
		{
			WorldProvider prov = mc.theWorld.provider;
			TragicMusic music = collisionTrack; 

			if (prov instanceof TragicWorldProvider)
			{
				music = musictype == MusicType.GAME ? collisionTrack : collisionCreative;
			}
			else if (prov instanceof SynapseWorldProvider)
			{
				music = mc.thePlayer != null && !mc.theWorld.getEntitiesWithinAABB(EntityOverlordCore.class, mc.thePlayer.boundingBox.expand(120, 128, 120)).isEmpty() ? synapseOverlord : (musictype == MusicType.GAME ? synapseTrack : collisionCreative);
			}
			else
			{
				return;
			}

			if (this.currentTrack != null)
			{					
				if (!this.currentTrack.getPositionedSoundLocation().equals(music.loc))
				{
					this.mc.getSoundHandler().stopSound(this.currentTrack);
					this.buffer = MathHelper.getRandomIntegerInRange(this.rand, 0, music.min / 2);
				}

				if (!this.mc.getSoundHandler().isSoundPlaying(this.currentTrack))
				{
					this.currentTrack = null;
					this.buffer = Math.min(MathHelper.getRandomIntegerInRange(this.rand, music.min, music.max), this.buffer);
				}
			}

			if (this.currentTrack == null && this.buffer-- <= 0)
			{
				this.currentTrack = PositionedSoundRecord.func_147673_a(music.loc);
				this.mc.getSoundHandler().playSound(this.currentTrack);
				this.buffer = Integer.MAX_VALUE;
				TragicMC.logInfo("Track was set to play.");
			}
		}
	}

	@SubscribeEvent
	public void blah(PlaySoundEvent17 event)
	{
		MusicType musictype = mc.func_147109_W();

		if (event.sound.getPositionedSoundLocation().equals(musictype.getMusicTickerLocation()))
		{
			if (mc.theWorld != null && (mc.theWorld.provider instanceof TragicWorldProvider || mc.theWorld.provider instanceof SynapseWorldProvider))
			{
				event.result = null;
			}
		}
	}

	public static class TragicMusic {
		public final ResourceLocation loc;
		public final int min;
		public final int max;

		public TragicMusic(ResourceLocation loc, int minBuf, int maxBuf)
		{
			this.loc = loc;
			this.min = minBuf;
			this.max = maxBuf;
		}
	}

}
