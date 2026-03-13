package kz.hxncus.mc.dynamicshop.adapter;

import lombok.RequiredArgsConstructor;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.OfflinePlayer;

@RequiredArgsConstructor
public class VaultEconomyAdapter {

    private final Economy economy;

    private Economy economy() {
        if (economy == null) {
            throw new IllegalStateException("Vault economy is not available");
        }
        return economy;
    }

    public double getBalance(OfflinePlayer player) {
        return economy().getBalance(player);
    }

    public EconomyResponse deposit(OfflinePlayer player, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than 0");
        }

        return economy().depositPlayer(player, amount);
    }

    public EconomyResponse withdraw(OfflinePlayer player, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be greater than 0");
        }

        if (!economy().has(player, amount)) {
            return new EconomyResponse(
                    0,
                    economy().getBalance(player),
                    EconomyResponse.ResponseType.FAILURE,
                    "Player does not have enough money"
            );
        }

        return economy().withdrawPlayer(player, amount);
    }
}