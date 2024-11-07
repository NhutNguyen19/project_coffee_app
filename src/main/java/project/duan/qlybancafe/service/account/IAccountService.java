package project.duan.qlybancafe.service.account;

import java.util.List;

import project.duan.qlybancafe.dto.request.AccountCreationRequest;
import project.duan.qlybancafe.dto.request.AccountUpdateRequest;
import project.duan.qlybancafe.dto.response.AccountResponse;

public interface IAccountService {

    AccountResponse createAccount(AccountCreationRequest request);

    List<AccountResponse> getAllAccounts();

    AccountResponse getAccountById(String id);

    AccountResponse updateAccount(AccountUpdateRequest request, String id);

    void deleteAccount(String id);

    AccountResponse getMyInfo();
}
