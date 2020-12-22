package cn.wolfcode.query;

public class EmployeeQueryObject extends QueryObject{
    private String keyword;
    private  Long deptId = -1L;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
}
