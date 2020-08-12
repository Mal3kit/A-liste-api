package org.alist.infrastructure.persistence.postgresql.entity;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "checklist")
public class CheckListEntity extends BaseEntity{
    public String name;

    @ElementCollection
    public List<String> listItems;
}
