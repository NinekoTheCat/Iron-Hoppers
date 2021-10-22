package ninekothecat.ironhoppersmod.utils;

import net.minecraft.block.BlockState;
import net.minecraft.block.HopperBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.Hopper;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class GenericHopper extends LootableContainerBlockEntity implements Tickable, Hopper, Inventory {
    private DefaultedList<ItemStack> inventory;
    private IHopperScreenHandlerFactory screenHandlerFactory;
    public GenericHopper(BlockEntityType blockEntityType, DefaultedList<ItemStack> inventory) {
        super(blockEntityType);
        this.inventory = inventory;
    }

    public void setScreenHandlerFactory(IHopperScreenHandlerFactory screenHandlerFactory) {
        this.screenHandlerFactory = screenHandlerFactory;
    }

    @Override
    public void fromTag(BlockState state, NbtCompound tag) {
        super.fromTag(state, tag);
        this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        if (!this.deserializeLootTable(tag)) {
            Inventories.readNbt(tag, this.inventory);
        }
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (!this.serializeLootTable(nbt)) {
            Inventories.writeNbt(nbt, this.inventory);
        }
        return nbt;
    }

    @Override
    public int size() {
        return this.inventory.size();
    }

    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return this.inventory;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> list) {
        this.inventory = list;
    }

    @Override
    protected Text getContainerName() {
        return this.screenHandlerFactory.getHopperName();
    }

    @Nullable
    @Override
    public World getWorld() {
        return this.world;
    }

    @Override
    public void tick() {
        if (this.world != null && !this.world.isClient) {
            this.insertAndExtract(() -> HopperBlockEntity.extract(this));
        }
    }

    @Override
    public double getHopperX() {
        return (double) this.pos.getX() + 0.5D;
    }

    @Override
    public double getHopperY() {
        return (double) this.pos.getY() + 0.5D;
    }

    @Override
    public double getHopperZ() {
        return (double) this.pos.getZ() + 0.5D;
    }

    private void insertAndExtract(Supplier<Boolean> extractMethod) {
        if (this.world != null && !this.world.isClient) {
            boolean OperationDone = false;
            if (!this.isEmpty()) {
                OperationDone = this.insert();
            }

            if (!this.isFull()) {
                OperationDone |= extractMethod.get();
            }

            if (OperationDone) {
                this.markDirty();
            }

        }
    }

    private boolean isFull() {
        Iterator<ItemStack> var1 = this.inventory.iterator();

        ItemStack itemStack;
        do {
            if (!var1.hasNext()) {
                return true;
            }
            itemStack = var1.next();
        } while (!itemStack.isEmpty() && itemStack.getCount() == itemStack.getMaxCount());

        return false;
    }

    private boolean insert() {
        Inventory inventory = this.getOutputInventory();
        if (inventory != null) {
            Direction direction = this.getCachedState().get(HopperBlock.FACING).getOpposite();
            if (!this.isInventoryFull(inventory, direction)) {
                for (int i = 0; i < this.size(); ++i) {
                    if (!this.getStack(i).isEmpty()) {
                        ItemStack itemStack = this.getStack(i).copy();
                        ItemStack itemStack2 = HopperBlockEntity.transfer(this, inventory, this.removeStack(i, 1), direction);
                        if (itemStack2.isEmpty()) {
                            inventory.markDirty();
                            return true;
                        }

                        this.setStack(i, itemStack);
                    }
                }

            }
        }
        return false;
    }

    private boolean isInventoryFull(Inventory direction, Direction direction2) {
        return getAvailableSlots(direction, direction2).allMatch((i) -> {
            ItemStack itemStack = direction.getStack(i);
            return itemStack.getCount() >= itemStack.getMaxCount();
        });
    }
    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return screenHandlerFactory.createScreenHandler(syncId,playerInventory);
    }

    private static IntStream getAvailableSlots(Inventory inventory, Direction side) {
        return inventory instanceof SidedInventory ? IntStream.of(((SidedInventory) inventory).getAvailableSlots(side)) : IntStream.range(0, inventory.size());
    }

    @Nullable
    private Inventory getOutputInventory() {
        Direction direction = this.getCachedState().get(HopperBlock.FACING);
        assert this.getWorld() != null;
        return HopperBlockEntity.getInventoryAt(this.getWorld(), this.pos.offset(direction));
    }
}
