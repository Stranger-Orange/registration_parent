package com.orange.hospital.mapper;

import com.orange.hospital.model.HospitalSet;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Orange
 */

@Mapper
public interface HospitalSetMapper extends BaseMapper<HospitalSet> {

}