package com.ll.mutbooks.member.entity;

import lombok.Getter;

@Getter
public enum MemberRole {
    USER("USER"), AUTHOR("AUTHOR"), ADMIN("ADMIN");

    MemberRole(String level) {
        this.level = level;
    }

    private final String level;
}
