package org.alist.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckList {
    private String id;
    private String name;
    private List<String> listItems;

}
