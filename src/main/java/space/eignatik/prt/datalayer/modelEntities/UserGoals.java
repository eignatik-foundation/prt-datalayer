package space.eignatik.prt.datalayer.modelEntities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "user_goals")
public class UserGoals implements IEntity {
    @Id @GeneratedValue private int id;
    private String goalName;
    private String description;
    private int userId;
    private Date startDate;
    private Date endDate;

    public int getId() {
        return id;
    }

    public UserGoals setId(int id) {
        this.id = id;
        return this;
    }

    public String getGoalName() {
        return goalName;
    }

    public UserGoals setGoalName(String goalName) {
        this.goalName = goalName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UserGoals setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public UserGoals setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public UserGoals setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public UserGoals setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserGoals userGoals = (UserGoals) o;
        return id == userGoals.id &&
                userId == userGoals.userId &&
                Objects.equals(goalName, userGoals.goalName) &&
                Objects.equals(startDate, userGoals.startDate) &&
                Objects.equals(endDate, userGoals.endDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, goalName, userId, startDate, endDate);
    }

    @Override
    public String toString() {
        return "UserGoals{" +
                "id=" + id +
                ", goalName='" + goalName + '\'' +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
