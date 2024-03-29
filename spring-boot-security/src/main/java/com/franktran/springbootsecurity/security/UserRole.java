package com.franktran.springbootsecurity.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.franktran.springbootsecurity.security.UserPermission.*;

public enum UserRole {

  ADMIN(Sets.newHashSet(STUDENT_READ, STUDENT_WRITE)),
  ADMINTRAINEE(Sets.newHashSet(STUDENT_READ)),
  STUDENT(Sets.newHashSet());

  private final Set<UserPermission> permissions;

  UserRole(Set<UserPermission> permissions) {
    this.permissions = permissions;
  }

  public Set<UserPermission> getPermissions() {
    return permissions;
  }

  public Set<GrantedAuthority> getAuthorities() {
    Set<GrantedAuthority> authorities = permissions.stream()
        .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
        .collect(Collectors.toSet());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + name()));
    return authorities;
  }
}
