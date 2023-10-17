package me.downloadablefox.uwuifier.mixin;

import me.downloadablefox.uwuifier.Translator;
import me.downloadablefox.uwuifier.Uwuifier;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Text.class)
public interface TextTranslatorMixin {
    @Unique
    private static MutableText translatableWrapper(String key, @Nullable String fallback, Object[] args) {
        final String translated = MutableText
                .of(new TranslatableTextContent(key, fallback, args))
                .getString()
                .transform(Translator::translate);

        return MutableText.of(new LiteralTextContent(translated));
    }

    @Inject(at = @At("HEAD"), method = "literal(Ljava/lang/String;)Lnet/minecraft/text/MutableText;", cancellable = true)
    private static void literals(String string, CallbackInfoReturnable<MutableText> cir) {
        final String translated = Translator.translate(string);
        cir.setReturnValue(MutableText.of(new LiteralTextContent(translated)));
    }

    @Inject(at = @At("HEAD"), method = "translatable(Ljava/lang/String;)Lnet/minecraft/text/MutableText;", cancellable = true)
    private static void translatable(String key, @NotNull CallbackInfoReturnable<MutableText> cir) {
        cir.setReturnValue(translatableWrapper(key, null, TranslatableTextContent.EMPTY_ARGUMENTS));
    }

    @Inject(at = @At("HEAD"), method = "translatable(Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/text/MutableText;", cancellable = true)
    private static void translatable(String key, Object[] args, @NotNull CallbackInfoReturnable<MutableText> cir) {
        cir.setReturnValue(translatableWrapper(key, null, args));
    }

    @Inject(at = @At("HEAD"), method = "translatableWithFallback(Ljava/lang/String;Ljava/lang/String;)Lnet/minecraft/text/MutableText;", cancellable = true)
    private static void translatableWithFallback(String key, @Nullable String fallback, CallbackInfoReturnable<MutableText> cir) {
        cir.setReturnValue(translatableWrapper(key, fallback, TranslatableTextContent.EMPTY_ARGUMENTS));
    }

    @Inject(at = @At("HEAD"), method = "translatableWithFallback(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/text/MutableText;", cancellable = true)
    private static void translatableWithFallback(String key, @Nullable String fallback, Object[] args, CallbackInfoReturnable<MutableText> cir) {
        cir.setReturnValue(translatableWrapper(key, fallback, args));
    }
}
