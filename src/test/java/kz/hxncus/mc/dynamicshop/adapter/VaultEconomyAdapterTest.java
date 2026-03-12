package kz.hxncus.mc.dynamicshop.adapter;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.OfflinePlayer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VaultEconomyAdapterTest {

    @Test
    void getBalance_returnsPlayerBalance() {
        Economy economy = mock(Economy.class);
        OfflinePlayer player = mock(OfflinePlayer.class);

        when(economy.getBalance(player)).thenReturn(150.0);

        VaultEconomyAdapter service = new VaultEconomyAdapter(economy);

        assertEquals(150.0, service.getBalance(player));
    }

    @Test
    void deposit_callsVaultDeposit() {
        Economy economy = mock(Economy.class);
        OfflinePlayer player = mock(OfflinePlayer.class);

        EconomyResponse response = new EconomyResponse(
                50.0,
                200.0,
                EconomyResponse.ResponseType.SUCCESS,
                null
        );

        when(economy.depositPlayer(player, 50.0)).thenReturn(response);

        VaultEconomyAdapter service = new VaultEconomyAdapter(economy);
        EconomyResponse result = service.deposit(player, 50.0);

        assertTrue(result.transactionSuccess());
        assertEquals(200.0, result.balance);
        verify(economy).depositPlayer(player, 50.0);
    }

    @Test
    void withdraw_callsVaultWithdraw_whenPlayerHasEnoughMoney() {
        Economy economy = mock(Economy.class);
        OfflinePlayer player = mock(OfflinePlayer.class);

        EconomyResponse response = new EconomyResponse(
                100.0,
                50.0,
                EconomyResponse.ResponseType.SUCCESS,
                null
        );

        when(economy.has(player, 100.0)).thenReturn(true);
        when(economy.withdrawPlayer(player, 100.0)).thenReturn(response);

        VaultEconomyAdapter service = new VaultEconomyAdapter(economy);
        EconomyResponse result = service.withdraw(player, 100.0);

        assertTrue(result.transactionSuccess());
        assertEquals(50.0, result.balance);
        verify(economy).withdrawPlayer(player, 100.0);
    }

    @Test
    void withdraw_failsWhenNotEnoughMoney() {
        Economy economy = mock(Economy.class);
        OfflinePlayer player = mock(OfflinePlayer.class);

        when(economy.has(player, 100.0)).thenReturn(false);
        when(economy.getBalance(player)).thenReturn(20.0);

        VaultEconomyAdapter service = new VaultEconomyAdapter(economy);
        EconomyResponse result = service.withdraw(player, 100.0);

        assertFalse(result.transactionSuccess());
        assertEquals(20.0, result.balance);
        assertEquals("Player does not have enough money", result.errorMessage);

        verify(economy, never()).withdrawPlayer(any(OfflinePlayer.class), anyDouble());
    }

    @Test
    void deposit_throwsWhenAmountIsZeroOrLess() {
        Economy economy = mock(Economy.class);
        VaultEconomyAdapter service = new VaultEconomyAdapter(economy);
        OfflinePlayer player = mock(OfflinePlayer.class);

        assertThrows(IllegalArgumentException.class, () -> service.deposit(player, 0));
        assertThrows(IllegalArgumentException.class, () -> service.deposit(player, -5));
    }

    @Test
    void withdraw_throwsWhenAmountIsZeroOrLess() {
        Economy economy = mock(Economy.class);
        VaultEconomyAdapter service = new VaultEconomyAdapter(economy);
        OfflinePlayer player = mock(OfflinePlayer.class);

        assertThrows(IllegalArgumentException.class, () -> service.withdraw(player, 0));
        assertThrows(IllegalArgumentException.class, () -> service.withdraw(player, -5));
    }
}