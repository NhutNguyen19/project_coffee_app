package project.duan.qlybancafe.service.role;

import java.util.List;

import project.duan.qlybancafe.dto.request.RoleRequest;
import project.duan.qlybancafe.dto.response.RoleResponse;

public interface IRoleService {
    RoleResponse createRole(RoleRequest roleRequest);

    List<RoleResponse> getALl();

    void deleteRole(String roleId);
}
