package com.cognizant.Comment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageOfItems<T> {
    private List<T> items;
    private boolean hasNext;
    private int totalElements;

    public PageOfItems(Page<T> page) {
        this.items = page.toList();
        this.hasNext = page.hasNext();
        this.totalElements = (int)page.getTotalElements();
    }

}
