package net.chatobfuscator.mixins;

import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import static net.chatobfuscator.ChatObfuscator.obsfucateString;

@Mixin(LocalPlayer.class)
public abstract class CensorOutgoingChat {
    #if PRE_MC_1_19
    @ModifyVariable(method = "chat", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    #elif POST_CURRENT_MC_1_19
    @ModifyVariable(method = "sendChat", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    #endif
    private String censorOutgoingChat(String string){
        return obsfucateString(string);
    }
}