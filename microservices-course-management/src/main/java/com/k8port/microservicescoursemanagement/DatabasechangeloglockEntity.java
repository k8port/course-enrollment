package com.k8port.microservicescoursemanagement;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "DATABASECHANGELOGLOCK", schema = "micro_course", catalog = "")
public class DatabasechangeloglockEntity {
    private int id;
    private boolean locked;
    private Timestamp lockgranted;
    private String lockedby;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "LOCKED")
    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Basic
    @Column(name = "LOCKGRANTED")
    public Timestamp getLockgranted() {
        return lockgranted;
    }

    public void setLockgranted(Timestamp lockgranted) {
        this.lockgranted = lockgranted;
    }

    @Basic
    @Column(name = "LOCKEDBY")
    public String getLockedby() {
        return lockedby;
    }

    public void setLockedby(String lockedby) {
        this.lockedby = lockedby;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatabasechangeloglockEntity that = (DatabasechangeloglockEntity) o;
        return id == that.id &&
                locked == that.locked &&
                Objects.equals(lockgranted, that.lockgranted) &&
                Objects.equals(lockedby, that.lockedby);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, locked, lockgranted, lockedby);
    }
}
