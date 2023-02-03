package dev.endgame.block;

import dev.endgame.Endgame;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class MixerMenuScreenHandler extends AbstractContainerMenu {
    private final Container inventory;
    private final ContainerData containerData;

   public MixerMenuScreenHandler(int syncId, Inventory inventory) {
        this(syncId, inventory, new SimpleContainer(3), new SimpleContainerData(2));
    }

    public MixerMenuScreenHandler(int syncId, @NotNull Inventory playerInventory, Container inventory, ContainerData containerData) {
        super(Endgame.MIXER_SCREEN_HANDLER, syncId);
        checkContainerSize(inventory, 2);
        this.inventory = inventory;
        this.containerData=containerData;
        inventory.startOpen(playerInventory.player);
        this.addSlot(new Slot(inventory, 0, 18, 24));
        this.addSlot(new Slot(inventory, 1, 143, 32));

        inventory(playerInventory);
        hotbar(playerInventory);
        addDataSlots(containerData);
    }
    public boolean checkCrafting() {
        return containerData.get(0) > 0;
    }
    public int progressScaled() {
        int status =containerData.get(0);
        int max = containerData.get(1);
        int width = 18;
        return max != 0 && status != 0 ? status * width / max : 0;
    }
    private void inventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void hotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 143
            ));
        }
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int i) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(i);
        if (slot.hasItem()) {
            ItemStack originalStack = slot.getItem();
            newStack = originalStack.copy();
            if (i < this.inventory.getContainerSize()) {
                if (!this.moveItemStackTo(originalStack, this.inventory.getContainerSize(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(originalStack, 0, this.inventory.getContainerSize(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return newStack;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return true;
    }
}
