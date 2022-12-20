package com.icolak.converter;

import com.icolak.dto.RoleDTO;
import com.icolak.service.RoleService;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding // not mandatory
public class RoleDtoConverter implements Converter<String, RoleDTO> {
// When we are trying to save a user we need RoleDTO for Role column in User List,
// since th:value="${role.id}" ("1", "2" or "3") in the form is string it gives error.
// So here we are converting that string to RoleDTO
    private final RoleService roleService;

    public RoleDtoConverter(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public RoleDTO convert(String source) {

        if (source == null || source.equals("")) { // Select -> ""
            return null;
        }

        // Since we need Long for findById method we convert source from string to Long
        return roleService.findById(Long.parseLong(source));
    }
}
