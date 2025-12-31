package org.dromara.merchant.infrastructure.marketing.mapper.typehandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.dromara.merchant.client.marketing.dto.data.command.Rule;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description Rule类型处理器，用于处理Rule接口及其实现类的JSON序列化/反序列化
 * @Author Code Skywalker
 * @Date 2025/12/31 13:11
 */
public class RuleTypeHandler implements TypeHandler<Rule> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void setParameter(PreparedStatement ps, int i, Rule parameter, JdbcType jdbcType) throws SQLException {
        try {
            ps.setString(i, objectMapper.writeValueAsString(parameter));
        } catch (JsonProcessingException e) {
            throw new SQLException("Failed to serialize Rule to JSON", e);
        }
    }

    @Override
    public Rule getResult(ResultSet rs, String columnName) throws SQLException {
        String jsonValue = rs.getString(columnName);
        if (jsonValue == null || jsonValue.isEmpty()) return null;
        try {
            return objectMapper.readValue(jsonValue, Rule.class);
        } catch (JsonProcessingException e) {
            throw new SQLException("Failed to deserialize JSON to Rule", e);
        }
    }

    @Override
    public Rule getResult(ResultSet rs, int columnIndex) throws SQLException {
        String jsonValue = rs.getString(columnIndex);
        if (jsonValue == null || jsonValue.isEmpty()) return null;
        try {
            return objectMapper.readValue(jsonValue, Rule.class);
        } catch (JsonProcessingException e) {
            throw new SQLException("Failed to deserialize JSON to Rule", e);
        }
    }

    @Override
    public Rule getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String jsonValue = cs.getString(columnIndex);
        if (jsonValue == null || jsonValue.isEmpty()) return null;
        try {
            return objectMapper.readValue(jsonValue, Rule.class);
        } catch (JsonProcessingException e) {
            throw new SQLException("Failed to deserialize JSON to Rule", e);
        }
    }
}
