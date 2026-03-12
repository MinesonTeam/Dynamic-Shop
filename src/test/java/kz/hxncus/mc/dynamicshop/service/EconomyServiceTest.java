//package kz.hxncus.mc.dynamicshop.service;
//
//import kz.hxncus.mc.dynamicshop.DynamicShop;
//import net.milkbowl.vault.economy.Economy;
//import net.milkbowl.vault.economy.EconomyResponse;
//import org.bukkit.OfflinePlayer;
//import org.junit.jupiter.api.Test;
//
//import static javafx.beans.binding.Bindings.when;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class EconomyServiceTest {
//
//    @Test
//    void getBalance_returnsPlayerBalance() {
//        DynamicShop plugin = mock(DynamicShop.class);
//        Economy economy = mock(Economy.class);
//        OfflinePlayer player = mock(OfflinePlayer.class);
//
//        when(plugin.getEconomy()).thenReturn(economy);
//        when(economy.getBalance(player)).thenReturn(150.0);
//
//        EconomyService service = new EconomyService(plugin);
//
//        assertEquals(150.0, service.getBalance(player));
//    }
//
//    @Test
//    void deposit_callsVaultDeposit() {
//        DynamicShop plugin = mock(DynamicShop.class);
//        Economy economy = mock(Economy.class);
//        OfflinePlayer player = mock(OfflinePlayer.class);
//
//        EconomyResponse response = new EconomyResponse(
//                50.0,
//                200.0,
//                EconomyResponse.ResponseType.SUCCESS,
//                null
//        );
//
//        when(plugin.getEconomy()).thenReturn(economy);
//        when(economy.depositPlayer(player, 50.0)).thenReturn(response);
//
//        EconomyService service = new EconomyService(plugin);
//        EconomyResponse result = service.deposit(player, 50.0);
//
//        assertTrue(result.transactionSuccess());
//        assertEquals(200.0, result.balance);
//        verify(economy).depositPlayer(player, 50.0);
//    }
//
//    @Test
//    void withdraw_callsVaultWithdraw_whenPlayerHasEnoughMoney() {
//        DynamicShop plugin = mock(DynamicShop.class);
//        Economy economy = mock(Economy.class);
//        OfflinePlayer player = mock(OfflinePlayer.class);
//
//        EconomyResponse response = new EconomyResponse(
//                100.0,
//                50.0,
//                EconomyResponse.ResponseType.SUCCESS,
//                null
//        );
//
//        when(plugin.getEconomy()).thenReturn(economy);
//        when(economy.has(player, 100.0)).thenReturn(true);
//        when(economy.withdrawPlayer(player, 100.0)).thenReturn(response);
//
//        EconomyService service = new EconomyService(plugin);
//        EconomyResponse result = service.withdraw(player, 100.0);
//
//        assertTrue(result.transactionSuccess());
//        assertEquals(50.0, result.balance);
//        verify(economy).withdrawPlayer(player, 100.0);
//    }
//
//    @Test
//    void withdraw_failsWhenNotEnoughMoney() {
//        DynamicShop plugin = mock(DynamicShop.class);
//        Economy economy = mock(Economy.class);
//        OfflinePlayer player = mock(OfflinePlayer.class);
//
//        when(plugin.getEconomy()).thenReturn(economy);
//        when(economy.has(player, 100.0)).thenReturn(false);
//        when(economy.getBalance(player)).thenReturn(20.0);
//
//        EconomyService service = new EconomyService(plugin);
//        EconomyResponse result = service.withdraw(player, 100.0);
//
//        assertFalse(result.transactionSuccess());
//        assertEquals(20.0, result.balance);
//        assertEquals("Player does not have enough money", result.errorMessage);
//
//        verify(economy, never()).withdrawPlayer(any(OfflinePlayer.class), anyDouble());
//    }
//
//    @Test
//    void deposit_throwsWhenAmountIsZeroOrLess() {
//        DynamicShop plugin = mock(DynamicShop.class);
//        EconomyService service = new EconomyService(plugin);
//        OfflinePlayer player = mock(OfflinePlayer.class);
//
//        assertThrows(IllegalArgumentException.class, () -> service.deposit(player, 0));
//        assertThrows(IllegalArgumentException.class, () -> service.deposit(player, -5));
//    }
//
//    @Test
//    void withdraw_throwsWhenAmountIsZeroOrLess() {
//        DynamicShop plugin = mock(DynamicShop.class);
//        EconomyService service = new EconomyService(plugin);
//        OfflinePlayer player = mock(OfflinePlayer.class);
//
//        assertThrows(IllegalArgumentException.class, () -> service.withdraw(player, 0));
//        assertThrows(IllegalArgumentException.class, () -> service.withdraw(player, -5));
//    }
//}