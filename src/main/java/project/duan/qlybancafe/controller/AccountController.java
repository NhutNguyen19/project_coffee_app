package project.duan.qlybancafe.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import project.duan.qlybancafe.dto.ApiResponse;
import project.duan.qlybancafe.dto.request.AccountCreationRequest;
import project.duan.qlybancafe.dto.request.AccountUpdateRequest;
import project.duan.qlybancafe.dto.response.AccountResponse;
import project.duan.qlybancafe.service.account.IAccountService;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccountController {
    IAccountService iAccountService;

    @PostMapping("/registration")
    ApiResponse<AccountResponse> createAccount(@RequestBody @Valid AccountCreationRequest request) {
        return ApiResponse.<AccountResponse>builder()
                .result(iAccountService.createAccount(request))
                .message("Successfully created account")
                .build();
    }

    @GetMapping()
    ApiResponse<List<AccountResponse>> getAllAccounts() {
        return ApiResponse.<List<AccountResponse>>builder()
                .result(iAccountService.getAllAccounts())
                .message("Successfully retrieved all accounts")
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<AccountResponse> getAccountById(@PathVariable("id") String id) {
        return ApiResponse.<AccountResponse>builder()
                .result(iAccountService.getAccountById(id))
                .message("Successfully retrieved account")
                .build();
    }

    @GetMapping("/my-info")
    ApiResponse<AccountResponse> getMyInfo() {
        return ApiResponse.<AccountResponse>builder()
                .result(iAccountService.getMyInfo())
                .message("Successfully retrieved my info")
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<AccountResponse> updateAccount(
            @RequestBody @Valid AccountUpdateRequest request, @PathVariable String id) {
        return ApiResponse.<AccountResponse>builder()
                .result(iAccountService.updateAccount(request, id))
                .message("Successfully updated account")
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<AccountResponse> deleteAccount(@PathVariable String id) {
        iAccountService.deleteAccount(id);
        return ApiResponse.<AccountResponse>builder()
                .message("Successfully deleted account")
                .build();
    }
}
