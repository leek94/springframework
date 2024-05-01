package com.mycompany.springframework.dao.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.springframework.dto.Ch13Board;
import com.mycompany.springframework.dto.Ch13Pager;

@Mapper
public interface Ch13BoardDao {
	public int insert(Ch13Board board); // 리턴 값은 void가 아니고 int로 변경하는 게 좋음
	public int count();
	public List<Ch13Board> selectByPage(Ch13Pager pager);
	public Ch13Board selectByBno(int bno);
	public Ch13Board selectAttributeData(int bno);
	public int update(Ch13Board board);
	public int deleteByBno(int bno);
}