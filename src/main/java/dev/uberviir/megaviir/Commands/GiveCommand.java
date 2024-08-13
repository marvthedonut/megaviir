package dev.uberviir.megaviir.Commands;

import net.minestom.server.MinecraftServer;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.event.EventNode;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.listener.manager.PacketListenerManager;
import net.minestom.server.network.packet.server.play.ChunkBatchStartPacket;

public class GiveCommand extends Command {
    public GiveCommand() {
        super("give");
        MinecraftServer.getCommandManager().register(this);

        setDefaultExecutor((sender, ctx) -> {
            sender.sendMessage("§cNo provided item§r");
        });

        String[] materials = new String[Material.values().size()];

        Material.values().forEach(material -> materials[material.id()] = material.namespace().namespace());

        var arg1 = ArgumentType.Word("items").from(materials);


        addSyntax((sender, context) -> {
//            final String item = context.get(arg);
//            sender.sendMessage(item);
        }, arg1);
    }
}
