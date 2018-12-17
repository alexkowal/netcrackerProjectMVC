package com.myproject.netcracker.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class Mark {
    @Entity
    @Table(name = "ad_mark")
    public class Brand {

        @Id
        @Column(name = "id_mark")
        private Long MarkId;

        @Column(name = "id_user")
        private Long UserId;

        @Column(name = "id_adv")
        private Long AdvId;

        public Long getMarkId() {
            return MarkId;
        }

        public void setMarkId(Long markId) {
            MarkId = markId;
        }

        public Long getUserId() {
            return UserId;
        }

        public void setUserId(Long userId) {
            UserId = userId;
        }

        public Long getAdvId() {
            return AdvId;
        }

        public void setAdvId(Long advId) {
            AdvId = advId;
        }
    }
}