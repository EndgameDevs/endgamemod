package dev.endgame.block;

import dev.architectury.registry.menu.MenuRegistry;
import dev.endgame.block.entity.MixerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static dev.endgame.registry.BlockEntityRegistry.MIXER_BLOCK_ENTITY;

public class MixerBlock extends DirectionalBlock implements EntityBlock {

    public MixerBlock() {
        super(Properties.of(Material.METAL).noOcclusion());
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState blockState, Level level, @NotNull BlockPos blockPos,
                                          @NotNull Player player, @NotNull InteractionHand interactionHand, @NotNull BlockHitResult blockHitResult){
        if(!level.isClientSide()){
            BlockEntity blockEntity=level.getBlockEntity(blockPos);
            if(blockEntity instanceof MixerBlockEntity){
                MenuRegistry.openMenu((ServerPlayer) player, (MenuProvider) blockEntity);
            }else{
                throw new IllegalStateException("ERRORE");
            }
        }
        return InteractionResult.SUCCESS;
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return MIXER_BLOCK_ENTITY.get().create(blockPos, blockState);
    }

    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2, boolean bl) {
        if (blockState.getBlock() != blockState2.getBlock()) {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof MixerBlockEntity) {
                ((MixerBlockEntity) blockEntity).dropBlock();
            }
        }
        super.onRemove(blockState, level, blockPos, blockState2, bl);
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING, context.getNearestLookingDirection().getOpposite());
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return (l, p, s, be) -> ((MixerBlockEntity) be).tick(l,(MixerBlockEntity)be);

    }
}
