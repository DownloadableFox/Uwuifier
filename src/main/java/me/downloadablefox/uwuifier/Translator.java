package me.downloadablefox.uwuifier;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Generate a singleton for the translator class
public class Translator {

    private static final Map<String, String> WordsDictionary = new HashMap<>();
    private static final Map<String, String> ExpressionDictionary = new HashMap<>();

    static {
        // Furry dictionary taken from https://lingojam.com/FurryTalk
        WordsDictionary.put("ahh", "murr");
        WordsDictionary.put("awesome", "pawsome");
        WordsDictionary.put("awful", "pawful");
        WordsDictionary.put("bite", "nom");
        WordsDictionary.put("bulge", "bulgy-wulgy");
        WordsDictionary.put("butthole", "tailhole");
        WordsDictionary.put("bye", "bai");
        WordsDictionary.put("celebrity", "popufur");
        WordsDictionary.put("cheese", "sergal");
        WordsDictionary.put("child", "cub");
        WordsDictionary.put("computer", "protogen");
        WordsDictionary.put("robot", "protogen");
        WordsDictionary.put("cyborg", "protogen");
        WordsDictionary.put("cum", "cummy wummy~");
        WordsDictionary.put("disease", "pathOwOgen");
        WordsDictionary.put("dog", "good boy");
        WordsDictionary.put("dragon", "derg");
        WordsDictionary.put("eat", "vore");
        WordsDictionary.put("fuck", "fluf");
        WordsDictionary.put("father", "daddy");
        WordsDictionary.put("foot", "footpaw");
        WordsDictionary.put("for", "fur");
        WordsDictionary.put("hand", "paw");
        WordsDictionary.put("hell", "hecc");
        WordsDictionary.put("hi", "hai");
        WordsDictionary.put("hyena", "yeen");
        WordsDictionary.put("kiss", "lick");
        WordsDictionary.put("lmao", "hehe~");
        WordsDictionary.put("love", "wuv");
        WordsDictionary.put("mouth", "maw");
        WordsDictionary.put("naughty", "knotty");
        WordsDictionary.put("not", "knot");
        WordsDictionary.put("perfect", "purrfect");
        WordsDictionary.put("persona", "fursona");
        WordsDictionary.put("pervert", "furvert");
        WordsDictionary.put("police", "pawlice");
        WordsDictionary.put("porn", "yiff");
        WordsDictionary.put("roar", "rawr");
        WordsDictionary.put("shout", "awoo");
        WordsDictionary.put("slut", "fox");
        WordsDictionary.put("source", "sauce");
        WordsDictionary.put("straight", "gay");
        WordsDictionary.put("tale", "tail");
        WordsDictionary.put("e621", "monosodium glutamate");
        WordsDictionary.put("the", "teh");
        WordsDictionary.put("this", "dis");
        WordsDictionary.put("toe", "toe bean");
        WordsDictionary.put("what", "wat");
        WordsDictionary.put("with", "wif");
        WordsDictionary.put("you", "chu");
        WordsDictionary.put("your", "ur");

        // Expressions
        ExpressionDictionary.put("!", "owo!");
        ExpressionDictionary.put("?", "uwu?");
        ExpressionDictionary.put(":)", ":3");
        ExpressionDictionary.put(":o", "OwO");
        ExpressionDictionary.put(":d", "UwU");
        ExpressionDictionary.put(":p", ">w<");
        ExpressionDictionary.put("xd", "x3");
    }

    public static String translate(final String text) {
        final String[] words = text.toLowerCase().split(" ");
        final List<String> translated = new ArrayList<>();

        for (final String word : words) {
            if (WordsDictionary.containsKey(word)) {
                translated.add(WordsDictionary.get(word));
                continue;
            }

            if (ExpressionDictionary.containsKey(word)) {
                translated.add(ExpressionDictionary.get(word));
                continue;
            }

            translated.add(
                    word
                        .replace("r", "w")
                        .replace("l", "w")
                        .replace("th", "d")
                        .replaceFirst(",$", "~")
                        .replaceFirst(";$", "~")
                        .replaceFirst("!$", " owo!")
                        .replaceFirst("\\?$", " uwu?")
                        //.replaceAll("o(?!$)", "u")
            );
        }

        return (text.startsWith(" ")) ? " " : "" + String.join(" ", translated) + (text.endsWith(" ") ? " " : "");
    }
}
