package com.yapp.api.domain.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

/**
 * Author : daehwan2yo
 * Date : 2022/07/16
 * Info :
 **/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {
    private T results;
    private Info info;

    public static <T> PageResponse of(T results, Page page) {
        return new PageResponse(results, Info.from(page));
    }

    @Getter
    @NoArgsConstructor
    private static class Info {
        private int totalCount;
        private int pageCount;
        private int pageSize;

        public Info(int totalCount, int pageCount, int size) {
            this.totalCount = totalCount;
            this.pageCount = pageCount;
            this.pageSize = size;
        }

        public static Info from(Page page) {
            return new Info((int) page.getTotalElements(), page.getTotalPages(), page.getSize());
        }
    }
}
