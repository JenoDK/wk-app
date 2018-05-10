package com.jeno.fantasyleague.data.service.email;

import java.util.UUID;

import com.jeno.fantasyleague.data.repository.PasswordResetTokenRepository;
import com.jeno.fantasyleague.exception.EmailException;
import com.jeno.fantasyleague.model.PasswordResetToken;
import com.jeno.fantasyleague.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetService {

	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;

	@Autowired
	private ApplicationEmailService emailService;

	public void sendPasswordResetMail(User user, String contextPath) throws EmailException {
		String token = UUID.randomUUID().toString();
		PasswordResetToken pwResetToken = new PasswordResetToken(user, token);
		passwordResetTokenRepository.save(pwResetToken);
		String url = contextPath + "/resetPassword?id=" + user.getId() + "&token=" + token;
		emailService.sendEmail(
				"Reset password " + user.getUsername(),
				"Click the following link to reset your password: " + url,
				user);
	}
}
