package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.RedEnvelope;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

public interface RedEnvelopeMapper extends OwnerMapper<RedEnvelope> {

    @Select("select count(distinct token) from red_envelope")
    @ResultType(Integer.class)
    Integer  getRedEnvelopePeopleNums();

    @Select("select count(distinct token) from red_envelope_log")
    @ResultType(Integer.class)
    Integer getRecivedRedEnvelopePeopleNums();

    @Select("select count(1) from red_envelope")
    @ResultType(Integer.class)
    Integer getRedEnvelopeNums();

    @Select("SELECT count(1) FROM red_envelope_log rel left join red_envelope  ef on rel.red_envelope_id = ef.id where ef.description = 'INVITATIONED' and rel.token = #{token} ")
    @ResultType(Integer.class)
    Integer checkNewPeopleByToken(@Param("token") String token);

}