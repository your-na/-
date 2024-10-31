package com.study.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.study.board.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // 아이디 중복 체크용
}
