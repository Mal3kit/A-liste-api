package org.alist.infrastructure.persistence.postgresql.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.util.Date;

@MappedSuperclass
public class BaseEntity extends PanacheEntityBase {
    @Id
    @Column(name = "id", columnDefinition = "SERIAL")
    public Long id;

    @CreationTimestamp
    public Date creationDate;

    @UpdateTimestamp
    public Date updatedDate;

    @Version
    public int version;
}
