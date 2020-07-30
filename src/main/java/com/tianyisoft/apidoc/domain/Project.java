package com.tianyisoft.apidoc.domain;

import com.tianyisoft.apidoc.validators.requiredIf.RequiredIf;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@Entity
@RequiredIf(field = "password", compareField = "open", bool = false, message = "当 open 为 false 时，密码不能为空")
public class Project extends AuditAble implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String name;
    private String description;
    @NotBlank
    private String slug;
    private Boolean open;
    private String password;
}
