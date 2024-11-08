package com.study.board.dto;

public class UserDTO {
    private String username;
    private String password;
    private String nickname;
    private String phone;
    private String email;
    private String name;
    private String birthdate;
    public UserDTO() {}

    // 생성자
    public UserDTO(String username, String password, String nickname, String phone, String email, String name, String birthdate) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
        this.email = email;
        this.name = name;
        this.birthdate = birthdate;
    }

    // Getter 및 Setter 추가

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}
