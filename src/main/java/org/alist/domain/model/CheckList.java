package org.alist.domain.model;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CheckList {
    public Long id;
    public String name;
    public List<String> listItems;

}
