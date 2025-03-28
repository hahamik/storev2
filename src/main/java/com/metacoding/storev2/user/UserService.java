package com.metacoding.storev2.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void 회원가입(UserRequest.JoinDTO joinDTO) {
        // 이미 존재하는 username???
        User user = userRepository.findByUsername(joinDTO.getUsername());
        // 잇으면 터뜨림
        if (user != null) throw new RuntimeException("이미 존재하는 아이디입니다.");
        // 없으면 로그인
        userRepository.save(joinDTO.getUsername(), joinDTO.getPassword(), joinDTO.getFullname());
    }

    public User 로그인(UserRequest.LoginDTO loginDTO) {
        // 존재하는 username??
        User user = userRepository.findByUsername(loginDTO.getUsername());
        // 없으면 터뜨림
        if (user == null) throw new RuntimeException("존재하지 않는 아이디");
        // 비밀번호 맞음??
        if (!(user.getPassword().equals(loginDTO.getPassword()))) throw new RuntimeException("password 틀림");
        // 로그인
        return userRepository.login(loginDTO.getUsername());
    }
}
