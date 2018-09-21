package space.eignatik.prt.datalayer.dataTools.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "teams")
public class Teams implements IEntity {
    @Id @GeneratedValue private int id;
    private int userId;
    private int teamId;

    public int getId() {
        return id;
    }

    public Teams setId(int id) {
        this.id = id;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public Teams setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public int getTeamId() {
        return teamId;
    }

    public Teams setTeamId(int teamId) {
        this.teamId = teamId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teams teams = (Teams) o;
        return id == teams.id &&
                userId == teams.userId &&
                teamId == teams.teamId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, teamId);
    }

    @Override
    public String toString() {
        return "Teams{" +
                "id=" + id +
                ", userId=" + userId +
                ", teamId=" + teamId +
                '}';
    }
}
