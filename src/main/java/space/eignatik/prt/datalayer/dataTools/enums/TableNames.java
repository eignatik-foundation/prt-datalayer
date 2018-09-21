package space.eignatik.prt.datalayer.dataTools.enums;

import space.eignatik.prt.datalayer.dataTools.entities.*;

public enum TableNames {
    DEP("department", Department.class),
    DEPS("departments", Departments.class),
    EMP_USER("emp_user", User.class),
    EMP("employee", Employee.class),
    EMP_SCORE("employee_score", EmployeeScore.class),
    GRADE("grade", Grade.class),
    GRADES("grades", Grades.class),
    MANAGERS("managers", Managers.class),
    PERM("permission", Permission.class),
    ROLE("role", Role.class),
    ROLE_PERMS("role_permissions", RolePermissions.class),
    TEAM("team", Team.class),
    TEAMS("teams", Teams.class),
    USR_GOALS("user_goals", UserGoals.class),
    USR_ROLES("user_roles", UserRoles.class);

    private String table;
    private Class entity;
    TableNames(String table, Class entity) {
        this.table = table;
        this.entity = entity;
    }

    public String getTable() {
        return table;
    }

    public String getEntityName() {
        return entity.getSimpleName();
    }

    public Class getEntity() {
        return entity;
    }
}
