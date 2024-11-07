package project.duan.qlybancafe.service.account;

import java.util.HashSet;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import project.duan.qlybancafe.constant.PredefinedRole;
import project.duan.qlybancafe.dto.request.AccountCreationRequest;
import project.duan.qlybancafe.dto.request.AccountUpdateRequest;
import project.duan.qlybancafe.dto.response.AccountResponse;
import project.duan.qlybancafe.exception.AppException;
import project.duan.qlybancafe.exception.ErrorCode;
import project.duan.qlybancafe.mapper.AccountMapper;
import project.duan.qlybancafe.model.Account;
import project.duan.qlybancafe.model.Role;
import project.duan.qlybancafe.repository.AccountRepository;
import project.duan.qlybancafe.repository.RoleRepository;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccountService implements IAccountService {
    AccountMapper accountMapper;
    AccountRepository accountRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public AccountResponse createAccount(AccountCreationRequest request) {
        if (accountRepository.existsByUsername(request.getUsername())) throw new AppException(ErrorCode.USER_EXISTED);
        if (accountRepository.existsByPhone(request.getPhone())) throw new AppException(ErrorCode.PHONE_EXISTED);
        Account account = accountMapper.toAccount(request);
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        HashSet<Role> roles = new HashSet<>();
        roleRepository.findById(PredefinedRole.USER_ROLE).ifPresent(roles::add);
        account.setRoles(roles);
        accountRepository.save(account);
        return accountMapper.toAccountResponse(account);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<AccountResponse> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(accountMapper::toAccountResponse)
                .toList();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public AccountResponse getAccountById(String id) {
        return accountMapper.toAccountResponse(
                accountRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    @Override
    public AccountResponse updateAccount(AccountUpdateRequest request, String id) {
        Account account =
                accountRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        if (accountRepository.existsByPhone(request.getPhone())) throw new AppException(ErrorCode.PHONE_EXISTED);
        accountMapper.updateAccount(account, request);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        var roles = roleRepository.findAllById(request.getRoles());
        account.setRoles(new HashSet<>(roles));
        return accountMapper.toAccountResponse(accountRepository.save(account));
    }

    @Override
    public void deleteAccount(String id) {
        accountRepository.deleteById(id);
    }

    @Override
    public AccountResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        Account account =
                accountRepository.findById(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return accountMapper.toAccountResponse(account);
    }
}
