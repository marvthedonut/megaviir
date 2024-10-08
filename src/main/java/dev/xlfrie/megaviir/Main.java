package dev.xlfrie.megaviir;

import dev.xlfrie.megaviir.BlockHandlers.SkullBlockHandler;
import dev.xlfrie.megaviir.Commands.GiveCommand;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.LightingChunk;
import net.minestom.server.instance.anvil.AnvilLoader;
import net.minestom.server.utils.NamespaceID;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        MinecraftServer minecraftServer = MinecraftServer.init();

        InstanceManager instanceManager = MinecraftServer.getInstanceManager();
        InstanceContainer instanceContainer = instanceManager.createInstanceContainer(new AnvilLoader(Path.of("worlds/SBHub")));

        instanceContainer.setChunkSupplier(LightingChunk::new);

        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        globalEventHandler.addListener(AsyncPlayerConfigurationEvent.class, event -> {
            final Player player = event.getPlayer();

            event.setSpawningInstance(instanceContainer);
            player.setRespawnPoint(new Pos(-2.5, 70, -68.5, 180, 0));

            player.setGameMode(GameMode.CREATIVE);
        });

        MinecraftServer.getBlockManager().registerHandler(NamespaceID.from("minecraft:skull"), SkullBlockHandler::new);

        new GiveCommand();

        MojangAuth.init();
        minecraftServer.start("192.168.50.34", 25565);
    }
}