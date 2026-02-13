package com.system_alunos.Sistema_alunos.dtos;

import java.util.List;

public class PageResponse<T> {
    public List<T> data;
    public int page;
    public int size;
    public long totalElements;
    public int totalPages;
    public boolean hasNext;
    public boolean hasPrevious;

    public PageResponse(List<T> data, int page, int size, long totalElements, int totalPages, boolean hasNext, boolean hasPrevious) {
        this.data = data;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.hasNext = hasNext;
        this.hasPrevious = hasPrevious;
    }
}
