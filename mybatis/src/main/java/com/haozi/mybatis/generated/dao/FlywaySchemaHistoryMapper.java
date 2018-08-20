package com.haozi.mybatis.generated.dao;

import com.haozi.mybatis.generated.entity.FlywaySchemaHistory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FlywaySchemaHistoryMapper {
    int deleteByPrimaryKey(Integer installedRank);

    int insert(FlywaySchemaHistory record);

    int insertSelective(FlywaySchemaHistory record);

    FlywaySchemaHistory selectByPrimaryKey(Integer installedRank);

    int updateByPrimaryKeySelective(FlywaySchemaHistory record);

    int updateByPrimaryKey(FlywaySchemaHistory record);
}