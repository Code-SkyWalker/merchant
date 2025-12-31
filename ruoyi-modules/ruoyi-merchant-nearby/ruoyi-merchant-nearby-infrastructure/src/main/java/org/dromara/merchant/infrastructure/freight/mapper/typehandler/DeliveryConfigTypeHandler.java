package org.dromara.merchant.infrastructure.freight.mapper.typehandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.dromara.merchant.client.freight.dto.data.command.DeliveryConfig;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/31 13:54
 */
public class DeliveryConfigTypeHandler implements TypeHandler<DeliveryConfig> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void setParameter(PreparedStatement ps, int i, DeliveryConfig parameter, JdbcType jdbcType) throws SQLException {
        try {
            ps.setString(i, objectMapper.writeValueAsString(parameter));
        } catch (JsonProcessingException e) {
            throw new SQLException("Failed to serialize Rule to JSON", e);
        }
    }

    @Override
    public DeliveryConfig getResult(ResultSet rs, String columnName) throws SQLException {
        String jsonValue = rs.getString(columnName);
        if (jsonValue == null || jsonValue.isEmpty()) return null;
        try {
            return objectMapper.readValue(jsonValue, DeliveryConfig.class);
        } catch (JsonProcessingException e) {
            throw new SQLException("Failed to deserialize JSON to Rule", e);
        }
    }

    @Override
    public DeliveryConfig getResult(ResultSet rs, int columnIndex) throws SQLException {
        String jsonValue = rs.getString(columnIndex);
        if (jsonValue == null || jsonValue.isEmpty()) return null;
        try {
            return objectMapper.readValue(jsonValue, DeliveryConfig.class);
        } catch (JsonProcessingException e) {
            throw new SQLException("Failed to deserialize JSON to Rule", e);
        }
    }

    @Override
    public DeliveryConfig getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String jsonValue = cs.getString(columnIndex);
        if (jsonValue == null || jsonValue.isEmpty()) return null;
        try {
            return objectMapper.readValue(jsonValue, DeliveryConfig.class);
        } catch (JsonProcessingException e) {
            throw new SQLException("Failed to deserialize JSON to Rule", e);
        }
    }
}
