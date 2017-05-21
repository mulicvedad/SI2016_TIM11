package com.timxyz.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.timxyz.controllers.forms.MyAccount.UpdateMyAccountForm;
import com.timxyz.models.Account;
import com.timxyz.services.AccountService;
import com.timxyz.services.TokenAuthenticationService;
import com.timxyz.services.exceptions.ServiceException;

@RestController
public class MyAccountController extends BaseController<Account, AccountService> {

	private Account findAccountByToken(String token) {
		String username = TokenAuthenticationService.parseJwt(token);
		
		if (username == null) {
			return null;
		}

		return service.getByUsername(username);
	}
	
	@ResponseBody
	public ResponseEntity get(@RequestHeader("Authorization") String token) {
		Account account = findAccountByToken(token);
		
		if (account == null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		return ResponseEntity.ok(account);
	}
	
	@ResponseBody
	public ResponseEntity update(@RequestHeader("Authorization") String token, @RequestBody @Valid UpdateMyAccountForm updatedAccount) {
		Account account = findAccountByToken(token);
		
		if (account == null || !BCrypt.checkpw(updatedAccount.getCurrentPassword(), account.getPassword())) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		try {
			service.updatePassword(account, updatedAccount.getNewPassword());
		} catch (ServiceException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();
	}
}
