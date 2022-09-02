package com.github.mcri.Mixins.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import net.minecraft.client.gui.screen.ingame.ForgingScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Mixin(AnvilScreen.class)
public abstract class AnvilScreenRemoveMaxLevelMixin extends ForgingScreen<AnvilScreenHandler> {
    AnvilScreenRemoveMaxLevelMixin(AnvilScreenHandler handler, PlayerInventory playerInventory, Text title, Identifier texture) {
        super(handler, playerInventory, title, texture);
    }

    @Accessor
    abstract PlayerEntity getPlayer();

    @Inject(method = { "drawForeground" }, at = @At("HEAD"), cancellable = true)
    protected void drawForegroundNoMax(MatrixStack matrices, int mouseX, int mouseY, CallbackInfo cir) {
        RenderSystem.disableBlend();
        super.drawForeground(matrices, mouseX, mouseY);
        int levelCost = ((AnvilScreenHandler) this.handler).getLevelCost();
        if (levelCost > 0) {
            int color = 0x80FF20;
            Object text;
            if (!((AnvilScreenHandler) this.handler).getSlot(2).hasStack()) {
                text = null;
            }
            else {
                text = Text.translatable("container.repair.cost", levelCost);
                if (!((AnvilScreenHandler) this.handler).getSlot(2).canTakeItems(this.getPlayer())) {
                    color = 0xFF6060;
                }
            }

            if (text != null) {
                int x = this.backgroundWidth - 8 - this.textRenderer.getWidth((StringVisitable) text) - 2;
                fill(matrices, x - 2, 67, this.backgroundWidth - 8, 79, 1325400064);
                this.textRenderer.drawWithShadow(matrices, (Text) text, (float) x, 69.0F, color);
            }
        }
        cir.cancel();
    }
}
