package org.alist.infrastructure.persistence.postgresql.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "checklist")
@EqualsAndHashCode
public class CheckListEntity extends BaseEntity{
    public String name;

    @ElementCollection
    public List<String> listItems;
}
