package com.github.mcri.scroll;

import com.github.mcri.ModRegistry;
import com.github.mcri.effect.scroll.ScrollEffect;
import com.google.common.collect.ImmutableList;
import java.util.List;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class Scroll {
    @Nullable
    private final String baseName;
    private final ImmutableList<ScrollEffect> effects;

    public static Scroll byId(String id) {
        return (Scroll) ModRegistry.SCROLL.get(Identifier.tryParse(id));
    }

    public Scroll(ScrollEffect... effects) {
		this((String)null, effects);
	}

    public Scroll(@Nullable String baseName, ScrollEffect... effects) {
		this.baseName = baseName;
		this.effects = ImmutableList.copyOf((ScrollEffect[])effects);
	}

    public String finishTranslationKey(String prefix) {
        return prefix + (this.baseName == null ? ModRegistry.SCROLL.getId(this).getPath() : this.baseName);
    }

    public List<ScrollEffect> getEffects() {
        return this.effects;
    }
}
