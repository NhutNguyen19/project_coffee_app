package project.duan.qlybancafe.service.role;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import project.duan.qlybancafe.dto.request.RoleRequest;
import project.duan.qlybancafe.dto.response.RoleResponse;
import project.duan.qlybancafe.mapper.RoleMapper;
import project.duan.qlybancafe.model.Role;
import project.duan.qlybancafe.repository.RoleRepository;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService implements IRoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public RoleResponse createRole(RoleRequest roleRequest) {
        Role role = roleMapper.toRole(roleRequest);
        roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<RoleResponse> getALl() {
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteRole(String roleId) {
        roleRepository.deleteById(roleId);
    }
}
