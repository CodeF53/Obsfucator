package net.chatobfuscator.forge;

#if POST_MC_1_16_5
import dev.architectury.platform.forge.EventBuses;
#else
import me.shedaniel.architectury.platform.forge.EventBuses;
#endif
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("chatobfuscator")
public class ChatObfuscatorForge {
    public ChatObfuscatorForge() {
        EventBuses.registerModEventBus("chatobfuscator", FMLJavaModLoadingContext.get().getModEventBus());
    }
}
