package me.dionclei.auth;

import java.util.List;

public record UserAuth(Long id, List<String> roles) {

}
