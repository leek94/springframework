package com.mycompany.springframework.dto;

import lombok.Data;

@Data
public class Ch13Pager {
   private int totalRows;        //페이징 대상이 되는 전체 행수
   private int totalPageNo;      //전체 페이지 수
   private int totalGroupNo;     //전체 그룹 수
   private int startPageNo;      //그룹의 시작 페이지 번호           1, 11, 21 ...
   											    //  1 2 3 4 5 6 7 8 9 10 -> 1 그룹
   												//  11 12 13 14 15 16 17 18 19 20 -> 2 그룹
   private int endPageNo;        //그룹의 끝 페이지 번호  10, 20 ,30 ...
   private int pageNo;           //현재 페이지 번호 -> 보고 싶은 페이지 정보
   private int pagesPerGroup;    //그룹당 페이지 수 -> 1 그룹이 10개임
   private int groupNo;          //현재 그룹 번호  6페이지 보고 있으면 -> 1 그룹
   private int rowsPerPage;      //페이지당 행 수  -> 한페이지당 몇 개를 보여 줄 것인지 10개로 설정함
   private int startRowNo;       //페이지의 시작 행 번호(1, ..., n) -> 1, 11, 21 이게 시작 로우넘버
   private int startRowIndex;    //페이지의 시작 행 인덱스(0, ..., n-1) for mysql
   private int endRowNo;         //페이지의 마지막 행 번호 -> 10개로 설정해서 10
   private int endRowIndex;      //페이지의 마지막 행 인덱스 -> DB에 따라 0 부터 시작하면 9가 될 수도 있고 1로 시작했으면 10이 됨
  
   public Ch13Pager(int rowsPerPage, int pagesPerGroup, int totalRows, int pageNo) { // 매개변수 4개의 값만 주면 다른 값은 자동으로 들어감
      this.rowsPerPage = rowsPerPage;
      this.pagesPerGroup = pagesPerGroup;
      this.totalRows = totalRows;
      this.pageNo = pageNo;
      													// [처음][이전] 1 2 3 4 5 [다음][맨끝]
      													// 			 6 7 8 9 10
      													// rowsPerpage = 10 -> 1페이지에 10개의 값을 봄 / pagesPerGroup = 5 -> 1개의 그룹에 5개의 페이지가 있음
      													// totlaRows=10000개   / pageNo = 1 1페이지를 보겠다.
      						

      totalPageNo = totalRows / rowsPerPage;
      if(totalRows % rowsPerPage != 0) totalPageNo++;
      // 10001일 경우 짜투리가 남으면  +1을 하면 페이지를 생성
      
      totalGroupNo = totalPageNo / pagesPerGroup;
      if(totalPageNo % pagesPerGroup != 0) totalGroupNo++;
      
      groupNo = (pageNo - 1) / pagesPerGroup + 1;
      
      startPageNo = (groupNo-1) * pagesPerGroup + 1;
      
      endPageNo = startPageNo + pagesPerGroup - 1;
      if(groupNo == totalGroupNo) endPageNo = totalPageNo;
      
      startRowNo = (pageNo - 1) * rowsPerPage + 1;
      startRowIndex = startRowNo - 1;
      endRowNo = pageNo * rowsPerPage;
      endRowIndex = endRowNo - 1; 
   }
}