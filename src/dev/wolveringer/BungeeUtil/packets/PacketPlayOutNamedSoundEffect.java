package dev.wolveringer.BungeeUtil.packets;

import dev.wolveringer.BungeeUtil.ClientVersion.BigClientVersion;
import dev.wolveringer.BungeeUtil.packets.Abstract.PacketPlayOut;
import dev.wolveringer.api.SoundEffect;
import dev.wolveringer.api.SoundEffect.SoundCategory;
import dev.wolveringer.api.position.Location;
import dev.wolveringer.packet.PacketDataSerializer;

//1.8 -> 0x29
public class PacketPlayOutNamedSoundEffect extends Packet implements PacketPlayOut{
	private float volume;
	private short pitch;
	private Location loc;
	private String sound;
	private int soundCategory;
	
	public PacketPlayOutNamedSoundEffect() {}

	@Override
	public void read(PacketDataSerializer s) {
		if(getBigVersion() == BigClientVersion.v1_9){
			sound = s.readString(-1);
			soundCategory = s.readVarInt();
			loc = new Location(s.readInt(), s.readInt(), s.readInt()).dividide(8D);
			volume = s.readFloat();
			pitch = s.readUnsignedByte();
		}
		else if(getBigVersion() == BigClientVersion.v1_8){
			sound = s.readString(-1);
			soundCategory = 0;
			loc = new Location(s.readInt(), s.readInt(), s.readInt()).dividide(8D);
			volume = s.readFloat();
			pitch = s.readUnsignedByte();
		}
	}
	
	@Override
	public void write(PacketDataSerializer s) {
		if(getBigVersion() == BigClientVersion.v1_9 || getBigVersion() == BigClientVersion.v1_8){
			s.writeString(sound);
			if(getBigVersion() == BigClientVersion.v1_9)
			s.writeVarInt(soundCategory);
			loc.multiply(8D);
			s.writeInt(loc.getBlockX());
			s.writeInt(loc.getBlockY());
			s.writeInt(loc.getBlockZ());
			loc.dividide(8D);
			s.writeFloat(volume);
			s.writeByte(pitch);
		}
	}
	
	public float getVolume() {
		return volume;
	}

	public void setVolume(float volume) {
		this.volume = volume;
	}

	public short getPitch() {
		return pitch;
	}

	public void setPitch(short pitch) {
		this.pitch = pitch;
	}

	public Location getLoc() {
		return loc;
	}

	public void setLoc(Location loc) {
		this.loc = loc;
	}

	

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public int getSoundCategory() {
		return soundCategory;
	}

	public void setSoundCategory(int soundCategory) {
		this.soundCategory = soundCategory;
	}

	@Override
	public String toString() {
		return "PacketPlayOutNamedSoundEffect [volume=" + volume + ", pitch=" + pitch + ", loc=" + loc + ", sound=" + sound + ", soundCategory=" + soundCategory + "]";
	}
	
}
