package dev.fabby.com.libraries.player.crates;

import dev.fabby.com.libraries.player.FabbyPlayer;
import dev.fabby.com.libraries.player.crates.callback.KeySource;

public record Key(long obtainedAt, KeySource source, String crateName, FabbyPlayer obtainedBy) {
}
