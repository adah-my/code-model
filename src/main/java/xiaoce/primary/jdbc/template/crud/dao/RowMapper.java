package xiaoce.primary.jdbc.template.crud.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper
{
    Object mapRow(ResultSet rs) throws SQLException;
}
