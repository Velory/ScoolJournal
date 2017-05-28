package sirotkina.sjournal.dao;

import sirotkina.sjournal.entity.Marks;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static sirotkina.sjournal.utils.DatabaseUtils.*;

public class MarksDAO extends AbstractDAO<Marks> {

    @Override
    protected String getTableName() {
        return "marks";
    }

    @Override
    protected Marks createEntityFromRS(ResultSet rs) throws SQLException {
        return new Marks(rs.getInt("id"), rs.getInt("mark"),
                rs.getString("comment"),
                lessonDAO().getById(rs.getInt("lessonId")),
                usersDAO().getById(rs.getInt("studentsId")));
    }

    @Override
    protected String getSaveQuery() {
        return "INSERT INTO marks (id, mark, comment, lessonId, studentsId) VALUES(?, ?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE marks SET mark=?, comment=?, lessonId=?, studentsId=? WHERE id=?";
    }

    @Override
    protected void prepareSaveInsertQuery(PreparedStatement ps, Marks mark) throws SQLException {
        ps.setInt(1, mark.getId());
        ps.setInt(2, mark.getMark());
        ps.setString(3, mark.getComment());
        ps.setInt(4, mark.getLessonFKId().getId());
        ps.setInt(5, mark.getStudentsFKId().getId());
    }

    @Override
    protected void prepareUpdateInsertQuery(PreparedStatement ps, Marks mark) throws SQLException {
        ps.setInt(1, mark.getMark());
        ps.setString(2, mark.getComment());
        ps.setInt(3, mark.getLessonFKId().getId());
        ps.setInt(4, mark.getStudentsFKId().getId());
        ps.setInt(5, mark.getId());
    }
}
