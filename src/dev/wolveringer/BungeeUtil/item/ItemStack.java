package dev.wolveringer.BungeeUtil.item;

import dev.wolveringer.BungeeUtil.Material;
import dev.wolveringer.BungeeUtil.Player;
import dev.wolveringer.api.inventory.Inventory;

public abstract class ItemStack extends Item {

	public ItemStack(Item stack) throws IllegalArgumentException {
		super(stack);
	}

	public ItemStack(Material type) {
		super(type);
	}

	public ItemStack(Material type, int amount) {
		super(type, amount);
	}

	public ItemStack(Material type, int amount, short damage) {
		super(type, amount, damage);
	}

	@SuppressWarnings("deprecation")
	public ItemStack(int type, int amount, short damage) {
		super(type, amount, damage);
	}

	public abstract void click(Click click);

	public static class Click {
		private Player player;
		private int slot;
		private Inventory inventory;
		private int mode;
		private boolean cancel = true;
		private Item item;
		public Click(Player p, int slot, Inventory inv,Item ci, int mode) {
			this.player = p;
			this.slot = slot;
			this.inventory = inv;
			this.mode = mode;
			this.item = ci;
		}

		public Player getPlayer() {
			return player;
		}

		public int getSlot() {
			return slot;
		}

		public Inventory getInventory() {
			return inventory;
		}

		public int getMode() {
			return mode;
		}
		
		public Item getItem() {
			return item;
		}
		
		@Deprecated
		public void setCancelled(boolean b) {
			this.cancel = b;
		}

		@Deprecated
		public boolean isCancelled() {
			return cancel;
		}
	}
}