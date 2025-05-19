package settings.typehandle;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import settings.entity.Email;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(value = JdbcType.VARCHAR)
public class TypeHandleForMailSplit extends BaseTypeHandler<Email> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Email parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i,parameter.getEmailPre() + parameter.getEmailSuf());
    }

    @Override
    public Email getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String string = rs.getString(columnName);
        if(string != null && !string.equals("")){

            int i = string.indexOf("@");
            if(i != -1){
                Email email = new Email();
                email.setEmailPre(string.substring(0,i));
                email.setEmailSuf(string.substring(i + 1));
                return email;
            }
        }
        return null;
    }

    @Override
    public Email getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String string = rs.getString(columnIndex);
        if(string != null && !string.equals("")){
            int i = string.indexOf("@");
            if(i != -1){
                Email email = new Email();
                email.setEmailPre(string.substring(0,i));
                email.setEmailSuf(string.substring(i));
                return email;
            }
        }
        return null;
    }

    @Override
    public Email getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String string = cs.getString(columnIndex);
        if(string != null && !string.equals("")){

            int i = string.indexOf("@");
            if(i != -1){
                Email email = new Email();
                email.setEmailPre(string.substring(0,i));
                email.setEmailSuf(string.substring(i + 1));
                return email;
            }
        }
        return null;
    }
}
