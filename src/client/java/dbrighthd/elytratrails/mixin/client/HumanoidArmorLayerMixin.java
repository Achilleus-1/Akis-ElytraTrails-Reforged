package dbrighthd.elytratrails.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import dbrighthd.elytratrails.rendering.FakeMultiBufferSource;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidArmorLayer.class)
public abstract class HumanoidArmorLayerMixin {
    @Inject(
            method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void elytratrails$skipArmorDuringHiddenPass(PoseStack poseStack, MultiBufferSource buffers,
            int packedLight, LivingEntity entity, float limbSwing, float limbSwingAmount,
            float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        // Custom armor renderers can bypass the supplied discard buffer and draw into the world.
        if (buffers == FakeMultiBufferSource.INSTANCE) {
            ci.cancel();
        }
    }
}
