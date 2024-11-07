package project.duan.qlybancafe.service.account;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import project.duan.qlybancafe.dto.request.AccountCreationRequest;
import project.duan.qlybancafe.dto.response.AccountResponse;
import project.duan.qlybancafe.exception.AppException;
import project.duan.qlybancafe.exception.ErrorCode;
import project.duan.qlybancafe.mapper.AccountMapper;
import project.duan.qlybancafe.model.Account;
import project.duan.qlybancafe.repository.AccountRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
class AccountServiceTest {
    @Autowired
    AccountService accountService;

    @MockBean
    AccountMapper accountMapper;

    @MockBean
    AccountRepository accountRepository;

    AccountCreationRequest request;
    AccountResponse accountResponse;
    Account account;

    Account account1;
    Account account2;
    AccountResponse response1;
    AccountResponse response2;

    @BeforeEach
    public void setUp() {
        request = AccountCreationRequest.builder()
                .username("nhut1234")
                .password("123456")
                .phone("0344145287")
                .displayName("Nhut Nguyen")
                .build();
        accountResponse = AccountResponse.builder()
                .id("97b47616-cf7a-4d3f")
                .username("nhut1234")
                .phone("0344145287")
                .displayName("Nhut Nguyen")
                .build();
        account = Account.builder()
                .id("97b47616-cf7a-4d3f")
                .username("nhut1234")
                .phone("0344145287")
                .displayName("Nhut Nguyen")
                .build();
    }

    @BeforeEach
    public void initData(){
            MockitoAnnotations.openMocks(this);

            account1 = new Account();
            account1.setId("197b47616-cf7a-4d3f");
            account1.setUsername("user1");
            account1.setPhone("123456789");
            account1.setDisplayName("User One");

            account2 = new Account();
            account2.setId("297b47616-cf7a-4d3f");
            account2.setUsername("user2");
            account2.setPhone("987654321");
            account2.setDisplayName("User Two");

            response1 = new AccountResponse();
            response1.setId("197b47616-cf7a-4d3f");
            response1.setUsername("user1");
            response1.setPhone("123456789");
            response1.setDisplayName("User One");

            response2 = new AccountResponse();
            response2.setId("297b47616-cf7a-4d3f");
            response2.setUsername("user2");
            response2.setPhone("987654321");
            response2.setDisplayName("User Two");
    }

    @Test
    public void createAccount_valid_success() {
        when(accountRepository.existsByUsername(anyString())).thenReturn(false);
        when(accountRepository.existsByPhone(anyString())).thenReturn(false);
        when(accountRepository.save(any())).thenReturn(account);

        var response = accountService.createAccount(request);

        assertThat(response.getId()).isEqualTo("97b47616-cf7a-4d3f");
        assertThat(response.getUsername()).isEqualTo("nhut1234");
        assertThat(response.getPhone()).isEqualTo("0344145287");
        assertThat(response.getDisplayName()).isEqualTo("Nhut Nguyen");
    }

    @Test
    public void createAccount_invalid_success() {
        when(accountRepository.existsByUsername(anyString())).thenReturn(true);
        var exception = assertThrows(AppException.class, () -> accountService.createAccount(request));
        assertThat(exception.getErrorCode().getCode()).isEqualTo(1002);
        assertThat(exception.getMessage()).isEqualTo("User existed");
    }

    @Test
    public void createAccount_invalid_phone_success() {
        when(accountRepository.existsByPhone(anyString())).thenReturn(true);
        var exception = assertThrows(AppException.class, () -> accountService.createAccount(request));
        assertThat(exception.getErrorCode().getCode()).isEqualTo(1009);
        assertThat(exception.getMessage()).isEqualTo("Phone existed");
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    public void getAllAccounts() {
        when(accountRepository.findAll()).thenReturn(Arrays.asList(account1, account2));
        when(accountMapper.toAccountResponse(account1)).thenReturn(response1);
        when(accountMapper.toAccountResponse(account2)).thenReturn(response2);

        List<AccountResponse> result = accountService.getAllAccounts();

        Assertions.assertThat(result).hasSize(2);
        Assertions.assertThat(result.get(0)).isEqualToComparingFieldByField(response1);
        Assertions.assertThat(result.get(1)).isEqualToComparingFieldByField(response2);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void getAccountById_shouldReturnAccountResponse_whenUserIsAdmin() {
        when(accountRepository.findById("197b47616-cf7a-4d3f")).thenReturn(Optional.of(account));
        when(accountMapper.toAccountResponse(account)).thenReturn(response1);
        AccountResponse result = accountService.getAccountById("197b47616-cf7a-4d3f");
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("197b47616-cf7a-4d3f");
        assertThat(result.getUsername()).isEqualTo("user1");
        assertThat(result.getPhone()).isEqualTo("123456789");
        assertThat(result.getDisplayName()).isEqualTo("User One");
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void getAccountById_shouldThrowException_whenUserNotExists() {
        when(accountRepository.findById("non-existent-id")).thenReturn(Optional.empty());
        assertThatThrownBy(() -> accountService.getAccountById("non-existent-id"))
                .isInstanceOf(AppException.class)
                .hasMessage(ErrorCode.USER_NOT_EXISTED.getMessage());
    }

    @Test
    void updateAccount() {
    }

    @Test
    void deleteAccount() {
    }

    @Test
    void getMyInfo() {
    }
}