package net.chatobfuscator.mixins;

import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.chatobfuscator.ChatObfuscator.obsfucateString;
import static net.minecraft.ChatFormatting.OBFUSCATED;

@Mixin(ChatComponent.class)
public abstract class CensorClientChat {
    #if PRE_CURRENT_MC_1_19
    @ModifyVariable(method = "addMessage(Lnet/minecraft/network/chat/Component;IIZ)V", at = @At(value = "HEAD"), ordinal = 0, argsOnly = true)
    #elif POST_MC_1_19
    @ModifyVariable(method = "addMessage(Lnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/MessageSignature;ILnet/minecraft/client/GuiMessageTag;Z)V", at = @At(value = "HEAD"), ordinal = 0, argsOnly = true)
    #endif
    private Component censorClientChat(Component component) {
        return component.copy().withStyle(OBFUSCATED);
    }

    #if PRE_CURRENT_MC_1_19
    @Redirect(method = "addMessage(Lnet/minecraft/network/chat/Component;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Component;getString()Ljava/lang/String;"))
    #elif POST_MC_1_19
    @Redirect(method = "logChatMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Component;getString()Ljava/lang/String;"))
    #endif
    private String censorLoggedChat(Component instance) {
        return obsfucateString(instance.getString());
    }
}