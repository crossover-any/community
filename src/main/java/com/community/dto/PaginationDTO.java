package com.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Tengxq
 * @Date 2019/10/16 9:46
 * @Describle
 * @Version 1.0
 */
@Data
public class PaginationDTO {
    private Integer page;
    private Integer totalPage;
    private boolean showFirstPage = true;
    private boolean showEndPage = true;
    private boolean showPrePage = true;
    private boolean showNextPage = true;
    private List<Integer> pages;
    private List<QuestionDTO> questions;

    public void setPagination(Integer page,Integer size,Integer totalCount){
        pages = new ArrayList<>();
        if (totalCount % size == 0)
            totalPage = totalCount/size;
        else
            totalPage = totalCount/size +1;
        if (page < 1 ) {
            this.page = 1;
        }
        else if(page > totalPage){
            this.page = totalPage;
        } else {
            this.page = page;
        }
        for(int i = 3 ;i>0;i--){
            if (page-i>0)
                pages.add(page-i);
        }
        pages.add(page);
        for(int i= 1 ; i<4 ; i++){
            if (page + 1 <= totalPage)
                pages.add( page+i);
        }

        if (pages.contains(1)){
            showFirstPage = false;
            showPrePage = false;
        }
        if (pages.contains(totalPage)){
            showNextPage =false;
            showEndPage = false;
        }



    }
}
