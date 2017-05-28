package com.timxyz.controllers;

import javax.validation.Valid;

import com.timxyz.controllers.forms.MyAccount.MyAccountUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.timxyz.models.Account;
import com.timxyz.services.AccountService;
import com.timxyz.services.TokenAuthenticationService;
import com.timxyz.services.exceptions.ServiceException;

@RestController
public class MyAccountController extends BaseController<Account, AccountService> {

	@ResponseBody
	public ResponseEntity get(@RequestHeader("Authorization") String token) {
		Account account = TokenAuthenticationService.findAccountByToken(token);
		
		if (account == null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		return ResponseEntity.ok(account);
	}

	@Transactional
	@ResponseBody
	public ResponseEntity update(@RequestHeader("Authorization") String token, @RequestBody @Valid MyAccountUpdateForm updatedAccount) {
		Account account = TokenAuthenticationService.findAccountByToken(token);
		
		if (account == null || !BCrypt.checkpw(updatedAccount.getCurrentPassword(), account.getPassword())) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		try {
			if (updatedAccount.isPasswordUpdated()) {
			    account.setRawPassword(updatedAccount.getNewPassword());
            }

			account = service.save(account);

			logForUpdate(token, account);
		} catch (ServiceException e) {
			return error(e);
		}

		return ResponseEntity.ok(account);
	}
}
