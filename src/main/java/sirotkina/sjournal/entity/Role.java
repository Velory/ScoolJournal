package sirotkina.sjournal.entity;

public class Role extends Entity{
    private String role;

    public Role(Integer id, String role) {
        setId(id);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}
