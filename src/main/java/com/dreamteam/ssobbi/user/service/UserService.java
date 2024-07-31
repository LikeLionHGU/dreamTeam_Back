package com.dreamteam.ssobbi.user.service;

import com.dreamteam.ssobbi.base.exception.NotFoundException;
import com.dreamteam.ssobbi.user.controller.request.UserAlarmMessageRequest;
import com.dreamteam.ssobbi.user.controller.request.UserIncomeRequest;
import com.dreamteam.ssobbi.user.controller.response.UpdateUseIncome;
import com.dreamteam.ssobbi.user.controller.response.UpdateUserInfoAboutPhone;
import com.dreamteam.ssobbi.user.entity.User;
import com.dreamteam.ssobbi.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	@Transactional
	public UpdateUserInfoAboutPhone updatePhoneNumber(Long id, UserAlarmMessageRequest userAlarmMessageRequest) {
		User user = userRepository.findById(id).orElseThrow(()-> new NotFoundException("유저 정보가 DB에 없습니다."));
		user.setPhoneNumber(userAlarmMessageRequest.getUserPhoneNumber());

		return new UpdateUserInfoAboutPhone(user.getName(), user.getPhoneNumber());
	}

	@Transactional
	public UpdateUserInfoAboutPhone deletePhoneNumber(Long id) {
		User user = userRepository.findById(id).orElseThrow(()-> new NotFoundException("유저 정보가 DB에 없습니다."));
		user.setPhoneNumber(null);

		return new UpdateUserInfoAboutPhone(user.getName(), user.getPhoneNumber());
	}

	@Transactional
	public UpdateUseIncome updateIncome(Long id, UserIncomeRequest userIncomeRequest) {
		User user = userRepository.findById(id).orElseThrow(()-> new NotFoundException("유저 정보가 DB에 없습니다"));
		user.setIncome(userIncomeRequest.getIncome());

		return new UpdateUseIncome(user.getName(), user.getIncome());
	}

	public UpdateUseIncome getIncome(Long id) {
		User user = userRepository.findById(id).orElseThrow(()-> new NotFoundException("유저 정보가 DB에 없습니다"));
		return new UpdateUseIncome(user.getName(), user.getIncome());
	}
}
