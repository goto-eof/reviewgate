package com.ad.reviewgate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
public class ModelCommon {

        @JsonIgnore
        @CreatedDate
        @Column(name = "insert_date", updatable = false, insertable = false)
        protected LocalDateTime createdDate;

        @JsonIgnore
        @LastModifiedDate
        @Column(name = "update_date", updatable = true, insertable = false)
        protected LocalDateTime lastModifiedDate;

        @JsonIgnore
        @Column(name = "active")
        protected Boolean active = Boolean.TRUE;

        @Version
        private int version;

        public LocalDateTime getCreatedDate() {
                return createdDate;
        }

        public void setCreatedDate(LocalDateTime createdDate) {
                this.createdDate = createdDate;
        }

        public LocalDateTime getLastModifiedDate() {
                return lastModifiedDate;
        }

        public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
                this.lastModifiedDate = lastModifiedDate;
        }

        public Boolean getActive() {
                return active;
        }

        public void setActive(Boolean active) {
                this.active = active;
        }
}
