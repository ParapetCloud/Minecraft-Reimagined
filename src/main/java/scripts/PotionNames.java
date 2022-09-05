package scripts;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.github.mcri.potion.ModPotions;
import com.github.mcri.utils.PotionName;
import com.google.gson.JsonObject;
import com.google.gson.JsonStreamParser;
import com.google.gson.stream.JsonWriter;

import net.minecraft.potion.Potion;

public final class PotionNames {
    public static void main(String[] args) throws Exception {
        String fileName = "./src/main/resources/assets/mcri/lang/en_us.json";

        System.out.println(System.getProperty("user.dir"));
        FileReader reader = new FileReader(fileName);
        JsonStreamParser parser = new JsonStreamParser(reader);

        JsonObject textArr = parser.next().getAsJsonObject();
        Set<String> keyset = textArr.deepCopy().keySet();
        Class<ModPotions> modPotions = ModPotions.class;
        List<Field> potions = Arrays.asList(modPotions.getFields()).stream()
            .filter(field -> Potion.class.isAssignableFrom(field.getType()) && field.getAnnotation(PotionName.class) != null)
            .toList();

        List<String> potionIds = potions.stream()
            .map(field -> field.getName().toLowerCase())
            .toList();

        // remove all the old ones if any are gone
        for (Iterator<String> iter = keyset.iterator(); iter.hasNext();) {
            String key = iter.next();

            if (key.startsWith("item.minecraft.potion.effect.") ||
                key.startsWith("item.minecraft.splash_potion.effect.") ||
                key.startsWith("item.minecraft.lingering_potion.effect.") ||
                key.startsWith("item.minecraft.tipped_arrow.effect.")) {

                String[] parts = key.split("\\.");
                String id = parts[parts.length - 1];
                if (!potionIds.stream().anyMatch(pId -> pId.equals(id))) {
                    textArr.remove(key);
                }
            }
        }

        for (Iterator<Field> iter = potions.iterator(); iter.hasNext();) {
            Field field = iter.next();
            String id = field.getName().toLowerCase();
            String name = field.getAnnotation(PotionName.class).name();

            if (keyset.stream().anyMatch(key -> key.equals("item.minecraft.potion.effect." + id))) {
                continue;
            }

            textArr.addProperty("item.minecraft.potion.effect." + id, "Potion of " + name);
            textArr.addProperty("item.minecraft.splash_potion.effect." + id, "Splash Potion of " + name);
            textArr.addProperty("item.minecraft.lingering_potion.effect." + id, "Lingering Potion of " + name);
            textArr.addProperty("item.minecraft.tipped_arrow.effect." + id, "Arrow of " + name);
        }
        
        keyset = textArr.keySet();

        reader.close();

        FileWriter fileWriter = new FileWriter(fileName);
        JsonWriter writer = new JsonWriter(fileWriter);
        writer.setIndent("    ");

        writer.beginObject();
        for (Iterator<String> iter = keyset.iterator(); iter.hasNext();) {
            String key = iter.next();
            writer.name(key);
            writer.value(textArr.get(key).getAsString());
        }
        writer.endObject();
        writer.close();
    }
}
