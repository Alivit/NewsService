package ru.clevertec.news.builder.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;
import ru.clevertec.news.builder.TestBuilder;
import ru.clevertec.news.entity.News;
import ru.clevertec.news.entity.User;
import ru.clevertec.news.entity.enums.Role;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor(staticName = "aUser")
@With
public class UserTestBuilder implements TestBuilder<User> {

    private Long id = 1L;
    private String username = "Vitaliy";
    private String password = "1111";
    private LocalDateTime dateOfRegistry = LocalDateTime.now();
    private Set<Role> roles = Set.of(Role.ADMIN);

    @Override
    public User build() {
        return User.builder()
                .id(id)
                .username(username)
                .password(password)
                .dateOfRegistry(dateOfRegistry)
                .roles(roles)
                .build();
    }
}
