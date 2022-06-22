package me.downloadablefox.uwuifier.mixin;

import me.downloadablefox.uwuifier.Translator;
import me.downloadablefox.uwuifier.Uwuifier;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Text.class)
public interface TextTranslatorMixin {
    // Inject into the literal method

    @Inject(at = @At("HEAD"), method = "literal(Ljava/lang/String;)Lnet/minecraft/text/MutableText;", cancellable = true)
    private static void literals(String string, CallbackInfoReturnable<MutableText> cir) {
        final String translated = Translator.translate(string);
        cir.setReturnValue(MutableText.of(new LiteralTextContent(translated)));
    }

    @Inject(at = @At("HEAD"), method = "translatable(Ljava/lang/String;)Lnet/minecraft/text/MutableText;", cancellable = true)
    private static void translatable(String key, CallbackInfoReturnable<MutableText> cir) {
        final String text = MutableText.of(new TranslatableTextContent(key)).getString();
        final String translated = Translator.translate(text);
        cir.setReturnValue(MutableText.of(new LiteralTextContent(translated)));
    }
}
