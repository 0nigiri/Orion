package com.desafio.orion.models;

import com.desafio.orion.validator.IsValidRut;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserDTO
{
    private Long id;

    @NotBlank(message = "Digite seu primeiro nome")
    private String firstName;

    @NotBlank(message = "Digite seu ultimo nome")
    private String lastName;

    @NotBlank(message = "Digite seu nome do usuario")
    private String username;

    @NotBlank(message = "Digite seu email")
    @Email
    private String email;

    @NotBlank(message = "Digite sua senha")
    @Pattern(regexp ="^(?=.{12}$)(?=(?:.*?[a-z]){2,})(?=(?:.*?[A-Z]){2,})(?=(?:.*.*\\W){2,})(?!.*(.)\\1{2}).*$",
            message = "Senha precisa ter 12 caracteres, 2 minusculos, 2 maiusculos, 2 especiais  e sem repetir o mesmo caracteres por 3 vezes sequencialmente")
    private String password;

    @IsValidRut
    private String rut;

    private boolean active = false;

    private String roles = "";

    private String permissions = "";

    public UserDTO(String username, String password, String roles, String permissions) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.permissions = permissions;
        this.active = true;
    }

    public List<String> getRoleList() {
        if (this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionList() {
        if (this.permissions.length() > 0) {
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }


}
