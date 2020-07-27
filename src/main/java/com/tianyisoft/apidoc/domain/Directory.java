package com.tianyisoft.apidoc.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@Entity
public class Directory extends AuditAble {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer sort;
    private Integer parentId;

    @OneToMany(targetEntity = Directory.class)
    @JoinColumn(name = "parentId")
    private List<Directory> directoryList;
}
