package com.ll.mutbooks.member.entity;

public enum AuthLevel {
    // 일반회원: 3, 관리자: 7
    NORMAL(3L), AUTHOR(5L), ADMIN(7L);

    private final Long level;

    private AuthLevel(Long level) {
        this.level = level;
    }

    public Long getLevel() {
        return level;
    }
}
