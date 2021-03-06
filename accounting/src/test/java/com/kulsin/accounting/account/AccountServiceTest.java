package com.kulsin.accounting.account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private AccountService accountService;

    @Test
    void getBalanceTest_Success() {
        Account account = new Account(123L, 5.5, "EUR");
        when(accountRepository.getById(123L)).thenReturn(account);

        double response = accountService.getBalance(123L);

        assertEquals(5.5, response);
        verify(accountRepository, times(1)).getById(123L);
    }

    @Test
    void updatePlayerBalanceTest_Success() {
        Account account = new Account(123L, 5.5, "EUR");
        when(accountRepository.save(account)).thenReturn(account);

        Account response = accountService.updatePlayerBalance(123L, 5.5, "EUR");

        assertEquals(account, response);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void accountExistTest_Success() {
        when(accountRepository.existsById(9988L)).thenReturn(true);

        assertTrue(accountService.accountExist(9988L));
        verify(accountRepository, Mockito.times(1)).existsById(9988L);
    }

    @Test
    void getBalanceTest_Failure_AccountServiceException() {
        when(accountRepository.getById(123L)).thenThrow(new RuntimeException());

        assertThrows(AccountServiceException.class, () -> accountService.getBalance(123L));

        verify(accountRepository, times(1)).getById(123L);
    }

    @Test
    void updatePlayerBalanceTest_Failure_AccountServiceException() {
        Account account = new Account(123L, 5.5, "EUR");
        when(accountRepository.save(account)).thenThrow(new RuntimeException());

        assertThrows(AccountServiceException.class, () -> accountService.updatePlayerBalance(123L, 5.5, "EUR"));

        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void accountExistTest_Failure_AccountServiceException() {
        when(accountRepository.existsById(9988L)).thenThrow(new RuntimeException());

        assertThrows(AccountServiceException.class, () -> accountService.accountExist(9988L));

        verify(accountRepository, Mockito.times(1)).existsById(9988L);
    }

}