package com.mycompany.springframework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.springframework.dao.mybatis.Ch13AccountDao;
import com.mycompany.springframework.dto.Ch15Account;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Ch15Service {
	
	@Autowired
	private Ch13AccountDao accountDao;
	
	//전체 계좌를 보여주는 기능
	public List<Ch15Account> getAccountList(){
		List<Ch15Account> accountList = accountDao.selectAll();
		return accountList;
		
	}
	
	@Transactional
	public void transfer(int fromAno, int toAno, int amount) {
		
		// 출금 작업
		// DB에서 금액 조회
		Ch15Account fromAccount = accountDao.selectByAno(fromAno);
		// 출금 계좌 없을 시
		if(fromAccount == null) {
			throw new RuntimeException("출금 계좌 없음");
		}
		
		// 기존 계좌 금액에서 출금액 뺌
		fromAccount.setBalance(fromAccount.getBalance() - amount);
		// 출금 이후 금액 수정
		accountDao.updateBalance(fromAccount);
		
		
		
		// 입금 작업
		Ch15Account toAccount = accountDao.selectByAno(toAno);
		// 입금 계좌 없을 시
		if(toAccount == null) {
			throw new RuntimeException("입금 계좌 없음");
		}
		
		// 입금 받은 계좌의 잔액 추가
		toAccount.setBalance(toAccount.getBalance() + amount);
		// 출금 이후 금액 수정
		accountDao.updateBalance(toAccount);
		
		
	}
}
