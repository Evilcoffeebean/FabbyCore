package dev.fabby.com.libraries.player.economy;

import dev.fabby.com.libraries.player.FabbyPlayer;
import org.bukkit.Bukkit;

import java.util.List;
import java.util.UUID;

public class TransactionToken {


    private static List<TransactionToken> recentTransactions = List.of();
    private long occuredOn;
    private FabbyPlayer sender;
    private FabbyPlayer receiver;

    private UUID senderUuid;
    private String senderName;
    private UUID receiverUuid;
    private String receiverName;
    private int amount;


    public static TransactionToken get(UUID sender, UUID receiver) {
        return recentTransactions.stream().filter(token -> token.getSenderUuid().equals(sender) && token.getReceiverUuid().equals(receiver)).findFirst().orElse(null);
    }


    public TransactionToken(FabbyPlayer sender, FabbyPlayer receiver, int amount) {
        this.occuredOn = System.currentTimeMillis();
        this.sender = sender;
        this.receiver = receiver;
        this.senderUuid = sender.getUuid();
        this.receiverUuid = receiver.getUuid();
        this.senderName = sender.getName();
        this.receiverName = receiver.getName();
        this.amount = amount;
    }

    public FabbyPlayer getSender() {
        return sender;
    }

    public FabbyPlayer getReceiver() {
        return receiver;
    }

    public UUID getSenderUuid() {
        return senderUuid;
    }

    public UUID getReceiverUuid() {
        return receiverUuid;
    }

    public int getAmount() {
        return amount;
    }
}
