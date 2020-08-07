package com.tianyisoft.apidoc.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@Entity
public class Directory extends AuditAble implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String name;
    private Integer sort = 0;
    @NotBlank
    private Integer projectId;
    private Integer parentId;

    @OneToMany(targetEntity = Directory.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private List<Directory> directoryList;
}
