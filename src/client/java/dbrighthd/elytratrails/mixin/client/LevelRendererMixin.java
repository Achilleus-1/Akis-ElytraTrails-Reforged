package dbrighthd.elytratrails.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import dbrighthd.elytratrails.rendering.FakeMultiBufferSource;
import dbrighthd.elytratrails.rendering.TrailManager;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import static dbrighthd.elytratrails.ElytraTrailsClient.getConfig;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererMixin {

    @Unique
    private boolean elytratrails$hiddenCameraEntityPass = false;

    @Unique
    private Entity elytratrails$cameraEntityThisFrame = null;

    @WrapOperation(
            method = "renderLevel",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/Camera;isDetached()Z"
            )
    )
    private boolean elytratrails$forceDetachedOnlyForHiddenCameraEntityPass(
            Camera camera,
            Operation<Boolean> original
    ) {
        boolean realDetached = original.call(camera);

        this.elytratrails$cameraEntityThisFrame = camera.getEntity();
        this.elytratrails$hiddenCameraEntityPass = false;

        var config = getConfig();
        if (config.forceHiddenEntityRenderPass && config.enableAllTrails && !realDetached
                && this.elytratrails$cameraEntityThisFrame instanceof Player player && !player.isSleeping()) {
            var trailConfig = TrailManager.getConfigFromPlayerId(player.getId());
            this.elytratrails$hiddenCameraEntityPass = trailConfig.enableTrail()
                    && TrailManager.isPlayerTrailValid(trailConfig, player);
        }

        // Reuse this decision below: an entity admitted here must use the discard buffer.
        return realDetached || this.elytratrails$hiddenCameraEntityPass;
    }

    @WrapOperation(
            method = "renderLevel",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/LevelRenderer;renderEntity(Lnet/minecraft/world/entity/Entity;DDDFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;)V"
            )
    )
    private void elytratrails$renderCameraEntityIntoNothingBufferOnlyWhenActuallyFirstPerson(LevelRenderer instance, Entity entity, double camX, double camY, double camZ, float partialTick, PoseStack poseStack, MultiBufferSource realBufferSource, Operation<Void> original
    ) {
        boolean shouldUseFakeBuffer = this.elytratrails$hiddenCameraEntityPass
                && entity == this.elytratrails$cameraEntityThisFrame;
        original.call(instance, entity, camX, camY, camZ, partialTick, poseStack, shouldUseFakeBuffer ? FakeMultiBufferSource.INSTANCE : realBufferSource
        );

    }
}
